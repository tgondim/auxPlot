package br.com.tg.guiUtil;

import br.com.tg.exceptions.NotResolvableFieldException;

public class BlankHandler implements FieldAccessHandler {
	@Override
	public Class<?> getFieldType() {
		return String.class;
	}

	@Override
	public Class<?> getTraceClassAt(int idx) {
		return String.class;
	}

	@Override
	public Object getValue(Object t, Formatter formatter) {
		return "";
	}

	@Override
	public void resolveField(Class<?> clazz, String expression)
			throws NotResolvableFieldException {
	}

	@Override
	public void setValue(Object t, Object value, Formatter formatter) {
	}

}
