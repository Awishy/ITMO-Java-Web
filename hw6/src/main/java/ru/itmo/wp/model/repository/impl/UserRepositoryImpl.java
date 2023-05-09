package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@SuppressWarnings("SqlNoDataSourceInspection")
public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {

    public UserRepositoryImpl() {
        super( "User");
    }

    @Override
    public User findByLoginOrEmail(String loginOrEmail) {
        return findFirstBySqlStatement("WHERE login=? OR email=?",
                loginOrEmail, loginOrEmail);
    }

    @Override
    public User findByLoginOrEmailAndPasswordSha(String loginOrEmail, String passwordSha) {
        return findFirstBySqlStatement("WHERE (login=? OR email=?) AND passwordSha=?",
                loginOrEmail, loginOrEmail, passwordSha);
    }

    @Override
    protected User toInstance(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    public void save(User user, String passwordSha) {
        saveImpl(user,
                "(`login`, `email`, `passwordSha`, `creationTime`) VALUES (?, ?, ?, NOW())", passwordSha);
    }

    @Override
    protected void setStatementParametersForSave(PreparedStatement statement, User user, String... args) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getEmail());
        statement.setString(3, args[0]);
    }
}
