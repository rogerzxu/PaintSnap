package ui.paintsnap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class FavoritesDB{

	private SQLiteDatabase db;
	private FavoritesDBHelper dbHelper;
	private final Context context;

	private static String DB_NAME = "paintColors4.jet";
	private static final int DB_VERSION = 1;  // added semester, when you add or delete fields, you must update the version number!

	private static final String FAV_TABLE = "favorites";
	public static final String FAV_ID = "fav_id";   // column 0
	public static final String HEX = "hex";
	public static final String[] FAV_COLS = {FAV_ID, HEX};

	public FavoritesDB(Context ctx) {
		context = ctx;
		dbHelper = new FavoritesDBHelper(context, DB_NAME, null, DB_VERSION);
	}

	public void open() throws SQLiteException {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException ex) {
			db = dbHelper.getReadableDatabase();
		}
	}

	public void close() {
		db.close();
	}

	public String getFavItem(long ri) throws SQLException {
		Cursor cursor = db.query(true, FAV_TABLE, FAV_COLS, FAV_ID+"="+ri, null, null, null, null, null);
		if ((cursor.getCount() == 0) || !cursor.moveToFirst()) {
			throw new SQLException("No course items found for row: " + ri);
		}
		// must use column indices to get column values
		String result = cursor.getString(1);
		cursor.close();
		return result;
	}

	public boolean contains(String hex){
		Cursor cursor = getAllEntries();
		if (cursor.moveToFirst())
			do {
				String result = cursor.getString(1);
				if(result.equals(hex)){
					cursor.close();
					return true;
				}
			} while (cursor.moveToNext());
		cursor.close();
		return false;
	}

	public long addEntry(String hex) {
		// create a new row of values to insert
		ContentValues cvalues = new ContentValues();
		// assign values for each col
		cvalues.put(HEX, hex);
		// add to course table in database
		return db.insert(FAV_TABLE, null, cvalues);
	}

	public Cursor getAllEntries() {
		return db.query(FAV_TABLE, FAV_COLS, null, null, null, null, null);
	}

	public boolean removeEntry(long ri) {
		return db.delete(FAV_TABLE, FAV_ID+"="+ri, null) > 0;
	}
	
	public boolean removeFav(String hex){
		Cursor cursor = getAllEntries();
		if (cursor.moveToFirst())
			do {
				String result = cursor.getString(1);
				System.out.println("----------------------------------------"+result);
			} while (cursor.moveToNext());
		cursor.close();
		return db.delete(FAV_TABLE, HEX+"=\""+hex+"\"", null) > 0;
	}

	public Cursor getRowIds(){
		return db.rawQuery("SELECT fav_id,* FROM favorites ;", null);
	}

	private static class FavoritesDBHelper extends SQLiteOpenHelper {

		// SQL statement to create a new database
		private static final String DB_CREATE = "CREATE TABLE " + FAV_TABLE
				+ " (" + FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HEX + " TEXT);";

		public FavoritesDBHelper(Context context, String name, CursorFactory fct, int version) {
			super(context, name, fct, version);
		}

		@Override
		public void onCreate(SQLiteDatabase adb) {
			// TODO Auto-generated method stub
			adb.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase adb, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w("FavoritesDB", "upgrading from version " + oldVersion + " to "
					+ newVersion + ", destroying old data");
			// drop old table if it exists, create new one
			// better to migrate existing data into new table
			adb.execSQL("DROP TABLE IF EXISTS " + FAV_TABLE);
			onCreate(adb);
		}
	}

}