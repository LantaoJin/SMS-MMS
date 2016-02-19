package org.alanjin.smsmms.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.alanjin.smsmms.frontend.util.FrontendUtil;

import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.service.MemberAction;
import org.alanjin.smsmms.backend.util.BackendUtil;

/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */

/**
 * @author AlanJin
 */
public class AddFeeJForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "续费";
    private MemberAction memberAction;
    private Member m;

    public AddFeeJForm(MemberAction memberAction, String memId) {
        this.memberAction = memberAction;
        this.m = memberAction.getMemberByMemId(memId);
        if (m != null) {
            initComponents();
            initValue(m);
        }
    }

    private void initValue(Member m) {
        fillAllComponent(m);
    }

    private void fillAllComponent(Member m) {
        memIdJTextField.setText(m.getMemId());
        textName.setText(m.getName());
        joindayTextField.setText(BackendUtil.fromSQLDate(m.getJoinDate()));
        lastdayTextField.setText(BackendUtil.fromSQLDate(m.getLastDate()));
        textIntroducer.setText(m.getIntroducer());
        textAddress.setText(m.getAddress());
        textTel.setText(m.getTel());
        textPhone.setText(m.getPhone());
        if (m.getGender() == 1) {
            radioButton1.setSelected(true);
        } else {
            radioButton2.setSelected(true);
        }
        textFee.setText(m.getFeeSum().toString());
        noteTextField.setText(m.getDescription());
    }

    private void addJButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        if (basicCheck()) {
            java.sql.Date lastDate = null;
            java.sql.Date addFeeDate = null;
            try {
                lastDate = BackendUtil.dateConvert(BackendUtil.getNextNYearFromDate(
                                BackendUtil.dateConvert(m.getLastDate()),
                                this.addYearComboBox.getSelectedIndex() + 1));
                addFeeDate = BackendUtil.toSQLDate(addFeedatePicker.getText());
            } catch (ParseException ex) {
                Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            BigDecimal feeSum = new BigDecimal(addFeeTextField.getText().trim());
            m.setLastDate(lastDate);
            System.out.println(m.toString());
            List<Receipt> receipts;
            if ((receipts = m.getReceiptList()) == null) {
                receipts = new ArrayList<Receipt>();
                m.setReceiptList(receipts);
            }
            Receipt r = new Receipt(textReceipt.getText().trim(), feeSum,
                    addFeeDate);
            r.setMemId(m.getMemId());
            r.setAttnName(attnName.getText().trim());
            r.setDescription(this.textDescription.getText().trim());
            receipts.add(r);
            m.setReceiptList(receipts);
            m.setFeeSum((m.getFeeSum().add(feeSum)));

            if (JOptionPane.showConfirmDialog(null, "确定保存？", TITLE,
                    JOptionPane.OK_CANCEL_OPTION) == 0) {
                this.addJButton.setEnabled(false);
                if (memberAction.modifyMember(m)) {
                    this.textFee.setText(m.getFeeSum().toPlainString());
                    this.successJLabel.setText("续费成功！");
                    this.textFee.setFont(new Font("宋体", Font.BOLD, 14));
                    if (memberAction.addReceipt(r)) {
                        this.noteLabel.setText("续费成功，累积会费已更新显示。");
                    } else {
                        System.out.println("续费在保存收据时发生了错误。");
                        this.noteLabel.setText("续费成功，但收据单未保存，可能是数据库错误。");
                    }
                } else {
                    this.successJLabel.setText("续费失败！");
                    this.noteLabel.setText("续费失败，可能是程序错误或操作不当。");
                }
            } else {

            }
        }
    }

    private boolean basicCheck() {
        if (attnName.getText().trim().equals("")
                || addFeeTextField.getText().trim().equals("")) {
            FrontendUtil.verifyAlert("收据单号|续费金额 不得为空", TITLE);
            return false;
        } else if (!FrontendUtil.isDigit(addFeeTextField.getText().trim())) {
            FrontendUtil.verifyAlert("续费金额必须是有效金额数字", TITLE);
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
        label7 = new JLabel();
        textAddress = new JTextField();
        label8 = new JLabel();
        textTel = new JTextField();
        label10 = new JLabel();
        textPhone = new JTextField();
        label21 = new JLabel();
        noteTextField = new JTextField();
        panel4 = new JPanel();
        label14 = new JLabel();
        joindayTextField = new JTextField();
        label17 = new JLabel();
        textFee = new JTextField();
        label19 = new JLabel();
        lastdayTextField = new JTextField();
        label5 = new JLabel();
        textIntroducer = new JTextField();
        noteLabel = new JLabel();
        label22 = new JLabel();
        addFeeTextField = new JTextField();
        label15 = new JLabel();
        addYearComboBox = new JComboBox();
        label18 = new JLabel();
        textReceipt = new JTextField();
        label23 = new JLabel();
//        addFeedatePicker = new DatePicker(new java.util.Date(),
//                BackendUtil.timeFormatStr, null, null);
        addFeedatePicker = new DatePicker(this, new java.util.Date());
        addFeedatePicker.setPattern(BackendUtil.timeFormatStr);
        label20 = new JLabel();
        attnName = new JTextField();
        label24 = new JLabel();
        textDescription = new JTextField();
        panel5 = new JPanel();
        addJButton = new JButton();

        // ======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(
                        new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation",
                        javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM,
                        new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });

        setLayout(new FormLayout(
                "default",
                "13dlu, $lgap, default, $lgap, 15dlu, $lgap, 89dlu, 10dlu, 137dlu, 10dlu, default"));

        // ---- label1 ----
        label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u7eed\u8d39");
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, CC.xy(1, 3));

        // ======== panel1 ========
        {
            panel1.setLayout(new FormLayout(
                    "35dlu, $lcgap, 80dlu, $lcgap, default:grow", "default"));

            // ---- label2 ----
            label2.setText("\u7f16\u53f7\uff1a");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(label2, CC.xy(1, 1));

            // ---- memIdJTextField ----
            memIdJTextField.setEnabled(false);
            memIdJTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(memIdJTextField, CC.xy(3, 1));

            // ---- successJLabel ----
            successJLabel.setForeground(Color.red);
            successJLabel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel1.add(successJLabel, CC.xy(5, 1));
        }
        add(panel1, CC.xy(1, 5));

        // ======== panel2 ========
        {
            panel2.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f",
                    TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null,
                    Color.gray));
            panel2.setLayout(new FormLayout(
                    "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                    "4*(default, 5dlu)"));

            // ---- label3 ----
            label3.setText("\u59d3    \u540d");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label3, CC.xy(1, 1));

            // ---- textName ----
            textName.setEnabled(false);
            textName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textName, CC.xy(3, 1));

            // ---- label4 ----
            label4.setText("\u6027    \u522b");
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label4, CC.xy(5, 1));

            // ======== panel3 ========
            {
                panel3.setLayout(new FormLayout("default, $lcgap, default",
                        "default"));

                // ---- radioButton1 ----
                radioButton1.setText("\u7537");
                radioButton1.setSelected(true);
                radioButton1.setEnabled(false);
                radioButton1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel3.add(radioButton1, CC.xy(1, 1));

                // ---- radioButton2 ----
                radioButton2.setText("\u5973");
                radioButton2.setEnabled(false);
                radioButton2.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel3.add(radioButton2, CC.xy(3, 1));
            }
            panel2.add(panel3, CC.xy(7, 1));

            // ---- label7 ----
            label7.setText("\u5730    \u5740");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            label7.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label7, CC.xy(1, 3));

            // ---- textAddress ----
            textAddress.setEnabled(false);
            textAddress.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textAddress, CC.xywh(3, 3, 5, 1));

            // ---- label8 ----
            label8.setText("\u56fa    \u8bdd");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label8, CC.xy(1, 5));

            // ---- textTel ----
            textTel.setEnabled(false);
            textTel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTel, CC.xy(3, 5));

            // ---- label10 ----
            label10.setText("\u624b    \u673a");
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            label10.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label10, CC.xy(5, 5));

            // ---- textPhone ----
            textPhone.setEnabled(false);
            textPhone.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textPhone, CC.xy(7, 5));

            // ---- label21 ----
            label21.setText("\u5907    \u6ce8");
            label21.setHorizontalTextPosition(SwingConstants.LEADING);
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            label21.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label21, CC.xy(1, 7));

            // ---- noteTextField ----
            noteTextField.setEnabled(false);
            noteTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(noteTextField, CC.xywh(3, 7, 5, 1));
        }
        add(panel2, CC.xy(1, 7));

        // ======== panel4 ========
        {
            panel4.setBorder(new TitledBorder(null, "\u4f1a\u5458\u4fe1\u606f",
                    TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null,
                    Color.gray));
            panel4.setLayout(new FormLayout(
                    "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                    "4*(default, 5dlu), 12dlu, 2*(5dlu, default)"));

            // ---- label14 ----
            label14.setText("\u5165\u4f1a\u65e5\u671f");
            label14.setHorizontalAlignment(SwingConstants.CENTER);
            label14.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label14, CC.xy(1, 1));

            // ---- joindayTextField ----
            joindayTextField.setEnabled(false);
            joindayTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(joindayTextField, CC.xy(3, 1));

            // ---- label17 ----
            label17.setText("\u7d2f\u79ef\u4f1a\u8d39");
            label17.setHorizontalAlignment(SwingConstants.CENTER);
            label17.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label17, CC.xy(5, 1));

            // ---- textFee ----
            textFee.setEnabled(false);
            textFee.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textFee, CC.xy(7, 1));

            // ---- label19 ----
            label19.setText("\u5230\u671f\u65e5\u671f");
            label19.setHorizontalAlignment(SwingConstants.CENTER);
            label19.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label19, CC.xy(1, 3));

            // ---- lastdayTextField ----
            lastdayTextField.setEditable(false);
            panel4.add(lastdayTextField, CC.xy(3, 3));

            // ---- label5 ----
            label5.setText("\u4ecb\u7ecd\u4eba");
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            label5.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label5, CC.xy(5, 3));

            // ---- textIntroducer ----
            textIntroducer.setEnabled(false);
            textIntroducer.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textIntroducer, CC.xy(7, 3));

            // ---- noteLabel ----
            noteLabel
                    .setText("\u4e0b\u9762\u5185\u5bb9\u8bf7\u8c28\u614e\u586b\u5199\uff0c\u4e00\u7ecf\u586b\u5199\u4e0d\u5f97\u4fee\u6539\uff01");
            noteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noteLabel.setForeground(Color.red);
            noteLabel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(noteLabel, CC.xywh(1, 5, 7, 1));

            // ---- label22 ----
            label22.setText("\u65b0\u589e\u4f1a\u8d39");
            label22.setHorizontalAlignment(SwingConstants.CENTER);
            label22.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label22, CC.xy(1, 7));

            // ---- addFeeTextField ----
            addFeeTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(addFeeTextField, CC.xy(3, 7));

            // ---- label15 ----
            label15.setText("\u7eed\u4f1a\u5e74\u9650");
            label15.setHorizontalAlignment(SwingConstants.CENTER);
            label15.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label15, CC.xy(5, 7));

            // ---- addYearComboBox ----
            addYearComboBox.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            addYearComboBox.setModel(new DefaultComboBoxModel(new String[] {
                    "1\u5e74", "2\u5e74", "3\u5e74", "4\u5e74", "5\u5e74",
                    "6\u5e74", "7\u5e74", "8\u5e74", "9\u5e74", "10\u5e74" }));
            panel4.add(addYearComboBox, CC.xy(7, 7));

            // ---- label18 ----
            label18.setText("\u6536\u636e\u5355\u53f7");
            label18.setHorizontalAlignment(SwingConstants.CENTER);
            label18.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label18, CC.xy(1, 9));

            // ---- textReceipt ----
            textReceipt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textReceipt, CC.xy(3, 9));

            // ---- label23 ----
            label23.setText("\u7eed\u8d39\u65f6\u95f4");
            label23.setHorizontalTextPosition(SwingConstants.LEADING);
            label23.setHorizontalAlignment(SwingConstants.CENTER);
            label23.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label23, CC.xy(5, 9));
            panel4.add(addFeedatePicker, CC.xy(7, 9));

            // ---- label20 ----
            label20.setText("\u7ecf\u529e\u4eba");
            label20.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            label20.setHorizontalAlignment(SwingConstants.CENTER);
            panel4.add(label20, CC.xy(1, 11));

            // ---- attnName ----
            attnName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(attnName, CC.xy(3, 11));

            // ---- label24 ----
            label24.setText("\u6536\u636e\u8bf4\u660e");
            label24.setHorizontalAlignment(SwingConstants.CENTER);
            label24.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label24, CC.xy(1, 13));

            // ---- textDescription ----
            textDescription.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textDescription, CC.xywh(3, 13, 5, 1));
        }
        add(panel4, CC.xy(1, 9, CC.DEFAULT, CC.FILL));

        // ======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                    "137dlu, $lcgap, 50dlu, $lcgap, default:grow",
                    "fill:default"));

            // ---- addJButton ----
            addJButton.setText("\u7eed  \u8d39");
            addJButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            addJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addJButtonActionPerformed(e);
                }
            });
            panel5.add(addJButton, CC.xy(3, 1));
        }
        add(panel5, CC.xy(1, 11));

        // ---- genderGroup ----
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
    private JLabel label7;
    private JTextField textAddress;
    private JLabel label8;
    private JTextField textTel;
    private JLabel label10;
    private JTextField textPhone;
    private JLabel label21;
    private JTextField noteTextField;
    private JPanel panel4;
    private JLabel label14;
    private JTextField joindayTextField;
    private JLabel label17;
    private JTextField textFee;
    private JLabel label19;
    private JTextField lastdayTextField;
    private JLabel label5;
    private JTextField textIntroducer;
    private JLabel noteLabel;
    private JLabel label22;
    private JTextField addFeeTextField;
    private JLabel label15;
    private JComboBox addYearComboBox;
    private JLabel label18;
    private JTextField textReceipt;
    private JLabel label23;
    private DatePicker addFeedatePicker;
    private JLabel label20;
    private JTextField attnName;
    private JLabel label24;
    private JTextField textDescription;
    private JPanel panel5;
    private JButton addJButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
