package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ListRecordsController {

	@FXML
	private TextArea txtArea;
	@FXML
	private Button btnCancel;

	@FXML
	private void initialize() {
		try {
			@SuppressWarnings("resource")

			// reading from file
			Scanner ifstream = new Scanner(new File("records.txt")).useDelimiter("\\s+");
			txtArea.setEditable(false);

			while (ifstream.hasNext()) {
				if (ifstream.hasNextLine()) {
					// appending each record to the text area
					txtArea.appendText(ifstream.nextLine() + " ");
					txtArea.appendText("\n");

				} else {
					txtArea.appendText(ifstream.nextLine() + " ");
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("No records in file! Add some records for display.");
		}

		btnCancel.setOnAction(e -> onCancelClicked());
	}

	public void onCancelClicked() {
		txtArea.clear();
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
