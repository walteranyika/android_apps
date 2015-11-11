package com.walter.Mchongoano;

import java.io.File;
import java.util.ArrayList;

import com.example.template.R;


import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
		actionBar.setTitle("Cheka");
		Toast.makeText(getApplicationContext(), "Refresh to populate new Items", Toast.LENGTH_SHORT).show();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9933cc")));
		if(!checdbExists())
		{
			populate();			
		}
		SQLiteHandler db=new SQLiteHandler(getApplicationContext());

		t=new ArrayList<String>();
		t=db.getData();
		adapter=new CustomAdapter(this, t);
		this.setListAdapter(adapter);
	    registerForContextMenu(getListView());

	}
     @Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		//super.onCreateContextMenu(menu, v, menuInfo);
	      menu.setHeaderTitle("Mchongoano");
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
				ii.putExtra(Intent.EXTRA_SUBJECT, "Mchongoano");
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
	 public void populate()
   {
	   
 		ProgressDialog progressDialog=null;
 
 			// TODO Auto-generated method stub
 			 if (progressDialog == null) {
 			        progressDialog = createProgressDialog(MainActivity.this);
 			        progressDialog.show();
 			        }
		 
		 
		 
		 
		 SQLiteHandler sq=new SQLiteHandler(getApplicationContext());
	      String[] data={"haha... ati grandmasako ni wa zamo mpaka alisahau handbag yake kwa Ark ya Noah ","ati wewe ni dingo hadi ukipita na fone ya mse hurudi bila feature ya MPESA ","Ati wewe ni mjinga ulipanda mti ndio ukuwe branch manager ","wewe ni mbroke kila saa hadi simu yako iko na M-deni badala ya M-pesa ","ati paka yenu ni holy design ma panya ziki kam ina zi sendia email ati woiye pls tokeni hapa? ","Naskia wesewe ni black hadi ile siku God ali kuumba alisema oops have burnt one ","Ati naskia nagos yako ni chinku hadi ikona game inaitwa Thika road ","Ati naskia ukona sura mbaya hadi traffic police ukusmamisha daily akikuuliza hii accident ulireporti kweli? ","Ati naskia teli yenyu nipyenga hadi nyinyi huwatch na straw.. ","Ati naskia kwenyu kunawagondi hadi gate mumeiweka kwa hao.....haahaaa ","Sistako ni mdogo hadi akimeza njugu anakaa nikama akona ball ","Ati kwenyu kumekauka hadi hamuezi bonga matope ","Ati naskia grandbudako amezeeka hadi yeye huishi kwa museum.. ","Ati naskia nyinyi kwenyu mumesota hadi nyinyi hukula ugali na story za nyama ","Naskia wewe ni fala siku moja ukiwa kwa court iliskia judge akisema order in court ukaitisha kebab na coke ","Ati wewe ni fala kunasiku ulichukuwa ruler ukaenda nayo bed ndio uangalie umelala masaa mangapi ","Ati wesewe ni fala hadi ukitaka kumeza dawa wewe hukata kando ndio usipate side effects ","Ati wesewe ni mchafu hadi ukienda kwa lake kuswim fish hutoka kutema mate ","Ati ninaskia sistako ni mnono hadi wewe humwita 2 in a million ","Ati wewe ni fala uliulizwa wingi wa mawe ukasema kokoto ","Dem yako ni fala yeye hu vaa pencil na rubber na hapendi kusoma ","Ati naskia budako yeye hukuwa ndedhe lakini yeye hu insist ku pocket ","Ati naskia dem yako akimeza mate yeye hu nyora ","Ati ninaskia siku yako ya fao kunyonji ulikimbia hosi ukidhani wewe ni msick ","Hao yenyu ni mzee hadi mume ishikilia na stand ya bike ","Ati naskia chali yako ni fala hadi yeye huweka radio kwa fridge ndio askize cool muzik ","Bazenga wako ni fala yeye huhepa job kuenda kuswing na gate ","radio yenu hushika petrol station ","TV yenu imezeeka mpaka ikiwashwa lazima muisukume. ","manzi yako ni mjinga hadi akafail pregnancy test ","dem wako ni fala hadi aliomba DJ kwa club mchezee ile ngoma ya progi ya inspekta mwala ","Ati naskia grandmasako ni mzee hadi magova walimnyanganya ID ","Dem yako nimu argly hadi akilia machozi zinaenda na nyuma ","Kwenyu nyinyi ni wa jinga hadi jirani akiwasalimia muna discuss answere ","Ati naskia wesewe ni mpyenga hadi wewe huenda mountain haike kwa pevu ","Ati dem yako nimweusi hadi chameleon ikimuangukia inachanganyikiwa ","Ati naskia bazenga wako ni mtall hadi akitafuta loose ya thao yeye hutafuta kwa wadhii wa ndege ","nkt! una maaaringo na we mblak design uki smile una kaa kama njahi! ","wewe ni mzee hadi birth cert yako imeandikwa kwa leaves ","dem yako ana boobs bwaku hadi yeye uvalia bra long sleeved ","Ati naskia wewe ni mjinga hadi ulirepeat naso mara mbili ","Ati dem yako akona sura mbaya hadi yeye huadmitiwa kwa store ya hosi ","Ati wewe ni fala hadi ulidangaya jina yako kwa exam ","ati kwenu ni ocha hadi samosa zenyu ni za githeri ","Wewe ni mfake hadi simu yako ilipozimwa SIM card iliungua ","nkt! kwenda huko! una ringa na niliskia ati we ni mfala umezoea kuride bike design uki panda mat una kunja mguu moja ya rodhi ","Manzi yako ni mshamba siku ya Bday yake ulimshow happy birth day naye akakujibu you too.. ","Ati naskia masako amekonda hadi budako akimuhag anaonekana nika amevaa tai ","nini umekimya hivyo? kwani umesota hadi simu yako ime rust memory ya kuweka airtime. ","Una mkono rough hadi ukishika mouse computer inadai found new hardware. ","Umezoea kula mogoka hadi unaogopa kumeza mboga ","Ati wewe nimchafu mpaka ukiingia kwa lake kuswim fish zinatoka kutema mate ","Ati umezoea kuokoa jahazi hadi safcom hukuuliza utahama lini? ","Ati Unakipara ukivaa polo neck unakaa roll on ","Ati wewe ni mzee hadi ukizaliwa OLD TESTAMENT ilikuwa inaitwa TESTAMENT ","Umezoea kutumia wasee please call me mpaka ukipress *130* simu ukutolea phone book ujichagulie. ","We ni dentist mpro hadi unangoa blue tooth ya phone yako. ","Ati wewe mweusi tu sana mpaka hauna bright ideas. ","Ati mtoi wenu alizaliwa akiwa mblack mpaka doki akasema Kameungua. ","We ni mkonda hadi uki dance helicopter una take off... ","UKo na macho kubwa hadi wewe huitwa eye witness ","uko na macho kubwa hadi unaitwa eye witness ","we ni mweusi mpaka ukishika mtoi anadoze akidhani ni usiku. ","ati we ni mfat hadi unaamka na sessions ","We ni mshamba vile uliskia comps inaeza pata virus uliibuyia condom ","ati nyumba yako ni mzee hadi imeshikiliwa na stand ya bike ","ati we ni mshamba hadi huweka redio kwa fridge ndo uskie cool musik ","kichwa yako ni kubwa badala ya kujifunga bandana unajifunga bedsheet. ","Manzi yako ana a** ndogo akitashu u move mbele. ","ati jo! uko na mguu ka smal mpaka huwezi kanyangia risto za baze ","manzi yako anakichwa biggy hadi akienda salon ye hushukwa style zote ","Ati we ni mzee hadi insted ya kuchukua ID we hurudisha! ","We ni mnono hadi ukikalia ashu inakuwa five bob mbili ","Nyanyako ni mapengo criaaas hadi anabondewa maji. ","ti we ni mu ugly hadi unainvitiwa kwa mazishi ndio wa2 walie ","uko na kisogo kubwa mpaka ukiangalia juu inaingia kwa mfuko ya nyuma ","Una mkono nono hadi vidole zako zote ni thumbs. ","we ni mweusi hadi ukichomwa na jua unanuka firestone. ","mko wengi instead of kutumia bafu mnaenda cattle dip. ","Eti keja yako ni kidogo mpaka umechora viti kwa wall. ","Budako ni mdosi hadi ana kipara mbili. ","una macho biggy hadi ukiblink zinalia �bonyi bonyi� !! ","manzi yako ni msupa badala ya kumea fudhi ye humea ma flowers ","ati we ni mkonde mpaka ukiingia class watoi huwika oooonnnneeeee!!! ","Ati we ni mkonde badala ya mbu kukuuma hudonate zikisema �He needs it mo than we do� ","ATI UMECHANUKA HADI FUDHI UMEZINYOA MOHAWK ","Dame wako amebeat hadi hufunga always na suspenders! ","Ati we ni mjinga hadi ukienda kwa hoteli unauliza ka kuna mayai alafu unadai upewe mama yake ","Nyanyako mzee alizaliwa rainbow ikiwa blak n white ","We ni msee wa choir damu hadi we hushuta desyn ya vigelegele alililililiiiii ","we ni mchoyo hadi we utema mate kwa wallet. ","ati uko na masikio bigi tym ya kuzaliwa ulishikwa ka cup ya UEFA. ","Ati we ni mneat phone yako iko na curtains ","we ni mweusi ukijipaka vaseline unakaa ni ka umevaa leather jacket. ","phone yako mzee ikivibrate SMS zinamwagika� ","una kichwa delicate hadi ukifikiria maskio zinaingia ndani ","kichwa yako kubwa hadi ina-appear kwa ID both sides ndio itoshee ","ati meno zako zina spaces mob mpaka ulimi yako inakaa ni kama iko kwa jela ","Manzi yako mnono hadi ukimchinjia lazima umuingize box tatu ","nyumba yenu ni ndogo mpaka nyi hutake turns kubreathe ","ati mko wengi design nyi hulala kwa kitanda mkiwa mmejipanga ka vibiriti ","Mlango ya hao yenu ni soo nyinyi hufungua wall instead ","naskia haukuzaliwa ulitangazwa kwa radio ","wewe ni mrefu mpaka skin yako ina ishia kwa magoti ","TV yenu ni nzee mpaka mnaizima na maji ","unajipaka make up mob ukilia unaacha erosion ","Ati wewe ni mkonda mpaka ukienda toile unatumia uzi badala ya tissue!!! ","Wewe ni mzee hadi id numba yako iko na roman numberz�.. ","haga ya dame wako ni small mpaka unamdara na tweezer ","ati mbuyu wa u ni mstingy ye uphotocopy socks zake ndio asave wode ya kuosha ","manzi yako ana ulimi ingine rough mpaka akiongea yeye huskip kama CD ","ati wewe ni mkondi hadi ukishuta una sikia uchungu ","Ati wewe ni mfupi mpaka unakata cabbage na ngazi? ","dame wako ni mshake hadi ulipomnunulia radio ya kwanza alibanjuka hadi newz ","wewe ni mwizi mpaka unaji ibia kutoka kwa mfuko ya right una weka kwa ya left ","Kwenu ni ocha mpaka mathree hutandika vitambaa kwa viti ","Ati computer yako ni slow hadi wewe huiwasha asubuhi ili uweze kuwatch movie usiku ","Umezoea kutuma plis call mpaka mathegi walikuja kwenu ukatuma *130*999# ","wewe ni mnono watu hufanya tizi by runnin around u ","Mbuyu wako ni mnono akikalia hashu akiamka inakuwa kobole mbili ","Ati ngozi yako mbaya inakaa kama combat! ","una kichwa kubwa mpaka ukikimbia unabaki umefloat ","Budako fala ulimtuma kitabu ya class 8 akaleta mbili za class 4 ","ati wee ni m old hadi marks zako za kcpe ziliandikwa kwa stone tablets ","Dame yako ana lips kubwa mpaka anatumia roll on ka lip balm ","We ni �mgalenjin orichinol hadi chogoo zenu huwiga �gogorigooo�� ","Ati nyumba yenyu ni kombo mpaka ukilala asubuhi unajipata nnje ","Ati una sura mbaya ukilia machozi zinaenda juu ","Ngotha yako chuma ikiingia lunch unaitoa na spark ","Ati wewe mkonde ukiinua mkono unaona maminyoo zikikatiana ","Nyinyi ni mababi mpaka mnaweka sufuria zenyu kwa saucer ","Nyinyi ni mababi mpaka muna hao ya glass ambayo ina madirisha ya mawe ","Ati wewe ni mfupi sana hadi uki pigwa picha lazima camera man alale kwa floor ","Ati nyanyako ni mblack design venye alijoin ma brownie yeye alibaki ame itwa �blackie� ","Kwenu nyi ni mafala hadi mna patia kuku zenu maji moto ati ndio zitoe mayai boilo ","Nyumba yenu ni round lakini mnafight kulala back left ","Gari yeni ni noma siku za mvua inavaa gumboots ","Mobile 4n yako ni kali mpaka inamixx ma ringtones ","Mko wadosi mpaka ng�ombe zenu huvalishwa Trust condoms zikienda kulala ","Mbuyu wako huhepa job kwenda kuswing na gate ","Nyinyi wengi kwa family yenu mpaka munaweka bumps musigongane ","wewe mweusi mpaka ukirushiwa mawe inarudi kuitisha torch! ","Wewe in mweusi hadi ukitoka nje giza inasema mmh ","Nyinyi mko poor mpaka kwa 10 bob zenu Moi amevaa vest imeraruka. ","ati wewe ni mweusi mpaka badal ya wasee kukuita wao huimbai le advert ya kiwi hebu kitokeze jionyease simama mbele ya watu... ","nyanyako mzee mpaka viatu zake zinasema Air Moses and the Burning Bush ","Kwenyu nyinyi ni wengi mpaka lazima utoke nje kuchange mind ","Kwenu kumekauka mpaka ngombe zenu zinatoa milkpowder ","Boss!!! Umesota mpaka huwezi ku afford ku pay attention ","Mbuyu wako ni mnono akijipima weight inajiandika 1 at a time!!! ","budako fala hadi alipoingia k-bu aliambia konda amshikie chuma ndio atoe pesa. ","ati wee mweusi mpaka ukikutana na mzungu asubuhi anakushow good evening! ","ati mbwa yenyu ni noma wagondi wakitokea inaanza kuimba teren teren ","yani wewe ni fala hadi unatry kuwrite o upside down ","kwenu u are upto date mpaka watoi wenu wakipigwa wana vibrate badala ya kulia. ","wewe ni mnono mpaka uki pigwa picha ina andikwa to b continued ","Tv yenyu ni mzee mpaka safo zikipita zinawacha vumbi kwa nyumba. ","Nyinyi wadosi mna screen kubwa mpaka Tom & Jerry wakikimbishana wanapumzikia katikati juu ya kuchoka. ","Ati wewe in mfupi mpaka ukimbao una anguka!! ","manzi yako mnono ukimpeleka kwa cinema ana sit next to everybody! ","wewe ni fala uligongwa na a parked car. ","Wewe ni danda ukiingia lift unafinya number tisa ikufikishe isich ","Ati wewe ni danda ulienda sunday school parallel programe ","Wewe mshamba mara yako ya kwanza kununua ballgum ulisema hutaki ya green ati haijaiva ","Ati una shida ya ngozi mpaka ukikaza rasa unalia ukiinua mkono unacheka. ","Wewe mshamba mara yako ya kwanza kununua ballgum ulisema hutaki ya green ati haijaiva ","Nyumba yenu imetengezwa na nyasi wezi wakikuja wao husema fungua mlango au tulete ngombe. ","Nyumba yenu imetengezwa na nyasi wezi wakikuja husema fungua mlango au tulete ngombe. ","nyumba yenu ni ya round lakini nyinyi wote mnangangana kulala kwa corner. ","ATI KWENU IMEJENGWA NA MCHANGA WEZI WAKI KAMU WANA SEMA FUNGUA AMA TUKOJOE ","we ni danda hadi unabeep upate bonga points ","Ati kwenu nyinyi ni wengi mpaka ukiingiza kifunguo kufungua mlango unasikia Wacha kunifinya ","Ati kwenu watoto wote ni ma boys solo mpaka last born wenu anaitwa Ah nilijua tu. ","Ati kwenu watotoi ni wengi mpaka lst born anaitwa Ah Tosha ","Ati ana dhambi nyingi mpaka shetani akikusikia anasema excuse me pliz ","Budako huhepa job ndio akuje home ku watch kids next door!! ","manze ukona kihara lakini bado we hu insist barber akunyoe box! ","Ati nyanyako mzee hadi amejaa matope kwa armpits...salaala! ","mbwa wenyu mdaku hadi badala ya kubark husema nteren nteren! ","Nilikupata umezubaa ukiangalia chupa ya orange juice...ati juu imeandikwa concentrate. ","Nyanya yako Kipara lakini bado analilia CURL KIT ","We Mshamba ulisikia computer inashika VIRUS ukainunulia condom ","kwenyu chapo za klisi nyi uzipika january alafu munazifile hadi dise ","kwenyu nyinyi wachafu hadi mende na nzi zimeanza kucomplain ati mnatupa. ","ati wewe uwet bed hadi masayako amehang kibao kwa bed yako imeandikwaonyo kali usikojoe hapa. ","Ati kwenu nyinyi ni wagondi mpaka helicopter ikipita juu inazima engine na mataa halafu ina tiptoe ndio musigondi propeller ","mamako mnono mpaka akipanda kwa weighing machine inajiandika no cows allowed ","wewe ni mjinga mpaka ulifail blood test!! ","wewe ni mweusi mpaka ukishootiwa bullet iarudi kuuliza wapi target!! ","Wewe una kichwa kubwa ukienda salon kusukwa nywele mwenye kukusuka akimaliza side moja ana chukua taxi kwenda hio side ingine. ","kwenyu nyinyi ni wengi mpaka mko na number plate ","nyinyi kwenu ni wa black mkiingia kwa moti yenyu dirisha zinakuwa tinted ","nyinyi ni wengi father akicome home anasema wananchi? ","nyinyi m-mesota mpaka mnalipa kiberiti na monthly installments!! ","wewe ni mshamba mpak siku yako ya kwanza kununua gari ulisema nifungie kwa karatasi!! ","tv yenyu umezeka mpaka inasoma news za jana na leo!! ","kwenyu mko wengi mpaka mnaritrenchiwa!! ","tv yenyu ndogo mpaka Kasavuli hupiga magoti kusoma news!! ","tenje yenyu deadly inamix kiss na ramogi!! ","wewe mjinga mpaka ulifail baby class!! ","nyinyi wachafu mpaka panya zilihama!! ","wew ni mwerevu mpaka ukienda mpregnancy test unapata B+ ","m-meokoka mpaka wezi wakikuja kwenyu umbwa zenyu zinawika iba tu mungu anakuona ","wewe ni mkonde mpaka ukisimama kwa ukuta inakaa kama ni crack!! ","Ngombe yenu ni mzee mpaka inatoanga yorghurt ","we mfupi jo unashuka kwa kitanda ya juu ya double decker na parachute ","Mamako m..Lazy..anapika chapo moja na anatoa fotocopy ya hizo zingine ","wewe ni mshamba mpaka the first time ulipanda mathree ulitandika vitambaa ","Kwenyu nyi wengi badala ya sofasets muko na viti za stadium. ","Nyinyi mmesota hadi the first time mlisikia story ya SMS mllidhani ina maanisha Send Me Something. ","ati kuku zenu ni gisty mpaka zikitagaa mayai zinapeleka kwa fridge ","hao yenyu ni ya nyasi wezi wakicome wao husemafungua mlango ama tulete ngombe. ","ati we ni mangaa hadi sunday skool ulipelekwa apprved ","ati manzi yako ni mfupi hadi akifungua mdomo una cheki shmo yarasa ","aah chali yako ana matako KUBWA hadi akilala blanketi anuma swallow ","manze umesota hadi chapo weupika na pasi ya makaa ","kamanzi kako ni kasmall hadi ukitaka kukapiga munju unatumia straw ","We mamako alikula chapo b4 uzaliwe ulitoka umevaa kombat ","Kwenu nyi ni wengi mkikutana munaulizana we ni nani? ","Hao yenu ni bigi mpaka in ma slumz ndani. ","Mdomo yako ni bigi unaeza chew loaf ya bread ka chiklet. ","Nyi kwenu ni masagodi hadi TV yenu inashika channel Heaven. ","Ati we ni mweusi mpaka ukisnapiwaphoto ina jiadika He was here! ","una mbo ingine ndogo ukitaka kunyora lazima umbao ndio uiget ","Ati we ni mEvil Mpaka Madevil wana demostrate juu ati unawawai compe!! ","ati nyii ni wagodi mpaka tv yenu haileti adverts!! ","ati dogi yenu ina ulimi heavy mpaka ikibark unasikia wuuth! wuuth! ","Una mobile phone ingine mzee ikiisha credo inashow insert coin kwa screen. ","Una mobile phone ingine mzee ikiisha credo inashow insert coin kwa screen. ","wewe in mweusi mpaka ukijiseti kea wall ya white wathii wanathanii no short cut ","Ati wewe no mnono mpaka ukiturn ukirudi wanaangusha welcome back party........ ","kipara yako ni kubwa watu lazima wasimame waone nywele yacko. ","Ati wee una mapua bigest hadi unanusa chakula ya kesho. ","Ati wee mjinga uli2mia mwakenya kufanya blood test. ","Ati we ni mnoma hadi ndoto zako huanzia..previously on.. ","Ati wewe ni mslim na dame yako ni mnono mkitembea nayeye usiku mpatane na polisi wanauliza? Wewe msichana kwanini unatembea na silaha? ","Kwenu TV yenu ndogo Njoroge Mwaura aliloose wieght atoshee... ","nyumbani kwenu kuchafu mpaka cockroach wanaingia wamevaa boots na nose mask. ","Mko rich mpaka mna graze ngombe zenu na remote ","we una kichwa big mpaka ikiingia kwa swimo watu udhani ni island ","kwenu mmeokoka sana mpaka mkieka food kwa table mnaimba chorus ndiyo muombee chakula !!!! ","kwenu ni wadosi mpaka mnaua siafu na gun. ","church yenu imeendela mpaka badala ya watu kulia wakiomba wana vibrate ","eti mamako ni mnono mpaka ana stretchmarks kwa uso ","ati we ni mdark mpaka unakosa kuhave bright ideas! ","Ati manzi yako ana pimples mob akipigwa picha yu andikwa tu join the dots ","budako ni mzee hadi akipanda ju ya mti anarap zilizopendwa. ","kwenyu mko msoto design mende zinajitegemea!!! ","wee ni m-bright sana na manzi yako ni danda sana hadi anataka kukuse kungarisha sufuria eti wewe ni super-bright ","ati manzi yako amesota mpaka badala ya kutumia always ye hutumia sometimes!! ","Tunga sentensi ukitumia neno jumamosi .... Omosh.. usiwase jiko ju mamosi tajaa kwa nyumba. ","Eti nimesikia.......Mbuyu wenyu mshort ana committ suicide kwa pavement! ","Gari yenu ni mzee hadi ikiruka Bump baba anasema shukeni mpange viti! ","Naskia wewe ni chopii hadi viti zako zikispoil instead ya kuzipeleka hardware unamwita chairman..... ","simu yako ni noma badala ya kuitisha PIN inaitisha msumari ","paka yenu ni nzee imebakisha siku moja iwe simba. ","nyinyi ni wachoyo eti mkipika chapati mna weka notice inasema tumehama ","We ni mstupid ulipoulizwa green inasimamia ni kwa flag ya kenya ulisema mpesa ","we ni mgondi hadi ukipewa noti ya ngiri unairudisha ka Kenyatta amevaa vest ","We ni mrough hadi ukiguza mouse comp inawika new hardware detected ","Kuku zenu ziko mbele hadi zikishikwa na jogoo zinawika you knocked me down ","TV yenu ni small hadi Swaleh Mdoe huingia ki gully creepa. ","Nyi ni mababi hadi kuku za neighbour zikiwika kukurukuu zenu zinaholler same ","umesota na uko down hadi we hudial *130*FACEBOOK# ku update status. ","Manzi yako ni mhairy hadi ako na Afro kwa nipples. ","Phone yako ni mzee ukiiturn inamwaga credo ","Uko na mdomo kubwa we hudema chapo ka criba. ","We ni fala hadi ulidial *144# kucheck balance kwa ATM. ","Manzi yako ni mthwack hadi time ulimchapa ulikimbizwa kwa Ocampo na wasee wa ANIMAL RIGHTS. ","We ni mslow excess hadi dreams zako ni audio pekee. ","We ni mfupi hadi we hucheza rudgy na njugu. ","Uko na rasa small chali yako hukuspank na thumb. ","We ni fala ulipatikana uki fine tune microwave yenu ishike XYZ ","Uko down tu sana hadi ulifikiria youtube ni contraceptive. ","We ni mchokozi mbaya hadi phone yako iko na tuma ngumi via m-ngumi ","We ni mlunje damu hadi we huredeem kuku na bonga points. ","mamako ni mjanja ye hupaka rob kwa chapo ndio zikue menthol. ","kuku zenu ni fala zikiulizwa why did the chicken cross the road zinajibu because it was jogoo road ","Mtoi wa Jelimo ni mshort hadi hukimbia 800m juu ya chapo. ","Uko na phone kubwa hadi ni bouncer club 20. ","We ni geek/nerd hadi maskio zako ziko na spinnerz. ","TV yenu ni oldskul sahii ndio mnapata advert ya chai jaba. ","We ni Mugly hadi ukiwa mtoi masako alikuwa anakusho kula ama niletee kioo(msirudie hii tena imetosha). ","Uko na skin rough we huscratch credo na forehead kisha inakusho Nakufeel pia. ","Siz yako ni msupu hadi akienda kushonde zinatoka sausages za kenchic ","Wee ni mnoma hadi ukislapiwa kwa facebook wall yako unafura uso. ","wee ni mugly hadi ulipozaliwa mamako alisema shitwhat the hell did i carry in ma stomach ","we ni sura mbaya hadi buda yako akikuona ye husemai wish i used a condom. ","meno yako ni brown ukinywa maji hufika tumboni ikiwa chai ","ati we ni mlafi sana unakulanga shimo ya katikati ya doughnut kwanza. ","wewe ni mbroke kila saa hadi unatumia M-deni badala ya M-pesa. ","ati umesota hadi ukihama unahamanga na flashdisk ","meno yako ni brown tu sana uki spit una spit matope... ","eti chief ako na kwalala ndogo ye udunga condom na belt! ","ati boyfriend yako nimnono mpaka yeye huenda kwa cow dip kuoga ","mazi deki yako ni slim mpaka lazima ufunge condom belt ","naskia grandmasako nimzee lakini bado anataka kua AMERICAN NEXT TOP MODEL ","mazi ati wewe umesota mpaka wewe hutumia mate ya kuma kama mafuta ","site yako ni supuu mpaka google inataka kuikatia! ","we ni fala hadi unafua ngotha na maji moto ndo ikauke haraka. ","ati demu yako ni so thin mpaka hatumii always anatumianga elastoplast ","atii wee ni msupuu mpaka choo yenu hukuona inambao! ","tuliskia wewee huwork posho mill mpaka huwezi oga na maji moto sababu uta nuka sembe. ","budako ana tumbo sooo mpaka ye hutuck in na mwiko ","dame yako ni mnono hadi vile ulimkatia aliingia box mbili ","ati kuku zenu zilikataa kutaga mayai juu jogoo zimechil ","Naskia wewe uko na udaku mob(unajua mob)tu sana hadi wadhee waliku-nickname Mass Media! ","ati wewe ni mkonda hadi ukishiba unafura mgongo ","dame yako ni mkonde tu sanaa hadi mkitembea na yeye tao wasee wanakuulizambona umebeba MSHALE!!? ","mathako nii m-ugly tu sanaa hata lotion yake huitwa WHY BOTHER ","kwenu mmesota hadi mkikula kuku na chapo na si chrissi tumbo inadai Found new hardware ","Mbuyu wako ako 2 down anafikiria marende ni News Anchor KBC ","dogi yenyu iko mbele hadi kabla ifukuze mgondi inadunga sports shoes ","Naskia we ni fala tu sana siku ya kwanza kuona advert ya YU network(new mobile network)ulidhani ni box office movie ya kutoka states! ","wee ni dingo noma hadi wee uiba sukari kwa chai... ","nyanyako ni mzee mpaka yeye hufanywa carbon dating research ","Nyumba yako ni chafu mpaka panya zikitoka zinapanguza miguu. ","Si naskia we ni mzee hadi uko na deni ya Noah ule wa bible. ","ati dogi zenyu nikonda mpaka zikitombana zina washa kuni ","Nyi muko wengi hadi mukipigwa family photo wengine wanahang kwa frame ","Brother yako ako na skin tight mpaka aki-wink mguu moja inainuka ","we ni mweusi mpaka mabeste wakikupita wanasemahaiya cheki shortcut! ","ati mathako mnono akivaa nguo ya yellow watu hufikiria ni taxi ","ati wewe mweusi mpaka unaacha fingerprints zako kwa makaa ","Budako mrefu mpaka anauza mahindi kwa watu kwa plane ","Doggy yenu noma inabark ikitweng werf!! werf!! ","TV yenyu ndogo mpako mukiwatch 100m athletics munalizia kuiona kwa ma neighbours. ","Nyanyako mzee mpaka chawa za nywele yake hutembea na bakora. ","Budako ni fala alienda kubuy ngombe akaona ikikojoa akasema sitaki hiyo imetoboka. ","Nyinyi kwenu mumesota mpaka mna-kunywanga chai kwa kifuniko ya Bic. ","Paka wenu ni mnoma mpaka aki shika panya inaitisha chumvi ","Wewe ni bowlegged na manzi wako ni knock-kneed mukisimama pamoja muna spell OX ","ATI KWENU KUNA INSECURITY HADI POLICE STATIONS ZINAFUNGWA SAA KUMI ","ati wewe umesota lakini ukona mashilingi kwa kichwa ","budako ni fala ati alienda kubuy pikipiki akakutwa akapiga magoti acheki ka ni ya kike ama ndume ","ati wewe ni dingo hadi ukipita na fone ya mse hurudi bila feature ya MPESA ","Ati wewe ni mjinga ulipanda mti ndio ukuwe branch manager ","Dem yako ni fala alienda kwa kibanda ku ulizia kama wakona apple earphones ","ati chali yako ame chapaa design ukipatikana na yeye tao ... kanjo wana kushika for littering ","ati moti yenu ni nze nyi umwagia maji kuizima ","ati teli yenu ni pyienga design Mwakazi na Mutegi usoma Breakfast Show kama wame keti kwa floor ","ati kwenu nyi ni wasafi nyi utashu kwa jwala halafu mna dump ","ati laptop yako ni chafu design kuna ma chokosh kwa recyclebin ","nasikia wewe ni sofara mpaka ukipewa chai ya maza unakunywa unabakisha ya strong tea ","eish?! ati paka yenu ni lazy design iki taka kudema panya ina tuma SMS blast kuzi warn ","Uko na sura kiatu hadi we hutumika kuadvert sumu ","Ati naskia wesewe ukona kichwa biggie hadi ulichaguliwa kama HEAD GIRL high school ","Ati naskia wewe ni mfupi wehuendesha panadol kama tire ","Ati naskia wewe ni mnono hadi uki simama kwa scale inaonyesha phone number yako ","Ati naskia wewe ni mtukutu ukienda toile wewe hukataa kusquat ","Ati naskia wewe siku ya fao kupanda mathree ulisalimia kila mutu"};
		  int x=data.length;
	      for (int i = 0; i < x; i++) 
		  {
			sq.Save(data[i], "");
		  }
		  progressDialog.dismiss();
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













