import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong>NOTE:</strong>
 * <ul><li>Name &amp; Age should always be the smallest index (Starting from 0) in transactionList object.</li>
 * <li>Name &amp; Age should always be in an alternate index pattern in transactionList object. (e.g. name[0], age[0], name[1], age[1]...).</li>
 * <li>rideGrp should always be the 2nd last index in transactionList object.</li>
 * <li>Total Players / Number of Players should always be the last index in transactionList object.</li>
 * </ul>
 * @see #scInt()
 * @see #scDbl()
 * @see #quitProcess(Object)
 * @see #namePrompt()
 * @see #updateTransListPlayerCount(List, int, int)
 * @see #addToTransactionList(List, String[], int[], int, int, String...)
 * @see #removeTransListOldestEntry(List)
 * @see #printBox(String, int, String, int, char...)
 * @author Joel
 * @version 5.1
 * @since 1.0
 */
public class FunIT {
	private static final Logger logIt = Logger.getGlobal();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
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
			printBox("FunIT", 44, "center", 2, '=');
			printBox("  1. Add rid players", 44, "left", 0);
			printBox("  2. Display entire day's transactions", 44, "left", 0);
			printBox("  3. Begin the ride", 44, "left", 0);
			printBox("-99. Quit", 44, "left", 3, '=');
			System.out.print("Enter your menu selection: ");
			menu = scInt();
			
			switch(menu) {
				// Add ride players
				case 1:
					if(currentPlayerList.size() < 5) {
						System.out.println();
						printBox("\u2022 During the process of adding, enter -99 to cancel the adding process.", 77, "left", 1, '=');
						printBox("\u2022 " + (5 - currentPlayerList.size()) + " PLAYER SLOT(S) LEFT CURRENTLY.", 77, "left", 3, '=');
						
						// First Player name
						while(quitProcess && "".equals(name[0])) {
							name[0] = namePrompt();
							quitProcess = quitProcess(name[0]);
						}

						//First Player age
						while(quitProcess && !(age[0] >= 3 && age[0] <= 80)) {
							System.out.print("Enter player age: ");
							age[0] = scInt();
							quitProcess = quitProcess(age[0]);
							
							if(quitProcess) {
								if(age[0] >= 3 && age[0] <= 80) {
									if(age[0] >= 3 && age[0] <= 5) {
										if(currentPlayerList.size() < 4) {
											System.out.println("You have entered a child player, the 2nd player has to be an adult.\n");
										}
										else {
											System.out.println("\nYou can't enter a child player, as there's only 1 available seat left.");
											System.out.println("Please re-enter an age range of 5 to 80 or -99 to cancel the process.\n");
										}
									}
									else {
										System.out.println("You have entered 1 player.\n");
									}
								}
								else {
									System.out.println("Please enter a player age between 3 to 80, or -99 to quit the process.\n");
								}
							}
						}

						// Second Player name
						while(quitProcess && "".equals(name[1])) {
							if(age[0] >= 3 && age[0] <= 5) {
								name[1] = namePrompt();
								quitProcess = quitProcess(name[1]);
							}
							else break;
						}

						// Second Player age
						while(quitProcess && !(age[1] >= 16 && age[1] <= 80)) {
							if(age[0] >= 3 && age[0] <= 5) {
								System.out.print("Enter 2nd player age: ");
								age[1] = scInt();
								quitProcess = quitProcess(age[1]);
								
								if(quitProcess) {
									if(age[1] >= 16 && age[1] <= 80) {
										System.out.println("You have entered 1 child and 1 adult players.\n");
									}
									else {
										System.out.println("Please enter a player age between 16 to 80.");
									}
								}
							}
							else break;
						}
						
						// Credit Card
						if(quitProcess) {
							printBox("\u2022 10% discount when paying with POSB Credit Card.", 55, "left", 1, '=');
							printBox("\u2022 5% discount when paying with OCBC Credit Card.", 55, "left", 0);
							printBox("\u2022 No discounts when paying with CASH.", 55, "left", 0);
							printBox("* Enter -99 to quit the process.", 55, "left", 0);
							printBox("* Leaving it BLANK will default to CASH payment.", 55, "left", 3, '=');
							
							while(quitProcess && !("POSB".equalsIgnoreCase(creditCard) || "OCBC".equalsIgnoreCase(creditCard) || "CASH".equalsIgnoreCase(creditCard))) {
								System.out.print("Enter payment method (POSB / OCBC / CASH): ");
								creditCard = sc.nextLine();
								quitProcess = quitProcess(creditCard);
								
								if(quitProcess) {
									if("POSB".equalsIgnoreCase(creditCard)) {
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
							}
						}
						
						// ID Card Discount
						if(quitProcess) {
							if((age[0] > 17 && age[0] <= 80) || (age[1] > 17 && age[1] <= 80)) {
								printBox("\u2022 20% discount for NYP Student card and age above 17.", 62, "left", 1, '=');
								printBox("\u2022 10% discount for Safra card and age above 18.", 62, "left", 0);
								printBox("* Only a maximum of one ID card discount can be applied.", 62, "left", 0);
								printBox("* Enter -99 to quit the process", 62, "left", 0);
								printBox("* Leaving it BLANK will default to None (No discounts).", 62, "left", 3, '=');
								
								while(quitProcess && !("NYP Student".equalsIgnoreCase(cardDisc) || "Safra card".equalsIgnoreCase(cardDisc) || "None".equalsIgnoreCase(cardDisc))) {
									System.out.print("Enter card discount (NYP Student / Safra card / None): ");
									cardDisc = sc.nextLine();
									quitProcess = quitProcess(cardDisc);
									
									if(quitProcess) {
										if("NYP Student".equalsIgnoreCase(cardDisc) && (age[0] > 17 || age[1] > 17)) {
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
								}
							}
							else {
								cardDisc = "None";
								System.out.println("No players met the age range for the card discounts.\n");
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
								quitProcess = quitProcess(amtRecTemp);
								
								if(quitProcess) {
									if(amtRecTemp >= 0) {
										amtRec += amtRecTemp;
										
										if(amtRec >= price) {
											System.out.println("Amount Due has been fully paid.\n");
										}
										else {
											System.out.format("%nAmount left to be paid: $%.2f%n", (price - amtRec));
										}
									}
									else {
										System.out.println("Please enter a postive double value, or -99 to quit the process.\n");
									}
								}
							}
							
							// Calculate change, Display current transaction and store to lists
							if(quitProcess) {
								changeReturn = amtRec - price;
								
								System.out.println();
								printBox("Transaction Detail", 45, "center", 2, '=');
								
								// Stores to currentPlayerList if name is not empty and age is not 0, and display
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
								System.out.format("Change to return: $%.2f%n", changeReturn);
								printBox("Player Slots Left: " + (5 - currentPlayerList.size()), 45, "left", 2, '=');
								System.out.println("\n");
								
								// Update number of players of the current ride group in transactionList
								transactionList = updateTransListPlayerCount(transactionList, rideGrp, currentPlayerList.size());
								
								// Stores up to a max of 1000 transactions. Else remove the the oldest rideGrp transaction and add new entry
								if(transactionList.size() < 1000) {
									transactionList = addToTransactionList(transactionList, name, age, rideGrp, currentPlayerList.size(), creditCard, cardDisc, String.format("%.2f", price), String.format("%.2f", amtRec), String.format("%.2f", changeReturn));
								}
								else {		// Remove oldest rideGrp entries (Last Index) and add new entry
									transactionList = removeTransListOldestEntry(transactionList);
									transactionList = addToTransactionList(transactionList, name, age, rideGrp, currentPlayerList.size(), creditCard, cardDisc, String.format("%.2f", price), String.format("%.2f", amtRec), String.format("%.2f", changeReturn));
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
							String rideGrpGet = transactionList.get(i)[9];
							
							if(i == 0) {
								System.out.println("");
								printBox("RIDE " + transactionList.get(i)[9], 45, "center", 2, '=');
							}
							
							for (int x = 0; x < (name.length * 2) && x < (age.length * 2); x+= 2) {
								String nameGet = transactionList.get(i)[x];
								String ageGet = transactionList.get(i)[x+1];
								
								if(!("".equals(nameGet) && "0".equals(ageGet))) {
									System.out.println("Player Name: " + nameGet);
									System.out.println("Player Age: " + ageGet);
								}
							}
							
							System.out.println("Payment Method: " + transactionList.get(i)[4]);
							System.out.println("Card Discount: " + transactionList.get(i)[5]);
							System.out.println("Amount Due (After Discount & GST): $" + transactionList.get(i)[6]);
							System.out.println("Amount Received: $" + transactionList.get(i)[7]);
							System.out.println("Change to return: $" + transactionList.get(i)[8]);
							
							if(i == transactionList.size() - 1) {
								printBox("Ride " + rideGrpGet + " Total Players: " + transactionList.get(i)[10], 45, "left", 2, '-');
								System.out.println("\n");
							}
							else {
								int nxtIndexLen = transactionList.get(i+1).length;
								String nxtRideGrpGet = transactionList.get(i+1)[nxtIndexLen - 2];
														
								if(!rideGrpGet.equals(nxtRideGrpGet)) {
									printBox("Ride " + rideGrpGet + " Total Players: " + transactionList.get(i)[10], 45, "left", 2, '-');
									System.out.println();
									printBox("RIDE " + nxtRideGrpGet, 45, "center", 2, '=');
								}
								else {
									printBox("", 45, "left", 4, '-');
								}
							}
						}
					}
					else {
						System.out.println("There is no transaction for the day to be display yet.\n");
					}
				break;
				
				// Begin the ride
				case 3:
					if(!currentPlayerList.isEmpty()) {
						System.out.println("\n");
						printBox("Current Ride Players", 45, "center", 2, '=');
						
						for(int i = 0; i < currentPlayerList.size(); i++) {
							System.out.println("Player " + (i+1) + " Name: " + currentPlayerList.get(i)[0]);
							System.out.println("Player " + (i+1) + " Age: " + currentPlayerList.get(i)[1]);
						}
						
						// Display total number of players
						printBox("Number Of Players For This Ride: " + currentPlayerList.size(), 45, "left", 2, '=');
						System.out.println("\nTHE RIDE HAS BEEN STARTED!\n");
						
						currentPlayerList.clear();
						rideGrp++;
					}
					else {
						System.out.println("You can't begin the ride without any players.\n");
					}
				break;
				
				// Quit
				case -99:
					System.out.println("User has quit the program.\n");
					sc.close();
				break;
				
				default:
					System.out.println("Please enter 1, 2, 3 or -99 to quit the program.\n");
				break;
			}
		}
	}
	
	/**
	 * Fixes Java nextInt() / nextLine() issues.<br>
	 * Documentation on <a href="http://bugs.java.com/bugdatabase/view_bug.do?bug_id=8170457">Oracle</a>.
	 * @return User input value [ int ]
	 */
	public static int scInt() {
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
	
	/**
	 * Try &amp; Catch Double input with own error message.
	 * @return User input value [ double ]
	 */
	public static double scDbl() {
		double i = 0;
		
		try {
			i = Double.valueOf(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input, please enter an Integer or Double value.");
		}
		
		return i;
	}
	
	/**
	 * Checks if input it's -99, display appropriate message and return value to boolean quitProcess.
	 * @param input Input value to check with [ String / double / int ]
	 * @return boolean false / boolean true
	 */
	public static boolean quitProcess(Object input) {
		String strInput = String.valueOf(input);
		if(strInput.contains(".")) {
			strInput = strInput.substring(0, strInput.indexOf('.'));
		}
		
		if("-99".equals(strInput)) {
			System.out.println("User has quit the process.\n");
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * For prompting player name and display appropriate message after input.
	 * @return User input value. [ String ]
	 */
	public static String namePrompt() {
		String name;
		
		System.out.print("Enter player name: ");
		name = sc.nextLine();
		
		if("".equals(name)) {
			System.out.println("Player name can't be empty.\n");
		}
		
		return name;
	}
	
	/**
	 * For updating transactionList player count for the current ride group.
	 * @param list List to be updated. [ List&#60;String[]&#62; transactionList ]
	 * @param rideGrp Current ride group number. [ int rideGrp ]
	 * @param playerCount Current number of players. [ int currentListPlayers.size() ]
	 * @return Updated List. [ List&#60;String[]&#62; ]
	 */
	public static List<String[]> updateTransListPlayerCount(List<String[]> list, int rideGrp, int playerCount) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i)[list.get(i).length - 2].equals(String.valueOf(rideGrp)) && !list.get(i)[list.get(i).length - 1].equals(String.valueOf(playerCount))) {
				String[] obj = new String[list.get(i).length];
				
				// Update playerCount and store all values into temporary variable,
				for(int x = 0; x < list.get(i).length; x++) {
					if(x == list.get(i).length - 1) {
						obj[x] = String.valueOf(playerCount);
					}
					else obj[x] = list.get(i)[x];
				}
				
				list.set(i, obj);
			}
			else break;		// Break out of for loop as current rideGrp are usually at the top index to prevent pointless looping
		}
		
		return list;
	}
	
	/**
	 * For adding values to transactionList &amp; helps to ensure that key or important values are placed in the right index.
	 * @param list List to add to. [ List&#60;String[]&#62; transactionList ]
	 * @param name Name to be added. [ String[] name ]
	 * @param age Age to be added. [ int[] age ]
	 * @param rideGrp Current ride group number to be added. [ int rideGrp ]
	 * @param playerCount Player count to be added. [ int currentPlayerList.size() ]
	 * @param strs Various values that needs to be added. [ String variables ]
	 * @return Updated list. [ List&#60;String[]&#62; ]
	 */
	public static List<String[]> addToTransactionList(List<String[]> list, String[] name, int[] age, int rideGrp, int playerCount, String... strs) {
		List<String> tempList = new ArrayList<String>();
		
		String[] obj;
		
		for(int i = 0; i < name.length && i < age.length; i++) {
			tempList.add(name[i]);
			tempList.add(String.valueOf(age[i]));
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
	
	/**
	 * For removing oldest rideGrp entries in transactionList.
	 * @param list List to remove from. [ List&#60;String[]&#62; transactionList ]
	 * @return Updated list [ List&#60;String[]&#62; ]
	 */
	public static List<String[]> removeTransListOldestEntry(List<String[]> list) {
		String oldRideGrp = list.get(list.size() - 1)[list.get(list.size() - 1).length - 1];
		
		// Iterate through list reversely (Oldest entry is from the bottom)
		for(int i = (list.size() - 1); i >= 0; i--) {
			if(oldRideGrp.equals(list.get(i)[list.get(i).length - 1])) {
				list.remove(i);
			}
			else break;		// Break out of for loop as old rideGrp are usually at the bottom index to prevent pointless looping
		}
		
		return list;
	}
	
	/**
	 * <p>This method is being use to print a table or box with or without message in it with ease.</p>
	 * <strong>OPTION:</strong>
	 * <ol start="0"><li>Message Only.</li>
	 * <li>Top border &amp; Message.</li>
	 * <li>Top &amp; bottom border with Message.</li>
	 * <li>Bottom border with Message.</li>
	 * <li>Border Only. (Defaults)</ol>
	 * @param display Message to be display. [ String ]
	 * @param width Width of border / table. [ int ]
	 * @param align Alignment of the text in the table. [ String left / center / right ]
	 * @param opt See OPTION for style explanation. [ int 1 / 2 / 3 / 4 ]
	 * @param border Style of the border. (e.g. '-', '=' &amp; e.t.c) [ char '-', '=', '_' ]
	 */
	public static void printBox(String display, int width, String align, int opt, char... border) {
		if(width >= display.length() + 6) {
			StringBuilder buildBorder = new StringBuilder();
			String displayBorder = "";
			int leftMax = 0,
				rightMax = 0,
				leftRightMax = width - display.length();
			
			for(int i = 0; i < width && border.length > 0; i++) {
				buildBorder.append(border[0]);
			}
			
			displayBorder = buildBorder.toString();
			
			if("center".equalsIgnoreCase(align)) {
				leftMax = -(leftRightMax / 2);
				rightMax = (int) Math.round((double) leftRightMax / 2);
			}
			else if("right".equalsIgnoreCase(align)) {
				rightMax = 3;
				leftMax = -(leftRightMax - rightMax);
			}
			else {
				leftMax = -3;
				rightMax = leftRightMax - Math.abs(leftMax);
			}
			
			display = String.format("%" + leftMax + "s%s" + "%" + rightMax + "s", "||", display, "||");
			
			switch(opt) {
				case 2:		// Top & Bottom border with message.
					System.out.println(displayBorder);
				case 3:		// Bottom border with message.
					System.out.println(display);
					System.out.println(displayBorder);
				break;
				
				case 1:		// Top border with message.
					System.out.println(displayBorder);
				case 0:		// Message only
					System.out.println(display);
				break;
				
				default:	// Border only
					System.out.println(displayBorder);
				break;
			}
		}
		else {
			logIt.log(Level.SEVERE, "Width parameter is too short.", new Exception("Please change the parameter of the width to a min of " + (display.length() + 6)));
		}
	}
}