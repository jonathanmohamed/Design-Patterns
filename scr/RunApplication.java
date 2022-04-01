import Exceptions.ItemDoesNotExistException;
import payment.CashRegister;
import payment.Payment;
import payment.Ideal;
import store.FurnitureStore;
import store.order.Order;
import utility.FurnitureCatalogue;

import java.util.Scanner;

/**
 * This is the frontend of our application
 */
public class RunApplication {
    //declare variables needed
    private FurnitureStore store;

    //Scanner and commands
    private Scanner userInput;
    private int startCommand;
    private String orderCommand;
    private String customerName;
    private String paymentOption;
    private String furnitureName;
    private String orderItemName;
    private String finish;

    /**
     * Initialise all variables in a new instance of our RunApplication class
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    public RunApplication() throws ItemDoesNotExistException, InterruptedException {
        userInput = new Scanner(System.in);
        this.welcomeMessage();
        this.startCommand = 0;
        this.orderCommand = "";
        this.customerName = "";
        this.paymentOption = "";
        this.furnitureName = "";
        this.orderItemName = "";
        this.finish = "";
    }

    /**
     * Initial step when application starts
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void welcomeMessage() throws ItemDoesNotExistException, InterruptedException {
        System.out.println("Welcome to our Furniture store.");
        System.out.print("Press 1 to start and create a store or 0 to exit: ");
        startCommand = userInput.nextInt();
        store(startCommand);
    }

    public void setUserInput(Scanner userInput) {
        this.userInput = userInput;
    }

    /**
     * Used for creating store based on user input
     * @param startCommand
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void store(int startCommand) throws ItemDoesNotExistException, InterruptedException {
        switch (startCommand) {
            case 1:
                this.store = new FurnitureStore();
                System.out.println("store successfully created.");
                this.createOrder();
                break;
            case 0:
                userInput.close();
                System.out.println("program exited.");
                break;
            default:

        }
    }

    /**
     * Creates an order
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void createOrder() throws ItemDoesNotExistException, InterruptedException {
        System.out.print("would you want to create an order? (y/n) ");
        setUserInput(new Scanner(System.in));//set a new input inside the scanner
        this.orderCommand = userInput.nextLine();
        switch (this.orderCommand.toLowerCase()) {
            case "y" -> {
                System.out.print("Enter the name of the person placing the order: ");
                this.customerName = userInput.nextLine();//get customerName
                this.store.createOrder(customerName);//save order name
                System.out.println("Order created for " + customerName);
                this.createFurniture();
            }
            case "n" -> {
                userInput.close();
                System.out.println("exited");
            }
        }
    }

    /**
     * Create furniture from menu based on user input
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void createFurniture() throws ItemDoesNotExistException, InterruptedException {
        System.out.print("Select from our available furniture: \nstool. \ntable. \nchair.\nstandard furniture.\n");
        do {
            setUserInput(new Scanner(System.in));//set a new input inside the scanner
            this.furnitureName = userInput.nextLine();
            if (!FurnitureCatalogue.availableFurniture().contains(furnitureName)) {//check to not print again it when statement is satisfied
                System.out.print("Enter furniture name again not part of list: ");
            }
        } while (!FurnitureCatalogue.availableFurniture().contains(furnitureName));

        System.out.print("Enter item name for " + this.furnitureName + " ");
        setUserInput(new Scanner(System.in));
        this.orderItemName = userInput.nextLine();
        this.store.findOrder(this.customerName).addFurniture(this.furnitureName, this.orderItemName);
        this.finishFurniture(this.orderItemName);
    }

    /**
     * Finishes furniture with the corresponding item name
     * @param itemName
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void finishFurniture(String itemName) throws ItemDoesNotExistException, InterruptedException {
        System.out.print("Select from our available finishes: \nwax. \nwater. \nshellac.\nvanish.\n");
        do {
            setUserInput(new Scanner(System.in));//set a new input inside the scanner
            this.finish = userInput.nextLine();//get toppings
            if (!FurnitureCatalogue.availableFurnitureFinish().contains(finish))
                System.out.print("Enter finish name again not part of list: ");
        } while (!FurnitureCatalogue.availableFurnitureFinish().contains(finish));

        this.store.findOrder(this.customerName).addDecorationToFurniture(itemName, this.finish);
        this.prepareOrder(this.store.findOrder(this.customerName));
    }

    /**
     * Prepares order and accepts payment
     * @param order
     * @throws ItemDoesNotExistException
     * @throws InterruptedException
     */
    private void prepareOrder(Order order) throws ItemDoesNotExistException, InterruptedException {
        System.out.print("Enter 1 to add another furniture to order or 2 to checkout current order? ");
        setUserInput(new Scanner(System.in));
        this.orderCommand = userInput.nextLine();
        switch (this.orderCommand.toLowerCase()) {
            case "1" -> {
                this.createOrder();
            }
            case "2" -> {
                double bill = order.calculateBill();
                System.out.print("Your bill is " + bill + ", select a payment method. \nEnter 1 for cash register and 2 for ideal ");
                setUserInput(new Scanner(System.in));//set a new input inside the scanner
                paymentOption = userInput.nextLine();//get payment option selected
                switch (paymentOption.toLowerCase()) {
                    case "1" -> {
                        System.out.print("Enter your transactionHash (INT) ");
                        setUserInput(new Scanner(System.in));
                        int transactionHash = userInput.nextInt();
                        Payment payment = new CashRegister(customerName, transactionHash);
                        this.store.findOrder(customerName).payment(payment);
                        this.orderPrep(this.store.findOrder(customerName));
                    }
                    case "2" -> {
                        System.out.print("Enter your IBAN ");
                        setUserInput(new Scanner(System.in));
                        String iban = userInput.nextLine();
                        Payment payment = new Ideal(customerName, iban);
                        this.store.findOrder(customerName).payment(payment);
                        this.orderPrep(this.store.findOrder(customerName));
                    }
                }
            }
        }
    }

    /**
     * prepares order entered as a parameter
     * @param order
     * @throws InterruptedException
     * @throws ItemDoesNotExistException
     */
    private void orderPrep(Order order) throws InterruptedException, ItemDoesNotExistException {
        if (order.isPaid()) { //If order is paid then start
            double time = order.getOrderEstimatedPreparationTime();
            System.out.print("relax while we start your order. \n" +
                    "Your order will be ready in " + time + " seconds");
            order.prepareOrder();
            System.out.println("\nYour Order is done and on its way! Thank you good-bye :)");
            System.out.print("Enter 1 to create another order or 2 to end ");
            setUserInput(new Scanner(System.in));
            String choice = userInput.nextLine();
            switch (choice.toLowerCase()) {
                case "1" -> {
                    this.createOrder();
                }
                case "2" -> {
                    userInput.close();
                }
            }
        }
    }
}
