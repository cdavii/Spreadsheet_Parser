package excel_parser.controller;

import excel_parser.model.EntryDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XlxsParser {

    public static String report = "src/main/resources/Report oct nov21.xlsx";
    public static String master = "src/main/resources/MASTER.xlsx";

    private static XSSFSheet getSheet(String filepath) {
        XSSFSheet sheet = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filepath);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            sheet = workbook.getSheetAt(0);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet;
    }

    public static ArrayList<EntryDTO> createReportList(String filepath) {
        XSSFSheet sheet = getSheet(filepath);
        ArrayList<EntryDTO> reportList = new ArrayList<>();

        for (Row row : sheet) {
            ArrayList<Object> data = new ArrayList<>();
            int lastColumn = row.getLastCellNum();
            for (int cn = 0; cn < lastColumn; cn++) {
                Cell cell = row.getCell(cn);//, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        if (cell.getStringCellValue().isBlank()) data.add(null);
                        else data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) data.add(cell.getDateCellValue());
                        else data.add(cell.getNumericCellValue());
                        break;
                    case BLANK:
                        data.add(null);
                        break;
                }
            }
            reportList.add(new EntryDTO(data));
        }
    return reportList;
    }

    public static ArrayList<EntryDTO> createMasterList(String filepath) {
        XSSFSheet sheet = getSheet(filepath);
        ArrayList<EntryDTO> masterList = new ArrayList<>();

        for (int rn = 1; rn < sheet.getLastRowNum(); rn++) {
            ArrayList<Object> data = new ArrayList<>();
            int lastColumn = sheet.getRow(rn).getLastCellNum();
            for (int cn = 0; cn < 9; cn++) {
                Cell cell = sheet.getRow(rn).getCell(cn);
                switch (cell.getCellType()) {
                    case STRING:
                        if (cell.getStringCellValue().isBlank()) data.add(null);
                        else data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) data.add(cell.getDateCellValue());
                        else data.add(cell.getNumericCellValue());
                        break;
                    case BLANK:
                        data.add(null);
                        break;
                }
            }
            masterList.add(new EntryDTO(data));
        }
    return masterList;
    }
}

