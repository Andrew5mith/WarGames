package application;
	
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	
	public Object[] settings;
	
	private int totalHumans;
	
	private Stage window;
	private Scene titleScene, settingsScene;
	private ComboBox<String> backgroundDropDown = new ComboBox<>();
	private TextField player1NameField = new TextField("User");
	private TextField player2NameField = new TextField("User");
	private ToggleGroup numOfPlayers = new ToggleGroup();
	private RadioButton zeroPlayers = new RadioButton("0");
	private RadioButton onePlayers = new RadioButton("1");
	private RadioButton twoPlayers = new RadioButton("2");
	private Button saveBtn = new Button("Save");
	private Button cancelBtn = new Button("Cancel");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			window = primaryStage;
			
			zeroPlayers.setToggleGroup(numOfPlayers);
			onePlayers.setToggleGroup(numOfPlayers);
			twoPlayers.setToggleGroup(numOfPlayers);
			zeroPlayers.setSelected(true);
			
			BorderPane titlePane = new BorderPane();
			titlePane.setCenter(getImage());
			titlePane.setBottom(getTitleButtons());
			titleScene = new Scene(titlePane,400,400);
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			BorderPane settingsPane = new BorderPane();
			settingsPane.setCenter(getSettings());
			settingsPane.setBottom(getSettingsButtons());
			settingsScene = new Scene(settingsPane, 400, 400);
			settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.getIcons().add(new Image("WarGamesIcon.png"));
			window.setTitle("War Games");
			window.setScene(titleScene);
			window.show();
			
			zeroPlayers.fireEvent(new ActionEvent());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private GridPane getSettings() {	
		GridPane settingsPane = new GridPane();
		settingsPane.setHgap(5);
		settingsPane.setVgap(5);
		
		settingsPane.add(new Label("Players: "), 0, 0);
		settingsPane.add(zeroPlayers, 1, 0);
		getNodeByColumnRowIndex(0, 0, settingsPane, "").setId("label");
		zeroPlayers.setId("num-of-players");
		
		settingsPane.add(onePlayers, 2, 0);
		onePlayers.setId("num-of-players");
		
		settingsPane.add(twoPlayers, 3, 0);
		twoPlayers.setId("num-of-players");
		
		zeroPlayers.setOnAction(e -> {
			totalHumans = 0;
			
			settingsPane.add(new Label("Background:"), 0, 1);
			settingsPane.add(backgroundDropDown, 1, 1, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane, "").setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!zeroPlayers.isSelected()) {
						getNodeByColumnRowIndex(0, 1, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 1, settingsPane, "remove");
					}
				}
			});
		});
		
		onePlayers.setOnAction(e -> {
			totalHumans = 1;
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);
			
			settingsPane.add(new Label("Background:"), 0, 2);
			settingsPane.add(backgroundDropDown, 1, 2, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane, "").setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!onePlayers.isSelected()) {
						getNodeByColumnRowIndex(0, 1, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 1, settingsPane, "remove");
						
						getNodeByColumnRowIndex(0, 2, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 2, settingsPane, "remove");
					}
				}
			});
		});
		
		twoPlayers.setOnAction(e -> {
			totalHumans = 2;
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);
			
			settingsPane.add(new Label("Player 2's Name:"), 0, 2);
			settingsPane.add(player2NameField, 1, 2, 4, 1);
			
			settingsPane.add(new Label("Background:"), 0, 3);
			settingsPane.add(backgroundDropDown, 1, 3, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 3, settingsPane, "").setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!twoPlayers.isSelected()) {
						getNodeByColumnRowIndex(0, 1, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 1, settingsPane, "remove");
						
						getNodeByColumnRowIndex(0, 2, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 2, settingsPane, "remove");
						
						getNodeByColumnRowIndex(0, 3, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 3, settingsPane, "remove");
					}
				}
			});
		});
		
		settingsPane.setAlignment(Pos.CENTER);
		return settingsPane;
		
	}
	
	private HBox getTitleButtons() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15,15,15,15));
		hBox.setAlignment(Pos.CENTER);
		Button playBtn = new Button("Play");
		Button settingsBtn = new Button("Settings");
		playBtn.setId("round-red");
		settingsBtn.setId("round-red");
		settingsBtn.setOnAction(e -> window.setScene(settingsScene));
		hBox.getChildren().addAll(playBtn, settingsBtn);
		return hBox;
	}
	
	private HBox getSettingsButtons() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15,15,15,15));
		hBox.setAlignment(Pos.CENTER);
		
		Button cancelBtn = new Button("Cancel");
		cancelBtn.setId("round-red");
		cancelBtn.setOnAction(e -> window.setScene(titleScene));
		
		Button saveBtn = new Button("Save");
		saveBtn.setId("round-red");
		saveBtn.setOnAction(e -> {
			// TODO Decide whether to send the background as an image or a string
			//String background;
			
			if (totalHumans == 1) {
				String player1 = player1NameField.getText();
				settings[0] = totalHumans;
				settings[1] = player1;
				//settings[3] = background;
			} else if (totalHumans == 2) {
				String player1 = player1NameField.getText();
				String player2 = player2NameField.getText();
				settings[0] = totalHumans;
				settings[1] = player1;
				settings[2] = player2;
				//settings[3] = background;
			}
			
			saveBtn.setText("Saved!");
			
			Timeline resetSaveBtnText = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
				saveBtn.setText("Save");
			}));
			resetSaveBtnText.setDelay(Duration.seconds(2));
			resetSaveBtnText.setCycleCount(1);
			resetSaveBtnText.play();
		});
		
		hBox.getChildren().addAll(cancelBtn, saveBtn);
		return hBox;
	}
	
	
	private HBox getImage() {
		HBox hBox = new HBox(25);
		ImageView imageView = new ImageView(new Image("WarGamesTitle.png"));
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().add(imageView);
		return hBox;
	}
	
	@SuppressWarnings("static-access")
	// Gets a node by (column, row) index
	private Node getNodeByColumnRowIndex(final int column, final int row, GridPane gridPane, String function) {
		ObservableList<Node> children = gridPane.getChildren();
		
		for(Node node : children) {
		    if((node instanceof Label || node instanceof TextField || node instanceof ComboBox) && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
		        switch (function) {
		        	case "remove":
				        gridPane.getChildren().remove(node);
				        return null;   
		        	case "":
		        		return node;
		        }
		    }
		} 
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
