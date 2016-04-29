package com.base.doc.util.jxl;

import java.util.HashMap;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;

public class Style {
	public static final String TYPE_STRING = "java.lang.String";
	public static final String TYPE_INT = "int";
	public static final String TYPE_DATE = "java.util.Date";
	public static final String TYPE_AMOUNT = "java.math.BigDecimal";
	
	public static Map<String, WritableCellFormat> styleMap = new HashMap<String, WritableCellFormat>();

	static {
		WritableCellFormat leftAlign = new  WritableCellFormat();
		WritableCellFormat centerAlign = new  WritableCellFormat();
		WritableCellFormat rightAlign = new  WritableCellFormat();
		DateFormat df = new DateFormat("dd/mm/yyyy");  
		WritableCellFormat dateFormat = new  WritableCellFormat(df);
		try {
			leftAlign.setAlignment(Alignment.LEFT);
			leftAlign.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			centerAlign.setAlignment(Alignment.CENTRE);
			centerAlign.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			rightAlign.setAlignment(Alignment.RIGHT);
			rightAlign.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			dateFormat.setAlignment(Alignment.CENTRE);
			dateFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			styleMap.put(TYPE_STRING, leftAlign);
			styleMap.put(TYPE_AMOUNT, rightAlign);
			styleMap.put(TYPE_DATE, dateFormat);
			styleMap.put(TYPE_INT, centerAlign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
