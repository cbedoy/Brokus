<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');


    $idempresa = $_GET["id"];
    
    $sql = "select * from usuario where empresa_idempresa = $idempresa;";
    $result = mysql_query($sql);
    if(mysql_num_rows($result)){
        $json = array();
        while($row = mysql_fetch_array($result)){
            $json['usuario'] = array(
                            'idUsuario' => $row[0],
                            'nombre' => $row[1],
                      );
        
        }
    }

  
    mysql_close($con);
    echo json_encode($json);
?> 

