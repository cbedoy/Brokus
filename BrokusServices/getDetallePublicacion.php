<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');


    $id = $_GET["id"];
    $sql = "select * from publicacion where idPublicacion = $id;";
    $result = mysql_query($sql);
    $json = array();
    
    if(mysql_num_rows($result)){
        $json = array();
        while($row = mysql_fetch_array($result)){
            $json['publicacion'][] = array(
                          'idpublicacion' => $row[0],
                          'titulo' => $row[1],
                          'contenido' => $row[2],
                                           'anexo' => base64_encode($row[3]),
                                           'extension' => $row[4],
                          'fecha' => $row[5],
                          'idusuario' => $row[6],
 
                          
                          
                          
                          );
        }
    }
    mysql_close($con);
    echo json_encode($json);
?> 

