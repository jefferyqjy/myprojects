package com.sys.plugin.doc;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sys.common.util.UUIDGenerator;

public class PDFWriteUtil<T> {

	public String filePath = System.getProperty("user.dir");
	public String fileName = UUIDGenerator.genFileName() + ".pdf";

	private Document document = null;
	private PdfPTable table = null;

	public String getFullFilePath() {
		return filePath + File.separator + fileName;
	}

	private PDFWriteUtil(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	private Document createDocument() {
		if (document == null) {
			document = new Document();
			try {
				PdfWriter.getInstance(document, new FileOutputStream(this
						.getFullFilePath()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.open();
		}
		return document;
	}
	
	private Font genChineseFont() {
		try {
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",  
                    "UniGB-UCS2-H", false);  
  
            Font bold_fontChinese = new Font(bfChinese, 12, Font.BOLD,  
                    Color.BLACK);  
			return bold_fontChinese;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Document setTableTitle(String[] titles) {
		this.createDocument();
		if (this.table == null) {
			this.table = new PdfPTable(titles.length);
		}
		PdfPCell cell = null;
		try {
			for (String title : titles) {
				cell = new PdfPCell(new Paragraph(title, this.genChineseFont()));
				table.addCell(cell);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.document;
	}
	
	public PdfPTable setTableContent(List<T> list, String[] names) {
		Class clazz = null;
		Object obj = null;
		Method method = null;
		try {
			int len = list.size();
			for(int i=0;i<len;i++) {
				int length = names.length;
				for(int j=0;j<length;j++) {
					clazz = list.get(i).getClass();
					method = clazz.getDeclaredMethod(names[j]);
					obj = method.invoke(list.get(i));
					if(obj == null) {
						this.table.addCell(new PdfPCell(new Paragraph("", this.genChineseFont())));
					} else {
						this.table.addCell(new PdfPCell(new Paragraph(String.valueOf(obj), this.genChineseFont())));
					}
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return this.table;
	}
	
	
	public void genPdf() {
		try {
			this.document.add(this.table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		document.close();
	}

	public static PDFWriteUtil getInstance(String filePath, String fileName) {
		return new PDFWriteUtil(filePath, fileName);
	}

	public static void main(String[] args) throws Exception {
		PDFWriteUtil<Object> util = PDFWriteUtil
				.getInstance("c:\\", "test.pdf");
		Document document = util.setTableTitle(new String[] { "客户信息", "身份额在",
				"3" });
		util.genPdf();
	}
}
