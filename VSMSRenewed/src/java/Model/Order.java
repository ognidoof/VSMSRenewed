package Model;

/**
 *
 * @author Joel
 */
public class Order {
    private String order_id;
    private String vendor_id;
    private int total_final_price;
    
    public Order(String order_id, String vendor_id, int total_final_price){
        this.order_id = order_id;
        this.vendor_id = vendor_id;
        this.total_final_price = total_final_price;
    }
    
    public String getOrder_id(){
        return order_id;
    }
    
    public String getVendor_id(){
        return vendor_id;
    }
    
    public int getTotal_final_price(){
        return total_final_price;
    }
    
    public void setOrder_id(String order_id){
        this.order_id = order_id;
    }
    
    public void setVendor_id(String vendor_id){
        this.vendor_id = vendor_id;
    }
    
    public void setTotal_final_price(int total_final_price){
        this.total_final_price = total_final_price;
    }
    
    public String toString(){
        return "Order_id: " + order_id + "Vendor_id: " + vendor_id + "Total final price: " + total_final_price;
    }
}