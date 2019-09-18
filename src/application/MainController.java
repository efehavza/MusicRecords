package application;

import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnFind;
	@FXML
	private Button btnList;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnExit;

	public void initialize() {

		btnAdd.setOnAction(e -> {
			try {
				onAddClicked();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnFind.setOnAction(e -> {
			try {
				onFindClicked();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnList.setOnAction(e -> {
			try {
				onListClicked();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnDelete.setOnAction(e -> {
			try {
				onDeleteClicked();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		btnExit.setOnAction(e -> onExitClicked());
	}

	public void onAddClicked() throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddRecord.fxml"));
			Parent p = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFindClicked() throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FindRecord.fxml"));
			Parent p = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onListClicked() throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRecords.fxml"));
			Parent p = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDeleteClicked() throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRecord.fxml"));
			Parent p = loader.load();

			Stage stage = new Stage();
			stage.setScene(new Scene(p));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onExitClicked() {
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Exit?");
		alert.setHeaderText("Are you sure you want to exit?");
		ButtonType btnYes = new ButtonType("Yes");
		ButtonType btnNo = new ButtonType("No");

		alert.getButtonTypes().setAll(btnYes, btnNo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == btnYes) {
			Platform.exit();
			System.exit(0);
		}
	}

}
