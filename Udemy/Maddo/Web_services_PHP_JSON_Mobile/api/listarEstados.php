<?
    require_once('DBConnection.php');
    

    const TABLE = "estado";
    const SQL_READ = 'SELECT * FROM ' . TABLE ;//. " WHERE nome="


    $stmt = mysqli_prepare($conn, SQL_READ);

    mysqli_stmt_execute($stmt);
    mysqli_stmt_store_result($stmt);
    mysqli_stmt_bind_result($stmt, $id, $sigla, $nome);

    var_dump($stmt);


?>