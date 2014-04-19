package org.alanjin.smsmms.frontend;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.eltima.components.ui.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;
/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */



/**
 * @author AlanJin
 */
public class MemberJForm extends JPanel {
	public MemberJForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
                // Generated using JFormDesigner Evaluation license - Jin Lantao
                label1 = new JLabel();
                panel1 = new JPanel();
                label2 = new JLabel();
                textField1 = new JTextField();
                panel2 = new JPanel();
                label3 = new JLabel();
                textField2 = new JTextField();
                label4 = new JLabel();
                panel3 = new JPanel();
                radioButton1 = new JRadioButton();
                radioButton2 = new JRadioButton();
                label6 = new JLabel();
                datePicker1 = new DatePicker();
                label5 = new JLabel();
                textField3 = new JTextField();
                label7 = new JLabel();
                textField5 = new JTextField();
                label8 = new JLabel();
                textField6 = new JTextField();
                label10 = new JLabel();
                textField9 = new JTextField();
                label9 = new JLabel();
                textField7 = new JTextField();
                label11 = new JLabel();
                comboBox1 = new JComboBox();
                label12 = new JLabel();
                textField10 = new JTextField();
                label13 = new JLabel();
                textField11 = new JTextField();
                panel4 = new JPanel();
                label14 = new JLabel();
                datePicker2 = new DatePicker();
                label15 = new JLabel();
                datePicker3 = new DatePicker();
                label16 = new JLabel();
                datePicker4 = new DatePicker();
                label17 = new JLabel();
                textField14 = new JTextField();
                label18 = new JLabel();
                textField16 = new JTextField();
                label19 = new JLabel();
                textField15 = new JTextField();
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
                    "20dlu, $lgap, default, $lgap, 20dlu, $lgap, default, $lgap, 73dlu, 3dlu, default:grow"));

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
                    panel1.add(textField1, CC.xy(3, 1));
                }
                add(panel1, CC.xy(1, 5));

                //======== panel2 ========
                {
                    panel2.setBorder(new TitledBorder(null, "\u57fa\u672c\u4fe1\u606f", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, Color.gray));
                    panel2.setLayout(new FormLayout(
                        "50dlu, $lcgap, 100dlu, $lcgap, 50dlu, $lcgap, 100dlu",
                        "6*(default, $lgap), 5dlu"));

                    //---- label3 ----
                    label3.setText("\u59d3    \u540d");
                    label3.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label3, CC.xy(1, 1));
                    panel2.add(textField2, CC.xy(3, 1));

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
                    panel2.add(datePicker1, CC.xy(3, 3));

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
                    panel2.add(textField9, CC.xy(7, 7));

                    //---- label9 ----
                    label9.setText("\u7535\u5b50\u90ae\u7bb1");
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    panel2.add(label9, CC.xy(1, 9));
                    panel2.add(textField7, CC.xy(3, 9));

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
                        "3*(default, $lgap), 5dlu"));

                    //---- label14 ----
                    label14.setText("\u5165\u4f1a\u65e5\u671f");
                    label14.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label14, CC.xy(1, 1));
                    panel4.add(datePicker2, CC.xy(3, 1));

                    //---- label15 ----
                    label15.setText("\u5165\u4f1a\u5e74\u9650");
                    label15.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label15, CC.xy(5, 1));
                    panel4.add(datePicker3, CC.xy(7, 1));

                    //---- label16 ----
                    label16.setText("\u7eed\u4f1a\u65f6\u9650");
                    label16.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label16, CC.xy(1, 3));
                    panel4.add(datePicker4, CC.xy(3, 3));

                    //---- label17 ----
                    label17.setText("\u4f1a\u8d39\u6570\u989d");
                    label17.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label17, CC.xy(5, 3));
                    panel4.add(textField14, CC.xy(7, 3));

                    //---- label18 ----
                    label18.setText("\u6536\u636e\u53f7\u7801");
                    label18.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label18, CC.xy(1, 5));
                    panel4.add(textField16, CC.xy(3, 5));

                    //---- label19 ----
                    label19.setText("\u7ecf\u529e\u4eba\u5458");
                    label19.setHorizontalAlignment(SwingConstants.CENTER);
                    panel4.add(label19, CC.xy(5, 5));
                    panel4.add(textField15, CC.xy(7, 5));
                }
                add(panel4, CC.xy(1, 9));

                //======== panel5 ========
                {
                    panel5.setLayout(new FormLayout(
                        "97dlu, $lcgap, 50dlu, 20dlu, 50dlu, $lcgap, default:grow",
                        "top:default"));

                    //---- button1 ----
                    button1.setText("\u786e  \u8ba4");
                    panel5.add(button1, CC.xy(3, 1));

                    //---- button2 ----
                    button2.setText("\u91cd  \u7f6e");
                    panel5.add(button2, CC.xy(5, 1));
                }
                add(panel5, CC.xy(1, 11));

                //---- buttonGroup1 ----
                ButtonGroup buttonGroup1 = new ButtonGroup();
                buttonGroup1.add(radioButton1);
                buttonGroup1.add(radioButton2);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
        // Generated using JFormDesigner Evaluation license - Jin Lantao
        private JLabel label1;
        private JPanel panel1;
        private JLabel label2;
        private JTextField textField1;
        private JPanel panel2;
        private JLabel label3;
        private JTextField textField2;
        private JLabel label4;
        private JPanel panel3;
        private JRadioButton radioButton1;
        private JRadioButton radioButton2;
        private JLabel label6;
        private DatePicker datePicker1;
        private JLabel label5;
        private JTextField textField3;
        private JLabel label7;
        private JTextField textField5;
        private JLabel label8;
        private JTextField textField6;
        private JLabel label10;
        private JTextField textField9;
        private JLabel label9;
        private JTextField textField7;
        private JLabel label11;
        private JComboBox comboBox1;
        private JLabel label12;
        private JTextField textField10;
        private JLabel label13;
        private JTextField textField11;
        private JPanel panel4;
        private JLabel label14;
        private DatePicker datePicker2;
        private JLabel label15;
        private DatePicker datePicker3;
        private JLabel label16;
        private DatePicker datePicker4;
        private JLabel label17;
        private JTextField textField14;
        private JLabel label18;
        private JTextField textField16;
        private JLabel label19;
        private JTextField textField15;
        private JPanel panel5;
        private JButton button1;
        private JButton button2;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
