package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DeleteRecordController {

	@FXML
	private TextField id;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnSearch;
	@FXML
	private TextArea txtArea;

	Records rec = new Records();
	String _id;

	public void initialize() {

		btnSearch.setOnAction(e -> {
			try {
				onSearchClicked();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnDelete.setOnAction(e -> {
			try {
				onDeleteClicked();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});

		btnCancel.setOnAction(e -> onCancelClicked());

		txtArea.setDisable(true);
		btnDelete.setDisable(true);
	}

	public void onSearchClicked() throws FileNotFoundException, IOException {

		try {

			_id = id.getText();
			rec.setId(_id); //it sets the id which is entered by the user.
			try {

				@SuppressWarnings("resource")
				Scanner ifstream = new Scanner(new File("records.txt")).useDelimiter("\\s+");

				while (ifstream.hasNext()) {

					String match = ifstream.nextLine(); //string match gets the writing line by line.
					txtArea.setDisable(false);

					if (match.contains(_id)) { //if the id entered is in the line of string,

						txtArea.clear(); //text area clears
						txtArea.appendText(match); //if the match string contains the id it puts it to the screen
						txtArea.appendText("\n");
						txtArea.setEditable(false);
						id.clear();
						btnDelete.setDisable(false); //if the song is find, delete button enables
						btnDelete.requestFocus(); //delete button gets the focus

					} else {

						txtArea.appendText(""); //if the match not contain the id put nothing to the text area

					}

					if (txtArea.getText().isEmpty()) { //if the text area is empty, gives a warning.

						txtArea.setText("Record doesn't exist in file!\n");
						txtArea.setEditable(false);
						id.clear();
						id.requestFocus();

					}
				}

			} catch (FileNotFoundException ex) { //if there is no records found, it gives this message.

				System.err.println("No records in file! Add some records for display.");

			}
		} catch (Exception ex) {

			// error messages are displayed if input is not valid
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ex.getMessage());
			alert.showAndWait();

		}

	}

	public void onDeleteClicked() throws FileNotFoundException, IOException {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Exit?");
		alert.setHeaderText("Are you sure you want to delete this record?");
		//buttons for the alert dialog
		ButtonType btnYes = new ButtonType("Yes");
		ButtonType btnNo = new ButtonType("No");

		alert.getButtonTypes().setAll(btnYes, btnNo);

		Optional<ButtonType> result = alert.showAndWait(); //result is given in arrayList

		if (result.get() == btnYes) {

			try {

				BufferedReader file = new BufferedReader(new FileReader("records.txt"));

				String match;

				String line = "";

				while ((match = file.readLine()) != null) { //this while loop reads through the file
					// System.out.println(line);
					if (match.contains(_id)) { //if it finds a match with the given id
						match = ""; //it deletes that line
						txtArea.setText("The record is deleted from file!");
						btnDelete.setDisable(true); //button is disabled again
						id.requestFocus();
					}
					line += match;
				}
				FileOutputStream File = new FileOutputStream("records.txt");
				File.write(line.getBytes());

				file.close();
				File.close();
			} catch (Exception e) {
				System.out.println("Problem reading file.");
			}

		}
	}

	public void onCancelClicked() {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}
}
