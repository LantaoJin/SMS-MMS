package org.alanjin.smsmms.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.db.DBConn;

public class ReceiptDaoImpl implements ReceiptDao {

    @Override
    public boolean insertReceipt(Receipt receipt) throws SQLException {
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        con.setAutoCommit(false);
        try {
            String sql = "insert into receipt (receiptId, memId, money, attnname, createdate, description) values (?,?,?,?,?,?);";
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setString(1, receipt.getReceiptId());
            ps2.setString(2, receipt.getMemId());
            ps2.setBigDecimal(3, receipt.getMoney());
            ps2.setString(4, receipt.getAttnName());
            ps2.setDate(5, receipt.getCreateDate());
            ps2.setString(6, receipt.getDescription());
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
    public Receipt selectReceipt(int id) throws SQLException {
        String sql = "Select * from receipt where (id = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet r = ps.executeQuery();
        Receipt e = new Receipt();
        if (r != null) {
            if (r.next()) {
                e.setId(r.getInt("id"));
                e.setAttnName(r.getString("attnname"));
                e.setCreateDate(r.getDate("createdate"));
                e.setDescription(r.getString("description"));
                e.setMemId(r.getString("memId"));
                e.setMoney(r.getBigDecimal("money"));
                e.setReceiptId(r.getString("receiptId"));
            }
            r.close();
            ps.close();
            con.close();
        }
        return e;
        // return null;
    }

    @Override
    public void updateReceipt(Receipt receipt) throws SQLException {
        throw new SQLException("Update receipt is no support!");
    }

    @Override
    public void deleteReceipt(int id) throws SQLException {
        // TODO Auto-generated method stub
        String sql = "Delete from receipt where (id = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public List<Receipt> getAllReceiptsByMemberId(String memId)
            throws SQLException {
        String sql = "Select * from receipt where (memId = ? );";
        DBConn db = new DBConn();
        Connection con = db.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement(sql);
        ps.setString(1, memId);

        ResultSet r = ps.executeQuery();
        List<Receipt> recList = new ArrayList<Receipt>();
        if (r != null) {
            while (r.next()) {
                Receipt e = new Receipt();
                e.setId(r.getInt("id"));
                e.setAttnName(r.getString("attnname"));
                e.setCreateDate(r.getDate("createdate"));
                e.setDescription(r.getString("description"));
                e.setMemId(r.getString("memId"));
                e.setMoney(r.getBigDecimal("money"));
                e.setReceiptId(r.getString("receiptId"));
                recList.add(e);
            }
            r.close();
            ps.close();
            con.close();
        }
        return recList;
        // return null;
    }

    @Override
    public int count() throws SQLException {
        String sql = "Select count(*) from receipt;";
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
