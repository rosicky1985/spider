package com.nbb.spider.manager.exporter.impl;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;
import com.nbb.spider.entity.impl.QiyiRank;
import com.nbb.spider.manager.exporter.AbstractExporter;
import com.nbb.spider.manager.exporter.Exporter;

public class QiyiExporter extends AbstractExporter implements Exporter {

	@Override
	public void export(Workbook wb, List<? extends Item> items, String sheetName)
			throws IOException {
		exportItemsToSheet(items, sheetName, wb);
	}

	protected void exportExtra(Row row, Item item) {
		QiyiRank rank = (QiyiRank) item;
		Cell cell;
		cell = row.createCell(2);
		cell.setCellValue(rank.getKeywords());
		cell = row.createCell(3);
		cell.setCellValue(rank.getLastDayIncreased());
	}

	@Override
	protected void createHeaderExtra(Row row, CellStyle style) {
		Cell cell;
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("Keywords");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("LastDayIncreased");
	}
}
