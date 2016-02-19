package org.alanjin.smsmms.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.alanjin.smsmms.frontend.bean.SMSEntity;
import org.alanjin.smsmms.frontend.util.FrontendUtil;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.bean.Response;
import org.alanjin.smsmms.backend.service.MessageService;
import org.alanjin.smsmms.backend.service.SenderAndReceiverService;
import org.alanjin.smsmms.backend.util.BackendUtil;

/*
 * Created by JFormDesigner on Sat Apr 19 16:52:18 CST 2014
 */

/**
 * @author AlanJin
 */
public class BroadcastJForm extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "短信群发";
    private SenderAndReceiverService srService;
    private List<SMSEntity> toSendSMSEntitys;
    private List<MessageModel> models;
    private Map<String, MessageModel> modelsMap;

    public BroadcastJForm(MessageService service,
            SenderAndReceiverService srService, List<SMSEntity> entitys) {
        this.srService = srService;
        this.toSendSMSEntitys = entitys;
        modelsMap = new HashMap<String, MessageModel>();
        models = service.getAllMessageModel();
        initComponents();
        initValue();
    }

    private void initValue() {
        for (MessageModel model : models) {
            modelsMap.put(model.getModelName(), model);
        }
        fillAllComponent();
    }

    private void fillAllComponent() {
        StringBuilder showSendToStringBuilder = new StringBuilder();
        for (SMSEntity entity : toSendSMSEntitys) {
            showSendToStringBuilder.append(entity.getPhone());
            showSendToStringBuilder.append("<");
            showSendToStringBuilder.append(entity.getName());
            showSendToStringBuilder.append(">");
            showSendToStringBuilder.append(";");
            showSendToStringBuilder.append("\n");
        }
        this.sendToTextArea.setText(showSendToStringBuilder.toString());
        this.modelComboBox.addItem("请选择短信模版");
        for (MessageModel model : models) {
            this.modelComboBox.addItem((String) model.getModelName());
        }
        this.titleComboBox.addItem("不使用称谓词");
        for (String title : MainFrame.getTitleList()) {
            this.titleComboBox.addItem(title);
        }
    }

    @SuppressWarnings("static-access")
    private void sendButtonActionPerformed(ActionEvent e) {
        String content = "";
        if (this.tabbedPane.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "新版本必须选择模板！", TITLE,
                    JOptionPane.OK_OPTION);
            return;
        } else {
            if (this.modelComboBox.getSelectedIndex() != 0) {
                String modelName = (String) this.modelComboBox
                        .getSelectedItem();
                MessageModel model = modelsMap.get(modelName);
                if (model != null) {
                    content = model.getContent();
                }
            }

            if (content.equals("")) {
                JOptionPane.showMessageDialog(this,
                        "未选择模版，请选择。如未创建模版，请先至【模版管理】进行创建。", TITLE,
                        JOptionPane.OK_OPTION);
                return;
            }
        }

        List<String> toSendList = new ArrayList<String>();
        List<SMSEntity> noSendList = new ArrayList<SMSEntity>();
        for (SMSEntity entity : this.toSendSMSEntitys) {
            if (entity.getPhone().isEmpty()
                    || !FrontendUtil.isMobileNO(entity.getPhone())) {
                noSendList.add(entity);
                continue;
            }
            toSendList.add(entity.getPhone());
        }
        System.out.println("群发短信to Send list:" + toSendList);
        String resultJson = srService.sendSms(content, toSendList);
        Response response = null;
        try {
            response = BackendUtil.parseResponse(resultJson);
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this,
                    "未知异常，请联系管理员！提示:" + e1.getStackTrace(), TITLE,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (response.getCode() == 0) {
            JOptionPane.showMessageDialog(this, "短信群发成功!选择发送" + toSendList.size()
                    + "条,实际发送" + response.getCount() + "条!帐号扣费" + response.getFee()
                    +"元,另外有" + noSendList.size() + "个因手机号不合格而未发送.", TITLE,
                    JOptionPane.OK_OPTION);
            
        } else {
            JOptionPane.showMessageDialog(this,
                    "短信群发失败！原因:"+ response.getMsg() + ",提示:" + response.getDetail(), TITLE,
                    JOptionPane.OK_OPTION);
        }
        StringBuilder builder = new StringBuilder();
        for (SMSEntity nosend : noSendList) {
            builder.append(",").append(nosend.getName()).append(":").append(nosend.getPhone());
        }
        builder.deleteCharAt(0);
        this.nosendTextArea.setText(builder.toString());
    }

    private void modelComboBoxItemStateChanged(ItemEvent e) {
        String modelName = (String) this.modelComboBox.getSelectedItem();
        if (modelName.equals("请选择短信模版")) {
        } else {
            this.descriptionTextField.setText(modelsMap.get(modelName)
                    .getDescription());
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jin Lantao
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        scrollPane1 = new JScrollPane();
        sendToTextArea = new JTextArea();
        tabbedPane = new JTabbedPane();
        panel2 = new JPanel();
        useHeadCheckBox = new JCheckBox();
        label7 = new JLabel();
        titleComboBox = new JComboBox();
        scrollPane2 = new JScrollPane();
        messageTextArea = new JTextArea();
        label5 = new JLabel();
        panel3 = new JPanel();
        label6 = new JLabel();
        modelComboBox = new JComboBox();
        label4 = new JLabel();
        descriptionTextField = new JTextField();
        panel4 = new JPanel();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        nosendTextArea = new JTextArea();
        panel5 = new JPanel();
        sendButton = new JButton();

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
                "default:grow",
                "20dlu, $lgap, default, $lgap, 64dlu, $lgap, fill:65dlu, 3dlu, fill:90dlu, 3dlu, default"));

        // ---- label1 ----
        label1.setText("\u4f5b\u6069\u4e92\u52a9\u4f1a \u77ed\u4fe1\u7fa4\u53d1");
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        add(label1, CC.xy(1, 3));

        // ======== panel1 ========
        {
            panel1.setLayout(new FormLayout("35dlu, $lcgap, 80dlu:grow",
                    "fill:default:grow"));

            // ---- label2 ----
            label2.setText("\u53d1\u9001\u5230\uff1a");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label2, CC.xy(1, 1));

            // ======== scrollPane1 ========
            {

                // ---- sendToTextArea ----
                sendToTextArea.setEditable(false);
                scrollPane1.setViewportView(sendToTextArea);
            }
            panel1.add(scrollPane1, CC.xy(3, 1, CC.DEFAULT, CC.FILL));
        }
        add(panel1, CC.xy(1, 5, CC.DEFAULT, CC.FILL));

        // ======== tabbedPane ========
        {

            // ======== panel2 ========
            {
                panel2.setLayout(new FormLayout(
                        "50dlu, $lcgap, 47dlu, $lcgap, default:grow",
                        "default, $lgap, fill:default:grow"));

                // ---- useHeadCheckBox ----
                useHeadCheckBox.setText("\u4f7f\u7528\u62ac\u5934");
                useHeadCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(useHeadCheckBox, CC.xy(1, 1));

                // ---- label7 ----
                label7.setText("\u9009\u62e9\u79f0\u8c13\uff1a");
                label7.setHorizontalAlignment(SwingConstants.RIGHT);
                panel2.add(label7, CC.xy(3, 1));
                panel2.add(titleComboBox, CC.xy(5, 1));

                // ======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(messageTextArea);
                }
                panel2.add(scrollPane2, CC.xywh(3, 3, 3, 1));

                // ---- label5 ----
                label5.setText("\u77ed\u4fe1\u5185\u5bb9\uff1a");
                label5.setHorizontalAlignment(SwingConstants.RIGHT);
                panel2.add(label5, CC.xy(1, 3));
            }
            tabbedPane.addTab("\u81ea\u5b9a\u4e49\u5185\u5bb9", panel2);

            // ======== panel3 ========
            {
                panel3.setLayout(new FormLayout("50dlu, $lcgap, default:grow",
                        "default, $lgap, default:grow"));

                // ---- label6 ----
                label6.setText("\u6a21\u7248\u540d\u79f0\uff1a");
                label6.setHorizontalAlignment(SwingConstants.RIGHT);
                panel3.add(label6, CC.xy(1, 1));

                // ---- modelComboBox ----
                modelComboBox.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        modelComboBoxItemStateChanged(e);
                    }
                });
                panel3.add(modelComboBox, CC.xy(3, 1));

                // ---- label4 ----
                label4.setText("\u6a21\u7248\u8bf4\u660e\uff1a");
                label4.setHorizontalAlignment(SwingConstants.RIGHT);
                panel3.add(label4, CC.xy(1, 3));

                // ---- descriptionTextField ----
                descriptionTextField.setEditable(false);
                panel3.add(descriptionTextField, CC.xy(3, 3));
            }
            tabbedPane.addTab("\u77ed\u4fe1\u6a21\u7248", panel3);
        }
        add(tabbedPane, CC.xy(1, 7));

        // ======== panel4 ========
        {
            panel4.setLayout(new FormLayout("default:grow",
                    "default, $lgap, fill:default:grow"));

            // ---- label3 ----
            label3.setText("\u4ee5\u4e0b\u53f7\u7801\u672a\u53d1\u9001\u6210\u529f\uff1a");
            panel4.add(label3, CC.xy(1, 1));

            // ======== scrollPane3 ========
            {

                // ---- nosendTextArea ----
                nosendTextArea.setEditable(false);
                scrollPane3.setViewportView(nosendTextArea);
            }
            panel4.add(scrollPane3, CC.xy(1, 3));
        }
        add(panel4, CC.xy(1, 9));

        // ======== panel5 ========
        {
            panel5.setLayout(new FormLayout(
                    "133dlu, $lcgap, 50dlu, $lcgap, default:grow",
                    "fill:default"));

            // ---- sendButton ----
            sendButton.setText("\u53d1  \u9001");
            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendButtonActionPerformed(e);
                }
            });
            panel5.add(sendButton, CC.xy(3, 1));
        }
        add(panel5, CC.xy(1, 11));
        // //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY
    // //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jin Lantao
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JScrollPane scrollPane1;
    private JTextArea sendToTextArea;
    private JTabbedPane tabbedPane;
    private JPanel panel2;
    private JCheckBox useHeadCheckBox;
    private JLabel label7;
    private JComboBox titleComboBox;
    private JScrollPane scrollPane2;
    private JTextArea messageTextArea;
    private JLabel label5;
    private JPanel panel3;
    private JLabel label6;
    private JComboBox modelComboBox;
    private JLabel label4;
    private JTextField descriptionTextField;
    private JPanel panel4;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTextArea nosendTextArea;
    private JPanel panel5;
    private JButton sendButton;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
