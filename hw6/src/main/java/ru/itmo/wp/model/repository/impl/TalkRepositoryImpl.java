package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.TalkRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalkRepository {

    public TalkRepositoryImpl() {
        super("Talk");
    }

    @Override
    protected Talk toInstance(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return talk;
    }

    @Override
    public void save(Talk talk) {
        saveImpl(talk, "(`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())");
    }

    @Override
    public List<Talk> findAllMessagesWithUser(User user) {
        List<Talk> list = findAllBySqlStatement("WHERE sourceUserId=? OR targetUserId=?",
                user.getId(), user.getId());
        Collections.reverse(list);
        return list;
    }

    @Override
    protected void setStatementParametersForSave(PreparedStatement statement, Talk object, String... args) throws SQLException {
        statement.setLong(1, object.getSourceUserId());
        statement.setLong(2, object.getTargetUserId());
        statement.setString(3, object.getText());
    }
}
