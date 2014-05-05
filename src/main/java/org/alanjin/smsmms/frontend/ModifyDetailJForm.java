package org.alanjin.smsmms.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.alanjin.smsmms.frontend.util.Util;

import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.service.MemberAction;
import org.alanjin.smsmms.frontend.util.Lunar;

/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */

/**
 * @author AlanJin
 */
public class ModifyDetailJForm extends JPanel {
    private static final long serialVersionUID = 1L;
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
        textAddress.setText(m.getAddress());
        textTel.setText(m.getTel());
        textPhone.setText(m.getPhone());
        textEmail.setText(m.getEmail());
        eduComboBox.setSelectedItem(m.getEdu());
        if (m.getGender() == 1) {
            radioButton1.setSelected(true);
        } else {
            radioButton2.setSelected(true);
        }
        String lunarBirthday = Lunar
                .solarTolunar(org.alanjin.smsmms.backend.util.Util
                        .fromSQLDate(m.getBirthday()));
        String[] lunarBirthdayStrArray = lunarBirthday.split("-");
        comboBoxYear.setSelectedItem(lunarBirthdayStrArray[0]);
        comboBoxMonth.setSelectedIndex(Integer
                .parseInt(lunarBirthdayStrArray[1]) - 1);
        comboBoxDay
                .setSelectedIndex(Integer.parseInt(lunarBirthdayStrArray[2]) - 1);
        textIndustry.setText(m.getIndustry());
        textTitle.setText(m.getTitle());
        noteTextField.setText(m.getDescription());
    }

    private void confirmJButtonActionPerformed(ActionEvent e) {
        if (basicCheck()) {
            int sex = radioButton1.isSelected() ? 1 : 0;
            m.setGender(sex);
            java.sql.Date birthDate = null;
            try {
                if (this.solarRadio.isSelected()) {
                    birthDate = org.alanjin.smsmms.backend.util.Util
                            .toSQLDate(birthdayPicker.getText());
                } else {
                    StringBuilder birthdayBuilder = new StringBuilder();
                    birthdayBuilder.append(this.comboBoxYear.getSelectedItem())
                            .append("-");
                    if (this.comboBoxMonth.getSelectedIndex() < 9) {
                        birthdayBuilder.append("0");
                    }
                    birthdayBuilder.append(
                            this.comboBoxMonth.getSelectedIndex() + 1).append(
                            "-");
                    if (this.comboBoxDay.getSelectedIndex() < 9) {
                        birthdayBuilder.append("0");
                    }
                    birthdayBuilder
                            .append(this.comboBoxDay.getSelectedIndex() + 1);
                    birthDate = org.alanjin.smsmms.backend.util.Util
                            .toSQLDate(Lunar.lunarTosolar(birthdayBuilder
                                    .toString()));
                }
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
            m.setDescription(this.noteTextField.getText().trim());
            System.out.println(m.toString());

            if (JOptionPane.showConfirmDialog(null, "确定保存？", TITLE,
                    JOptionPane.OK_CANCEL_OPTION) == 0) {
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
        solarRadio = new JRadioButton();
        birthdayPicker = new DatePicker(
                org.alanjin.smsmms.backend.util.Util.dateConvert(m
                        .getBirthday()),
                org.alanjin.smsmms.backend.util.Util.dayFormatStr, null, null);
        radioButton4 = new JRadioButton();
        panel4 = new JPanel();
        comboBoxYear = new JComboBox();
        label5 = new JLabel();
        comboBoxMonth = new JComboBox();
        comboBoxDay = new JComboBox();
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

        // ======== this ========
        setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));

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

        setLayout(new FormLayout("341dlu",
                "20dlu, $lgap, default, $lgap, 15dlu, $lgap, 135dlu, 10dlu, 2*(default)"));

        // ---- label1 ----
        label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u8d44\u6599\u4fee\u6539");
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
                    "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 119dlu",
                    "7*(default, 5dlu)"));

            // ---- label3 ----
            label3.setText("\u59d3    \u540d");
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label3, CC.xy(1, 1));

            // ---- textName ----
            textName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textName, CC.xy(3, 1));

            // ---- label4 ----
            label4.setText("\u6027    \u522b");
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            label4.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label4, CC.xy(5, 1));

            // ======== panel3 ========
            {
                panel3.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel3.setLayout(new FormLayout("default, $lcgap, default",
                        "default"));

                // ---- radioButton1 ----
                radioButton1.setText("\u7537");
                radioButton1.setSelected(true);
                panel3.add(radioButton1, CC.xy(1, 1));

                // ---- radioButton2 ----
                radioButton2.setText("\u5973");
                panel3.add(radioButton2, CC.xy(3, 1));
            }
            panel2.add(panel3, CC.xy(7, 1));

            // ---- solarRadio ----
            solarRadio.setText("\u516c  \u5386");
            solarRadio.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            solarRadio.setHorizontalAlignment(SwingConstants.RIGHT);
            solarRadio.setHorizontalTextPosition(SwingConstants.LEFT);
            panel2.add(solarRadio, CC.xy(1, 3));
            panel2.add(birthdayPicker, CC.xy(3, 3));

            // ---- radioButton4 ----
            radioButton4.setText("\u519c  \u5386");
            radioButton4.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            radioButton4.setHorizontalAlignment(SwingConstants.RIGHT);
            radioButton4.setHorizontalTextPosition(SwingConstants.LEFT);
            radioButton4.setSelected(true);
            panel2.add(radioButton4, CC.xy(5, 3));

            // ======== panel4 ========
            {
                panel4.setLayout(new FormLayout(
                        "37dlu, default, 36dlu, default", "default"));

                // ---- comboBoxYear ----
                comboBoxYear.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                comboBoxYear.setMaximumRowCount(20);
                comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {
                        "1900", "1901", "1902", "1903", "1904", "1905", "1906",
                        "1907", "1908", "1909", "1910", "1911", "1912", "1913",
                        "1914", "1915", "1916", "1917", "1918", "1919", "1920",
                        "1921", "1922", "1923", "1924", "1925", "1926", "1927",
                        "1928", "1929", "1930", "1931", "1932", "1933", "1934",
                        "1935", "1936", "1937", "1938", "1939", "1940", "1941",
                        "1942", "1943", "1944", "1945", "1946", "1947", "1948",
                        "1949", "1950", "1951", "1952", "1953", "1954", "1955",
                        "1956", "1957", "1958", "1959", "1960", "1961", "1962",
                        "1963", "1964", "1965", "1966", "1967", "1968", "1969",
                        "1970", "1971", "1972", "1973", "1974", "1975", "1976",
                        "1977", "1978", "1979", "1980", "1981", "1982", "1983",
                        "1984", "1985", "1986", "1987", "1988", "1989", "1990",
                        "1991", "1992", "1993", "1994", "1995", "1996", "1997",
                        "1998", "1999", "2000", "2001", "2002", "2003", "2004",
                        "2005", "2006", "2007", "2008", "2009", "2010", "2011",
                        "2012", "2013", "2014", "2015", "2016", "2017", "2018",
                        "2019", "2020", "2021", "2022", "2023", "2024", "2025",
                        "2026", "2027", "2028", "2029", "2030", "2031", "2032",
                        "2033", "2034", "2035", "2036", "2037", "2038", "2039",
                        "2040", "2041", "2042", "2043", "2044", "2045", "2046",
                        "2047", "2048", "2049", "2050", "2051", "2052", "2053",
                        "2054", "2055", "2056", "2057", "2058", "2059", "2060",
                        "2061", "2062", "2063", "2064", "2065", "2066", "2067",
                        "2068", "2069", "2070", "2071", "2072", "2073", "2074",
                        "2075", "2076", "2077", "2078", "2079", "2080", "2081",
                        "2082", "2083", "2084", "2085", "2086", "2087", "2088",
                        "2089", "2090", "2091", "2092", "2093", "2094", "2095",
                        "2096", "2097", "2098", "2099", "2100" }));
                panel4.add(comboBoxYear, CC.xy(1, 1));

                // ---- label5 ----
                label5.setText("\u5e74");
                label5.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel4.add(label5, CC.xy(2, 1));

                // ---- comboBoxMonth ----
                comboBoxMonth.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                comboBoxMonth.setMaximumRowCount(12);
                comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {
                        "\u6b63\u6708", "\u4e8c\u6708", "\u4e09\u6708",
                        "\u56db\u6708", "\u4e94\u6708", "\u516d\u6708",
                        "\u4e03\u6708", "\u516b\u6708", "\u4e5d\u6708",
                        "\u5341\u6708", "\u5341\u4e00", "\u814a\u6708" }));
                panel4.add(comboBoxMonth, CC.xy(3, 1));

                // ---- comboBoxDay ----
                comboBoxDay.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                comboBoxDay.setMaximumRowCount(15);
                comboBoxDay.setModel(new DefaultComboBoxModel(new String[] {
                        "\u521d\u4e00", "\u521d\u4e8c", "\u521d\u4e09",
                        "\u521d\u56db", "\u521d\u4e94", "\u521d\u516d",
                        "\u521d\u4e03", "\u521d\u516b", "\u521d\u4e5d",
                        "\u521d\u5341", "\u5341\u4e00", "\u5341\u4e8c",
                        "\u5341\u4e09", "\u5341\u56db", "\u5341\u4e94",
                        "\u5341\u516d", "\u5341\u4e03", "\u5341\u516b",
                        "\u5341\u4e5d", "\u4e8c\u5341", "\u4e8c\u4e00",
                        "\u4e8c\u4e8c", "\u4e8c\u4e09", "\u4e8c\u56db",
                        "\u4e8c\u4e94", "\u4e8c\u516d", "\u4e8c\u4e03",
                        "\u4e8c\u516b", "\u4e8c\u4e5d", "\u4e09\u5341" }));
                panel4.add(comboBoxDay, CC.xy(4, 1));
            }
            panel2.add(panel4, CC.xy(7, 3));

            // ---- label7 ----
            label7.setText("\u5730    \u5740");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            label7.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label7, CC.xy(1, 5));

            // ---- textAddress ----
            textAddress.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textAddress, CC.xywh(3, 5, 5, 1));

            // ---- label8 ----
            label8.setText("\u56fa    \u8bdd");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label8, CC.xy(1, 7));

            // ---- textTel ----
            textTel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTel, CC.xy(3, 7));

            // ---- label10 ----
            label10.setText("\u624b    \u673a");
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            label10.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label10, CC.xy(5, 7));

            // ---- textPhone ----
            textPhone.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textPhone, CC.xy(7, 7));

            // ---- label9 ----
            label9.setText("\u7535\u5b50\u90ae\u7bb1");
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            label9.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label9, CC.xy(1, 9));

            // ---- textEmail ----
            textEmail.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textEmail, CC.xy(3, 9));

            // ---- label11 ----
            label11.setText("\u6587\u5316\u7a0b\u5ea6");
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            label11.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label11, CC.xy(5, 9));

            // ---- eduComboBox ----
            eduComboBox.setModel(new DefaultComboBoxModel(new String[] {
                    "\u521d\u4e2d\u53ca\u4ee5\u4e0b", "\u9ad8\u4e2d",
                    "\u5927\u4e13", "\u672c\u79d1", "\u7855\u58eb",
                    "\u535a\u58eb" }));
            eduComboBox.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(eduComboBox, CC.xy(7, 9));

            // ---- label12 ----
            label12.setText("\u884c    \u4e1a");
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            label12.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label12, CC.xy(1, 11));

            // ---- textIndustry ----
            textIndustry.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textIndustry, CC.xy(3, 11));

            // ---- label13 ----
            label13.setText("\u804c    \u79f0");
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            label13.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label13, CC.xy(5, 11));

            // ---- textTitle ----
            textTitle.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTitle, CC.xy(7, 11));

            // ---- label21 ----
            label21.setText("\u5907    \u6ce8");
            label21.setHorizontalTextPosition(SwingConstants.LEADING);
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            label21.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label21, CC.xy(1, 13));

            // ---- noteTextField ----
            noteTextField.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(noteTextField, CC.xywh(3, 13, 5, 1));
        }
        add(panel2, CC.xywh(1, 7, 1, 2));

        // ======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                    "133dlu, $lcgap, 50dlu, $lcgap, default:grow",
                    "fill:default"));

            // ---- confirmJButton ----
            confirmJButton.setText("\u4fee  \u6539");
            confirmJButton.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            confirmJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    confirmJButtonActionPerformed(e);
                }
            });
            panel5.add(confirmJButton, CC.xy(3, 1));
        }
        add(panel5, CC.xy(1, 10));

        // ---- genderGroup ----
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);

        // ---- birthdayGroup ----
        ButtonGroup birthdayGroup = new ButtonGroup();
        birthdayGroup.add(solarRadio);
        birthdayGroup.add(radioButton4);
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
    private JRadioButton solarRadio;
    private DatePicker birthdayPicker;
    private JRadioButton radioButton4;
    private JPanel panel4;
    private JComboBox comboBoxYear;
    private JLabel label5;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxDay;
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
