<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');


    $nombre = $_GET["nombre"];
    $sql = mysql_query("insert into empresa values (null, '$nombre');");
    $result = mysql_query($sql);
    
    echo $result;

//$sql = "insert into empresa values (null, $nombre);";
//$result = mysql_query($sql);
    $sql = "select * from empresa where nombre = '$nombre';";
    $result = mysql_query($sql);
    $json = array();
    
    if(mysql_num_rows($result)){
        while($row=mysql_fetch_assoc($result)){
            $json['empresa'][]=$row;
        }
    }
    mysql_close($con);
    echo json_encode($json);
?> 

