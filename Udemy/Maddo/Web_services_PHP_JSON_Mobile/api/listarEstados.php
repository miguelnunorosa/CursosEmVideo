<?php
    global $conection;

    const TABLE = "estado";
    const SQL_READ = 'SELECT * FROM ' . TABLE ;
    const API_TOKEN = "api_token_example";


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        include ('dbConnection.php');

        $get_api_token = "api_token_example";

        if($get_api_token == API_TOKEN){
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
                echo "Error. No data available.";
            }

        }else{
            $response['auth_key'] = false;
            echo "Error. Authentication refused.";
        }

    }

?>