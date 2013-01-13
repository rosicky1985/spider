package com.nbb.spider.manager.exporter.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.KanKan;
import com.nbb.spider.manager.exporter.AbstractExporter;

public class KanKanExporter extends AbstractExporter {
	public void export(Workbook wb, List<? extends Item> items, String sheetName)
			throws IOException {
		exportItemsToSheet(items, sheetName, wb);
	}

	@Override
	protected void createHeaderExtra(Row row, CellStyle style) {
		Cell cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("Area");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("MainActor");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("Category");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("index");
	}

	@Override
	protected void exportExtra(Row row, Item item) {
		KanKan rank = (KanKan) item;
		Cell cell = row.createCell(2);
		cell.setCellValue(rank.getArea());
		cell = row.createCell(3);
		cell.setCellValue(rank.getMainActor());
		cell = row.createCell(4);
		cell.setCellValue(rank.getCategory());
		cell = row.createCell(5);
		cell.setCellValue(rank.getIndex());
	}

}
