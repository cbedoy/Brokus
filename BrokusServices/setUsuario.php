<?php
$host="localhost"; //replace with database hostname 
$username="root"; //replace with database username 
$password=""; //replace with database password 
$db_name="brokus"; //replace with database name
 
$con=mysql_connect("$host", "$username", "$password")or die("cannot connect"); 

mysql_select_db("$db_name")or die("cannot select DB");

mysql_query('SET CHARACTER SET utf8');

$correo = $_GET["correo"];          //correo usuario
$contrasena = $_GET["contrasena"];  //contraseña usuario
$nombre = $_GET["nombre"];          //nombre usuario
$puesto = $_GET["puesto"];          //puesto usuario
$sector = $_GET["sector"];          //sector usuario
$imagen = $_GET["imagen"];          //imagen
    $empresa = $_GET["empresa"];         //empresa
$existeEmpresa = $_GET["esta"];       //existe empresa registrada
    $registrado = null;
    $result = null;
    $existe = null;
    //------------------------
    $sql = "select idusuario from usuario where correo = '$correo';";
    $result = mysql_query($sql);
    $json;
    $id = -1;
    if(mysql_num_rows($result)){
        while($row=mysql_fetch_assoc($result)){
            $id=$row;
        }
    }
    
		//echo $id;
    if($id == -1){
        if($existeEmpresa == 'Y'){   
		//Existe empresa en la base de datos
            $sql = "insert into usuario values (null, '$correo', '$contrasena', '$nombre', '$puesto', '$sector', '$imagen', (select idempresa from empresa where nombre = '$empresa' ) );";
            
			$result = mysql_query($sql);
			$sql = "select * from usuario where correo='$correo' and contrasena='$contrasena';";
			
			$result = mysql_query($sql);
            
            if(mysql_num_rows($result)){
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
				$json['respuesta'][0] = 'successfull';
            }else{
				$json['respuesta'][0] = 'error';
			}			
        }else{
		//no existe empresa en la base de datos
            $sql = "insert into empresa values (null, '$empresa')";
            mysql_query($sql);
            $sql = "insert into usuario values (null, '$correo', '$contrasena', '$nombre', '$puesto', '$sector', '$imagen', (select idempresa from empresa where nombre = '$empresa' ) );";
            $result = mysql_query($sql);
			$sql = "select * from usuario where correo='$correo' and contrasena='$contrasena'";
			$result = mysql_query($sql);
            
            if(mysql_num_rows($result)){
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
				$json['respuesta'][0] = 'successfull';
            }else{
				$json['respuesta'][0] = 'error';
			}
           
        
        }
		
    }else{
		$json['respuesta'][0] = 'error';
	}
    
    
    
    
mysql_close($con);
echo json_encode($json);

?> 

