package ecomm;

import ecomm.Globals.Category;

public class Book extends Product { // Derived class from Product
    public Book(String name, String prodID, float price, int quantity) { // Constructor
        this.name = name;
        this.prodID = prodID;
        this.price = price;
        this.quantity = quantity;
    }

    // common queries to get category, unique name, price, and number available
    public Globals.Category getCategory() { // Returns category of the object
        return Category.Book;
    }

    public String getName() // Returns name of the book
    {
        return name;
    }

    public String getProductID() { // Returns productID of the book
        return prodID;
    }

    public float getPrice() { // Returns price of the book
        return price;
    }

    public int getQuantity() { // Returns quantity of books available
        return quantity;
    }

    public void setQuantity(int x) // Sets the quantity of the book to x
    {
        this.quantity = x;
    }

    private String name, prodID; // Datamembers
    private int quantity;
    private float price;
}