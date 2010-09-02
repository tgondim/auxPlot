package br.com.tg.guiUtil;

/**
 *The class to access the field value.
 * 
 *@author Marcos Vasconcelos
 */
public class FieldResolver {
	private String fieldName;// The field Name.
	private String name;// A name for this field column.
	private Formatter formatter;
	private FieldAccessHandler method;
	private Class<?> owner;

	public FieldResolver(Class<?> clazz, String fieldName, String name) {
		this(clazz, fieldName, name, null);
	}

	public FieldResolver(Class<?> clazz, String fieldName) {
		this(clazz, fieldName, "", null);
	}

	public FieldResolver(Class<?> clazz, String fieldName,
			FieldAccessHandler handler) {
		this(clazz, fieldName, "", handler);
	}

	public FieldResolver(Class<?> clazz, String fieldName, String name,
			FieldAccessHandler handler) {
		if (handler == null)
			handler = new FieldHandler();

		owner = clazz;

		this.fieldName = fieldName;
		this.name = name;

		method = handler;
		method.resolveField(clazz, fieldName);

		setFormatter(new BasicFormatter());
	}

	public void setFormatter(Formatter formatter) {
		if (formatter == null)
			throw new IllegalArgumentException("Formatter can't be null!");
		this.formatter = formatter;
	}

	public void setValue(Object t, Object value) {
		method.setValue(t, value, formatter);
	}

	public Object getValue(Object t) {
		return method.getValue(t, formatter);
	}

	public String getName() {
		return name;
	}

	public Class<?> getFieldType() {
		return method.getFieldType();
	}

	public String getFieldName() {
		return fieldName;
	}

	public Class<?> getOwnerClass() {
		return owner;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	/**
	 *The default Formatter if no one is givem this will be taken.
	 * 
	 *This formatter assume all Object are String.
	 */
	public static class BasicFormatter implements Formatter {
		@Override
		public String format(Object obj) {
			if (obj == null)
				return "";
			return obj.toString();
		}

		@Override
		public Object parse(String obj) {
			return obj;
		}

		@Override
		public String getName() {
			return "string_basic";
		}
	};

	public Class<?> getTraceClassAt(int idx) {
		return method.getTraceClassAt(idx);
	}
}
