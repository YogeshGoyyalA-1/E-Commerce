package ecomm;

import java.util.*;
import ecomm.Globals.Category;

public class Seller3 extends Seller {
    public Seller3(String id) {
        super(id);
    }
    // id is passed in by the class that instantiates sub-type of seller

    // ID of seller.

    // Platform it is being added to.
    // Should in turn add itself to the Platform (with addSeller)
    public void addPlatform(Platform thePlatform) {
        thePlatform.addSeller(this);
    }

    // Seller to return listing of Products of specified Category
    public ArrayList<Product> findProducts(Globals.Category whichOne) {
        if (whichOne == Category.Book) {
            return BookStore;
        } else
            return MobileStore;
    }
    // User wants to buy specified quantity of productID
    // Return true if transaction succeeds, false otherwise.
    // Transaction fails if incorrect productID or quantity exceeds available
    // inventory

    Product ob1 = new Book("GOD_OF_SMALL_THINGS", "Seller3-GOD_OF_SMALL_THINGS", 200, 19); // Product objects created
                                                                                           // that are to be added
    Product ob2 = new Book("CLEOPATRA_AND_FRANKENSTEIN", "Seller3-CLEOPATRA_AND_FRANKENSTEIN", 100, 13);
    Product ob3 = new Book("PRISONER_OF_AZKABAN", "Seller3-PRISONER_OF_AZKABAN", 80, 15);
    Product ob4 = new Mobile("OPPO", "Seller3-OPPO", 22000, 7);
    Product ob5 = new Mobile("IPHONE_13_MINI", "Seller3-IPHONE_13_MINI", 80000, 15);
    Product ob6 = new Mobile("SAMSUNG_S20+", "Seller3-SAMSUNG_S20+", 60000, 5);

    public boolean buyProduct(String productID, int quantity) 
    // Returns true if possible to buy and updates the
    // quantity, else returns false
    {
        for (Product prod : BookStore) { 
            // Iterates through all the books
            if (productID == prod.getProductID() && prod.getQuantity() >= quantity) {
                prod.setQuantity(prod.getQuantity() - quantity);
                return true;
            }
        }

        for (Product prod1 : MobileStore) { 
            // Iterates through all the mobiles if not found above
            if (productID == prod1.getProductID() && prod1.getQuantity() >= quantity) {
                prod1.setQuantity(prod1.getQuantity() - quantity);
                return true;
            }
        }

        // Else return false
        return false; 
    }

    // Arraylists to maintain objects of books and mobiles                                                                                     
    private ArrayList<Product> BookStore = new ArrayList<>(Arrays.asList(ob1, ob2, ob3)); 
    private ArrayList<Product> MobileStore = new ArrayList<>(Arrays.asList(ob4, ob5, ob6));

}