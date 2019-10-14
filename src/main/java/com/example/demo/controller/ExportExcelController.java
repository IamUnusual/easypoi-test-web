package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CehuaItem;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;

@Controller
public class ExportExcelController {
	@RequestMapping("/export")
	@ResponseBody
	public String exportExcel(HttpServletResponse response) throws Exception {
		System.out.println("开始导出>>>>>>>>>>>>>>>>>>>>>>>>>>");

		CehuaItem c = new CehuaItem();
		c.setId(1);
		c.setDes("礼券");
		c.setType(0);
		Date d = new Date();
		c.setAddTime(d);
		c.setModifyTime(d);

		CehuaItem c2 = new CehuaItem();
		c2.setId(2);
		c2.setDes("金币");
		c2.setType(1);
		Date d2 = new Date();
		c2.setAddTime(d2);
		c2.setModifyTime(d2);

		List<CehuaItem> list = new ArrayList<>();

		list.add(c);

		list.add(c2);

		/********************************************************/
		Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(),

				CehuaItem.class, list);
		// 保存数据
		export(response, workbook, "CehuaItem");
		System.out.println("excel导出结束>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		return "SUCCESS";

	}

	/**
	 * export导出请求头设置 浏览器下载excel
	 *
	 * @param response
	 * @param workbook
	 * @param fileName
	 * @throws Exception
	 */
	private static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception {
		response.reset();
		response.setContentType("application/x-msdownload");
		fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		response.setHeader("Content-disposition",
				"attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
		ServletOutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
			workbook.write(outStream);
		} finally {
			outStream.close();
		}
	}

}
