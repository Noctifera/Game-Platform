package modal;

import java.awt.Point;
import java.util.ArrayList;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerThread extends Thread {
	private Player player;
	private Controller con;

	private Scene scene;

	private ArrayList<Point> path = new ArrayList<>();

	private volatile boolean supress = false;
	private int reader = 0;
	private int wait = 0;

	public PlayerThread(Player player, Controller con, Scene scene) {
		this.player = player;
		this.scene = scene;
		this.con = con;
	}

	public void run() {
		player.setPos(player.playerSpawn());
		handle();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader = 0;
		while (!supress) {
			if (reader < path.size()) {
				player.setPos(path.get(reader));
				player.score(path.get(reader));
				reader++;
				con.setLives();
				con.setScore();
				try {
					Thread.sleep(450);
					if (wait == 1)
						saty();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public void supress() {
		supress = true;
	}

	public void saty() throws InterruptedException {
		Thread.sleep(500);
		wait = 0;
	}

	public void handle() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// liikkuminen
				KeyCode code = event.getCode();
				path = player.move(code);
				reader = 0;
			}
		});
	}

	public void retrunTospawn() {
		reader = 0;
		path.clear();
		player.setPos(player.playerSpawn());
		wait = 1;

	}

}
