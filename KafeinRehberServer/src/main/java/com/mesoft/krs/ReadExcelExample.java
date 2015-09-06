package com.mesoft.krs;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelExample {

	private static List<Contact> phoneList = null;

	public static List<Contact> getPhoneList() {

		if (phoneList == null) {
			ReadExcelExample
					.readExcel("/home/programs/KafeinRehberServer-1.0/conf/liste.xls");
		}

		return phoneList;
	}

	public static void setPhoneList(List<Contact> phoneList) {
		ReadExcelExample.phoneList = phoneList;
	}

	public static void readExcel(String filename) {

		phoneList = new ArrayList<Contact>();

		try {

			FileInputStream file = new FileInputStream(new File(filename));

			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows from first sheet
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// For each row, iterate through each columns
				Iterator<Cell> cellIterator = row.cellIterator();

				String name = cellIterator.next().getStringCellValue();
				String number = cellIterator.next().getStringCellValue();

				Contact c = new Contact();
				c.setName(name);
				c.setNumber(number);

				phoneList.add(c);

			}
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
