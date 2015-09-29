package com.example.wallcolor;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class MenuScene implements Scene
{
	private GameView view;
	private PlayerEffect playerEffect;
	private int highscore;
	public MenuScene(GameView v)
	{
		view = v;
		playerEffect = new PlayerEffect();
		highscore = view.getHighscore();
	}
	
	@Override
	public void draw(Canvas c)
	{
		// TODO Auto-generated method stub
		float size = (GameView.width + GameView.height) / 10;
		Paint text = new Paint();
		text.setTextAlign(Paint.Align.CENTER);
		text.setTextSize(size);
		
		String[] name1 = "WALL".split("");
		
		for(int i = 0; i < name1.length; i++)
		{
			int mid = name1.length / 2 + name1.length % 2;
			int color = BaseColor.getColorById(i);
			if(name1[i].equals("L"))
				color = BaseColor.getColorById(3);
			text.setColor(color);
			c.drawText(name1[i], GameView.width / 2 + (i - mid) * size / 2, 200, text);
		}
		
		String[] name2 = "COLOR".split("");
		
		for(int i = 0; i < name2.length; i++)
		{
			int mid = name2.length / 2 + name2.length % 2;
			int color = BaseColor.getColorById((i + 4) % 7);
			if(name2[i].equals("O"))
				color = BaseColor.getColorById(4);
			if(name2[i].equals("L"))
				color = BaseColor.getColorById(3);
			text.setColor(color);
			c.drawText(name2[i], GameView.width / 2 + (i - mid) * size / 2, 350, text);
		}
		
		playerEffect.onDraw(c);
		
		text.setColor(Color.WHITE);
		text.setTextSize((GameView.width + GameView.height) / 30);
		c.drawText("HIGHSCORE: " + Clock.valueToString(highscore), GameView.width / 2, 450, text);
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		playerEffect.onUpdate();
		
		if(playerEffect.getFinish())
		{
			view.changeScene(1);
		}
	}

	@Override
	public boolean touch(MotionEvent me)
	{
		playerEffect.onTouchPlay(me);
		// TODO Auto-generated method stub
		return true;
	}
}
