<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');

	$titulo = $_POST['titulo'];
	$contenido = $_POST['contenido'];
    $anexo = $_POST['anexo'];
    $fecha = $_POST['fecha'];
	$idUsuario = $_POST['idusuario'];
    $extencion = $_POST['extension'];
	
    
	$sql = mysql_query("insert into publicacion values (null, '$titulo', '$contenido', '$anexo', '$extencion','$fecha', $idUsuario);");
   
    
	$resultado = mysql_query($sql);
	$json = array();
    $json['respuesta'][0]='publicado';

	
	mysql_close($con);
echo json_encode($json);
?> 

