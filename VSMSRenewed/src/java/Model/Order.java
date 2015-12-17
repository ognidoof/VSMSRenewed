package Model;

/**
 *
 * @author Joel
 */
public class Order {
    private int order_id;
    private int vendor_id;
    private double total_final_price;
    
    public Order(int order_id, int vendor_id, double total_final_price){
        this.order_id = order_id;
        this.vendor_id = vendor_id;
        this.total_final_price = total_final_price;
    }
    
    public int getOrder_id(){
        return order_id;
    }
    
    public int getVendor_id(){
        return vendor_id;
    }
    
    public double getTotal_final_price(){
        return total_final_price;
    }
    
    public void setOrder_id(int order_id){
        this.order_id = order_id;
    }
    
    public void setVendor_id(int vendor_id){
        this.vendor_id = vendor_id;
    }
    
    public void setTotal_final_price(double total_final_price){
        this.total_final_price = total_final_price;
    }
    
    public String toString(){
        return "Order_id: " + order_id + "Vendor_id: " + vendor_id + "Total final price: " + total_final_price;
    }
}