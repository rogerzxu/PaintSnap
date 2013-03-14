package ui.paintsnap;

import java.util.ArrayList;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Favorites extends ListActivity {
	/** Called when the activity is first created. */

	public Context context;
	private static Cursor cursor;
	public static FavoritesDB favDB = null;

	protected static ArrayList<String> hexes;
	protected static ArrayAdapter<String> aa;
	protected static FavAdapter fa;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorites);

		context = getApplicationContext();
		hexes = new ArrayList<String>();
		if(favDB == null){
			favDB = new FavoritesDB(this);
			favDB.open();
		}

		aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hexes);


		//ListView listView = (ListView)findViewById(R.id.list);
		ListView listView = getListView();
		fa = new FavAdapter(this, hexes);
		listView.setAdapter(fa);
		//listView.setAdapter(aa);
		//registerForContextMenu(listView);
		populateList();
		updateArray();
		System.out.println("---------------"+hexes.size());
	}

	public void populateList()
	{
		cursor = favDB.getAllEntries();
		startManagingCursor(cursor);
		updateArray();
	}

	public void updateArray()
	{
		cursor.requery();
		hexes.clear();
		if (cursor.moveToFirst())
			do {
				String result = cursor.getString(1);
				hexes.add(result);
			} while (cursor.moveToNext());
		//aa.notifyDataSetChanged();
		fa.notifyDataSetChanged();
	}

	protected void onListItemClick(ListView l, View v, int position, long id){
		int index = position;
		int[] map = new int[hexes.size()];
		Cursor c = favDB.getRowIds();
		startManagingCursor(c);
		if (c.moveToFirst()){
			int i = 0;
			do {
				int num = Integer.parseInt(c.getString(1));
				map[i] = num;
				i++;
			} while (c.moveToNext());
		}
		Intent intent = new Intent(context, ColorInfo.class);
		String x = favDB.getFavItem(map[index]);
		intent.putExtra("HEX value", x);
		Favorites.this.startActivity(intent);

	}

	/**public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId()==R.id.list) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			String[] menuItems = {"View", "Delete"};
			for (int i = 0; i<menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = {"View", "Delete"};
		String menuItemName = menuItems[menuItemIndex];

		int index = info.position;
		int[] map = new int[hexes.size()];
		Cursor c = favDB.getRowIds();
		startManagingCursor(c);
		if (c.moveToFirst()){
			int i = 0;
			do {
				int num = Integer.parseInt(c.getString(1));
				map[i] = num;
				i++;
			} while (c.moveToNext());
		}

		if(menuItemName.equals("View")){
			Intent intent = new Intent(context, ColorInfo.class);
			String x = favDB.getFavItem(map[index]);
			intent.putExtra("HEX value", x);
			Favorites.this.startActivity(intent);
		}
		else{
			favDB.removeEntry(map[index]);
			populateList();
			updateArray();
		}
		return true;
	}**/

	private class FavAdapter extends BaseAdapter{

		private Activity activity;
		private ArrayList<String> data;
		private LayoutInflater inflater = null;

		public FavAdapter(Activity a, ArrayList<String> data){
			activity = a;
			this.data = data;
			inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View vi=convertView;
			if(convertView==null)
				vi = inflater.inflate(R.layout.item, null);

			TextView b = (TextView)vi.findViewById(R.id.color);
			b.setBackgroundResource(R.drawable.white_border);
			String hex = data.get(position);
			b.setBackgroundColor(Integer.parseInt(hex, 16)+0xFF000000);
			return vi;
		}

	}

	protected void onStart() {
		super.onStart();
		populateList();
		updateArray();
	}

}