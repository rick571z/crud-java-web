<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear Produto</title>
</head>
<body>

	<h1>Crear Producto</h1>
	
	<!--"producto"se mapea hacia el servlet con nombre: /producto  -->
	
	<form action="producto" method="post">
		<input type="hidden" name="opcion" value="guardar">
		<table border="1">
			
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" size="50"></td>	
			</tr>
			
			<tr>
				<td>Cantidad:</td>
				<td><input type="text" name="cantidad" size="50"></td>	
			</tr>
			
			<tr>
				<td>Precio:</td>
				<td><input type="text" name="precio" size="50"></td>	
			</tr>
			
		</table>
		<input type="submit" value="Guardar">
	</form>

</body>
</html>