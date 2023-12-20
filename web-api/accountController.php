<?php
include_once 'database.php';

class account{
    public function showAccount(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];

        $where = array('userID', '=', $uid);
        $user = $db->select('*', 'users', $where);

        if(count($user) > 0){
            $imgPath = $user[0]->uProfilePicture;
            $fullPath = "images/".$imgPath;
            $user[0]->uProfilePicture = $fullPath;

            $response = ['Success'=> True, 'user' => $user[0]];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function updateAccount(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];
        $name = $request['uName'];
        $address = $request['uAddress'];
        $phone = $request['uPhone'];
        $email = $request['uEmail'];
        $bday = $request['uBirthdate'];
        $pfp = $request['image'];

        $file_name = basename($data['image']['name']);
        $file_path = 'images/' . $file_name;

        if (file_put_contents($file_path, base64_decode($data['image']['data']))) {
            $fields = array('uName', 'uAddress', 'uPhone', 'uEmail', 'uBirthdate', 'uProfilePicture');
            $values = array($name, $address, $phone, $email, $bday, $file_name);
            $assoc_array = array_combine($fields, $values);

            $where = array('userID', $uid);

            if($db->update('users', $assoc_array, $where)){
                $response = ['Success'=> True];
                header('Content-Type: application/json');
                echo json_encode($response);
            }else{
                $response = ['Success'=> False];
                header('Content-Type: application/json');
                echo json_encode($response);
            }
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
    
    public function delete(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];
        
        $where = array('userID', '=', $uid);
        if($db->delete('users', $where)){
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