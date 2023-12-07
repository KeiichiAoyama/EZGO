<?php

namespace App\Controller;

use App\Tools\DB;

class login{
    public function checkID(){
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

    public function checkExist(){
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

    public function login(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];
        $pw = $request['uPassword'];

        $where = array('userID', '=', $uid);
        $user = $db->select('*', 'users', $where)->getResult();
        if(count($user) > 0){
            if(strcasecmp($pw, $user[0]->uPassword) == 0){
                $response = ['Success' => True, 'User' => $user[0]];
                header('Content-Type: application/json');
                echo json_encode($response);
            }else{
                $response = ['Success' => False];
                header('Content-Type: application/json');
                echo json_encode($response);
            }
        }else{
            $response = ['Success' => False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function createAccount(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];
        $pw = $request['uPassword'];
        $name = $request['uName'];
        $mail = $request['uEmail'];
        $telp = $request['uPhone'];

        $fields = array('userID', 'uPassword', 'uName', 'uEmail', 'uPhone');
        $values = array($uid, $pw, $name, $mail, $telp);
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