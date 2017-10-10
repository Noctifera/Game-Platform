package application;

import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Main Classes interface
 * @author markus
 *
 */
public interface PacMan_gui_IF {
	
	/** 
	 * Gets all HighScores from DataBase and shows them
	 * @return HorizontalBox with highScores from DataBase
	 */
	public HBox bottomDataPane(); //tietokannan ulos tulo
	
	/**
	 * 
	 * @param grid
	 * @param list
	 * @param text
	 */
	public void listLooper(GridPane grid, ArrayList<String> list, String text); //bottomDataPane k�ytt�m� funktio
	
	/**
	 * 
	 * @return HorizontalBox with the lives and score
	 */
	public HBox topHorizonatalBox(); //elkit ja score 
	
	/**
	 *  combines all to one gridPane and added to the Scene
	 */
	public void combine(); // yhdistet��n kaikki n�kyviin
	
	/**
	 * Updates TopHorizontal boxes shown lives
	 * @param livs players lives
	 */
	public void setLives(int livs); // p�ivitet��n ekit
	
	/**
	 * updates TopHorizontal boxes shown score
	 * @param score players score
	 */
	public void setScore(int score); // p�ivitet��n pisteet
	
	/**
	 * Gives the player to add score to HighScore Database
	 * @return a small pop-up stage
	 */
	public Stage popUpGameOver();
	
	/**�
	 * 
	 *  shows popUPGameOver();
	 */
	public void gameOver();
	
	
	

}
