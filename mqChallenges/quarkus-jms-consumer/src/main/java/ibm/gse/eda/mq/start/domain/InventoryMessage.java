package ibm.gse.eda.mq.start.domain;

public class InventoryMessage {
    public long id;
    public String storeName;
    public String itemCode;
    public Long quantity;
    public Double price;
    public String timestamp;

    public InventoryMessage(long n){
        this.id = n;
    }
}