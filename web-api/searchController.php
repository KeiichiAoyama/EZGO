<?php
include_once 'database.php';

class search{
    public function explore(){
        $db = DB::getInstance();

        $locs = $db->select('*', 'locations')->getResult();
        $tixs = $db->select('*', 'tickets')->getResult();
        $htls = $db->select('*', 'hotel')->getResult();
        $trps = $db->select('*', 'tour_package')->getResult();

        if(count($locs) > 0 && count($tixs) > 0 && count($htls) > 0 && count($trps) > 0){
            $response = ['Success'=> True, 'Data1' => $locs, 'Data2' => $tixs, 'Data3' => $htls, 'Data4' => $trps];
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