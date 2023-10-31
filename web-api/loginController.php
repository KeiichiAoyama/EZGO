<?php

namespace App\Controller;

use App\Tools\DB;

class login{
    public function checkID($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];

        $where = array('userID', '=', $uid);
        if(count($db->select('userID', 'users', $where)->getResult()) > 0){
            $response = ['Success' => True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success' => False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function checkExist($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];

        $where = array('userID', '=', $uid);
        if(count($db->select('userID', 'users', $where)->getResult()) > 0){
            $response = ['Success' => False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success' => True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function createAccount($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];
        $name = $request['name'];

        $fields = array('userID', 'uName');
        $values = array($uid, $name);
        $assoc_fields = array_combine($fields, $values);

        if($db->insert('users', $assoc_fields)){
            $response = ['Success'=> True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
}

?>