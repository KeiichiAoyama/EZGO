<?php
include_once 'database.php';

class order{
    public function orderTicket(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tid = $request['productID'];
        $uid = $request['userID'];
        $pmt = $request['payMethod'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];
        $id = $request['id'];
        $uamt = $request['upAmount'];
            
        $fields = array('userID', 'paymentMethod', 'productID', 'tdAmount', 'tdTotalPrice');
        $values = array($uid, $pmt, $tid, $amt, $ttp);
        $assoc_fields = array_combine($fields, $values);
        
        if($db->insert('transaction_details', $assoc_fields)){
            $fields = array('tcSeat');
            $values = array($uamt);
            $assoc_array = array_combine($fields, $values);
            
            $where = array('ticketID', $id);
            
            if($db->update('tickets', $assoc_array, $where)){
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
        $tid = $request['productID'];
        $uid = $request['userID'];
        $pmt = $request['payMethod'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];
        $id = $request['id'];
        $uamt = $request['upAmount'];
            
        $fields = array('userID', 'paymentMethod', 'productID', 'tdAmount', 'tdTotalPrice');
        $values = array($uid, $pmt, $tid, $amt, $ttp);
        $assoc_fields = array_combine($fields, $values);
        
        if($db->insert('transaction_details', $assoc_fields)){
            $fields = array('hNights');
            $values = array($uamt);
            $assoc_array = array_combine($fields, $values);
            
            $where = array('hotelID', $id);
            
            if($db->update('hotel', $assoc_array, $where)){
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
        $tid = $request['productID'];
        $uid = $request['userID'];
        $pmt = $request['payMethod'];
        $amt = $request['tdAmount'];
        $ttp = $request['tdTotalPrice'];
        $id = $request['id'];
        $uamt = $request['upAmount'];
            
        $fields = array('userID', 'paymentMethod', 'productID', 'tdAmount', 'tdTotalPrice');
        $values = array($uid, $pmt, $tid, $amt, $ttp);
        $assoc_fields = array_combine($fields, $values);
        
        if($db->insert('transaction_details', $assoc_fields)){
            $fields = array('tpSlot');
            $values = array($uamt);
            $assoc_array = array_combine($fields, $values);
            
            $where = array('tourID', $id);
            
            if($db->update('tour_package', $assoc_array, $where)){
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
        $tixs = $db->select('*', 'tickets', $where)->getResult();

        if(count($tixs) > 0){
            $tix = $tixs[0];
            $imgPath = $tix->tcImage;
            $fullPath = "images/".$imgPath;
            $tix->tcImage = $fullPath;

            $response = ['Success'=> True, 'ticket' => $tix];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getHotelSingular(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $hid = $request['hotelID'];

        $where = array('hotelID', '=', $hid);
        $htls = $db->select('*', 'hotel', $where)->getResult();

        if(count($htls) > 0){
            $htl = $htls[0];
            $imgPath = $htl->hImage;
            $fullPath = "images/".$imgPath;
            $htl->hImage = $fullPath;

            $response = ['Success'=> True, 'hotel' => $htl];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTourSingular(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $hid = $request['tourID'];

        $where = array('tourID', '=', $trid);
        $trps = $db->select('*', 'tour_package', $where)->getResult();

        if(count($trps) > 0){
            $trp = $trps[0];
            $imgPath = $trp->tpImage;
            $fullPath = "images/".$imgPath;
            $trp->tpImage = $fullPath;

            $response = ['Success'=> True, 'tour' => $trp];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTicketSeveral(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $num = $request['number'];

        $tixs = $db->select('*', 'tickets', null, $num)->getResult();
        
        if(count($tixs) > 0){
            foreach($tixs as $tix){
                $imgPath = $tix->tcImage;
                $fullPath = "images/".$imgPath;
                $tix->tcImage = $fullPath;
            }

            $response = ['Success'=> True, 'tickets' => $tixs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getHotelSeveral(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $num = $request['number'];

        $htls = $db->select('*', 'hotel', null, $num)->getResult();
        
        if(count($htls) > 0){
            foreach($htls as $htl){
                $imgPath = $htl->hImage;
                $fullPath = "images/".$imgPath;
                $htl->hImage = $fullPath;
            }

            $response = ['Success'=> True, 'hotels' => $htls];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTourSeveral(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $num = $request['number'];

        $htls = $db->select('*', 'tour_package', null, $num)->getResult();
        
        if(count($trps) > 0){
            foreach($trps as $trp){
                $imgPath = $trp->tpImage;
                $fullPath = "images/".$imgPath;
                $trp->tpImage = $fullPath;
            }

            $response = ['Success'=> True, 'tours' => $trps];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTicketAll(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tixs = $db->select('*', 'tickets')->getResult();

        if(count($tixs) > 0){
            foreach($tixs as $tix){
                $imgPath = $tix->tcImage;
                $fullPath = "images/".$imgPath;
                $tix->tcImage = $fullPath;
            }

            $response = ['Success'=> True, 'tickets' => $tixs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getHotelAll(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $htls = $db->select('*', 'hotel')->getResult();

        if(count($htls) > 0){
            foreach($htls as $htl){
                $imgPath = $htl->hImage;
                $fullPath = "images/".$imgPath;
                $htl->hImage = $fullPath;
            }

            $response = ['Success'=> True, 'hotels' => $htls];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTourAll(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $trps = $db->select('*', 'tour_package')->getResult();

        if(count($trps) > 0){
            foreach($trps as $trp){
                $imgPath = $trp->tpImage;
                $fullPath = "images/".$imgPath;
                $trp->tpImage = $fullPath;
            }

            $response = ['Success'=> True, 'tours' => $trps];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getTicketSpecificType(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $type = $request['type'];

        $where = array('tcType', '=', $type);
        $tixs = $db->select('*', 'tickets', $where)->getResult();

        if(count($tixs) > 0){
            foreach($tixs as $tix){
                $imgPath = $tix->tcImage;
                $fullPath = "images/".$imgPath;
                $tix->tcImage = $fullPath;
            }

            $response = ['Success'=> True, 'tickets' => $tixs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function getMixedProd(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $city = $request['city'];

        $tixs = array();
        $htls = array();
        $trps = array();

        $where = array('tcFrom','=', $city);
        $tixs1 = $db->select('*', 'tickets', $where);
        
        if(count($tixs1) > 0){
            $where = array('tcDestination','=', $city);
            $tixs2 = $db->select('*', 'tickets', $where);

            if(count($tixs2) > 0){
                $tixs = $tixs1 + $tixs2;
                
                foreach($tixs as $tix){
                    $imgPath = $tix->tcImage;
                    $fullPath = "images/".$imgPath;
                    $tix->tcImage = $fullPath;
                }
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

        $where = array('cityID','=', $city);
        $htls = $db->select('*', 'hotel', $where);

        if(count($htls) > 0){
            $where = array('cityID','=', $city);
            $trps = $db->select('*', 'tour_package', $where);

            if(count($trps) > 0){
                $response = ['Success'=> True, 'tickets' => $tixs, 'hotels' => $htls, 'tours' => $trps];
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

    public function searchHotel(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $city = $request['cityID'];
        $date = $request['hDate'];
        $room = $request['hRoomType'];
        $night = $request['hNights'];

        $cols = array('cityID', 'hDate', 'hRoomType', 'hNights');
        $operator = array('=', '>=', '=', '>=');
        $vals = array($city, $date, $room, $night);
        $where = array($cols, $operator, $vals);

        $htls = $db->select('*', 'hotel', $where)->getResult();

        if(count($htls) > 0){
            $response = ['Success'=> True, 'Data' => $htls];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function searchTicket(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $from = $request['tcFrom'];
        $to = $request['tcDestination'];
        $date = $request['tcDate'];
        $seat = $request['tcSeat'];
        $type = $request['tcType'];

        $cols = array('tcFrom', 'tcDestination', 'tcDate', 'tcSeat', 'tcType');
        $operator = array('=', '=', '>=', '>=', '=');
        $vals = array($from, $to, $date, $seat, $type);
        $where = array($cols, $operator, $vals);

        $tixs = $db->select('*', 'tickets', $where)->getResult();

        if(count($tixs) > 0){
            $response = ['Success'=> True, 'Data' => $tixs];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function searchTour(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $city = $request['cityID'];
        $date = $request['tpDate'];
        $slot = $request['tpSlot'];

        $cols = array('cityID', 'tpDate', 'tpSlot');
        $operator = array('=', '>=', '>=');
        $vals = array($city, $date, $slot);
        $where = array($cols, $operator, $vals);

        $trps = $db->select('*', 'tour_package', $where)->getResult();

        if(count($trps) > 0){
            $response = ['Success'=> True, 'Data' => $trps];
            header('Content-Type: application/json');
            echo json_encode($response);
        }else{
            $response = ['Success'=> False];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    }

    public function productSearch(){
        $db = DB::getInstance();

        $tixs = $db->select('*', 'tickets')->getResult();

        if(count($tixs) > 0){
            foreach($tixs as $tix){
                $imgPath = $tix->tcImage;
                $fullPath = "images/".$imgPath;
                $tix->tcImage = $fullPath;
            }

            $htls = $db->select('*', 'hotel')->getResult();

            if(count($htls) > 0){
                foreach($htls as $htl){
                    $imgPath = $htl->hImage;
                    $fullPath = "images/".$imgPath;
                    $htl->hImage = $fullPath;
                }

                $trps = $db->select('*', 'tour_package')->getResult();

                if(count($trps) > 0){
                    foreach($trps as $trp){
                        $imgPath = $trp->tpImage;
                        $fullPath = "images/".$imgPath;
                        $trp->tpImage = $fullPath;
                    }

                    $cities = $db->select('*', 'city')->getResult();
                    
                    if(count($cities) > 0){
                        $response = ['Success'=> True, 'tickets' => $tixs, 'hotel' => $htls, 'tours' => $trps, 'cities' => $cities];
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
    
    public function homePopular(){
        $db = DB::getInstance();
        
        $tixs = $db->select('*', 'tickets', null, 2, 'tcRating', 'DESC')->getResult();
        $htls = $db->select('*', 'hotel', null, 2, 'hRating', 'DESC')->getResult();
        $trps = $db->select('*', 'tour_package', null, 2, 'tpRating', 'DESC')->getResult();

        if(count($tixs) && count($htls) && count($trps)){
            $response = ['Success'=> True, 'Data1' => $tixs, 'Data2' => $htls, 'Data3' => $trps];
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