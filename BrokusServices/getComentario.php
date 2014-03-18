<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');


    $idpublicacion=$_GET['id'];
    



    $sql = "SELECT Comentario.idComentario, Comentario.contenido, usuario.nombre FROM Comentario INNER JOIN usuario ON Comentario.usuario_idusuario = usuario.idusuario INNER JOIN publicacion ON Comentario.publicacion_idpublicacion = publicacion.idpublicacion WHERE publicacion.idpublicacion = $idpublicacion";
$result = mysql_query($sql);
$json = array();
 
if(mysql_num_rows($result)){
while($row=mysql_fetch_assoc($result)){
$json['comentario'][]=$row;
}
}
mysql_close($con);
echo json_encode($json); 
?> 

