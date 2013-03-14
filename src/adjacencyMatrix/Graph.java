package adjacencyMatrix;

import java.util.ArrayList;

import colorLibrary.RGB;

public class Graph{

	public Node[] nodes;
	public int lastIndex;
	public boolean[][] edges;
	private int size;

	public Graph(){
		nodes = new Node[192];
		lastIndex = 0;
		edges = new boolean[192][192];
		size = 0;
	}

	public void addNode(Node n){
		//System.out.println("------------------addnode");
		nodes[lastIndex] = n;
		lastIndex++;
		size++;
	}

	public void addEdge(int i1, int i2){
		//System.out.println("------------------addedge");
		edges[i1][i2] = true;
		edges[i2][i1] = true;
		nodes[i1].degree++;
		nodes[i2].degree++;
	}

	public void removeNode(int index){
		//System.out.println("------------------removenode");
		nodes[index].alive = false;
		size --;
		int[] connected = new int[192];
		int cIndex = 0;
		for(int i = 0 ; i < edges[index].length; i++){
			if(edges[index][i]){
				connected[cIndex] = i;
				cIndex++;
				edges[index][i] = false;
				edges[i][index] = false;
				nodes[i].alive = false;
				size--;
			}
		}
		for(int i = 0 ; i < connected.length; i ++){
			int temp = connected[i];
			if(temp == 0 && i > 0){
				break;
			}
			for(int j = 0 ; j < edges[temp].length; j++){
				if(edges[temp][j]){
					edges[temp][j] = false;
					edges[j][temp] = false;
					nodes[j].degree--;
				}
			}
		}
	}
	
	public ArrayList<RGB> getConnected(int index) {
		ArrayList<RGB> colors = new ArrayList<RGB>();
		for(int i = 0; i < edges[index].length; i++) {
			if(edges[index][i]) {
				colors.add(nodes[i].rgb);
			}
		}
		return colors;
	}
	
	public int size(){
		return size;
	}

}