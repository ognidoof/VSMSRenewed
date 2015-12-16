/*
 * To open up Supplier, Vendor, FavSupplier
 * Left to do:
 *  Add on attributes to suppliers and vendors?
 *  Sign up
 *  Adding Favourite Supplier
 *  Save and delete account
 *  Do email communication
 */
package DAO;

import Controller.ConnectionManager;
import Model.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class UserDAO {

//    //For testing purpose
    public static void main(String[] args){
        //Test general SQL
        ArrayList<Supplier> supplierList = retrieveSupplierList();
        ArrayList<Vendor> vendorList = retrieveVendorList();
        ArrayList<FavouriteSupplier> favouriteSupplierList = retrieveFavouriteSupplierList();
        for (Supplier supplier: supplierList){
            System.out.println(supplier);
        }
        for(Vendor vendor: vendorList){
            System.out.println(vendor);
        }
        for (FavouriteSupplier favSupplier : favouriteSupplierList){
            System.out.println(favSupplier);
        }
        
        //Test special sql
        System.out.println(loginSupplier("FreshFruitz",""));
        System.out.println(loginVendor("Bob's Bakery And Bistro",""));
        
    }
    private static void handleSQLException(SQLException e, String sql, String... parameters) {
        String msg = "Unable to access data. SQL : " + sql + "\n";

        for (String para : parameters) {
            msg += para + "\n";
        }

        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, msg, e);
        throw new RuntimeException(msg, e);
    }

    //SUPPLIER SQL
    public static ArrayList<Supplier> retrieveSupplierList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from supplier";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int supplier_id = rs.getInt("supplier_id");
                String supplier_name = rs.getString("supplier_name");
                String password = rs.getString("password");
                String supplier_description = rs.getString("supplier_description");
                String supplier_type = rs.getString("supplier_type");
                String email = rs.getString("email");
                int area_code = rs.getInt("area_code");
                int telephone_number = rs.getInt("telephone_number");
                String address = rs.getString("address");

                Supplier supplier = new Supplier(supplier_id, password, supplier_name, supplier_description, supplier_type, email, area_code, telephone_number, address);
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return supplierList;
    }

    public static Supplier loginSupplier(String supplier_name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        Supplier supplier = null;
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from supplier where supplier_name = ? and password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplier_name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int supplier_id = rs.getInt("supplier_id");
                String supplier_description = rs.getString("supplier_description");
                String supplier_type = rs.getString("supplier_type");
                String email = rs.getString("email");
                int area_code = rs.getInt("area_code");
                int telephone_number = rs.getInt("telephone_number");
                String address = rs.getString("address");
                supplier = new Supplier(supplier_id, password, supplier_name, supplier_description, supplier_type, email, area_code, telephone_number, address);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } catch (Exception e) {
            //Supplier is not found
            return null;
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return supplier;
    }

    //VENDOR SQL
    public static ArrayList<Vendor> retrieveVendorList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from vendor";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int vendor_id = rs.getInt("vendor_id");
                String vendor_name = rs.getString("vendor_name");
                String password = rs.getString("password");
                String vendor_description = rs.getString("vendor_description");
                String email = rs.getString("email");
                int area_code = rs.getInt("area_code");
                int telephone_number = rs.getInt("telephone_number");
                String address = rs.getString("address");
                Vendor vendor = new Vendor(vendor_id, password, vendor_name, vendor_description, email, area_code, telephone_number, address);
                vendorList.add(vendor);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            //conn.close();
            ConnectionManager.close(conn, stmt, rs);
        }
        return vendorList;

    }

    public static Vendor loginVendor(String vendor_name, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        Vendor vendor = null;
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from vendor where vendor_name = ? and password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, vendor_name);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int vendor_id = rs.getInt("vendor_id");
                String vendor_description = rs.getString("vendor_description");
                String email = rs.getString("email");
                int area_code = rs.getInt("area_code");
                int telephone_number = rs.getInt("telephone_number");
                String address = rs.getString("address");
                vendor = new Vendor(vendor_id, password, vendor_name, vendor_description,email,area_code,telephone_number,address);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } catch (Exception e) {
            //Vendor is not found
            return null;
        } finally {
            ConnectionManager.close(conn, stmt, rs);
        }
        return vendor;
    }

    //FAVOURITE SUPPPLIER SQL
    public static ArrayList<FavouriteSupplier> retrieveFavouriteSupplierList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<FavouriteSupplier> favouriteSupplierList = new ArrayList<FavouriteSupplier>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from favourite_supplier";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int vendor_id = rs.getInt("vendor_id");
                int supplier_id = rs.getInt("supplier_id");
                FavouriteSupplier favouriteSupplier = new FavouriteSupplier(vendor_id, supplier_id);
                favouriteSupplierList.add(favouriteSupplier);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            //conn.close();
            ConnectionManager.close(conn, stmt, rs);
        }
        return favouriteSupplierList;

    }

    public static ArrayList<Supplier> retrieveFavouriteSupplierListByVendor(int vendor_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
        try {
            //creates connections to database
            conn = ConnectionManager.getConnection();
            sql = "Select * from favourite_supplier fs, supplier s where fs.supplier_id = s.supplier_id and vendor_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vendor_id);
            rs = stmt.executeQuery();

            //Retrieves the supplier info from database and create a new supplier object to return
            while (rs.next()) {
                int supplier_id = rs.getInt("supplier_id");
                String supplier_name = rs.getString("supplier_name");
                String password = rs.getString("password");
                String supplier_description = rs.getString("supplier_description");
                String supplier_type = rs.getString("supplier_type");
                String email = rs.getString("email");
                int area_code = rs.getInt("area_code");
                int telephone_number = rs.getInt("telephone_number");
                String address = rs.getString("address");
                Supplier supplier = new Supplier(supplier_id, password, supplier_name, supplier_description, supplier_type, email, area_code, telephone_number, address);
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            handleSQLException(e, sql);
        } finally {
            //conn.close();
            ConnectionManager.close(conn, stmt, rs);
        }
        return supplierList;

    }

    //Not done yet//
    public static void saveAsFavouriteSupplier(String supplierId, String vendorId) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";

        String vendor_id = vendorId;
        String supplier_id = supplierId;
        //get supplier ID? or retrieve from arraylist?

        try {
            conn = ConnectionManager.getConnection();
            query = "INSERT INTO favourite_supplier " + "(vendor_id,supplier_id) VALUES(?,?)";
            //where vendor_id=?
            statement = conn.prepareStatement(query);
            statement.setString(1, vendor_id);
            statement.setString(2, supplier_id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteFavouriteSupplier(String supplierId, String vendorId) {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "";

        String vendor_id = vendorId;
        String supplier_id = supplierId;
        //get supplier ID? or retrieve from arraylist?

        try {
            conn = ConnectionManager.getConnection();
            query = "Delete from favourite_supplier where vendor_id=? and supplier_id=?";
            //where vendor_id=?
            statement = conn.prepareStatement(query);
            statement.setString(1, vendor_id);
            statement.setString(2, supplier_id);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
