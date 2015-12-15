
package Model;

import java.util.logging.Logger;

public class Vendor {
    private int vendor_id;
    private String password;
    private String vendor_name;
    private String vendor_description;

    public Vendor(int vendor_id, String password, String vendor_name, String vendor_description) {
        this.vendor_id = vendor_id;
        this.password = password;
        this.vendor_name = vendor_name;
        this.vendor_description = vendor_description;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public String getPassword() {
        return password;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public String getVendor_description() {
        return vendor_description;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public void setVendor_description(String vendor_description) {
        this.vendor_description = vendor_description;
    }
    
     public String toString() {
        return "Vendor ID: " + vendor_id + ", name: " + vendor_name + ", password: " + password  + " description " + vendor_description;
    }
    
}
