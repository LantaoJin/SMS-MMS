package org.alanjin.smsmms.backend.dao;

import java.sql.SQLException;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Receipt;

public interface ReceiptDao {
	public void insertReceipt(Receipt receipt) throws SQLException;
	public Receipt selectReceipt(int id) throws SQLException;
	public void updateReceipt(Receipt receipt) throws SQLException;
	public void deleteReceipt(int id) throws SQLException;
	public List<Receipt> getAllReceiptsByMemberId(String MemberId) throws SQLException;
	public int count() throws SQLException;
}
