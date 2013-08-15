package org.pccpa.api;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrmTable {
	String name();

	String pk();

	String[] excludeColumns() default {};

	boolean insertPk() default true;

	String oracleSeqIdRegexp() default "";
	
	
}
