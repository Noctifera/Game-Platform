 package application;

import java.awt.Point;

import controller.*;
import modal.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;

public class Main extends Application implements Main_IF {

	private Controller con;
	private Draw draw;
	private Map map;
	private DrawThread pms;
	private Player player;
	private MovementLogic ml;

	private GraphicsContext gc1;
	private Point ppos = new Point(80, 80);
	private Point gpos = new Point(1000,700);
	private final int blSize = 40;
	private final Point gSize = new Point(1280, 720);
	private BorderPane root;
	private String[] strings = {"Dot", "LargeDot", "Wall", "Empty" };
	private int ghostAmount = 4;
	private Ghost[] ghlist = new Ghost[ghostAmount];
	private GhostThread[] ghtlist = new GhostThread[ghostAmount];

	private Scene scene;

	public void init() {
		map = new Map();
		ml = new  MovementLogic(gSize, blSize,map,strings);
		player = new Player(ppos,ml,map,blSize,strings);

		draw = new Draw((int) gSize.getX(), (int) gSize.getY(), gc1, blSize, player,map,ghlist,strings);
		pms = new DrawThread(draw);
		con = new Controller(player,map);

	}

	public void start(Stage primaryStage) {
		root = new BorderPane();


		scene = new Scene(root, (int) gSize.getX(), (int) gSize.getY());

		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
		});
		CanvasSetuUp();
		handle();
		con.start();
		pms.start();
		inisGhost();



	}
	public void inisGhost(){
		for(int i = 0; i<ghtlist.length; i++){
			ghlist[i] = new Ghost(gpos,ml,gSize,blSize,player);
			ghtlist[i] = new GhostThread(ghlist[i]);

		}
		for (GhostThread ghost : ghtlist) {
			ghost.start();
		}
	}

	public void handle() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				// liikkuminen
				con.move(event);
			}
		});
	}
	public void CanvasSetuUp(){
		root.getChildren().add(draw);

	}
	public static void main(String[] args) {
		launch(args);
	}

}
