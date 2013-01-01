 package com.nbb.spider.manager.exporter;
 
 import com.nbb.spider.entity.Item;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.util.List;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.ss.usermodel.Cell;
 import org.apache.poi.ss.usermodel.CellStyle;
 import org.apache.poi.ss.usermodel.Font;
 import org.apache.poi.ss.usermodel.Row;
 import org.apache.poi.ss.usermodel.Sheet;
 import org.apache.poi.ss.usermodel.Workbook;
 
 public abstract class AbstractExporter
 {
   protected void exportItemsToSheet(List<? extends Item> items, String sheetName, Workbook wb)
     throws FileNotFoundException, IOException
   {
     Sheet sheet = wb.createSheet(sheetName);
     createHearder(sheet);
 
     if (items != null) {
       for (int r = 0; r < items.size(); ++r) {
         Item item = (Item)items.get(r);
         Row row = sheet.createRow(r + 1);
         Cell cell = row.createCell(0);
         cell.setCellValue(item.getRank().intValue());
         cell = row.createCell(1);
         cell.setCellValue(item.getTitle());
         exportExtra(row, item);
       }
       for (int i = 0; i < 10; ++i)
         sheet.autoSizeColumn(i);
     }
   }
 
   protected void createHearder(Sheet sheet)
   {
     Row row = sheet.createRow(0);
     Cell cell = row.createCell(0);
     CellStyle style = headerStyle(sheet, cell);
     cell.setCellStyle(style);
     cell.setCellValue("Rank");
     cell = row.createCell(1);
     cell.setCellStyle(style);
     cell.setCellValue("Title");
     createHeaderExtra(row, style);
   }
 
   protected CellStyle headerStyle(Sheet sheet, Cell cell) {
     CellStyle style = cell.getCellStyle();
     Font font = sheet.getWorkbook().createFont();
     font.setFontName("Courier New");
     font.setBoldweight((short) 700);
     style.setFont(font);
     return style;
   }
 
   protected abstract void createHeaderExtra(Row paramRow, CellStyle paramCellStyle);
 
   public void saveWorkBook(Workbook wb, String destination) throws FileNotFoundException, IOException {
     FileOutputStream fileOut = new FileOutputStream(destination);
     wb.write(fileOut);
     fileOut.close();
   }
 
   public Workbook createWorkBook() {
     return new HSSFWorkbook();
   }
 
   protected abstract void exportExtra(Row paramRow, Item paramItem);
 }

