package com.example.wallcolor;

import java.util.Date;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Player implements Element
{
	private float x, y, touchX, touchY, radius;
	private int color, newColor;
	private float time, timeDouble;
	private boolean doubleTouch;
	
	public Player()
	{
		newColor = BaseColor.RED;
		color = BaseColor.GREEN;
		x = touchX = GameView.width / 2;
		y = touchY = GameView.height / 2;
		radius = (GameView.width + GameView.height) / 30;
	}
	@Override
	public void onDraw(Canvas canvas)
	{		
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawCircle(x, y, radius, paint);
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(x, y, radius * 0.75f, paint);
		paint.setColor(newColor);
		float circleX = x - radius * 0.75f;
		float circleY = y - radius * 0.75f;
		float circleW = x + radius * 0.75f;
		float circleH = y + radius * 0.75f;
		canvas.drawArc(new RectF(circleX, circleY, circleW, circleH),
				-90, (time / 5000) * 360, true, paint);
		paint.setColor(color);
		canvas.drawCircle(x, y, radius * 0.65f, paint);
	}

	@Override
	public void onUpdate()
	{
		float distX = touchX - x;
		float distY = touchY - y;
		distX = distX < 0 ? -distX : distX;
		distY = distY < 0 ? -distY : distY;
		
		if(x > touchX) x -= distX / 10;
		else if(x < touchX) x += distX / 10;
		
		if(y > touchY) y -= distY / 10;
		else if(y < touchY) y += distY / 10;
		
		if(x + radius > GameView.width)
			x = GameView.width - radius;
		if(x - radius < 0)
			x = radius;
		if(y + radius > GameView.height)
			y = GameView.height - radius;
		if(y - radius < 0)
			y = radius;
		
		time += GameView.deltaTime;
		if(time > 5000)
		{
			color = newColor;
			newColor = color;
			while(newColor == color)
				newColor = BaseColor.getRandomColor();
			time = 0;
		}
	}
	
	public boolean onCollisionWall(Wall wall)
	{
		float distanceX = wall.getX() - x;
		float distanceY = wall.getY() - y;
		distanceX = distanceX < 0 ? -distanceX : distanceX;
		distanceY = distanceY < 0 ? -distanceY : distanceY;
		float hip = (float)Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		
		if(hip < radius + wall.getWidth()/2
		|| hip < radius + wall.getHeight()/2)
		{
			return true;
		}
		if((distanceX - wall.getWidth()/2) * (distanceX - wall.getWidth()/2) +
		(distanceY - wall.getHeight()/2) * (distanceY - wall.getHeight()/2) <= radius * radius)
		{
			return true;
		}
		
		return false;
	}

	public boolean onTouchMove(MotionEvent me)
	{
		if(me.getAction() == MotionEvent.ACTION_DOWN
		|| me.getAction() == MotionEvent.ACTION_MOVE)
		{
			touchX = me.getX();
			touchY = me.getY();
		}
		
		return true;
	}
	
	public boolean onTouchTeleport(MotionEvent me)
	{
		if(me.getAction() == MotionEvent.ACTION_DOWN
		|| me.getAction() == MotionEvent.ACTION_MOVE)
		{
			touchX = x = me.getX();
			touchY = y = me.getY();
		}
		
		return true;
	}
	
	public int getColor()
	{
		return color;
	}
}