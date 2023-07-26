<?php

    define('HOST', 'localhost');
    define('USER', 'mike');
    define('PASS', 'mertola21');
    define('DB', 'db_curso_udemy');
    define('CHARSET', 'utf8');

    
    $conn = mysqli_connect(HOST, USER, PASS, DB) or die("Falha!");
    
    mysqli_set_charset($conn, CHARSET);

?>