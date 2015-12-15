
package Model;

public class FavoriteSupplier {
    private int supplier_id;
    private int vendor_id;

    public FavoriteSupplier(int supplier_id, int vendor_id) {
        this.supplier_id = supplier_id;
        this.vendor_id = vendor_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }
    
    
}
