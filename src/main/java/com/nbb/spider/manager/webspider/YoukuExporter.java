package com.nbb.spider.manager.webspider;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;
import com.nbb.spider.manager.exporter.AbstractExporter;
import com.nbb.spider.manager.exporter.Exporter;

public class YoukuExporter extends AbstractExporter implements Exporter {

	@Override
	public void export(Workbook wb, List<? extends Item> items, String sheetName)
			throws IOException {
		exportItemsToSheet(items, sheetName, wb);
	}

	@Override
	protected void createHeaderExtra(Row row, CellStyle style) {
		Cell cell;
		cell = row.createCell(2);
		cell.setCellValue("Actors");
		cell = row.createCell(3);
		cell.setCellValue("PlayIndex");
	}

	@Override
	protected void exportExtra(Row row, Item item) {
		Youku youku = (Youku) item;
		Cell cell;
		cell = row.createCell(2);
		cell.setCellValue(youku.getActorsList());
		cell = row.createCell(3);
		cell.setCellValue(youku.getTrend());
	}

}
