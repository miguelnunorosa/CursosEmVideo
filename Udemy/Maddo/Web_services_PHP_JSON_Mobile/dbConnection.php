<?php

    define('HOST', 'localhost');
    define('USER', 'mike');
    define('PASS', 'mertola21');
    define('DB', 'db_curso_udemy');

    
    $conn = mysqli_connect(HOST, USER, PASS, DB) or die("Falha!");

?>