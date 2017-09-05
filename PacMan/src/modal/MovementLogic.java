package modal;

import java.awt.Point;

public class MovementLogic implements MovementLogic_IF {
	private Point gSize;
	private Map map;
	private int blSize;
	private String[] strings;

	public MovementLogic(Point gSize, int blSize, Map map,String[] strings) {
		this.gSize = gSize;
		this.blSize = blSize;
		this.map = map;
		this.strings = strings;
	}

	public Point yli(Point newpos) {
		Point piste = newpos;
		if (newpos.getX() >= gSize.getX()) {
			piste.setLocation(0, newpos.getY());
		} // oikea reuna
		if (newpos.getX() < 0) {
			piste.setLocation(gSize.getX() - blSize, newpos.getY());
		} // vasen reuna
		if (newpos.getY() >= gSize.getY()) {
			piste.setLocation(newpos.getX(), 0);
		} // alareuna
		if (newpos.getY() < 0) {
			piste.setLocation(newpos.getX(), gSize.getY() - blSize);
		} // yläreuna
		return piste;
	}

	public boolean posibleMove(Point pos) {
		// TODO Auto-generated method stub
		boolean onko = false;
		if(map.getMap().get(pos)!= strings[2]){
			onko = true;
		}
		return onko;
	}



}
