package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class AddRecordController {

	@FXML
	private TextField id;
	@FXML
	private TextField name;
	@FXML
	private TextField artist;
	@FXML
	private TextField genre;
	@FXML
	private TextField year;

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnCancel;

	Records rec = new Records();
	private ArrayList<Records> record = new ArrayList<Records>();
	String _id, _name, _artist, _genre, _year;

	@FXML
	private void initialize() {

		btnAdd.setOnAction(e -> {
			try {
				onAddClicked(); // the handle method for Add button
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});

		btnCancel.setOnAction(e -> onCancelClicked());

	}

	public void onAddClicked() throws IOException {

		try {

			_id = id.getText();
			rec.setId(_id);
			_name = name.getText();
			rec.setName(_name);
			_artist = artist.getText();
			rec.setArtist(_artist);
			_genre = genre.getText();
			rec.setGenre(_genre);
			_year = year.getText();
			rec.setYear(_year);

			record.add(rec); //this adds the rec object to the array list.`

			try {
				// create file at location
				File file = new File("records.txt");
				FileWriter filew = new FileWriter(file, true);
				BufferedWriter bwrite = new BufferedWriter(filew);
				bwrite.write(rec.getId() + ": " + rec.getName() + ", " + rec.getArtist() + "- " + rec.getGenre() + ", "
						+ rec.getYear() + "\n");

				// confirm that contact has been added to file
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Confirmation");
				alert.setHeaderText("Record added to file!");
				alert.showAndWait();

				bwrite.close();
				filew.close();

				id.clear();
				name.clear();
				artist.clear();
				genre.clear();
				year.clear();

				Stage stage = (Stage) btnAdd.getScene().getWindow();
				stage.close();

			} catch (Exception e) {
				System.out.println(e.toString());
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
		
		
		System.out.println("Testing");
	}

}
