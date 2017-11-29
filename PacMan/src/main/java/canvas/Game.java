package canvas;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;

import com.google.common.base.Strings;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import map.Map;

/**
 * Class extends Canvas and allows items to be drawn onto it
 * 
 * @author markus
 * @version 1.0
 *
 */
public class Game extends Canvas implements Draw_IF {

	private Map map;

	private String[] strings;
	private GraphicsContext gc;
	private Point gSize;

	private int tileSize;

	private Image blinky;
	private Image bashful;
	private Image speedy;
	private Image pokey;
	private Image player;
	private Image vulnerable;

	public Game(int tileSize, Map map, Point gSize, String[] strings) {
		super(gSize.x, gSize.y);
		this.tileSize = tileSize;
		this.gSize = gSize;
		this.gc = this.getGraphicsContext2D();
		this.map = map;
		this.strings = strings;
		blinky = getImage("/Pictures/Pacman-red-blinky.png");
		speedy = getImage("/Pictures/Pacman-pink-speedy.png");
		bashful = getImage("/Pictures/Pacman-inky-bashful.png");
		pokey = getImage("/Pictures/Pacman-orange-pokey.png");
		player = getImage("/Pictures/Pacman-pacman-player.png");
		vulnerable = getImage("/Pictures/Pacman-blue-vulnerable.png");
	}

	private Image getImage(String s) {
		File f = new File(getClass().getResource(s).getFile());
		Image image = new Image(f.toURI().toString());
		return image;
	}

	/**
	 * One function that combines all drawn
	 * 
	 * @return
	 */
	public void update() {
		clear();
		draw();
	}

	/**
	 * Gets Players position and draws an orange circle
	 */

	private void drawplayer(Point point) {
		gc.drawImage(player, point.x, point.y, tileSize, tileSize);
	}

	/**
	 * draws a black square the size of the canvas
	 */

	private void clear() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Draws a wall to the given point
	 * 
	 * @param point
	 *            The Point where The wall is Drawn
	 */
	private void drawWall(Point point) {
		gc.setFill(Color.BLUE);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawDot(Point point) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 4;
		gc.fillOval(point.x + block + block / 2, point.y + block + block / 2, block, block);
	}

	private void drawLargeDot(Point point) {
		gc.setFill(Color.WHITE);
		int block = tileSize / 2;
		gc.fillOval(point.x + block / 2, point.y + block / 2, block, block);
	}

	private void drawPlayerSpawn(Point point) {
		gc.setFill(Color.GREEN);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawGhostHouse(Point point) {
		gc.setFill(Color.RED);
		gc.fillRect(point.x, point.y, tileSize - 2, tileSize - 2);
	}

	private void drawGhost(Image image, Point point) {
		gc.drawImage(image, point.x, point.y, tileSize, tileSize);
	}

	private void draw() {
		for (int y = 0; y < gSize.y;) {
			for (int x = 0; x < gSize.x;) {
				Point point = new Point(x, y);
				String item = map.getMap().get(point);
				
				if (item.equals(strings[0])) {
					drawDot(point);
				} else if (item.equals(strings[1])) {
					drawLargeDot(point);
				} else if (item.equals(strings[2])) {
					drawWall(point);
				} else if (item.equals(strings[4])) {
					drawPlayerSpawn(point);
				} else if (item.equals(strings[5])) {
					drawGhostHouse(point);
				} else if (item.contains(strings[6])) {
					drawplayer(point);
				} else if (item.contains(strings[8])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(blinky, point);
					}

				} else if (item.contains(strings[9])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(speedy, point);
					}

				} else if (item.contains(strings[10])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(bashful, point);
					}

				} else if (item.contains(strings[11])) {

					if (item.contains("true")) {
						drawGhost(vulnerable, point);
					} else if (item.contains("false")) {
						drawGhost(pokey, point);
					}

				}

				x = x + tileSize;
			}
			y = y + tileSize;
		}

	}
}
