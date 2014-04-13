/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend;

import java.util.List;
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
}
