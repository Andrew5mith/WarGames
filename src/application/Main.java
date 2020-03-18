package application;
	
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
<<<<<<< HEAD
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
=======
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
>>>>>>> Branch-one
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
<<<<<<< HEAD


public class Main extends Application {
	
	public Object[] settings;
	
	private int totalHumans;
	
=======
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;


public class Main extends Application {
	//branch test
>>>>>>> Branch-one
	private Stage window;
	private BorderPane gameBorderPane;
	private Scene titleScene, settingsScene, gameScene, gameOverScene;
	private ComboBox<String> backgroundDropDown = new ComboBox<>();
	private TextField player1NameField = new TextField("User");
	private TextField player2NameField = new TextField("User");
	private ToggleGroup numOfPlayers = new ToggleGroup();
	private RadioButton zeroPlayers = new RadioButton("0");
	private RadioButton onePlayers = new RadioButton("1");
	private RadioButton twoPlayers = new RadioButton("2");
	private Button saveBtn = new Button("Save");
	private Button cancelBtn = new Button("Cancel");
	private char whoseTurn = 'X';
	// Create and initialize cell
	private Cell[][] cell =  new Cell[3][3];
	// Create and initialize a status label
	 private Label lblStatus;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			window = primaryStage;
			
<<<<<<< HEAD
			zeroPlayers.setToggleGroup(numOfPlayers);
			onePlayers.setToggleGroup(numOfPlayers);
			twoPlayers.setToggleGroup(numOfPlayers);
			zeroPlayers.setSelected(true);
			
=======
			//Title
>>>>>>> Branch-one
			BorderPane titlePane = new BorderPane();
			titlePane.setCenter(getImage());
			titlePane.setBottom(getTitleButtons());
			titleScene = new Scene(titlePane,400,400);
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Settings
			BorderPane settingsPane = new BorderPane();
			settingsPane.setCenter(getSettings());
			settingsPane.setBottom(getSettingsButtons());
			settingsScene = new Scene(settingsPane, 400, 400);
			settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Game
			gameBorderPane = new BorderPane();
			gameBorderPane.setTop(getScoreBoard());
			gameBorderPane.setCenter(getGameGrid());
			gameBorderPane.setBottom(getLblStatus());	
			gameScene = new Scene(gameBorderPane, 400, 400);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//GameOver
			BorderPane gameOverPane = new BorderPane();
			gameOverPane.setCenter(getGameOverScreen());
			gameOverScene = new Scene(gameOverPane, 400, 400);
			gameOverScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.getIcons().add(new Image("WarGamesIcon.png"));
			window.setTitle("War Games");
			window.setScene(titleScene);
			window.show();
			
			zeroPlayers.fireEvent(new ActionEvent());
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private VBox getGameOverScreen() {
		VBox vBox = new VBox(15);
		vBox.setPadding(new Insets(15,15,15,15));
		vBox.setAlignment(Pos.CENTER);
		Button playAgainBtn = new Button("Play Again");
		playAgainBtn.setOnAction(e -> window.setScene(gameScene));
		vBox.getChildren().addAll(getLblStatus(), playAgainBtn);
		return vBox;
	}
	
	
	private Node getLblStatus() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15,15,15,15));
		lblStatus = new Label("X's turn to play");
		hBox.getChildren().addAll(lblStatus);
		return hBox;
	}

	private Node getScoreBoard() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15,15,15,15));
		return hBox;
	}

	private GridPane getGameGrid() {
		GridPane gameGridPane = new GridPane();
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				gameGridPane.add(cell[i][j] = new Cell(),  j,  i);
		return gameGridPane;
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
		playBtn.setOnAction(e -> window.setScene(gameScene));
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
	
<<<<<<< HEAD
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
=======
	
	  /** Determine if the cell are all occupied */
	  public boolean isFull() {
	    for (int i = 0; i < 3; i++)
	      for (int j = 0; j < 3; j++)
	        if (cell[i][j].getToken() == ' ')
	          return false;

	    return true;
	  }
	  
	  /** Determine if the player with the specified token wins */
	  public boolean isWon(char token) {
	    for (int i = 0; i < 3; i++)
	      if (cell[i][0].getToken() == token
	          && cell[i][1].getToken() == token
	          && cell[i][2].getToken() == token) {
	        return true;
	      }
	    for (int j = 0; j < 3; j++)
	        if (cell[0][j].getToken() ==  token
	            && cell[1][j].getToken() == token
	            && cell[2][j].getToken() == token) {
	          return true;
	        }

	      if (cell[0][0].getToken() == token 
	          && cell[1][1].getToken() == token        
	          && cell[2][2].getToken() == token) {
	        return true;
	      }

	      if (cell[0][2].getToken() == token
	          && cell[1][1].getToken() == token
	          && cell[2][0].getToken() == token) {
	        return true;
	      }

	      return false;
	    }
	  
	// An inner class for a cell
	  public class Cell extends Pane {
	    // Token used for this cell
	    private char token = ' ';

	    public Cell() {
	      setStyle("-fx-border-color: black"); 
	      this.setPrefSize(800, 800);
	      this.setOnMouseClicked(e -> handleMouseClick());
	    }

	    /** Return token */
	    public char getToken() {
	      return token;
	    }
	    
	    /** Set a new token */
	    public void setToken(char c) {
	      token = c;
	      
	      if (token == 'X') {
	        Line line1 = new Line(10, 10, 
	          this.getWidth() - 10, this.getHeight() - 10);
	        line1.endXProperty().bind(this.widthProperty().subtract(10));
	        line1.endYProperty().bind(this.heightProperty().subtract(10));
	        Line line2 = new Line(10, this.getHeight() - 10, 
	          this.getWidth() - 10, 10);
	        line2.startYProperty().bind(
	          this.heightProperty().subtract(10));
	        line2.endXProperty().bind(this.widthProperty().subtract(10));
	        
	        // Add the lines to the pane
	        this.getChildren().addAll(line1, line2); 
	      }
	      else if (token == 'O') {
	        Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
	          this.getHeight() / 2, this.getWidth() / 2 - 10, 
	          this.getHeight() / 2 - 10);
	        ellipse.centerXProperty().bind(
	          this.widthProperty().divide(2));
	        ellipse.centerYProperty().bind(
	            this.heightProperty().divide(2));
	        ellipse.radiusXProperty().bind(
	            this.widthProperty().divide(2).subtract(10));        
	        ellipse.radiusYProperty().bind(
	            this.heightProperty().divide(2).subtract(10));   
	        ellipse.setStroke(Color.BLACK);
	        ellipse.setFill(Color.TRANSPARENT);
	        
	        getChildren().add(ellipse); // Add the ellipse to the pane
	      }
	    }
	    /* Handle a mouse click event */
	    private void handleMouseClick() {
	      // If cell is empty and game is not over
	      if (token == ' ' && whoseTurn != ' ') {
	        setToken(whoseTurn); // Set token in the cell

	        // Check game status
	        if (isWon(whoseTurn)) {
	          lblStatus.setText(whoseTurn + " won! The game is over");
	          whoseTurn = ' '; // Game is over
	          window.setScene(gameOverScene);
	        }
	        else if (isFull()) {
	          lblStatus.setText("Draw! The game is over");
	          whoseTurn = ' '; // Game is over
	          
	        }
	        else {
	          // Change the turn
	          whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
	          // Display whose turn
	          lblStatus.setText(whoseTurn + "'s turn");
	        }
	      }
	    }
	  }
	    
>>>>>>> Branch-one
	
	public static void main(String[] args) {
		launch(args);
	}
}
