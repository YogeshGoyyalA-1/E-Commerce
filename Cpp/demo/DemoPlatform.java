package demo;

import java.io.*;
import java.util.*;
import ecomm.*;

public class DemoPlatform extends Platform {

	@Override
	public boolean addSeller(Seller aSeller) { // Adds seller to the arraylist of sellers
		sellersList.add(aSeller);
		return true;
	}

	@Override
	public void processRequests() { // Processes the requests and writes the output to PlatformtoPortal.txt

		Globals glb = new Globals(); // Instantiating globals object
		File file = new File(Globals.toPlatform); // File initialisation for reading from PortaltoPlatform
		Scanner sc;
		try {

			try (FileWriter fw = new FileWriter(Globals.fromPlatform, true); // For fileWriting to PlatfromtoPortal
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter fWriter = new PrintWriter(bw)) {

				sc = new Scanner(file);
				while (sc.hasNextLine()) {
					String s = sc.nextLine(); // Take input line by line
					String[] input = s.split(" "); // Split it into array
					if ((!reqprocessed.containsKey((input[1])))) { // Check if the request has been processed or not
						reqprocessed.put(input[1], 1);

						if (input[2].equals("List")) { // If request is of List
							ArrayList<Product> prodList = new ArrayList<>();
							for (var e : sellersList) { // Iterate through all the sellers

								for (Globals.Category cat : Globals.Category.values()) { // To find what category of
																							// product is to be listed

									if (glb.getCategoryName(cat).equals(input[3])) {
										prodList = e.findProducts(cat); // Find products of that category that this
																		// seller has and store it in the arraylist
										break;
									}
								}
								for (var p : prodList) { // Write the contents of the arraylist to the file
									String ss = (input[0] + " " + input[1] + " ");
									fWriter.print(ss);
									String ss1 = (p.getName() + " " + e.getID() + "-" + p.getName() + " " + p.getPrice()
											+ " " + p.getQuantity());
									fWriter.println(ss1);
								}
							}
						} else if (input[2].equals("Buy")) { // If request is to buy
							ArrayList<Product> prodList1 = new ArrayList<>();
							ArrayList<Product> prodList2 = new ArrayList<>();
							String[] splitName = input[3].split("-");
							for (var e : sellersList) { // Iterate through sellers
								if (e.getID().equals(splitName[0])) { // Find that item and store in arrayList
									prodList1 = e.findProducts(Globals.Category.Book);
									prodList2 = e.findProducts(Globals.Category.Mobile);
									Boolean z = false; // Flag z as false if found
									for (Product p : prodList1) { // Iterate through arraylist
										if (p.getName().equals(splitName[1])) { // Find product with that name
											if (e.buyProduct(p.getProductID(), Integer.parseInt(input[4]))) { // If it
																												// can
																												// be
																												// bought

												String ss = (input[0] + " " + input[1] + " " + "Success"); // Write
																											// success
												fWriter.println(ss);
											} else {

												String ss = (input[0] + " " + input[1] + " " + "Failure"); // Else write
																											// failure
												fWriter.println(ss);
											}
											z = true; // Again make z flag true
											break;
										}
									}
									for (Product p : prodList2) { // Same as above loop
										if (p.getName().equals(splitName[1])) {
											if (e.buyProduct(p.getProductID(), Integer.parseInt(input[4]))) {

												String ss = (input[0] + " " + input[1] + " " + "Success");
												fWriter.println(ss);
											} else {

												String ss = (input[0] + " " + input[1] + " " + "Failure");
												fWriter.println(ss);
											}
											z = true;
											break;
										}
									}
									if (!z) { // If z is false then write failure, as such product does not exist

										String ss = (input[0] + " " + input[1] + " " + "Failure");
										fWriter.println(ss);
									}

								}
							}

						} else if (input[2].equals("Start")) { // If request is start

							String ss = (input[0] + " " + input[1] + " ");
							fWriter.print(ss);
							String s1 = "";
							for (Globals.Category cat : Globals.Category.values()) { // Write all the categories
								s1 = s1 + cat + " ";

							}
							fWriter.println(s1);
						}
					} else // If anything else is requested ignore and go to the next request
					{
						continue;
					}

				}

				sc.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	ArrayList<Seller> sellersList = new ArrayList<>(); // Datamembers: arraylist to store sellers and hashmap to keep
														// track if request has been processed or not
	HashMap<String, Integer> reqprocessed = new HashMap<String, Integer>();
}
