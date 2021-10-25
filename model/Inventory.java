package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.Locale;
/** This creates the Inventory Class*/
public class Inventory {

    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    /** this adds a part to the part list.
     * @param newPart the part to add
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /** this adds a product to the product list.
     @param newProduct the product to add
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

/** This is the parts getter.
 * @return all of the parts in the inventory
 */

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /** This is the products getter.
     * @return all of the products in the inventory
     */

    public static ObservableList<Product>  getAllProducts() {
        return allProducts;
    }

    /** This searches for a part using ID
     * @param partID the ID to search
     * @return the part(s) found
     */

    public static Part lookupPart(int partID){
        ObservableList<Part> partlist = Inventory.getAllParts();
        for(int i=0; i <partlist.size(); i++){
            Part part = partlist.get(i);
            if(part.getId() == partID){

                return part;

            }
        }
        return null;
    }

    /** This searches for a product.
     * @param productID the ID to search
     * @return the product(s) found
     */

    public static Product lookupProduct(int productID){
        ObservableList<Product> productlist = Inventory.getAllProducts();
        for(int i=0; i <productlist.size(); i++){
            Product product = productlist.get(i);
            if(product.getProductID() == productID){

                return product;

            }
        }
        return null;
    }

    /** This searches for a part.
     * @param partstring the string to search
     * @return the part(s) found
     */
    public static ObservableList<Part> lookupPart(String partstring){
        ObservableList<Part> partMatches = FXCollections.observableArrayList();
        for(Part part: allParts){
            if(part.getName().toLowerCase(Locale.ROOT).contains(partstring.toLowerCase(Locale.ROOT))){
                partMatches.add(part);

            }
        } return partMatches;
    }

    /** This searches for a product.
     * @param productstring the string to search
     * @return the product(s) found
     */
    public static ObservableList<Product> lookupProduct(String productstring){
        ObservableList<Product> productMatches = FXCollections.observableArrayList();
        for(Product product: allProducts){
            if(product.getProductName().toLowerCase(Locale.ROOT).contains(productstring.toLowerCase(Locale.ROOT))){
                productMatches.add(product);

            }
        } return productMatches;
    }

    /** This updates a part.
     * @param selectedPart the part being updated
     */
    public static void updatePart(Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == selectedPart.getId()) {

                allParts.set(i, selectedPart);
                break;
            }
        }
        return;
    }
    /** This updates a product.
     * @param selectedProduct the product to update
     */
    public static void updateProduct(Product selectedProduct){
        for (int i = 0; i<allProducts.size(); i++){
            if (allProducts.get(i).getProductID() == selectedProduct.getProductID()) {
                allProducts.set(i, selectedProduct);
                break;
            }} return;
    }


/** This deletes a part.
 * @param selectedPart the part to delete
 */

    public static void deletePart(Part selectedPart){
        allParts.remove(selectedPart);

    }

/** This deletes a product.
 @param selectedProduct the product to delete
 */

    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }
}


