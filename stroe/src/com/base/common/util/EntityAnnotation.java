package com.base.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EntityAnnotation {
	//字段解释
	String desc() default "";
	//是否需要显示到页面
	boolean needShow() default true;
	//实体类名称，只有加在id上有效
	String beanName() default "";
	//是否需要更新这个字段
	boolean needUpdate() default true;
	//该字段是否是查询字段
	boolean isQueryField() default false;
	//页面上字段的rule(CHAR,TEXT,RADI,CHEC)
	String rule() default "";
	//字段长度
	int length() default 200;
	boolean isDetailLink() default false;
	boolean isSelectColumn() default false;
}
