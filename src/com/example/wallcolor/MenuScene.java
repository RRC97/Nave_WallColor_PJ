package com.example.wallcolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class MenuScene implements Scene
{
	private GameView view;
	public MenuScene(GameView v)
	{
		view = v;
	}
	
	@Override
	public void draw(Canvas c)
	{
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		c.drawCircle(0, 0, 20, paint);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean touch(MotionEvent me)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
