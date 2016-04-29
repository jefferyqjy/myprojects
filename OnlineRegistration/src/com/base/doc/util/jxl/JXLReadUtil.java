package com.base.doc.util.jxl;


import java.io.File;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
/** 
 * 不支持2007 
 * @author  
 * 
 */  
public class JXLReadUtil {  
    private String filePath = null;  
    private File file = null;  
    private Workbook wk = null;  
    private Map hmSheet = new HashMap();  
  
    private JXLReadUtil(String filePath) {  
        this.filePath = filePath;  
        this.file = new File(filePath);  
    }  
  
    private JXLReadUtil(File file) {  
        this.file = file;  
    }  
  
    public static JXLReadUtil getInstance(String filePath) {  
        return new JXLReadUtil(filePath);  
    }  
  
    public static JXLReadUtil getInstance(File file) {  
        return new JXLReadUtil(file);  
    }  
  
    public Workbook getWorkbook() {  
        if (wk == null) {  
            try {  
                wk = Workbook.getWorkbook(this.file);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return wk;  
    }  
  
    /** 
     *  
     * @param index 
     *            start with 0 
     * @return 
     */  
    public Sheet getSheet(int index) {  
        wk = getWorkbook();  
        if (hmSheet.containsKey(index)) {  
            return (Sheet) hmSheet.get(index);  
        } else {  
            Sheet sheet = wk.getSheet(index);  
            hmSheet.put(index, sheet);  
            return sheet;  
        }  
    }  
  
    /** 
     *  
     * @param sheet 
     * @param row 
     *            start with 0 
     * @return 
     */  
    public String[] getContentsViaRow(Sheet sheet, int row) {  
        Cell[] rowCells = sheet.getRow(row);  
        int len = rowCells.length;  
        String[] strCells = new String[len];  
        for (int i = 0; i < len; i++) {  
            strCells[i] = rowCells[i].getContents();  
        }  
        return strCells;  
    }  
  
    /** 
     *  
     * @param sheet 
     * @param col 
     *            start with 0 
     * @return 
     */  
    public String[] getContentsViaCol(Sheet sheet, int col) {  
        Cell[] cells = sheet.getColumn(col);  
        int len = cells.length;  
        String[] strCols = new String[len];  
        Cell c = null;  
        for (int i = 0; i < len; i++) {  
            c = cells[i];  
            strCols[i] = c.getContents();  
        }  
        return strCols;  
    }  
      
    public List<String[]> getFirstSheetRowsContents() {  
        Sheet sheet = this.getSheet(0);  
        int rows = sheet.getRows();  
        List<String[]> ls = new ArrayList<String[]>();  
        for(int i=0;i<rows;i++) {  
            ls.add(getContentsViaRow(sheet,i));  
        }  
        return ls;  
    }  
      
    public List<String[]> getFirstSheetColsContents() {  
        Sheet sheet = this.getSheet(0);  
        int cols = sheet.getColumns();  
        List<String[]> ls = new ArrayList<String[]>();  
        for(int i=0;i<cols;i++) {  
            ls.add(getContentsViaCol(sheet,i));  
        }  
        return ls;  
    }  
      
    public static void main(String[] args) throws Exception {  
        JXLReadUtil util = JXLReadUtil.getInstance("f:\\CUST01.xls");  
        List<String[]> ls = util.getFirstSheetRowsContents();  
        for(String[] ss : ls) {  
            for(String s : ss) {  
                System.out.println(s);  
            }  
        }  
          
        List<String[]> lss = util.getFirstSheetColsContents();  
        for(String[] ss : lss) {  
            for(String s : ss) {  
                System.out.println(s);  
            }  
        }  
    }  
}  