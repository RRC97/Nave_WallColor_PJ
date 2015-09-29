package com.example.wallcolor;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

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

		scene = new MenuScene(this);
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
	public void changeScene(int id)
	{
		switch(id)
		{
			case 0: scene = new MenuScene(this); break;
			case 1: scene = new GameScene(this); break;
			case 2: scene = new GoverScene(this); break;
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent me)
	{
		return scene.touch(me);
	}
	public int getHighscore()
	{
		int result = 0;
		try
		{
			FileInputStream stream = getContext().openFileInput("highscore");
			BufferedInputStream buffered = new BufferedInputStream(stream);
			byte[] bytes = new byte[1024];
			buffered.read(bytes);
			List<Byte> arrayList = new ArrayList<Byte>();
			for(Byte b : bytes)
				if(b.intValue() > 0)
					arrayList.add(b);
			bytes = new byte[arrayList.size()];
			for(int i = 0; i < bytes.length; i++)
				bytes[i] = arrayList.get(i);
			String string = new String(bytes, "UTF-8");
			result = Integer.parseInt(string);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public void setHighscore(int value)
	{
		if(getHighscore() > value)
			return;
		try
		{
			FileOutputStream fos = getContext().openFileOutput("highscore", Context.MODE_PRIVATE);
			byte[] bytes = (""+value).getBytes();
			fos.write(bytes);
			fos.flush();
			fos.close();
			System.out.println("VALUE: " + value);
		}
		catch (Exception e)
		{
		}
		
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://rrc97.net76.net:80/add.php");
			List<NameValuePair> list = new ArrayList<NameValuePair>(3);
			list.add(new BasicNameValuePair("block", "paodebatatacomgergilimeagriao123"));
			list.add(new BasicNameValuePair("name", "JAMV"));
			list.add(new BasicNameValuePair("value", ""+getHighscore()));
			httpPost.setEntity(new UrlEncodedFormEntity(list));
			HttpResponse response = httpClient.execute(httpPost);
			String responseStr = EntityUtils.toString(response.getEntity());
			System.out.println(responseStr);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}