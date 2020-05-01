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
 * The Product Controller Class
 *
 * @author <Gabriel Ferreira Barros de Sousa>
 */
@Named
@ApplicationScoped
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Product thisProduct = new Product();
    private List<Vendor> productVendors = new ArrayList<>();
    private Vendor thisVendor = new Vendor();

    /**
     * Basic Constructor for Products - Retrieves from DB
     */
    public ProductController() {
        refreshProductsList();
    }

    /**
     * Retrieve the full list of Products
     *
     * @return the List of Products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Retrieve the Product Model used in Forms
     *
     * @return the Product Model used in Forms
     */
    public Product getThisProduct() {
        return thisProduct;
    }

    /**
     * Set the Product Model used in Forms
     *
     * @param thisProduct the Product Model used in Forms
     */
    public void setThisProduct(Product thisProduct) {
        this.thisProduct = thisProduct;
    }

    /**
     * Retrieve the Product Vendors
     *
     * @return the Product Vendors
     */
    public List<Vendor> getProductVendors() {
        return productVendors;
    }

    /**
     * Set the List of Product Vendors
     *
     * @param productVendors the Product Vendors
     */
    public void setProductVendors(List<Vendor> productVendors) {
        this.productVendors = productVendors;
    }

    /**
     * Retrieve the Vendor
     *
     * @return the Vendor
     */
    public Vendor getThisVendor() {
        return thisVendor;
    }

    /**
     * Set the Vendor
     *
     * @param thisVendor the Vendor
     */
    public void setThisVendor(Vendor thisVendor) {
        this.thisVendor = thisVendor;
    }

    /**
     * Add a new Product to the Database and List
     */
    public String add() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO Products (Name, VendorId) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, thisProduct.getName());
            pstmt.setInt(2, thisProduct.getVendorId());
            pstmt.executeUpdate();
            //products.add(thisProduct); -- O problema eh que ele ta adicionando o proprio thisProduct, que logicamente nao tem ID.
            refreshProductsList();
            thisProduct = new Product();
            return "index";
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Refresh the table of products, selecting the products from Database
     */
    public void refreshProductsList() {
        try {
            products = new ArrayList<>();
            productVendors = new ArrayList<>();
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
            while (rs.next()) {
                Product p = new Product();
                p.setName(rs.getString("Name"));
                p.setProductId(rs.getInt("ProductId"));
                p.setVendorId(rs.getInt("VendorId"));

                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM vendors JOIN products ON (vendors.VendorId = ?)");
                pstmt.setInt(1, p.getVendorId());
                ResultSet vendorResultSet = pstmt.executeQuery();
                while (vendorResultSet.next()) {
                    Vendor v = new Vendor();
                    v.setName(vendorResultSet.getString("Name"));
                    v.setVendorContactName(vendorResultSet.getString("ContactName"));
                    v.setVendorId(vendorResultSet.getInt("VendorId"));
                    v.setVendorPhone(vendorResultSet.getString("PhoneNumber"));
                    p.setVendor(v);
                    productVendors.add(v);
                }

                products.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Delete a product from the database and from the list
     */
    public void deleteProduct(Product product) {
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE ProductId = ?");
            pstmt.setInt(1, product.getProductId());
            pstmt.execute();
            refreshProductsList();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Redirects to the Edit Product page, and sets thisProduct, in order to
     * populate the forms in this page
     */
    public String editProduct(Product product) {
        thisProduct = product;
        return "editProduct";
    }

    /**
     * Updates a Product on the Database
     */
    public String update() {
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET Name = ?, VendorId = ? WHERE ProductId = ?");
            pstmt.setString(1, thisProduct.getName());
            pstmt.setInt(2, thisProduct.getVendorId());
            pstmt.setInt(3, thisProduct.getProductId());
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshProductsList();
        return "index";
    }
}
