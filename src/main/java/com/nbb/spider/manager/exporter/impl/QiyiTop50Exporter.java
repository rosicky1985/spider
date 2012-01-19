package com.nbb.spider.manager.exporter.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.QiyiTop50;
import com.nbb.spider.manager.exporter.AbstractExporter;
import com.nbb.spider.manager.exporter.Exporter;

public class QiyiTop50Exporter extends AbstractExporter implements Exporter {

	@Override
	public void export(Workbook wb, List<? extends Item> items, String sheetName)
			throws IOException {
		exportItemsToSheet(items, sheetName, wb);
	}

	@Override
	protected void createHeaderExtra(Row row, CellStyle style) {
		Cell cell;
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("Actors");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("LastWeek");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("YesterDay");
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("History");
	}

	@Override
	protected void exportExtra(Row row, Item item) {
		QiyiTop50 rank = (QiyiTop50) item;
		Cell cell;
		cell = row.createCell(2);
		cell.setCellValue(rank.getActorsString());
		cell = row.createCell(3);
		cell.setCellValue(rank.getLastWeek());
		cell = row.createCell(4);
		cell.setCellValue(rank.getYesterDay());
		cell = row.createCell(5);
		cell.setCellValue(rank.getHistory());
	}

}
