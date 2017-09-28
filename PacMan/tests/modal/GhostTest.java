package modal;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import modal.Map;
import modal.MovementLogic;
	
public class GhostTest {

	private static MovementLogic ml;
	private static Map map;
	private static Player player;
	private static Point gSize;
	private static int blSize;
	private static Ghost gh;
	
	@BeforeClass
	public static void start() {
		System.out.println("before test");
		gSize = new Point(720, 480);
		String[] strings = {"Dot", "LargeDot", "Wall", "Empty","PlayerSpawn", "GhostHouse"  };
		blSize = 40;
		map = new Map(strings, gSize, blSize);
		ml = new MovementLogic(gSize, blSize, map);
		player = new Player(ml, 0);
		gh = new Ghost(ml, gSize, blSize, player);
		
	}
	
	@Test
	public void testGhostMovement() {
		map.readMap("Level1-fixed.txt");
		
		Point SPoint = new Point(640, 200);
		Point TPoint = new Point(640, 400);
		ArrayList<Point> test = gh.path(SPoint,TPoint);
		assertEquals(TPoint, test.get(test.size()-1));			
	}
	
	@Test
	public void testPathSize() {
		map.readMap("Level1-fixed.txt");
		
		Point SPoint = new Point(640, 200);
		Point TPoint = new Point(640, 400);
		ArrayList<Point> test = gh.path(SPoint,TPoint);
		assertEquals(6, test.size());
	}
	
	@Test
	public void testGhostMovementLong() {
		map.readMap("testMovementLong.txt");

		
		Point SPoint = new Point(40, 40);
		Point TPoint = new Point(640, 400);
		ArrayList<Point> test = gh.path(SPoint,TPoint);
		assertEquals(TPoint, test.get(test.size()-1));
	}
	
	@Test
	public void testPathSizeLong() {
		map.readMap("testMovementLong.txt");

		Point SPoint = new Point(40, 40);
		Point TPoint = new Point(640, 400);
		ArrayList<Point> test = gh.path(SPoint,TPoint);
		assertEquals(37, test.size());
	}
	
	@Test
	public void testGhostImpossibleMovement() {
		map.readMap("impossbleMove.txt");

		Point SPoint = new Point(map.getGhostHouse());
		Point TPoint = new Point(640, 400);
		ArrayList<Point> test = gh.path(SPoint,TPoint);
		assertEquals(null, test);
	}
	

	
	
	
	
}
