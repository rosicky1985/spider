package com.nbb.spider.manager.exporter;

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

import com.nbb.spider.entity.Item;

public abstract class AbstractExporter {

	public AbstractExporter() {
		super();
	}

	protected void exportItemsToSheet(List<? extends Item> items,
			String sheetName, Workbook wb) throws FileNotFoundException,
			IOException {
		Sheet sheet = wb.createSheet(sheetName);
		createHearder(sheet);
		Row row;
		Cell cell;
		for (int r = 0; r < items.size(); r++) {
			Item item = items.get(r);
			row = sheet.createRow(r + 1);
			cell = row.createCell(0);
			cell.setCellValue(item.getRank());
			cell = row.createCell(1);
			cell.setCellValue(item.getTitle());
			exportExtra(row, item);
		}
		for (int i = 0; i < 10; i++) {
			sheet.autoSizeColumn(i);
		}
	}

	protected void createHearder(Sheet sheet) {
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
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	abstract protected void createHeaderExtra(Row row, CellStyle style);

	public void saveWorkBook(Workbook wb, String destination)
			throws FileNotFoundException, IOException {
		FileOutputStream fileOut = new FileOutputStream(destination);
		wb.write(fileOut);
		fileOut.close();
	}

	public Workbook createWorkBook() {
		return new HSSFWorkbook();
	}

	protected abstract void exportExtra(Row row, Item item);
}