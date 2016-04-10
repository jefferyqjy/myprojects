package com.sys.plugin.doc;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * iText-2.0.8.jar iTextAsian.jar itext-rtf-2.1.7.jar
 * @author Jason Yao
 *
 */
public class WordGenerator implements IDocumentGen {
	/**文档标题*/
	public String MAIN_TITLE = "";
	public Rectangle PAGE_SIZE = PageSize.A4;
	/**文件存放目录*/
	public String FILE_PATH = System.getProperty("user.dir");
	/**页眉内容*/
	public String PAGE_HEAD_LINE = "";
	/**页脚内容*/
	public String PAGE_FOOT_LINE = "";
	public String genDocument(String[] titles,List<String[]> datas,String fileName) {
		Document doc = new Document(PAGE_SIZE);
		int cols = titles.length;
		try {
			if(fileName == null ||  fileName.trim().length() == 0) {
				fileName = this.genFileName();
			}
			String fullFilePath = FILE_PATH + File.separator + fileName + ".doc";
			RtfWriter2.getInstance(doc, new FileOutputStream(new File(fullFilePath)));
			 
			doc.open();
			 // 添加页眉   
	        HeaderFooter header = new HeaderFooter(new Phrase(PAGE_HEAD_LINE), false);   
	        header.setAlignment(Rectangle.ALIGN_CENTER);   
	        doc.setHeader(header); 
	        
	        // 添加页脚   
	        HeaderFooter footer = new HeaderFooter(new Phrase(PAGE_FOOT_LINE), false);   
	        footer.setAlignment(Rectangle.ALIGN_CENTER);   
	        doc.setFooter(footer); 
	        
	        // 设置中文字体  
	        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	        Font contextFont = new Font(bfChinese, 10, Font.NORMAL); 
	        Paragraph context = new Paragraph(MAIN_TITLE,new Font(Font.BOLD,18));  
	        context.setFont(contextFont); 
	        context.add("\n");
	        
	        Table table = new Table(cols);
		   	table.setBorderWidth(1);
		   	table.setBorderColor(Color.BLACK);
		   	table.setPadding(0);
		   	table.setSpacing(0);
		   	Cell cell = null;
		   	for(String sTitle : titles) {
		   		cell = new Cell(sTitle);
		   		cell.setBackgroundColor(Color.LIGHT_GRAY);
		   		table.addCell(cell);
		   	}
		   	int rows = datas.size();
		   	String[] obj = null;
		   	for(int i=0;i<rows;i++) {
		   		obj = datas.get(i);
		   		for(String sValue : obj) {
		   			table.addCell(sValue);
		   		}
		   	}
		   	context.add(table);
		   	doc.add(context);
		   	doc.close();
		   	return fullFilePath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String genFileName() {
		String uuid =  UUID.randomUUID().toString();
		StringTokenizer token = new StringTokenizer(uuid,"-");
		StringBuffer sb = new StringBuffer();
		while(token.hasMoreElements()) {
			sb.append(token.nextToken());
		}
		return sb.toString().toUpperCase();
	}
	
	public static void main(String[] args) {
		WordGenerator gen = new  WordGenerator();
		gen.MAIN_TITLE = "test";
		gen.FILE_PATH="c:\\";
		List list = new ArrayList();
		String[] s1 = new String[]{"fad","要","滚"};
		String[] s2 = new String[]{"fad","要","滚"};
		String[] s3 = new String[]{"fad","要","滚"};
		String[] s4 = new String[]{"fad","要","滚"};
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		gen.genDocument(new String[]{"aa","bb","cc"}, list, null);
	}

}
