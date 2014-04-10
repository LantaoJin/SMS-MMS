/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.alanjin.smsmms.frontend;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Administrator
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer{

	public CheckBoxRenderer () {
		this.setBorderPainted(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {	
		return this;
        }
}
