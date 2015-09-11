package com.example.wallcolor;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BaseColor 
{
	private static Random random = new Random();
	public static int BLUE = Color.parseColor("#0000FF");
	public static int AQUA = Color.parseColor("#00FFFF");
	public static int RED = Color.parseColor("#FF0000");
	public static int GREEN = Color.parseColor("#00FF00");
	public static int YELLOW = Color.parseColor("#FFFF00");
	public static int MAGENTA = Color.parseColor("#FF00FF");
	public static int ORANGE = Color.parseColor("#FFA500");
	
	public static int getRandomColor()
	{
		int result = 0;
		int id = random.nextInt(7);
		switch(id)
		{
			case 0: result = BLUE; break;
			case 1: result = AQUA; break;
			case 2: result = RED; break;
			case 3: result = GREEN; break;
			case 4: result = YELLOW; break;
			case 5: result = MAGENTA; break;
			case 6: result = ORANGE; break;
		}
		return result;
		
	}
}
