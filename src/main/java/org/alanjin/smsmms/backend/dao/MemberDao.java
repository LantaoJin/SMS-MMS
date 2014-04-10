package org.alanjin.smsmms.backend.dao;

import java.sql.SQLException;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;

public interface MemberDao {
	public void insertMember(Member member) throws SQLException;
	public void deleteMember(String memId) throws SQLException;
	public Member selectMember(String memId)throws SQLException;
	public void updateMember(Member member) throws SQLException;
	public List<Member> getAllMembers()throws SQLException;
}
