package modal;

import java.awt.Point;

import javafx.scene.input.KeyEvent;

public class Player extends Score {
	private Point pos;
	private MovementLogic ml;
	private int blSize;
	private String[] strings;
	private int life;

	public Player(MovementLogic ml, int blSize,String[] strings, int life) {
		super(0);
		this.ml = ml;
		this.blSize = blSize;
		this.strings = strings;
		this.life = life;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public void move(KeyEvent event) {
		// TODO Auto-generated method stub

		Point newpos = pos;

		switch (event.getCode()) {
		case W:
			// miinus laatikon korkeus
			double up = pos.getY() - blSize;
			newpos = new Point((int) pos.getX(), (int) up);
			//System.out.println(newpos);

			newpos = ml.yli(newpos);
			//System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case S:
			// plus laatikon korkeus
			double down = pos.getY() + blSize;
			newpos = new Point((int) pos.getX(), (int) down);
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case A:
			// miinus laatikon leveys
			double left = pos.getX() - blSize;
			newpos = new Point((int) left, (int) pos.getY());
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case D:
			// plus laatikon leveys
			double right = pos.getX() + blSize;
			newpos = new Point((int) right, (int) pos.getY());
			newpos = ml.yli(newpos);
			// System.out.println(newpos);
			if (ml.avoidWall(newpos)) {
				pos = newpos;
				score(newpos);
			}
			break;
		case ESCAPE:
			System.exit(0);
		default:
			System.out.println("use WASD keys");
			newpos = null;
			break;
		}
		// pm.move();

	}
	public void score(Point pos){
		if(ml.score(pos).equals(strings[0])){
			dot();

		}else if(ml.score(pos).equals(strings[1])){
			LargeDot();
		}
		System.out.println(score);
	}
	public void getEaten(){
		life--;
	}

	public int getLife() {
		return life;
	}
}
