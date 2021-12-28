package excel_parser.controller;

import excel_parser.model.EntryDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XlxsParser {

    private static final ArrayList<EntryDTO> listOfEntries = new ArrayList<>();

    public static XSSFSheet getSheet(String filepath) {
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

    public static void createEntryData(XSSFSheet sheet) {

        for (Row row : sheet) {
            ArrayList<Object> data = new ArrayList<>();
            int lastColumn = row.getLastCellNum();
            for (int cn = 0; cn < lastColumn; cn++) {
                Cell cell = row.getCell(cn);//, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                switch (cell.getCellType()) {
                    case STRING:
                        data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) data.add(cell.getDateCellValue());
                        else data.add(cell.getNumericCellValue());
                        break;
                    case BLANK:
                        data.add(null);
                }
            }
            listOfEntries.add(new EntryDTO(data));
        }

    }

    public static void parseMasterWorkbook(XSSFSheet sheet) {

        for (int rn = 2; rn < sheet.getLastRowNum(); rn++) {
            ArrayList<Object> data = new ArrayList<>();
            int lastColumn = sheet.getRow(rn).getLastCellNum();
            for (int cn = 0; cn < 9; cn++) {
                Cell cell = sheet.getRow(rn).getCell(cn);//, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                switch (cell.getCellType()) {
                    case STRING:
                        data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) data.add(cell.getDateCellValue());
                        else data.add(cell.getNumericCellValue());
                        break;
                    case BLANK:
                        data.add(null);
                }
            }
            listOfEntries.add(new EntryDTO(data));
        }

    }



    public static void main(String[] args) {
        createEntryData(getSheet("D:/Callum/Documents/#Programming/Report oct nov21.xlsx"));

        for (int i = 0; i < listOfEntries.size(); i++) {
            System.out.println(listOfEntries.get(i).toString());
        }

    }
}

