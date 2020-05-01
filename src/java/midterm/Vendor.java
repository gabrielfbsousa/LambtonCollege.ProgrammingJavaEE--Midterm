package midterm;

/**
 * The Vendor Model Class
 *
 * @author <Gabriel Ferreira Barros de Sousa>
 */
public class Vendor {

    private int vendorId;
    private String name;
    private String vendorContactName;
    private String vendorPhone;

    /**
     * Retrieve the Vendor ID
     *
     * @return the Vendor ID
     */
    public int getVendorId() {
        return vendorId;
    }

    /**
     * Set the Vendor ID
     *
     * @param vendorID the Vendor ID
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * Retrieve the Name 
     *
     * @return the Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Vendor Name
     *
     * @param name the Vendor Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieve the Vendor's Contact Name
     *
     * @return the Vendor's Contact Name
     */
    public String getVendorContactName() {
        return vendorContactName;
    }

    /**
     * Set the Vendor's Contact Name 
     *
     * @param vendorContactName the Vendor Contact Name
     */
    public void setVendorContactName(String vendorContactName) {
        this.vendorContactName = vendorContactName;
    }

    /**
     * Retrieve the Vendor's Phone
     *
     * @return the Vendor's Phone
     */
    public String getVendorPhone() {
        return vendorPhone;
    }

    /**
     * Set the Vendor's Phone
     *
     * @param vendorPhone the Vendor Phone
     */
    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }
    
    
}
