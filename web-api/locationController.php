<?php

namespace App\Controller;

use App\Tools\DB;

class location{
    public function homePopular(){
        $db = DB::getInstance();

        $locs = $db->select('*', 'locations', null, 5, 'lLikes')->getResult();

        if(count($locs) > 0){
            foreach($locs as $loc){
                $imgPath = $loc->lImage;
                $fullPath = "images/".$imgPath;
                $loc->lImage = $fullPath;
            }

            $response = ['Success'=> True, 'locations' => $locs];
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