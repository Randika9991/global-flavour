package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import lk.ijse.global_flavour.dto.UserCreateAcountSetAndGet;

public class UserCreateAcountSetAndGetModel {

    public static boolean save(UserCreateAcountSetAndGet userCreate) throws SQLException {  //data baes ekata dana set eka

        String sql = "INSERT INTO user(userName,empId,password, email,jobTitle) " +
                "VALUES(?,?, ?, ?, ?)";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, userCreate.getName());
            pstm.setString(2,null);
            pstm.setString(3, userCreate.getPassword());
            pstm.setString(4, userCreate.getEmail());
            pstm.setString(5, userCreate.getJobTitle());

            return pstm.executeUpdate() > 0;
        }
    }
}
