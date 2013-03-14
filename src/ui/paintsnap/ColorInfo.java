package ui.paintsnap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import colorLibrary.ColorHelper;
import colorLibrary.HEX;
import colorLibrary.RGB;

public class ColorInfo extends Activity{

	private Button favoriteButton, analogColor1, analogColor2, complementaryColor;
	private TextView brandText, rgbText, hexText, hsvText, colorText;
	private View colorBox;
	private String hexString;
	MySQLiteHelper dbHelper = new MySQLiteHelper(this);
	private RGB comp;
	private HEX compHex;
	private String a1, a2;
	OnClickListener l1, l2;
	ColorHelper helper = new ColorHelper();
    ProgressDialog progDialog;
	HEX temp;
	String family;
	String subfamily;

	private class runBehind extends AsyncTask<Void, Void, ArrayList<RealColor>> {
		protected ArrayList<RealColor> doInBackground(Void... args) {
			return database();//processBitmap();
		}

		protected void onPostExecute(ArrayList<RealColor> result) {
			display(result);
			progDialog.dismiss();
		}
	}
	

	public void onCreate(Bundle savedInstanceState) {
        progDialog = new ProgressDialog(this); 
        progDialog.setIndeterminate(true); 
        progDialog.setMessage(getText(R.string.loadingText)); 
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
        progDialog.show();
		Bundle extras = getIntent().getExtras(); 
		hexString = extras.getString("HEX value");
		temp = new HEX(hexString);
		family = helper.getFamily(temp.HEXToRGB());
		subfamily = helper.getSubFamily(temp.HEXToRGB());
		new runBehind().execute();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.colorinfo);

		//These are all views, stuff, but I don't think they're needed
		colorBox = (View) findViewById(R.id.colorBox);

		favoriteButton = (Button) findViewById(R.id.star);
		analogColor1 = (Button) findViewById(R.id.analogcolor1);
		analogColor2 = (Button) findViewById(R.id.analogcolor2);
		complementaryColor = (Button) findViewById(R.id.complementarycolor);

		brandText = (TextView) findViewById(R.id.brandtext);
		rgbText = (TextView) findViewById(R.id.rgbtext);
		hexText = (TextView) findViewById(R.id.hextext);
		hsvText = (TextView) findViewById(R.id.hsvtext);
		colorText = (TextView) findViewById(R.id.colorText);

		



		l1 = new View.OnClickListener() {
			public void onClick(View v) {
				Favorites.favDB.addEntry(hexString);
				favoriteButton.setBackgroundResource(R.drawable.appbar_star_minus);
				favoriteButton.setOnClickListener(l2);
			}
		};
		l2 = new View.OnClickListener() {
			public void onClick(View v) {
				Favorites.favDB.removeFav(hexString);
				favoriteButton.setBackgroundResource(R.drawable.star);
				favoriteButton.setOnClickListener(l1);
			}
		};

		if(Favorites.favDB == null){
			Favorites.favDB = new FavoritesDB(getApplicationContext());
			Favorites.favDB.open();
		}
		
		if(Favorites.favDB.contains(hexString)){
			favoriteButton.setBackgroundResource(R.drawable.appbar_star_minus);
			favoriteButton.setOnClickListener(l2);
		}
		else{
			favoriteButton.setBackgroundResource(R.drawable.star);
			favoriteButton.setOnClickListener(l1);

		}

		analogColor1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
				intent.putExtra("HEX value", a1);
				startActivity(intent);
			}
		});

		analogColor2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
				intent.putExtra("HEX value", a2);
				startActivity(intent);
			}
		});

		complementaryColor.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
				Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
				intent.putExtra("HEX value", compHex.toString());
				startActivity(intent);
			}
		});
	}
	
	private ArrayList<RealColor> database()
	{
		dbHelper.openDataBase();
		ArrayList<RealColor> comparedColors = MySQLiteHelper.getPaintColors(family, subfamily, temp.HEXToRGB());
		dbHelper.close();
		return comparedColors;
	}
	
	private void display(ArrayList<RealColor> comparedColors)
	{
		//Processing
				

				/*
				ColorHelper helper = new ColorHelper();
				HEX temp = new HEX(hexString);
				String family = helper.getFamily(temp.HEXToRGB());
				String subfamily = helper.getSubFamily(temp.HEXToRGB());
				*/

				
				/*THIS IS THE DATABASE
				dbHelper.openDataBase();
				ArrayList<RealColor> comparedColors = MySQLiteHelper.getPaintColors(family, subfamily, temp.HEXToRGB());
				dbHelper.close();*/

				Random rand = new Random();
				Random rand2 = new Random();

				int range = comparedColors.size();
				int r1 = rand.nextInt(range - 1) + 1;
				int r2 = rand2.nextInt(range - 1) + 1;

				RealColor badass = comparedColors.get(0);
				RealColor analog1 = comparedColors.get(r1);
				RealColor analog2 = comparedColors.get(r2);

				if (analog1.getName().equalsIgnoreCase(analog2.getName()))
				{
					analogColor2.setBackgroundColor(0xFF000000);
					analogColor2.setClickable(false);
				}

				a1 = analog1.hex.substring(1);
				a2 = analog2.hex.substring(1);
				comp = new RGB(badass.r, badass.g, badass.b);
				comp = comp.complement();
				//compHex = comp.complement().RGBToHEX();
				compHex = comp.RGBToHEX();
				
				
				//HSV Stuff
				float[] tempHSV = new float[3];
				Color.RGBToHSV(badass.r, badass.g, badass.b, tempHSV);
				DecimalFormat twoPlaces = new DecimalFormat("#.##");
				double formattedHSV0 = Double.valueOf(twoPlaces.format(tempHSV[0]));
				//double formattedHSV1 = Double.valueOf(twoPlaces.format(tempHSV[1]));
				//double formattedHSV2 = Double.valueOf(twoPlaces.format(tempHSV[2]));
				int formattedHSV1 =(int) (100*tempHSV[1]);
				int formattedHSV2 = (int) (100*tempHSV[2]);
				String hsb = + formattedHSV0 + "°, " + formattedHSV1 + "%, " + formattedHSV2 + "%";
				

				//Processing
				complementaryColor.setBackgroundColor(Integer.parseInt(compHex.toString(), 16)+0xFF000000);
				

				//VIEW STUFF
				analogColor1.setBackgroundColor(Integer.parseInt(a1, 16)+0xFF000000);
				analogColor2.setBackgroundColor(Integer.parseInt(a2, 16)+0xFF000000);


				
				rgbText.setText("RGB: (" + badass.r + ", " + badass.g + ", " + badass.b + ")");
				colorText.setText(badass.name);

				hsvText.setText("HSV: " + hsb);
				hexText.setText("HEX: #" + hexString);
				brandText.setText("Brand: " + badass.brand);
				colorBox.setBackgroundColor(Integer.parseInt(hexString, 16)+0xFF000000);
				
				if(badass.name.equalsIgnoreCase(""))
				{
					colorText.setText("SF: " + subfamily);
				}

				if(badass.brand.equalsIgnoreCase(""))
				{
					brandText.setText("Brand: Google it");
				}
	}

}