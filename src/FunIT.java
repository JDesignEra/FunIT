import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Name: Tan Kok Kiang Joel<br>
 * Admin No.: 174866J<br>
 * Course Name: DPST<br>
 * Class: BI1701<br>
 * Start Date: 12 June 2017<br>
 * Completed Date: 28 July 2017
 * @see #scInt()
 * @see #scBigDecimal()
 * @see #quitProcess(Object)
 * @see #namePrompt()
 * @see #printTransactions(String[][], String[], int[])
 * @see #updateTransPlayerCount(String[][], int, int)
 * @see #addTransactions(String[][], String[], int[], int, int, String...)
 * @see #removeTransOldestEntry(String[][])
 * @see #printBox(String, int, String, int, char...)
 * @author Joel
 * @version 7.5
 * @since 1.0
 */
public class FunIT {
	private static final Logger logIt = Logger.getGlobal();
	private static final Scanner sc = new Scanner(System.in);
	
	/**
	 * <strong>NOTE:</strong><br>
	 * Some clarification,<br>
	 * <p><strong>currentPlayers[5][]</strong><br>
	 * Is for storing current ride players, 1 adult & one child is consider 2 players, and 1 adult is considered as 1 player.</p>
	 * <p><strong>transactions[1000][]</strong><br>
	 * Is for storing transactions. 1 adult or 1 adult & 1 child is considered one transaction.</p>
	 * <p><strong>BigDecimal</strong><br>
	 * The reason for using BigDecimal instead of double or float is because double/float can inexact as<br>
	 * you performed multiple calculation (addition, subtraction, multiplication, division),<br>
	 * which makes floats and doubles inadequate for dealing with money.</p>
	 */
	public static void main(String[] args) {
		final double GST = 0.07;
		
		int menu = 0, rideGrp = 1, playerCount = 0;
		BigDecimal amtRec = new BigDecimal(0), amtRecInput = new BigDecimal(0), price = new BigDecimal(0), changeReturn = new BigDecimal(0);
		String creditCard = "", cardDisc = "";
		boolean quitProcess = true;		// false == User quit the process with -99
		
		String[] name = {"", ""};
		int[] age = {0, 0};
		String[][] currentPlayers = new String[5][], transactions = new String[1000][];
		
		while(menu != -99) {
			printBox("FunIT", 44, "center", 2, '=');
			printBox("  1. Add ride players", 44, "left", 0);
			printBox("  2. Display entire day's transactions", 44, "left", 0);
			printBox("  3. Begin the ride", 44, "left", 0);
			printBox("-99. Quit", 44, "left", 3, '=');
			System.out.print("Enter your menu selection: ");
			menu = scInt();
			
			switch(menu) {
				case 1:
					if(playerCount < 5) {
						System.out.println();
						printBox("\u2022 During the process of adding, enter -99 to cancel the adding process.", 77, "left", 1, '=');
						printBox("\u2022 Current ride group is " + rideGrp, 77, "left", 0);
						printBox("\u2022 " + (5 - playerCount) + " PLAYER SLOT(S) LEFT CURRENTLY.", 77, "left", 3, '=');
						
						// First Player name
						while(quitProcess && ("".equals(name[0]) || name[0].matches(".*\\d+.*"))) {
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
										if(playerCount < 4) {
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
						while(quitProcess && ("".equals(name[1]) || name[1].matches(".*\\d+.*"))) {
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
						
						// Price calculation and Prompt for amount received
						if(quitProcess) {
							// Calculates total price for age
							for(int i : age) {
								if(i >= 3 && i < 6) {
									price = price.add(new BigDecimal(5));
								}
								else if(i >= 6 && i <= 12) {
									price = price.add(new BigDecimal(8));
								}
								else if(i >=13 && i <= 16) {
									price = price.add(new BigDecimal(10));
								}
								else if(i >= 17 && i <= 65) {
									price = price.add(new BigDecimal(15));
								}
								else if(i >=66 && i <= 80) {
									price = price.add(new BigDecimal(2));
								}
							}
							

							
							// Applies credit card discounts
							if("POSB".equalsIgnoreCase(creditCard)) {
								price = price.add(price.multiply(BigDecimal.valueOf(0.10)));
							}
							else if("OCBC".equalsIgnoreCase(creditCard)) {
								price = price.add(price.multiply(BigDecimal.valueOf(0.05)));
							}
							
							// Applies ID card discounts
							if("NYP Student".equalsIgnoreCase(cardDisc)) {
								price = price.add(price.multiply(BigDecimal.valueOf(0.20)));
							}
							else if("Safra Card".equalsIgnoreCase(cardDisc)) {
								price = price.add(price.multiply(BigDecimal.valueOf(0.10)));
							}
							
							price = price.add(price.multiply(BigDecimal.valueOf(GST)));		// Applies GST to Price
							price = price.setScale(2, BigDecimal.ROUND_HALF_UP);			// Round to 2 Decimal
							
							System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
							
							// Prompt for amount received
							
							while(quitProcess && amtRec.compareTo(price) < 0) {
								System.out.print("Enter amount recieved from Customer: $");
								amtRecInput = scBigDecimal();
								quitProcess = quitProcess(amtRecInput);
								
								if(quitProcess) {
									if(amtRecInput.compareTo(BigDecimal.ZERO) >= 0) {
										amtRec = amtRec.add(amtRecInput);
										
										if(amtRec.compareTo(price) >= 0) {
											System.out.println("Amount Due has been fully paid.\n");
										}
										else {
											System.out.format("%nAmount left to be paid: $%.2f%n", (price.subtract(amtRec)));
										}
									}
									else {
										System.out.println("Please enter a postive double value, or -99 to quit the process.\n");
									}
								}
							}
							
							// Calculate change, Display current transaction and store to currentPlayers
							if(quitProcess) {
								changeReturn = amtRec.subtract(price);
								
								System.out.println();
								printBox("Transaction Detail", 45, "center", 2, '=');
								
								for(int i = 0; i < name.length && i < age.length; i++) {
									// Store player name and age into currentPlayers if it's not "" && 0.
									if(!"".equals(name[i]) && age[i] != 0) {
										for(int x = 0; x < currentPlayers.length; x++) {
											if(currentPlayers[x] == null) {
												String[] newPlayer = {name[i], String.valueOf(age[i])};
												currentPlayers[x] = newPlayer;
												break;
											}
										}
										
										playerCount++;
										System.out.println("Player " + (i+1) + " name: " + name[i]);
										System.out.println("Player " + (i+1) + " age: " + age[i]);
									}
								}
								
								System.out.println("Payment Method: " + creditCard);
								System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
								System.out.format("Change to return: $%.2f%n", changeReturn);
								printBox("Player Slots Left: " + (5 - playerCount), 45, "left", 2, '=');
								System.out.println("\n");
								rtnToMenuPrompt();
								
								// Update number of players of the current ride group in transactions & stores new transaction
								updateTransPlayerCount(transactions, rideGrp, playerCount);
								addTransactions(transactions, name, age, rideGrp, playerCount, creditCard, cardDisc, String.format("%.2f", price), String.format("%.2f", amtRec), String.format("%.2f", changeReturn));
							}
						}
						
						// Reset values to be reused
						for(int i = 0; i < name.length; i++) {
							name[i] = "";
						}
						
						for(int i = 0; i < age.length; i++) {
							age[i] = 0;
						}
						
						creditCard = "";
						cardDisc = "";
						price = new BigDecimal(0);
						amtRec = new BigDecimal(0);
						quitProcess = true;
					}
					else {
						System.out.println("You can't add anymore players until you start the ride.\n");
					}
				break;
				
				case 2:
					if(transactions[0] != null) {
						printTransactions(transactions, name, age);
						rtnToMenuPrompt();
					}
					else {
						System.out.println("There is no transaction for the day to be display yet.\n");
					}
				break;
				
				case 3:
					if(currentPlayers[0] != null) {
						System.out.println("\n");
						printBox("Current Ride Players", 45, "center", 2, '=');
						
						for(int i = 0; i < currentPlayers.length && currentPlayers[i] != null; i++) {
							for(int x = 0; x < currentPlayers[i].length; x++) {
								if(x % 2 == 0) {
									System.out.println("Player " + (i+1) + " Name: " + currentPlayers[i][x]);
								}
								else {
									System.out.println("Player " + (i+1) + " Age: " + currentPlayers[i][x]);
								}
							}
						}
						
						printBox("Number Of Players For This Ride: " + (playerCount), 45, "left", 2, '=');
						System.out.println();
						printBox("THE RIDE HAS BEEN STARTED!", 45, "center", 1, '-');
						rtnToMenuPrompt();
						
						for(int i = 0; i < currentPlayers.length; i++) {
							currentPlayers[i] = null;
						}
						
						playerCount = 0;
						rideGrp++;
					}
					else {
						System.out.println("You can't begin the ride without any players.\n");
					}
				break;
				
				case -99:
					printTransactions(transactions, name, age);
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
		
		try {
			i = Integer.valueOf(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input, please enter an Integer value.");
		}
		
		return i;
	}
	
	/**
	 * Try & Catch BigDecimal input with own error message.
	 * @return User input value [ BigDecimal ]
	 */
	public static BigDecimal scBigDecimal() {
		BigDecimal i = new BigDecimal(0);
		
		try {
			i = BigDecimal.valueOf(Double.valueOf(sc.nextLine()));
			i = i.setScale(2, BigDecimal.ROUND_HALF_DOWN);		// Round Down to 2 Decimal
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input, please enter an Integer or Decimal value.");
		}
		
		return i;
	}
	
	/**
	 * Checks if input is -99, display appropriate message and return value to boolean quitProcess.
	 * @param input Input value to check with [ String / double / int ]
	 * @return boolean false / boolean true
	 */
	public static boolean quitProcess(Object input) {
		if(input instanceof BigDecimal) {
			if(((BigDecimal) input).compareTo(new BigDecimal(-99)) == 0) {
				System.out.println("User has quit the process.\n");
				return false;
			}
		}
		else if(input instanceof Integer) {
			if((int) input == -99) {
				System.out.println("User has quit the process.\n");
				return false;
			}
		}
		else {
			if("-99".equals((String) input)) {
				System.out.println("User has quit the process.\n");
				return false;
			}
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
		else if(name.matches(".*\\d+.*") && !"-99".equals(name)) {
			System.out.println("Player name can't contain numeric characters.\n");
		}
		
		return name;
	}
	
	/**
	 * For prompting user to press enter to return to the main menu.
	 */
	public static void rtnToMenuPrompt() {
		printBox("PRESS ENTER TO RETURN TO MAIN MENU", 45, "center", 2, '-');
		sc.nextLine();
		
		System.out.println("\n\n");
	}
	
	/**
	 * For updating transactions player count for the current ride group.
	 * @param transactions 2 Dimensional transactions array to be updated. [ String[][] transactions ]
	 * @param rideGrp Current ride group number. [ int rideGrp ]
	 * @param playerCount Current number of players. [ int playerCount ]
	 * @return Updated 2 Dimensional transactions array [ String[][] transactions ] 
	 */
	public static String[][] updateTransPlayerCount(String[][] transactions, int rideGrp, int playerCount) {
		for(int i = 0; i < transactions.length; i++) {
			if(transactions[i] != null && transactions[i][transactions[i].length - 2].equals(String.valueOf(rideGrp))) {
				transactions[i][transactions[i].length - 1] = String.valueOf(playerCount);
			}
			else break;
		}
		
		return transactions;
	}
	
	/**
	 * For adding values to transactions & helps to ensure that key or important values are placed in the right index.
	 * @param transactions 2 Dimensional transactions array to be updated. [ String[][] transactions ]
	 * @param name Name to be added. [ String[] name ]
	 * @param age Age to be added. [ int[] age ]
	 * @param rideGrp Current ride group number to be added. [ int rideGrp ]
	 * @param playerCount Player count to be added. [ int currentPlayerList.size() ]
	 * @param strs Various values that needs to be added. [ String variables ]
	 * @return Updated 2 Dimensional transactions array [ String[][] transactions ]
	 */
	public static String[][] addTransactions(String[][] transactions, String[] name, int[] age, int rideGrp, int playerCount, String... strs) {
		String[] newTransaction = new String[name.length + age.length + strs.length + 2];
		
		// Build newTransaction array
		for(int i = 0; i < name.length && i < age.length; i++) {
			newTransaction[i*2] = name[i];
			newTransaction[i*2+1] = String.valueOf(age[i]);
		}
		
		for(int i = 0; i < strs.length; i++) {
			newTransaction[name.length + age.length + i] = strs[i];
		}
		
		newTransaction[newTransaction.length - 2] = String.valueOf(rideGrp);
		newTransaction[newTransaction.length - 1] = String.valueOf(playerCount);
		
		// Remove all transactions with the oldest rideGrp number if last index != null
		removeTransOldestEntry(transactions);
		
		// Shift before inserting newTransaction into first index
		for(int i = transactions.length - 1; i > 0; i--) {
			transactions[i] = transactions[i - 1];
		}
		
		transactions[0] = newTransaction;
		
		return transactions;
	}
	
	/**
	 * For removing oldest rideGrp entries in transactions when transaction is cap.
	 * @param transactions 2 Dimensional transactions array to be updated. [ String[][] transactions ]
	 * @return Updated 2 Dimensional transactions array [ String[][] transactions ]
	 */
	public static String[][] removeTransOldestEntry(String[][] transactions) {
		// If last index != null then transaction is cap, therefore remove all transactions with the oldest rideGrp number entries
		if(transactions[transactions.length - 1] != null) {
			// Get last index(Oldest) transaction rideGrp number
			String oldRideGrp = transactions[transactions.length - 1][transactions[transactions.length - 1].length - 2];
			
			for(int i = transactions.length - 1; i > 0; i--) {
				if(oldRideGrp.equals(transactions[i][transactions[i].length - 2])) {
					transactions[i] = null;
				}
				else break;		// Break to prevent redundant loop.
			}
		}
		
		return transactions;
	}
	
	/**
	 * For printing or displaying entire day transactions
	 * @param transactions 2 Dimensional transactions array to be updated. [ String[][] transactions ]
	 * @param name For counting name[] length [ String[] age ]
	 * @param age For counting age[] length [ int[] age ]
	 */
	public static void printTransactions(String[][] transactions, String[] name, int[] age) {
		for(int i = 0; i < transactions.length && transactions[i] != null; i++) {
			String rideGrpGet = transactions[i][transactions[i].length - 2],
					totaPlayerGet = transactions[i][transactions[i].length - 1];
			
			if(i == 0) {
				System.out.println("");
				printBox("RIDE " + rideGrpGet, 45, "center", 2, '=');
			}
			
			for(int x = 0; x < (name.length * 2) && x < (age.length * 2); x+=2) {
				String nameGet = transactions[i][x];
				String ageGet = transactions[i][x+1];
				
				if(!("".equals(nameGet) && "0".equals(ageGet))) {
					System.out.println("Player Name: " + nameGet);
					System.out.println("Player Age: " + ageGet);
				}
			}
			
			System.out.println("Payment Method: " + transactions[i][4]);
			System.out.println("Card Discount: " + transactions[i][5]);
			System.out.println("Amount Due (After Discount & GST): $" + transactions[i][6]);
			System.out.println("Amount Received: $" + transactions[i][7]);
			System.out.println("Change to return: $" + transactions[i][8]);
			
			if(i == transactions.length - 1 || transactions[i+1] == null) {
				printBox("Ride " + rideGrpGet + " Total Players: " + totaPlayerGet, 45, "left", 2, '-');
				System.out.println("\n");
			}
			else {
				String nxtRideGrpGet = transactions[i+1][transactions[i+1].length - 2];
				
				if(!rideGrpGet.equals(nxtRideGrpGet)) {
					printBox("Ride " + rideGrpGet + " Total Players: " + totaPlayerGet, 45, "left", 2, '-');
					System.out.println();
					printBox("RIDE " + nxtRideGrpGet, 45, "center", 2, '=');
				}
				else {
					printBox("", 45, "left", 4, '-');
				}
			}
		}
	}
	
	/**
	 * <p>This method is being use to print a table or box with or without message in it with ease,<br>
	 * without having to manually count the number of characters to ensure equal length for a table / box.</p>
	 * <strong>OPTION:</strong>
	 * <ol start="0"><li>Message Only with side border.</li>
	 * <li>Top border & Message.</li>
	 * <li>Top & bottom border with Message.</li>
	 * <li>Bottom border with Message.</li>
	 * <li>Border Only. (Default)</ol>
	 * @param display Message to be display. [ String ]
	 * @param width Width of border / table. [ int ]
	 * @param align Alignment of the text in the table. (Defaults to left) [ String left / center / right ]
	 * @param opt See OPTION for style explanation. [ int 1 / 2 / 3 / 4 ]
	 * @param border Style of the top & bottom border. (e.g. '-', '=' & e.t.c) [ char '-' / '=' / '_' ]
	 */
	public static void printBox(String display, int width, String align, int opt, char... border) {
		if(width >= display.length() + 6) {
			StringBuilder buildBorder = new StringBuilder();
			String displayBorder = "";
			int leftMax = 0,
				rightMax = 0,
				leftRightMax = width - display.length();
			
			// Build border length
			for(int i = 0; i < width && border.length > 0; i++) {
				buildBorder.append(border[0]);
			}
			
			displayBorder = buildBorder.toString();
			
			// Calculates alignment left and right spacing
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
			// Display proper logging message with minimum required character count number with stack trace
			logIt.log(Level.SEVERE, "Width parameter is too short.", new Exception("Please change the parameter of the width to a minimum of " + (display.length() + 6)));
		}
	}
}
