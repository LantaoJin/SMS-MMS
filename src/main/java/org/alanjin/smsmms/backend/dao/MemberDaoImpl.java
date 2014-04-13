package org.alanjin.smsmms.backend.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.db.DBConn;

public class MemberDaoImpl implements MemberDao {

	@Override
	public void insertMember(Member member) throws SQLException {
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		try{
			String sql = "insert into member ("
					+ "memId,name,gender,birthday,"
					+ "zip,address,tel,phone,email,"
					+ "edu,industry,title,expert,"
					+ "joindate,lastdate,disabledate,feesum"
					+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getMemId());
			ps.setString(2, member.getName());
			ps.setInt(3, member.getGender());
			ps.setDate(4, member.getBirthday());
			ps.setString(5, member.getZip());
			ps.setString(6, member.getAddress());
			ps.setString(7, member.getTel());
			ps.setString(8, member.getPhone());
			ps.setString(9, member.getEmail());
			ps.setString(10, member.getEdu());
			ps.setString(11, member.getIndustry());
			ps.setString(12, member.getTitle());
			ps.setString(13, member.getExpert());
			ps.setDate(14, member.getJoinDate());
			ps.setDate(15, member.getLastDate());
			ps.setDate(16, member.getDisableDate());
			ps.setBigDecimal(17, member.getFeeSum());
			ps.executeUpdate();
			ps.close();
			
			String sql2 = "insert into receipt (receiptId, memId, money, attnname, createdate, descriptioin) values (?,?,?,?,?,?);";
			for (Receipt receipt : member.getReceiptList()) {
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, receipt.getReceiptId());
				ps2.setString(2, receipt.getMemId());
				ps2.setBigDecimal(3, receipt.getMoney());
				ps2.setString(4, receipt.getAttnName());
				ps2.setDate(5, receipt.getCreateDate());
				ps2.setString(6, receipt.getDescription());
				ps2.executeUpdate();
				ps2.close();
			}
			con.commit();
			con.close();
			System.out.println("committed successfully");
		}catch(Exception e){
			System.out.println("rolling back");
			con.rollback();
			con.close();
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMember(int id) throws SQLException {
		String sql = "Delete from member where (id = ? );";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		con.close();
	}

	@Override
	public Member selectMember(int id) throws SQLException {
		String sql = "Select * from member where (id = ? );";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet r = ps.executeQuery();
		Member e = new Member();
		if(r!=null){
			if(r.next()){
				e.setMemId(r.getString("memId"));
				e.setName(r.getString("name"));
				e.setGender(r.getInt("gender"));
				e.setBirthday(r.getDate("birthday"));
				e.setZip(r.getString("zip"));
				e.setAddress(r.getString("address"));
				e.setTel(r.getString("tel"));
				e.setPhone(r.getString("phone"));
				e.setEmail(r.getString("email"));
				e.setEdu(r.getString("edu"));
				e.setIndustry(r.getString("industry"));
				e.setTitle(r.getString("title"));
				e.setExpert(r.getString("expert"));
				e.setJoinDate(r.getDate("joindate"));
				e.setLastDate(r.getDate("lastdate"));
				e.setDisableDate(r.getDate("disabledate"));
				e.setFeeSum(r.getBigDecimal("feesum"));
			}
			r.close();
			ps.close();
			con.close();
		}
		return e;
		//return null;
	}

	@Override
	public void updateMember(Member member) throws SQLException {
		try{
			String sql = "UPDATE member set name=?, gender=?, birthday=?," +
					"zip=?, address=?, tel=?, phone=?, email=?, edu=?, industry=?," +
					"title=?, expert=?, joindate=?, lastdate=?, disabledate=?, feesum=?" +
					"where (id = ? );";
			System.out.println(sql);
			DBConn db = new DBConn();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setInt(2, member.getGender());
			ps.setDate(3, member.getBirthday());
			ps.setString(4, member.getZip());
			ps.setString(5, member.getAddress());
			ps.setString(6, member.getTel());
			ps.setString(7, member.getPhone());
			ps.setString(8, member.getEmail());
			ps.setString(9, member.getEdu());
			ps.setString(10, member.getIndustry());
			ps.setString(11, member.getTitle());
			ps.setString(12, member.getExpert());
			ps.setDate(13, member.getJoinDate());
			ps.setDate(14, member.getLastDate());
			ps.setDate(15, member.getDisableDate());
			ps.setBigDecimal(16, member.getFeeSum());
			ps.setInt(17, member.getId());
			ps.executeUpdate();
			ps.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> getAllMembers() throws SQLException {
		List<Member> memList = new ArrayList<Member>();
		String sql = "Select * from member;";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet r = ps.executeQuery();
		
		if(r!=null){
			while(r.next()){
				Member e = new Member();
				e.setId(r.getInt("id"));
				e.setMemId(r.getString("memId"));
				e.setName(r.getString("name"));
				e.setGender(r.getInt("gender"));
				e.setBirthday(r.getDate("birthday"));
				e.setZip(r.getString("zip"));
				e.setAddress(r.getString("address"));
				e.setTel(r.getString("tel"));
				e.setPhone(r.getString("phone"));
				e.setEmail(r.getString("email"));
				e.setEdu(r.getString("edu"));
				e.setIndustry(r.getString("industry"));
				e.setTitle(r.getString("title"));
				e.setExpert(r.getString("expert"));
				e.setJoinDate(r.getDate("joindate"));
				e.setLastDate(r.getDate("lastdate"));
				e.setDisableDate(r.getDate("disabledate"));
				e.setFeeSum(r.getBigDecimal("feesum"));
				memList.add(e);
			}
			r.close();
			ps.close();
			con.close();
		}
		return memList;
	}

	@Override
	public int count() throws SQLException {
		String sql = "Select count(*) from member;";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet r = ps.executeQuery();
		int count = 0;
		if(r!=null){
			while(r.next()){
				count = r.getInt(0);
			}
			r.close();
			ps.close();
			con.close();
		}
		return count;
	}

	@Override
	public List<Member> getMembersByBirthday(String birthdayString)
			throws SQLException {
		String sql = "Select * from member where (birthday = ? );";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql);
		List<Member> memList = new ArrayList<Member>();
		Date birthday = null;
		try {
			birthday = org.alanjin.smsmms.backend.util.Util.toSQLDate(birthdayString);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			return memList;
		}
		ps.setDate(1, birthday);
		ResultSet r = ps.executeQuery();
		if(r!=null){
			while(r.next()){
				Member e = new Member();
				e.setMemId(r.getString("memId"));
				e.setName(r.getString("name"));
				e.setGender(r.getInt("gender"));
				e.setBirthday(r.getDate("birthday"));
				e.setZip(r.getString("zip"));
				e.setAddress(r.getString("address"));
				e.setTel(r.getString("tel"));
				e.setPhone(r.getString("phone"));
				e.setEmail(r.getString("email"));
				e.setEdu(r.getString("edu"));
				e.setIndustry(r.getString("industry"));
				e.setTitle(r.getString("title"));
				e.setExpert(r.getString("expert"));
				e.setJoinDate(r.getDate("joindate"));
				e.setLastDate(r.getDate("lastdate"));
				e.setDisableDate(r.getDate("disabledate"));
				e.setFeeSum(r.getBigDecimal("feesum"));
				memList.add(e);
			}
			r.close();
			ps.close();
			con.close();
		}
		return memList;
	}
}