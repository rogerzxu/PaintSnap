package colorLibrary;

public class XYZ {
	
	public double x;
	public double y;
	public double z;
    private double e = 216 / 24389;
    private double k = 24389 / 27;
	
	XYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

    public XYZ complement() {
    	return this.XYZToLab().complement().LabToXYZ();
    }
	
    public RGB XYZToRGB()
    {
        double temp_x = this.x / 100;
        double temp_y = this.y / 100;
        double temp_z = this.z / 100;
        double red = (3.2404542 * temp_x) - (1.5371385 * temp_y) - (.4985314 * temp_z);
        double green = (-.9692660 * temp_x) + (1.8760108 * temp_y) + (.0415560 * temp_z);
        double blue = (.0556434 * temp_x) - (.2040259 * temp_y) + (1.0572252 * temp_z);

        return new RGB((int)Math.round(compand(red) * 255), (int)Math.round(compand(green) * 255), (int)Math.round(compand(blue) * 255));
    }
    
    public Lab XYZToLab()
    {
        double L = (116 * getf(this.y / 100)) - 16;
        double a = 500 * (getf(this.x / 95.047) - getf(this.y / 100));
        double b = 200 * (getf(this.y / 100) - getf(this.z / 108.883));

        return new Lab(L, a, b);
    }
    
    public HEX XYZToHEX()
    {
    	return XYZToRGB().RGBToHEX();
    }
    
    public double compareTo(XYZ that)
    {
    	return XYZToLab().compareTo(that.XYZToLab());
    }
    
    private double compand(double val)
    {
        return ((val <= .0031308) ? (val * 12.92) : (val < 0) ? 0 : ((1.055 * Math.pow(val, (1 / 2.4))) - .055));
    }

    private double getf(double val)
    {
        return ((val > e) ? Math.pow(val, (1.0 / 3.0)) : (k * val + 16 / 116));
    }
}
