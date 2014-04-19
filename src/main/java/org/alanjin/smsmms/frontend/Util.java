/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.alanjin.smsmms.backend.bean.Member;

/**
 *
 * @author Administrator
 */
public class Util {
    public static void fillMemberTable(JTable memberTable, List<Member> members) {
        if (members.size() == 0) return;
        Object[][] content = new Object[members.size()][8];
        for(int i = 0; i<members.size(); i++) {
            content[i] = new Object[8];
            content[i][1] = members.get(i).getMemId();
            content[i][2] = members.get(i).getName();
            content[i][3] = members.get(i).getGender();
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
        };
        String[] columnIdentifiers = new String [] {
                "select", "会员号", "姓名", "性别", "手机号", "出生日期", "入会日期", "累计会费"
        };
        model.setDataVector(content, columnIdentifiers);
        memberTable.setModel(model);
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

    public static void main(String[] args) throws IOException {
        System.out.println(Util.isMobileNO("15921778090"));
        System.out.println(Util.isMobileNO("1592177090"));
        System.out.println(Util.isEmail("w232@dfd.dsfd"));
        System.out.println(Util.isEmail("232@dfd.ds. fd"));
        System.out.println(Util.isDigit("23.03"));
        System.out.println(Util.isDigit("1dsa.1e"));
    }
}
