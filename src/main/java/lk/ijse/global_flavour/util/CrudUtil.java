package lk.ijse.global_flavour.util;

import lk.ijse.global_flavour.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql , Object...args) throws SQLException {
                                                                                                                       //prmitive data type walata poduwe bawitha karna ekak
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i + 1), args[i]);

        }
        if (sql.startsWith("SELECT") || sql.startsWith("select")) {
            return (T) pstm.executeQuery();
        }

            return (T) (Boolean) (pstm.executeUpdate() > 0);

    }
}
