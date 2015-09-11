package com.example.wallcolor;

import java.util.ArrayList;
import java.util.List;

import com.example.wallcolor.Wall.Direction;

import android.graphics.Canvas;

public class WallManager implements Element 
{
	private Player player;
	private float screenW, screenH, time;
	private List<Wall> walls;
	public WallManager(Player p, float w, float h)
	{
		player = p; screenW = w; screenH = h;
		walls = new ArrayList<Wall>();
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		for(Wall wall : walls)
			wall.onDraw(canvas);
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpdate()
	{
		for(Wall wall : walls)
			wall.onUpdate();
		// TODO Auto-generated method stub
		time += GameView.deltaTime;
		if(time > 5000)
		{
			float w = (GameView.width + GameView.height) / 10;
			float h = w;
			int c = BaseColor.getRandomColor();
			walls.add(new Wall());
			time = 0;
		}
	}
}
