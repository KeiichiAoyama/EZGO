<?php

namespace App\Controller;

use App\Tools\DB;

class order{
    public function orderTicket($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tix = $request['ticket'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

        $tix_arr = get_object_vars($tix);

        $fields = array('userID', 'payID');
        $values = array($uid, $pid);
        $assoc_fields = array_combine($fields, $values);

        if($db->insert('transactions', $assoc_fields)){

            $tidAll = $db->select('ticketID', 'transactions')->getResult();
            $tid = $tidAll[count($tidAll)-1]->ticketID;

            $fields = array('transID', 'productID', 'tdAmount', 'tdTotalPrice');
            $values = array($tid, $tix->productID, $amt, $ttp);

            if($db->insert('transaction_details', $assoc_fields)){
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

    public function orderHotel($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $htl = $request['hotel'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

        $tix_arr = get_object_vars($tix);

        $fields = array('userID', 'payID');
        $values = array($uid, $pid);
        $assoc_fields = array_combine($fields, $values);

        if($db->insert('transactions', $assoc_fields)){

            $tidAll = $db->select('ticketID', 'transactions')->getResult();
            $tid = $tidAll[count($tidAll)-1]->ticketID;

            $fields = array('transID', 'productID', 'tdAmount', 'tdTotalPrice');
            $values = array($tid, $htl->productID, $amt, $ttp);

            if($db->insert('transaction_details', $assoc_fields)){
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

    public function orderTour($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $trp = $request['tour'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

        $tix_arr = get_object_vars($tix);

        $fields = array('userID', 'payID');
        $values = array($uid, $pid);
        $assoc_fields = array_combine($fields, $values);

        if($db->insert('transactions', $assoc_fields)){

            $tidAll = $db->select('ticketID', 'transactions')->getResult();
            $tid = $tidAll[count($tidAll)-1]->ticketID;

            $fields = array('transID', 'productID', 'tdAmount', 'tdTotalPrice');
            $values = array($tid, $trp->productID, $amt, $ttp);

            if($db->insert('transaction_details', $assoc_fields)){
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
}


?>