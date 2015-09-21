package com.example.wallcolor;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Wall implements Element
{
	public enum Direction
	{
		Right, Left, Top, Bottom
	}
	private float x,y,width,height;
	private Direction direction;
	private float speed = 3;
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
				this.x = -this.width/2;
				this.y = random.nextFloat() * (GameView.height - this.height/2);
				break;
			case Left:
				this.x = GameView.width + this.width/2;
				this.y = random.nextFloat() * (GameView.height - this.height/2);
				break;
			case Bottom:
				this.x = random.nextFloat() * (GameView.width - this.width/2);
				this.y = -this.height/2;
				break;
			case Top:
				this.x = random.nextFloat() * (GameView.width - this.width/2);
				this.y = GameView.height + this.height/2;
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
		canvas.drawRect(this.x - this.width/2, this.y - this.height/2,
					this.x + this.width/2, this.y + this.height/2, paint);
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
	public int getColor()
	{
		return color;
	}
	
	public void onCollisionWall(Wall wall)
	{
		if(wall.x - wall.width/2 < x + width/2 && wall.x + wall.width/2 > x - width/2
		&& wall.y - wall.width/2 < y + height/2 && wall.y + wall.height/2 > y - height/2)
		{
			switch (direction)
			{
				case Right: direction = Direction.Left; break;
				case Left: direction = Direction.Right; break;
				case Top: direction = Direction.Bottom; break;
				case Bottom: direction = Direction.Top; break;
			}
		}
	}
}
