<?php
include_once 'database.php';

class city{
    public function list(){
        $db = DB::getInstance();

        $city = $db->select('*', 'city')->getResult();

        if(count($city) > 0){
            $response = ['Success'=> True, 'Data' => $city];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
    
    public function getCity(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $cid = $request['cityID'];
        
        $where = array('cityID', '=', $cid);
        $cityName = $db->select('cName', 'city', $where)->getResult();
        
        if(count($cityName) > 0){
            $response = ['Success'=> True, 'Data' => $cityName[0]->cName];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
    
    public function getCityTwo(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $cid1 = $request['cityIDfrom'];
        $cid2 = $request['cityIDdest'];
        
        $where = array('cityID', '=', $cid1);
        $cityName1 = $db->select('cName', 'city', $where)->getResult();
        
        $where = array('cityID', '=', $cid2);
        $cityName2 = $db->select('cName', 'city', $where)->getResult();
        
        if(count($cityName1) && count($cityName2)){
            $names = array($cityName1[0]->cName, $cityName2[0]->cName);
            $response = ['Success'=> True, 'Data' => $names];
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