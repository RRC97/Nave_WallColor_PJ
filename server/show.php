<?php
	
	error_reporting(0);
	
	if($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST["block"])
	&& $_POST["block"] == "paodebatatacomgergilimeagriao123")
	{
		$mysql_host = "mysql2.000webhost.com";
		$mysql_username = "a1432537_wcgame";
		$mysql_password = "Meta@10";
		$mysql_db_name = "a1432537_wcgame";
		$mysql_connect = mysqli_connect($mysql_host,
			$mysql_username, $mysql_password, $mysql_db_name);
		
		$mysqli_query = "SELECT * FROM ranking ORDER BY value DESC";
		$mysqli_response = mysqli_query($mysql_connect, $mysqli_query);
		
		for($i = 0; $i < 10; $i++)
		{
			$result = $mysqli_response->fetch_row();
			$name = $result[1];
			$value = $result[2];
			$string = $name . ";" . $value . "\n";
			echo $string;
		}
		
		mysqli_close($mysql_connect);
	}
?>