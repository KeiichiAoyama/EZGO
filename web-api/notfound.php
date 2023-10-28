<?php

namespace App\Tools;

class NotFound{
    public function error($message) {
        http_response_code(404);
        echo "404 - Page not found: " . $message;
    }
}

?>