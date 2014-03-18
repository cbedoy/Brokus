<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');

$correo = $_GET["correo"];
$contrasena = $_GET["contrasena"];

$sql = "select idusuario, correo, contrasena, nombre, puesto, sector, empresa_idempresa, imagen from usuario where correo='$correo' and contrasena='$contrasena';";
$result = mysql_query($sql);
    $json = array();
    while($row = mysql_fetch_array($result)){
        $json['usuario'] = array(
                      'idUsuario' => $row[0],
                      'correo' => $row[1],
                      'contrasena' => $row[2],
                      'nombre' => $row[3],
                      'puesto' => $row[4],
                      'sector' => $row[5],
                      'empre_isempresa' => $row[6],
                      'imagen' => base64_encode($row[7]),
                      
        
        
        );
        
    }
//if(mysql_num_rows($result)){
//while($row=mysql_fetch_assoc($result)){
//    $json['usuario'][]=$row;
//    }
//}
    
//    mysql_query('SET CHARACTER SET utf8');
//    $sql = "select imagen from usuario where correo='$correo' and contrasena='$contrasena';";
//    $result = mysql_query($sql);
//    if(mysql_num_rows($result)){
//        while($row=mysql_fetch_assoc($result)){
//            $jsonImagen['imagen'][] = base64_encode($row['imagen']);
//        }
//    }
    
  
    
mysql_close($con);
echo json_encode($json);
 //   echo json_encode($jsonImagen);
?> 

