package com.example.wallcolor;

import java.util.Date;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Player implements Element, OnTouchListener
{
	private float x, y, touchX, touchY, radius;
	private int color;
	private float time;
	
	public Player()
	{
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
		
		time += GameView.deltaTime;
		if(time > 5000)
		{
			int newColor = color;
			while(newColor == color)
				newColor = BaseColor.getRandomColor();
			color = newColor;
			time = 0;
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent me)
	{
		if(me.getAction() == MotionEvent.ACTION_DOWN
		|| me.getAction() == MotionEvent.ACTION_MOVE)
		{
			touchX = me.getX();
			touchY = me.getY();
		}
		
		return true;
	}
}
