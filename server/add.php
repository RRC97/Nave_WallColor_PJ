<?php
	
	error_reporting(0);
	
	if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["block"])
	&& isset($_POST["name"]) && isset($_POST["value"]) 
	&& $_POST["block"] == "paodebatatacomgergilimeagriao123")
	{
		$info1 = $_POST["name"];
		$info2 = $_POST["value"];
		$mysql_host = "mysql2.000webhost.com";
		$mysql_username = "a1432537_wcgame";
		$mysql_password = "Meta@10";
		$mysql_db_name = "a1432537_wcgame";
		$mysql_connect = mysqli_connect($mysql_host,
			$mysql_username, $mysql_password, $mysql_db_name);
		
		$mysqli_query = "INSERT INTO ranking (name, value) VALUES('$info1', '$info2')";
		$mysqli_response = mysqli_query($mysql_connect, $mysqli_query);
		
		
		echo "ta";
		
		mysqli_close($mysql_connect);
	}
?>