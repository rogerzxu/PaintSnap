package ui.paintsnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {
	
	private Button takePhoto, chooseFromAlbum, colorPalette, savedColors, colorComparison, settings;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		takePhoto = (Button) findViewById(R.id.takephoto);
		chooseFromAlbum = (Button) findViewById(R.id.choosefromalbum);
		colorPalette = (Button) findViewById(R.id.colorpalette);
		savedColors = (Button) findViewById(R.id.savedcolors);
		colorComparison = (Button) findViewById(R.id.colorcomparison);
		settings = (Button) findViewById(R.id.settings);
		
		
        takePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(), PaintSnapActivity.class);
        		startActivity(intent);
            }
        });
        
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), SomeAlbumActivity.class);
        		//startActivity(intent);
            }
        });
        
        colorPalette.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), ColorPaletteActivity.class);
        		//startActivity(intent);
            }
        });
        
        savedColors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), SavedColorsActivity.class);
        		//startActivity(intent);
            }
        });
        
        colorComparison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(),  ColorComparisonActivity.class);
        		//startActivity(intent);
            }
        });
        
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        		//startActivity(intent);
            }
        });
        
        
        
        
        
        
        
	}
	
}