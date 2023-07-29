<?php
    global $conection;

    const TABLE = "cidade";
    const SQL_CREATE = "INSERT INTO " .TABLE. " (estadoID, nome)";
    const API_TOKEN = "api_token_example";


    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        include('dbConnection.php');

        $get_api_token = "api_token_example";
        $api_idCidade = $_POST["api_idCidade"];
        $api_nome = $_POST["api_nome"];

        if($get_api_token == API_TOKEN){
            $response = array();

            $sql = SQL_CREATE . "VALUES ('" . $api_nome . "' , '" . $api_nome . "')";
            $query = mysqli_stmt_prepare($conection, $sql);
            mysqli_stmt_execute($query);

            if(mysqli_stmt_num_rows($query) > 0){
                $response["inserted"] = true;

                echo json_encode($response);
            }else{
                $response["inserted"] = false;
                $response["SQL"] = $sql;

                echo json_encode($response);
            }
        }else{
            $response['auth_token'] = false;

            echo "Error. Authentication refused.";
        }
    }