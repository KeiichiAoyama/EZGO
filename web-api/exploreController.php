<?php

namespace App\Controller;

use App\Tools\DB;

class explore{
    public function getAll(){
        $db = DB::getInstance();

        $locs = $db->select('*', 'locations')->getResult();

        if(count($locs) > 0){
            foreach($locs as $loc){
                $imgPath = $loc->lImage;
                $fullPath = "images/".$imgPath;
                $loc->lImage = $fullPath;
            }

            $cities = $db->select('*', 'city')->getResult();
            if(count($cities) > 0){
                $response = ['Success'=> True, 'locations' => $locs, 'cities' => $cities];
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