package vendians.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataPool {

    public HashMap<String, String> readExcelData(String excelFileName, String sheetName, String rowToMatch) throws IOException {

        InputStream excelFileTestdata = new FileInputStream("resources/" + excelFileName + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(excelFileTestdata);
        XSSFSheet currentSheet = workbook.getSheet(sheetName);

        // Get the Last ROW and Column number
        XSSFRow titleRow = currentSheet.getRow(0);
        int lastCol = titleRow.getLastCellNum();
        int lastRow = currentSheet.getLastRowNum();

        Object[][] Testdata2DArray = new Object[1][2];
        HashMap<String, String> rowTestdataHashMap = new HashMap<String, String>();

        int rowTestdata = 0;
        for (int row = 1; row <= lastRow; row++) {
            if (currentSheet.getRow(row).getCell(0) == null) {
                rowTestdata = 10000;
            } else if (currentSheet.getRow(row).getCell(0).toString().equals(rowToMatch)) {
                rowTestdata = row;
            }
        }

        XSSFRow currentRow = currentSheet.getRow(rowTestdata);
        Cell firstCell = currentRow.getCell(0);

        for (int col = 0; col < lastCol - 1; col++) {

            if (currentRow.getCell(col + 1) == null) {
                rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(), "null");
            } else {
                rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(), currentRow.getCell(col + 1).getStringCellValue());
            }
        }

        Testdata2DArray[0][0] = firstCell.getStringCellValue();
        Testdata2DArray[0][1] = rowTestdataHashMap.clone();

        workbook.close();
        excelFileTestdata.close();
        return rowTestdataHashMap;
    }


}
