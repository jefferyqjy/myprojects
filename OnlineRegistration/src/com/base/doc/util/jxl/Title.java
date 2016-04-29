package com.base.doc.util.jxl;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;

public class Title {

	private String name = "";
	private Colour backColor = Colour.GRAY_25;
	private Alignment align = Alignment.CENTRE;
	private VerticalAlignment verticalAlign = VerticalAlignment.CENTRE;
	private boolean isNeedWrap = false;
	private int columnWidth = 20;
	private int columnHeight = 400;

	public Title(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(int columnWidth) {
		this.columnWidth = columnWidth;
	}

	public void setBackColor(Colour backColor) {
		this.backColor = backColor;
	}

	public Alignment getAlign() {
		return align;
	}

	public void setAlign(Alignment align) {
		this.align = align;
	}

	public VerticalAlignment getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(VerticalAlignment verticalAlign) {
		this.verticalAlign = verticalAlign;
	}

	public int getColumnHeight() {
		return columnHeight;
	}

	public void setColumnHeight(int columnHeight) {
		this.columnHeight = columnHeight;
	}

	public Colour getBackColor() {
		return backColor;
	}

	public void setNeedWrap(boolean isNeedWrap) {
		this.isNeedWrap = isNeedWrap;
	}

	public WritableCellFormat getCellFormat() {
		WritableCellFormat cellFormat = new WritableCellFormat();
		try {
			cellFormat.setAlignment(this.align);
			cellFormat.setVerticalAlignment(this.verticalAlign);
			cellFormat.setBackground(this.backColor);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat.setWrap(this.isNeedWrap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellFormat;
	}

}
