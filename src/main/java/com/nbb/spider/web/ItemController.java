package com.nbb.spider.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nbb.spider.dao.FullItemDao;
import com.nbb.spider.dao.FullItemQuery;
import com.nbb.spider.entity.full.FullItem;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private FullItemDao itemDao;

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@Transactional
	public void export(Date start, Date end, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"spider.csv\"");
		OutputStream os = null;
		PrintWriter out = null;
		try {
			os = response.getOutputStream();
			os.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
			out = new PrintWriter(os);
			out.println(FullItem.csvHeader());
			FullItemQuery q = itemDao.createQuey();
			q.start(start);
			q.end(end);
			Iterator<FullItem> itr = q.list();
			while (itr.hasNext())
				out.println(itr.next().toCsv());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (out != null)
				out.close();
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyyMMdd"), false));
	}
}
