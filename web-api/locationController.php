<?php
include_once 'database.php';

class location{
    public function homePopular(){
        $db = DB::getInstance();

        $locs = $db->select('*', 'locations', null, 6, 'lLikes', 'DESC')->getResult();

        if(count($locs) > 0){
            $response = ['Success'=> True, 'Data' => $locs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
    
    public function explore(){
        $db = DB::getInstance();

        $locs = $db->select('*', 'locations', null, null, 'lLikes', 'DESC')->getResult();

        if(count($locs) > 0){
            $response = ['Success'=> True, 'Data' => $locs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
    
    
    public function like(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $lid = $request['locID'];
        $like = $request['lLikes'];
        
        $fields = array(
            'lLikes' => $like
        );
        $where = array(
            'locID' => $lid
        );
        $update = $db->update('locations', $fields, $where);
        if($update){
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