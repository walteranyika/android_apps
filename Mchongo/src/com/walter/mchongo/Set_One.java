package com.walter.mchongo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Set_One extends Activity {
    int pos=0;
    String []data={"mbwa wenyu mdaku hadi badala ya kubark husema nteren nteren! ", "wewe ni mkonde mpaka ukisimama kwa ukuta inakaa kama ni crack!! ", "manze umesota hadi chapo weupika na pasi ya makaa ", "ati kuku zenu ni gisty mpaka zikitagaa mayai zinapeleka kwa fridge ", "Ngotha yako chuma ikiingia lunch unaitoa na spark ", "We ni “mgalenjin orichinol hadi chogoo zenu huwiga ‘gogorigooo’” ", "Mbuyu wako ni mnono akijipima weight inajiandika 1 at a time!!! ", "Nyinyi wadosi mna screen kubwa mpaka Tom & Jerry wakikimbishana wanapumzikia katikati juu ya kuchoka. ", "Una mobile phone ingine mzee ikiisha credo inashow insert coin kwa screen. ", "Nyumba yenu imetengezwa na nyasi wezi wakikuja wao husema fungua mlango au tulete ngombe. ", "Ati wewe ni mfupi sana hadi uki pigwa picha lazima camera man alale kwa floor ", "wewe ni mjinga mpaka ulifail blood test!! ", "Nyinyi mko poor mpaka kwa 10 bob zenu Moi amevaa vest imeraruka. ", "aah chali yako ana matako KUBWA hadi akilala blanketi anuma swallow ", "Ati we ni mweusi mpaka ukisnapiwaphoto ina jiadika He was here! ", "Ngombe yenu ni mzee mpaka inatoanga yorghurt ", "ati we ni mangaa hadi sunday skool ulipelekwa apprved ", "Wewe ni danda ukiingia lift unafinya number tisa ikufikishe isich ", "tv yenyu umezeka mpaka inasoma news za jana na leo!! ", "Mobile 4n yako ni kali mpaka inamixx ma ringtones ", "una kichwa kubwa mpaka ukikimbia unabaki umefloat ", "nyinyi kwenu ni wa black mkiingia kwa moti yenyu dirisha zinakuwa tinted ", "kwenyu nyinyi wachafu hadi mende na nzi zimeanza kucomplain ati mnatupa. ", "tenje yenyu deadly inamix kiss na ramogi!! ", "Kwenu kumekauka mpaka ngombe zenu zinatoa milkpowder ", "nyumba yenu ni ya round lakini nyinyi wote mnangangana kulala kwa corner. ", "wewe in mweusi mpaka ukijiseti kea wall ya white wathii wanathanii no short cut ", "Wewe mshamba mara yako ya kwanza kununua ballgum ulisema hutaki ya green ati haijaiva ", "Ati nyumba yenyu ni kombo mpaka ukilala asubuhi unajipata nnje ", "Ati wewe in mfupi mpaka ukimbao una anguka!!"};
    TextView tv;
	@Override	
    protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_one);
		tv=(TextView)findViewById(R.id.textView1);
	    tv.setText(data[pos]);
	}   
    public void move_next(View v)
    {
       pos++;
       if(pos>=0 && pos<30)
       {
    	   tv.setText(data[pos]);
       }
       else
       {
    	 pos=0;
    	 Toast.makeText(this, 
    			 "Reached End",
    			 Toast.LENGTH_SHORT).show();
       }
    	
    }
    public void move_prev(View w)
    {
    	pos--;
        if(pos>=0 && pos<30)
        {
     	   tv.setText(data[pos]);
        }
        else
        {
     	 pos=29;
     	 Toast.makeText(this,"Reached Beginning", Toast.LENGTH_SHORT).show();
        }
    }
   
   
   
   
   
   
   
   
   
	
}
