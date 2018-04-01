package application.model;

import javafx.collections.ObservableList;

public interface DAO {

	 boolean leggi();
	 boolean scrivi(ObservableList<Password> data);
}
