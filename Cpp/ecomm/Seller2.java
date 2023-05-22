package ecomm;

import java.util.*;
import ecomm.Globals.Category;

public class Seller2 extends Seller {
    public Seller2(String id) {
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

    Product ob1 = new Book("ORDER_OF_THE_PHOENIX", "Seller2-ORDER_OF_THE_PHOENIX", 500, 9); // Product objects created that are to be added
    Product ob2 = new Book("THE_PSYCHOLOGY_OF_MONEY", "Seller2-THE_PSYCHOLOGY_OF_MONEY", 300, 3);
    Product ob3 = new Book("ATOMIC_HABITS", "Seller2-ATOMIC_HABITS", 100, 12);
    Product ob4 = new Mobile("REDMI", "Seller2-REDMI", 18000, 17);
    Product ob5 = new Mobile("IPHONE_12_PLUS", "Seller2-IPHONE_12_PLUS", 70000, 14);
    Product ob6 = new Mobile("SAMSUNG_FOLD", "Seller2-SAMSUNG_FOLD", 260000, 3);

    

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
             // If not found will iterate through all the mobiles
            if (productID == prod1.getProductID() && prod1.getQuantity() >= quantity) {
                prod1.setQuantity(prod1.getQuantity() - quantity);
                return true;
            }
        }

        // if still no matching product is found, return false
        return false;
    }
    private ArrayList<Product> BookStore = new ArrayList<>(Arrays.asList(ob1, ob2, ob3)); // Arraylists to maintain objects of books and mobiles                                                                            
    private ArrayList<Product> MobileStore = new ArrayList<>(Arrays.asList(ob4, ob5, ob6));
}
