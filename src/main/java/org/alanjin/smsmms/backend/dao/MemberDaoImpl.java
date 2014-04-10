package org.alanjin.smsmms.backend.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.db.DBConn;
import org.alanjin.smsmms.backend.db.SqlHelper;

public class MemberDaoImpl implements MemberDao {

	@Override
	public void insertMember(Member member) throws SQLException {
		// TODO Auto-generated method stub
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		try{
			String sql = "insert into member ("
					+ "memId,name,gender,birthday,"
					+ "zip,address,tel,phone,email,"
					+ "edu,industry,title,expert,"
					+ "joinDate,lastDate,disableDate,feeSum"
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
			String sql3 = "select max(id)as id from member";
			PreparedStatement ps1 = con.prepareStatement(sql3);
			ResultSet r = ps1.executeQuery();
			Integer emp_id = 0;
			if(r!=null){
				while(r.next()){
					emp_id = r.getInt("id");
				}
			}
			r.close();
			ps1.close();
			String sql2 = "insert into address (id, emp_id, address) values (?,?,?);";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,member.getId());
			ps2.setInt(2,  emp_id);
			ps2.setString(3, member.getAddress());
			ps2.executeUpdate();
			ps2.close();
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
	public void deleteMember(String memId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Delete from members where (id = ? );";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		con.close();
	}

	@Override
	public Member selectMember(String memId) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "Select * from members where (id = ? );";
		DBConn db = new DBConn();
		//Database db = new Database();
		//db.setDefaultParameters();
		//db.setConnection(true);
		Connection con=null;
		if(db == null){
			System.out.println("Null database");
			System.exit(1);
		}
		con = db.getConnection();
		if(con == null){
			System.out.println("Null connection");
			System.exit(2);
		}
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet r = ps.executeQuery();
		Member e = new Member();
		if(r!=null){
			if(r.next()){
				e.setId(r.getInt("id"));
				e.setFull_name(r.getString("full_name"));
				e.setGender(r.getString("gender"));
				e.setPhone(r.getString("phone"));
				e.setAddress(r.getString("address"));
				e.setUser_id(r.getInt("dept_id"));
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
		// TODO Auto-generated method stub
		try{
			String sql = "UPDATE `members` set full_name=?, gender=?, phone=?," +
					"address=?, dept_id=? where (id = ? );";
			System.out.println(sql);
			DBConn db = new DBConn();
			Connection con = db.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getFull_name());
			System.out.println("full_name= "+member.getFull_name());
			ps.setString(2, member.getGender());
			System.out.println("gender= "+member.getGender());
			ps.setString(3, member.getPhone());
			System.out.println("phone= "+member.getPhone());
			ps.setString(4, member.getAddress());
			System.out.println("address= "+member.getAddress());
			ps.setInt(5, member.getDept_id());
			System.out.println("dept_id= "+member.getDept_id());
			ps.setInt(6, member.getId());
			System.out.println("id= "+member.getId());
			
			//System.out.println(member.getDept_id());
			ps.executeUpdate();
			//ps.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> getAllMembers() throws SQLException {
		// TODO Auto-generated method stub
		List<Member> empList = new ArrayList<Member>();
		String sql = "Select * from members;";
		DBConn db = new DBConn();
		Connection con = db.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet r = ps.executeQuery();
		
		if(r!=null){
			while(r.next()){
				Member e = new Member();
				e.setId(r.getInt("id"));
				e.setFull_name(r.getString("full_name"));
				e.setGender(r.getString("gender"));
				e.setPhone(r.getString("phone"));
				e.setAddress(r.getString("address"));
				e.setUser_id(r.getInt("dept_id"));
				empList.add(e);
			}
			r.close();
			ps.close();
			con.close();
		}
		return empList;
	}
//	public Integer dept_count() throws SQLException{
//		
//		String sql1 ="{ call dept_count(?) }";
//		//String sql2 ="select @var";
//		DBConn db = new DBConn();
//		Connection con = db.getConnection();
//		CallableStatement ps = con.prepareCall(sql1);
//		//PreparedStatement ps2 = con.prepareStatement(sql1);
//		ps.setString(1, "var");
//		ResultSet rs = ps.executeQuery();
//		Integer count=null;
//		if(rs!=null){
//			if(rs.next()){
//				count = rs.getInt(1);
//			}
//		}
//		con.close();
//		return count;
//	}
}