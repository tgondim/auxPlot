package br.com.tg.guiUtil;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

import br.com.tg.entidades.Telefone;
import br.com.tg.entidades.TipoTelefone;

public class TelefoneTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	public static final int TIPO_TELEFONE_INDEX = 0;
	public static final int CODIGO_AREA_INDEX = 1;
	public static final int NUMERO_INDEX = 2;
	public static final int TIPO_TELEFONE_ID_INDEX = 3;

	protected String[] columnNames;
	protected Vector<Object> dataVector;
	
	public TelefoneTableModel(String[] newColumnNames) {
        this.columnNames = newColumnNames;
        this.dataVector = new Vector<Object>();
	}
	
	public String getColumnNames(int column) {
		return columnNames[column];
	}

    public boolean isCellEditable(int row, int column) {
        if (column == TIPO_TELEFONE_ID_INDEX) return false;
        else return true;
    }
    
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case TIPO_TELEFONE_INDEX:
            	return JComboBox.class;
            case CODIGO_AREA_INDEX:
            case NUMERO_INDEX:
            	return String.class;
            default:
               return Object.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
    	Telefone telefone;
    	if (dataVector.firstElement() == null) {
    		telefone = new Telefone();
    	}
        telefone = (Telefone)dataVector.get(row);
        switch (column) {
            case TIPO_TELEFONE_INDEX:
            	return telefone.getTipoTelefone();
            case CODIGO_AREA_INDEX:
            	return telefone.getCodigoArea();
            case NUMERO_INDEX:
            	return telefone.getNumero();
            default:
            	return new Object();
        }
    }
    
    public void setValueAt(Object value, int row, int column) {
        Telefone telefone = (Telefone)dataVector.get(row);
        switch (column) {
        case TIPO_TELEFONE_INDEX:
        	telefone.setTipoTelefone((TipoTelefone)value);
        	break;
        case CODIGO_AREA_INDEX:
        	telefone.setCodigoArea((String)value);
        	break;
        case NUMERO_INDEX:
        	telefone.setNumero((String)value);
        	break;
        default:
        	System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        Telefone telefone = (Telefone)dataVector.get(dataVector.size() - 1);
        if (telefone.getTipoTelefone().getDescricao().trim().equals("") &&
        		telefone.getCodigoArea().trim().equals("") &&
        		telefone.getNumero().trim().equals(""))
        {
           return true;
        }
        else return false;
    }

    public void addEmptyRow() {
        dataVector.add(new Telefone());
        fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }
    
    public void addRow(Telefone newTelefone) {
    	dataVector.add(newTelefone);
    	fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }
    
    public void removeRow(int row) {
    	dataVector.removeElementAt(row);
    }
    
    public void clearTable() {
    	dataVector = new Vector<Object>();
    }

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}
	
	@Override
	public String getColumnName(int column){  
		return columnNames[column];  
	}  
	
	public boolean isEmpty() {
		if (dataVector.firstElement() == null) {
			return true;
		}
		return false;
	}
	
	public List<Telefone> getList() {
		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		for (Iterator<Object> i = dataVector.iterator(); i.hasNext();) {
			listaTelefone.add((Telefone)i.next());
		}
		return listaTelefone;
	}
	
}
