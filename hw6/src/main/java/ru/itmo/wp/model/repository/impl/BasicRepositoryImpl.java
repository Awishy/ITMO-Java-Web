package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.AbstractDomain;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.BasicRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicRepositoryImpl<T extends AbstractDomain> implements BasicRepository<T> {
    protected final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();
    protected final String fieldName;

    protected BasicRepositoryImpl(String fieldName) {
        this.fieldName = fieldName;
    }

    public T find(long id) {
        return findFirstBySqlStatement("WHERE id=?", id);
    }

    public List<T> findAll() {
        return findAllBySqlStatement("ORDER BY id DESC");
    }

    protected T findFirstBySqlStatement(String sqlStatement, Object... args) {
        List<T> all = findAllBySqlStatement(sqlStatement, args);
        return all.size() > 0 ? all.get(0) : null;
    }

    public List<T> findAllBySqlStatement(String sqlStatement, Object... args) {
        List<T> list = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + fieldName + " " + sqlStatement)) {
                for (int i = 0; i < args.length; ++i) {
                    if (args[i].getClass() == Long.class) {
                        statement.setLong(i + 1, (Long) args[i]);
                    } else if (args[i].getClass() == String.class) {
                        statement.setString(i + 1, (String) args[i]);
                    } else {
                        throw new RepositoryException("Expected Long or String arguments in SQL statement.");
                    }
                }
                try (ResultSet resultSet = statement.executeQuery()) {
                    T object;
                    while ((object = toInstance(statement.getMetaData(), resultSet)) != null) {
                        list.add(object);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find object.", e);
        }
        return list;
    }

    public int findCount() {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + fieldName)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find object.", e);
        }
        return 0;
    }

    protected abstract T toInstance(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;

    protected void saveImpl(T object, String sqlStatement, String... args) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO `" + fieldName +  "` " + sqlStatement,
                    Statement.RETURN_GENERATED_KEYS
            )) {
                setStatementParametersForSave(statement, object, args);
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't save object.");
                } else {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        object.setId(generatedKeys.getLong(1));
                        object.setCreationTime(find(object.getId()).getCreationTime());
                    } else {
                        throw new RepositoryException("Can't save object [no autogenerated fields].");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save object.", e);
        }
    }

    protected abstract void setStatementParametersForSave(PreparedStatement statement, T object, String... args)
            throws SQLException;
}
