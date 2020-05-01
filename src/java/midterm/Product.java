package midterm;

/**
 * The Product Model Class
 *
 * @author <Gabriel Ferreira Barros de Sousa>
 */
public class Product {

    private int productId;
    private String name;
    private int vendorId;
    private Vendor vendor;

    /**
     * Retrieve the Product ID
     *
     * @return the Product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Set the Product ID
     *
     * @param productId the Product ID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Retrieve the Product Name
     *
     * @return the Product Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Product Name
     *
     * @param name the Product Name
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * @param vendorId the Vendor ID
     */
    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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
        if (this.vendorId == vendor.getVendorId()) {
            this.vendor = vendor;
        }
    }

}
