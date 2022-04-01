package payment;

import java.util.Scanner;

public class Ideal implements Payment {
    private String name;
    private String IBAN;

    public Ideal(String name, String IBAN) {
        this.name = name;
        this.IBAN = IBAN;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);
        String IBANToConfirm = "";
        System.out.print("Enter IBAN to confirm payment: ");
        //keep repeating until email is correct
        do {
            IBANToConfirm = scanner.nextLine();
            if (!(IBANToConfirm.equals(getIBAN())))
                System.out.print("IBAN incorrect enter again: ");

        } while (!(IBANToConfirm.equals(getIBAN())));

        System.out.println("payment successful");
        System.out.println("paid " + amount + " with Ideal");
    }

}
