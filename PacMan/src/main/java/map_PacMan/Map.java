package map_PacMan;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


/**
 * Implements {@link Map_IF}, class Map creates the PacMan maze.
 * @author kari-antti
 *
 */

public class Map implements Map_IF {
	private HashMap<Point, String> map = new HashMap<>();
	private String[] strings;
	private Point gSize;
	private int tileSize;
	

	private ArrayList<Point> dots = new ArrayList<>();
	private ArrayList<Point> largedots = new ArrayList<>();

	public Map(String[] strings, Point gSize, int tileSize) {
		this.strings = strings;
		this.gSize = gSize;
		this.tileSize = tileSize;
	}

	public HashMap<Point, String> getMap() {
		return map;
	}

	public Point getPlayerSpawn() {
		Point spawn = null;

		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).contains(strings[4])) {
				spawn = e.getKey();
			}
		}
		return spawn;

	}

	public Point getGhostHouse() {
		Point house = null;
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).contains(strings[5])) {
				house = e.getKey();
			}

		}
		return house;
	}

	public ArrayList<Point> getWalls() {
		ArrayList<Point> walls = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[2])) {
				walls.add(e.getKey());
			}
		}

		return walls;
	}

	public ArrayList<Point> freeSpaces() {
		ArrayList<Point> points = new ArrayList<>();
		for (int i = 0; i < gSize.getY();) {
			for (int j = 0; j < gSize.getX();) {
				if (!map.get(new Point(j, i)).equals(strings[2]) && !map.get(new Point(j, i)).contains(strings[5])) {
					points.add(new Point(j, i));
				}
				j = j + tileSize;
			}
			i = i + tileSize;
		}

		return points;
	}

	public void setDots() {
		ArrayList<Point> points = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[0])) {
				points.add(e.getKey());
			}
		}
		dots = points;
	}

	public ArrayList<Point> getDots() {
		return dots;
	}

	public void setLargeDots() {
		ArrayList<Point> points = new ArrayList<>();
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[1])) {
				points.add(e.getKey());
			}
		}
		largedots = points;
	}

	public ArrayList<Point> getLargeDots() {
		return largedots;
	}
	
	public void setMap(HashMap<Point, String> map) {
		this.map = map;
		setDots();
		
		setLargeDots();
		System.out.println("dots: "+dots.size());
		System.out.println("largedots: "+largedots.size() );
	}

}
