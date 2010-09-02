package br.com.tg.guiUtil;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

import br.com.tg.entidades.TipoTelefone;

@SuppressWarnings("serial")
public class jcbTipoTelefoneCellEditor extends DefaultCellEditor {

	public jcbTipoTelefoneCellEditor(Vector<TipoTelefone> items) { 
		super(new JComboBox(items)); 
	} 
	
}
