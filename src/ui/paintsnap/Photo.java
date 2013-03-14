package ui.paintsnap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Photo extends Activity{
	
	private Button colorButton1, colorButton2, colorButton3;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		
		colorButton1 = (Button) findViewById(R.id.color1);
		colorButton2 = (Button) findViewById(R.id.color2);
		colorButton3 = (Button) findViewById(R.id.color3);
		
        colorButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
        		startActivity(intent);
            }
        });
        
        colorButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
        		//startActivity(intent);
            }
        });
        
        colorButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        		//Intent intent = new Intent(getApplicationContext(), ColorInfo.class);
        		//startActivity(intent);
            }
        });
		
	}
}