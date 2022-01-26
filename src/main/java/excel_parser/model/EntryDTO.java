package excel_parser.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntryDTO {

    private String supplier;
    private String date;
    private String purchaseOrderNumber;
    private String contract;
    private String invoiceNumberAndDate;
    private String client;
    private String net;
    private String vat;
    private String gross;

    public EntryDTO(ArrayList<Object> data){
        this.setSupplier(String.valueOf(data.get(0)));
        this.setDate(String.valueOf(data.get(1)));
        this.setPurchaseOrderNumber(String.valueOf(data.get(2)));
        this.setContract(String.valueOf(data.get(3)));
        this.setInvoiceNumberAndDate((String) data.get(4));
        this.setClient(String.valueOf(data.get(5)));
        this.setNet(String.valueOf(data.get(6)));
        this.setVat(String.valueOf(data.get(7)));
        this.setGross(String.valueOf(data.get(8)));
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getInvoiceNumberAndDate() {
        return invoiceNumberAndDate;
    }

    public void setInvoiceNumberAndDate(String invoiceNumberAndDate) {
        this.invoiceNumberAndDate = invoiceNumberAndDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "[" + supplier +
                ", " + date +
                ", " + purchaseOrderNumber +
                ", " + contract +
                ", " + invoiceNumberAndDate +
                ", " + client +
                ", £" + net +
                ", £" + vat +
                ", £" + gross +
                ']';
    }
}
