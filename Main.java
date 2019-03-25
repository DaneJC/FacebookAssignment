/**
 * Class: B.Sc. Cloud Computing
 * Instructor: Maria Boyle
 * Description: API-FacebookAssignement -- PARTS 1-5 --: Facebook login/signup GUI
 * Date: 21/03/2019
 * @author Dane Campbell [L00142041]
 * @version 1.0
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

/** Facebook login | signup GUI class */
public class Main extends Application {

	private VBox root, vbSignUp;
	private HBox hbTop, hbBottom;
	private Label lblEmail, lblPassword, lblSignUp, fbImage;
	private Button btnLogin, btnSignUp;
	private TextField txfLoginEmail, txfFName, txfSName, txfEmail, txfPassword;
	private PasswordField pwfLoginPassword;
	private ImageView iv;
	private FacebookDB fbDb = new FacebookDB();

	/* -- API-FacebookAssignment PART 1 [30%] -- */
    /** application start method override */
	@Override
	public void start(Stage primaryStage) {

		try {

			root = new VBox();  // create vbox root pane
			root.setBackground(
					new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
			Scene scene = new Scene(root,570,200);  // set scene as vbox root pane

			/* top hbox pane and components --------------------------------------------------------------------------------------- */
			// set component with
			double upperComponentWidth = scene.getWidth()/5;  // size for each component in top pane

			// email label:
			lblEmail = new Label("Email: ");  // create email label 
			lblEmail.setPrefWidth(upperComponentWidth);  // set email label width
			lblEmail.setPadding(new Insets(4, 0, 0, 0));  // set email label padding
			lblEmail.setAlignment(Pos.CENTER_LEFT);  // set email label text alignment

			// email text field:
			txfLoginEmail = new TextField();  // create email text field
			txfLoginEmail.setPromptText("\"bartsimpson@lyit.ie\"");
			txfLoginEmail.setPrefWidth(upperComponentWidth);  // set email text field width

			// password label:
			lblPassword = new Label("Password: ");  // create password label
			lblPassword.setPrefWidth(upperComponentWidth);  // set password label width
			lblPassword.setPadding(new Insets(4, 0, 0, 0));  // set password label padding
			lblPassword.setAlignment(Pos.CENTER_LEFT);  // set password label text alignment

			// password field:
			pwfLoginPassword = new PasswordField();  // create password text field
			pwfLoginPassword.setPrefWidth(upperComponentWidth);  // set password text field width

			// login button:
			btnLogin = new Button("Log In");  // create login button
			btnLogin.setPrefWidth(upperComponentWidth);  // set login button width
			btnLogin.setBackground(
					new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(0))));  // set login button colour ALICEBLUE
			btnLogin.setOnMouseEntered( e-> buttonMouseEnter(btnLogin));  // set login button facebook blue with mouse hover
			btnLogin.setOnMouseExited( e-> buttonMouseExit(btnLogin));  // set login button white with mouse hover
			btnLogin.setOnAction( e-> loginButtonClick());  // call loginButtonClick() when login button clicked

			// top hbox pane
			hbTop =  new HBox();  // create top hbox pane
			hbTop.setBorder(new Border(new BorderStroke(Color.BLACK, 
		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));  // create border around top vbox pane
			hbTop.getChildren().addAll(lblEmail, txfLoginEmail, lblPassword, pwfLoginPassword, btnLogin);  // add components to top hbox
			root.getChildren().add(hbTop);  // add top hbox to root vbox pane

			/* bottom hbox pane components ---------------------------------------------------------------------------------------- */					
			// sign up vbox
			vbSignUp = new VBox(1);
			vbSignUp.setPrefWidth(126);  // set signup vbox width 126
			
			// facebook image
			iv = new ImageView(getClass().getResource("facebook.png").toExternalForm());
			fbImage = new Label("", iv);
			fbImage.setPadding(new Insets(10, 42, 10, 42));

			// signup label
			lblSignUp = new Label("Sign Up");  // create signup label
			lblSignUp.setPrefWidth(126);  // set label width 126
			lblSignUp.setFont(Font.font("SanSerif", FontWeight.BOLD, 18) );  // set signup label font properties
			lblSignUp.setBorder(new Border(new BorderStroke(Color.BLACK, 
		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));  // create border around signup label
			vbSignUp.getChildren().add(lblSignUp);  // add signup label to signup vbox pane

			// first name textfield
			txfFName = new TextField();  // create first name textfield
			txfFName.setPromptText("Enter First Name");
			vbSignUp.getChildren().add(txfFName);  // add first name textfield to signup vbox pane
			
			// surname textfield
			txfSName = new TextField();  // create surname textfield
			txfSName.setPromptText("Enter Surname");
			vbSignUp.getChildren().add(txfSName);  // add surname textfield to signup vbox pane

			// email textfield
			txfEmail = new TextField();  // create email textfield
			txfEmail.setPromptText("Enter Email Address");
			vbSignUp.getChildren().add(txfEmail);  // add email textfield to signup vbox pane

			// surname textfield
			txfPassword = new TextField();  // create password textfield
			txfPassword.setPromptText("Enter Password");
			vbSignUp.getChildren().add(txfPassword);  // add password textfield to signup vbox pane
			
			// signup button
			btnSignUp = new Button("Sign Up");  // create signup button
			btnSignUp.setPrefWidth(Double.MAX_VALUE);
			btnSignUp.setFont(Font.font("SanSerif", FontWeight.BOLD, 18) );  // set signup label font properties
			btnSignUp.setBackground(
					new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(0))));
			btnSignUp.setOnMouseEntered( e-> buttonMouseEnter(btnSignUp));  // set signup button facebook blue with mouse hover
			btnSignUp.setOnMouseExited( e-> buttonMouseExit(btnSignUp));  // set signup button white with mouse hover
			btnSignUp.setOnAction( e-> signUpButtonClick());  // call signUpButtonClick() when signup button clicked
			vbSignUp.getChildren().add(btnSignUp);  // add signup button to signup vbox pane

			// bottom hbox pane
			hbBottom =  new HBox();  // create bottom hbox pane
			hbBottom.getChildren().addAll(fbImage, vbSignUp);//, txfLoginEmail, lblPassword, pwfLoginPassword, btnLogin);  // add components to top hbox
			root.getChildren().add(hbBottom);  // add top hbox to root vbox pane

//			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(this.getClass().getResource("fb-icon.png").toString()));
			primaryStage.setScene(scene);
			primaryStage.setTitle("FACEBOOK");
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Set button text white and background facebook blue when button mouse enter */
	private void buttonMouseEnter(Button btn){
		btn.setBackground(
				new Background(new BackgroundFill(Color.rgb(66,103,178), new CornerRadii(0), new Insets(0))));  // set login button facebook blue with mouse enter
		btn.setTextFill(Color.WHITE);
	}

	/** Set button text black background aliceblue when button mouse exit */
	private void buttonMouseExit(Button btn){
		btn.setBackground(
				new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(0), new Insets(0))));  // set login button aliceblue with mouse exit
		btn.setTextFill(Color.BLACK);  // set text black
	}

	/* -- API-FacebookAssignment PART 3 [26%] -- */
	/** signUpButtonClick() - Extracts new user’s details from login TextFields ->
	 * 	use these details to build an SQL INSERT String ->
	 *  use the insertIntoDatabase() method to add the new user to the user table in the facebook database ->
	 *  create relevant information message dialogs */
	private void signUpButtonClick(){

		// get signup textFields content - trim whitespace if present
		String fName = txfFName.getText().trim();
		String sName = txfSName.getText().trim();
		String email = txfEmail.getText().trim();
		String password = txfPassword.getText().trim();

		// check user text entries -> notify user of errors if preset
		if(confirmSignUpEntries(fName, sName, email, password)){

			// create string SQL statement for insertIntoDatabase(sql) call
			String sql =
					"INSERT INTO `user` "+
					"VALUES (lower('"+email+"'), '"+password+"', '"+fName+"', '"+sName+"') "+
					"ON DUPLICATE KEY UPDATE " +
					"password='"+password+"', firstname='"+fName+"', lastname='"+sName+"';";

			if(fbDb.insertIntoDatabase(sql)){  // if successful user signup operation

				printInformationAlert("SignUp Complete", "Welcome to Facebook " + fName + "!", "Your users details have been added to our database.");
			}
			else{

				printErrorAlert("SignUp Incomplete", "Apologies "+fName+"!",
						"We seem to be experiencing some technical difficulties.\n Please try again!");
			}
		}
	}

	/* -- API-FacebookAssignment PART 5 [18%] -- */
	/** loginButtonClick() - Calls the getUserPasswordFromDatabase() method to get the password for the Email: entered. ->
	 *  If the password in the database matches the Password entered: display a simple message dialog box saying “Correct Password Entered” ->
	 *  ELSE IF it does not match: display a message dialog box saying “Incorrect Password Entered”. ->
	 *  ELSE IF Email entered does not exist in the database: display an appropriate message dialog box.*/
	private void loginButtonClick(){

		// get login textFields content - trim whitespace if present
		String email = txfLoginEmail.getText().trim();
		String password = pwfLoginPassword.getText().trim();

		// search for provided email in database
		String dbResult = fbDb.getUserPasswordFromDatabase(email);

		// display appropriate message dialog
		if(dbResult.equals("undetected"))
			printErrorAlert("ERROR", "User Not Found!", "Email: "+email+" not in database!");
		else if(!dbResult.equals(password))
			printErrorAlert("ERROR", "Incorrect Password!", "Incorrect Password for user: "+email+"!");
		else if(dbResult.equals(password))
			printInformationAlert("LOGGED IN", "Password Accepted!", "Correct Password entered for user: "+email+"!");
	}

	/** Check sign up text field entries for errors -> IF entries permitted: return true ->
	 *  ELSE: compute error message for printErrorAlert(String message) call */
	private boolean confirmSignUpEntries(String fName, String sName, String email, String password){

		boolean permitted = true;
		String errorMessage = "";

		// check first name entry
		if(fName.length()==0){
			errorMessage = errorMessage + "No first name entered!\n";
			permitted = false;
		}
		else if(fName.contains(" ")){
			errorMessage = errorMessage + "Enter only first name! (no spaces)\n";
			permitted = false;
		}

		// check surnname entry
		if(sName.length()==0){
			errorMessage = errorMessage + "No surname entered!\n";
			permitted = false;
		}
		else if(sName.contains(" ")){
			errorMessage = errorMessage + "Enter only surname! (no spaces)\n";
			permitted = false;
		}

		// check email entry
		if(email.length()==0){
			errorMessage = errorMessage + "No email address entered!\n";
			permitted = false;
		}
		else if(!email.contains("@") || !email.contains(".")){
			errorMessage = errorMessage + "Enter valid email address!\n";
			permitted = false;
		}

		// check password entry
		if(password.length()==0){
			errorMessage = errorMessage + "No password entered!\n";
			permitted = false;
		}
		else if(password.length()<8){
			errorMessage = errorMessage + "Password to short! (min:8)\n";
			permitted = false;
		}
		// IF not satisfactory textField entries: print constructed error message dialog
		if(!permitted)
			printErrorAlert("Input Error", "Please review the following:", errorMessage);

		// Return to user signup button event method with flag to either proceed or not
		return permitted;
	}

	/** Print error alert dialog displaying contents of methods string parameter */
	private void printErrorAlert(String title, String header, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if(title.length()>0)
			alert.setTitle(title);
		if(header.length()>0)
			alert.setHeaderText(header);
		if(message.length()>0)
			alert.setContentText(message);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("x.png").toString()));
		alert.showAndWait();
	}

	/** Print information alert dialog displaying  contents of methods string parameter -> clear GUI textFields on OK button click*/
	private void printInformationAlert(String title, String header, String message) {
		// create successful signup message dialog
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		if(title.length()>0)
			alert.setTitle(title);
		if(header.length()>0)
			alert.setHeaderText(header);
		if(message.length()>0)
			alert.setContentText(message);
		alert.setGraphic(new ImageView(this.getClass().getResource("check-mark.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("check-mark.png").toString()));

		// clear textField entries for next user when message dialog OK button clicked
		Optional<ButtonType> result = alert.showAndWait();
		if (!result.isPresent() || result.get() == ButtonType.OK) {

			clearTextFields();
		}
	}

	/** clear all text fields */
	private void clearTextFields() {

		txfLoginEmail.clear();
		txfLoginEmail.setPromptText("bartsimpson@lyit.ie");
		pwfLoginPassword.clear();
		txfFName.clear();
		txfFName.setPromptText("Enter First Name");
		txfSName.clear();
		txfSName.setPromptText("Enter Surname");
		txfEmail.clear();
		txfEmail.setPromptText("Enter Email Address");
		txfPassword.clear();
		txfPassword.setPromptText("Enter Password");
	}

    /** Main Method */
	public static void main(String[] args) {
		launch(args);
	}
}
