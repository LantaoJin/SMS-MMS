package org.alanjin.smsmms.backend.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.dao.MessageModelDao;
import org.alanjin.smsmms.backend.dao.MessageModelDaoImpl;

public class MessageService {
    private static MessageService service;
    private static MessageModelDao dao;
    
    private MessageService() {
    }
    
    public static MessageService newInstance() {
        if (service == null) {
            dao = new MessageModelDaoImpl();
            service = new MessageService();
        }
        return service;
    }
    
    public List<MessageModel> getAllMessageModel() {
        try {
            return dao.getAllMessageModels();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<MessageModel>();
        }
    }
    
    public boolean addMessageModel(MessageModel model) {
        try {
            return dao.insertMessageModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
