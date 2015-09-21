package com.example.wallcolor;

import java.util.Date;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameScene implements Scene
{
	private GameView view;
	private Player player;
	private WallManager wallManager;
	private Clock clock;
	public GameScene(GameView v)
	{
		view = v;
		player = new Player();
		clock = new Clock();
		wallManager = new WallManager(player, GameView.width, GameView.height);
	}
	@Override
	public void draw(Canvas canvas)
	{
		wallManager.onDraw(canvas);
		player.onDraw(canvas);
		clock.onDraw(canvas);
	}

	@Override
	public void update()
	{
		player.onUpdate();
		wallManager.onUpdate();
		clock.onUpdate();
		
		if(wallManager.onFinish())
		{
			view.changeScene("Menu");
		}
	}
	
	@Override
	public boolean touch(MotionEvent me)
	{
		player.onTouchMove(me);
		
		return true;
	}
}
