package examenUD2;

import javax.xml.transform.Result;
import java.sql.*;

public class Examen2 {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Por favor, introduzca argumentos");
			System.exit(-1);
		}

		int cmd = 0;

		try {
			cmd = Integer.parseInt(args[0]);

			if (cmd <= 0 || cmd >= 3) {
				System.err.println("Argumento numérico entre 1 y 2");
				System.exit(-1);
			}

		} catch (NumberFormatException e) {
			System.err.println("Por favor, introduzca un valor numérico (1 o 2)");
			System.exit(-1);
		}

		String[] queryClientes = {
				"INSERT INTO CLIENTES VALUES(5, 'PILAR MARTÍN', 'C/Félix Fernández 12', 'Cáceres', '927229988', '11223434L')",
				"INSERT INTO CLIENTES VALUES(6, 'FERNANDO GARCÍA', 'C/Serrano 25', 'Madrid', '916754554', '88004320E')",
				"INSERT INTO CLIENTES VALUES(7, 'PEDRO AGUILAR', 'Av Valdecañas 1', 'Navalmoral', '927908790', '4133219J')" };

		String[] queryProductos = { "INSERT INTO PRODUCTOS VALUES(1, 'Cubo de basura rojo 20 litros', 20, 2, 12.00)",
				"INSERT INTO PRODUCTOS VALUES(2, '200 lapices Staedtler', 15, 2, 15.25)",
				"INSERT INTO PRODUCTOS VALUES(3, 'Flamenco chill in, 2 discos', 14, 1, 8.00)" };
//
//		String[] queryVENTAS = { "INSERT INTO ventas VALUES (1, '2023-07-16', 5, 1, 3);",
//				"INSERT INTO ventas VALUES (2, '2023-07-17', 6, 2, 2);",
//				"INSERT INTO ventas VALUES (3, '2023-07-19', 5, 2, 1);",
//				"INSERT INTO ventas VALUES (4, '2023-08-20', 7, 3, 5);",
//				"INSERT INTO ventas VALUES (5, '2023-08-22', 5, 3, 1);" };

		try {
			Connection con = DriverManager.getConnection(ConfigData.DATABASE, ConfigData.USER, ConfigData.PASS);

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			switch (cmd) {
			case 1:
				executeQueries(stmt, queryClientes);
				break;
			case 2:
				executeQueries(stmt, queryProductos);
				break;
			}

			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.err.println("Hubo un error durante la ejecución del programa");
			e.printStackTrace();
		}
	}

	private static void executeQueries(Statement stmt, String[] queries) throws SQLException {
		int rows = 0;
		for (String query : queries) {
			rows += stmt.executeUpdate(query);
			
			String[] inserted = query.replaceAll("(", " ").replaceAll(")", "").split(" ");
			
			for(int i = 4; i < inserted.length; i++) {
				System.out.println("Datos insertados: " +  inserted[i]);
			}
			
		}
		System.out.printf("Filas afectadas: %d%n", rows);
	}

}
