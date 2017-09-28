package modal;

import java.awt.Point;
import java.util.ArrayList;

public interface Ghost_IF {
	
	public ArrayList<Point> insPath(); // palauttaa polun jota liikutaan
	
	public ArrayList<Point> path(Point start, Point goal); // etsii kuljettavan reittin palautta sen inisPathille jos polkua ei ole palauttaa nullin
	
	public ArrayList<Node> neighbors(Node current, Point goal, Point start, ArrayList<Node> closedSet);// pathin kaytt�m� funktio joka kattoo mahdolliset viereiset nodet
	
	public ArrayList<Point> retrunPath(Node current); //pathin k�ytt�m� funktio joka palauttaa valmiin polun
	
	public int fScore(Point goal, Point point); //neighborsin k�ytt�m� funktio joka laskee matkaa

}
