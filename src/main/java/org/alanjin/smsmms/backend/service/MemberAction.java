package org.alanjin.smsmms.backend.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.dao.MemberDao;
import org.alanjin.smsmms.backend.dao.MemberDaoImpl;
import org.alanjin.smsmms.backend.dao.ReceiptDao;
import org.alanjin.smsmms.backend.dao.ReceiptDaoImpl;
import org.alanjin.smsmms.backend.db.DBConn;
import org.alanjin.smsmms.backend.util.BackendUtil;
import org.alanjin.smsmms.frontend.util.FrontendUtil;

public class MemberAction {
    private static MemberAction action;
    private static MemberDao memberDao;
    private static ReceiptDao receiptDao;

    private MemberAction() {
    }

    public static MemberAction newInstance() {
        if (action == null) {
            DBConn db = new DBConn();
            memberDao = new MemberDaoImpl(db);
            receiptDao = new ReceiptDaoImpl(db);
            action = new MemberAction();
        }
        return action;
    }

    public List<Member> getAllMembers() {
        try {
            return memberDao.getAllMembers();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<Member>();
        }
    }

    public List<Member> getMembersByBirthDayStr(String birthdayString,
            boolean isLunar) {
        String tmp = birthdayString.replace("-", "");
        int beginIndex = 0;
        if (tmp.length() == 8) {
            beginIndex = 4;
        }

        try {
            return memberDao.getMembersByBirthdayStr(tmp.substring(beginIndex),
                    isLunar);
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

    public Member getMemberByMemId(String memId) {
        try {
            return memberDao.selectMember(memId);
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

    public List<Member> getMembersByName(String name) {
        try {
            return memberDao.getMembersByName(name);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<Member>();
        }
    }

    public List<Member> getMembersByPhone(String phone) {
        try {
            return memberDao.getMembersByPhone(phone);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<Member>();
        }
    }

    public List<Member> getMembersBetweenJoinday(String fromStr, String toStr) {
        Date from, to;
        try {
            from = BackendUtil.toSQLDate(fromStr);
            to = BackendUtil.toSQLDate(toStr);
            return memberDao.getMembersBetweenJoinday(from, to);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ArrayList<Member>();
        }
    }

    public List<Member> getMembersBetweenFeesum(String fromStr, String toStr) {
        BigDecimal from, to;
        try {
            from = BigDecimal.valueOf(Double.parseDouble(fromStr));
            to = BigDecimal.valueOf(Double.parseDouble(toStr));
            return memberDao.getMembersBetweenFeesum(from, to);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ArrayList<Member>();
        }
    }

    public boolean addReceipt(Receipt receipt) {
        try {
            return receiptDao.insertReceipt(receipt);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public String generateMemberId() throws SQLException {
        String newest = memberDao.getLastMemberId();
        if (newest == null)
            return "000001";
        int beginNum = Integer.parseInt(newest);
        return FrontendUtil.nextGoodNum(beginNum);
    }
}
