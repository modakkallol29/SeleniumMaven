package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	/*
	 * https://yandex.com/ ========= 
	 * https://pixabay.com/
	 * https://opensource-demo.orangehrmlive.com/
	 */
	
	private static List<String> details;
	private static HashMap<Integer, List<String>> rowDetails;

	private static boolean skipped = false;

	public static List<String> readRow(String filePath) throws IOException {

		FileInputStream file = new FileInputStream(new File(filePath));

		Workbook excelFile = new XSSFWorkbook(file);
		Sheet sheet = excelFile.getSheetAt(0);

		Iterator<Row> iteratorRow = sheet.iterator();

		details = new ArrayList<String>();

		DataFormatter dataFormatter = new DataFormatter();

		while (iteratorRow.hasNext()) {
			if (!skipped) {
				iteratorRow.next();
				skipped = true;
				continue;
			}

			Row row = iteratorRow.next();

			Iterator<Cell> cellIterator = row.iterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String data = dataFormatter.formatCellValue(cell);
				if (data != "") {
					details.add(data);
				}
			}
		}

		return details;
	}// 4 records>>test case will run 4 times for each set of data

	public static HashMap<Integer, List<String>> readRows(String filePath) throws IOException {
		FileInputStream file = new FileInputStream(new File(filePath));

		Workbook excelFile = new XSSFWorkbook(file);
		Sheet sheet = excelFile.getSheetAt(0);

		Iterator<Row> iteratorRow = sheet.iterator();

		rowDetails = new HashMap<Integer, List<String>>();
		details = new ArrayList<String>();

		DataFormatter dataFormatter = new DataFormatter();

		Integer i = 0;
		while (iteratorRow.hasNext()) {
			if (!skipped) {
				iteratorRow.next();
				skipped = true;
				continue;
			}

			Row row = iteratorRow.next();

			if (row == null)
				System.out.println("null");

			Iterator<Cell> cellIterator = row.iterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				String data = dataFormatter.formatCellValue(cell);
				if (data != "") {
					details.add(data);
				}
			}
			rowDetails.put(i, new ArrayList<String>(details));
			details.clear();
			i++;
		}
		System.out.println(rowDetails.size());
		return rowDetails;
	}

	public static List<String> readAnyRow(String filePath, int rowNo) throws IOException {

		FileInputStream file = new FileInputStream(new File(filePath));

		Workbook excelFile = new XSSFWorkbook(file);
		Sheet sheet = excelFile.getSheetAt(0);

		Iterator<Row> iteratorRow = sheet.iterator();

		details = new ArrayList<String>();

		DataFormatter dataFormatter = new DataFormatter();

		Row row = sheet.getRow(rowNo);
		if (row == null) {
			System.out.println("null");
			return null;
		}
		Iterator<Cell> cellIterator = row.iterator();

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String data = dataFormatter.formatCellValue(cell);
			if (data != "") {
				details.add(data);
			}
		}

		return details;
	}

	public static HashMap<Integer, List<String>> readRowsTest(String filePath) throws IOException {
		FileInputStream file = new FileInputStream(new File(filePath));

		Workbook excelFile = new XSSFWorkbook(file);
		Sheet sheet = excelFile.getSheetAt(0);

		Iterator<Row> iteratorRow = sheet.iterator();

		rowDetails = new HashMap<Integer, List<String>>();
		details = new ArrayList<String>();

		DataFormatter dataFormatter = new DataFormatter();

		Integer i = 0;

//		for (Row row : sheet) {
//			if (row.getCell(0).getStringCellValue() == "")
//				System.out.println("Null");
//		}
		while (iteratorRow.hasNext()) {
			if (!skipped) {
				iteratorRow.next();
				skipped = true;
				continue;
			}

			Row row = iteratorRow.next();

			if (row == null)
				System.out.println("null");

			Iterator<Cell> cellIterator = row.iterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				String data = dataFormatter.formatCellValue(cell);

				if (cell.getColumnIndex() == 0 && data == "")
					return rowDetails;
				if (data != "" || data!=null) {
					details.add(data);
				}
			}
			if (details!=null)
			{
				rowDetails.put(i, new ArrayList<String>(details));
				details.clear();
			}

			
			i++;
		}
		excelFile.close();
		return rowDetails;
	}
}
