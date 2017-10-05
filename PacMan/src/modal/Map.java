package modal;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Map implements Map_IF {
	private HashMap<Point, String> map = new HashMap<>();
	private File folder = new File("Maps");
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
			if (map.get(e.getKey()).equals(strings[4])) {
				spawn = e.getKey();
			}
		}
		return spawn;

	}

	public Point getGhostHouse() {
		Point house = null;
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[5])) {
				house = e.getKey();
			}

		}
		return house;
	}

	@SuppressWarnings("unchecked")
	public void readMap(String tiedostonNimi) {
		HashMap<Point, String> map1 = new HashMap<>();
		File file = new File(folder + "//" + tiedostonNimi);
		try {
			
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream dataIn = new ObjectInputStream(fileIn);
			map1 = (HashMap<Point, String>) dataIn.readObject();
			dataIn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.clear();
		map.putAll(map1);

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
				if (!map.get(new Point(j, i)).equals(strings[2])) {
					points.add(new Point(j, i));
				}
				j = j + tileSize;
			}
			i = i + tileSize;
		}

		return points;
	}

	public void setDots() {
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[0])) {
				dots.add(e.getKey());
			}
		}
	}

	public ArrayList<Point> getDots() {
		return dots;
	}

	public void setLargeDots() {
		for (Entry<Point, String> e : map.entrySet()) {
			if (map.get(e.getKey()).equals(strings[1])) {
				largedots.add(e.getKey());
			}
		}
	}

	public ArrayList<Point> getLargeDots() {
		return largedots;
	}

}
