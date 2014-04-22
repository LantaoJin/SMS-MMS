package org.alanjin.smsmms.backend.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;

public interface MemberDao {
	public boolean insertMember(Member member) throws SQLException;
	public boolean deleteMember(int id) throws SQLException;
	public Member selectMember(int id)throws SQLException;
	public boolean updateMember(Member member) throws SQLException;
	public List<Member> getAllMembers() throws SQLException;
	public List<Member> getMembersByBirthday(String birthdaystr) throws SQLException;
	public List<Member> getMembersByName(String name) throws SQLException;
	public int count() throws SQLException;
	public String getLastMemberId() throws SQLException;
	public List<Member> getMembersByPhone(String phone) throws SQLException;
	public List<Member> getMembersBetweenJoinday(Date from, Date to) throws SQLException;
	public List<Member> getMembersBetweenFeesum(BigDecimal from, BigDecimal to) throws SQLException;
}
