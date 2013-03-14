package colorLibrary;

public class RGB {
	
	public int r;
	public int g;
	public int b;
	
	public RGB(int red, int green, int blue) {
		this.r = (red > 255) ? 255 : ((red < 0) ? 0 : red);
		this.g = (green > 255) ? 255 : ((green < 0) ? 0 : green);
		this.b = (blue > 255) ? 255 : ((blue < 0) ? 0 : blue);
	}
	
	public boolean equals(RGB that) {
		if (this.toString().equals(that.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	public RGB complement() {
		return this.RGBToLab().complement().LabToRGB();
	}
	
	public String toString()
	{
		return "" + r + " " + g + " " + b + "";
	}
	
    public XYZ RGBToXYZ()
    {
        double red = inverseCompand((double)(this.r) / 255);
        double green = inverseCompand((double)(this.g) / 255);
        double blue = inverseCompand((double)(this.b) / 255);

        red = red * 100;
        green = green * 100;
        blue = blue * 100;

        double x = (.4124564 * red) + (.3575761 * green) + (.1804375 * blue);
        double y = (.2126729 * red) + (.7151522 * green) + (.0721750 * blue);
        double z = (.0193339 * red) + (.1191920 * green) + (.9503041 * blue);

        return new XYZ(x, y, z);
    }
    
    public Lab RGBToLab()
    {
    	return RGBToXYZ().XYZToLab();
    }
    
    public HEX RGBToHEX()
    {
    	String red = Integer.toHexString(r);
    	String green = Integer.toHexString(g);
    	String blue = Integer.toHexString(b);
    	if(red.length() < 2) {
    		red = "0" + red;
    	}
    	if(green.length() < 2) {
    		green = "0" + green;
    	}
    	if(blue.length() < 2) {
    		blue = "0" + blue;
    	}
    	return new HEX(red + green + blue);
    }
    
    public double compareTo(RGB that)
    {
    	return RGBToLab().compareTo(that.RGBToLab());
    }
	
    private double inverseCompand(double val)
    {
        /*if (val > 0.4045)
        {
            return Math.Pow((val + .055) / 1.055, 2.4);
        }
        else
        {
            return val / 12.92;
        }*/
        return (val <= .04045) ? (val / 12.92) : Math.pow((val + .055) / 1.055, 2.4);
    }
    
    public int rToInt()
    {
    	return r;
    }
    
    public int gToInt()
    {
    	return g;
    }
    
    public int bToInt()
    {
    	return b;
    }
}
