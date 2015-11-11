package com.walter.inspireafrica;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	RadioButton[] rb;
	CheckBox [] cb;
	TextView title;
	LinearLayout mLinearLayout;
	JSONArray array;
	int pos=0;
	int size;
	int q_num=1;
	Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
          String json=read_txt();
        //  Log.d("json string",json);
          btn=(Button) findViewById(R.id.btn_next);
          try 
          {
				array=new JSONArray(json);
				size=array.length();
				//Toast.makeText(this, "size :"+array.length(), Toast.LENGTH_SHORT).show();
				//Log.d("Length", "size :"+array.length());
		   } catch (JSONException e) 
		   {
				//Log.e("Json Error", e.getMessage());
				Toast.makeText(this, "Error processing Json", Toast.LENGTH_SHORT).show();
		   }
         
          render_question(4,0);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    private void render_question(int size,int pos)
    {
    	   mLinearLayout= (LinearLayout) findViewById(R.id.linear1);
	       if(mLinearLayout.getChildCount()>0)
	       {
	    	   mLinearLayout.removeAllViews();
	       }
	       JSONObject ob=new JSONObject();
	       String question="";
	       int size_of_ans=2;
	       String[] xyz=null;
	       String type="";
	       try 
	       {
				ob=array.getJSONObject(pos);
				question=ob.getString("question");
				String sizey=ob.getString("size");
				size_of_ans=Integer.parseInt(sizey);
				xyz=new String[size_of_ans];
				type=ob.getString("type");
				type=type.trim();
				for(int k=0;k<size_of_ans;k++)
				{
					int c=k;
					c=c+1;
					xyz[k]=ob.getString("ans"+c);
					//Log.d("Item at ans"+c, xyz[k]);
				}			
		   } catch (JSONException e) 
		   {
				//e.printStackTrace();
		   }
	       
		   title= new TextView(this);
		   title.setText("Q "+q_num+". "+question);
		   title.setTextColor(Color.BLUE);
		   mLinearLayout.addView(title);
		   //Toast.makeText(this, "Type is : "+type, Toast.LENGTH_SHORT).show();
		   if(type.contains("rb"))
		   {
			   rb = new RadioButton[size_of_ans];
			   RadioGroup rg = new RadioGroup(this);
			   rg.setOrientation(RadioGroup.VERTICAL);
			   for (int i = 0; i < size_of_ans; i++) 
			   {
				    rb[i] = new RadioButton(this);
				    rg.addView(rb[i]);
				    rb[i].setText(xyz[i]);		 
			   }
			   mLinearLayout.addView(rg); 
		   }else if(type.contains("cb"))
		   {
			  cb=new CheckBox[size_of_ans];     
			   for (int i = 0; i < size_of_ans; i++) 
			   {
				    cb[i] = new CheckBox(this);
				    cb[i].setText(xyz[i]);	
				    mLinearLayout.addView(cb[i]);
			   }  
		   }
    }
    public void render_next(View v)
    {
	      pos++;
	      q_num++;
	      if(pos<size)
	      {
	    	render_question(4,pos);  
	      }else
	      {
	    	 btn.setText("Done. Submit"); 
	      }    	
    }
    private String read_txt()
    {
    	String x="";
      try {
    		
			InputStream input=getAssets().open("data.txt");
			int size=input.available();
			byte[] buffer=new byte[size];
			input.read(buffer);
			input.close();
			String text=new String(buffer);
			x=text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			x="Nothing Found";
		}
        return x;
    	
    }

}
