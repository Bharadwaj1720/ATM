import java.util.*;
public class ATM {
	//This is the main class
	//ALl the methods are called from here
	static Entry E = new Entry();
	static Account key = new Account();
	static BankDatabase B = new BankDatabase();
	static Slip sp = new Slip();
	//Various static objects are created,so that they can be used in whole code.
	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	E.display(scan);
	//We call display method of Entry class
	int C=key.getChoice();
	if(C==1){
		System.out.println("Your balance: "+B.getAvailableBalance(key.getAccountNumber()));
		System.out.println("Do you want to print the total balance?\n1)Yes\n2)No");
		int p = scan.nextInt();
		if(p==1) {
			System.out.println("Printing your balance on the slip. Please collect the slip.");
			System.out.println("Name: "+ATM.B.getName(ATM.key.getAccountNumber()));
			System.out.println("Account Number: "+ATM.key.getAccountNumber()+"\nTotal Balance: "+B.getAvailableBalance(key.getAccountNumber()));
		}
		System.out.println("-------------------------------------");
		ATM.main(null);
	}
	if(C==2) {
		withdrawalMenu WM = new withdrawalMenu();
		Withdrawal W = new Withdrawal();
		WM.display(scan);
		if(ATM.key.getAmount()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		if(ATM.key.getAmount()<0) {
			System.out.println("Invalid amount is entered. Process cancelled.");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		W.execute();
		ATM.main(null);
	}
	if(C==3) {
		depositMenu DM = new depositMenu();
		Deposit D = new Deposit();
		DM.display(scan);
		if(ATM.key.getAmount()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		if(ATM.key.getAmount()<0) {
			System.out.println("Invalid amount is entered. Process cancelled.");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		D.execute();
		ATM.main(null);
		
	}
	if(C==4) {
		fundMenu FM = new fundMenu();
		FundTransfer FT = new FundTransfer();
		FM.display(scan);
		if(ATM.key.getAmount()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		if(ATM.key.getAmount()<0) {
			System.out.println("Invalid amount is entered. Process cancelled.");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		FT.execute();
		ATM.main(null);
	}
	if(C==5) {
		changePinMenu CM = new changePinMenu();
		ChangePin CP = new ChangePin();
		CM.display(scan);
		if(ATM.key.getNewPin()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		if(ATM.key.getNewPin()<1000 || ATM.key.getNewPin()>9999) {
			System.out.println("The entered pin is out of range\n Process cancelled");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		CP.execute();
		System.out.println("-------------------------------------");
		ATM.main(null);
	}
	if(C==6) {
		System.out.println("-------------------------------------");
		ATM.main(null);
	}
	else {
		System.out.println("Wrong option is selected");
		System.out.println("-------------------------------------");
		ATM.main(null);
	}
	}
}
abstract class Screen{
	//This is an abstract class
	public abstract void display(Scanner scan);
	
}
class Entry extends Screen{
	//This class is responsible to display the entry details,like account number and pin.
	public void display(Scanner scan) {
		System.out.println("Welcome to the ATM of Bharadwaj Bank!\n\nPlease enter your account number:");
		ATM.key.setAccountNumber(scan);
		while(ATM.B.checkAccountNumber(ATM.key.getAccountNumber())==false) {
			System.out.println("Sorry invalid Account Number\nTry again...\nEnter '0' to exit");
			ATM.key.setAccountNumber(scan);
			if(ATM.key.getAccountNumber()==0) {
				break;
			}
		}
		if(ATM.key.getAccountNumber()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		System.out.println("Enter your ATM PIN: ");
		ATM.key.setPin(scan);
		while(ATM.B.checkPIN(ATM.key.getPin(),ATM.key.getAccountNumber())==false) {
			System.out.println("Sorry invalid PIN\nTry again...\nEnter '0' to exit");
			ATM.key.setPin(scan);
			if(ATM.key.getPin()==0) {
				break;
			}
		}
		if(ATM.key.getPin()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		System.out.println("Hello "+ATM.B.getName(ATM.key.getAccountNumber()));
		System.out.println("Main menu\n\t1 - View my balance\n\t2 - Withdraw cash\n\t3 - Deposit funds\n\t4 - Fund transfer\n\t5 - Change Pin\n\t6 - Exit\nEnter a choice: ");
		ATM.key.setChoice(scan);
	}
}
class withdrawalMenu extends Screen{
	//This is used to display Withdrawal menu
	public void display(Scanner scan) {
		System.out.println("Withdrawal menu\n\tEnter the amount: \n\nEnter 0 if you want to cancel the process");
		ATM.key.setAmount(scan);
	}
	
}
class depositMenu extends Screen{
	//This is used to display deposit menu
	public void display(Scanner scan) {
		System.out.println("Deposit menu\n\tEnter the amount to be deposited: \n\nEnter 0 if you want to cancel the process");
		ATM.key.setAmount(scan);
	}
}
class fundMenu extends Screen{
	//This class is called at fund transfer. It displays fund transfer menu
	public void display(Scanner scan) {
		System.out.println("Fund transfer menu\n\tEnter the account number of payee: \n\nEnter 0 if you want to cancel the process");
		ATM.key.setPayeeAccountNumber(scan);
		if(ATM.key.getPayeeAccountNumber()==0){
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		while(ATM.B.checkAccountNumber(ATM.key.getPayeeAccountNumber())==false) {
			System.out.println("Sorry invalid Account Number\nTry again...\nEnter '0' to exit");
			ATM.key.setPayeeAccountNumber(scan);
			if(ATM.key.getPayeeAccountNumber()==0) {
				break;
			}
		}
		if(ATM.key.getPayeeAccountNumber()==0) {
			ATM.main(null);
		}
		System.out.println("Enter the amount to be transferred: \n\nEnter 0 if you want to cancel the process");
		ATM.key.setAmount(scan);
	}
}
class changePinMenu extends Screen{
	//This class is called at the time of pin change
	public void display(Scanner scan) {
		System.out.println("Change PIN menu\n\tEnter the current PIN: \n\nEnter 0 to cancel the process");
		ATM.key.setPin(scan);
		if(ATM.key.getPin()==0) {
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		while(ATM.B.checkPIN(ATM.key.getPin(),ATM.key.getAccountNumber())==false) {
			System.out.println("Sorry invalid PIN\nTry again...\nEnter '0' to exit");
			ATM.key.setPin(scan);
			if(ATM.key.getPin()==0) {
				break;
			}
		}
		if(ATM.key.getPin()==0) {
			ATM.main(null);
		}
		System.out.println("Enter new PIN: \tNew pin should be in the following range: 1000 to 9999. \n\nEnter 0 to cancel the process");
		ATM.key.setNewPin(scan);
	}
}

class Account{
	//This is responsible to collect the inputs given by the user. All kinds of inputs like account number,pin,choices etc,which are given by the user are stored here.
	private long accountNumber;
	private long payeeAccountNumber;
	private int pin;
	private int new_pin;
	private int choice;
	private double amount;
	public void setAccountNumber(Scanner scan) {
		this.accountNumber=scan.nextLong();
	}
	public void setPayeeAccountNumber(Scanner scan) {
		this.payeeAccountNumber=scan.nextLong();
	}
	public void setPin(Scanner scan) {
		this.pin=scan.nextInt();
	}
	public void setNewPin(Scanner scan) {
		this.new_pin=scan.nextInt();
	}
	public long getAccountNumber() {
		return this.accountNumber;
	}
	public long getPayeeAccountNumber() {
		return this.payeeAccountNumber;
	}
	public int getPin() {
		return this.pin;
	}
	public int getNewPin() {
		return this.new_pin;
	}
	public void setChoice(Scanner scan) {
		this.choice=scan.nextInt();
	}
	public void setAmount(Scanner scan) {
		this.amount=scan.nextInt();
	}
	public double getAmount() {
		return this.amount;
	}
	public int getChoice() {
		return this.choice;
	}
	
}
interface transaction{
	//This is an interference
	 public long accountNumber = ATM.key.getAccountNumber();
	 public int pin=ATM.key.getPin();
	 public void execute();
	
}
class Withdrawal implements transaction{
	//This class does the withdrawal work.
	public void execute() {
		if(ATM.B.getAvailableBalance(accountNumber)-ATM.key.getAmount()<0) {
			System.out.println("Funds insufficient\nProcess failed...");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		else {
			ATM.B.setBalance(accountNumber, ATM.B.getAvailableBalance(accountNumber)-ATM.key.getAmount());
			System.out.println("Process completed.\nCollect the money and card\n Generating Slip:");
			System.out.println("Task Performed: Withdrawal from the account "+ATM.key.getAccountNumber());
			ATM.sp.getSlip(accountNumber, 1);
		}
	}
	
}
class Deposit implements transaction{
	//This class does Deposit work.
	public void execute() {
		ATM.B.setBalance(accountNumber, ATM.B.getAvailableBalance(accountNumber)+ATM.key.getAmount());
		System.out.println("Process completed\nCollect Card\nGenerating Slip:");
		System.out.println("Task Performed: Deposit to the account "+ ATM.key.getAccountNumber());
		ATM.sp.getSlip(accountNumber, 2);
	
	}
}
class FundTransfer implements transaction{	
	//This class does fund transfer work.
	public void execute() {
		if(ATM.B.getAvailableBalance(accountNumber)-ATM.key.getAmount()<0) {
			System.out.println("Funds insufficient\nProcess failed...");
			System.out.println("-------------------------------------");
			ATM.main(null);
		}
		else {
			ATM.B.setBalance(accountNumber, ATM.B.getAvailableBalance(accountNumber)-ATM.key.getAmount());
			ATM.B.setBalance(ATM.key.getPayeeAccountNumber(), ATM.B.getAvailableBalance(ATM.key.getPayeeAccountNumber())+ATM.key.getAmount());
			System.out.println("Process completed\nCollect Card\nGenerating Slip:");
			System.out.println("Task Performed: Fund transferred from "+ATM.key.getAccountNumber()+" to "+ATM.key.getPayeeAccountNumber());
			if(accountNumber==ATM.key.getPayeeAccountNumber()) {
				ATM.sp.getSlip(accountNumber, 3);
			}
			else {
				ATM.sp.getSlip(accountNumber, 1);
			}
			
		}
	}
	
}
class ChangePin implements transaction{
	//This performs pin change operation
	public void execute() {
		ATM.B.changePIN(ATM.key.getNewPin(),accountNumber);
		System.out.println("PIN successfully changed");
	}
	
}
class Slip{
	//This class is used to print the slip at the end of each operations,like withdrawal,deposit or fund transfer
	public void getSlip(long a,int n) {
		System.out.println("Name: "+ATM.B.getName(ATM.key.getAccountNumber()));
		if(n==1) {
			System.out.println("Initial Balance: "+(ATM.B.getAvailableBalance(ATM.key.getAccountNumber())+ATM.key.getAmount()));
			System.out.println("Credit: 0");
			System.out.println("Debit: "+ATM.key.getAmount());
		}
		if(n==2) {
			System.out.println("Initial Balance: "+(ATM.B.getAvailableBalance(ATM.key.getAccountNumber())-ATM.key.getAmount()));
			System.out.println("Credit: "+ATM.key.getAmount());
			System.out.println("Debit: 0");
		}
		if(n==3) {
			System.out.println("Initial Balance: "+(ATM.B.getAvailableBalance(ATM.key.getAccountNumber())));
			System.out.println("Credit: "+ATM.key.getAmount());
			System.out.println("Debit: "+ ATM.key.getAmount());
		}
		System.out.println("Final Balance: "+ATM.B.getAvailableBalance(ATM.key.getAccountNumber()));
		System.out.println("-------------------------------------");
	}
}
class BankDatabase{
	//This class is used to keep all the database of the bank. This class has all the information of its customers.
	private String[] Names = {"Bharadwaj","Sridhar Chimalakonda","A Eshaan Rao","Prantik Parashar Sarmah","Vinay K S","Sriram Shanbhag"};
	private long[] AccountNumbers = {900000001,900000002,900000003,900000004,900000005,900000006};
	private int[] PIN= {2002,2003,2004,2005,2006,2007};
	private double[] Balance= {100000,200000,300000,400000,500000,600000};
	public boolean checkAccountNumber(long n) {
		//This method checks whether the account number given by the user is correct or not
		for(int i=0;i<6;i++) {
			if(AccountNumbers[i]==n) {
				return true;
			}
		}
		return false;
	}
	public boolean checkPIN(int n,long a) {
		//This method checks whether the PIN number given by the user is correct or not
		for(int i=0;i<6;i++) {
			if(AccountNumbers[i]==a && PIN[i]==n) {
				return true;
			}
		}
		return false;
	}
	public void changePIN(int n,long a) {
		//This changes the PIN
		for(int i=0;i<6;i++) {
			if(AccountNumbers[i]==a) {
				PIN[i]=n;
			}
		}
	}
	public double getAvailableBalance(long a) {
		//This is used to return the balance of an account
		int i=-1;
		for(i=0;i<6;i++) {
			if(AccountNumbers[i]==a) {
				break;
			}
		}
		return Balance[i];
	}
	public String getName(long a) {
		//This is used to return the name of the account holder
		int i=-1;
		for(i=0;i<6;i++) {
			if(AccountNumbers[i]==a) {
				break;
			}
		}
		return Names[i];
	}
	public void setBalance(long a,double b) {
		//This method is used to update the balance.
		int i=-1;
		for(i=0;i<6;i++) {
			if(AccountNumbers[i]==a) {
				break;
			}
		}
		Balance[i]=b;
	}
		
}
