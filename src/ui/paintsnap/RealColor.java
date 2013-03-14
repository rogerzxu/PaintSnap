package ui.paintsnap;

public class RealColor {
	
	public String name;
	//public String date;
	public int r;
	public int g;
	public int b;
	public String hex;
	public String hsv;
	public String brand;
	//public boolean isGreyScale;
	public boolean isFavorite;
	
	public RealColor (String n, /*String dateTaken,*/String br, int rValue, int gValue, int bValue, String hexValue, String hsvValue/*, boolean greyScale*/) {
		name = n;
		brand = br;
		//date = dateTaken;
		r = rValue;
		g = gValue;
		b = bValue;
		hex = hexValue;
		hsv = hsvValue;
		//isGreyScale = greyScale;
	}
	
	public RealColor() {
		name = "Blank Color";
		//date = "12/21/2012";
		brand = "Awesome Corp.";
		r = 0;
		g = 0;
		b = 0;
		hex = "#000000";
		hsv = "0°, 0°, 0°";
		//isGreyScale = false;
	}
	
	/*public RealColor(String hex)
	{
		MySQLiteHelper dbHelper = new MySQLiteHelper(this);
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}*/

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}

	public String getHsv() {
		return hsv;
	}

	public void setHsv(String hsv) {
		this.hsv = hsv;
	}

	/*public boolean isGreyScale() {
		return isGreyScale;
	}

	public void setGreyScale(boolean isGreyScale) {
		this.isGreyScale = isGreyScale;
	}*/

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	
	

}
