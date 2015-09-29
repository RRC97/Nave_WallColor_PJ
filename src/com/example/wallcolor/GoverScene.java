package com.example.wallcolor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GoverScene implements Scene
{
	private GameView view;
	private PlayerEffect playerEffect;
	private float time;
	private int highscore;
	private String[] names;
	private String[] values;
	public GoverScene(GameView v)
	{
		view = v;
		playerEffect = new PlayerEffect();
		highscore = view.getHighscore();
		names = new String[10];
		values = new String[10];
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://rrc97.net76.net:80/show.php");
			List<NameValuePair> list = new ArrayList<NameValuePair>(1);
			list.add(new BasicNameValuePair("block", "paodebatatacomgergilimeagriao123"));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse response = httpClient.execute(httpPost);
			String responseStr = EntityUtils.toString(response.getEntity());
			String[] scores = responseStr.split("\n");
			for(int i = 0; i <  scores.length; i++)
			{
				if(!scores[i].equals(""))
				{
					String[] parts = scores[i].split(";");
					if(parts.length > 1)
					{
						names[i] = parts[0];
						values[i] = parts[1];
					}
				}
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Canvas c)
	{
		// TODO Auto-generated method stub
		float size = (GameView.width + GameView.height) / 10;
		Paint text = new Paint();
		text.setTextAlign(Paint.Align.CENTER);
		text.setTextSize(size);
		
		playerEffect.onDraw(c);
		
		text.setColor(Color.WHITE);
		text.setTextSize((GameView.width + GameView.height) / 30);
		for(int i = 0; i < names.length; i++)
		{
			int score = 0;
			if(values[i] != null)
				score = Integer.parseInt(values[i]);
			c.drawText(names[i] + ": " + Clock.valueToString(score), GameView.width / 2, 100 + 50 * i, text);
		}
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		time += GameView.deltaTime;
		
		playerEffect.onUpdate();
		
		if(playerEffect.getFinish())
		{
			view.changeScene(1);
		}
		
		if(time > 10000)
		{
			view.changeScene(0);
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
