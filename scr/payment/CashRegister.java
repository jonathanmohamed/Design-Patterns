package payment;

import java.util.Scanner;

public class CashRegister implements Payment {
    private String firstName;
    private int transactionHash;

    public CashRegister(String firstName, int transactionHash) {
        this.firstName = firstName;
        this.transactionHash = transactionHash;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTransactionHash() {
        return this.transactionHash;
    }

    public void setTransactionHash(int transactionHash) {
        this.transactionHash = transactionHash;
    }

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        int amountToConfirm;
        System.out.print("Enter amount to confirm payment (DOUBLE): ");
        //keep repeating until bank card is correct
        do {
            amountToConfirm = scanner.nextInt();
            if (amountToConfirm != getTransactionHash())
                System.out.print("Amount incorrect enter again: ");

        } while (amountToConfirm != getTransactionHash());
        System.out.println("payment successful");
        System.out.println(amount + " paid at the Register by: " + getFirstName());
    }
}
