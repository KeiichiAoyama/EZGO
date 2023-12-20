<?php
include_once 'database.php';

class transaction{
    public function transAll(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];

        $where = array('userID', '=', $uid);
        $trids = $db->select('productID', 'transaction_details', $where)->getResult();
        $tixs = $db->select('*', 'tickets')->getResult();
        $htls = $db->select('*', 'hotel')->getResult();
        $trps = $db->select('*', 'tour_package')->getResult();
        
        if(count($trids) > 0 && count($tixs) > 0 && count($htls) > 0 && count($trps) > 0){
            $id = array();
            
            foreach($trids as $trid){
                $id[] = $trid->productID;
            }
            
            $response = ['Success' => True, 'Data1' => $id, 'Data2' => $tixs, 'Data3' => $htls, 'Data4' => $trps];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success' => False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }


}

?>