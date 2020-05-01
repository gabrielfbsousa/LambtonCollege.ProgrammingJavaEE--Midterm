package midterm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * The Vendor Controller Class
 *
 * @author <Gabriel Ferreira Barros de Sousa>
 */
@Named
@ApplicationScoped
public class VendorController {
    private List<Vendor> vendors = new ArrayList<>();
    private Vendor vendor = new Vendor();
    
    public VendorController(){
        refreshVendorList();
    }

    /**
     * Retrieve the List of Vendors 
     *
     * @return the List of Vendors 
     */
    public List<Vendor> getVendors() {
        return vendors;
    }

    /**
     * Set the List of Vendors
     *
     * @param vendors the Vendor's List
     */
    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    /**
     * Retrieve the Vendor
     *
     * @return the Vendor
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * Set the Vendor
     *
     * @param vendor the Vendor
     */
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
     /**
     * Refresh the table of vendors, selecting the products from Database
     */
    public void refreshVendorList(){
        try {
            vendors = new ArrayList<>();
            Connection conn = DBUtils.getConnection(); 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Vendors");
            while(rs.next()){
                Vendor v = new Vendor();
                v.setName(rs.getString("Name"));
                v.setVendorContactName(rs.getString("ContactName"));
                v.setVendorId(rs.getInt("VendorId"));
                v.setVendorPhone(rs.getString("PhoneNumber"));
                vendors.add(v);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Add a new Vendor to the Database and List
     */
    public String add(){
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT into vendors (VendorId, Name, ContactName, PhoneNumber) VALUES (?,?,?,?)");
            pstmt.setInt(1, vendor.getVendorId());
            pstmt.setString(2, vendor.getName());
            pstmt.setString(3, vendor.getVendorContactName());
            pstmt.setString(4, vendor.getVendorPhone());
            pstmt.execute();
            vendors.add(vendor);
            vendor = new Vendor();
        } catch (SQLException ex) {
            Logger.getLogger(VendorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshVendorList();
        return "index";
    }
}
