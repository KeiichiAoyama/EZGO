<?php

use App\Tools\NotFound;

$nf = new NotFound();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $request = $_REQUEST;

    if (isset($request['controller']) && isset($request['method'])) {
        $controllerClass = $request['controller'];
        $controllerMethod = $request['method'];

        $controllerClassName = ucfirst($controllerClass) . 'Controller';
        $controllerFile = $controllerClassName . '.php';

        if (file_exists($controllerFile)) {
            include $controllerFile;
            $controller = new $controllerClassName();

            if (method_exists($controller, $controllerMethod)) {
                $controller->$controllerMethod($request);
            } else {
                $nf->error("Method Not Found!");
            }
        } else {
            $nf->error("Controller Not Found!");
        }
    } else {
        $nf->error("Controller and Method parameters are required in the request.");
    }
} else {
    $nf->error("Unsupported HTTP Method!");
}


?>