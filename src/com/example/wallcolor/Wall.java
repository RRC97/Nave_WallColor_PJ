package com.example.wallcolor;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Wall implements Element
{
	public enum Direction
	{
		Right, Left, Top, Bottom
	}
	public float x,y,width,height;
	private Direction direction;
	private float speed = 1;
	int color;
	
	public Wall()
	{
		this.direction = getRandomDirection();
		this.color = BaseColor.getRandomColor();
		this.width = (GameView.width + GameView.height) / 10;
		this.height = this.width;
		switch (direction)
		{
			case Right:
				this.x = -this.width;
				this.y = random.nextFloat() * (GameView.height - this.height);
				break;
			case Left:
				this.x = GameView.width;
				this.y = random.nextFloat() * (GameView.height - this.height);
				break;
			case Bottom:
				this.x = random.nextFloat() * (GameView.width - this.width);
				this.y = -this.height;
				break;
			case Top:
				this.x = random.nextFloat() * (GameView.width - this.width);
				this.y = GameView.height;
				break;
		}
	}
	public Wall(float x, float y, float w, float h, int c, Direction d)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.color = c;
		this.direction = d;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		Paint paint = new Paint();
		paint.setColor(this.color);
		canvas.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, paint);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpdate()
	{
		switch (direction)
		{
			case Right: this.x += speed; break;
			case Left: this.x -= speed; break;
			case Top: this.y -= speed; break;
			case Bottom: this.y += speed; break;
		}
	}
	
	private static Random random = new Random();
	public static Direction getRandomDirection()
	{
		int value = random.nextInt(4);
		Direction result = Direction.Right;
		switch (value)
		{
			case 0: result = Direction.Right; break;
			case 1: result = Direction.Left; break;
			case 2: result = Direction.Top; break;
			case 3: result = Direction.Bottom; break;
		}
		return result;
	}
	
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public float getWidth()
	{
		return width;
	}
	public float getHeight()
	{
		return height;
	}
}
