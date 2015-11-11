package com.walter.pickuplines;

import java.io.File;
import java.util.ArrayList;

import com.example.pickuplines.R;




import android.app.ActionBar;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.WindowManager.BadTokenException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;
import android.widget.Toast;


public class MainActivity extends ListActivity implements OnQueryTextListener {
	 ArrayList<String> t;
	 CustomAdapter adapter;
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Lines");
		Toast.makeText(getApplicationContext(),"Refresh To Load New Pick Up Lines", Toast.LENGTH_SHORT).show();
		t=new ArrayList<String>();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ea6b48")));
		if(!checdbExists())
		{
			new LoadIntoDB().execute();	
			SQLiteHandler db=new SQLiteHandler(getApplicationContext());
			t=db.getData();
			adapter=new CustomAdapter(this, t);
		}
		else
		{
			SQLiteHandler db=new SQLiteHandler(getApplicationContext());
			t=db.getData();
			adapter=new CustomAdapter(this, t);	
		}		
		this.setListAdapter(adapter);
	    registerForContextMenu(getListView());

	}
     @Override
	 public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//super.onCreateContextMenu(menu, v, menuInfo);
	      menu.setHeaderTitle("Proverbs");
		  menu.add(0,v.getId(),0,"Share");
		  menu.add(0,v.getId(),0,"Favorite");
		
	}
	
	    @Override
	 public boolean onContextItemSelected(MenuItem item) {
			AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
	        //Toast.makeText(getApplicationContext(), String.valueOf(info.position), Toast.LENGTH_SHORT).show();
			int i=info.position;
			if(item.getTitle().equals("Share"))
			{
				Intent ii=new Intent();
				ii.setAction(Intent.ACTION_SEND);
				ii.setType("text/plain");
				ii.putExtra(Intent.EXTRA_SUBJECT, "Proverb:");
				ii.putExtra(Intent.EXTRA_TEXT, t.get(i));
				startActivity(Intent.createChooser(ii,"Share Via"));
			}else if(item.getTitle().equals("Favorite"))
			{
				SQLiteHandler2 handle=new SQLiteHandler2(getApplicationContext());
				handle.Save(t.get(i), "");
				Toast.makeText(getApplicationContext(), "Added to Your Favourites", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		@Override
	 public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	        searchView.setOnQueryTextListener(this);
			return true;
		}
	  
	 public boolean checdbExists()
	   {
			boolean its=false;   
			File database=getApplicationContext().getDatabasePath("appdata.db");
		
			if (!database.exists()) 
			{	  
			   its=false; 
			} else
			{
			   its=true; 
			}
			return its;   
	   }

		 @Override
	 public boolean onOptionsItemSelected(MenuItem item) 
		{
			//AdapterView.AdapterContextMenuInfo info=(AdapterContextMenuInfo) item.getMenuInfo();
		    //Toast.makeText(getApplicationContext(), String.valueOf(info.position), Toast.LENGTH_SHORT).show();
		
			if(item.getTitle().equals("Refresh"))
			{
				SQLiteHandler h=new SQLiteHandler(getApplicationContext());
				t.clear();	
				//System.out.println(t.size());
				adapter.clear();
				t=h.getData();
				adapter.addAll(t);			
				//System.out.println(adapter.getCount());
			    adapter.notifyDataSetChanged();
			   // this.setListAdapter(adapter);		    
			   // System.out.println(t.size());
			    Toast.makeText(getApplicationContext(), "Refreshing", Toast.LENGTH_SHORT).show();
			}
			else if(item.getTitle().equals("Favourites"))
			{
			 Intent i=new Intent(this,Favour.class);
			 startActivity(i);
			}else if(item.getTitle().equals("Help and About"))
			{
	              Intent i=new Intent(this,Help_About.class);
	              startActivity(i);
			}
			return true;
		}
		@Override
	 public boolean onQueryTextChange(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
	 public boolean onQueryTextSubmit(String search_term) 
		{
			Intent i=new Intent(this,Search_Results.class);
			i.putExtra("data", search_term);
			startActivity(i);			
			return true;
		}

     private class LoadIntoDB extends AsyncTask<Void, Void, Void>
     {
 		ProgressDialog progressDialog;
 		@Override
 		protected void onPreExecute() {
 			// TODO Auto-generated method stub
 			 if (progressDialog == null) {
 			        progressDialog = createProgressDialog(MainActivity.this);
 			        progressDialog.show();
 			        } else 
 			        {
 			         progressDialog.show();
 			        }
 			super.onPreExecute();
 		}
		@Override
		protected Void doInBackground(Void... params) {
		     SQLiteHandler sq=new SQLiteHandler(getApplicationContext());
		     String[] data={"Is your name Google? Because you have everything I've been searching for. ","You make my software turn into hardware! ","Is your name Wi-fi? Because I'm really feeling a connection. ","Are you sitting on the F5 key? Cause your jass is refreshing. ","You had me at Hello World. ","Want to see my HARD Disk? I promise it isn't 3.5 inches and it ain't floppy. ","You can put a Trojan on my Hard Drive anytime. ","You still use Internet Explorer? You must like it nice and slow. ","I hope you're an ISO file, because I'd like to mount you. ","My servers never go down... but I do! ","My 'up-time' is better than BSD. ","Are you an angel, because your texture mapping is divine! ","You've stolen the ASCII to my heart. ","Are you a computer keyboard? Because you're my type. ","You got me stuck on Caps Lock, if you know what I mean. ","If you were a web browser, you'd be called a Fire-foxy lady ","How about we do a little peer-to-peer saliva swapping? ","Mind if I run a sniffer to see if your ports are open? ","Your beauty rivals the graphics of Call of Duty. ","You must be Windows 95 because you've got me feeling so unstable. ","I was hoping you wouldn't block my pop-up. ","Want to see my Red Hat? ","If you won't let me buy you a drink, at least let me fix your laptop. ","You put the SPARC in my workstation. ","You're so pretty, I wouldn't even need to use an Instagram filter if I took your photo. ","Isn't your e-mail address beautifulgirl@mydreams.com? ","I'd switch to emacs for you. ","What's a nice girl like you doing in a chatroom like this? ","No, that's not a Logitech MX-100 in my pants, but thanks for noticing. ","Nice Set of Floppies! ","I think you could be an integral part of my project life cycle. ","If you have an empty slot, I have the card to fill it. ","WebMD says your love is contagious. ","Hey, how 'bout I take off your cover and insert a bigger CPU. ","I'd like to play on your laptop. ","Where's the 'like' button for that smile? ","You totally spiked my traffic. ","You are the Apple of my i-Mac. ","If you were an ISP I'd dial you all day long. ","If you were an ebay auction, I'd totally 'buy it now'. ","You have a trojan? hmm... I think I'll need to take a look at that backdoor. ","Come to my 127.0.0.1 and I’ll give you sudo access. ","I must be using Apple maps, because I keep getting lost in your eyes. ","I'd get a T3 to watch your streaming video. ","I'll bet my hard drive is the biggest you've ever seen. ","Your homepage or mine? ","Hey Baby, Let me hack your kernel. ","No, that's not an iPod mini in my pocket. I'm just happy to see you. ","You auto-complete me. ","I didn't mean to ogle you, but I'd sure like to Google you. ","I was wondering if you'd like to go back to My-Space, so I can Twitter with your Yahoo, until I Google all over your Facebook? ","If you ever need to get rid of a trojan, don't hesitate to call me! ","You're making me feel like I have something in common with these pop-up ads. ","What's the difference between a crush and a Facebook account? [what?] I'm not rapidly developing a Facebook account on you. ","I need to hop over to Facebook for a second to change my status to smitten. ","What do you say we play a game of Words With More Than Friends? ","Need me to unzip your files? ","Are your pants a compressed file? Because I'd love to unzip them! ","I googled your name earlier... I clicked on 'I'm Feeling Lucky.' ","How about we go home and you handle my exception? ","If we were connected on Linkedin, I'd endorse you all night long. ","I wish you were Broadband, so I could get high-speed access. ","I'd ask if you come here often, but I already stalk you on FourSquare. ","Computer techs have skilled fingers if you know what I mean. ","If I were a drum I'd let you bang me all night long! ","You must be a choir director, because you make my heart sing! ","This cello isn't the only big wood between my legs. ","Are you a trumpet player? Because you sure are makin' me horny! ","I may not be Mumford, but do you want to have my sons? ","Let's make some sweet music together, honey. ","You can tickle my ivories anytime, baby. ","I can make you hit all the high notes! ","You had me at cello. ","Excuse me, do you believe in premarital sax? ","Would you like to play my organ? ","I'd like to finger your fret board. ","Flute players provide some cheap trills. ","I C Major potential in us getting together. ","Good boys deserve more than just fudge. ","Girl, you give me a tromboner. ","Save a drum, bang a drummer. ","This reed isn't the only thing I can get wet. ","That's a nice set of mallets you have. ","Damn girl, you're lookin' sharp! ","I'd love to strum your g-string ","I just lost my job and may be Baroque, but that doesn't mean I can't show you a good time. ","Perhaps you and your friend would like to play a trio with me? ","The pads on my MPC2000XL aren't the only thing I'll be banging tonight. ","My embouchure is good for more than just playing the trumpet, if you know what I mean. ","I bet that flute isn't the only thing you know how to blow. ","Slow down girl, you're giving me a woodwind. ","You wanna grease my slide? ","You must be a fourth or a fifth, because you're just perfect! ","I bet we'd get into some serious Treble together. ","Call me AC/DC, because I'm gonna rock you all night long! ","The Proclaimers would walk 500 miles to come back home to you, but I'll do you one better. I'll sit through an entire Nickelback concert. ","Let's play a love game. I'll be Alejandro, you be Lady Gaga and I'll let you take a ride on my disco stick. ","Composers always score. ","---------------------------- ","I wish I was your derivative so I could lie tangent to your curves. ","My love for you is like a concave up function because it is always increasing. ","How can I know so many hundreds of digits of pi and not the 7 digits of your phone number? ","I wish I was your second derivative so I could investigate your concavities. ","You and I would add up better than a Riemann sum. ","Hey baby, what's your sine? ","I need a little help with my Calculus, can you integrate my natural log? ","By looking at you I can tell you're 36-25-36, which by the way are all perfect squares. ","You fascinate me more than the Fundamental Theorem of Calculus. ","Are you a 90 degree angle? 'Cause you are looking right! ","My love for you is like pi... never ending. ","I'd like to plug my solution into your equation. ","Since distance equals velocity times time, let's let velocity and time approach infinity, because I want to go all the way with you. ","I am equivalent to the Empty Set when you are not with me. ","I don’t like my current girlfriend. Mind if I do a you-substitution? ","I can figure out the square root of any number in less than 10 seconds. What? You don’t believe me? Well, then, let’s try it with your phone number. ","Hey, baby want to Squeeze my Theorem while I poly your nomial? ","Hey...nice asymptote. ","i'm not being obtuse, but you're acute girl. ","I don't know if you're in my range, but I'd sure like to take you back to my domain. ","Are you a 45 degree angle? Because you're acute-y. ","My love for you is like y=2^x... exponentially growing. ","I'll take you to your limit if you show me your end behavior. ","Can i explore your mean value? ","The derivative of my love for you is 0, because my love for you is constant. ","I'm good at math... let's add a bed, subtract our clothes, divide your legs, and multiply! ","Our love is like dividing by zero... you cannot define it. ","If you were a graphics calculator, i'd look at your curves all day long! ","I've been secant you for a long time. ","If I'm sine and you're cosine, wanna make like a tangent? ","Meeting you is like making a switch to polar coordinates: complex and imaginary things are given a magnitude and a direction. ","Being without you is like being a metric space in which exists a cauchy sequence that does not converge ","My love for you is a monotonically increasing unbounded function ","You are the solution to my homogeneous system of linear equations. ","I heard you're good at algebra - Could you replace my X without asking Y? ","Are you a math teacher? Because you got me harder than calculus. ","i'll take you to the limit as X approaches infinity. ","Your name is Leslie? Look, I can spell your name on my calculator! ","Let's take each other to the limit to see if we converge ","You must be the square root of two because I feel irrational around you. ","Let me integrate our curves so that I can increase our volume ","If i were a function you would be my asymptote - i always tend towards you. ","Your beauty cannot be spanned by a finite basis of vectors. ","I wish i was your problem set, because then I'd be really hard, and you'd be doing me on the desk. ","My love is like an exponential curve - it's unbounded ","My love for you is like a fractal - it goes on forever. ","My love for you is like the derivative of a concave up function because it is always increasing. we're going to assume this concave up function resembles x^2 so that slopes is actually increasing. ","I hope you know set theory because i want to intersect and union you ","You've got more curves than a triple integral. ","Honey, you're sweeter than pi. ","If you were sin^2x and I was cos^2x, then together we'd make one. ","Baby, you're like a student and I'm like a math book... you solve all my problems! ","My friends told me that I should ask you out because you can't differentiate. Do you need math help? ","Wanna expand my polynomial? ","Do your legs hurt from running through my dreams all night? ","You may fall from the sky, you may fall from a tree, but the best way to fall... is in love with me. ","Do you believe in love at first sight, or should I walk by again? ","If a fat man puts you in a bag at night, don't worry I told Santa I wanted you for Christmas. ","Are you a parking ticket? Because you've got FINE written all over you. ","You are the reason Santa even has a naughty list. ","Crap. Something is wrong with my cell phone. {Oh Really. What is that?} Its just that...your numbers not in it. ","It's a good thing that I have my library card. Why? Because I am totally checking you out!! ","You’re so beautiful you made me forget my pick up line. ","Do I know you? Cause you look a lot like my next girlfriend. ","If I received a nickel for everytime I saw someone as beautiful as you, I'd have five cents. ","I'm no photographer, but I can picture us together. ","Can I borrow a kiss? I promise I'll give it back. ","Are those space pants? Because your jass is out of this world! ","People call me John, but you can call me Tonight! ","Did you have lucky charms for breakfast? Because you look magically delicious! ","Know what's on the menu? Me-n-u. ","See my friend over there? He wants to know if you think I'm cute. ","If you were a library book, I would check you out. ","Say I bet I can kiss you on the lips without touching you. and kiss her, then tell her you lost the bet. ","If beauty were time, you’d be eternity. ","Your eyes are bluer than the Atlantic ocean, and baby I'm lost at sea! ","You are so sweet you could put Hershey’s out of business. ","You're like a candy bar: half sweet and half nuts. ","Excuse me, if I go straight this way, will I be able to reach your heart? ","I have a boyfriend. [Guy] I have a pet goldfish. [Girl] What? [Guy] I thought we were talking about things that didn't matter. ","I think it is time I tell you what people are saying behind your back. Nice jass! ","It’s a good thing I wore my gloves today; otherwise, you’d be too hot to handle. ","My love for you is like diarrhea, I just cant hold it in! ","A boy gives a girl roses. real, fake and he says to her I will stop loving you when all the roses die ","You smell like trash, may I take you out? ","If you were a burger at McDonald's you'd be the McGorgeous. ","If I told you that you had a beautiful body, would you hold it against me? ","Can you take me to the bakery? Because, I want a Cutiepie like you! ","If you were a chicken, you'd be impeccable. ","I'm not trying to impress you or anything, but... I'm Batman! ","Excuse me, does this rag smell like chloroform to you? ","Would you sleep with a stranger? [No] Then Hi, my name is... ","There are angels in the world are playing, are sleeping and of them is standing in front of me. ","Was your Dad a baker? Because you've got a nice set of buns. ","If I had a garden I'd put your two lips and my two lips together. ","I want to tell you your fortune. [Take her hand and write your phone number on it.] Your future is clear. ","I can't think of anyone else I'd rather survive a Zombie Apocalypse with. ","My love for you is like a fart. Everything about it is powered by my heart. ","If I could rearrange the alphabet, I'd put U and I together. ","Do you know karate? Cause your body's kickin! ","I’m not drunk, I’m just intoxicated by you. ","You must be a Snickers, because you satisfy me. ","Do you have a mirror in your pocket? 'Cause I could see myself in your pants. ","You must be Jamaican, because Jamaican me crazy. ","My name is [your here] but you can call me tonight! ","Excuse me, but does this smell like chloroform to you? ","If you were a booger I would pick you first. ","I've noticed you noticing me and I'm just giving you notice that I've noticed you! ","I don't have a girlfriend, but I do know a woman who would be mad at me for saying that. ","Your daddy must be a drug dealer, cuz you're dope. ","You know what material this is? [Grab your shirt] Boyfriend material. ","Was that an earthquake or did you just rock my world? ","Somebody call the cops, because it's got to be illegal to look that good! ","I must be a snowflake, because I've fallen for you. ","How much does a polar beat weight? Enough to break the ice! ","You're so hot you must've started global warming. ","Do you have the time? No, the time to write down my number? ","I'd marry your cat just to get in the family. ","If I followed you home, would you keep me? ","What do you and the weather have in common? You're both Hot! ","Could you please step away from the bar? You're melting all the ice! ","Can you kiss me on the cheek so I can at least say a cute girl kissed me tonight? ","Did the sun come up or did you just smile at me? ","Let's have breakfast together tomorrow; shall I call you or nudge you? ","Are you form Tennessee? Cause you're the only ten I see!!! ","If you were a new hamburger at McDonald's, you would be a McGorgeous. ","I must be lost… because I see paradise. ","Did you fall from heaven? Cause your face is pretty messed up! ","Do you wanna come dance with the big bad wolf? [ No! ] Its okay, the other two pigs said no too! ","I may not be the best-looking guy in here, but I'm the only one talking to you. ","I'm not actually this tall. I'm sitting on my wallet. ","You look cold. Want to use me as a blanket? ","I just got this naughty list from Santa and I'm pretty sure you're on it. ","[man] Excuse me, would you like to dance? [women] NO! [man] Maybe u didn't hear me.... I said u look really fat in those pants! ","Hi, I’m Mr. Right--I heard you were looking for me. ","I would die a million deaths if it meant I could be with you! ","Here's $. Drink until I am really good looking, then come and talk to me. ","Do you have any raisins? No? How about a date? ","You owe me a drink, you're so ugly I dropped mine when I saw you. ","I must be in heaven because I'm looking at an angel! ","Are you a Hurricane [name]? Cause you're blowing me away. ","I'm going outside to make out... care to join me? ","Bond....James Bond ","Can I buy you a drink, or do you just want the money? ","If you were a laser you would be set on stunning. ","Girl you're like a car accident, cause I just can't look away. ","We're like Little Ceasar's, we're Hot and Ready. ","You're like a fat stump, I'm always falling over you. ","Polar Bear (HUh) I just wanted to break the ice. ","(steps on some ice) Now that the ice is broken, what's your name? ","Did it hurt when you fell? [Girl: Huh?] When you fell from heaven? ","The fact that I'm missing some teeth only means that there's more room for your tongue. ","If this bar is a meat market, you must be the prime rib! ","If I had to choose between one night with you or winning the lottery. I would choose the lottery. But it would be sooooo close, real close. ","If you were a steak you would be well done. ","Looks like you dropped something , My jaw! ","You don't sweat much for a fat chick. ","Let's make like a fabric softener and snuggle! ","You're so sweet, you're giving me a toothache. ","I'm going to need a tall glass of cold water, cuz baby your making me HOT! ","You're ugly, but you intrigue me... ","Are you a clock? Cause you're ticking me off. ","Is your name mickey? because your so FINE! ","What’s a nice girl like you doing in a place like this? ","Girl you so fine I wish I could plant you and grow a whole feild of y'all! ","(She asks you the time) Its two flirty and the date's with you and me. ","(To someone working somewhere where a counter seperates you) You're like a drug to me. Good thing you're over the counter. ","Excuse me, did you just fart? ","Do you mind if I hang out here until it's safe back where I farted? ","Is stalking still cute? ","Did something bad happen to you or are you just naturally ugly. ","Your eyes are as blue as my toilet water at home. ","You MUST have a nice personality. ","Girl, you got more legs than a bucket of chicken! ","Me without you is like a nerd without braces, A shoe without laces, aSentenceWithoutSpaces. ","You are the SDK in my life. I won’t compile without you. ","You know, it’s not the length of the vector that counts… it’s how you apply the force. ","If you were a triangle you'd be acute one. ","Baby, you make my floppy disk turn into a hard drive ","If I had a star for every time you brightened my day, I’d have a galaxy in my hand. ","Urkuk lu Stalga. That’s Klingon for 'I love you baby.' ","My love for you is like dividing by zero-- it cannot be defined. ","I wish I was your derivative so I can lie tangent to your curve. ","You have the nicest syntax I've ever seen. ","You and I would undergo a more energetic reaction than Potassium and water. ","Are you an exception? I bet I can catch you. ","Baby, if you were words on a page, you’d be what they call FINE PRINT! ","Roses are #FF0000, violets are #0000FF. All my base are belong to you. ","I wish I was an Ion so I could form an exothermic bond with you. ","If I was an enzyme, I’d be helicase so I could unzip your genes. ","If i was cosin squared and you were sin squared we would be one. ","You’re like a dictionary, you add meaning to my life! ","A life without you, would be like a computer without an OS. ","If I can't buy you a drink, at least let me fix your laptop. ","I wish I were adenine because then I could get paired with U. ","When you and me get together it’s like superposition of waves in phase. ","Mind if I run a scanner to see if your ports are open? ","If I was an operating system, your process would have top priority. ","I think my heart just lagged. ","I'm not staring, I'm just stuck in a loop. ","You’re sweeter than fructose. ","You’ve stolen the ASCII to my heart. ","You are my loop condition. I keep coming back to you. ","You’re so cute you make my zygomaticus muscles contract. ","Baby, every time i see you, my cardiovascular system gets all worked up. ","I’m attracted to you so strongly, scientists will have to develop a fifth fundamental force. ","If I was a Jedi, would you be my force? 0 ","You must be an angel, because your texture mapping is so divine! ","Press any key to continue. ","What's your sine? The sine^(-) of you must be pi/ cause you're the one! ","You compute me. ","You had me at Hello World ","You’re like an exothermic reaction, you spread your hotness everywhere! ","You make me want to calibrate my joystick without the latest drivers. ","Are you a keyboard? Because you're my type! ","I need more than characters to tell you how beautiful you are. ","My attraction to you is an inversed square law. ","You must be Windows because you've got me feeling so unstable. ","What's a nice girl like you doing in a chatroom like this? ","I'd switch to emacs for you. ","If kisses were snowflakes, I’d send you a blizzard. ","Me without you is like a nerd without braces, A shoe without laces, aSentenceWithoutSpaces. ","Let's commit the perfect crime: I'll steal you're heart, and you'll steal mine. ","Are you religious? 'Cause you're the answer to all my prayers. ","Are you a parking ticket? Because you've got FINE written all over you. ","I was so enchanted by your beauty that I ran into that wall over there. So I am going to need your name and number for insurance purposes. ","You are the reason Santa even has a naughty list. ","Your eyes are like a sunset, They're Beautiful, inspiring, and hard to turn away from. ","It's a good thing that I have my library card. Why? Because I am totally checking you out!! ","You’re so beautiful you made me forget my pick up line. ","You know, beautiful is my favorite color. (girl) that's not a color.(boy) its the color of your eyes. ","If I received a nickel for everytime I saw someone as beautiful as you, I'd have five cents. ","Are those space pants? Because your ass is out of this world! ","If I had a star for every time you brightened my day, I’d have a galaxy in my hand. ","Is your dad an art thief? Because you're a masterpiece. ","I hope you know CPR, because you take my breath away! ","Even if there wasn't any gravity on earth, I would still fall for you! ","If you stood in front of a mirror and held up roses, you would see of the most beautiful things in the world. ","I think there's something wrong with my eyes because I can't take them off you. ","Are you as beautiful on the inside as you are on the outside? ","Are you lost ma'am? Because heaven is a long way from here. ","If beauty were time, you’d be eternity. ","If you were a tear drop, I would never cry for the fear of losing you. ","Your eyes are bluer than the Atlantic ocean, and baby I'm lost at sea! ","You are so sweet you could put Hershey’s out of business. ","You really shouldn't wear makeup. You're messing with perfection! ","Excuse me, if I go straight this way, will I be able to reach your heart? ","I think it is time I tell you what people are saying behind your back. Nice jass! ","It’s a good thing I wore my gloves today; otherwise, you’d be too hot to handle. ","A boy gives a girl roses. real, fake and he says to her I will stop loving you when all the roses die ","Are those diamonds real? [YES] I was talking about the ones in your eyes. ","You know, you might be asked to leave soon. You are making the other women look bad. ","If you were a burger at McDonald's you'd be the McGorgeous. ","Heaven's missing an Angel. ","Do you have a map? Because I just keep getting lost in your eyes! ","Can you take me to the bakery? Because, I want a Cutiepie like you! ","A face without freckles is like a night sky without stars. ","There are angels in the world are playing, are sleeping and of them is standing in front of me. ","Was your Dad a baker? Because you've got a nice set of buns. ","I didn't believed in heaven, until I saw you. ","I just wanted to show this rose how incredibly beautiful you are! ","Do you remember me? [No.] Oh that's right, we've only met in my dreams. ","I know somebody who likes you but if I weren't so shy, I'd tell you who. ","No wonder the sky is grey today, all the blue is in your eyes. ","I can't think of anyone else I'd rather survive a Zombie Apocalypse with. ","Do you know karate? Cause your body's kickin! ","I’m not drunk, I’m just intoxicated by you. ","Baby, if you were words on a page, you’d be what they call FINE PRINT! ","I know I don't have a chance, but I just wanted to hear an angel speak. ","I don't know which is prettier today, the water, the sky or your eyes. ","You must be Jamaican, because Jamaican me crazy. ","Hey, I didnt know angels flew so low. ","If LOVE was written on every grain of sand in the Sahara Desert that still doesn't equal my love for you. ","Is your last name Campbell? Cause you're mm mm good! ","You're so hot you must've started global warming. ","I don’t normally date models but here's my number. ","What do you and the weather have in common? You're both Hot! ","Do you have a band-aid? (No,why) Because I just scraped my knee falling for you! ","Could you please step away from the bar? You're melting all the ice! ","You: Your father must have been a thief. ","Them: Huh? ","You: Because he stole the stars from the sky and put them in your eyes. ","I wasnt sure if you were a beautiful angel or a sexy devil, but now that I'm close I see heaven in your eyes. ","Do you want to see a picture of a beautiful person I know? (hold up a mirror) ","Did the sun come up or did you just smile at me? ","Are you form Tennessee? Cause you're the only ten I see!!! ","If you were a new hamburger at McDonald's, you would be a McGorgeous. ","I must be lost… because I see paradise. ","So, you must be the reason men fall in love. ","The only thing your eyes haven't told me is your name. ","Were do you hide your wings? ","Would you touch me so I can tell my friends I've been touched by an angel? ","Whenever I think of the finer things in life, I think of exotic cars, fine wine and you. ","It's not my fault that I fell for you, you tripped me! ","I would die a million deaths if it meant I could be with you! ","I must be in heaven because I'm looking at an angel! ","You should go in the water, cuz you're so hot you're on fire! ","Are you a Hurricane [name]? Cause you're blowing me away. ","You're so hot you would make the devil sweat. ","How does it feel to be the most beautiful girl in this place? ","Do you have an eraser? Because I can't get you out of my mind. ","All those curves, and me with no brakes! ","Pointing at a spot on a girls face and say; You got a little beautiful on your face. ","I'm writing a term paper on the finer things in life, and I was wondering if I could interview you. ","Damn girl, I thought diamonds were pretty until I laid my eyes on you! ","I was wondering if you have a moment to spare for me to hit on you? ","Hi, did your license get suspended for driving all these guys crazy? ","If you were a laser you would be set on stunning. ","Did it hurt when you fell? [Girl: Huh?] When you fell from heaven? ","If this bar is a meat market, you must be the prime rib! ","I can hold my liquor but kissing you would make me weak at the knees. ","Vogue just called, they want to put you on the cover. ","If you were a steak you would be well done. ","Was your Dad in the Air Force? Because you're da bomb. ","Is there an airport nearby or is that my heart taking off? ","What size shoe you wear babygirl? I'm gonna guess size sexy! ","Is it hot in here or is it just you? ","We haven't even spoken yet and im already on my knees! ","Your smile lit up the room, so I just had to come over. ","Your eyes are really cute. Oh, wait! I think the right one is a litle cuter than the left one. ","Are you sure you're not an alien because you've just abducted my heart! ","Girl, are you a cop? [No] Cause you're America's Finest ","Is your name Summer? Cause you are hot! ","They say milk does a body good, but you're living proof! ","You're so sweet, you're giving me a toothache. ","I think you just stole something. [What?] My heart. ","So what haven't you been told tonight? ","I'm going to need a tall glass of cold water, cuz baby your making me HOT! ","You make me wish I weren't gay! ","Is your dad a jewel thief? because you're a real jem. ","What are you doing for the rest of your life? Because I want to spend it with you. ","I had a pick up line but, your beautiful eyes continue to interrupt me. ","I am a man of few words. You are beautiful. Would you like to go out? ","Was your father an alien? Because there's nothing else like you on earth! ","Is your name mickey? because your so FINE! ","You're so hot; you make the sun envious. ","Girl you so fine I wish I could plant you and grow a whole feild of y'all! ","(To someone working somewhere where a counter seperates you) You're like a drug to me. Good thing you're over the counter. ","If women were trophies, you'd be first place! ","Can you pull this heart-shaped arrow out of my butt? Some little kid with wings shot me. ","Was your dad king? He must have been to make a princess like you. ","Somebody needs to call the bomb squad, because you're the bomb! ","Did god take the thunder out the skys and put it in your thighs?! ","If you weren’t here I'd be the hottest person in this place. ","Can I borrow your cell phone? I need to call animal control, because I just saw a fox! ","Girl, you got more legs than a bucket of chicken! ","Hey I know you. Yeah, you’re the woman with the million dollar smile! ","Honey, your dad doesn't have a penis. He's got a paintbrush! ","Are you related to Jean-Claude Van Damme? Because Jean-Claude Van Damme you're sexy! ","Damn girl, your legs go all the way up and make and jass of themselves! ","You're hotter than donut grease. ","Can I copy your dance moves? ","I think I've seen you somewhere before. You're the same girl from my dream last night."};
			 int len=data.length; 
		     for (int i = 0; i<len; i++) 
			  {
				sq.Save(data[i], "");
			  }	
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			//
			progressDialog.dismiss();
			super.onPostExecute(result);
		}
    	 
     }

     public static ProgressDialog createProgressDialog(Context mContext) {
         ProgressDialog dialog = new ProgressDialog(mContext);
         try {
                 dialog.show();
         } catch (BadTokenException e) {

         }
         dialog.setCancelable(false);
         dialog.setContentView(R.layout.progressdialog);
         // dialog.setMessage(Message);
         return dialog;
   }

}













