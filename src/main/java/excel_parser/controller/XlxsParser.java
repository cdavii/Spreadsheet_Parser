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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XlxsParser {

    private static final ArrayList<EntryDTO> listOfEntries = new ArrayList<>();
    private static final ArrayList<EntryDTO> masterList = new ArrayList<>();

    public static String report = "D:/Callum/Documents/#Programming/Report oct nov21.xlsx";
    public static String master = "D:/Callum/Documents/#Programming/MASTER.xlsx";

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

    public static void createEntryList(XSSFSheet sheet) {

        for (Row row : sheet) {
            ArrayList<Object> data = new ArrayList<>();
            int lastColumn = row.getLastCellNum();
            for (int cn = 0; cn < lastColumn; cn++) {
                Cell cell = row.getCell(cn);//, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        data.add(cell.getStringCellValue());
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
            listOfEntries.add(new EntryDTO(data));
        }

    }

    public static void createMasterList(XSSFSheet sheet) {

        for (int rn = 1; rn < sheet.getLastRowNum(); rn++) {
            ArrayList<Object> data = new ArrayList<>();
            for (int cn = 0; cn < 9; cn++) {
                Cell cell = sheet.getRow(rn).getCell(cn);
                switch (cell.getCellType()) {
                    case STRING:
                        data.add(cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell))
                            data.add(new SimpleDateFormat("dd/MM/yyyy").format(cell.getDateCellValue()));
                        else data.add(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case BLANK:
                        data.add("");
                        break;
                }
            }
            masterList.add(new EntryDTO(data));
        }
    }

    public static void main(String[] args) {


//        createEntryList(getSheet(report));
//        for (EntryDTO entry : listOfEntries) System.out.println(entry.toString());

        createMasterList(getSheet(master));

        //for (EntryDTO entry : masterList) System.out.println(entry.toString());



    }
}

