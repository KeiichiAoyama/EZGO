<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $json = file_get_contents("php://input");
    $request = json_decode($json, true);

    if (isset($request['controller']) && isset($request['method'])) {
        $controllerClass = $request['controller'];
        $controllerMethod = $request['method'];

        $controllerClassName = ($controllerClass) . 'Controller';
        $controllerFile = $controllerClassName . '.php';

        if (file_exists($controllerFile)) {
            include $controllerFile;
            $controller = new $controllerClass();

            if (method_exists($controller, $controllerMethod)) {
                $controller->$controllerMethod();
            } else {
                $response = ['Success' => False, 'Message' => 1];
                header('Content-Type: application/json');
                echo json_encode($response);
            }
        } else {
            $response = ['Success' => False, 'Message' => 2];
            header('Content-Type: application/json');
            echo json_encode($response);
        }
    } else {
        $response = ['Success' => False, 'Message' => 3];
        header('Content-Type: application/json');
        echo json_encode($response);
    }
} else {
    $response = ['Success' => False, 'Message' => 4];
    header('Content-Type: application/json');
    echo json_encode($response);
}


?>