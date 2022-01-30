import excel_parser.model.EntryDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static excel_parser.controller.XlxsParser.*;

public class App {
    public static void comparator(String master, String report) {

        ArrayList<EntryDTO> reportList = createReportList(report);
        ArrayList<EntryDTO> masterList = createMasterList(master);
        Map<Integer,Integer> matches = new HashMap<>();

        for (int i = 0; i < masterList.size(); i++) {
            for (int j = 0; j < reportList.size(); j++) {
                if (masterList.get(i).getSupplier().contentEquals(reportList.get(j).getSupplier()) &&
                        masterList.get(i).getDate().equals(reportList.get(j).getDate()) &&
                        masterList.get(i).getNet() == reportList.get(j).getNet()) {
                    matches.put(i,j);
                }
            }
        }
        System.out.println("\nThe following entries do not match master:\n");

        //System.out.println(reportIndices.size() + "|" + masterIndices.size() + "|" + matches.size());

//        for (Integer key : matches.keySet()) {
//            System.out.println(masterList.get(key));
//            System.out.println(reportList.get(matches.get(key)));
//        }

        for (int i = 0; i < matches.values().size(); i++) {
            if (!matches.containsValue(i)) {
                System.out.println(reportList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        comparator(master, report);
    }
}
