package ecomm;

import java.util.*;
import ecomm.Globals.Category;

public class Seller1 extends Seller {
    public Seller1(String id) // Constructor
    {
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
    // list of items this Seller offers
    Product ob1 = new Book("ULYSSES", "Seller1-ULYSSES", 100, 10); // Product objects of made that are to be added
    Product ob2 = new Book("THE_GREAT_GATSBY", "Seller1-THE_GREAT_GATSBY", 300, 3);
    Product ob3 = new Book("BRAVE_NEW_WORLD", "Seller1-BRAVE_NEW_WORLD", 80, 15);
    Product ob4 = new Mobile("VIVO", "Seller1-VIVO", 20000, 10);
    Product ob5 = new Mobile("IPHONE_14", "Seller1-IPHONE_14", 90000, 5);
    Product ob6 = new Mobile("SAMSUNG_S22", "Seller1-SAMSUNG_S22", 80000, 15);



    public boolean buyProduct(String productID, int quantity) // returns true/false depending whether transaction is
                                                              // favourable or not
    {
        for (Product prod : BookStore) {
            if (productID == prod.getProductID() && prod.getQuantity() >= quantity) { // iterate check for prodid
                prod.setQuantity(prod.getQuantity() - quantity); // decrease the quantity as well
                return true; // returns true
            }
        }

        for (Product prod1 : MobileStore) {
            if (productID == prod1.getProductID() && prod1.getQuantity() >= quantity) { // iterate check for prodid
                prod1.setQuantity(prod1.getQuantity() - quantity); // decrease the quantity as well
                return true; // returns true
            }
        }

        return false; // if nothing returned then returns false
    }
    private ArrayList<Product> BookStore = new ArrayList<>(Arrays.asList(ob1, ob2, ob3)); // ArrayList of items of Book
    private ArrayList<Product> MobileStore = new ArrayList<>(Arrays.asList(ob4, ob5, ob6)); // ArrayList of items of Mobile
}
