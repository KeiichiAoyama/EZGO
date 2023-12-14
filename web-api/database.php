<?php
class DB{
    private static $_instance = null;
    private $_pdo, $_query, $_error = false, $_results, $_count = 0;

    private function __construct(){
        try{
            $this->_pdo = new PDO('mysql:host=localhost;dbname=ezgo;', 'root', '');
        }catch(PDOException $e){
            die($e->getMessage());
        }
    }

    public static function getInstance(){
        if(!isset(self::$_instance)){
            self::$_instance = new DB();
        }
        return self::$_instance;
    }

    public function query($sql, $params = array()){
        $this->_error = false;
        if($this->_query = $this->_pdo->prepare($sql)){
            $x=1;
            if(count($params)){
                foreach($params as $param){
                    $this->_query->bindValue($x, $param);
                    $x++;
                }
            }
        }

        if($this->_query->execute()){
            $this->_results = $this->_query->fetchAll(PDO::FETCH_OBJ);
            $this->_count = $this->_query->rowCount();
        }else{
            $this->_error = true;
        }
        return $this;
    }

    public function getResult(){
        return $this->_results;
    }

    public function error(){
        return $this->_error;
    }

    public function isRectangular($array = array()) {
        if (!is_array($array) || empty($array)) {
            return false;
        }
        if (!is_array(reset($array))) {
            return false;
        }
        try {
            $x = count(array_unique(array_map(function($elem) {
                return is_array($elem) || $elem instanceof Countable ? count($elem) : 0;
            }, $array)));
            if ($x === 1) {
                return true;
            }
        } catch (Exception | Error $e) {
            return false;
        } catch (Throwable $t) {
            return false;
        } catch (TypeError $err) { 
            return false;
        }
        return false;
    }

    public function select($getField, $table, $where = null, $limit = null, $orderby = null){
        if(is_array($where) && count($where) === 3 && !($this->isRectangular($where))){
            $operators = array('=', '>', '<', '>=', '<=');

            $field = $where[0];
            $operator = $where[1];
            $value = $where[2];

            if(in_array($operator, $operators)){
                $sql = "SELECT {$getField} FROM {$table} WHERE {$field} {$operator} ?";
                if(!$this->query($sql, array($value))->error()){
                    return $this;
                }
            }
        }else if(is_null($where)){
            $sql = "SELECT {$getField} FROM {$table}";
            if(!$this->query($sql)->error()){
                return $this;
            }
        }else if(is_array($where) && $this->isRectangular($where)){
            $sql = "SELECT {$getField} FROM {$table} WHERE ";
            $x = 1;
            $value = array();
            for($i = 0; $i < count($where); $i++){
                $sql .= $where[$i][0]." ".$where[$i][1]." ?";
                if($x < count($where)){
                    $sql.=" AND ";
                }
                $value[] = $where[$i][2];
                $x++;
            }
            if (!is_null($limit)) {
                $sql .= " LIMIT {$limit}";
            }
            if(!is_null($orderby)){
                $sql .= " ORDER BY {$orderby}";
            }
            if(!$this->query($sql, $value)->error()){
                return $this;
            }
        }
        return false;
    }

    public function insert($table, $fields = array()){
        if(count($fields)){
            $keys = array_keys($fields);
            $values = null;
            $x = 1;

            foreach($fields as $field){
                $values .= "?";
                if($x < count($fields)){
                    $values .= ", ";
                }
                $x++;
            }

            $sql = "INSERT INTO {$table} (`".implode('`, `', $keys)."`) VALUES ({$values})";

            if(!$this->query($sql, $fields)->error()){
                return true;
            }
        }
        return false;
    }

    public function update($table, $fields, $where){
        if(count($fields) && count($where) === 2){
            $set = null;
            $keys = array_keys($fields);
            $x = 1;

            foreach($keys as $key){
                $set .= "$key = ?";
                if($x < count($fields)){
                    $set .= ", ";
                }
                $x++;
            }

            $sql = "UPDATE {$table} SET {$set} WHERE {$where[0]} = `{$where[1]}`";

            if (!$this->query($sql, $fields)->error()) {
                return true;
            }
        }
        return false;
    }

    public function delete($table, $where){
        $sql = "DELETE FROM {$table} WHERE ";
        $value = array();

        if($this->isRectangular($where)){
            $x=1;
            for($i = 0; $i < count($where); $i++){
                $sql .= $where[$i][0]." ".$where[$i][1]." ?";
                if($x < count($where)){
                    $sql.=" AND ";
                }
                $value[] = $where[$i][2];
                $x++;
            }
        }else{
            $sql .= $where[0]." ".$where[1]." ?";
            $value[] = $where[2];
        }

        if(!$this->query($sql, $value)->error()){
            return true;
        }
        return false;
    }
}