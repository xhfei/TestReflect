package excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excel.dto.School;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		ReadExcel xlsxMain = new ReadExcel();
		String fileName = "C:\\Documents and Settings\\Administrator\\桌面\\数据\\重点大学.xlsx";
		xlsxMain.readXlsx(fileName);
	}

	public List<School> readXlsx(String fileName) throws IOException {
		List<School> list = new ArrayList<School>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				if (0 == rowNum) {
					continue;
				}
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				School school = new School();
				// 循环列Cell
				school.getSchoolId();
				XSSFCell intXssfCell = xssfRow.getCell(0);
				school.setSchoolId(getIntegerValue(intXssfCell));
				XSSFCell strXssfCell = xssfRow.getCell(1);
				school.setSchoolName(getStringValue(strXssfCell));
				list.add(school);
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private String getStringValue(XSSFCell xssfCell) {
		return String.valueOf(xssfCell.getStringCellValue());
	}
	@SuppressWarnings("static-access")
	private Integer getIntegerValue(XSSFCell xssfCell) {
		return (int)xssfCell.getNumericCellValue();
	}
}
