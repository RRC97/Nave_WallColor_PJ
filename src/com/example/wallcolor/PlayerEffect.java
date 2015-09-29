package com.example.wallcolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;

public class PlayerEffect implements Element
{
	private float x, y, radius;
	private int color, newColor;
	private float yNormal, radiusNormal;
	private boolean touchEffect, finish;
	
	public PlayerEffect()
	{
		newColor = BaseColor.RED;
		color = BaseColor.GREEN;
		x = GameView.width / 2;
		y = GameView.height * 0.75f;
		yNormal = GameView.height / 2;
		radius = (GameView.width + GameView.height) / 10;
		radiusNormal = (GameView.width + GameView.height) / 30;
	}
	@Override
	public void onDraw(Canvas canvas)
	{		
		Paint paint = new Paint();
		paint.setColor(color);
		canvas.drawCircle(x, y, radius, paint);
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(x, y, radius * 0.75f, paint);
		paint.setColor(color);
		canvas.drawCircle(x, y, radius * 0.65f, paint);
		
		float ta = (radius - radiusNormal) * 0.75f;
		
		Path path = new Path();
		path.moveTo(x - ta + 20, y - ta);
		path.lineTo(x + ta + 20, y);
		path.lineTo(x - ta + 20, y + ta);
		path.close();

		paint.setColor(Color.DKGRAY);
		canvas.drawPath(path, paint);
	}

	@Override
	public void onUpdate()
	{
		if(touchEffect)
		{
			radius = (radiusNormal + radius) / 2;
			y = (yNormal + y) / 2;
			
			if((int)radiusNormal == (int)radius
			&& (int)yNormal == (int)y)
			{
				finish = true;
			}
		}
	}
	
	public void onTouchPlay(MotionEvent me)
	{
		if(me.getAction() == MotionEvent.ACTION_DOWN)
		{
			float distanceX = me.getX() - x;
			float distanceY = me.getY() - y;
			distanceX = distanceX < 0 ? -distanceX : distanceX;
			distanceY = distanceY < 0 ? -distanceY : distanceY;
			
			if(distanceX < radius && distanceY < radius)
				touchEffect = true;
		}
	}
	
	public boolean getFinish()
	{
		return finish;
	}
}
