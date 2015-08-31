package com.example.wallcolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Wall 
{
	public float x,y,width,height;
	int color;
	
	public Wall(float x, float y, float w, float h, int c,int number)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = c;
		// TODO Auto-generated constructor stub
	}
	public void Draw(Canvas c)
	{
		Paint paint = new Paint();
		paint.setColor(this.color);
		c.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
	}
	
	public void Update()
	{		
	}
}
