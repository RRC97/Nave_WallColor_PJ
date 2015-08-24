package com.example.wallcolor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class GameView extends View implements Runnable
{
	private Handler handler;
	private Player player;
	
	public GameView(Context context)
	{
		super(context);
		
		handler = new Handler();
		handler.postDelayed(this, 1);
		
		player = new Player();
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.DKGRAY);
		canvas.drawPaint(paint);
		
		player.onDraw(canvas);
	}
	public void onUpdate()
	{
		player.onUpdate();
	}
	@Override
	public void run()
	{
		onUpdate();
		invalidate();
		handler.postDelayed(this, 1);
	}
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		player.onTouch(this, me);
		
		return true;
	}
}
