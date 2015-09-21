package com.example.wallcolor;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class GoverScene implements Scene
{
	private GameView view;
	public GoverScene(GameView v)
	{
		view = v;
	}
	
	@Override
	public void draw(Canvas c)
	{
		// TODO Auto-generated method stub

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
