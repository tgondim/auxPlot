package br.com.tg.guiUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import br.com.tg.entidades.Pessoa;
import br.com.tg.entidades.PessoaFisica;

public class PessoaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	public static final int ID_INDEX = 0;
	public static final int NOME_INDEX = 1;
	public static final int TIPO_PESSOA_INDEX = 2;
	public static final int LOGRADOURO_INDEX = 3;
	public static final int NUMERO_INDEX = 4;
	public static final int BAIRRO_INDEX = 5;
	public static final int CIDADE_INDEX = 6;
	public static final int UF_INDEX = 7;

	protected String[] columnNames;
	protected Vector<Object> dataVector;
	
	public PessoaTableModel(String[] newColumnNames) {
        this.columnNames = newColumnNames;
        this.dataVector = new Vector<Object>();
	}
	
	public String getColumnNames(int column) {
		return columnNames[column];
	}

    public boolean isCellEditable(int row, int column) {
        if (column == ID_INDEX || column == NOME_INDEX
        		|| column == TIPO_PESSOA_INDEX
        		|| column == LOGRADOURO_INDEX
        		|| column == NUMERO_INDEX
        		|| column == BAIRRO_INDEX
        		|| column == CIDADE_INDEX
        		|| column == UF_INDEX) return false;
        else return true;
    }
    
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case ID_INDEX:
            	return Integer.class;
            case NOME_INDEX:
            	return String.class;
            case TIPO_PESSOA_INDEX:
            	return String.class;
            case LOGRADOURO_INDEX:
            	return String.class;
            case NUMERO_INDEX:
            	return Integer.class;
            case BAIRRO_INDEX:
            	return String.class;
            case CIDADE_INDEX:
            	return String.class;
            case UF_INDEX:
               return String.class;
            default:
               return Object.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        Pessoa pessoa = (Pessoa)dataVector.get(row);
        switch (column) {
            case ID_INDEX:
            	return pessoa.getId();
            case NOME_INDEX:
            	return pessoa.getNome();
            case TIPO_PESSOA_INDEX:
            	return pessoa.getTipoPessoa().getDescricao();
            case LOGRADOURO_INDEX:
            	return pessoa.getEndereco().getLogradouro();
            case NUMERO_INDEX:
            	return pessoa.getEndereco().getNumero();
            case BAIRRO_INDEX:
            	return pessoa.getEndereco().getBairro();
            case CIDADE_INDEX:
            	return pessoa.getEndereco().getCidade();
            case UF_INDEX:
            	return pessoa.getEndereco().getUf();
            default:
            	return new Object();
        }
    }
    
    public void setValueAt(Object value, int row, int column) {
        Pessoa pessoa = (Pessoa)dataVector.get(row);
        switch (column) {
        case ID_INDEX:
        	pessoa.setId((Integer)value);
        	break;
        case NOME_INDEX:
        	pessoa.setNome((String)value);
        	break;
        case TIPO_PESSOA_INDEX:
        	pessoa.getTipoPessoa().setDescricao((String)value);
        	break;
        case LOGRADOURO_INDEX:
        	pessoa.getEndereco().setLogradouro((String)value);
        	break;
        case NUMERO_INDEX:
        	pessoa.getEndereco().setNumero((Integer)value);
        	break;
        case BAIRRO_INDEX:
        	pessoa.getEndereco().setBairro((String)value);
        	break;
        case CIDADE_INDEX:
        	pessoa.getEndereco().setCidade((String)value);
        	break;
        case UF_INDEX:
        	pessoa.getEndereco().setUf((String)value);
        	break;
        default:
        	System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public boolean hasEmptyRow() {
        if (dataVector.size() == 0) return false;
        Pessoa pessoa = (Pessoa)dataVector.get(dataVector.size() - 1);
        if (pessoa.getNome().trim().equals("") &&
        		pessoa.getTipoPessoa().getDescricao().trim().equals("") &&
        		pessoa.getEndereco().getLogradouro().trim().equals("") &&
        		pessoa.getEndereco().getBairro().trim().equals("") &&
        		pessoa.getEndereco().getCidade().trim().equals("") &&
        		pessoa.getEndereco().getUf().trim().equals("")) 
        {
           return true;
        }
        else return false;
    }

    public void addEmptyRow() {
        dataVector.add(new PessoaFisica());
        fireTableRowsInserted(
           dataVector.size() - 1,
           dataVector.size() - 1);
    }
    
    public void addRow(Pessoa newPessoa) {
    	dataVector.add(newPessoa);
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
	
	public int getRow(Pessoa pessoa) {
		int indice = dataVector.indexOf(pessoa);
		return indice;
	}
	
	public List<Pessoa> getList() {
		List<Pessoa> listaPessoa = new ArrayList<Pessoa>();
		for (Iterator<Object> i = dataVector.iterator(); i.hasNext();) {
			listaPessoa.add((Pessoa)i.next());
		}
		return listaPessoa;
	}


}
