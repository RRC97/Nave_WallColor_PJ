package com.example.wallcolor;

import android.graphics.Canvas;

public interface Element
{
	public void onDraw(Canvas canvas);
	public void onUpdate();
}
