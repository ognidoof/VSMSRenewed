
package Model;

import java.util.ArrayList;

public class Supplier {
    private int supplier_id;
    private String password;
    private String supplier_name;
    private String supplier_description;
    private ArrayList<String> supplier_type;

    public Supplier(int supplier_id, String password, String supplier_name, String supplier_description, ArrayList<String> supplier_type) {
        this.supplier_id = supplier_id;
        this.password = password;
        this.supplier_name = supplier_name;
        this.supplier_description = supplier_description;
        this.supplier_type = supplier_type;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public String getPassword() {
        return password;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public String getSupplier_description() {
        return supplier_description;
    }

    public ArrayList<String> getSupplier_type() {
        return supplier_type;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public void setSupplier_description(String supplier_description) {
        this.supplier_description = supplier_description;
    }

    public void setSupplier_type(ArrayList<String> supplier_type) {
        this.supplier_type = supplier_type;
    }
    
}
