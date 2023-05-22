package ecomm;

import ecomm.Globals.Category;

public class Mobile extends Product { // Class derived from Product
    public Mobile(String name, String prodID, float price, int quantity) { // Constructor
        this.name = name;
        this.prodID = prodID;
        this.price = price;
        this.quantity = quantity;
    }

    // common queries to get category, unique name, price, and number available
    public Globals.Category getCategory() { // Returns category
        return Category.Mobile;
    }

    public String getName() // Returns name of mobile
    {
        return name;
    }

    public String getProductID() { // Returns productID of mobile
        return prodID;
    }

    public float getPrice() { // Returns price of mobile
        return price;
    }

    public int getQuantity() { // Returns quantity available of mobile
        return quantity;
    }

    public void setQuantity(int x) // Sets the available quantity of mobiles to x
    {
        this.quantity = x;
    }

    private String name, prodID; // Datamembers
    private int quantity;
    private float price;
}