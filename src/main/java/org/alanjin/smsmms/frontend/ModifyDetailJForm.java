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
public class ModifyDetailJForm extends JPanel {
    private static final String TITLE = "修改信息";
    private MemberAction memberAction;
    private Member m;

    public ModifyDetailJForm(MemberAction memberAction, String memId) {
        this.memberAction = memberAction;
        this.m = memberAction.getMemberByMemId(memId);
        initComponents();
        initValue();
    }
    
    private void initValue() {
        fillAllComponent(m);
    }

    private void fillAllComponent(Member m) {
        memIdJTextField.setText(m.getMemId());
        textName.setText(m.getName());
        textZip.setText(m.getZip());
        textAddress.setText(m.getAddress());
        textTel.setText(m.getTel());
        textPhone.setText(m.getPhone());
        textEmail.setText(m.getEmail());
        eduComboBox.setSelectedItem(m.getEdu());
        if (m.getGender() == 1) { radioButton1.setSelected(true);} else {radioButton2.setSelected(true);}
        textIndustry.setText(m.getIndustry());
        textTitle.setText(m.getTitle());
        noteTextField.setText("");//TODO
    }

    private void confirmJButtonActionPerformed(ActionEvent e) {
        if (basicCheck()) {
            int sex = radioButton1.isSelected() ? 1 : 0;
            m.setGender(sex);
            java.sql.Date birthDate = null;
            try {
                birthDate = org.alanjin.smsmms.backend.util.Util
                        .toSQLDate(birthdayPicker.getText());
            } catch (ParseException ex) {
                Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            m.setName(textName.getText().trim());
            m.setBirthday(birthDate);
            m.setAddress(this.textAddress.getText().trim());
            m.setEdu((String) this.eduComboBox.getSelectedItem());
            m.setEmail(this.textEmail.getText().trim());
            m.setExpert("");
            m.setIndustry(this.textIndustry.getText().trim());
            m.setTel(this.textTel.getText().trim());
            m.setPhone(this.textPhone.getText().trim());
            m.setTitle(this.textTitle.getText().trim());
            m.setZip(this.textZip.getText().trim());
            System.out.println(m.toString());

            if (JOptionPane.showConfirmDialog(null, "确定保存？", TITLE,
                    JOptionPane.OK_CANCEL_OPTION) == 0) {
                //TODO
                memberAction.modifyMember(m);
                this.successJLabel.setText("修改成功！");
            } else {

            }
        }
    }

    private boolean basicCheck() {
        if (textName.getText().trim().equals("")
                || textPhone.getText().trim().equals("")) {
            Util.verifyAlert("姓名|手机| 不得为空", TITLE);
            return false;
        } else if (!Util.isMobileNO(textPhone.getText().trim())) {
            Util.verifyAlert("手机号格式不对", TITLE);
            return false;
        } else if (!textEmail.getText().trim().isEmpty()
                && !Util.isEmail(textEmail.getText().trim())) {
            Util.verifyAlert("电子邮箱格式不对", TITLE);
            return false;
        }
        return true;
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
        birthdayPicker = new DatePicker(org.alanjin.smsmms.backend.util.Util.dateConvert(m.getBirthday()),
                        org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
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
        noteTextField = new JTextField();
        panel5 = new JPanel();
        confirmJButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new FormLayout(
            "default",
            "20dlu, $lgap, default, $lgap, 15dlu, $lgap, 135dlu, 10dlu, 2*(default)"));

        //---- label1 ----
        label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u8d44\u6599\u4fee\u6539");
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, CC.xy(1, 3));

        //======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                "35dlu, $lcgap, 80dlu, $lcgap, default:grow",
                "default"));

            //---- label2 ----
            label2.setText("\u7f16\u53f7\uff1a");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2, CC.xy(1, 1));

            //---- memIdJTextField ----
            memIdJTextField.setEnabled(false);
            panel1.add(memIdJTextField, CC.xy(3, 1));

            //---- successJLabel ----
            successJLabel.setForeground(Color.red);
            successJLabel.setFont(successJLabel.getFont().deriveFont(successJLabel.getFont().getStyle() | Font.BOLD));
            panel1.add(successJLabel, CC.xy(5, 1));
        }
        add(panel1, CC.xy(1, 5));

        //======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
            panel2.setLayout(new FormLayout(
                "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                "7*(default, 5dlu)"));

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
            panel2.add(birthdayPicker, CC.xy(3, 3));

            //---- label5 ----
            label5.setText("\u90ae    \u7f16");
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label5, CC.xy(5, 3));
            panel2.add(textZip, CC.xy(7, 3));

            //---- label7 ----
            label7.setText("\u5730    \u5740");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label7, CC.xy(1, 5));
            panel2.add(textAddress, CC.xywh(3, 5, 5, 1));

            //---- label8 ----
            label8.setText("\u56fa    \u8bdd");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label8, CC.xy(1, 7));
            panel2.add(textTel, CC.xy(3, 7));

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

            //---- eduComboBox ----
            eduComboBox.setModel(new DefaultComboBoxModel(new String[] {
                "\u521d\u4e2d\u53ca\u4ee5\u4e0b",
                "\u9ad8\u4e2d",
                "\u5927\u4e13",
                "\u672c\u79d1",
                "\u7855\u58eb",
                "\u535a\u58eb"
            }));
            panel2.add(eduComboBox, CC.xy(7, 9));

            //---- label12 ----
            label12.setText("\u884c    \u4e1a");
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label12, CC.xy(1, 11));
            panel2.add(textIndustry, CC.xy(3, 11));

            //---- label13 ----
            label13.setText("\u804c    \u79f0");
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label13, CC.xy(5, 11));
            panel2.add(textTitle, CC.xy(7, 11));

            //---- label21 ----
            label21.setText("\u5907    \u6ce8");
            label21.setHorizontalTextPosition(SwingConstants.LEADING);
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            panel2.add(label21, CC.xy(1, 13));
            panel2.add(noteTextField, CC.xywh(3, 13, 5, 1));
        }
        add(panel2, CC.xywh(1, 7, 1, 2));

        //======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                "133dlu, $lcgap, 50dlu, $lcgap, default:grow",
                "fill:default"));

            //---- confirmJButton ----
            confirmJButton.setText("\u4fee  \u6539");
            confirmJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmJButtonActionPerformed(e);
                }
            });
            panel5.add(confirmJButton, CC.xy(3, 1));
        }
        add(panel5, CC.xy(1, 10));

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
    private DatePicker birthdayPicker;
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
    private JTextField noteTextField;
    private JPanel panel5;
    private JButton confirmJButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
