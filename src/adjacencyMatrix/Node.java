package adjacencyMatrix;
import colorLibrary.*;

public class Node{
	
	public RGB rgb;
	public int degree;
	public boolean alive;
	
	public Node(RGB rgb){
		this.rgb = rgb;
		degree = 0;
		alive = true;
	}
	
	public boolean equals(Node n){
		return rgb.equals(n.rgb);
	}
	
}