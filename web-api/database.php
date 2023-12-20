<?php
class DB{
    private static $_instance = null;
    private $_pdo, $_query, $_error = false, $_results, $_count = 0;

    private function __construct(){
        try{
            $this->_pdo = new PDO('mysql:host=localhost;dbname=id21634510_ezgo;', 'id21634510_root', 'Ezgo$mobapp20');
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

    public function select($getField, $table, $where = null, $limit = null, $orderby = null, $orderDirection = 'ASC') {
        $sql = "SELECT {$getField} FROM {$table}";
    
        $value = array();
    
        if (is_array($where)) {
            if (count($where) === 3 && !$this->isRectangular($where)) {
                $operators = array('=', '>', '<', '>=', '<=');
    
                $field = $where[0];
                $operator = $where[1];
                $value = array($where[2]);
    
                if (in_array($operator, $operators)) {
                    $sql .= " WHERE {$field} {$operator} ?";
                }
            } else if ($this->isRectangular($where)) {
                $sql .= " WHERE ";
                $conditions = array();
                foreach ($where[0] as $index => $field) {
                    $operator = $where[1][$index];
                    $val = $where[2][$index];
            
                    $conditions[] = "{$field} {$operator} ?";
                    $value[] = $val;
                }
                $sql .= implode(" AND ", $conditions);
            }
        }
    
        if (!is_null($orderby)) {
            $sql .= " ORDER BY {$orderby} {$orderDirection}";
        }
    
        if (!is_null($limit)) {
            $sql .= " LIMIT {$limit}";
        }
    
        if (!$this->query($sql, $value)->error()) {
            return $this;
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
        if(count($fields) && count($where)){
            $set = null;
            $keys = array_keys($fields);
            $whereKeys = array_keys($where);
            $x = 1;

            foreach($keys as $key){
                $set .= "$key = ?";
                if($x < count($fields)){
                    $set .= ", ";
                }
                $x++;
            }
            
            $whereClause = null;
            foreach ($whereKeys as $key) {
                $whereClause .= "$key = ? AND ";
            }
            $whereClause = rtrim($whereClause, ' AND ');

            
            $sql = "UPDATE {$table} SET {$set} WHERE {$whereClause}";
            
            $fields = array_merge(array_values($fields), array_values($where));
            
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