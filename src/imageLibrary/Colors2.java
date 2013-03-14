package imageLibrary;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Bitmap;
import android.graphics.Color;

import colorLibrary.Lab;
import colorLibrary.RGB;
import adjacencyMatrix.*;

public class Colors2 {

	public ArrayList<RGB> getColorsFromImage(Bitmap image) {
		return shrinkColors(populateGraph(getColors(image)));
	}

	public ArrayList<RGB> getColors(Bitmap image) {
		ArrayList<RGB> allColors = new ArrayList<RGB>();
		Bitmap smallImage = Bitmap.createScaledBitmap(image, 16, 12, true);
		//int thresh = 12;
		for (int i = 0; i < smallImage.getWidth(); i++) {
			for (int j = 0; j < smallImage.getHeight(); j++) {
				int clr = smallImage.getPixel(i, j);
				int r = Color.red(clr);
				int g = Color.green(clr);
				int b = Color.blue(clr);
				//System.out.println(""+r+" "+g+" "+b);
				boolean grey = false;
				/**if (((r < (b + thresh)) && (r > (b - thresh))) && ((r < (g + thresh)) && (r > (g - thresh))) && ((b < (g + thresh)) && (b > (g - thresh)))) {
					grey = true;
				}**/
				if(!grey){
					RGB tempClr = new RGB(r, g, b);
					allColors.add(tempClr);
				}
			}
		}
		return allColors;
	}

	public Graph populateGraph(ArrayList<RGB> big) {
		Graph g = new Graph();
		for(int i = 0 ; i < big.size() ; i++){
			RGB temp = big.get(i);
			Node tempNode = new Node(temp);
			g.addNode(tempNode);
			if (temp != null) {
				for(int j = 0 ; j < g.size(); j++){
					Node thatNode = g.nodes[j];
					RGB that = (thatNode != null) ? thatNode.rgb : null;
					if (that != null && that.compareTo(temp) < 21)// do these RGB values need an Edge?
					{
						g.addEdge(i,j);
					}
				}
			}
		}
		return g;
	}

	public ArrayList<RGB> shrinkColors(Graph g) {
		ArrayList<RGB> mostConnected = new ArrayList<RGB>();

		for (int x = 0; x < 5; x++) {
			Node popular = null;
			int friends = 1;
			int index = -1;
			for(int i = 0 ; i < g.nodes.length; i ++){
				Node check = g.nodes[i];
				if(check != null && check.alive){
					int j = check.degree;
					if (friends < j) {
						popular = check;
						friends = j;
						index = i;
					}
				}
			}
			if(popular != null){
				RGB average = averageRGB(g.getConnected(index));
				//mostConnected.add(popular.rgb);
				mostConnected.add(average);
				g.removeNode(index);
			}
		}
		return mostConnected;
	}
	
	public RGB averageRGB(ArrayList<RGB> colors) {
		Iterator<RGB> iter = colors.iterator();
		int count = 0;
		double totalL = 0;
		double totala = 0;
		double totalb = 0;
		while(iter.hasNext()) {
			RGB tempRGB = iter.next();
			Lab tempLab = tempRGB.RGBToLab();
			totalL += tempLab.L;
			totala += tempLab.a;
			totalb += tempLab.b;
			count++;
		}
		Lab finalLab = new Lab(totalL/count, totala/count, totalb/count);
		return finalLab.LabToRGB();
	}
}
