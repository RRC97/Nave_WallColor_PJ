package com.example.wallcolor;

import java.util.Date;

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
	private WallManager wallManager;
	public static float width, height;
	private Date time = new Date();
	public static float deltaTime;
	private Clock clock;
	
	public GameView(Context context)
	{
		super(context);
		
		handler = new Handler();
		handler.postDelayed(this, 1);
		
		width = getResources().getDisplayMetrics().widthPixels;
		height = getResources().getDisplayMetrics().heightPixels;
		
		player = new Player();
		clock = new Clock();
		wallManager = new WallManager(player, width, height);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.DKGRAY);
		canvas.drawPaint(paint);
		
		wallManager.onDraw(canvas);
		player.onDraw(canvas);
		clock.onDraw(canvas);
	}
	public void onUpdate()
	{
		player.onUpdate();
		wallManager.onUpdate();
		clock.onUpdate();

		Date now = new Date();
		int seconds = (int)time.getTime();
		int current = (int)now.getTime();
		deltaTime = (current - seconds);
		time = now;
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
