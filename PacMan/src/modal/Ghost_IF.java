package modal;

import java.awt.Point;
import java.util.ArrayList;

public interface Ghost_IF {

	public Point randomPoint(); //palauttaa random pisteen johon on mahdollista siirty�
	public ArrayList<Point> insPath(); // palauttaa polun jota liikutaan


}
