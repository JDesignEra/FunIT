import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FunIT {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final double GST = 0.7;
		
		int menu = 0, playerCount = 5, rideGrp = 1;
		double price = 0, amtRec = 0, changeReturn = 0, amtRecTemp = 0;
		int[] age = {0, 0};
		String[] name = {"", ""};
		String creditCard = "", cardDisc = "";
		Boolean mError = true;	//For error message display, on case Default
		
		List<String[]> playerList = new ArrayList<String[]>();			//For storing all transactions
		List<String[]> currentPlayerList = new ArrayList<String[]>();	//For storing current ride players.
		
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
			
			//Try menu input. If error, displays error message.
			try {
				menu = Integer.valueOf(sc.nextLine());
				mError = true;
			}
			catch(Exception e) {
				mError = false;
				System.out.println("You have entered a non-integer value, please enter an integer.\n");
			}
			
			switch(menu) {
				//Add ride players
				case 1:
					if(playerCount > 0) {	//playerCount > 0
						System.out.println("\n=============================================================================");
						System.out.println("|| \u2022 During the process of adding, enter -99 to cancel the adding process. ||");
						System.out.println("|| \u2022 " + playerCount + " PLAYER SLOT(S) LEFT CURRENTLY                                       ||");
						System.out.println("=============================================================================");
						//1st player name
						while(!"-99".equals(name[0])) {
							System.out.print("Enter player name: ");
							name[0] = sc.nextLine();
							
							//Sets other variable to -99 to quit adding process
							if("-99".equals(name[0])) {
								age[0] = -99;
								age[1] = -99;
								name[1] = "-99";
								creditCard = "-99";
								cardDisc = "-99";
								System.out.println("User quit the process.\n");
							}
							//Break out of loop if it's not empty
							else if(!"".equals(name[0])) {
								break;
							}
							else {
								System.out.println("Player name can't be empty or blank.\n");
							}
						}
						
						//1st player name
						while(age[0] != -99) {
							System.out.print("Enter player age: ");
							try {
								age[0] = Integer.valueOf(sc.nextLine());
							}
							catch(Exception e) {
								System.out.println("Invalid input, please enter an integer.");
							}
							
							if(age[0] >= 3 && age[0] <= 80) {
								if(age[0] >= 3 && age[0] <= 5) {
									if(playerCount >=2) {
										playerCount--;
										System.out.println("You have entered a child player, 2nd player has to be an adult.\n");
										break;
									}
									else {
										System.out.println("\nYou can't enter a child player, as there's only 1 available seat left.");
										System.out.println("Please re-enter an age range of 5 to 80 or enter -99 to cancel the process.\n");
									}
								}
								else {
									playerCount--;
									System.out.println("1 player has been registered.\n");
									break;
								}
							}
							else if(age[0] == -99) {	//Sets other variable to -99 to quit adding process
								age[1] = -99;
								name[1] = "-99";
								creditCard = "-99";
								cardDisc = "-99";
								System.out.println("User quit the process.\n");
							}
							else {
								System.out.println("Invalid age range, age range has to be between 3 to 80.\n");
							}
						}
						
						//2nd player name
						while(!"-99".equals(name[1])) {
							if(age[0] >= 3 && age[0] <= 5) {
								System.out.print("Enter 2nd player name: ");
								name[1] = sc.nextLine();
								
								if("-99".equals(name[1])) {		//Sets other variable to -99 to quit adding process
									playerCount++;
									age[1] = -99;
									creditCard = "-99";
									cardDisc = "-99";
									System.out.println("User quit the process.\n");
								}
								else if(!"".equals(name[1])) {
									break;
								}
								else {
									System.out.println("2nd Player name can't be empty or blank.\n");
								}
							}
							else {
								break;
							}
						}
						
						//2nd player age
						while(age[1] != -99) {
							if(age[0] >= 3 && age[0] <= 5) {
								System.out.print("Enter 2nd player age: ");
								try {
									age[1] = Integer.valueOf(sc.nextLine());
								}
								catch(Exception e) {
									System.out.println("Invalid input, please enter an integer.");
								}
								
								if(age[1] == -99) {		//Sets other variable to -99 to quit adding process
									playerCount++;
									creditCard = "-99";
									cardDisc = "-99";
									System.out.println("User quit the process.\n");
								}
								else if(age[1] >= 16 && age[1] <= 80) {
									playerCount--;
									System.out.println("1 child and 1 adult has been registered.\n");
									break;
								}
								else {
									System.out.println("Invalid age range, age range has to be between 16 to 80.\n");
								}
							}
							else {
								break;
							}
						}
						
						//Credit Card
						while(!"-99".equals(creditCard)) {
							System.out.println("=======================================================");
							System.out.println("|| \u2022 10% discount when paying with POSB Credit Card. ||");
							System.out.println("|| \u2022 5% discount when paying with OCBC Credit Card.  ||");
							System.out.println("|| \u2022 No discounts when paying with CASH.             ||");
							System.out.println("|| * Leaving it BLANK will default to CASH payment.  ||");
							System.out.println("=======================================================");
							System.out.print("Enter payment method (POSB / OCBC / CASH): ");
							creditCard = sc.nextLine();
							
							if("-99".equals(creditCard)){		//Sets other variable to 
								for(int i = 0; i < age.length; i++) {
									if(age[i] != 0 && age[i] != -99) {
										playerCount++;
									}
								}
								
								cardDisc = "-99";
								System.out.println("User quit the process.\n");
							}
							else if("POSB".equalsIgnoreCase(creditCard)) {
								System.out.println("You are paying with POSB Credit Card (10% Discount).\n");
								break;
							}
							else if("OCBC".equalsIgnoreCase(creditCard)) {
								System.out.println("You are paying with OCBC Credit Card (5% Discount).\n");
								break;
							}
							else if ("CASH".equalsIgnoreCase(creditCard) || "".equals(creditCard)) {
								creditCard = "CASH";
								System.out.println("You are paying with CASH.\n");
								break;
							}
							else {
								System.out.println("You have entered an invalid value. Please enter POSB, OCBC, CASH or leave it blank for CASH.\n");
							}
						}
						
						//ID Card Discount
						while(!"-99".equals(cardDisc)) {						
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
									for(int i = 0; i < age.length; i++) {
										if(age[i] != 0 && age[i] != -99) {
											playerCount++;
										}
									}
									
									System.out.println("User quit the process.\n");
								}
								else if("NYP Student".equalsIgnoreCase(cardDisc) && (age[0] > 17 || age[1] > 17)) {
									System.out.println("20% discount applied for NYP Student card.");
									break;
								}
								else if("Safra card".equalsIgnoreCase(cardDisc) && (age[0] > 18 || age[1] > 18)) {
									System.out.println("10% discount applied for Safra card");
									break;
								}
								else if("".equals(cardDisc) || "None".equalsIgnoreCase(cardDisc)) {
									cardDisc = "None";
									System.out.println("You do not have NYP Student card or Safra card to be eligible for discounts.\n");
									break;
								}
								else if("Safra card".equalsIgnoreCase(cardDisc) && (age[0] <= 18 || age[1] <= 18)) {
									System.out.println("You do not meet the age requirements for the Safra card discount.\n");
								}
								else {
									System.out.println("You have entered an invalid value. Please enter NYP Student, Safra card, None, or leave it blank for None.\n");
								}
							}
							else {
								cardDisc = "None";
								System.out.println("No players met the age range for the card discounts.\n");
								break;
							}
						}
						
						//Checks for -99 Values and resets to default state (0 / "").
						if(age[0] == -99 || age[1] == -99 || "-99".equals(name[0]) || "-99".equals(name[1]) || "-99".equals(creditCard) || "-99".equals(cardDisc)) {
							for(int i = 0; i < name.length; i++) {
								name[i] = "";
							}
							
							for(int i = 0; i < age.length; i++) {
								age[i] = 0;
							}
							
							creditCard = "";
							cardDisc = "";
						}
						
						//Calculates total for age only
						for(int i = 0; i < age.length; i++) {
							if(age[i] >= 3 && age[i] < 6) {
								price += 5;
							}
							if(age[i] >= 6 && age[i] <=12) {
								price += 8;
							}
							if(age[i] >= 13 && age[i] <= 16) {
								price += 10;
							}	
							if(age[i] >= 17 && age[i] <= 65) {
								price += 15;
							}
							if(age[i] >= 66 && age[i] <= 80) {
								price += 2;
							}
						}
						
						//Applies credit card discounts
						if("POSB".equalsIgnoreCase(creditCard)) {
							price -= (price * 0.10);
						}
						else if("OCBC".equalsIgnoreCase(creditCard)) {
							price -= (price * 0.5);
						}
						
						//Applies ID card discounts
						if("NYP Student".equalsIgnoreCase(cardDisc)) {
							price -= (price * 0.20);
						}
						else if("Safra card".equalsIgnoreCase(cardDisc)) {
							price -= (price * 0.10);
						}
						
						//Add in GST to price
						price += (price * GST);
						
						//Displays Amount Due & prompt for amount received.
						if(price != 0) {
							System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
							
							while(amtRecTemp != -99 && amtRec < price) {
								System.out.print("Enter amount recieved from Customer: ");
								try {
									amtRecTemp = Double.valueOf(sc.nextLine());
								}
								catch(Exception e) {
									System.out.println("Invalid input, please enter an integer or decimal value.\n");
								}
								
								//If -99 entered, set name, age, creditCard and cardDisc to default value ("" and 0)
								if(amtRecTemp == -99) {
									for(int i = 0; i < age.length; i++) {
										if(age[i] != 0 && age[i] != -99) {
											playerCount++;
											
											for(int j = 0; j < name.length; j++) {
												name[j] = "";
											}
											
											for(int j = 0; j < age.length; j++) {
												age[j] = 0;
											}
											
											creditCard = "";
											cardDisc = "";
										}
									}
									
									System.out.println("User quit the process.\n");
								}
								else if(amtRecTemp != 0 && amtRecTemp != -99) {		//Stores and add amtRecTemp to amtRec
									amtRec += amtRecTemp;
									
									if(amtRec >= price) {
										System.out.println("Amount Due has been fully paid.\n");
									}
									else {
										System.out.format("%nAmount Due (After Discount & GST): $%.2f%n", price);
										System.out.format("Amount left to be paid: $%.2f%n", (price - amtRec));
									}
								}
							}
							
							//If amtRec is -99, set price, changeReturn and amtRec to 0
							if(amtRecTemp == -99) {
								price = 0;
								changeReturn = 0;
								amtRec = 0;
								amtRecTemp = 0;
							}
							else {		//Calculates changeReturn
								changeReturn = amtRec - price;
							}
						}
						
						//Checks that -99 are not entered, price and playerCount are valid before storing into playerList & currentPlayerList
						if(!("-99".equals(name[0]) && "-99".equals(name[1]) && age[0] == -99 && age[1] != 99 && "-99".equals(creditCard) && "-99".equals(cardDisc) && price == 0 && playerCount == 0)) {
							//Store current transaction to currentPlayerList
							currentPlayerList.add(new String[] {
								name[0],
								String.valueOf(age[0]),
								name[1],
								String.valueOf(age[1]),
							});
							
							//Change number of players per ride for current ride group in playerList
							for(int i = 0; i < playerList.size(); i++) {
								if(playerList.get(i)[9].equals(String.valueOf(rideGrp))) {
									playerList.set(i, new String[] {
										playerList.get(i)[0],				//name[0]
										playerList.get(i)[1],				//age[0]
										playerList.get(i)[2],				//name[1]
										playerList.get(i)[3],				//age[1]
										playerList.get(i)[4],				//creditCard
										playerList.get(i)[5],				//cardDisc
										playerList.get(i)[6],				//price
										playerList.get(i)[7],				//amtRec
										playerList.get(i)[8],				//changeReturn
										playerList.get(i)[9],				//rideGrp
										String.valueOf(5 - playerCount)		//playerCount (Number of players)
									});
								}
							}
							
							//Stores up to a maximum of 1000 transactions. Else replace.
							if(playerList.size() < 1000) {
								playerList.add(new String[] {
									name[0],
									String.valueOf(age[0]),
									name[1],
									String.valueOf(age[1]),
									creditCard,
									cardDisc,
									String.format("%.2f", price),
									String.format("%.2f", amtRec),
									String.format("%.2f", changeReturn),
									String.valueOf(rideGrp),
									String.valueOf(5 - playerCount)
								});
							}
							else {		//Remove oldest entry of rideGrp sets (First index) and add new entry
								String tempRideGrp = playerList.get(0)[9];
								for(int i = 0; i < playerList.size(); i++) {
									if(tempRideGrp.equals(playerList.get(i)[9])) {
										playerList.remove(playerList.remove(0));
									}
								}

								playerList.add(new String[] {
									name[0],
									String.valueOf(age[0]),
									name[1],
									String.valueOf(age[1]),
									creditCard,
									cardDisc,
									String.format("%.2f", price),
									String.format("%.2f", amtRec),
									String.format("%.2f", changeReturn),
									String.valueOf(rideGrp),
									String.valueOf(5 - playerCount)
								});
							}
						}
						
						//Displays current transaction
						for(int i = 0; i < age.length; i++) {
							if(!"".equals(name[i])) {
								System.out.println("==============================================");
								System.out.println("||            Transaction Detail            ||");
								System.out.println("==============================================");
								System.out.println("Player " + (i+1) + " name: " + name[i]);
							}
							
							if(age[i] != 0) {
								System.out.println("Player " + (i+1) + " age: " + age[i]);
							}
						}
						
						if(!"".equals(creditCard)) {
							System.out.println("Payment Method: " + creditCard);
						}
						
						if(!"".equals(cardDisc)) {
							System.out.println("Card Discount: " + cardDisc);
						}
						
						if(price != 0) {
							System.out.format("Amount Due (After Discount & GST): $%.2f%n", price);
							System.out.format("Change to return: $%.2f%n%n", changeReturn);
							
							System.out.println("Player Slots Left: " + playerCount);
							System.out.println("==============================================\n\n");
						}
					}
					else {
						System.out.println("You can't add anymore players until you start the ride.\n");
					}
					
					//Resets all value after Add ride players process to be reused when adding again.
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
					break;
				
				//Display entire day's transactions
				case 2:
					//If playerList not empty, for each index in playerList display (Latest entry to oldest)
					if(!playerList.isEmpty()) {
						for(int i = (playerList.size() - 1); i >= 0; i--) {
							if(i == (playerList.size() - 1)) {
								System.out.println("\n===========================================");
								System.out.println("||                 Ride " + playerList.get(i)[9] + "                ||");
								System.out.println("===========================================");
							}
							
							//name[0] not empty display
							System.out.println("First Player Name: " + playerList.get(i)[0]);
							
							//age[0] not empty display
							System.out.println("First Player Age: " + playerList.get(i)[1]);
							
							//name[1] not empty display
							if(!"".equals(playerList.get(i)[2])) {
								System.out.println("Second Player Name: " + playerList.get(i)[2]);
							}
							
							//age[1] not 0 display
							if(!"0".equals(playerList.get(i)[3])) {
								System.out.println("Second Player Age: " + playerList.get(i)[3]);
							}
							
							//creditCard
							System.out.println("Payment Method: " + playerList.get(i)[4]);
							
							//cardDisc
							System.out.println("Card Discount: " + playerList.get(i)[5]);
							
							//price
							System.out.println("Amount Due (After Discount & GST): " + playerList.get(i)[6]);
							
							//amtRec
							System.out.println("Amount Received: $" + playerList.get(i)[7]);
							
							//changeReturn
							System.out.println("Change to return: $" + playerList.get(i)[8] + "\n");
							
							try {
								if(!playerList.get(i)[9].equals(playerList.get(i-1)[9])) {
									System.out.println("===========================================");
									System.out.println("Ride " + playerList.get(i)[9] + " Total Players: " + playerList.get(i-1)[10] + "\n");
									System.out.println("\n===========================================");
									System.out.println("||                 Ride " + playerList.get(i-1)[9] + "                ||");
									System.out.println("===========================================");
								}
							}
							catch(IndexOutOfBoundsException e) {
								System.out.println("===========================================");
								System.out.println("Ride " + playerList.get(i)[9] + " Total Players: " + playerList.get(i)[10] + "\n\n");
							}
						}
					}
					else {
						System.out.println("There is no transaction for the day yet to be displayed.\n");
					}
					break;
					
				//Begin the ride
				case 3:
					//If currentPlayerList is not empty
					if(!currentPlayerList.isEmpty()) {
						//Display currentPlayerList
						System.out.println("\n\n");
						for(int i = 0; i < currentPlayerList.size(); i++) {
							System.out.println("========================================");
							System.out.println("||           Player Group " + (i+1) + "           ||");
							System.out.println("========================================");
							
							if(!"".equals(currentPlayerList.get(i)[0])) {
								System.out.println("First Player Name: " + currentPlayerList.get(i)[0]);
							}
							
							if(!"0".equals(currentPlayerList.get(i)[1])) {
								System.out.println("First Player Age: " + currentPlayerList.get(i)[1]);
							}
							
							if(!"".equals(currentPlayerList.get(i)[2])) {
								System.out.println("Second Player Name: " + currentPlayerList.get(i)[2]);
							}
							
							if(!"0".equals(currentPlayerList.get(i)[3])) {
								System.out.println("Second Player Age: " + currentPlayerList.get(i)[3]);
							}
						}
						
						//Display total number of players
						System.out.println("========================================");
						System.out.println("|| Number Of Players For This Ride: " + (5 - playerCount) + " ||");
						System.out.println("========================================");
						
						//resets playerCount to 5
						playerCount = 5;
						
						//clear currentPlayerList
						currentPlayerList.clear();
						
						//Increases rideGrp by 1
						rideGrp++;
						
						//Display start ride message
						System.out.println("\nTHE RIDE HAS BEEN STARTED!\n");
					}
					else {		//Prints can't start message
						System.out.println("\nYou can't begin the ride without any players.");
					}
					break;
				
				//Quit
				case -99:
					System.out.println("User quit the program.");
					sc.close();
					break;
				
				//Invalid CASE value
				default:
					if(mError) {
						System.out.println("Invalid integer value, please enter 1 to 3 or -99 to quit.\n");
					}
					break;
			}
			
			//resets menu to 0 if not -99
			if(menu != -99) {
				menu = 0;
			}
		}
	}
}
