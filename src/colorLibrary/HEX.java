package colorLibrary;

public class HEX {
	
	public String HEX;
	
	public HEX(String hex1) {
		HEX = hex1;
	}
	
    public RGB HEXToRGB()
    {
        String hex = (HEX).replace("#", "");
        int red = Integer.parseInt(hex.substring(0, 2), 16);
        int green = Integer.parseInt(hex.substring(2, 4), 16);
        int blue = Integer.parseInt(hex.substring(4, 6), 16);

        return new RGB(red, green, blue);
    }
    
    public HEX complement() {
    	return this.HEXToLab().complement().LabToHEX();
    }
    
    public XYZ HEXToXYZ()
    {
    	return HEXToRGB().RGBToXYZ();
    }
    
    public Lab HEXToLab()
    {
    	return HEXToRGB().RGBToLab();
    }
    
    public double compareTo(HEX hex)
    {
		return (HEXToLab()).compareTo(hex.HEXToLab());
    }
    
    public String toString()
    {
    	return HEX;
    }
}
