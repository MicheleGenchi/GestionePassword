package application.model;

public class DAOFactory {

	public static DAO get(String tipo) {
		switch (tipo) {
		case "mysql": return new MysqlDAO<Password>();
		}
		return null;
	}

}
