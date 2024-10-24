<?php
    header('content-type: application/json');


    define('HOST', 'localhost');
    define('USER', 'mike');
    define('PASS', 'mertola21');
    define('DB', 'db_curso_udemy');
    define('CHARSET', 'utf8');
    define('API_TOKEN', 'api_token_example');


    $conection = mysqli_connect(HOST, USER, PASS, DB) or die("Falha!");

    mysqli_set_charset($conection, CHARSET);

?>