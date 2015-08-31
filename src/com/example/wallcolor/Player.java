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
	private float x, y, touchX, touchY;
	private int color;
	private Date time = new Date();
	
	public Player()
	{
		color = BaseColor.GREEN;
	}
	@Override
	public void onDraw(Canvas canvas)
	{		
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawCircle(x, y, 40, paint);
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(x, y, 30, paint);
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
		
		int seconds = (int)time.getTime();
		int current = (int)new Date().getTime();
		if(current - seconds > 4)
		{
			color = BaseColor.getRandonColor();
			time = new Date();
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
