package org.alanjin.smsmms.backend.dao;

import java.sql.SQLException;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;

public interface MemberDao {
	public void insertMember(Member member) throws SQLException;
	public void deleteMember(int id) throws SQLException;
	public Member selectMember(int id)throws SQLException;
	public void updateMember(Member member) throws SQLException;
	public List<Member> getAllMembers() throws SQLException;
	public List<Member> getMembersByBirthday(String birthday) throws SQLException;
	public int count() throws SQLException;
}
