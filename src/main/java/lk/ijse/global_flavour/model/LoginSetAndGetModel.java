package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.LoginSetAndGet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSetAndGetModel {
    public static LoginSetAndGet search(String userName) throws SQLException {
        String sql = "SELECT * FROM user WHERE userName = ?";
        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, userName);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                return new LoginSetAndGet(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }
            return null;
        }
    }
}
