package application.control;

import application.model.DAOFactory;
import application.model.MysqlDAO;
import application.model.Password;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController extends Application {
	@FXML BorderPane root;
	@FXML PasswordTableControl<Password> tablePassword;
	@FXML ComboBox<String> cmbTipo;

	@Override
	public void start(Stage primaryStage) throws Exception {

		MysqlDAO<?> dao=(MysqlDAO<?>) DAOFactory.get("mysql");
		dao.setSql("SELECT * FROM password");
		ObservableList<String> dataTipo=dao.getDataTipo();
		cmbTipo=new ComboBox<String>(dataTipo);
		
		PasswordTableControl.injectionMainController(this);
		ObservableList<Password> data=dao.getData();
		tablePassword=new PasswordTableControl<Password>(data, Password.class);
		root=new BorderPane(tablePassword);
		root.setTop(cmbTipo);
		Scene scene=new Scene(root,1500,500);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
