package org.alanjin.smsmms.frontend;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.alanjin.smsmms.frontend.Util;

import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */



/**
 * @author AlanJin
 */
public class MemberJForm extends JPanel {
    private static final String TITLE = "新增会员";
	public MemberJForm() {
		initComponents();
	}
        
        private void addMemberActionPerformed(ActionEvent e) {
            // TODO add your code here
            if(textName.getText().trim().equals("") || 
               textPhone.getText().trim().equals("") ||
               textReceipt.getText().trim().equals("") ||
               textAttnName.getText().trim().equals("")) {
                Util.verifyAlert("姓名|手机|收据单号|经办人员 不得为空", TITLE);
            } else if (!Util.isMobileNO(textPhone.getText().trim())) {
                Util.verifyAlert("手机号格式不对", TITLE);
            } else if (!textEmail.getText().trim().isEmpty() && !Util.isEmail(textEmail.getText().trim())) {
                Util.verifyAlert("电子邮箱格式不对", TITLE);
            } else if (!Util.isDigit(textFee.getText().trim())) {
                Util.verifyAlert("会费必须是有效金额数字", TITLE);
            } else {// else if (birthday.ge) 
                String memId = "12345";//TODO 生成会员号
                int sex = radioButton1.isSelected()? 1:0;
                java.sql.Date birthDate = null;
                java.sql.Date joinDate = null;
                try {
                    birthDate = org.alanjin.smsmms.backend.util.Util.toSQLDate(birthday.getText());
                    joinDate = org.alanjin.smsmms.backend.util.Util.toSQLDate(joinday.getText());
                } catch (ParseException ex) {
                    Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                BigDecimal feeSum = new BigDecimal(textFee.getText().trim());
                Member m = new Member(memId, textName.getText().trim(), sex, birthDate, joinDate, feeSum);
                m.setAddress("");
                m.setDisableDate(birthDate);
                m.setEdu(memId);
                m.setEmail(memId);
                m.setExpert("");
                m.setIndustry(memId);
                m.setLastDate(joinDate);
                m.setTel(memId);
                m.setTitle(memId);
                m.setZip(memId);
                List<Receipt> receipts;
                if((receipts = m.getReceiptList()) == null) {
                    receipts = new ArrayList<Receipt>();
                    m.setReceiptList(receipts);
                }
                Receipt r = new Receipt(textReceipt.getText().trim(), feeSum, joinDate);
                r.setAttnName(textAttnName.getText().trim());
                r.setDescription(textField1.getText().trim());
                receipts.add(r);
                m.setReceiptList(receipts);
                
                if(JOptionPane.showConfirmDialog(null, "确定保存？", TITLE, JOptionPane.OK_CANCEL_OPTION) == 0) {
                    //set all disable
                } else {
                    
                }
            }
        }

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
                // Generated using JFormDesigner Evaluation license - Jin Lantao
                label1 = new JLabel();
                panel1 = new JPanel();
                label2 = new JLabel();
                textId = new JTextField();
                panel2 = new JPanel();
                label3 = new JLabel();
                textName = new JTextField();
                label4 = new JLabel();
                panel3 = new JPanel();
                radioButton1 = new JRadioButton();
                radioButton2 = new JRadioButton();
                label6 = new JLabel();
                birthday = new DatePicker(new java.util.Date(0), org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
                label5 = new JLabel();
                textField3 = new JTextField();
                label7 = new JLabel();
                textField5 = new JTextField();
                label8 = new JLabel();
                textField6 = new JTextField();
                label10 = new JLabel();
                textPhone = new JTextField();
                label9 = new JLabel();
                textEmail = new JTextField();
                label11 = new JLabel();
                comboBox1 = new JComboBox();
                label12 = new JLabel();
                textField10 = new JTextField();
                label13 = new JLabel();
                textField11 = new JTextField();
                panel4 = new JPanel();
                label14 = new JLabel();
                joinday = new DatePicker(new java.util.Date(), org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
                label15 = new JLabel();
                lastday = new DatePicker(null, org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
                label16 = new JLabel();
                disableday = new DatePicker(null, org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
                label17 = new JLabel();
                textFee = new JTextField();
                label18 = new JLabel();
                textReceipt = new JTextField();
                label19 = new JLabel();
                textAttnName = new JTextField();
                label20 = new JLabel();
                textField1 = new JTextField();
                panel5 = new JPanel();
                button1 = new JButton();
                button2 = new JButton();

                //======== this ========

                // JFormDesigner evaluation mark
                setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

                setLayout(new FormLayout(
                    "default",
                    "20dlu, $lgap, default, $lgap, 15dlu, $lgap, 128dlu, 10dlu, 92dlu, 10dlu, default"));

                //---- label1 ----
                label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u4f1a\u5458\u767b\u8bb0\u8868");
                label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                add(label1, CC.xy(1, 3));

                //======== panel1 ========
                {
                    panel1.setLayout(new FormLayout(
                        "228dlu, $lcgap, 80dlu",
                        "default"));

                    //---- label2 ----
                    label2.setText("\u7f16\u53f7\uff1a");
                    label2.setHorizontalAlignment(SwingConstants.RIGHT);
                    panel1.add(label2, CC.xy(1, 1));

                    //---- textId ----
                    textId.setEnabled(false);
                    panel1.add(textId, CC.xy(3, 1));
                }
                add(panel1, CC.xy(1, 5));

                //======== panel2 ========
                {
                    panel2.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
                    panel2.setLayout(new FormLayout(
                        "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                        "6*(default, 5dlu)"));

                    //---- label3 ----
                    label3.setText("\u59d3    \u540d");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label3, CC.xy(1, 1));
                    panel2.add(textName, CC.xy(3, 1));

                    //---- label4 ----
                    label4.setText("\u6027    \u522b");
                    label4.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label4, CC.xy(5, 1));

                    //======== panel3 ========
                    {
                        panel3.setLayout(new FormLayout(
                            "default, $lcgap, default",
                            "default"));

                        //---- radioButton1 ----
                        radioButton1.setText("\u7537");
                        radioButton1.setSelected(true);
                        panel3.add(radioButton1, CC.xy(1, 1));

                        //---- radioButton2 ----
                        radioButton2.setText("\u5973");
                        panel3.add(radioButton2, CC.xy(3, 1));
                    }
                    panel2.add(panel3, CC.xy(7, 1));

                    //---- label6 ----
                    label6.setText("\u51fa\u751f\u65e5\u671f");
                    label6.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label6, CC.xy(1, 3));
                    panel2.add(birthday, CC.xy(3, 3));

                    //---- label5 ----
                    label5.setText("\u90ae    \u7f16");
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label5, CC.xy(5, 3));
                    panel2.add(textField3, CC.xy(7, 3));

                    //---- label7 ----
                    label7.setText("\u5730    \u5740");
                    label7.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label7, CC.xy(1, 5));
                    panel2.add(textField5, CC.xywh(3, 5, 5, 1));

                    //---- label8 ----
                    label8.setText("\u56fa    \u8bdd");
                    label8.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label8, CC.xy(1, 7));
                    panel2.add(textField6, CC.xy(3, 7));

                    //---- label10 ----
                    label10.setText("\u624b    \u673a");
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label10, CC.xy(5, 7));
                    panel2.add(textPhone, CC.xy(7, 7));

                    //---- label9 ----
                    label9.setText("\u7535\u5b50\u90ae\u7bb1");
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label9, CC.xy(1, 9));
                    panel2.add(textEmail, CC.xy(3, 9));

                    //---- label11 ----
                    label11.setText("\u6587\u5316\u7a0b\u5ea6");
                    label11.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label11, CC.xy(5, 9));

                    //---- comboBox1 ----
                    comboBox1.setModel(new DefaultComboBoxModel(new String[] {
                        "\u521d\u4e2d\u53ca\u4ee5\u4e0b",
                        "\u9ad8\u4e2d",
                        "\u5927\u4e13",
                        "\u672c\u79d1",
                        "\u7855\u58eb",
                        "\u535a\u58eb"
                    }));
                    panel2.add(comboBox1, CC.xy(7, 9));

                    //---- label12 ----
                    label12.setText("\u884c    \u4e1a");
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label12, CC.xy(1, 11));
                    panel2.add(textField10, CC.xy(3, 11));

                    //---- label13 ----
                    label13.setText("\u804c    \u79f0");
                    label13.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label13, CC.xy(5, 11));
                    panel2.add(textField11, CC.xy(7, 11));
                }
                add(panel2, CC.xy(1, 7));

                //======== panel4 ========
                {
                    panel4.setBorder(new TitledBorder(null, "\u4f1a\u5458\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
                    panel4.setLayout(new FormLayout(
                        "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                        "4*(default, 5dlu)"));

                    //---- label14 ----
                    label14.setText("\u5165\u4f1a\u65e5\u671f");
                    label14.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label14, CC.xy(1, 1));
                    panel4.add(joinday, CC.xy(3, 1));

                    //---- label15 ----
                    label15.setText("\u5165\u4f1a\u5e74\u9650");
                    label15.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label15, CC.xy(5, 1));
                    panel4.add(lastday, CC.xy(7, 1));

                    //---- label16 ----
                    label16.setText("\u7eed\u4f1a\u65f6\u9650");
                    label16.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label16, CC.xy(1, 3));
                    panel4.add(disableday, CC.xy(3, 3));

                    //---- label17 ----
                    label17.setText("\u4f1a\u8d39\u6570\u989d");
                    label17.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label17, CC.xy(5, 3));
                    panel4.add(textFee, CC.xy(7, 3));

                    //---- label18 ----
                    label18.setText("\u6536\u636e\u5355\u53f7");
                    label18.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label18, CC.xy(1, 5));
                    panel4.add(textReceipt, CC.xy(3, 5));

                    //---- label19 ----
                    label19.setText("\u7ecf\u529e\u4eba\u5458");
                    label19.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label19, CC.xy(5, 5));
                    panel4.add(textAttnName, CC.xy(7, 5));

                    //---- label20 ----
                    label20.setText("\u6536\u636e\u8bf4\u660e");
                    label20.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label20, CC.xy(1, 7));
                    panel4.add(textField1, CC.xywh(3, 7, 5, 1));
                }
                add(panel4, CC.xy(1, 9, CC.DEFAULT, CC.FILL));

                //======== panel5 ========
                {
                    panel5.setLayout(new FormLayout(
                        "97dlu, $lcgap, 50dlu, 20dlu, 50dlu, $lcgap, default:grow",
                        "fill:default"));

                    //---- button1 ----
                    button1.setText("\u786e  \u8ba4");
                    button1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            addMemberActionPerformed(e);
                        }
                    });
                    panel5.add(button1, CC.xy(3, 1));

                    //---- button2 ----
                    button2.setText("\u91cd  \u7f6e");
                    panel5.add(button2, CC.xy(5, 1));
                }
                add(panel5, CC.xy(1, 11));

                //---- genderGroup ----
                ButtonGroup genderGroup = new ButtonGroup();
                genderGroup.add(radioButton1);
                genderGroup.add(radioButton2);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Jin Lantao
        private JLabel label1;
        private JPanel panel1;
        private JLabel label2;
        private JTextField textId;
        private JPanel panel2;
        private JLabel label3;
        private JTextField textName;
        private JLabel label4;
        private JPanel panel3;
        private JRadioButton radioButton1;
        private JRadioButton radioButton2;
        private JLabel label6;
        private DatePicker birthday;
        private JLabel label5;
        private JTextField textField3;
        private JLabel label7;
        private JTextField textField5;
        private JLabel label8;
        private JTextField textField6;
        private JLabel label10;
        private JTextField textPhone;
        private JLabel label9;
        private JTextField textEmail;
        private JLabel label11;
        private JComboBox comboBox1;
        private JLabel label12;
        private JTextField textField10;
        private JLabel label13;
        private JTextField textField11;
        private JPanel panel4;
        private JLabel label14;
        private DatePicker joinday;
        private JLabel label15;
        private DatePicker lastday;
        private JLabel label16;
        private DatePicker disableday;
        private JLabel label17;
        private JTextField textFee;
        private JLabel label18;
        private JTextField textReceipt;
        private JLabel label19;
        private JTextField textAttnName;
        private JLabel label20;
        private JTextField textField1;
        private JPanel panel5;
        private JButton button1;
        private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
