package com.taxation.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.taxation.global.exception.FileStorageException;
import com.taxation.global.exception.MyFileNotFoundException;
import com.taxation.model.Property;
import com.taxation.model.TaxDetail;

@Service
public class ReportService {
	
	public	Map<String, Object[]> createAllPaidAndDueInfo(List<TaxDetail>	taxDetails,String	all){
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "SAMAGRA ID", "PROPERTY	UNIQUE ID", "SUBHOLDER NAME","RESIDENT NAME","AMOUNT "+all,"Date" });
		int	counter=2;
		for(int	i=0;i<taxDetails.size();i++){
			TaxDetail	td	=	taxDetails.get(i);
			Property	p=td.getProperty();
			empinfo.put(counter+"", new Object[] { p.getSamagraId(), p.getCustomUniqueId(), p.getSubHolder(),p.getResidentName(),td.getAmount(),String.valueOf(td.getLastTaxPaidOn()) });
			counter++;
		}
		
		return	empinfo;
	}

	public Resource getReport(Map<String, Object[]> empinfo) throws Exception {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Tax Info");

		// Create row object
		XSSFRow row;

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			
			/*CellStyle c=workbook.createCellStyle();
			c.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			row.setRowStyle(c);
			*/
			
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				XSSFCell cell = row.createCell(cellid++);
				if(obj instanceof Float){
					cell.setCellValue((Float)obj);
				}else
				cell.setCellValue((String) obj);
			}
		}
		
		if(keyid.size()>1){
		
		XSSFRow rowTotal = spreadsheet.createRow(rowid + 2);
		
		XSSFCell cellTotalText = rowTotal.createCell(3);
        cellTotalText.setCellValue("Total:");
         
        XSSFCell cellTotal = rowTotal.createCell(4);
        cellTotal.setCellType(CellType.FORMULA);
        cellTotal.setCellFormula("SUM(E2:E"+rowid+")");

		}
   

		String fileName = UUID.randomUUID() + "TaxReport.xlsx";
		Path p = Paths.get("./tempUploads").toAbsolutePath().normalize();
		try {
			Files.createDirectories(p);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
		FileUtils.cleanDirectory(new File("./tempUploads"));
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("./tempUploads/" + fileName));
		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");
		return loadFileAsResource(fileName, p);
	}

	public Resource loadFileAsResource(String fileName, Path p) {
		try {
			Path filePath = p.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}
