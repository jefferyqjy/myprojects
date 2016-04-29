package com.base.doc.util.jxl;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JXLWriteUtil<T> {

	private String outputPath = null;
	private OutputStream os = null;
	private WritableWorkbook wk = null;
	private Map hmSheet = new HashMap();

	public JXLWriteUtil(String fullFilePath) {
		this.outputPath = fullFilePath;
	}

	public JXLWriteUtil(OutputStream os) {
		this.os = os;
	}

	private WritableWorkbook createExcel() {
		if (wk == null) {
			try {
				if (os != null) {
					wk = Workbook.createWorkbook(os);
				} else {
					wk = Workbook.createWorkbook(new File(outputPath));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wk;
	}

	public WritableSheet setFirstSheetTitles(Title[] titles, String sheetName) {
		WritableSheet sheet = this.getSheet(0, sheetName);
		try {
			int len = titles.length;
			for (int i = 0; i < len; i++) {
				sheet.setColumnView(i, titles[i].getColumnWidth());
				sheet.setRowView(i, titles[i].getColumnHeight());
				sheet.addCell(new Label(i, 0, titles[i].getName(), titles[i].getCellFormat()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public WritableSheet setFisrtSheetCells(List<T> list, String[] names) {
		WritableSheet sheet = this.getSheet(0, null);
		Class clazz = null;
		Object obj = null;
		Method method = null;
		try {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				int length = names.length;
				String returnType = null;
				for (int j = 0; j < length; j++) {
					clazz = list.get(i).getClass();
					method = clazz.getDeclaredMethod(names[j]);
					returnType = method.getReturnType().getName();
					obj = method.invoke(list.get(i));
					if (obj == null) {
						sheet.addCell(new Label(j, i + 1, ""));
					} else {
						sheet.addCell(new Label(j, i + 1, String.valueOf(obj), Style.styleMap.get(returnType)));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	public WritableSheet setFisrtSheetCells(List<T> list, Methods[] methods) {
		WritableSheet sheet = this.getSheet(0, null);
		Class clazz = null;
		Object obj = null;
		Method method = null;
		try {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				int length = methods.length;
				String returnType = null;
				for (int j = 0; j < length; j++) {
					clazz = list.get(i).getClass();
					method = clazz.getDeclaredMethod(methods[j].getName());
					returnType = method.getReturnType().getName();
					obj = method.invoke(list.get(i));
					if (obj == null) {
						sheet.addCell(new Label(j, i + 1, ""));
					} else {
						sheet.addCell(new Label(j, i + 1, String.valueOf(obj), methods[j].getCellFormat()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheet;
	}

	private WritableSheet getSheet(int index, String sheetName) {
		WritableSheet sheet = (WritableSheet) this.hmSheet.get(index);
		if (sheet == null) {
			sheet = this.createExcel().createSheet(sheetName, index);
			this.hmSheet.put(index, sheet);
		}
		return sheet;
	}

	public void generateExcel() {
		try {
			this.createExcel().write();
			this.createExcel().close();
			if (this.os != null) {
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
