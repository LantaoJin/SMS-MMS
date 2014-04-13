package org.alanjin.smsmms.backend.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.dao.MemberDao;

public class MemberAction {
	private MemberDao memberDao;
	public MemberAction(MemberDao dao) {
		this.memberDao = dao;
	}
	
	public List<Member> getAllMembers() {
		try {
			return memberDao.getAllMembers();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new ArrayList<Member>();
		}
	}
	
	public List<Member> getMembersByBirthDay(String birthdayString) {
		try {
			return memberDao.getMembersByBirthday(birthdayString);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return new ArrayList<Member>();
		}
	}
	
	public Member getMemberById(int id) {
		try {
			return memberDao.selectMember(id);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public boolean modifyMember(Member member) {
		try {
			return memberDao.updateMember(member);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean addMember(Member member) {
		try {
			return memberDao.insertMember(member);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public void deleteMemberByIds(List<Integer> ids) {
		for (Integer id : ids) {
			try {
				memberDao.deleteMember(id);
			} catch (SQLException e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
	}
}
