<?php

namespace App\Controller;

use App\Tools\DB;

class city{
    public function list(){
        $db = DB::getInstance();

        $city = $db->select('*', 'city');

        if(count($city) > 0){
            $response = ['Success'=> True, 'cities' => $city];
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