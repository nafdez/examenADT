package examenUD2;

import java.sql.*;

public class ListadoVentas {

	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("mas argumetnos");
			System.exit(-1);
		}

		try {
			int idCliente = Integer.parseInt(args[0]);

			Connection con = DriverManager.getConnection(ConfigData.DATABASE, ConfigData.USER, ConfigData.PASS);

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM VENTAS WHERE IDCLIENTE=?");
			pstmt.setInt(1, idCliente);

			if (pstmt.execute()) {
				ResultSet rs = pstmt.getResultSet();

				while (rs.next()) {
					System.out.printf("Row %d, ID: %d, Fecha: %s, IDC: %d, IDP: %d, Cantidad: %d%n", rs.getRow(),
							rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
				}
			}

		} catch (SQLException | NumberFormatException e) {
			System.err.println("error");
			e.printStackTrace();
		}

	}

}
