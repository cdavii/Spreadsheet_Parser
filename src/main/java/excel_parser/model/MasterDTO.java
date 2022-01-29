package excel_parser.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MasterDTO {

    private String supplier;
    private Date date;
    private int purchaseOrderNumber;
    private String contract;
    private String invoiceNumberAndDate;
    private String client;
    private double net;
    private double vat;
    private double gross;

    public MasterDTO(ArrayList<Object> data){
        this.setSupplier(String.valueOf(data.get(0)));
        this.setDate((Date) data.get(1));
        this.setPurchaseOrderNumber(Double.parseDouble(String.valueOf(data.get(2))));
        this.setContract(String.valueOf(data.get(3)));
        this.setInvoiceNumberAndDate((String) data.get(4));
        this.setClient(String.valueOf(data.get(5)));
        this.setNet((double) data.get(6));
        this.setVat((double) data.get(7));
        this.setGross((double) data.get(8));
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(Double purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber.intValue();
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
        if (invoiceNumberAndDate == null) {
            this.invoiceNumberAndDate = "";
        }else this.invoiceNumberAndDate = invoiceNumberAndDate;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getGross() {
        return gross;
    }

    public void setGross(double gross) {
        this.gross = gross;
    }

    @Override
    public String toString() {
        return "[" + supplier +
                ", " + new SimpleDateFormat("dd/MM/yyyy").format(date) +
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
