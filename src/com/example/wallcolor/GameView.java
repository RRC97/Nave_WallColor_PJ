package com.example.wallcolor;

import java.util.Date;

import android.content.Context;
import android.content.Intent;
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
	public static float width, height;
	private Date time = new Date();
	public static float deltaTime;
	private Scene scene;
	
	public GameView(Context context)
	{
		super(context);
		setKeepScreenOn(true);
		
		handler = new Handler();
		handler.postDelayed(this, 1);
		
		width = getResources().getDisplayMetrics().widthPixels;
		height = getResources().getDisplayMetrics().heightPixels;

		scene = new GameScene(this);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.DKGRAY);
		canvas.drawPaint(paint);

		scene.draw(canvas);
	}
	public void onUpdate()
	{
		Date now = new Date();
		int seconds = (int)time.getTime();
		int current = (int)now.getTime();
		deltaTime = (current - seconds);
		time = now;
		
		scene.update();
	}
	@Override
	public void run()
	{
		onUpdate();
		invalidate();
		handler.postDelayed(this, 1);
	}
	public void changeScene(String name)
	{
		scene = new MenuScene(this);
	}
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		return scene.touch(me);
	}
}