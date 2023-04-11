package lk.ijse.global_flavour.model;

import lk.ijse.global_flavour.db.DBConnection;
import lk.ijse.global_flavour.dto.ChangePassword;
import lk.ijse.global_flavour.dto.Item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangePasswordModel {
    public static boolean update(ChangePassword supAdd) throws SQLException {

        String sql = "UPDATE user SET empId = ?, password = ?, email = ?, jobTitle = ? WHERE userName = ?";

        try (PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql)) {

            pstm.setString(1, supAdd.getEmpid());
            pstm.setString(2, supAdd.getPassword());
            pstm.setString(3, supAdd.getEmail());
            pstm.setString(4, supAdd.getJobtitel());
            pstm.setString(5, supAdd.getUsrname());

            return pstm.executeUpdate() > 0;
        }
    }
}
