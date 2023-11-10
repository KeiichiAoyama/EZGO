<?php

namespace App\Controller;

use App\Tools\DB;

class order{
    public function orderTicket(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tix = $request['ticket'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

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

    public function orderHotel(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $htl = $request['hotel'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

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

    public function orderTour(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $trp = $request['tour'];
        $uid = $request['userID'];
        $pid = $request['payID'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];

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

    public function changeTicket(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tix = request['ticket'];

        $fields = array('tcSeat');
        $values = array($tix->tcSeat);
        $assoc_array = array_combine($fields, $values);
        
        $where = array('ticketID', $tix->ticketID);

        if($db->update('tickets', $assoc_array, $where)){
            $response = ['Success'=> True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function changeHotel(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $htl = request['hotel'];

        $fields = array('hNights');
        $values = array($htl->hNights);
        $assoc_array = array_combine($fields, $values);
        
        $where = array('hotelID', $htl->hotelID);

        if($db->update('hotel', $assoc_array, $where)){
            $response = ['Success'=> True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }   
    }

    public function changeTour(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $trp = request['tour'];

        $fields = array('tpSlot');
        $values = array($trp->tpSlot);
        $assoc_array = array_combine($fields, $values);
        
        $where = array('tourID', $trp->tourID);

        if($db->update('tour_package', $assoc_array, $where)){
            $response = ['Success'=> True];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }   
    }

    public function getTicketSingular(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tid = $request['ticketID'];

        $where = array('ticketID', '=', $tid);
        $tixs = $db->select('userID', 'users', $where)->getResult();

        if(count($tixs) > 0){
            $tix = $tixs[0];
            $response = ['Success'=> True, 'Ticket'];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }
}


?>