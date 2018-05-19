package application.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MysqlDAO<T> implements DAO {
	private String sql = "SELECT * FROM password";
	private List<Password> data = new ArrayList<>();
	private Set<String> dataTipo = new TreeSet<>();

	@Override
	public boolean leggi() {
		Connection conn = DBConnector.connectToDb();
		if (conn != null) {
			try (Statement st = conn.createStatement()) {
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					Password record = new Password(rs.getString("tipo"), rs.getString("nome"), rs.getString("utente"),
							rs.getString("password"), rs.getString("sito"), rs.getString("note"));
					data.add(record);
					dataTipo.add(rs.getString("tipo"));
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;

	}

	@Override
	public boolean scrivi(ObservableList<Password> data) {
		return false;
	}

	public String getSql() {
		return sql;
	}

	public ObservableList<Password> getData() {
		leggi();
		return FXCollections.observableArrayList(data);
	}

	public ObservableList<String> getDataTipo() {
		leggi();
		return FXCollections.observableArrayList(dataTipo);
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setData(List<Password> data) {
		this.data = data;
	}

}
