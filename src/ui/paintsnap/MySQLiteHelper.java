package ui.paintsnap;

import colorLibrary.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/ui.paintsnap/databases/";
    private static String DB_NAME = "paintColors4.jet";
    private static SQLiteDatabase sqlDb; 
    private final Context dbContext;
    
  
    public MySQLiteHelper(Context context)
    {
    	super(context, DB_NAME, null, 1);
        this.dbContext = context;
        
    }
 
    public void createDataBase() throws IOException
    {
    	boolean dbExists = checkDataBase();
 
    	File f = new File(DB_PATH);
    	if (!f.exists()) {
    	f.mkdir();
    	}
    	
    	if (dbExists)
    	{
    		//return;
    	}
    	else
    	{
        	this.getReadableDatabase();
        	try
        	{
    			copyDataBase();
    		}
        	catch(IOException e)
        	{
        		throw new Error("There was an error copying the database.");
        	}
    	}
    }
    
    public boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }
 
    /**
     * Copies database from the local assets folder to the new database
	 */
    private void copyDataBase() throws IOException
    {	
    	InputStream is = dbContext.getAssets().open(DB_NAME);
    	String filePath = DB_PATH + DB_NAME;
    	OutputStream os = new FileOutputStream(filePath);
    	
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = is.read(buffer)) > 0)
    	{
    		os.write(buffer, 0, length);
    	}
 
    	os.flush();
    	os.close();
    	is.close();
    }
 
    public void openDataBase() throws SQLException
    {
        String myPath = DB_PATH + DB_NAME;
    	sqlDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }
 
    @Override
	public synchronized void close()
    {
    	if (sqlDb != null)
    	{
    		sqlDb.close();
    	}
    	super.close();
	}
 
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//not necessary here
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		//not necessary here
	}
	
	//edit or delete methods here
	public HEX getEntry(long ri) throws SQLException {
		String[] temp = {"HEX"};
		Cursor cursor = sqlDb.query(true, "Paint", temp, "_id="+ri, null, null, null, null, null);
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			throw new SQLException("No entry items found for getEntry row: " + ri);
		}
		// must use column indices to get column values
		int index = cursor.getColumnIndex("HEX");
		String hex = cursor.getString(index);
		HEX h = new HEX(hex);
		//RGB rgb = h.HEXToRGB();
		cursor.close();
		return h;
	}

	//---------------------------------------------------------------------------------------
	
	public static ArrayList<RealColor> getPaintColors(String family, String subfamily, RGB color) {
		ArrayList<HEX> colors = getFamilyEntry(family, subfamily);
		ArrayList<RealColor> comparedColors = new ArrayList<RealColor>();
		RealColor temp;
		double threshold = 10.0;
		String hex;
		for(int i = 0; i < colors.size(); i++)
		{
			if (colors.get(i).compareTo(color.RGBToHEX()) < threshold)
			{
				int r = colors.get(i).HEXToRGB().rToInt();
				int g = colors.get(i).HEXToRGB().gToInt();
				int b = colors.get(i).HEXToRGB().bToInt();
				float[] tempHSV = new float[3];
				//final String LOG_TAG = "Really?";
				Color.RGBToHSV(r, g, b, tempHSV);
				String hsb = "" + tempHSV[0] + tempHSV[1] + tempHSV[2];
				hex = "'" + "#" + colors.get(i).toString() + "'";
				Cursor cCursor = sqlDb.query("Paint", new String[] {"_id", "HEX", "Family", "Subfamily", "Brand", "Collection", "Name", "Number"}, 
							"HEX like " + hex, null, null, null, null);
				//Log.v(LOG_TAG, "cCursor Count: " + cCursor.getCount());
				int index = cCursor.getColumnIndex("HEX");
				cCursor.moveToFirst();
				//Log.v(LOG_TAG, "cCursor Index: " + cCursor.getString(index + 5));
				temp = new RealColor(cCursor.getString(index + 5), cCursor.getString(index + 3), r, g, b, cCursor.getString(index), hsb);
				comparedColors.add(temp);
				cCursor.close();
			}
		}
		return comparedColors;
	}
	
	public static ArrayList<HEX> getFamilyEntry(String family, String subfamily) throws SQLException {
		ArrayList<HEX> colors = new ArrayList<HEX>();
		String[] temp = {"HEX"};
		Cursor cursor = sqlDb.query(true, "Paint", temp, " Family LIKE '"+ family + "' AND Subfamily LIKE '" + subfamily + "'", null, null, null, null, null);
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			throw new SQLException("No entry items found for getEntry row: " + family);
		}
		// must use column indices to get column values
		if(cursor.moveToFirst())
		{
			//Log.v(LOG_TAG, "THIS IS THE CURSOR COUNT: " + cursor.getCount());
			for (int x = 0; x < cursor.getCount(); x++)
			{
				cursor.moveToPosition(x);
				int index = cursor.getColumnIndex("HEX");
				String hex = cursor.getString(index);
				//Log.v(LOG_TAG, "CURSOR POSITRON: " + x + " hex: " + hex.substring(1));
				HEX h = new HEX(hex.substring(1));
				colors.add(h);
			}
		}
		cursor.close();
		return colors;
	}

}
