package br.com.tg.guiUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *A TableModel based on reflection.
 * 
 *@author Marcos Vasconcelos
 */
@SuppressWarnings("serial")
public class ObjectTableModel<T> extends AbstractTableModel {
	private List<T> data;
	private FieldResolver fields[];
	private boolean editDefault;

	public ObjectTableModel(AnnotationResolver resolver, String cols) {
		getData();
		fields = (FieldResolver[]) resolver.resolve(cols).clone();
		editDefault = false;
	}

	public ObjectTableModel(FieldResolver fields[]) {
		getData();
		fields = (FieldResolver[]) fields.clone();
		editDefault = false;
	}

	public void setEditableDefault(boolean editable) {
		editDefault = editable;
	}

	public boolean isCellEditable(int i, int k) {
		return editDefault;
	}

	public int getColumnCount() {
		return fields.length;
	}

	public int getRowCount() {
		return getData().size();
	}

	public Object getValueAt(int arg0, int arg1) {
		try {
			Object obj = getData().get(arg0);
			return fields[arg1].getValue(obj);
		} catch (Exception e) {
			return null;
		}
	}

	public void setValueAt(Object value, int arg0, int arg1) {
		try {
			Object obj = getData().get(arg0);
			fields[arg1].setValue(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T getValue(int arg0) {
		return getData().get(arg0);
	}

	public String getColumnName(int row) {
		return fields[row].getName();
	}

	public void add(T obj) {
		getData().add(obj);
		fireTableDataChanged();
	}

	public void clean() {
		setData(new ArrayList<T>());
		fireTableDataChanged();
	}

	public void setData(List<T> newData) {
		data = newData;
		fireTableDataChanged();
	}

	public void remove(int row) {
		getData().remove(row);
		fireTableDataChanged();
	}

	public List<T> getData() {
		if (data == null) {
			data = new ArrayList<T>();
		}
		return data;
	}

	public void remove(int idx[]) {
		for (int i : idx)
			remove(i);
	}

	public void remove(List<T> objs) {
		for (T t : objs)
			remove(indexOf(t));
	}

	public void remove(T obj) {
		remove(indexOf(obj));
	}

	public void addAll(Collection<T> coll) {
		for (T t : coll)
			add(t);
	}

	public List<T> getList(int idx[]) {
		List<T> list = new ArrayList<T>();
		int size = idx.length;
		for (int j = 0; j < size; j++)
			list.add(getValue(idx[j]));

		return list;
	}

	public int indexOf(T obj) {
		return getData().indexOf(obj);
	}

	public FieldResolver getColumnResolver(int colIndex) {
		return fields[colIndex];
	}
}