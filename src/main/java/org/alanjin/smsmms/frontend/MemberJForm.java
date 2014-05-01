package org.alanjin.smsmms.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.alanjin.smsmms.frontend.util.Util;

import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.service.MemberAction;

/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */

/**
 * @author AlanJin
 */
public class MemberJForm extends JPanel {
    private static final String TITLE = "新增会员";
    private MemberAction memberAction;
    private java.util.Date nextYearDate;
    private java.util.Date nextYearNextMonthDate;

    public MemberJForm(MemberAction memberAction) {
        this.memberAction = memberAction;
        this.nextYearDate = Util.getNextYearFromNow();
        this.nextYearNextMonthDate = Util.getNextMonthFromDate(nextYearDate);
        initComponents();
    }

    private void addMemberActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (basicCheck()) {
            String memId = null;
            try {
                memId = memberAction.generateMemberId();
            } catch (SQLException ex) {
                Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            int sex = radioButton1.isSelected() ? 1 : 0;
            java.sql.Date birthDate = null;
            java.sql.Date joinDate = null;
            java.sql.Date disableDate = null;
            java.sql.Date lastDate = null;
            try {
                birthDate = org.alanjin.smsmms.backend.util.Util
                        .toSQLDate(birthday.getText());
                joinDate = org.alanjin.smsmms.backend.util.Util
                        .toSQLDate(joinday.getText());
                disableDate = org.alanjin.smsmms.backend.util.Util
                        .toSQLDate(disableday.getText());
                lastDate = org.alanjin.smsmms.backend.util.Util
                        .toSQLDate(lastday.getText());
            } catch (ParseException ex) {
                Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            BigDecimal feeSum = new BigDecimal(textFee.getText().trim());
            Member m = new Member(memId, textName.getText().trim(), sex,
                    birthDate, joinDate, feeSum);
            m.setAddress(this.textAddress.getText().trim());
            m.setDisableDate(disableDate);
            m.setEdu((String) this.eduComboBox.getSelectedItem());
            m.setEmail(this.textEmail.getText().trim());
            m.setExpert("");
            m.setIndustry(this.textIndustry.getText().trim());
            m.setLastDate(lastDate);
            m.setTel(this.textTel.getText().trim());
            m.setPhone(this.textPhone.getText().trim());
            m.setTitle(this.textTitle.getText().trim());
            m.setZip(this.textZip.getText().trim());
            System.out.println(m.toString());
            List<Receipt> receipts;
            if ((receipts = m.getReceiptList()) == null) {
                receipts = new ArrayList<Receipt>();
                m.setReceiptList(receipts);
            }
            Receipt r = new Receipt(textReceipt.getText().trim(), feeSum,
                    joinDate);
            r.setMemId(memId);
            r.setAttnName(textAttnName.getText().trim());
            r.setDescription(this.textDescription.getText().trim());
            receipts.add(r);
            m.setReceiptList(receipts);

            if (JOptionPane.showConfirmDialog(null, "确定保存？", TITLE,
                    JOptionPane.OK_CANCEL_OPTION) == 0) {
                //TODO
                memberAction.addMember(m);
                this.confirmAddJButton.setEnabled(false);
                this.resetAddJButton.setEnabled(false);
                this.nextAddJButton.setEnabled(true);
                this.memIdJTextField.setText(memId);
                this.successJLabel.setText("保存成功！请记住会员号。");
            } else {

            }
        }
    }

    private void clearAllComponent() {
        this.nextYearDate = Util.getNextYearFromNow();
        this.nextYearNextMonthDate = Util.getNextMonthFromDate(nextYearDate);
        memIdJTextField.setText("");
        textName.setText("");
        birthday = new DatePicker(new java.util.Date(0),
                org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
        textZip.setText("");
        textAddress.setText("");
        textTel.setText("");
        textPhone.setText("");
        textEmail.setText("");
        eduComboBox.setSelectedIndex(0);
        textIndustry.setText("");
        textTitle.setText("");
        textFee.setText("");
        textReceipt.setText("");
        textAttnName.setText("");
        textDescription.setText("");
        successJLabel.setText("");
    }

    private boolean basicCheck() {
        if (textName.getText().trim().equals("")
                || textAttnName.getText().trim().equals("")) {
            Util.verifyAlert("姓名|经办人员  不得为空", TITLE);
            return false;
        } else if (!textPhone.getText().trim().isEmpty()
                &&!Util.isMobileNO(textPhone.getText().trim())) {
            Util.verifyAlert("手机号格式不对", TITLE);
            return false;
        } else if (!textEmail.getText().trim().isEmpty()
                && !Util.isEmail(textEmail.getText().trim())) {
            Util.verifyAlert("电子邮箱格式不对", TITLE);
            return false;
        } else if (!Util.isDigit(textFee.getText().trim())) {
            Util.verifyAlert("会费必须是有效金额数字", TITLE);
            return false;
        }
        return true;
    }

    private void nextAddJButtonActionPerformed(ActionEvent e) {
        clearAllComponent();
        this.nextAddJButton.setEnabled(false);
        this.confirmAddJButton.setEnabled(true);
        this.resetAddJButton.setEnabled(true);
    }

    private void confirmAddJButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void resetAddJButtonActionPerformed(ActionEvent e) {
        clearAllComponent();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jin Lantao
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        memIdJTextField = new JTextField();
        successJLabel = new JLabel();
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
        textZip = new JTextField();
        label7 = new JLabel();
        textAddress = new JTextField();
        label8 = new JLabel();
        textTel = new JTextField();
        label10 = new JLabel();
        textPhone = new JTextField();
        label9 = new JLabel();
        textEmail = new JTextField();
        label11 = new JLabel();
        eduComboBox = new JComboBox();
        label12 = new JLabel();
        textIndustry = new JTextField();
        label13 = new JLabel();
        textTitle = new JTextField();
        label21 = new JLabel();
        textNote = new JTextField();
        panel4 = new JPanel();
        label14 = new JLabel();
        joinday = new DatePicker(new java.util.Date(), org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
        label15 = new JLabel();
        lastday = new DatePicker(nextYearDate, org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
        label16 = new JLabel();
        disableday = new DatePicker(nextYearNextMonthDate, org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
        label17 = new JLabel();
        textFee = new JTextField();
        label18 = new JLabel();
        textReceipt = new JTextField();
        label19 = new JLabel();
        textAttnName = new JTextField();
        label20 = new JLabel();
        textDescription = new JTextField();
        panel5 = new JPanel();
        confirmAddJButton = new JButton();
        resetAddJButton = new JButton();
        nextAddJButton = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(800, 550));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "65dlu, default",
            "20dlu, $lgap, default, $lgap, 15dlu, $lgap, 135dlu, 10dlu, default, 92dlu, 10dlu, default"));

        //---- label1 ----
        label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u4f1a\u5458\u767b\u8bb0\u8868");
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, CC.xy(2, 3));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "54dlu, $lcgap, 99dlu, $lcgap, default:grow",
                "default"));

            //---- label2 ----
            label2.setText("\u7f16    \u53f7");
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(label2, CC.xy(1, 1));

            //---- memIdJTextField ----
            memIdJTextField.setEnabled(false);
            memIdJTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(memIdJTextField, CC.xy(3, 1));

            //---- successJLabel ----
            successJLabel.setForeground(Color.red);
            successJLabel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(successJLabel, CC.xy(5, 1));
        }
        add(panel1, CC.xy(2, 5));

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
            panel2.setLayout(new FormLayout(
                "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                "7*(default, 5dlu)"));

            //---- label3 ----
            label3.setText("\u59d3    \u540d");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label3, CC.xy(1, 1));

            //---- textName ----
            textName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textName, CC.xy(3, 1));

            //---- label4 ----
            label4.setText("\u6027    \u522b");
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label4, CC.xy(5, 1));

            //======== panel3 ========
            {
                panel3.setLayout(new FormLayout(
                    "default, $lcgap, default",
                    "default"));

                //---- radioButton1 ----
                radioButton1.setText("\u7537");
                radioButton1.setSelected(true);
                radioButton1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel3.add(radioButton1, CC.xy(1, 1));

                //---- radioButton2 ----
                radioButton2.setText("\u5973");
                radioButton2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel3.add(radioButton2, CC.xy(3, 1));
            }
            panel2.add(panel3, CC.xy(7, 1));

            //---- label6 ----
            label6.setText("\u51fa\u751f\u65e5\u671f");
            label6.setHorizontalAlignment(SwingConstants.CENTER);
            label6.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label6, CC.xy(1, 3));
            panel2.add(birthday, CC.xy(3, 3));

            //---- label5 ----
            label5.setText("\u90ae    \u7f16");
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            label5.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label5, CC.xy(5, 3));

            //---- textZip ----
            textZip.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textZip, CC.xy(7, 3));

            //---- label7 ----
            label7.setText("\u5730    \u5740");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            label7.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label7, CC.xy(1, 5));

            //---- textAddress ----
            textAddress.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textAddress, CC.xywh(3, 5, 5, 1));

            //---- label8 ----
            label8.setText("\u56fa    \u8bdd");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label8, CC.xy(1, 7));

            //---- textTel ----
            textTel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTel, CC.xy(3, 7));

            //---- label10 ----
            label10.setText("\u624b    \u673a");
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            label10.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label10, CC.xy(5, 7));

            //---- textPhone ----
            textPhone.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textPhone, CC.xy(7, 7));

            //---- label9 ----
            label9.setText("\u7535\u5b50\u90ae\u7bb1");
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            label9.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label9, CC.xy(1, 9));

            //---- textEmail ----
            textEmail.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textEmail, CC.xy(3, 9));

            //---- label11 ----
            label11.setText("\u6587\u5316\u7a0b\u5ea6");
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            label11.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label11, CC.xy(5, 9));

            //---- eduComboBox ----
            eduComboBox.setModel(new DefaultComboBoxModel(new String[] {
                "\u521d\u4e2d\u53ca\u4ee5\u4e0b",
                "\u9ad8\u4e2d",
                "\u5927\u4e13",
                "\u672c\u79d1",
                "\u7855\u58eb",
                "\u535a\u58eb"
            }));
            eduComboBox.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(eduComboBox, CC.xy(7, 9));

            //---- label12 ----
            label12.setText("\u884c    \u4e1a");
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            label12.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label12, CC.xy(1, 11));

            //---- textIndustry ----
            textIndustry.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textIndustry, CC.xy(3, 11));

            //---- label13 ----
            label13.setText("\u804c    \u79f0");
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            label13.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label13, CC.xy(5, 11));

            //---- textTitle ----
            textTitle.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTitle, CC.xy(7, 11));

            //---- label21 ----
            label21.setText("\u5907    \u6ce8");
            label21.setHorizontalTextPosition(SwingConstants.LEADING);
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            label21.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label21, CC.xy(1, 13));

            //---- textNote ----
            textNote.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textNote, CC.xywh(3, 13, 5, 1));
        }
        add(panel2, CC.xywh(2, 7, 1, 2));

        //======== panel4 ========
        {
            panel4.setBorder(new TitledBorder(null, "\u4f1a\u5458\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
            panel4.setLayout(new FormLayout(
                "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                "4*(default, 5dlu)"));

            //---- label14 ----
            label14.setText("\u5165\u4f1a\u65e5\u671f");
            label14.setHorizontalAlignment(SwingConstants.CENTER);
            label14.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label14, CC.xy(1, 1));
            panel4.add(joinday, CC.xy(3, 1));

            //---- label15 ----
            label15.setText("\u5165\u4f1a\u5e74\u9650");
            label15.setHorizontalAlignment(SwingConstants.CENTER);
            label15.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label15, CC.xy(5, 1));
            panel4.add(lastday, CC.xy(7, 1));

            //---- label16 ----
            label16.setText("\u7eed\u4f1a\u65f6\u9650");
            label16.setHorizontalAlignment(SwingConstants.CENTER);
            label16.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label16, CC.xy(1, 3));
            panel4.add(disableday, CC.xy(3, 3));

            //---- label17 ----
            label17.setText("\u4f1a\u8d39\u6570\u989d");
            label17.setHorizontalAlignment(SwingConstants.CENTER);
            label17.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label17, CC.xy(5, 3));

            //---- textFee ----
            textFee.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textFee, CC.xy(7, 3));

            //---- label18 ----
            label18.setText("\u6536\u636e\u5355\u53f7");
            label18.setHorizontalAlignment(SwingConstants.CENTER);
            label18.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label18, CC.xy(1, 5));

            //---- textReceipt ----
            textReceipt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textReceipt, CC.xy(3, 5));

            //---- label19 ----
            label19.setText("\u7ecf\u529e\u4eba\u5458");
            label19.setHorizontalAlignment(SwingConstants.CENTER);
            label19.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label19, CC.xy(5, 5));

            //---- textAttnName ----
            textAttnName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textAttnName, CC.xy(7, 5));

            //---- label20 ----
            label20.setText("\u6536\u636e\u8bf4\u660e");
            label20.setHorizontalAlignment(SwingConstants.CENTER);
            label20.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label20, CC.xy(1, 7));

            //---- textDescription ----
            textDescription.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textDescription, CC.xywh(3, 7, 5, 1));
        }
        add(panel4, CC.xy(2, 10, CC.DEFAULT, CC.FILL));

        //======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                "76dlu, $lcgap, 2*(50dlu, 10dlu), $lcgap, 62dlu, $lcgap, default:grow",
                "fill:default"));

            //---- confirmAddJButton ----
            confirmAddJButton.setText("\u786e  \u8ba4");
            confirmAddJButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            confirmAddJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addMemberActionPerformed(e);
                }
            });
            panel5.add(confirmAddJButton, CC.xy(3, 1));

            //---- resetAddJButton ----
            resetAddJButton.setText("\u91cd  \u7f6e");
            resetAddJButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel5.add(resetAddJButton, CC.xy(5, 1));

            //---- nextAddJButton ----
            nextAddJButton.setText("\u7ee7\u7eed\u6dfb\u52a0");
            nextAddJButton.setEnabled(false);
            nextAddJButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            nextAddJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    nextAddJButtonActionPerformed(e);
                }
            });
            panel5.add(nextAddJButton, CC.xy(8, 1));
        }
        add(panel5, CC.xy(2, 12));

        //---- genderGroup ----
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);
        // //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    // //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jin Lantao
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField memIdJTextField;
    private JLabel successJLabel;
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
    private JTextField textZip;
    private JLabel label7;
    private JTextField textAddress;
    private JLabel label8;
    private JTextField textTel;
    private JLabel label10;
    private JTextField textPhone;
    private JLabel label9;
    private JTextField textEmail;
    private JLabel label11;
    private JComboBox eduComboBox;
    private JLabel label12;
    private JTextField textIndustry;
    private JLabel label13;
    private JTextField textTitle;
    private JLabel label21;
    private JTextField textNote;
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
    private JTextField textDescription;
    private JPanel panel5;
    private JButton confirmAddJButton;
    private JButton resetAddJButton;
    private JButton nextAddJButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
