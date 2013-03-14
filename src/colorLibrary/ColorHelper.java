package colorLibrary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ColorHelper{
	
	private Map<String, Lab> familyMap = new HashMap<String, Lab>();
	private Map<String, HEX> subFamilyMap = new HashMap<String, HEX>();
	
	public ColorHelper(){
		familyMap.put("DarkSkin", new Lab(37.773194, 12.591372, 13.737671));
        familyMap.put("LightSkin", new Lab(65.431902, 16.847301, 17.382375));
        familyMap.put("BlueSky", new Lab(50.221729, -2.300949, -21.549151));
        familyMap.put("Foliage", new Lab(43.041192, -14.943426, 22.050773));
        familyMap.put("BlueFlower", new Lab(55.365421, 11.462945, -25.157673));
        familyMap.put("BluishGreen", new Lab(70.913577, -33.081077, 0.267526));
        familyMap.put("Orange", new Lab(62.036338, 33.607695, 56.361011));
        familyMap.put("PurplishBlue", new Lab(40.660847, 16.044043, -45.139595));
        familyMap.put("ModerateRed", new Lab(50.619702, 47.698344, 15.195276));
        familyMap.put("Purple", new Lab(30.442918, 24.88989, -21.589918));
        familyMap.put("YellowGreen", new Lab(72.285461, -27.944917, 57.869844));
        familyMap.put("OrangeYellow", new Lab(71.386101, 15.890408, 67.707828));
        familyMap.put("Blue", new Lab(29.574016, 20.596891, -49.210196));
        familyMap.put("Green", new Lab(55.232852, -41.208618, 31.934147));
        familyMap.put("Red", new Lab(41.46805, 52.749746, 26.868485));
        familyMap.put("Yellow", new Lab(81.222088, -0.439129, 80.265793));
        familyMap.put("Magenta", new Lab(51.726475, 51.213992, -15.239271));
        familyMap.put("Cyan", new Lab(51.562878, -23.96825, -27.845315));
        familyMap.put("White", new Lab(96.529813, -0.534197, 1.181468));
        familyMap.put("Neutral8", new Lab(81.264387, -0.604031, -0.322163));
        familyMap.put("Neutral6", new Lab(66.775704, -0.683625, -0.488092));
        familyMap.put("Neutral5", new Lab(50.870676, -0.126949, -0.265144));
        familyMap.put("Neutral3", new Lab(35.671194, -0.301919, -1.212649));
        familyMap.put("Black", new Lab(20.471425, 0.014011, -0.962288));
        
        subFamilyMap.put("Aliceblue", new HEX("#F0F8FF"));
        subFamilyMap.put("AntiqueWhite", new HEX("#FAEBD7"));
        //subFamilyMap.put("Aqua", new HEX("#00FFFF"));
        subFamilyMap.put("Aquamarine", new HEX("#7FFFD4"));
        subFamilyMap.put("Azure", new HEX("#F0FFFF"));
        subFamilyMap.put("Beige", new HEX("#F5F5DC"));
        subFamilyMap.put("Bisque", new HEX("#FFE4C4"));
        subFamilyMap.put("Black", new HEX("#000000"));
        subFamilyMap.put("BlanchedAlmond", new HEX("#FFEBCD"));
        subFamilyMap.put("Blue", new HEX("#0000FF"));
        subFamilyMap.put("BlueViolet", new HEX("#8A2BE2"));
        subFamilyMap.put("Brown", new HEX("#A52A2A"));
        subFamilyMap.put("BurlyWood", new HEX("#DEB887"));
        subFamilyMap.put("CadetBlue", new HEX("#5F9EA0"));
        subFamilyMap.put("Chartreuse", new HEX("#7FFF00"));
        subFamilyMap.put("Chocolate", new HEX("#D2691E"));
        subFamilyMap.put("Coral", new HEX("#FF7F50"));
        subFamilyMap.put("CornflowerBlue", new HEX("#6495ED"));
        subFamilyMap.put("Cornsilk", new HEX("#FFF8DC"));
        subFamilyMap.put("Crimson", new HEX("#DC143C"));
        subFamilyMap.put("Cyan", new HEX("#00FFFF"));
        subFamilyMap.put("DarkBlue", new HEX("#00008B"));
        subFamilyMap.put("DarkCyan", new HEX("#008B8B"));
        subFamilyMap.put("DarkGoldenrod", new HEX("#B8860B"));
        subFamilyMap.put("DarkGray", new HEX("#A9A9A9"));
        subFamilyMap.put("DarkGreen", new HEX("#006400"));
        subFamilyMap.put("DarkKhaki", new HEX("#BDB76B"));
        subFamilyMap.put("DarkMagenta", new HEX("#8B008B"));
        subFamilyMap.put("DarkOliveGreen", new HEX("#556B2F"));
        subFamilyMap.put("DarkOrange", new HEX("#FF8C00"));
        subFamilyMap.put("DarkOrchid", new HEX("#9932CC"));
        subFamilyMap.put("DarkRed", new HEX("#8B0000"));
        subFamilyMap.put("DarkSalmon", new HEX("#E9967A"));
        subFamilyMap.put("DarkSeaGreen", new HEX("#8FBC8F"));
        subFamilyMap.put("DarkSlateBlue", new HEX("#483D8B"));
        subFamilyMap.put("DarkSlateGray", new HEX("#2F4F4F"));
        subFamilyMap.put("DarkTurquoise", new HEX("#00CED1"));
        subFamilyMap.put("DarkViolet", new HEX("#9400D3"));
        subFamilyMap.put("DeepPink", new HEX("#FF1493"));
        subFamilyMap.put("DeepSkyBlue", new HEX("#00BFFF"));
        subFamilyMap.put("DimGray", new HEX("#696969"));
        subFamilyMap.put("DodgerBlue", new HEX("#1E90FF"));
        subFamilyMap.put("Firebrick", new HEX("#B22222"));
        subFamilyMap.put("FloralWhite", new HEX("#FFFAF0"));
        subFamilyMap.put("ForestGreen", new HEX("#228B22"));
        subFamilyMap.put("Fuchsia", new HEX("#FF00FF"));
        subFamilyMap.put("Gainsboro", new HEX("#DCDCDC"));
        subFamilyMap.put("GhostWhite", new HEX("#F8F8FF"));
        subFamilyMap.put("Gold", new HEX("#FFD700"));
        subFamilyMap.put("Goldenrod", new HEX("#DAA520"));
        subFamilyMap.put("Gray", new HEX("#808080"));
        subFamilyMap.put("Green", new HEX("#008000"));
        subFamilyMap.put("GreenYellow", new HEX("#ADFF2F"));
        subFamilyMap.put("Honeydew", new HEX("#F0FFF0"));
        subFamilyMap.put("HotPink", new HEX("#FF69B4"));
        subFamilyMap.put("IndianRed", new HEX("#CD5C5C"));
        subFamilyMap.put("Indigo", new HEX("#4B0082"));
        subFamilyMap.put("Ivory", new HEX("#FFFFF0"));
        subFamilyMap.put("Khaki", new HEX("#F0E68C"));
        subFamilyMap.put("Lavender", new HEX("#E6E6FA"));
        subFamilyMap.put("LavenderBlush", new HEX("#FFF0F5"));
        subFamilyMap.put("LawnGreen", new HEX("#7CFC00"));
        subFamilyMap.put("LemonChiffon", new HEX("#FFFACD"));
        subFamilyMap.put("LightBlue", new HEX("#ADD8E6"));
        subFamilyMap.put("LightCoral", new HEX("#F08080"));
        subFamilyMap.put("LightCyan", new HEX("#E0FFFF"));
        subFamilyMap.put("LightGoldenrodYellow", new HEX("#FAFAD2"));
        subFamilyMap.put("LightGray", new HEX("#D3D3D3"));
        subFamilyMap.put("LightGreen", new HEX("#90EE90"));
        subFamilyMap.put("LightPink", new HEX("#FFB6C1"));
        subFamilyMap.put("LightSalmon", new HEX("#FFA07A"));
        subFamilyMap.put("LightSeaGreen", new HEX("#20B2AA"));
        subFamilyMap.put("LightSkyBlue", new HEX("#87CEFA"));
        subFamilyMap.put("LightSlateGray", new HEX("#778899"));
        subFamilyMap.put("LightSteelBlue", new HEX("#B0C4DE"));
        subFamilyMap.put("LightYellow", new HEX("#FFFFE0"));
        subFamilyMap.put("Lime", new HEX("#00FF00"));
        subFamilyMap.put("LimeGreen", new HEX("#32CD32"));
        subFamilyMap.put("Linen", new HEX("#FAF0E6"));
        subFamilyMap.put("Magenta", new HEX("#FF00FF"));
        subFamilyMap.put("Maroon", new HEX("#800000"));
        subFamilyMap.put("MediumAquamarine", new HEX("#66CDAA"));
        subFamilyMap.put("MediumBlue", new HEX("#0000CD"));
        subFamilyMap.put("MediumOrchid", new HEX("#BA55D3"));
        subFamilyMap.put("MediumPurple", new HEX("#9370DB"));
        subFamilyMap.put("MediumSeaGreen", new HEX("#3CB371"));
        subFamilyMap.put("MediumSlateBlue", new HEX("#7B68EE"));
        subFamilyMap.put("MediumSpringGreen", new HEX("#00FA9A"));
        subFamilyMap.put("MediumTurquoise", new HEX("#48D1CC"));
        subFamilyMap.put("MediumVioletRed", new HEX("#C71585"));
        subFamilyMap.put("MidnightBlue", new HEX("#191970"));
        subFamilyMap.put("MintCream", new HEX("#F5FFFA"));
        subFamilyMap.put("MistyRose", new HEX("#FFE4E1"));
        subFamilyMap.put("Moccasin", new HEX("#FFE4B5"));
        subFamilyMap.put("NavajoWhite", new HEX("#FFDEAD"));
        subFamilyMap.put("Navy", new HEX("#000080"));
        subFamilyMap.put("OldLace", new HEX("#FDF5E6"));
        subFamilyMap.put("Olive", new HEX("#808000"));
        subFamilyMap.put("OliveDrab", new HEX("#6B8E23"));
        subFamilyMap.put("Orange", new HEX("#FFA500"));
        subFamilyMap.put("OrangeRed", new HEX("#FF4500"));
        subFamilyMap.put("Orchid", new HEX("#DA70D6"));
        subFamilyMap.put("PaleGoldenrod", new HEX("#EEE8AA"));
        subFamilyMap.put("PaleGreen", new HEX("#98FB98"));
        subFamilyMap.put("PaleTurquoise", new HEX("#AFEEEE"));
        subFamilyMap.put("PaleVioletRed", new HEX("#DB7093"));
        subFamilyMap.put("PapayaWhip", new HEX("#FFEFD5"));
        subFamilyMap.put("PeachPuff", new HEX("#FFDAB9"));
        subFamilyMap.put("Peru", new HEX("#CD853F"));
        subFamilyMap.put("Pink", new HEX("#FFC0CB"));
        subFamilyMap.put("Plum", new HEX("#DDA0DD"));
        subFamilyMap.put("PowderBlue", new HEX("#B0E0E6"));
        subFamilyMap.put("Purple", new HEX("#800080"));
        subFamilyMap.put("Red", new HEX("#FF0000"));
        subFamilyMap.put("RosyBrown", new HEX("#BC8F8F"));
        subFamilyMap.put("RoyalBlue", new HEX("#4169E1"));
        subFamilyMap.put("SaddleBrown", new HEX("#8B4513"));
        subFamilyMap.put("Salmon", new HEX("#FA8072"));
        subFamilyMap.put("SandyBrown", new HEX("#F4A460"));
        subFamilyMap.put("SeaGreen", new HEX("#2E8B57"));
        subFamilyMap.put("SeaShell", new HEX("#FFF5EE"));
        subFamilyMap.put("Sienna", new HEX("#A0522D"));
        subFamilyMap.put("Silver", new HEX("#C0C0C0"));
        subFamilyMap.put("SkyBlue", new HEX("#87CEEB"));
        subFamilyMap.put("SlateBlue", new HEX("#6A5ACD"));
        subFamilyMap.put("SlateGray", new HEX("#708090"));
        subFamilyMap.put("Snow", new HEX("#FFFAFA"));
        subFamilyMap.put("SpringGreen", new HEX("#00FF7F"));
        subFamilyMap.put("SteelBlue", new HEX("#4682B4"));
        subFamilyMap.put("Tan", new HEX("#D2B48C"));
        subFamilyMap.put("Teal", new HEX("#008080"));
        subFamilyMap.put("Thistle", new HEX("#D8BFD8"));
        subFamilyMap.put("Tomato", new HEX("#FF6347"));
        subFamilyMap.put("Turquoise", new HEX("#40E0D0"));
        subFamilyMap.put("Violet", new HEX("#EE82EE"));
        subFamilyMap.put("Wheat", new HEX("#F5DEB3"));
        subFamilyMap.put("White", new HEX("#FFFFFF"));
        subFamilyMap.put("WhiteSmoke", new HEX("#F5F5F5"));
        subFamilyMap.put("Yellow", new HEX("#FFFF00"));
        subFamilyMap.put("YellowGreen", new HEX("#9ACD32"));
	}
	
	public String getFamily(RGB rgb){
		Set set = familyMap.entrySet();
        Iterator it = set.iterator();
        double comparison = Double.MAX_VALUE;
        String name = "";
        
		while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            String family = (String)m.getKey();
            Lab lab = (Lab)m.getValue();

            Lab l = rgb.RGBToLab();
            double comp = lab.compareTo(l);
            if(comp < comparison){
            	name = family;
            	comparison = comp;
            }
        }
		return name;
	}
	
	public String getSubFamily(RGB rgb){
		Set set = subFamilyMap.entrySet();
        Iterator it = set.iterator();
        double comparison = Double.MAX_VALUE;
        String name = "";
        
		while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            String subfamily = (String)m.getKey();
            HEX hex = (HEX)m.getValue();

            HEX h = rgb.RGBToHEX();
            double comp = hex.compareTo(h);
            if(comp < comparison){
            	name = subfamily;
            	comparison = comp;
            }
        }
		return name;
	}
}