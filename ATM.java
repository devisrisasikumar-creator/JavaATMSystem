import java.util.Scanner;

public class ATM{

    private Account currentAccount;

public ATM(){
}

    public void start(){

        Scanner sc=new Scanner(System.in);

        System.out.print("Enter Account Number: ");
int accountNumber = sc.nextInt();

System.out.print("Enter PIN: ");
int enteredPin = sc.nextInt();

currentAccount = DatabaseManager.login(accountNumber, enteredPin);

if(currentAccount == null){
    System.out.println("Invalid Account Number or PIN");
    return;
}

        int choice;

        do{

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1.Check Balance");
            System.out.println("2.Deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Transaction History");
            System.out.println("5.Exit");

            choice=sc.nextInt();

            switch(choice){

                case 1:
                    System.out.println("Balance: Rs."+currentAccount.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    currentAccount.deposit(sc.nextDouble());
DatabaseManager.updateBalance(currentAccount);
                    break;

                case 3:
                    if(currentAccount.withdraw(sc.nextDouble())){
    DatabaseManager.updateBalance(currentAccount);
    System.out.println("Withdrawal Successful");
}
else{
    System.out.println("Insufficient Balance");
}
break;
                case 4:
                    System.out.println("\nTransaction History");

                    if(currentAccount.getHistory().isEmpty()){
                        System.out.println("No Transactions Yet");
                    }else{
                        for(String transaction : currentAccount.getHistory()){
                            System.out.println(transaction);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Thank You");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        }while(choice!=5);
    }
}