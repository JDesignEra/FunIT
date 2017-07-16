# FunIT
FunIT is a new theme park that provides fun rides for both young and old. The executives of FunIT has decided that they need to implement a system to track all payment transactions and to enforce business rules. You are the software engineer employed to create this new application.

----
### Instructions
- You are expected to do your entire assignment on your own, otherwise, you will be penalized.
- You must build a **command-line interface** (CLI) to your application as opposed to a graphical-user interface (GUI). A command-line interface application does not make use of dialogue boxes to communicate with the user. Instead, it displays its output in the same window in which you have issued the "execute" or "run" command to run your application and it takes its input from the keyboard. In most of our assignments, your application will need to be "menu-driven", i.e., the execution of your application is driven by the menu selection the user has made (from menus displayed on the monitor console).
- You must use validate any input that the user keys and prevent any erroneous and exceptional (i.e., unexpected situations) input that may cause your system to crash that may occur.
- You cannot use any libraries aside from the libraries that comes with standard Java.
- Do not "hardcode" any values into your code. Instead, ask the user to enter this information. This makes your application more flexible.
- You must not create Java packages. You must not create Jar files. You must not create additional classes. You must have all your java files in one directory.

<table>
	<tr>
		<th width="20%">Front Menu</th>
		<td>
			Display the following options to staff
			<ul><li>Add ride players</li>
			<li>Display entire day's transactions</li>
			<li>Begin the ride</li></ul>
			Reads input from user and display repective sub menus.<br>
			Quit from application when user enters -99.<br>
			Upon quitting, display entire day's transactions.<br>
		</td>
	</tr>
	<tr>
		<th>Add ride players</th>
		<td>
			<ul><li>Allows staff to add new player(s) into the queue. System will prompt staff to key in player’s name and age.</li>
			<li>Only player between the age of 3 to 80 are allowed into the rides, and players between the age of 3 and 5 needs to be
			accompanied by an adult between the age of 16 and above.</li>
			<li>For the system to add an accompanying adult, the child needs to be added first.</li>
			<li>The maximum number of players allowed is an underage child and an adult.</li>
			<li>After the number of players has been confirmed, the system will calculate the total price and display
			it to the staff.</li>
			<li>When the staff keys in -99, the application will cancel the process of adding of players and displays
			the main menu.</li>
			</ul>
		</td>
	</tr>
	<tr>
		<th>Calculation of final price of ride</th>
		<td>
			The price of the ride for each player is stated in the table below.
			<table>
				<tr>
					<th>Age</th>
					<th>Price</th>
				</tr>
				<tr>
					<td align="center">Below 6</td>
					<td align="center">$5</td>
				</tr>
				<tr>
					<td align="center">6 - 12</td>
					<td align="center">$8</td>
				</tr>
				<tr>
					<td align="center">13 - 16</td>
					<td align="center">$10</td>
				</tr>
				<tr>
					<td align="center">17 - 65</td>
					<td align="center">$15</td>
				</tr>
				<tr>
					<td align="center">Above 66</td>
					<td align="center">$2</td>
				</tr>
			</table>
			If the player wishes to pay using credit card, the player will get the following discount rate.
			<table>
				<tr>
					<th>Credit Card</th>
					<th>Discount</th>
				</tr>
				<tr>
					<td align="center">POSB</td>
					<td align="center">10%</td>
				</tr>
				<tr>
					<td align="center">OCBC</td>
					<td align="center">5%</td>
				</tr>
			</table>
			If the player's age meets the requirement and has the following ID, s/he will be offered the following discount.
			<table>
				<tr>
					<th>Type</th>
					<th>Discount</th>
					<th>Remarks</th>
				</tr>
				<tr>
					<td align="center">NYP Student</td>
					<td align="center">20%</td>
					<td align="center">Above 17</td>
				</tr>
				<tr>
					<td align="center">Safra card</td>
					<td align="center">10%</td>
					<td align="center">Above 18</td>
				</tr>
			</table>
			<p>For each of the above discounts, display to the staff the appropriate input / menu and await their input so that the
			correct discount can be applied to the final price.</p>
			<p>After the final price has been calculated, a final 7% GST must be applied.</p>
			<p>When the staff keys in -99, cancel the adding of player(s) process.
		</td>
	</tr>
	<tr>
		<th>Ride</th>
		<td>
			<p>As the current ride only allow 1 adult and 1 child ONLY.</p>
			<p>After a player has made full payment, the staff will not be able to add another player until the ride has started.</p>
			<p>Display the player's name(s) after the ride.</p>
		</td>
	</tr>
</table>

----
### Advance requirements
<table>
	<tr>
		<th width="20%">Daily Transactions</th>
		<td>
			<p>Display all the transactions since the start of the day. Each transaction should hold the name of the players, final cost
			and number of players for each ride is being display.</p>
			<strong>Trigger:</strong>
			<ul><li>Input selection from main menu.</li>
			<li>User quits application. (E.g. After user inputs -99 at the main menu)</li></ul>
			<strong>Assumption:</strong>
			<ul><li>Start of the day begins when the application runs.</li>
			<li>End of the day by menu input / close of application.</li>
			<li>Maximum transaction of 1000 is allowed per day.</li></ul>
		</td>
	</tr>
	<tr>
		<th>Ride</th>
		<td>
			<p>Additional of 5 players per ride.</p>
			<p>After the 5 slots has been take up, the staff will not be able to add another player until the ride has started.</p>
			<p>Display the list of players that was added into the ride after it has started</p>
		</td>
	</tr>
</table>

----
### Documentation
Marks will be deducted for lack of documentation or over-commenting. Some guidelines are stated below.

#### Guidline
The proper way to create a function is to first write a header comment, then perhaps a stub, and finally the code.

Each function (this includes "main") must have a header comment containing a short description of what the function is supposed to accomplish. In order to competently tell what the function does, these "headers" must say what the inputs and outputs are.

The header comment of a function must contain the names of the parameters of the function (if any), plus explanations of what kind of data they are supposed to contain. It must explain what the function accomplishes with these objects.

The idea is to tell enough about the function so that people will be able to figure out how to use the function based on your information. You must include enough detail in your headers to get the main idea across, but no more detail than is necessary.

The beginning of the header for the function "main" must contain your name, your admin number, the course name, and the date the writing of the program was started and completed. Each of these things must be clearly labeled.

The header of any function besides "main" must give the names of all its callers (the subprograms that call it) and the names of all its callees (the subprograms it calls), All names of constants, types, variables, functions, etc. must be "self-documenting" and "selfdescribing.". For example, you should call a variable a name such as "total" if it is used to store a total. Call a function a name like "printList" if its job is to print a list. If you are able to name a particular constant, type, or variable so well that it becomes
abundantly clear from its name alone what it is used for, then it may not need any further documentation. Otherwise, you must explain its use with "in-line" documentation next to its declaration.

The decision whether to make other in-line comments is left up to you. However, in-line comments are only needed occasionally in programs that have good headers, good naming conventions, and good organization. "Good form", which is one of the criteria upon which
your program is graded, demands that you avoid over-commenting, as well as undercommenting.

For example, the program line:
```Java
Sum = 0 ; /* We initialize the value of the sum to zero */
```
illustrates blatant over-commenting. The comment inserted to the right of the statement does not add anything to our understanding of what the statement does.

The statement
```Java
Sum = 0 ;
```
alone tells us just as much.

----
### Sructural Design
Don’t place all your codes into the main method. You will be assessed on how your usage of methods to reduce repeated codes in your application.
