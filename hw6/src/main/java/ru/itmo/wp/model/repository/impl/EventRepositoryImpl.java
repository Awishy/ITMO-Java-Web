package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.repository.EventRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class EventRepositoryImpl extends BasicRepositoryImpl<Event> implements EventRepository {

    public EventRepositoryImpl() {
        super("Event");
    }

    @Override
    protected Event toInstance(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Event event = new Event();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    event.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    event.setUserId(resultSet.getLong(i));
                    break;
                case "type":
                    event.setType(EventType.valueOf(resultSet.getString(i)));
                    break;
                case "creationTime":
                    event.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return event;
    }

    @Override
    public void save(Event event) {
        saveImpl(event, "(`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())");
    }

    @Override
    protected void setStatementParametersForSave(PreparedStatement statement, Event object, String... args) throws SQLException {
        statement.setLong(1, object.getUserId());
        statement.setString(2, object.getType().name());
    }
}
