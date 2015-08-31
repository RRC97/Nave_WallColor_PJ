<?php
	
	//error_reporting(0);
	
	if($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET["block"])
	&& $_GET["block"] == "paodebatatacomgergilimeagriao123")
	{
		$mysql_host = "127.0.0.1";
		$mysql_username = "root";
		$mysql_password = "";
		$mysql_db_name = "wallcolor";
		$mysql_connect = mysqli_connect($mysql_host,
			$mysql_username, $mysql_password, $mysql_db_name);
		
		$mysqli_query = "SELECT * FROM ranking";
		$mysqli_response = mysqli_query($mysql_connect, $mysqli_query);
		
		echo $mysqli_response->fetch_row;
		
		mysqli_close($mysql_connect);
	}
?>