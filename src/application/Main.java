package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
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
		getNodeByColumnRowIndex(0, 0, settingsPane).setId("label");
		zeroPlayers.setId("num-of-players");
		
		settingsPane.add(onePlayers, 2, 0);
		onePlayers.setId("num-of-players");
		
		settingsPane.add(twoPlayers, 3, 0);
		twoPlayers.setId("num-of-players");
		
		zeroPlayers.setOnAction(e -> {
			settingsPane.add(new Label("Background:"), 0, 1);
			settingsPane.add(backgroundDropDown, 1, 1, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane).setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!zeroPlayers.isSelected()) {
						removeNodeByColumnRowIndex(0, 1, settingsPane);
						removeNodeByColumnRowIndex(1, 1, settingsPane);
					}
				}
			});
		});
		
		onePlayers.setOnAction(e -> {
			numOfPlayers.setUserData(onePlayers);
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);
			
			settingsPane.add(new Label("Background:"), 0, 2);
			settingsPane.add(backgroundDropDown, 1, 2, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane).setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane).setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!onePlayers.isSelected()) {
						removeNodeByColumnRowIndex(0, 1, settingsPane);
						removeNodeByColumnRowIndex(1, 1, settingsPane);
						
						removeNodeByColumnRowIndex(0, 2, settingsPane);
						removeNodeByColumnRowIndex(1, 2, settingsPane);
					}
				}
			});
		});
		
		twoPlayers.setOnAction(e -> {
			numOfPlayers.setUserData(twoPlayers);
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);
			
			settingsPane.add(new Label("Player 2's Name:"), 0, 2);
			settingsPane.add(player2NameField, 1, 2, 4, 1);
			
			settingsPane.add(new Label("Background:"), 0, 3);
			settingsPane.add(backgroundDropDown, 1, 3, 4, 1);
			
			getNodeByColumnRowIndex(0, 1, settingsPane).setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane).setId("label");
			getNodeByColumnRowIndex(0, 3, settingsPane).setId("label");
			
			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!twoPlayers.isSelected()) {
						removeNodeByColumnRowIndex(0, 1, settingsPane);
						removeNodeByColumnRowIndex(1, 1, settingsPane);
						
						removeNodeByColumnRowIndex(0, 2, settingsPane);
						removeNodeByColumnRowIndex(1, 2, settingsPane);
						
						removeNodeByColumnRowIndex(0, 3, settingsPane);
						removeNodeByColumnRowIndex(1, 3, settingsPane);
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
		Button saveBtn = new Button("Save");
		cancelBtn.setId("round-red");
		saveBtn.setId("round-red");
		cancelBtn.setOnAction(e -> window.setScene(titleScene));
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
	// Removes a node by (column, row) index
	public void removeNodeByColumnRowIndex(final int column, final int row, GridPane gridPane) {
		ObservableList<Node> children = gridPane.getChildren();
		
		for(Node node : children) {
		    if((node instanceof Label || node instanceof TextField || node instanceof ComboBox) && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
		        gridPane.getChildren().remove(node);
		        break;
		    }
		} 
	}
	
	@SuppressWarnings("static-access")
	// Gets a node by (column, row) index
	public Node getNodeByColumnRowIndex(final int column, final int row, GridPane gridPane) {
		ObservableList<Node> children = gridPane.getChildren();
		
		for(Node node : children) {
		    if((node instanceof Label || node instanceof TextField || node instanceof ComboBox) && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
		        return node;
		    }
		} 
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
