<?php

namespace App\Controller;

use App\Tools\DB;

class transaction{
    public function transAll(){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $uid = $request['userID'];

        $where = array('userID', '=', $uid);
        $trids = $db->select('transID', 'transaction', $where)->getResult();
        if(count($trids) > 0){
            $transArr = array();
            $prodSet = array();
            $prodArr = array();
            $tixArr = array();
            $hotArr = array();
            $turArr = array();

            $trdes = $db->select('*', 'transaction_details')->getResult();
            if(count($trdes) > 0){
                foreach ($trdes as $trde) {
                    if (in_array($trde->transID, $trids, true)) {
                        $transArr[] = $trde;
                        $prodSet[] = $trde->productID;
                    }
                }

                $prods = $db->select('*', 'product')->getResult();
                if(count($prods) > 0){
                    foreach ($prods as $prod) {
                        if (in_array($prod->productID, $prodSet, true)) {
                            $prodArr[] = $prod;
                        }
                    }
                }
            }

            $tixs = $db->select('*', 'tickets')->getResult();
            if(count($tixs) > 0){
                foreach ($tixs as $tix) {
                    if (in_array($tix->productID, $prodSet, true)) {
                        $tixArr[] = $tix;
                    }
                }
            }

            $hots = $db->select('*', 'hotel')->getResult();
            if(count($hots) > 0){
                foreach ($hots as $hot) {
                    if (in_array($hot->productID, $prodSet, true)) {
                        $hotArr[] = $hot;
                    }
                }
            }

            $tors = $db->select('*', 'tour_package')->getResult();
            if(count($tors) > 0){
                foreach ($tors as $tor) {
                    if (in_array($tor->productID, $prodSet, true)) {
                        $turArr[] = $tor;
                    }
                }
            }

            $response = ['Success' => True, 'Transactions' => $transArr, 'Products' => $prodArr, 'Tickets' => $tixArr, 'Hotels' => $hotArr, 'Tours' => $turArr];
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