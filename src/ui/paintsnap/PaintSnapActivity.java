package ui.paintsnap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import colorLibrary.HEX;
import colorLibrary.RGB;

public class PaintSnapActivity extends Activity {
	private ImageView mImageView;
	private Bitmap mImageBitmap;
	private static final int ACTION_TAKE_PHOTO_S = 2;
	private Button c1;
	private Button c2;
	private Button c3;
	private Button c4;
	private Button c5;
	private String x1 = "";
	private String x2 = "";
	private String x3 = "";
	private String x4 = "";
	private String x5 = "";
    ProgressDialog progDialog;
    int delay = 40;                  // Milliseconds of delay in the update loop
    Button button1, button2;
    int typeBar;                     // Determines type progress bar: 0 = spinner, 1 = horizontal

	MySQLiteHelper dbHelper = new MySQLiteHelper(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		c1 = (Button) findViewById(R.id.color1);
		c2 = (Button) findViewById(R.id.color2);
		c3 = (Button) findViewById(R.id.color3);
		c4 = (Button) findViewById(R.id.color4);
		c5 = (Button) findViewById(R.id.color5);
		
		c1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!x1.equalsIgnoreCase(""))
				{
					Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
					intent.putExtra("HEX value", x1);
					startActivity(intent);
				}
			}
		});

		c2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!x2.equalsIgnoreCase(""))
				{
					Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
					intent.putExtra("HEX value", x2);
					startActivity(intent);
				}
			}
		});

		c3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!x3.equalsIgnoreCase(""))
				{
					Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
					intent.putExtra("HEX value", x3);
					startActivity(intent);
				}
			}
		});

		c4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!x4.equalsIgnoreCase(""))
				{
					Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
					intent.putExtra("HEX value", x4);
					startActivity(intent);
				}
			}
		});

		c5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!x5.equalsIgnoreCase(""))
				{
					Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
					intent.putExtra("HEX value", x5);
					startActivity(intent);
				}
			}
		});

		try
		{
			dbHelper.createDataBase();
		}
		catch (IOException e)
		{
			throw new Error("Couldn't create a database.");
		}

		try
		{
			dbHelper.openDataBase();
			System.out.println("------------------------------");
			HEX h = dbHelper.getEntry(1);
			System.out.println(h.HEX);
			dbHelper.close();
		}
		catch (SQLException e)
		{
			throw new Error("Couldn't open the database.");
		}

		mImageView = (ImageView) findViewById(R.id.imageView1);
		mImageBitmap = null;

		Button picSBtn = (Button) findViewById(R.id.btnIntendS);
		setBtnListenerOrDisable(picSBtn, mTakePicSOnClickListener,
				MediaStore.ACTION_IMAGE_CAPTURE);
		
		Button favs = (Button)findViewById(R.id.Favorites);
		favs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(), Favorites.class);
        		startActivity(intent);
            }
        });
	}
	
	public void onResume(Bundle savedInstanceState) {
		super.onResume();
		setContentView(R.layout.main);
		
		c1 = (Button) findViewById(R.id.color1);
		c2 = (Button) findViewById(R.id.color2);
		c3 = (Button) findViewById(R.id.color3);
		c4 = (Button) findViewById(R.id.color4);
		c5 = (Button) findViewById(R.id.color5);
		
		c1.setBackgroundResource(R.drawable.white_border);
		c2.setBackgroundResource(R.drawable.white_border);
		c3.setBackgroundResource(R.drawable.white_border);
		c4.setBackgroundResource(R.drawable.white_border);
		c5.setBackgroundResource(R.drawable.white_border);
	}

	Button.OnClickListener mTakePicSOnClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			dispatchTakePictureIntent(ACTION_TAKE_PHOTO_S);
		}
	};
	
	private void dispatchTakePictureIntent(int actionCode) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		switch(actionCode) {
		default:
			break;			
		} // switch

		startActivityForResult(takePictureIntent, actionCode);
	}

	public static boolean isIntentAvailable(Context context, String action) {
		final PackageManager packageManager = context.getPackageManager();
		final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}

	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
	}
	
	private ArrayList<RGB> processBitmap() {
		imageLibrary.Colors2 test = new imageLibrary.Colors2();
		return test.getColorsFromImage(mImageBitmap);//need loading animation
	}
	
	private void displayBitmap(ArrayList<RGB> colors) {
		Iterator<RGB> iter = colors.iterator();
		
		c1.setBackgroundResource(R.drawable.white_border);
		c2.setBackgroundResource(R.drawable.white_border);
		c3.setBackgroundResource(R.drawable.white_border);
		c4.setBackgroundResource(R.drawable.white_border);
		c5.setBackgroundResource(R.drawable.white_border);

		try{
			RGB next = iter.next();
			String hex = toBrowserHexValue(next.r) + toBrowserHexValue(next.g) + toBrowserHexValue(next.b);
			c1.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			x1 = hex;

			next = iter.next();
			hex = toBrowserHexValue(next.r) + toBrowserHexValue(next.g) + toBrowserHexValue(next.b);
			c2.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			x2 = hex;

			next = iter.next();
			hex = toBrowserHexValue(next.r) + toBrowserHexValue(next.g) + toBrowserHexValue(next.b);
			c3.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			x3 = hex;

			next = iter.next();
			hex = toBrowserHexValue(next.r) + toBrowserHexValue(next.g) + toBrowserHexValue(next.b);
			c4.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			x4 = hex;


			next = iter.next();
			hex = toBrowserHexValue(next.r) + toBrowserHexValue(next.g) + toBrowserHexValue(next.b);
			c5.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			x5 = hex;
		}catch(Exception e){}
		mImageView.setImageBitmap(mImageBitmap);
	}

	private static String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case ACTION_TAKE_PHOTO_S: {
				if (resultCode == RESULT_OK) {
					handleSmallCameraPhoto(data);
                    progDialog = new ProgressDialog(this); 
                    progDialog.setIndeterminate(true); 
                    progDialog.setMessage(getText(R.string.processingText)); 
                    progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
                    progDialog.show();
					new runBehind().execute();
				}
				break;
			} // ACTION_TAKE_PHOTO_S
		} // switch
	}
	
	private class runBehind extends AsyncTask<Void, Void, ArrayList<RGB>> {
	     protected ArrayList<RGB> doInBackground(Void... args) {
	        return processBitmap();
	     }

	     protected void onPostExecute(ArrayList<RGB> result) {
	         displayBitmap(result);
	         progDialog.dismiss();
	     }
	 }
	
	private void setBtnListenerOrDisable(Button btn,
			Button.OnClickListener onClickListener, String intentName) {
		if (isIntentAvailable(this, intentName)) {
			btn.setOnClickListener(onClickListener);
		} else {
			btn.setText(getText(R.string.cannot).toString() + " "
					+ btn.getText());
			btn.setClickable(false);
		}
	}

	public void toggleButtons(Boolean state){
		c1.setEnabled(state);
		c2.setEnabled(state);
		c3.setEnabled(state);
		c4.setEnabled(state);
		c5.setEnabled(state);
	}
}
