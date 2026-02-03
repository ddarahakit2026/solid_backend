package org.example.api.user.repository;

import org.example.api.user.model.LoginDtoReq;
import org.example.api.user.model.LoginDtoRes;
import org.example.api.user.model.SignupDtoReq;
import org.example.api.user.model.SignupDtoRes;

import javax.sql.DataSource;
import java.sql.*;

public class UserJdbcRepository implements UserRepository{
    private final DataSource dataSource;

    public UserJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SignupDtoRes signup(SignupDtoReq req) {
        String sql = "INSERT INTO user(email, name, password) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, req.getEmail());
            ps.setString(2, req.getName());
            ps.setString(3, req.getPassword());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                SignupDtoRes res = new SignupDtoRes(rs.getInt(1), req.getEmail(), req.getName());
                return res;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public LoginDtoRes login(LoginDtoReq req) {
        String sql = "SELECT * FROM user WHERE email=? AND password=?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, req.getEmail());
            ps.setString(2, req.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                LoginDtoRes res = new LoginDtoRes(rs.getInt("idx"),rs.getString("email"), rs.getString("name"));
                return res;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
