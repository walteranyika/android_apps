package com.walter.leafly;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LoadLeafs extends Activity {
	 String[] data=new String[]{"Phantom Cookies has super dense rock hard nuggets that are dark blue and purple in color. Her pistils are bright carrot orange, and they protrude out all over this super colorful flower. Her crystal content is something from another planet, because you can see amber crystals, both the stalk and head without a microscope.",
			                    "The bright green nugs look like they have been covered in snow, there are so many crystals covering the buds. The nugs have spiky calyxes and each bud is approximately the size of a strawberry. The tangerine colored pistils are short and pointy",
			                    "Mango Haze’s plants grow with long, slender yellow-green leaves. The buds themselves follow the pattern of the leaves, and are tall and slender as well. Lighter green toward the stems, the sugar leaves and calyxes extend into a rich dark green, woven between dark, rust-colored pistils. The buds themselves are covered in a jacket of trichomes - wall-to-wall carpeting, if you will.",
			                    "Dull green leaves and modest trich coverage serve to highlight Chiesel’s pumpkin-orange curls.",
			                    "A very delicately colored strain, Elephant Stomper doesn’t appear as the name might have you believe. Light green and yellow are the primary colors involved in the buds, with a milky layer of tichromes to hold it all together. From afar the buds appear to almost be glowing a bit - and the entire plant is one that exudes delicacy rather than the big and hard stomp stomp of elephants.",
			                    "Emerald Jack has golf ball sized nuggets that are in the shape of diamonds. She has deep forrest green color with accents of lime green and purple through her colorful flower. She has flaxen colored hairs that cover her flowers like a gorilla’s heavily haired body. Her most eye appealing quality is her crystal content, that basically looks like a powdered sugar donut with light colored crystals oozing off of her buds.",
			                    "Her flowers are a little fluffy with a neon green hue to them. They also have highlights of blue and magenta covering her buds. Her pistils are dark golden in color ash they ever so slightly stick out of the buds. Her trichomes are amber in color, and are covering her flowers like they were just dipped into powdered sugar.",
			                    "Her flowers are long and dense with a lime green hue and olive accents. Her pistils are crimson in color, and abundant wrapping around her long buds. Her trichome coverage look like it has been dusted with powdered sugar, because they are abundant and shiny.",
			                    "Kushy. Dense base, lush flowers smooth on touch.",
			                    "Foxtail like main coals that generally become covered in a frosting of THC encrusted trichomes. The lower branches and their flowers are generally smaller and less dense in nature.",
			                    "She is in a class of her own represented as the Ferrari of cannabis. You may be blinded by the reflection off of her mirror like surface in direct light. Each bud condenses a million glistening diamonds piled in every observed inch.",
			                    "Albert Walker buds have an insane appeal to them that calls to you across the room. The bright fire-orange hairs that are highlighted by the white and crystalline background of the bracts give this sample an aura of pure dankness.",
			                    "Girl Scout Cookies buds have an insane appeal to them that calls to you across the room. The bright fire-orange hairs that are highlighted by the white and crystalline background of the bracts give this sample an aura of pure dankness.",
			                    "Way more visible trichomes and density than in your standard OG, its Indica heritage shines through. The nuggets thin out as they protrude from the base, but veer towards the bulkier side, especially for an OG.",
			                    " Extremely dense nuggets that shine with red hairs and keif with a wide base.",
			                    "Ewoks are hairy beasts. Sticky, resin encrusted nugs… reddish orange pistils, mixed with the frosted green sugar leaf. An aroma reminiscent of Tahoe OG, from the Tahoe Alien side of her genetic tree.",
	                            "Caked in Trichomes, G-13 has (or had) a dense, perfect Indica profile.",
	                            "","","","","","","","","","","",
	                            "","","","","","","","","","",
	                            "","","","","","","","","","","",""
	 
	 };
  
	 String names[]={"Phantom Cookies","Presidential OG","Mango Haze","Chiesel",
			 "Elephant Stomper","Emerald Jack"," Shiva Skunk","Afghan Haze",
             "Fishman Kush",
             "Bob Marley",
             " Red Stem Afghani",
             " Albert Walker",
             "Girl Scout Cookies",
             "Kosher Kush",
              "Private Reserve",
              "Ewok",
              "Urban Legend",
              "Afghan Skunk",
              "Willy’s Wonder","Tahoe OG Kush","Space Bomb","Black Jack","Chernobyl",
              "Shiva","Tangerine Dream","White Lightning","Silver Sufer","Sweet Berry",
              "Sour Mango","Skunkberry","Strawberry Ice","Master Kush","Nebula","Northern Lights",
               "Pot of Gold","Romulan","Silver Haze","The Purps",
               "Sugar Kush","THC Bomb","Bubba OG","Apollo 13","Medicine Man","Mango",
               "Lavender","Kali Mist","Ice","Hash Plant","Grape Kush","Flo"};
     int[]photos=new int[]{
    		               R.drawable.phantomcookies,   R.drawable.xpresidential_og,
    		               R.drawable.mango_haze,R.drawable.chiesel,R.drawable.elephant, R.drawable.emerald_jack, R.drawable.shiva_skunk, R.drawable.afghan_haze, R.drawable.afghan_haze,                           R.drawable.bob_marley,                           R.drawable.red_stem_afghani,                           R.drawable.albert_walker,                           R.drawable.girlscoutcookies,                           R.drawable.kosherkush,                           R.drawable.private_reserve,                           R.drawable.ewok,                           R.drawable.urban_legend,                           R.drawable.afghan_skunk,                           R.drawable.willies_wonder,                           R.drawable.tahoe_og,                           R.drawable.space_bomb,                           R.drawable.black_jack,                           R.drawable.chernobyl,                          R.drawable.shiva,                          R.drawable.tangerine_dream,                           R.drawable.whitelightning,                           R.drawable.silfer_surfer,                           R.drawable.sweetberry,
    		               R.drawable.sourmango, R.drawable.skunkberry, R.drawable.strawberry_ice,    		               R.drawable.master_kush,    		               R.drawable.nebula,    		               R.drawable.northern_lights,    		               R.drawable.pot_of_gold,    		               R.drawable.romulan,    		               R.drawable.silver_haze,    		               R.drawable.the_purps,
    		               R.drawable.sugar_kush,R.drawable.thc_bomb,R.drawable.bubba_og,R.drawable.apollo_thirteen,R.drawable.medicine_man,R.drawable.mango,
    		               R.drawable.lavendar,R.drawable.kalimist,R.drawable.ice,R.drawable.hash_plant,
    		               R.drawable.grape_kush,R.drawable.flo
    		               };
     String teams[]={"Hybrid",  "Hybrid" ,  "Hybrid","Sativa","Hybrid",  "Hybrid",     
    		     "Hybrid","Hybrid",
                 "Hybrid",
                 "Sativa",
                 "Indica",
                 "Hybrid",
                 "Hybrid",
                 "Hybrid",
                 "indica",
                 "Hybrid",
                 "Indica",
                 "","","","","","","","","","","",
                 "","","","","","","","","","",
                 "","","","","","","","","","","",""};
     TextView tvName;
     TextView tvDesc;
     ImageView img_view;
     int pos=0;
     String [] facts={"Marijuana is the most common illegal drug used in the United States. Approximately 100 million Americans have tried marijuana at least once, and more than 25 million have smoked it in the last year. ","According to one national survey on drug use, each day approximately 6,000 Americans try marijuana for the first time. ","Worldwide, it is estimated that about 162 million adults use marijuana at least once per year, and 22.5 million use the drug daily. ","After alcohol, marijuana is the most popular recreational or mood-altering drug used worldwide. ","Just under 40% of high school students in the U.S. report using marijuana at least once in their life, and 20% report using it regularly. ","According to one report, it would take 800 joints to kill a person—but the cause of death would be carbon monoxide poisoning. ","There are over 200 slang terms for marijuana in the popular vernacular. Some of the more common nicknames include pot, grass, weed, hash, and ganja. ","The international and scientific name for marijuana is cannabis. However, the substance is most commonly called marijuana within the United States. ","The name marijuana comes from a Mexican slang term for cannabis and is believed to have derived from the Spanish pronunciation of the names Mary and Jane. (The two names were also common Mexican military slang for a prostitute or brothel.) Marijuana came into popularity as a name for cannabis in the U.S. during the late 1800s.b ","The cannabis plant can grow in nearly any environment and averages one to two inches of growth per day and up to 18 feet total in ideal conditions. ","The primary active ingredient in marijuana is THC (delta 9 tetrhydrocannabinol). It is this chemical that produces marijuana’s mind-altering effects. ","The psychoactive side effects of THC in small doses include loss of inhibition, elation, and a distorted sense of time. The drug can also cause increased visual sensitivity and heightened imagination. ","Depending upon the weather conditions, soil type, and time of harvest for a cannabis plant, as well as the specific mixture of dried leaves and flowers in the marijuana product, a sample of marijuana can contain anywhere from 3% to 20% THC. ","Cannabis seeds were used as a food source in China as early as 6000 B.C.a ","Marijuana was first used as a medicinal drug in 2737 B.C. by Chinese emperor Shen Nung ","The first recorded use of marijuana as a medicinal drug occurred in 2737 B.C. by Chinese emperor Shen Nung. The emperor documented the drug’s effectiveness in treating the pains of rheumatism and gout. ","The first law in the American colonies regarding marijuana was a 1619 law that actually required farmers to grow the hemp plant. Once harvested, hemp was useful for clothing, sails, and rope. ","During the temperance movement of the 1890s, marijuana was commonly recommended as a substitute for alcohol. The reason for this was that use of marijuana did not lead to domestic violence while alcohol abuse did. ","Marijuana was first severely restricted as a recreational and medicinal drug in the U.S. by the Marihuana Tax Act of 1937. The law did not prohibit marijuana use but imposed such a heavy tax that legal sale and use became nearly impossible. ","In October of 1937, Samuel Caldwell was the first U.S. citizen arrested under the Marihuana Tax Act for selling marijuana without paying the newly mandated tax. He was fined $1,000 and sentenced to four years of hard labor in Leavenworth. ","Prior to its ban, hemp was a staple cash crop of the family farm in early America. The first two drafts of the United States Declaration of Independence were written on paper made from hemp. ","The Controlled Substances Act of 1970 made it illegal to possess, use, buy, sell, or cultivate marijuana in the United States. The law classifies marijuana as a Schedule 1 drug, meaning it has a high potential for abuse and no acceptable medical use. ","Marijuana production and trafficking make up the world’s largest drug market and the substance can be grown in almost every country. The United Nations Office on Drug and Crimes (UNODC) has data on 172 countries and territories known to grow marijuana. ","Paraguay is believed to be the world’s largest producer of marijuana. ","According to the UNODC, there are several countries worldwide where greater than 8% of the population are said to use marijuana. Among those countries are the United States, Canada, England, Spain, France, South Africa, and New Zealand. ","In 2007, nearly 900,000 arrests for marijuana violations were made in the United States. Approximately 90% of offenders charged with marijuana-related crimes were arrested for possession only. ","Marijuana was easily obtained at the local grocery store or pharmacy until the early 1940s ","From 1850 to 1942, marijuana was listed in the United States Pharmacopoeia as a useful medicine for nausea, rheumatism, and labor pains and was easily obtained at the local general store or pharmacy. ","In 2003, Canada became the first country in the world to offer medical marijuana to pain-suffering patients. ","In 1996, California became the first U.S. state to legally allow medical marijuana for patients with a valid doctor’s recommendation"};
	 @Override
  
protected void onCreate(Bundle savedInstanceState)
	 {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			Bundle extras=getIntent().getExtras();
			setContentView(R.layout.loadleafs);
			tvName=(TextView) findViewById(R.id.textName);
			tvDesc=(TextView) findViewById(R.id.textDesc);
			img_view=(ImageView) findViewById(R.id.imageView1);
			 LinearLayout layout=(LinearLayout) findViewById(R.id.full_view);
			 ScrollView sv=(ScrollView) findViewById(R.id.scrollView1);
			 ActionBar actionBar = getActionBar();
				actionBar.setTitle("Leaf Strains");
			    // Enabling Up / Back navigation
			   // actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF1919")));
			if(extras!=null)
			{
			  int x=extras.getInt("data");	
			  Log.d(names[x], data[x]);
			  tvName.setText(names[x]);
			  String zz=data[x];
			   if(zz.equals("") || zz.isEmpty())
			   {
				   int r = (int) ((Math.random() * (29 - 0)) + 0);  
				   zz=facts[r];
			   }
			  tvDesc.setText(zz);
			  img_view.setImageResource(photos[x]);
			  pos=x;
			}
			
			sv.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
				
				@Override
				public void onSwipeLeft() {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
					moveNext();
					super.onSwipeLeft();
				}
				@Override
				public void onSwipeRight() {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                    movePrev();
					super.onSwipeRight();
				}
				
			});
    }
 public void moveNext()
 {
	 int len=photos.length;
	 if(pos>=0 && pos<len-1)
	 {
		  tvName.setText(names[pos]);
		  String zz=data[pos];
		   if(zz.equals("") || zz.isEmpty())
		   {
			   int r = (int) ((Math.random() * (29 - 0)) + 0);  
			   zz=facts[r];
		   }
		  tvDesc.setText(zz);
		  img_view.setImageResource(photos[pos]); 
		  pos++;
	 }else
	 {
		pos=0;
		 tvName.setText(names[pos]);
		  String zz=data[pos];
		   if(zz.equals("") || zz.isEmpty())
		   {
			   int r = (int) ((Math.random() * (29 - 0)) + 0);  
			   zz=facts[r];
		   }
		  tvDesc.setText(zz);
		 img_view.setImageResource(photos[pos]);	
		 
	 }
	 
 }
 public void movePrev()
 {
	 int len=photos.length;
	 if(pos>=0 && pos<len-1)
	 {
		  tvName.setText(names[pos]);
		  String zz=data[pos];
		   if(zz.equals("") || zz.isEmpty())
		   {
			   int r = (int) ((Math.random() * (29 - 0)) + 0);  
			   zz=facts[r];
		   }
		  tvDesc.setText(zz);
		  img_view.setImageResource(photos[pos]); 
		  pos--;
	 }else
	 {
		 pos=len-2;
		 tvName.setText(names[pos]);
		  String zz=data[pos];
		   if(zz.equals("") || zz.isEmpty())
		   {
			   int r = (int) ((Math.random() * (29 - 0)) + 0);  
			   zz=facts[r];
		   }
		  tvDesc.setText(zz);
		 img_view.setImageResource(photos[pos]);	
		 
	 }
	 	 
	 
 }
	@Override
public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		if(item.getTitle().equals("Help and About"))
		{
			Intent i=new Intent(this,Help_About.class); 
			startActivity(i); 			
		   
		}
		
		return true;
	}
	@Override
public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}		
}
