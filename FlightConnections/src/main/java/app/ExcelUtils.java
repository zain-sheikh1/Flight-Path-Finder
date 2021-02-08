package app;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

public class ExcelUtils {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, String sheetName) throws IOException {
		workbook = new XSSFWorkbook(excelPath);
		sheet = workbook.getSheet(sheetName);
	}
	
	public  int getRowCount() throws IOException{
		return sheet.getPhysicalNumberOfRows();
	}
	
	public  String getCellData(int rowNum, int columnNum) throws IOException {
		return sheet.getRow(rowNum).getCell(columnNum).getStringCellValue();
	}
}
