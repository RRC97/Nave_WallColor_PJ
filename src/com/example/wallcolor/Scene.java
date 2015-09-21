package com.example.wallcolor;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene
{
	public void draw(Canvas c);
	public void update();
	public boolean touch(MotionEvent me);
}
