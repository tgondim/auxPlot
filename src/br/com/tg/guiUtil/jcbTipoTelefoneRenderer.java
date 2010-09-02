package br.com.tg.guiUtil;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import br.com.tg.entidades.TipoTelefone;

public class jcbTipoTelefoneRenderer extends JComboBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public jcbTipoTelefoneRenderer(Vector<TipoTelefone> items) { 
		super(items);
		setSelectedIndex(-1);
	} 
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
		if (isSelected) { 
			setForeground(table.getSelectionForeground()); 
			super.setBackground(table.getSelectionBackground()); 
		} else { 
			setForeground(table.getForeground()); 
			setBackground(table.getBackground()); 
		} // Select the current value 
		setSelectedItem(value); 
		return this; 
	}

}
