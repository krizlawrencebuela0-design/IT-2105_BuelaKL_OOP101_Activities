import java.util.Scanner;
 
public class Canteen {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
 
        // Menu data
        String[] itemNames = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] itemPrices = {80.00, 120.00, 100.00, 70.00, 90.00};
 
        // Running totals for the whole transaction
        int totalItemsPurchased = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;
        double finalAmountToPay = 0.0;
 
        // Display the menu once at the start
        System.out.println("===== MENU =====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }
 
        char orderAgain = 'Y';
 
        while (orderAgain == 'Y' || orderAgain == 'y') {
            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = input.nextInt();
 
            System.out.print("Enter quantity: ");
            int quantity = input.nextInt();
 
            System.out.print("Are you a student? (Y/N): ");
            char studentAnswer = input.next().charAt(0);
            boolean isStudent = (studentAnswer == 'Y' || studentAnswer == 'y');
 
            // Validate item number and quantity
            boolean validItem = (itemNumber >= 1 && itemNumber <= itemNames.length);
            boolean validQuantity = (quantity >= 1 && quantity <= 10);
 
            if (!validItem || !validQuantity) {
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");
                // Skip the rest of the processing for this order
            } else {
                double price = itemPrices[itemNumber - 1];
                double subtotal = price * quantity;
 
                // Determine discount rate based on the rules
                double discountRate;
                if (isStudent && subtotal >= 500) {
                    discountRate = 0.15;
                } else if (isStudent) {
                    discountRate = 0.10;
                } else if (subtotal >= 500) {
                    discountRate = 0.05;
                } else {
                    discountRate = 0.0;
                }
 
                double discount = subtotal * discountRate;
                double orderTotal = subtotal - discount;
 
                System.out.println();
                System.out.printf("Subtotal: $%.2f%n", subtotal);
                System.out.printf("Discount: $%.2f%n", discount);
                System.out.printf("Order total: $%.2f%n", orderTotal);
 
                // Update running totals
                totalItemsPurchased += quantity;
                totalBeforeDiscount += subtotal;
                totalDiscount += discount;
                finalAmountToPay += orderTotal;
            }
 
            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            orderAgain = input.next().charAt(0);
        }
 
        // Final order summary
        System.out.println();
        System.out.println("===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalItemsPurchased);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmountToPay);
        System.out.println("Thank you for ordering!");
 
        input.close();
    }
}
