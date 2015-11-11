package com.inspireafrica.chatter;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class EditFriendsActivity extends ListActivity {
protected List<ParseUser> mUsers;
protected ParseRelation<ParseUser> mFreindsRelation;
protected ParseUser mCurrentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_edit_friends);
		// Show the Up button in the action bar.
		setupActionBar();
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		mCurrentUser=ParseUser.getCurrentUser();
		mFreindsRelation=mCurrentUser.getRelation(ParseConstants.KEY_FRIENDS_RELATION);
		setProgressBarIndeterminateVisibility(true);
		ParseQuery<ParseUser> query=ParseUser.getQuery();
		query.addDescendingOrder(ParseConstants.KEY_USERNAME);
		query.setLimit(1000);
		query.findInBackground(new FindCallback<ParseUser>() {			
			@Override
			public void done(List<ParseUser> users, ParseException e) {
			 setProgressBarIndeterminateVisibility(false);
			  if(e==null)
			  {
				   mUsers=users;
				   String[] usernames=new String[mUsers.size()];
				   int i=0;
				   for (ParseUser user:mUsers)
				   {
					  usernames[i]=user.getUsername();
					  i++;
				   }
				   ArrayAdapter<String> adapter=new ArrayAdapter<String>(EditFriendsActivity.this, 
						    android.R.layout.simple_list_item_checked,usernames	);
				   setListAdapter(adapter);
				   addFriendsCheckMarks();
				  
			  }else
			  {
					AlertDialog.Builder builder=new AlertDialog.Builder(EditFriendsActivity.this);
					builder.setMessage(e.getMessage());
					builder.setTitle("Error");
					builder.setPositiveButton(android.R.string.ok,null);
					AlertDialog dialog=builder.create();
					dialog.show();		
			  }
				
			}
		});
		
	}
	protected void addFriendsCheckMarks() {
		mFreindsRelation.getQuery().findInBackground(new FindCallback<ParseUser>() {		
			@Override
			public void done(List<ParseUser> friends, ParseException e) {
			  if(e==null)
			  {
				for (int i = 0; i < mUsers.size(); i++)
				{
					 ParseUser user=mUsers.get(i);
					 for (ParseUser friend:friends)
					 {
						 if(user.getObjectId().equals(friend.getObjectId()))
						 {
							 getListView().setItemChecked(i, true); 
						 }				
		           	 }
				}  
			  }
				
			}
		});
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_friends, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			if(getListView().isItemChecked(position))
			{
				mFreindsRelation.add(mUsers.get(position));
				mCurrentUser.saveInBackground(new SaveCallback() {				
					@Override
					public void done(ParseException e) {
						if(e!=null)
						{
						 Log.d("Relations", e.getMessage());	
						}
						
					}
				})	;
			}else
			{
								
			}			
		}
}