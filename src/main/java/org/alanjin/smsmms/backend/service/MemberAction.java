package org.alanjin.smsmms.backend.service;

import java.sql.SQLException;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.dao.MemberDao;

public class MemberAction {
	private MemberDao memberDao;
	public MemberAction(MemberDao dao) {
		this.memberDao = dao;
	}
	
	public List<Member> getAllMembers() throws SQLException {
		return memberDao.getAllMembers();
	}
	
	public List<Member> getMembersByBirthDay(String birthdayString) throws SQLException {
		return memberDao.getMembersByBirthday(birthdayString);
	}
	
	public Member getMemberById(int id) throws SQLException {
		return memberDao.selectMember(id);
	}
	
	public void modifyMember(Member member) throws SQLException {
		memberDao.updateMember(member);
	}
	
}
