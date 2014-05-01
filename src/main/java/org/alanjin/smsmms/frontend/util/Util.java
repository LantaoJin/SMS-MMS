/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend.util;

import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.dao.MemberDao;
import org.alanjin.smsmms.backend.dao.MemberDaoImpl;

/**
 *
 * @author Administrator
 */
public class Util {
    public static void fillModelTable(JTable modelTable, List<MessageModel> messageModels) {
        Object[][] content = new Object[messageModels.size()][6];
        for(int i = 0; i<messageModels.size(); i++) {
            content[i] = new Object[8];
            content[i][0] = messageModels.get(i).getId();
            content[i][1] = messageModels.get(i).getModelName();
            content[i][2] = messageModels.get(i).isUseHead()?"是":"否";
            content[i][3] = messageModels.get(i).getTitle();
            content[i][4] = messageModels.get(i).getContent();
            content[i][5] = messageModels.get(i).getDescription();
        }
        
        String[] columnIdentifiers = new String [] {
            "编号", "模版名称", "使用抬头", "后接称谓", "内容", "说明"
        };
        DefaultTableModel model = new DefaultTableModel(content, columnIdentifiers);
        model.setDataVector(content, columnIdentifiers);
        modelTable.setModel(model);
        modelTable.getTableHeader().setFont(new Font("宋体", 0, 14));
        TableColumn column1 = modelTable.getColumnModel().getColumn(0);
        TableColumn column2 = modelTable.getColumnModel().getColumn(1);
        TableColumn column3 = modelTable.getColumnModel().getColumn(2);
        TableColumn column4 = modelTable.getColumnModel().getColumn(3);
        TableColumn column6 = modelTable.getColumnModel().getColumn(5);
        column1.setPreferredWidth(50);
        column1.setMaxWidth(50);
        column2.setPreferredWidth(120);
        column2.setMaxWidth(120);
        column3.setPreferredWidth(80);
        column3.setMaxWidth(80);
        column4.setPreferredWidth(80);
        column4.setMaxWidth(80);
        column6.setPreferredWidth(200);
        column6.setMaxWidth(200);
    }
    
    public static void fillMemberTable(JTable memberTable, List<Member> members) {
        Object[][] content = new Object[members.size()][8];
        for(int i = 0; i<members.size(); i++) {
            content[i] = new Object[8];
            content[i][1] = members.get(i).getMemId();
            content[i][2] = members.get(i).getName();
            content[i][3] = members.get(i).getGender()==1?"男":"女";
            content[i][4] = members.get(i).getPhone();
            content[i][5] = members.get(i).getBirthday();
            content[i][6] = members.get(i).getJoinDate();
            content[i][7] = members.get(i).getFeeSum();
        }

        DefaultTableModel model = new DefaultTableModel() {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            @Override
            public Class getColumnClass(int c)	{
                Object value = getValueAt(0, c);
                if(value!=null)
                return value.getClass();
                else return super.getClass();
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnIdentifiers = new String [] {
                "select", "会员号", "姓名", "性别", "手机号", "出生日期", "入会日期", "累计会费"
        };
        model.setDataVector(content, columnIdentifiers);
        memberTable.setModel(model);
        memberTable.getTableHeader().setFont(new Font("宋体", 0, 14));
    }
    
    public static void verifyAlert(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.CLOSED_OPTION);
    }

    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    
    public static boolean isEmail(String mobiles){
        Pattern p = Pattern.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    
    public static boolean isDigit(String digit){
        Pattern p = Pattern.compile("[0-9]+(.[0-9]+)?");
        Matcher m = p.matcher(digit);
        return m.matches();
    }
    
    public static String preGoodNum(int i) {
        String tmp = Integer.toString(--i);
        int place = tmp.length();
        while(tmp.indexOf('4') != -1) {
            int fact = place - tmp.indexOf('4') - 1;//1-0-1=0(4), 3-0-1=2(401), 3-2-1=0(224)
            i = i - (int)Math.pow(10, fact);
            tmp = Integer.toString(i);
        }
        for (int j = 0; j < 6 - place; j++) {
            tmp = "0" + tmp;
        }
        return tmp;
    }
    
    public static String nextGoodNum(int i) {
        String tmp = Integer.toString(++i);
        int place = tmp.length();
        while(tmp.indexOf('4') != -1) {
            int fact = place - tmp.indexOf('4') - 1;//1-0-1=0(4), 3-0-1=2(401), 3-2-1=0(224)
            i = i + (int)Math.pow(10, fact);
            tmp = Integer.toString(i);
        }
        for (int j = 0; j < 6 - place; j++) {
            tmp = "0" + tmp;
        }
        return tmp;
    }
    
    public static Date getNextYearFromNow() {
        return getDateFromDate(new Date(), Calendar.YEAR, 1);
    }
    
    public static Date getNextMonthFromDate(Date date) {
        return getDateFromDate(date, Calendar.MONTH, 1);
    }
    
    public static Date getDateFromDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }
    
    public static final String HOME_SHOW    = "【显示首页】：首页上显示了当天是有哪些会员的生日,已经收到的短信提醒。\n\n"
                + "（本版本尚未添加该功能），所以【显示首页】的作用主要是使操作员返回到一个安全的位置。";
    public static final String HOME_PRINT   = "【打印】：连接打印机后，进行会员信息的打印和会员列表的打印（本版本尚未添加该功能）。";
    
    public static void main(String[] args) throws IOException {
        System.out.println(Util.isMobileNO("15921778090"));
        System.out.println(Util.isMobileNO("1592177090"));
        System.out.println(Util.isEmail("w232@dfd.dsfd"));
        System.out.println(Util.isEmail("232@dfd.ds. fd"));
        System.out.println(Util.isDigit("23.03"));
        System.out.println(Util.isDigit("1dsa.1e"));
        
        String tmp = "1234";
        System.out.println(tmp.lastIndexOf('4'));
        System.out.println(tmp.indexOf('4'));
        
        System.out.println(Util.nextGoodNum(3));
        System.out.println(Util.nextGoodNum(13));
        System.out.println(Util.nextGoodNum(33));
        System.out.println(Util.nextGoodNum(39));
        System.out.println(Util.nextGoodNum(339));
        System.out.println(Util.nextGoodNum(399));
        System.out.println(Util.nextGoodNum(3999));
        
        System.out.println(Integer.parseInt("000011"));
    }
}
