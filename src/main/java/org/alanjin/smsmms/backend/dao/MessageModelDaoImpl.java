package org.alanjin.smsmms.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.db.DBConn;

public class MessageModelDaoImpl implements MessageModelDao {

    @Override
    public boolean insertMessageModel(MessageModel model) throws SQLException {
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        con.setAutoCommit(false);
        try {
            String sql = "insert into model (modelname, content, politeness, description) values (?,?,?,?);";
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setString(1, model.getModelName());
            ps2.setString(2, model.getContent());
            ps2.setInt(3, model.isUsePoliteness() ? 1 : 0);
            ps2.setString(4, model.getDescription());
            ps2.executeUpdate();
            ps2.close();
            con.commit();
            con.close();
            System.out.println("committed successfully");
        } catch (Exception e) {
            System.out.println("rolling back");
            con.rollback();
            con.close();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public MessageModel selectMessageModel(int id) throws SQLException {
        String sql = "Select * from model where (id = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet r = ps.executeQuery();
        MessageModel e = new MessageModel();
        if (r != null) {
            if (r.next()) {
                e.setId(r.getInt("id"));
                e.setModelName(r.getString("modelname"));
                e.setContent(r.getString("content"));
                e.setUsePoliteness(r.getInt("politeness") == 1 ? true : false);
                e.setDescription(r.getString("description"));
            }
            r.close();
            ps.close();
            con.close();
        }
        return e;
        // return null;
    }

    @Override
    public MessageModel selectMessageModel(String modelName)
            throws SQLException {
        String sql = "Select * from model where (modelname = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, modelName);

        ResultSet r = ps.executeQuery();
        MessageModel e = new MessageModel();
        if (r != null) {
            if (r.next()) {
                e.setId(r.getInt("id"));
                e.setModelName(r.getString("modelname"));
                e.setContent(r.getString("content"));
                e.setUsePoliteness(r.getInt("politeness") == 1 ? true : false);
                e.setDescription(r.getString("description"));
            }
            r.close();
            ps.close();
            con.close();
        }
        return e;
        // return null;
    }

    @Override
    public boolean updateMessageModel(MessageModel model) throws SQLException {
        String sql = "UPDATE model set content=?, politeness=?, description where (id = ? );";
        System.out.println(sql);
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, model.getContent());
        ps.setInt(2, model.isUsePoliteness() ? 1 : 0);
        ps.setInt(3, model.getId());
        ps.setString(4, model.getDescription());
        ps.executeUpdate();
        ps.close();
        con.close();
        return true;
    }

    @Override
    public void deleteMessageModel(int id) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "Delete from model where (id = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public List<MessageModel> getAllMessageModels() throws SQLException {
        String sql = "Select * from model;";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet r = ps.executeQuery();
        List<MessageModel> modelList = new ArrayList<MessageModel>();
        if (r != null) {
            while (r.next()) {
                MessageModel m = new MessageModel();
                m.setId(r.getInt("id"));
                m.setModelName(r.getString("modelname"));
                m.setContent(r.getString("content"));
                m.setUsePoliteness(r.getInt("politeness") == 1 ? true : false);
                m.setDescription(r.getString("description"));
                modelList.add(m);
            }
            r.close();
            ps.close();
            con.close();
        }
        return modelList;
        // return null;
    }

    @Override
    public int count() throws SQLException {
        String sql = "Select count(*) from model;";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet r = ps.executeQuery();
        int count = 0;
        if (r != null) {
            while (r.next()) {
                count = r.getInt(0);
            }
            r.close();
            ps.close();
            con.close();
        }
        return count;
    }
}
