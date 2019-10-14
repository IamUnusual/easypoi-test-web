package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CehuaItem;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

@RestController
public class ImportExcelController {
	@RequestMapping("/import")
	public String importExcel(@RequestParam MultipartFile file) throws IOException {
		/*------接收MultipartFile转化成File--------*/
		File toFile = null;
		if (file.equals("") || file.getSize() <= 0) {
			file = null;
		} else {
			InputStream ins = null;
			ins = file.getInputStream();
			toFile = new File(file.getOriginalFilename());
			inputStreamToFile(ins, toFile);
			ins.close();
		}
		// ------Excel转化成对象------------//
		ImportParams params = new ImportParams();
		long start = new Date().getTime();
		List<CehuaItem> list = ExcelImportUtil.importExcel(toFile, CehuaItem.class, params);
		for (CehuaItem cehuaItem : list) {
			System.out.println(cehuaItem);
		}
		System.out.println(new Date().getTime() - start);
		return "1";
	}

	/**
	 * MultipartFile转化成File
	 * 
	 * @param ins
	 * @param file
	 * @author Administrator
	 */
	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
