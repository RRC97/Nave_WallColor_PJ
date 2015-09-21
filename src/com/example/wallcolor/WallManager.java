package com.example.wallcolor;

import java.util.ArrayList;
import java.util.List;

import com.example.wallcolor.Wall.Direction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		if(time > 1000)
		{
			walls.add(new Wall());
			time = 0;
		}
		
		for(Wall wall : walls)
		{
			if(wall.getX() - wall.getWidth()/2 > GameView.width
			|| wall.getY() - wall.getHeight()/2 > GameView.height
			|| wall.getX() + wall.getWidth()/2 < 0
			|| wall.getY() + wall.getHeight()/2 < 0)
			{
				walls.remove(wall);
				break;
			}
		}
		
		for(Wall wall1 : walls)
		{
			for(Wall wall2 : walls)
			{
				if(wall1 != wall2)
				{
					wall1.onCollisionWall(wall2);
				}
			}
		}
	}
	public boolean onFinish()
	{
		for(Wall wall : walls)
		{
			if(player.onCollisionWall(wall)
			&& player.getColor() != wall.getColor())
			{
				return true;
			}
		}
		return false;
	}
}