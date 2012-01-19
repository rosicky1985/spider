package com.nbb.spider.manager.exporter;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.nbb.spider.entity.Item;

public interface Exporter {
	public void export(Workbook wb, List<? extends Item> items, String sheetName)
			throws IOException;
}