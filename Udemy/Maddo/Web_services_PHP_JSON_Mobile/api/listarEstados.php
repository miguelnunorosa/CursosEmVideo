<?php
    global $conection;
    include ('dbConnection.php');



    const TABLE = "estado";
    const SQL_READ = 'SELECT * FROM ' . TABLE ;


    $query = mysqli_prepare($conection, SQL_READ);

    mysqli_stmt_execute($query);
    mysqli_stmt_store_result($query);
    mysqli_stmt_bind_result($query, $id, $sigla, $nome);

    $data = array();

    if(mysqli_stmt_num_rows($query) > 0){
        while (mysqli_stmt_fetch($query)){
            array_push($data, array("id"=>$id, "sigla"=>$sigla, "nome"=>$nome));
        }

        echo json_encode($data);
    }else{
        echo json_encode($data);
    }




?>