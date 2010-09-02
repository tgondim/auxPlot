package br.com.tg.guiUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.tg.guiUtil.FieldResolver.BasicFormatter;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Resolvable {
	public String colName() default "";

	public Class<?> formatter() default BasicFormatter.class;

	public Class<?> accessMethod() default FieldHandler.class;
}
