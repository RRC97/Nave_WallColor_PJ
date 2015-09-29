package com.example.wallcolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;

public class Clock implements Element
{
	private float x, y, size;
	private float time;
	public Clock()
	{
		size = (GameView.width + GameView.height) / 25;
		x = GameView.width / 2;
		y = size;
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		int millisecond = (int)(time % 1000);
		int second = (int)((time / 1000) % 60);
		int minute = (int)((time / 1000) / 60);
		String text = String.format("TIME: %02d:%02d:%03d",minute,second,millisecond);
		Paint paint = new Paint();
		paint.setTextAlign(Align.CENTER);
		paint.setTextSize(size); 
		paint.setColor(Color.WHITE);
		canvas.drawText(text, x, y, paint);
	}

	@Override
	public void onUpdate()
	{
		time += GameView.deltaTime;
	}
	
	public static String valueToString(int value)
	{
		int millisecond = (int)(value % 1000);
		int second = (int)((value / 1000) % 100);
		int minute = (int)(value / 100000);

		String text = String.format("%02d:%02d:%03d",minute,second,millisecond);
		return text;
	}
	
	public int getLikeScore()
	{
		int millisecond = (int)(time % 1000);
		int second = (int)((time / 1000) % 60);
		int minute = (int)((time / 1000) / 60);
		return (minute * 100000) + (second * 1000) + millisecond;
	}

}
