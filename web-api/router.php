<?php

use App\Tools\NotFound;

$url = $_SERVER['REQUEST_URI'];
$urlSegments = explode('/', $url);
$nf = new NotFound();

if (count($urlSegments) >= 2) {
    $controllerClass = $urlSegments[0];
    $controllerMethod = $urlSegments[1];
} else {
    $nf->error("Wrong Request!");
}

$controllerFile = $controllerClass . '.php';

if (file_exists($controllerFile)) {
    include $controllerFile;
    $controller = new $controllerClass();

    if (method_exists($controller, $controllerMethod)) {
        $controller->$controllerMethod();
    } else {
        $nf->error("Method Not Found!");
    }
} else {
    $nf->error("Controller Not Found!");
}

?>