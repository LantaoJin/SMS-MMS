package org.alanjin.smsmms.backend.dao;

import java.sql.SQLException;
import java.util.List;
import org.alanjin.smsmms.backend.bean.MessageModel;

public interface MessageModelDao {
    public boolean insertMessageModel(MessageModel model) throws SQLException;

    public MessageModel selectMessageModel(int id) throws SQLException;

    public MessageModel selectMessageModel(String modelName)
            throws SQLException;

    public boolean updateMessageModel(MessageModel model) throws SQLException;

    public void deleteMessageModel(int id) throws SQLException;

    public List<MessageModel> getAllMessageModels() throws SQLException;

    public int count() throws SQLException;
}
