package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Password;

public class PasswordTableControl<E> extends TableView<E>  {
	private static MainController main;
	private ObservableList<E> masterData;
	@FXML
	private List<TableColumn<E, String>> columns = new ArrayList<>();

	public PasswordTableControl(ObservableList<E> list, Class<E> class1) {
		masterData = list;
		FilteredList<E> filteredData = new FilteredList<>(masterData);
		Arrays.stream(class1.getDeclaredFields()).forEach(e -> {
			TableColumn<E, String> currentColumn = new TableColumn<E, String>(e.getName());
			PropertyValueFactory<E, String> p=new PropertyValueFactory<E, String>(e.getName());
			currentColumn.setCellValueFactory(p);
			columns.add(currentColumn);
		});
		
		main.cmbTipo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				filteredData.setPredicate(e -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compare tipo di ogni riga con questo filtro
	                String lowerCaseFilter = newValue.toLowerCase();
	                if (((Password) e).getTipo().toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // stesso tipo
	                }
	                return false; // tipo diverso
	            });
			}
		});
		
		this.setItems(filteredData);
		this.getColumns().setAll(columns);
	}

	public static void injectionMainController(MainController mainController) {
		main = mainController;
	}
	
}