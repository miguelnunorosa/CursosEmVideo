<?php
global $conection;
include ('dbConnection.php');



    const TABLE = "estado";
    const SQL_READ = 'SELECT * FROM ' . TABLE ;


    $query = mysqli_prepare($conection, SQL_READ);

    mysqli_stmt_execute($query);
    mysqli_stmt_store_result($query);
    mysqli_stmt_bind_result($query, $id, $sigla, $nome);


    var_dump($query);
?>