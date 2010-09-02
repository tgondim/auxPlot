package br.com.tg.guiUtil;

import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.tg.entidades.Telefone;


public class JcbTipoTelefoneModel extends AbstractListModel implements ComboBoxModel {

	private static final long serialVersionUID = 1L;
	
	protected Vector<Telefone> elementos = new Vector<Telefone>();
	
	public JcbTipoTelefoneModel(Vector<Telefone> newElementos) {
		elementos = newElementos;
	}

	@Override
	public Object getSelectedItem() {
		return null;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object getElementAt(int index) {
		return elementos.get(index);
	}

	@Override
	public int getSize() {
		return elementos.size();
	}

}
