package com.nbb.spider.manager.exporter;

import com.nbb.spider.entity.Item;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

public abstract interface Exporter
{
  public abstract void export(Workbook paramWorkbook, List<? extends Item> paramList, String paramString)
    throws IOException;
}

