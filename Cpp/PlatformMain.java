import demo.DemoPlatform;
import ecomm.*;

import java.io.FileNotFoundException;
import java.util.*;

public class PlatformMain {

	public static void main(String[] args) {

		Platform pf = new DemoPlatform(); // replace with appropriate derived class

		// create a number of sellers (of different sub-types of Seller)
		// Assign a name (sellerID) to each of them.

		Seller s1 = new Seller1("Seller1"); // Create objects of sellers
		s1.addPlatform(pf); // Add them to the platform

		Seller s2 = new Seller2("Seller2");
		s2.addPlatform(pf);

		Seller s3 = new Seller3("Seller3");
		s3.addPlatform(pf);

		Scanner sc = new Scanner(System.in);
		while (true) {

			String s = sc.nextLine();
			if (s.equals("Check")) { // When check is requested in terminal
				try {
					pf.processRequests(); // Call processRequests for the platform
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}
			}
		}

	}

}
