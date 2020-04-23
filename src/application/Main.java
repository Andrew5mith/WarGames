package application;
	
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	public Object[] settings;
	private int totalHumans;
	private Stage window;
	private BorderPane gameBorderPane, titlePane, settingsPane, gameOverPane;
	private Scene titleScene, settingsScene, gameScene, gameOverScene;
	private ComboBox<String> backgroundDropDown = new ComboBox<>();
	private ComboBox<String> avatarDropDown1 = new ComboBox<>();
	private ComboBox<String> avatarDropDown2 = new ComboBox<>();
	private StackPane stackPane1 = new StackPane();
	private StackPane stackPane2 = new StackPane();
	private TextField player1NameField = new TextField("Player 1");
	private TextField player2NameField = new TextField("Player 2");
	private ToggleGroup numOfPlayers = new ToggleGroup();
	private RadioButton zeroPlayers = new RadioButton("0");
	private RadioButton onePlayers = new RadioButton("1");
	private RadioButton twoPlayers = new RadioButton("2");
	private Button saveBtn;
	private String player1;
	private String player2;
	private char whoseTurn = 'X';
	// Create and initialize cell
	private Cell[][] cell =  new Cell[3][3];
	// Create and initialize a status label
	 private Label lblStatus;
	private String[] colors = {"Red", "Blue", "Green", "White", "Purple", "Orange"};
	private String[] avatars = {"Imperial Knights", "Chaos Space Marine", "Grey Knights",
								"Orks", "Tempestus Scions", "Dark Eloar"};
	private ImageView[] avatarImages1 = {new ImageView(
			new Image("imperialknights.png")), 
			new ImageView(new Image("chaosspacemarine.png")),
			new ImageView(new Image("greyknights.png")),
			new ImageView(new Image("orks.png")),
			new ImageView(new Image("tempestusscions.png")),
			new ImageView(new Image("darkeloar.png"))};
	
	private ImageView[] avatarImages2 = {new ImageView(
			new Image("imperialknights.png")), 
			new ImageView(new Image("chaosspacemarine.png")),
			new ImageView(new Image("greyknights.png")),
			new ImageView(new Image("orks.png")),
			new ImageView(new Image("tempestusscions.png")),
			new ImageView(new Image("darkeloar.png"))};
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
		
			window = primaryStage;

			zeroPlayers.setToggleGroup(numOfPlayers);
			onePlayers.setToggleGroup(numOfPlayers);
			twoPlayers.setToggleGroup(numOfPlayers);
			onePlayers.setSelected(true);
			
			//Title
			titlePane = new BorderPane();
			//  titlePane.setStyle(style);

			titlePane.setCenter(getImage());
			titlePane.setBottom(getTitleButtons());
			titleScene = new Scene(titlePane, 700, 700);
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Settings
			settingsPane = new BorderPane();
			settingsPane.setTop(getAvatars());
			settingsPane.setCenter(getSettings());
			settingsPane.setBottom(getSettingsButtons());
			settingsScene = new Scene(settingsPane, 700, 700);
			settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//Game
			gameBorderPane = new BorderPane();
			gameBorderPane.setTop(getScoreBoard());
			gameBorderPane.setCenter(getGameGrid());
			gameBorderPane.setBottom(getLblStatus());	
			gameScene = new Scene(gameBorderPane, 700, 700);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//GameOver
			gameOverPane = new BorderPane();
			gameOverPane.setCenter(getGameOverScreen());
			gameOverScene = new Scene(gameOverPane, 700, 700);
			gameOverScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.getIcons().add(new Image("WarGamesIcon.png"));
			window.setTitle("War Games");
			window.setScene(titleScene);
			window.show();
			
			onePlayers.fireEvent(new ActionEvent()); //start the game against computer
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private VBox getGameOverScreen() {
		VBox vBox = new VBox(15);
		vBox.setPadding(new Insets(15,15,15,15));
		vBox.setAlignment(Pos.CENTER);
		Button playAgainBtn = new Button("Play Again");
		playAgainBtn.setOnAction((ActionEvent event) -> {
			window.close();
			Platform.runLater( () -> {
				try {
					new Main().start( new Stage() );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} );
	
		});
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
		
		avatarDropDown1 = new ComboBox<>(); 
		avatarDropDown1.setValue("Imperial Knights");
		ObservableList<String> avatarList1 = FXCollections.observableArrayList(avatars);
		avatarDropDown1.getItems().addAll(avatarList1);
		
		avatarDropDown2 = new ComboBox<>(); 
		avatarDropDown2.setValue("Dark Eloar");
		ObservableList<String> avatarList2 = FXCollections.observableArrayList(avatars);
		avatarDropDown2.getItems().addAll(avatarList2);
		
		backgroundDropDown = new ComboBox<>();		
		backgroundDropDown.setValue("Red");
		ObservableList<String> items = FXCollections.observableArrayList(colors);
		backgroundDropDown.getItems().addAll(items); // Add items to combo box
		
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
			
			if(!saveBtn.isPressed()) {
				avatarDropDown1.setValue("Imperial Knights");
			}
			stackPane1.getChildren().add(new ImageView(new Image("imperialknights.png")));
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);	
			settingsPane.add(new Label("Player 1's Avatar:"), 0, 2);
			settingsPane.add(avatarDropDown1, 1, 2, 4, 1);
			settingsPane.add(new Label("Background:"), 0, 3);
			settingsPane.add(backgroundDropDown, 1, 3, 4, 1);		
			getNodeByColumnRowIndex(0, 1, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 3, settingsPane, "").setId("label");

			numOfPlayers.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
					if (!onePlayers.isSelected()) {
						getNodeByColumnRowIndex(0, 1, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 1, settingsPane, "remove");
						
						getNodeByColumnRowIndex(0, 2, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 2, settingsPane, "remove");
						
						getNodeByColumnRowIndex(0, 3, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 3, settingsPane, "remove");
						
						stackPane1.getChildren().clear();
					}
				}
			});
		});
		
		twoPlayers.setOnAction(e -> {
			totalHumans = 2;
			
			if(!saveBtn.isPressed()) {
				avatarDropDown1.setValue("Imperial Knights");
				avatarDropDown2.setValue("Dark Eloar");
			}
			stackPane1.getChildren().add(new ImageView(new Image("imperialknights.png")));
			stackPane2.getChildren().add(new ImageView(new Image("darkeloar.png")));
			
			settingsPane.add(new Label("Player 1's Name:"), 0, 1);
			settingsPane.add(player1NameField, 1, 1, 4, 1);	
			settingsPane.add(new Label("Player 1's Avatar:"), 0, 2);
			settingsPane.add(avatarDropDown1, 1, 2, 4, 1);
			settingsPane.add(new Label("Player 2's Name:"), 0, 3);
			settingsPane.add(player2NameField, 1, 3, 4, 1);	
			settingsPane.add(new Label("Player 2's Avatar:"), 0, 4);
			settingsPane.add(avatarDropDown2, 1, 4, 4, 1);	
			settingsPane.add(new Label("Background:"), 0, 5);
			settingsPane.add(backgroundDropDown, 1, 5, 4, 1);	
			getNodeByColumnRowIndex(0, 1, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 2, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 3, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 4, settingsPane, "").setId("label");
			getNodeByColumnRowIndex(0, 5, settingsPane, "").setId("label");

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
						getNodeByColumnRowIndex(0, 4, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 4, settingsPane, "remove");
						getNodeByColumnRowIndex(0, 5, settingsPane, "remove");
						getNodeByColumnRowIndex(1, 5, settingsPane, "remove");
						
						stackPane1.getChildren().clear();
						stackPane2.getChildren().clear();
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
	
	//return avatars
	private HBox getAvatars() {	
		HBox avatarPane = new HBox();

		avatarPane.setAlignment(Pos.TOP_CENTER);
		avatarPane.setSpacing(5);
		avatarPane.getChildren().addAll(stackPane1, stackPane2);
		return avatarPane;
	}
	

	//return settings 
	private HBox getSettingsButtons() {
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(15,15,15,15));
		hBox.setAlignment(Pos.CENTER);
		
		Button backBtn = new Button("Back");
		backBtn.setId("round-red");
		backBtn.setOnAction(e -> window.setScene(titleScene));
		
		saveBtn = new Button("Save");
		saveBtn.setId("round-red");
		saveBtn.setOnAction(e -> {
			
			//String background;
			player1 = player1NameField.getText();
			player2 = player2NameField.getText();
			
			String avatar1 = avatarDropDown1.getValue();
			String avatar2 = avatarDropDown2.getValue();
			
			if(onePlayers.isSelected()) {
				for(int i = 0; i < 6; i++) {
					if(avatar1 == avatars[i]) {
						stackPane1.getChildren().clear();
						stackPane1.getChildren().add(avatarImages1[i]);
					}
				}
			}

			if(twoPlayers.isSelected()) {
				for(int i = 0; i < 6; i++) {
					if(avatar1 == avatars[i]) {
						stackPane1.getChildren().clear();
						stackPane1.getChildren().add(avatarImages1[i]);
					}
				}
				for(int i = 0; i < 6; i++) {
					if(avatar2 == avatars[i]) {
						stackPane2.getChildren().clear();
						stackPane2.getChildren().add(avatarImages2[i]);
					}
				}
			}
			
			String background = backgroundDropDown.getValue();

			String redBg = "-fx-background-color: firebrick;";
			String blueBg = "-fx-background-color: blue;";
			String greenBg = "-fx-background-color: green;";
			String whiteBg = "-fx-background-color: white;";
			String purpleBg = "-fx-background-color: purple;";
			String orangeBg = "-fx-background-color: orange;";
			
			//change the background when saved
			if(background == "Red") {
				titlePane.setStyle(redBg);
				gameBorderPane.setStyle(redBg);
				settingsPane.setStyle(redBg);
				gameOverPane.setStyle(redBg);
				
			}
			if(background == "Blue") {
				titlePane.setStyle(blueBg);
				gameBorderPane.setStyle(blueBg);
				settingsPane.setStyle(blueBg);
				gameOverPane.setStyle(blueBg);
				
			}
			if(background == "Green") {
				titlePane.setStyle(greenBg);
				gameBorderPane.setStyle(greenBg);
				settingsPane.setStyle(greenBg);
				gameOverPane.setStyle(greenBg);
				
			}
			if(background == "White") {
				titlePane.setStyle(whiteBg);
				gameBorderPane.setStyle(whiteBg);
				settingsPane.setStyle(whiteBg);
				gameOverPane.setStyle(whiteBg);
				
			}
			if(background == "Purple") {
				titlePane.setStyle(purpleBg);
				gameBorderPane.setStyle(purpleBg);
				settingsPane.setStyle(purpleBg);
				gameOverPane.setStyle(purpleBg);
				
			}
			if(background == "Orange") {
				titlePane.setStyle(orangeBg);
				gameBorderPane.setStyle(orangeBg);
				settingsPane.setStyle(orangeBg);
				gameOverPane.setStyle(orangeBg);
				
			}
			
			
			//create an animation when the saved button is clicked
			saveBtn.setText("Saved!");
			Timeline resetSaveBtnText = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
				saveBtn.setText("Save");
			}));
			resetSaveBtnText.setDelay(Duration.seconds(2));
			resetSaveBtnText.setCycleCount(1);
			resetSaveBtnText.play();
		
		});
		
		hBox.getChildren().addAll(backBtn, saveBtn);
		return hBox;
	}
	
	//return title image
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
	
	  // Determine if the cell are all occupied 
	  public boolean isFull() {
	    for (int i = 0; i < 3; i++)
	      for (int j = 0; j < 3; j++)
	        if (cell[i][j].getToken() == ' ')
	          return false;

	    return true;
	  }
	  
	  // Determine if the player with the specified token wins 
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
	  
	    //find empty cell - call this method only when some cells are left.
	    public Cell findEmptyCell() {
	        int index;
	        Random r = new Random();

	        while (true) {
	            index = r.nextInt(9);
	            int row = index % 3;
	            int col = index / 3;

	            if(cell[row][col].getToken() == ' ') {
	                return cell[row][col];
	            }
	        }
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

	    // Return token 
	    public char getToken() {
	      return token;
	    }
	    
	    //Set new token
	    public void setToken(char c) {
	      token = c;
	      
	      if (token == 'X') {
	        //use dog image for X
	        ImageView dog = new ImageView(new Image("dog.png"));
	        this.getChildren().addAll(dog); 
	      }
	      //use cat image for O
	      else if (token == 'O') {
	        ImageView cat = new ImageView(new Image("cat.png"));
	        this.getChildren().addAll(cat);
	         
	      }
	    }
	    // Handle a mouse click event 
	    private void handleMouseClick() {
	      // If cell is empty and game is not over
	    	
	      if (token == ' ' && whoseTurn != ' ') {
	        setToken(whoseTurn); // Set token in the cell

	        // Check game status
	        if (isWon(whoseTurn)) {
	        	
	        	//display who won
	        	if(totalHumans == 1) {
	        		if(whoseTurn == 'X') {
	        			lblStatus.setText(player1 + " won!");
	        		}
	        		else {
	        			lblStatus.setText("Computer won! ");
	        		}
	        		// lblStatus.setText(whoseTurn + " won! The game is over");
	        		
	        	}
	        	if (totalHumans == 2) {
	        		if(whoseTurn == 'X') {
	        			lblStatus.setText(player1 + " won!");
	        		}
	        		else {
	        			lblStatus.setText(player2 + " won!");
	        		}
	        	}
	        	
	        	if(totalHumans == 0) {
	        		lblStatus.setText(whoseTurn + " won! The game is over");
	        	}
	        	
	        	whoseTurn = ' '; // Game is over
        		window.setScene(gameOverScene);
	        }
	        //if gameboard is full and no one one display draw text
	        else if (isFull()) {
	        	window.setScene(gameOverScene);
	          lblStatus.setText("Draw! The game is over");
	          whoseTurn = ' '; // Game is over
	          
	        }
	        else {
	        	//PvP
	        	if(totalHumans == 2) {
	        		// Change the turn
	        		whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';
	        		// Display whose turn
	        		lblStatus.setText(whoseTurn + "'s turn");
	        	}
	        	//Player vs. CPU
	        	if(totalHumans == 1) {
	        		whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';

                    // Make move if it is computer's turn
                    if(whoseTurn == 'O') {
                        Cell compCell = findEmptyCell();
                        compCell.handleMouseClick();
                   }
	        	}
	        	//CPU vs CPU
	        	if(totalHumans == 0) {
                    // Change the turn
                    whoseTurn = (whoseTurn == 'X') ? 'O' : 'X';

                    // Make move if it is computer's turn
                    if(whoseTurn == 'O') {
                        Cell compCell = findEmptyCell();
                        compCell.handleMouseClick();
                    }
                    if(whoseTurn == 'X') {
                        Cell compCell = findEmptyCell();
                        compCell.handleMouseClick();
                    }
	        	}
	      }
	    }
	  }
	  }
	    
	
	public static void main(String[] args) {
		launch(args);
	}
}
