package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FindRecordController {

	@FXML
	private TextField name;
	@FXML
	private TextField artist;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnCancel;
	@FXML
	private TextArea txtArea;

	Records rec = new Records();
	String _name, _artist;

	public void initialize() {

		btnSearch.setOnAction(e -> {
			try {
				onSearchClicked();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		btnCancel.setOnAction(e -> onCancelClicked());

		txtArea.setDisable(true);
	}

	public void onSearchClicked() throws FileNotFoundException, IOException {
		try {
			_name = name.getText();
			rec.setName(_name);
			_artist = artist.getText();
			rec.setArtist(_artist);

			try {
				@SuppressWarnings("resource")

				// reading from file
				Scanner ifstream = new Scanner(new File("records.txt")).useDelimiter("\\s+");
				while (ifstream.hasNext()) {
					String match = ifstream.nextLine();
					txtArea.setDisable(false);
					if (match.contains(_name) && match.contains(_artist)) {
						txtArea.clear();
						txtArea.appendText(match);
						txtArea.appendText("\n");
						txtArea.setEditable(false);
						name.clear();
						artist.clear();
					} else {
						txtArea.appendText("");
					}
				}
				if (txtArea.getText().isEmpty()) {
					txtArea.setText("Record doesn't exist in file!\n");
					txtArea.setEditable(false);
					name.clear();
					artist.clear();
					name.requestFocus();
				}
			} catch (FileNotFoundException ex) {
				System.err.println("No records in file! Add some records for display.");
			}
		} catch (Exception ex) {
			// error messages are displayed if input is not valid
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(ex.getMessage());
			alert.showAndWait();
		}
	}

	public void onCancelClicked() {
		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();
	}

}
