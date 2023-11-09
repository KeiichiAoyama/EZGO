<?php

namespace App\Controller;

use App\Tools\DB;

class order{
    public function orderTicket($request){
        $db = DB::getInstance();
        $json = file_get_contents('php://input');

        $request = json_decode($json, true);
        $tix = $request['ticket'];

        $tix_arr = get_object_vars($tix);

        if($db->insert('users', $assoc_fields)){
            
        }
    }
}

?>