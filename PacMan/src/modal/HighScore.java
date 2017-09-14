package modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HighScore {

	/*
	 	java.util.Date date = new java.util.Date();

		java.text.SimpleDateFormat time = new java.text.SimpleDateFormat("yyyy-MM-dd");

		String currentTime = time.format(date);
	*/
	
	List<Integer> scores = new ArrayList<>();
	List<String> names = new ArrayList<>();
	List<String> dates = new ArrayList<>();


	public List<Integer> getScores() {
		return scores;
	}

	public List<String> getNames() {
		return names;
	}

	public List<String> getDates() {
		return dates;
	}

	public Connection getConnection() throws Exception {

		try{
		  String driver = "com.mysql.jdbc.Driver";
		  //database osoite
		  String connectionUrl = "jdbc:mysql://localhost:2280/highscores";
		  String username = "kariants";
		  String password = "pacman2017";
		  Class.forName(driver);

		  //luodaan yhteys
		  Connection conn = DriverManager.getConnection(connectionUrl, username, password);
		  System.out.println("Yhdisti");
		  //palautetaan yhteys
		  return conn;

		} catch(Exception e){
		System.out.println("Error: "+ e);
		}

		return null;

		}

	public void post(int score, String playername, String date) throws Exception {
		Connection conn = null;
		try {
		   conn = getConnection();

		  java.sql.PreparedStatement query = conn.prepareStatement("INSERT INTO pacmanHighscore (score, playername, submission_date) VALUES('"+ score + "', '"+ playername +"', '"+date+"')");
		  query.executeUpdate();
		} catch(Exception e) {
			System.out.println("Error: "+ e);
		}
		conn.close();
	}

	public void selectFromDatabase() throws Exception {
		Connection conn = null;
				try {
			conn = getConnection();

			java.sql.Statement select = conn.createStatement();
			String sql = "SELECT score, playername, submission_date FROM pacmanHighscore";
			ResultSet resultset = select.executeQuery(sql);

			while(resultset.next()){
		         int score  = resultset.getInt("score");
		         String name = resultset.getString("playername");
		         String date = resultset.getString("submission_date");
		         
		         //adding data to lists
		         scores.add(score);
		         names.add(name);
		         dates.add(date);


		         //Display values
		         System.out.print("Score: " + score);
		         System.out.print(", Name: " + name);
		         System.out.print(", Date: " + date);
		         System.out.println(" ");
		      }


		}catch(Exception e){
			System.out.println("Error: "+ e);
		}
		conn.close();
	}


}
