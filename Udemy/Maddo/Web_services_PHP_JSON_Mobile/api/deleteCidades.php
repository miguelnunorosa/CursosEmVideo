<?php
    global $conection;

    const TABLE = "cidade";
    const SQL_DELETE = "DELETE FROM " . TABLE ;
    const API_TOKEN = "api_token_example";


    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        include('dbConnection.php');

        $get_api_token = "api_token_example";
        $api_idCidade = $_POST["api_idCidade"];

        if($get_api_token == API_TOKEN){
            $sql = SQL_DELETE . " WHERE id=" . $api_idCidade;
            $query = mysqli_prepare($conection, $sql);

            $response = array();
            mysqli_stmt_execute($query);

            if(mysqli_stmt_affected_rows($query) > 0){
                $response["deleted"] = true;

                echo json_encode($response);
            }else{
                $response["deleted"] = false;
                $response["SQL"] = $sql;

                echo json_encode($response);
            }
        }else{
            $response['auth_token'] = false;

            echo "Error. Authentication refused.";
        }
    }