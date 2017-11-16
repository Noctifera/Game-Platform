package application;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modal.Draw;
import modal.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	private Controller con;
	private Draw draw;
	private Map map;

	private Stage saveStage;
	private Stage smallPopUpStage;
	private Stage warningPopup;
	private Button newM;
	private Button ready;

	private ChoiceBox<String> types;
	private ChoiceBox<String> lang;
	private RadioButton button1;
	private RadioButton button2;
	private final int width = 720;
	private final int height = 480;
	private final int tileSize = 40;
	private String[] strings = { "Dot", "LargeDot", "Wall", "Empty", "PlayerSpawn", "GhostHouse" };
	private final String[] languages = { "English", "Se Sotho sa Sotho", "Afrikaans", "Zulu", "IsiXhosa" };

	private final Locale[] locale = { new Locale("en", "RSA"), new Locale("st", "RSA"), new Locale("af", "RSA"), new Locale("zu", "RSA"), new Locale("xh", "RSA") };

	public void init() {
		map = new Map(width, height, tileSize, strings);
		draw = new Draw(width, height, tileSize, map, strings);
		con = new Controller(this, draw, map, strings, tileSize);
	}

	public Stage SavePopUp() {
		saveStage = new Stage();

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		String s = con.mapContains();
		if (!s.equals("")) {
			Label check = new Label();
			check.setText(s);
			gp.add(check, 1, 1, 2, 1);
		}

		ToggleGroup group = createToggleGroup();
		RadioButton button1 = (RadioButton) group.getToggles().get(0);
		RadioButton button2 = (RadioButton) group.getToggles().get(1);

		gp.add(button1, 1, 2);
		gp.add(button2, 2, 2);
		group.getSelectedToggle();

		Label label = new Label();
		label.setText("Map Name: ");
		gp.add(label, 1, 3);

		TextField fileName = new TextField();
		gp.add(fileName, 2, 3);

		Button save = new Button("Save Map");
		gp.add(save, 2, 4);
		
		Label warningLabel = new Label();
		warningLabel.setText("Test");

		if (!s.equals("")) {
			button1.setDisable(true);
			button2.setDisable(true);
			fileName.setDisable(true);
			save.setDisable(true);
		}

		con.handePopUp(save, group, fileName);

		Scene scene = new Scene(gp);
		saveStage.setScene(scene);
		saveStage.setResizable(false);
		return saveStage;
	}

	public void SavePopUpClose() {
		saveStage.close();
	}
	
	public Stage warningPopup() {
		GridPane gp = new GridPane();
		Label label = new Label("Your progress will not be saved! \n Press Ok to continue.");
		Button OkButton = new Button("Ok");
		Button cancelButton = new Button("Cancel");
		
		
		gp.add(label, 1, 1);
		gp.add(OkButton, 1, 2);
		gp.add(cancelButton, 2, 2);
			
		warningPopup = new Stage();
		Scene scene = new Scene(gp);
		warningPopup.setScene(scene);
		
		con.warningHandle(OkButton, cancelButton);
		
		warningPopup.show();
		
		return warningPopup;
		}
	
	public void warningPopupClose() {
		warningPopup.close();
	}

	public Stage smallPopup(boolean save) {
		GridPane gp = new GridPane();
		Label label = new Label();
		gp.add(label, 1, 1, 2, 1);
		if (save) {
			label.setText("Map Saved");
		} else {
			label.setText("Map could not be saved");
		}
		Button button = new Button("OK");
		gp.add(button, 2, 2);

		smallPopUpStage = new Stage();
		Scene scene = new Scene(gp);
		smallPopUpStage.setScene(scene);

		con.smallHandle(button);
		return smallPopUpStage;

	}

	public void smallPopupClose() {
		smallPopUpStage.close();
	}

	@Override
	public void start(Stage primaryStage) {

		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);

		Scene scene = new Scene(root, width + 400, height + 20);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Map Creator");
		Image image = new Image("file:maxresdefault.jpg");
		primaryStage.getIcons().add(image);

		con.beginning();
		root.add(draw, 1, 1);
		root.add(rightVerticalBox(), 2, 1);

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);

			}
		});

	}

	private ToggleGroup createToggleGroup() {
		ToggleGroup group = new ToggleGroup();
		button1 = new RadioButton("private (File)");
		button1.setToggleGroup(group);
		button1.setId("private");
		button1.setSelected(true);
		button2 = new RadioButton("public (Database)");
		button2.setToggleGroup(group);
		button2.setId("public");
		return group;
	}

	private VBox rightVerticalBox() {
		GridPane gd = new GridPane();
		GridPane top = new GridPane();
		GridPane bottom = new GridPane();
		//gd.setGridLinesVisible(true);
		gd.setHgap(10);
		gd.setVgap(5);
		
		top.setHgap(10);
		top.setVgap(5);
		
		bottom.setHgap(10);

		types = new ChoiceBox<>();
		types.setItems(FXCollections.observableArrayList(strings));

		types.setValue(strings[0]);
		top.add(types, 0, 0, 1, 1);

		newM = new Button("Clear Map");
		top.add(newM, 1, 0, 1, 1);

		ToggleGroup tg = createToggleGroup();
		button1 = (RadioButton) tg.getToggles().get(0);
		button2 = (RadioButton) tg.getToggles().get(1);

		top.add(button1, 0, 1, 1, 1);
		top.add(button2, 1, 1, 1, 1);

		ListView<String> files = new ListView<>();

		files.setItems(FXCollections.observableArrayList(con.readFiles()));

		

		ready = new Button("Map Ready to be Saved");
		bottom.add(ready, 0, 3, 1, 1);

		lang = new ChoiceBox<>();
		lang.setItems(FXCollections.observableArrayList(languages));
		lang.setValue(languages[0]);
		bottom.add(lang, 1, 3, 1, 1);
		
		gd.add(top, 0, 0);
		gd.add(files, 0, 1,2,1);
		gd.add(bottom, 0, 2);

		VBox vb = new VBox(gd);

		con.handle(ready, newM, types, files, tg, button1, button2, lang);

		return vb;

	}

	public void lang(int currentIndex) {
		// System.out.println(currentIndex);
		Locale current = locale[currentIndex];
		// System.out.println(current.toString());
		ResourceBundle rb = ResourceBundle.getBundle("locales/MessagesBundle", current);

		String[] typeList = { rb.getString("TileTypeDot"), rb.getString("TileTypeLargeDot"), rb.getString("TileTypeWall"), rb.getString("TileTypeEmpty"), rb.getString("TileTypePlayerSpawn"),
				rb.getString("TileTypeGhostHouse") };
		for (String s : typeList) {
			System.out.println(s);
		}
		strings = typeList;
		List<String> s = FXCollections.observableArrayList(strings);
		System.out.println(s);
		// types.setItems(FXCollections.observableArrayList(strings));
		types.setValue(typeList[0]);

		newM.setText(rb.getString("ClearMapButton"));

		button1.setText(rb.getString("RadioMapTypePrivate"));
		button2.setText(rb.getString("RadioMapTypePublic"));

		ready.setText(rb.getString("MapReadyToSaveButton"));

	}

	public static void main(String[] args) {
		launch(args);
	}
}
