package org.alanjin.smsmms.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import org.alanjin.smsmms.frontend.util.FrontendUtil;

import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.Receipt;
import org.alanjin.smsmms.backend.service.MemberAction;
import org.alanjin.smsmms.backend.util.BackendUtil;
import org.alanjin.smsmms.frontend.util.Lunar;

/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */

/**
 * @author AlanJin
 */
public class MemberJForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "新增会员";
    private MemberAction memberAction;

    public MemberJForm(MemberAction memberAction) {
        this.memberAction = memberAction;
        initComponents();
    }

    private void addMemberActionPerformed(ActionEvent e) {
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
            java.sql.Date lastDate = null;
            try {
                if (this.solarRadio.isSelected()) {
                    birthDate = BackendUtil.toSQLDate(birthday.getText());
                } else {
                    StringBuilder birthdayBuilder = new StringBuilder();
                    birthdayBuilder.append(this.lunarYear.getSelectedItem())
                            .append("-");
                    if (this.lunarMonth.getSelectedIndex() < 9) {
                        birthdayBuilder.append("0");
                    }
                    birthdayBuilder.append(
                            this.lunarMonth.getSelectedIndex() + 1).append("-");
                    if (this.lunarDay.getSelectedIndex() < 9) {
                        birthdayBuilder.append("0");
                    }
                    birthdayBuilder
                            .append(this.lunarDay.getSelectedIndex() + 1);
                    birthDate = BackendUtil.toSQLDate(Lunar.lunarTosolar(birthdayBuilder.toString()));
                }
                joinDate = BackendUtil.toSQLDate(joinday.getText());
                lastDate = BackendUtil.dateConvert(BackendUtil.getNextNYearFromDate(
                                BackendUtil.dateConvert(joinDate),
                                this.yearsComboBox.getSelectedIndex() + 1));

            } catch (ParseException ex) {
                Logger.getLogger(MemberJForm.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            BigDecimal feeSum = new BigDecimal(textFee.getText().trim());
            Member m = new Member(memId, textName.getText().trim(), sex,
                    birthDate, joinDate, feeSum);
            m.setAddress(this.textAddress.getText().trim());
            m.setEdu((String) this.eduComboBox.getSelectedItem());
            m.setEmail(this.textEmail.getText().trim());
            m.setExpert("");
            m.setIndustry(this.textIndustry.getText().trim());
            m.setLastDate(lastDate);
            m.setTel(this.textTel.getText().trim());
            m.setPhone(this.textPhone.getText().trim());
            m.setTitle(this.textTitle.getText().trim());
            m.setZip("");
            m.setDescription(this.personDescription.getText().trim());
            m.setIntroducer(this.introducer.getText().trim());
            System.out.println(m.toString());
            List<Receipt> receipts;
            if ((receipts = m.getReceiptList()) == null) {
                receipts = new ArrayList<Receipt>();
                m.setReceiptList(receipts);
            }
            Receipt r = new Receipt(textReceipt.getText().trim(), feeSum,
                    joinDate);
            r.setMemId(memId);
            r.setAttnName(attnName.getText().trim());
            r.setDescription(this.textDescription.getText().trim());
            receipts.add(r);
            m.setReceiptList(receipts);

            if (JOptionPane.showConfirmDialog(null, "确定保存？", TITLE,
                    JOptionPane.OK_CANCEL_OPTION) == 0) {
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
        memIdJTextField.setText("");
        textName.setText("");
//        birthday = new DatePicker(new java.util.Date(0), BackendUtil.dayFormatStr, null, null);
        birthday = new DatePicker(this, new java.util.Date(0));
        birthday.setPattern(BackendUtil.dayFormatStr);
        textAddress.setText("");
        textTel.setText("");
        textPhone.setText("");
        textEmail.setText("");
        eduComboBox.setSelectedIndex(0);
        lunarYear.setSelectedIndex(0);
        lunarMonth.setSelectedIndex(0);
        lunarDay.setSelectedIndex(0);
        textIndustry.setText("");
        textTitle.setText("");
        textFee.setText("");
        textReceipt.setText("");
        introducer.setText("");
        personDescription.setText("");
        textDescription.setText("");
        attnName.setText("");
        successJLabel.setText("");
    }

    private boolean basicCheck() {
        if (textName.getText().trim().equals("")) {
            FrontendUtil.verifyAlert("姓名 不得为空", TITLE);
            return false;
        } else if (!textPhone.getText().trim().isEmpty()
                && !FrontendUtil.isMobileNO(textPhone.getText().trim())) {
            FrontendUtil.verifyAlert("手机号格式不对", TITLE);
            return false;
        } else if (!textEmail.getText().trim().isEmpty()
                && !FrontendUtil.isEmail(textEmail.getText().trim())) {
            FrontendUtil.verifyAlert("电子邮箱格式不对", TITLE);
            return false;
        } else if (!FrontendUtil.isDigit(textFee.getText().trim())) {
            FrontendUtil.verifyAlert("会费必须是有效金额数字", TITLE);
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

    private void resetAddJButtonActionPerformed(ActionEvent e) {
        clearAllComponent();
    }

    private void solarRadioStateChanged(ChangeEvent e) {
        if (solarRadio.isSelected())
            this.birthday.setEnabled(true);
        else
            this.birthday.setEnabled(false);
    }

    private void lunarRadioStateChanged(ChangeEvent e) {
        if (lunarRadio.isSelected()) {
            this.lunarYear.setEnabled(true);
            this.lunarMonth.setEnabled(true);
            this.lunarDay.setEnabled(true);
        } else {
            this.lunarYear.setEnabled(false);
            this.lunarMonth.setEnabled(false);
            this.lunarDay.setEnabled(false);
        }
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
        label25 = new JLabel();
        label23 = new JLabel();
        solarRadio = new JRadioButton();
//        birthday = new DatePicker(new java.util.Date(0), BackendUtil.dayFormatStr, null, null);
        birthday = new DatePicker(this, new java.util.Date(0));
        birthday.setPattern(BackendUtil.dayFormatStr);
        lunarRadio = new JRadioButton();
        panel6 = new JPanel();
        lunarYear = new JComboBox();
        label24 = new JLabel();
        lunarMonth = new JComboBox();
        lunarDay = new JComboBox();
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
        personDescription = new JTextField();
        panel4 = new JPanel();
        label14 = new JLabel();
//        joinday = new DatePicker(new java.util.Date(), BackendUtil.dayFormatStr, null, null);
        joinday = new DatePicker(this, new java.util.Date());
        joinday.setPattern(BackendUtil.dayFormatStr);
        label15 = new JLabel();
        yearsComboBox = new JComboBox();
        label17 = new JLabel();
        textFee = new JTextField();
        label19 = new JLabel();
        introducer = new JTextField();
        label18 = new JLabel();
        textReceipt = new JTextField();
        label5 = new JLabel();
        attnName = new JTextField();
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
            "51dlu, 341dlu",
            "10dlu, $lgap, default, $lgap, 15dlu, $lgap, 161dlu, 3dlu, 92dlu, 3dlu, default"));

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
                "50dlu, $lcgap, 100dlu, 10dlu, 50dlu, $lcgap, 115dlu",
                "8*(default, 5dlu)"));

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

            //---- label25 ----
            label25.setText("\u51fa\u751f\u65e5\u671f");
            label25.setHorizontalAlignment(SwingConstants.CENTER);
            label25.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label25, CC.xy(1, 3));

            //---- label23 ----
            label23.setText("\u8bf7\u9009\u62e9\u516c\u5386\u6216\u8005\u519c\u5386\uff0c\u95f0\u4e03\u6708\u53ef\u80fd\u4f1a\u8ba1\u7b97\u51fa\u9519\u8bef\u516c\u5386\u65e5\u671f");
            label23.setForeground(Color.red);
            label23.setHorizontalAlignment(SwingConstants.LEFT);
            panel2.add(label23, CC.xywh(3, 3, 5, 1));

            //---- solarRadio ----
            solarRadio.setText("\u516c  \u5386");
            solarRadio.setHorizontalTextPosition(SwingConstants.LEFT);
            solarRadio.setHorizontalAlignment(SwingConstants.RIGHT);
            solarRadio.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            solarRadio.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    solarRadioStateChanged(e);
                }
            });
            panel2.add(solarRadio, CC.xy(1, 5));

            //---- birthday ----
            birthday.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(birthday, CC.xy(3, 5));

            //---- lunarRadio ----
            lunarRadio.setText("\u519c  \u5386");
            lunarRadio.setHorizontalAlignment(SwingConstants.RIGHT);
            lunarRadio.setHorizontalTextPosition(SwingConstants.LEFT);
            lunarRadio.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            lunarRadio.setSelected(true);
            lunarRadio.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    lunarRadioStateChanged(e);
                }
            });
            panel2.add(lunarRadio, CC.xy(5, 5));

            //======== panel6 ========
            {
                panel6.setLayout(new FormLayout(
                    "35dlu, default, 2*(35dlu)",
                    "default"));

                //---- lunarYear ----
                lunarYear.setMaximumRowCount(20);
                lunarYear.setModel(new DefaultComboBoxModel(new String[] {
                    "1900",
                    "1901",
                    "1902",
                    "1903",
                    "1904",
                    "1905",
                    "1906",
                    "1907",
                    "1908",
                    "1909",
                    "1910",
                    "1911",
                    "1912",
                    "1913",
                    "1914",
                    "1915",
                    "1916",
                    "1917",
                    "1918",
                    "1919",
                    "1920",
                    "1921",
                    "1922",
                    "1923",
                    "1924",
                    "1925",
                    "1926",
                    "1927",
                    "1928",
                    "1929",
                    "1930",
                    "1931",
                    "1932",
                    "1933",
                    "1934",
                    "1935",
                    "1936",
                    "1937",
                    "1938",
                    "1939",
                    "1940",
                    "1941",
                    "1942",
                    "1943",
                    "1944",
                    "1945",
                    "1946",
                    "1947",
                    "1948",
                    "1949",
                    "1950",
                    "1951",
                    "1952",
                    "1953",
                    "1954",
                    "1955",
                    "1956",
                    "1957",
                    "1958",
                    "1959",
                    "1960",
                    "1961",
                    "1962",
                    "1963",
                    "1964",
                    "1965",
                    "1966",
                    "1967",
                    "1968",
                    "1969",
                    "1970",
                    "1971",
                    "1972",
                    "1973",
                    "1974",
                    "1975",
                    "1976",
                    "1977",
                    "1978",
                    "1979",
                    "1980",
                    "1981",
                    "1982",
                    "1983",
                    "1984",
                    "1985",
                    "1986",
                    "1987",
                    "1988",
                    "1989",
                    "1990",
                    "1991",
                    "1992",
                    "1993",
                    "1994",
                    "1995",
                    "1996",
                    "1997",
                    "1998",
                    "1999",
                    "2000",
                    "2001",
                    "2002",
                    "2003",
                    "2004",
                    "2005",
                    "2006",
                    "2007",
                    "2008",
                    "2009",
                    "2010",
                    "2011",
                    "2012",
                    "2013",
                    "2014",
                    "2015",
                    "2016",
                    "2017",
                    "2018",
                    "2019",
                    "2020",
                    "2021",
                    "2022",
                    "2023",
                    "2024",
                    "2025",
                    "2026",
                    "2027",
                    "2028",
                    "2029",
                    "2030",
                    "2031",
                    "2032",
                    "2033",
                    "2034",
                    "2035",
                    "2036",
                    "2037",
                    "2038",
                    "2039",
                    "2040",
                    "2041",
                    "2042",
                    "2043",
                    "2044",
                    "2045",
                    "2046",
                    "2047",
                    "2048",
                    "2049",
                    "2050",
                    "2051",
                    "2052",
                    "2053",
                    "2054",
                    "2055",
                    "2056",
                    "2057",
                    "2058",
                    "2059",
                    "2060",
                    "2061",
                    "2062",
                    "2063",
                    "2064",
                    "2065",
                    "2066",
                    "2067",
                    "2068",
                    "2069",
                    "2070",
                    "2071",
                    "2072",
                    "2073",
                    "2074",
                    "2075",
                    "2076",
                    "2077",
                    "2078",
                    "2079",
                    "2080",
                    "2081",
                    "2082",
                    "2083",
                    "2084",
                    "2085",
                    "2086",
                    "2087",
                    "2088",
                    "2089",
                    "2090",
                    "2091",
                    "2092",
                    "2093",
                    "2094",
                    "2095",
                    "2096",
                    "2097",
                    "2098",
                    "2099",
                    "2100"
                }));
                lunarYear.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                lunarYear.setSelectedIndex(70);
                panel6.add(lunarYear, CC.xy(1, 1));

                //---- label24 ----
                label24.setText("\u5e74");
                label24.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                panel6.add(label24, CC.xy(2, 1));

                //---- lunarMonth ----
                lunarMonth.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                lunarMonth.setModel(new DefaultComboBoxModel(new String[] {
                    "\u6b63\u6708",
                    "\u4e8c\u6708",
                    "\u4e09\u6708",
                    "\u56db\u6708",
                    "\u4e94\u6708",
                    "\u516d\u6708",
                    "\u4e03\u6708",
                    "\u516b\u6708",
                    "\u4e5d\u6708",
                    "\u5341\u6708",
                    "\u5341\u4e00",
                    "\u814a\u6708"
                }));
                lunarMonth.setMaximumRowCount(12);
                panel6.add(lunarMonth, CC.xy(3, 1));

                //---- lunarDay ----
                lunarDay.setModel(new DefaultComboBoxModel(new String[] {
                    "\u521d\u4e00",
                    "\u521d\u4e8c",
                    "\u521d\u4e09",
                    "\u521d\u56db",
                    "\u521d\u4e94",
                    "\u521d\u516d",
                    "\u521d\u4e03",
                    "\u521d\u516b",
                    "\u521d\u4e5d",
                    "\u521d\u5341",
                    "\u5341\u4e00",
                    "\u5341\u4e8c",
                    "\u5341\u4e09",
                    "\u5341\u56db",
                    "\u5341\u4e94",
                    "\u5341\u516d",
                    "\u5341\u4e03",
                    "\u5341\u516b",
                    "\u5341\u4e5d",
                    "\u4e8c\u5341",
                    "\u4e8c\u4e00",
                    "\u4e8c\u4e8c",
                    "\u4e8c\u4e09",
                    "\u4e8c\u56db",
                    "\u4e8c\u4e94",
                    "\u4e8c\u516d",
                    "\u4e8c\u4e03",
                    "\u4e8c\u516b",
                    "\u4e8c\u4e5d",
                    "\u4e09\u5341"
                }));
                lunarDay.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
                lunarDay.setPreferredSize(new Dimension(50, 22));
                lunarDay.setMaximumRowCount(15);
                panel6.add(lunarDay, CC.xy(4, 1));
            }
            panel2.add(panel6, CC.xy(7, 5));

            //---- label7 ----
            label7.setText("\u5730    \u5740");
            label7.setHorizontalAlignment(SwingConstants.CENTER);
            label7.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label7, CC.xy(1, 7));

            //---- textAddress ----
            textAddress.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textAddress, CC.xywh(3, 7, 5, 1));

            //---- label8 ----
            label8.setText("\u56fa    \u8bdd");
            label8.setHorizontalAlignment(SwingConstants.CENTER);
            label8.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label8, CC.xy(1, 9));

            //---- textTel ----
            textTel.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTel, CC.xy(3, 9));

            //---- label10 ----
            label10.setText("\u624b    \u673a");
            label10.setHorizontalAlignment(SwingConstants.CENTER);
            label10.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label10, CC.xy(5, 9));

            //---- textPhone ----
            textPhone.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textPhone, CC.xy(7, 9));

            //---- label9 ----
            label9.setText("\u7535\u5b50\u90ae\u7bb1");
            label9.setHorizontalAlignment(SwingConstants.CENTER);
            label9.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label9, CC.xy(1, 11));

            //---- textEmail ----
            textEmail.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textEmail, CC.xy(3, 11));

            //---- label11 ----
            label11.setText("\u6587\u5316\u7a0b\u5ea6");
            label11.setHorizontalAlignment(SwingConstants.CENTER);
            label11.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label11, CC.xy(5, 11));

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
            panel2.add(eduComboBox, CC.xy(7, 11));

            //---- label12 ----
            label12.setText("\u884c    \u4e1a");
            label12.setHorizontalAlignment(SwingConstants.CENTER);
            label12.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label12, CC.xy(1, 13));

            //---- textIndustry ----
            textIndustry.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textIndustry, CC.xy(3, 13));

            //---- label13 ----
            label13.setText("\u804c    \u79f0");
            label13.setHorizontalAlignment(SwingConstants.CENTER);
            label13.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label13, CC.xy(5, 13));

            //---- textTitle ----
            textTitle.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(textTitle, CC.xy(7, 13));

            //---- label21 ----
            label21.setText("\u5907    \u6ce8");
            label21.setHorizontalTextPosition(SwingConstants.LEADING);
            label21.setHorizontalAlignment(SwingConstants.CENTER);
            label21.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(label21, CC.xy(1, 15));

            //---- personDescription ----
            personDescription.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel2.add(personDescription, CC.xywh(3, 15, 5, 1));
        }
        add(panel2, CC.xy(2, 7));

        //======== panel4 ========
        {
            panel4.setBorder(new TitledBorder(null, "\u4f1a\u5458\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
            panel4.setLayout(new FormLayout(
                "50dlu, $lcgap, 100dlu, 10dlu, 50dlu, $lcgap, 111dlu",
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

            //---- yearsComboBox ----
            yearsComboBox.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            yearsComboBox.setModel(new DefaultComboBoxModel(new String[] {
                "1\u5e74",
                "2\u5e74",
                "3\u5e74",
                "4\u5e74",
                "5\u5e74",
                "6\u5e74",
                "7\u5e74",
                "8\u5e74",
                "9\u5e74",
                "10\u5e74"
            }));
            panel4.add(yearsComboBox, CC.xy(7, 1));

            //---- label17 ----
            label17.setText("\u4f1a\u8d39\u6570\u989d");
            label17.setHorizontalAlignment(SwingConstants.CENTER);
            label17.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label17, CC.xy(1, 3));

            //---- textFee ----
            textFee.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textFee, CC.xy(3, 3));

            //---- label19 ----
            label19.setText("\u4ecb\u7ecd\u4eba");
            label19.setHorizontalAlignment(SwingConstants.CENTER);
            label19.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label19, CC.xy(5, 3));

            //---- introducer ----
            introducer.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(introducer, CC.xy(7, 3));

            //---- label18 ----
            label18.setText("\u6536\u636e\u5355\u53f7");
            label18.setHorizontalAlignment(SwingConstants.CENTER);
            label18.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label18, CC.xy(1, 5));

            //---- textReceipt ----
            textReceipt.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textReceipt, CC.xy(3, 5));

            //---- label5 ----
            label5.setText("\u7ecf\u529e\u4eba");
            label5.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            label5.setHorizontalAlignment(SwingConstants.CENTER);
            panel4.add(label5, CC.xy(5, 5));

            //---- attnName ----
            attnName.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(attnName, CC.xy(7, 5));

            //---- label20 ----
            label20.setText("\u6536\u636e\u8bf4\u660e");
            label20.setHorizontalAlignment(SwingConstants.CENTER);
            label20.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(label20, CC.xy(1, 7));

            //---- textDescription ----
            textDescription.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 14));
            panel4.add(textDescription, CC.xywh(3, 7, 5, 1));
        }
        add(panel4, CC.xy(2, 9, CC.DEFAULT, CC.FILL));

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
            resetAddJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetAddJButtonActionPerformed(e);
                }
            });
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
        add(panel5, CC.xy(2, 11));

        //---- genderGroup ----
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioButton1);
        genderGroup.add(radioButton2);

        //---- calendarGroup ----
        ButtonGroup calendarGroup = new ButtonGroup();
        calendarGroup.add(solarRadio);
        calendarGroup.add(lunarRadio);
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
    private JLabel label25;
    private JLabel label23;
    private JRadioButton solarRadio;
    private DatePicker birthday;
    private JRadioButton lunarRadio;
    private JPanel panel6;
    private JComboBox lunarYear;
    private JLabel label24;
    private JComboBox lunarMonth;
    private JComboBox lunarDay;
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
    private JTextField personDescription;
    private JPanel panel4;
    private JLabel label14;
    private DatePicker joinday;
    private JLabel label15;
    private JComboBox yearsComboBox;
    private JLabel label17;
    private JTextField textFee;
    private JLabel label19;
    private JTextField introducer;
    private JLabel label18;
    private JTextField textReceipt;
    private JLabel label5;
    private JTextField attnName;
    private JLabel label20;
    private JTextField textDescription;
    private JPanel panel5;
    private JButton confirmAddJButton;
    private JButton resetAddJButton;
    private JButton nextAddJButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
