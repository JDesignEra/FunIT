/*
 * XXX NOTE:
 * - Total Players / Number of Players should always be the last index in transactionList object
 * - rideGrp should always be the 2nd last index in transactionList object
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FunIT {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final double GST = 0.7;
		
		int menu = 0, rideGrp = 1;
		double amtRec = 0, amtRecTemp = 0, price = 0, changeReturn = 0;
		String creditCard = "", cardDisc = "";
		boolean quitProcess = true;
		
		String[] name = {"", ""};
		int[] age = {0, 0};
		
		List<String[]> currentPlayerList = new ArrayList<String[]>();
		List<String[]> transactionList = new ArrayList<String[]>();
		
		while(menu != -99) {
			System.out.println("============================================");
			System.out.println("||                 FunIT                  ||");
			System.out.println("============================================");
			System.out.println("||   1. Add ride players                  ||");
			System.out.println("||   2. Display entire day's transactions ||");
			System.out.println("||   3. Begin the ride                    ||");
			System.out.println("|| -99. Quit                              ||");
			System.out.println("============================================");
			System.out.print("Enter your selection number: ");
			menu = scInt();
			
			switch(menu) {
				// Add ride players
				case 1:
					if(currentPlayerList.size() < 5) {
						System.out.println("\n=============================================================================");
						System.out.println("|| \u2022 During the process of adding, enter -99 to cancel the adding process. ||");
						System.out.println("|| \u2022 " + (5 - currentPlayerList.size()) + " PLAYER SLOT(S) LEFT CURRENTLY                                       ||");
						System.out.println("=============================================================================");
						
						// First Player name
						while(quitProcess && "".equals(name[0])) {
							System.out.print("Enter player name: ");
							name[0] = sc.nextLine();
							
							if("-99".equals(name[0])) {
								quitProcess = quitProcess();
							}
							else if("".equals(name[0])) {
								System.out.println("Player name can't be empty\n");
							}
						}
						
						//First Player age
						while(quitProcess) {
							System.out.print("Enter player age: ");
							age[0] = scInt();
							
							if(age[0] == -99) {
								quitProcess = quitProcess();
							}
							else if(age[0] >= 3 && age[0] <= 80) {
								if(age[0] >= 3 && age[0] <= 5) {
									if(currentPlayerList.size() < 4) {
										System.out.println("You have entered a child player, the 2nd player has to be an adult.\n");
										break;
									}
									else {
										System.out.println("\nYou can't enter a child player, as there's only 1 available seat left.");
										System.out.println("Please re-enter an age range of 5 to 80 or -99 to cancel the process.\n");
									}
								}
								else {
									System.out.println("You have entered 1 player\n");
									break;
								}
							}
							else {
								System.out.println("Please enter a player age between 3 to 80.\n");
							}
						}
						
						// Second Player name
						while(quitProcess && "".equals(name[1])) {
							if(age[0] >= 3 && age[0] <= 5) {
								System.out.print("Enter 2nd player name: ");
								name[1] = sc.nextLine();
								
								if("-99".equals(name[1])) {
									quitProcess = quitProcess();
								}
								else if("".equals(name[1])) {
									System.out.println("2nd player name can't be empty\n");
								}
							}
							else {
								break;
							}
						}
						
						// Second Player age
						while(quitProcess && !(age[1] >= 16 && age[1] <= 80)) {
							if(age[0] >= 3 && age[0] <= 5) {
								System.out.print("Enter 2nd player age: ");
								age[1] = scInt();
								
								if(age[1] == -99) {
									quitProcess =quitProcess();
								}
								else if(age[1] >= 16 && age[1] <= 80) {
									System.out.println("You have entered 1 child and 1 adult players.\n");
								}
								else {
									System.out.println("Please enter a player age between 16 to 80");
								}
							}
							else {
								break;
							}
						}
						
						// Credit Card
						while(quitProcess && !("POSB".equalsIgnoreCase(creditCard) || "OCBC".equalsIgnoreCase(creditCard) || "CASH".equalsIgnoreCase(creditCard))) {
							System.out.println("=======================================================");
							System.out.println("|| \u2022 10% discount when paying with POSB Credit Card. ||");
							System.out.println("|| \u2022 5% discount when paying with OCBC Credit Card.  ||");
							System.out.println("|| \u2022 No discounts when paying with CASH.             ||");
							System.out.println("|| * Leaving it BLANK will default to CASH payment.  ||");
							System.out.println("=======================================================");
							System.out.print("Enter payment method (POSB / OCBC / CASH): ");
							creditCard = sc.nextLine();
							
							if("-99".equals(creditCard)) {
								quitProcess = quitProcess();
							}
							else if("POSB".equalsIgnoreCase(creditCard)) {
								creditCard = creditCard.toUpperCase();
								System.out.println("You are paying with POSB Credit Card (10% Discount).\n");
							}
							else if("OCBC".equalsIgnoreCase(creditCard)) {
								creditCard = creditCard.toUpperCase();
								System.out.println("You are paying with OCBC Credit Card (5% Discount).\n");
							}
							else if ("CASH".equalsIgnoreCase(creditCard) || "".equals(creditCard)) {
								creditCard = "CASH";
								System.out.println("You are paying with CASH.\n");
							}
							else {
								System.out.println("Please enter POSB, OCBC, CASH or leave it empty for CASH.\n");
							}
						}
						
						// ID Card Discount
						while(quitProcess && !("NYP Student".equalsIgnoreCase(cardDisc) || "Safra card".equalsIgnoreCase(cardDisc) || "None".equalsIgnoreCase(cardDisc))) {
							if((age[0] > 17 && age[0] <= 80) || (age[1] > 17 && age[1] <= 80)) {
								System.out.println("==============================================================");
								System.out.println("|| \u2022 20% discount for NYP Student card and age above 17.    ||");
								System.out.println("|| \u2022 10% discount for Safra card and age above 18.          ||");
								System.out.println("|| * Only a maximum of one ID card discount can be applied. ||");
								System.out.println("|| * Leaving it BLANK will default to None (No discounts).  ||");
								System.out.println("==============================================================");
								System.out.print("Enter card discount (NYP Student / Safra card / None): ");
								cardDisc = sc.nextLine();
								
								if("-99".equals(cardDisc)) {
									quitProcess = quitProcess();
								}
								else if("NYP Student".equalsIgnoreCase(cardDisc) && (age[0] > 17 || age[1] > 17)) {
									cardDisc = "NYP Student";
									System.out.println("20% discount applied for NYP Student card.\n");
								}
								else if("Safra card".equalsIgnoreCase(cardDisc) && (age[0] > 18 || age[1] > 18)) {
									cardDisc = "Safra Card";
									System.out.println("10% discount applied for Safra card\n");
								}
								else if("".equals(cardDisc) || "None".equalsIgnoreCase(cardDisc)) {
									cardDisc = "None";
									System.out.println("You do not have NYP Student card or Safra card to be eligible for discounts.\n");
								}
								else if("Safra card".equalsIgnoreCase(cardDisc) && (age[0] <= 18 || age[1] <= 18)) {
									System.out.println("You do not meet the age requirements for the Safra card discount.\n");
								}
								else {
									System.out.println("Please enter NYP Student, Safra card, None, or leave it blank for None.\n");
								}
							}
							else {
								cardDisc = "None";
								System.out.println("No players met the age range for the card discounts.\n");
								break;
							}
						}
						
						if(quitProcess) {
							// Calculates total for age
							for(int i = 0; i < age.length; i++) {
								if(age[i] >= 3 && age[i] < 6) {
									price += 5;
								}
								if(age[i] >= 6 && age[i] <=12) {
									price += 8;
								}
								if(age[i] > 12 && age[i] <= 16) {
									price += 10;
								}	
								if(age[i] > 16 && age[i] <= 65) {
									price += 15;
								}
								if(age[i] > 65 && age[i] <= 80) {
									price += 2;
								}
							}
							
							// Applies credit card discounts
							if("POSB".equalsIgnoreCase(creditCard)) {
								price -= (price * 0.10);
							}
							else if("OCBC".equalsIgnoreCase(creditCard)) {
								price -= (price * 0.5);
							}
							
							// Applies ID card discounts
							if("NYP Student".equalsIgnoreCase(cardDisc)) {
								price -= (price * 0.20);
							}
							else if("Safra Card".equalsIgnoreCase(cardDisc)) {
								price -= (price * 0.10);
							}
							
							price += (price * GST);
							System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
							
							// Prompt for amount received
							while(quitProcess && amtRec < price) {
								System.out.print("Enter amount recieved from Customer: $");
								amtRecTemp = scDbl();
								
								if(amtRecTemp == -99) {
									quitProcess = quitProcess();
								}
								else if(amtRecTemp >= 0) {
									amtRec += amtRecTemp;
									
									if(amtRec >= price) {
										System.out.println("Amount Due has been fully paid.\n");
									}
									else {
										System.out.format("%nAmount Due (After Discount & GST): $%.2f%n", price);
										System.out.format("Amount left to be paid: $%.2f%n", (price - amtRec));
									}
								}
								else {
									System.out.println("Please enter a postive double value.\n");
								}
							}
							
							// Calculate change, Display current transaction and store to lists
							if(quitProcess) {
								changeReturn = amtRec - price;

								System.out.println("==============================================");
								System.out.println("||            Transaction Detail            ||");
								System.out.println("==============================================");
								
								// Stores to currentPlayerList if name is not empty or age is not 0, and display
								for(int i = 0; i < name.length && i < age.length; i++) {
									if(!"".equals(name[i]) && age[i] != 0) {
										currentPlayerList.add(new String[] {
											name[i],
											String.valueOf(age[i])
										});
										
										System.out.println("Player " + (i+1) + " name: " + name[i]);
										System.out.println("Player " + (i+1) + " age: " + age[i]);
									}
								}
								
								System.out.println("Payment Method: " + creditCard);
								System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
								System.out.format("Change to return: $%.2f%n%n", changeReturn);
								System.out.println("==============================================");
								System.out.println("Player Slots Left: " + (5 - currentPlayerList.size()));
								System.out.println("==============================================\n\n");
								
								// Update number of players of the current ride group in transactionList
								transactionList = updateTransListPlayerCount(transactionList, rideGrp, currentPlayerList.size());
								
								// Stores up to a max of 1000 transactions. Else remove the the oldest rideGrp transaction and add new entry
								if(transactionList.size() < 1000) {
									transactionList = addToTransactionList(transactionList,
										name,									// name[0] & name[1]
										age,									// age[0] & age[1]
										rideGrp,								// rideGrp Number
										currentPlayerList.size(),				// Number of Players
										creditCard,								// creditCard
										cardDisc,								// cardDisc
										String.format("%.2f", price),			// price
										String.format("%.2f", amtRec),			// amtRec
										String.format("%.2f", changeReturn)		// changeReturn
									);
								}
								else {		// Remove oldest rideGrp entries (Last Index) and add new entry
									transactionList = removeTransListOldestEntry(transactionList);
									
									transactionList = addToTransactionList(transactionList,
										name,
										age,
										rideGrp,
										currentPlayerList.size(),
										creditCard,
										cardDisc,
										String.format("%.2f", price),
										String.format("%.2f", amtRec),
										String.format("%.2f", changeReturn)
									);
								}
							}
						}
						
						// Resets value to be reused
						for(int i = 0; i < name.length; i++) {
							name[i] = "";
						}
						
						for(int i = 0; i < age.length; i++) {
							age[i] = 0;
						}
						
						creditCard = "";
						cardDisc = "";
						price = 0;
						amtRec = 0;
						amtRecTemp = 0;
						changeReturn = 0;
						quitProcess = true;
					}
					else {
						System.out.println("You can't add anymore players until you start the ride.\n");
					}
				break;
				
				// Display entire day's transactions
				case 2:
					if(!transactionList.isEmpty()) {
						for(int i = 0; i < transactionList.size(); i++) {
							// Index related (Can be expanded in the future if needed)
							int currIndexLen = transactionList.get(i).length;
									
							String totalPlayerGet = transactionList.get(i)[currIndexLen - 1],
									rideGrpGet = transactionList.get(i)[currIndexLen - 2],
									changeRtnGet = transactionList.get(i)[currIndexLen - 3],
									amtRecGet = transactionList.get(i)[currIndexLen - 4],
									priceGet = transactionList.get(i)[currIndexLen - 5],
									cardDiscGet = transactionList.get(i)[currIndexLen - 6],
									creditCardGet = transactionList.get(i)[currIndexLen - 7];
							
							
							String tempOut = "||                 Ride " + rideGrpGet + "                ||";
							
							if(i == 0) {
								System.out.println("");
								charCountPrintEqual(2, tempOut);
							}
							
							System.out.println("First Player Name: " + transactionList.get(i)[0]);
							System.out.println("First Player Age: " + transactionList.get(i)[1]);
							
							// If transactionList current index object length is 11 = there's 2 set's of players
							if(currIndexLen == 11) {
								System.out.println("Second Player Name: " + transactionList.get(i)[2]);
								System.out.println("Second Player Age: " + transactionList.get(i)[3]);
							}
							
							System.out.println("Payment Method: " + creditCardGet);
							System.out.println("Card Discount: " + cardDiscGet);
							System.out.println("Amount Due (After Discount & GST): $" + priceGet);
							System.out.println("Amount Received: $" + amtRecGet);
							System.out.println("Change to return: $" + changeRtnGet + "\n");
							
							try {
								int nxtIndexLen = transactionList.get(i+1).length;
								String nxtRideGrpGet = transactionList.get(i+1)[nxtIndexLen - 2];
								
								// Checks if the next players rideGrp is the same as the current rideGrp								
								if(!rideGrpGet.equals(nxtRideGrpGet)) {
									tempOut = "||                 Ride " + nxtRideGrpGet + "                ||";
									
									charCountPrintEqual(0, tempOut);
									System.out.println("Ride " + rideGrpGet + " Total Players: " + totalPlayerGet + "\n\n");
									charCountPrintEqual(2, tempOut);
								}
							}
							catch(IndexOutOfBoundsException e) {
								charCountPrintEqual(0, tempOut);
								System.out.println("Ride " + rideGrpGet + " Total Players: " + totalPlayerGet + "\n\n");
							}
						}
					}
					else {
						System.out.println("There is no transaction for the day to be display yet.");
					}
				break;
				
				// Begin the ride
				case 3:
					if(!currentPlayerList.isEmpty()) {
						System.out.println("\n========================================");
						System.out.println("||        Current Ride Players        ||");
						System.out.println("========================================");
						
						for(int i = 0; i < currentPlayerList.size(); i++) {
							System.out.println("Player " + (i+1) + " Name: " + currentPlayerList.get(i)[0]);
							System.out.println("Player " + (i+1) + " Age: " + currentPlayerList.get(i)[1]);
						}
						
						// Display total number of players
						System.out.println("========================================");
						System.out.println("|| Number Of Players For This Ride: " + currentPlayerList.size() + " ||");
						System.out.println("========================================");
						System.out.println("\nTHE RIDE HAS BEEN STARTED!\n");
						
						currentPlayerList.clear();
						rideGrp++;
					}
					else {
						System.out.println("\nYou can't begin the ride without any players.\n");
					}
				break;
				
				// Quit
				case -99:
					menu = -99;
					System.out.println("User has quit the program.\n");
					sc.close();
				break;
				
				default:
					System.out.println("Please enter 1, 2, 3 or -99 to quit the program.\n");
				break;
			}
		}
	}
	
	// Fixes Java nextInt() / nextLine() issues
	// Documented on Oracle [http://bugs.java.com/bugdatabase/view_bug.do?bug_id=8170457]
	public static int scInt() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int i = 0;
		
		// Try and catch int input with own error message
		try {
			i = Integer.valueOf(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input, please enter an Integer value.");
		}
		
		return i;
	}
	
	// Try and catch double input with own error message
	public static double scDbl() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		double i = 0;
		
		try {
			i = sc.nextDouble();
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid input, please enter an Integer or Double value.");
		}
		
		return i;
	}
	
	// Return false for boolean quitProcess, and display quit process message
	public static boolean quitProcess() {
		System.out.println("User has quit the process.\n");
		return false;
	}
	
	// For counting max String length to print equal length of "=" (As some output does not have fix length)
	public static void charCountPrintEqual(int opt, String outCount) {
		String outEquals = "";
		
		for(int i = 0; i < outCount.length(); i++) {
			outEquals += "=";
		}
		
		switch(opt) {
			// Double Equals
			case 2:
				System.out.println(outEquals);
				System.out.println(outCount);
				System.out.println(outEquals);
			break;
			
			// Only Equal
			default:
				System.out.println(outEquals);
			break;
		}
	}
	
	// For Updating transactionList number of players for the current rideGrp
	public static List<String[]> updateTransListPlayerCount(List<String[]> list, int rideGrp, int playerCount) {
		// *(rideGrp should always be 2nd last index, and playerCount should always be last index)
		for(int i = 0; i < list.size(); i++) {
			// If rideGrp == list(i)[2nd Last Index] && playerCount != list(i)[Last Index] update number of players.
			if(list.get(i)[list.get(i).length - 2].equals(String.valueOf(rideGrp)) && !list.get(i)[list.get(i).length - 1].equals(String.valueOf(playerCount))) {
				String[] obj = new String[list.get(i).length];
				
				// Store updated playerCount into temporary variable & the other values without changing it,
				for(int x = 0; x < list.get(i).length; x++) { 
					if(x == list.get(i).length - 1) {
						obj[x] = String.valueOf(playerCount);
					}
					else {
						obj[x] = list.get(i)[x];
					}
				}
				
				list.set(i, obj);
			}
			else break;		// Break out of for loop as current rideGrp are usually at the top index to prevent pointless looping
		}
		
		return list;
	}
	
	// For adding values to transactionList (Can be easily expanded for future use)
	public static List<String[]> addToTransactionList(List<String[]> list, String[] name, int[] age, int rideGrp, int playerCount, String... strs) {
		List<String> tempList = new ArrayList<String>();
		String[] obj;
		
		for(int i = 0; i < name.length && i < age.length; i++) {
			if(!"".equals(name[i])) {
				tempList.add(name[i]);
			}
			if(age[i] != 0) {
				tempList.add(String.valueOf(age[i]));
			}
		}
		
		for(int i = 0; i < strs.length; i++) {
			tempList.add(strs[i]);
		}
		
		tempList.add(String.valueOf(rideGrp));			// *rideGrp should always be the 2nd last value added into the list
		tempList.add(String.valueOf(playerCount));		// *playerCount should always be the last value added into the list
		
		obj = tempList.toArray(new String[tempList.size()]);
		
		list.add(0, obj);
		
		return list;
	}
	
	// For removing oldest rideGrp entries in transactionList
	public static List<String[]> removeTransListOldestEntry(List<String[]> list) {
		String oldRideGrp = list.get(list.size() - 1)[list.get(list.size() - 1).length - 1];
		
		// Iterate through list reversely (Oldest entry is from the bottom)
		for(int i = (list.size() - 1); i >= 0; i--) {
			// If current List Index rideGrp = to the oldest entry rideGrp, remove it.
			if(oldRideGrp.equals(list.get(i)[list.get(i).length - 1])) {
				list.remove(i);
			}
			else break;		// Break out of for loop as old rideGrp are usually at the bottom index to prevent pointless looping
		}
		
		return list;
	}
}
