import javax.swing.text.View;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class UserActions {

    // hehe xd

    public void run(User user){
        int input = 0;
        while (true){
            Scanner scan = new Scanner(System.in);
            System.out.println("--- User Menu --- \n");
            System.out.println("(1) View items and wishlist \n (2) View user stats \n (3) Request an unfreeze \n" +
                    "(4) View other users \n (5) View your pending trades \n (6) View active temporary trades");
            input = scan.nextInt();
            if (input > 6 || input < 1){
                System.out.println("Please enter a number from 1 to 6");
            } else break;
        }
        if (input == 1){
            ViewItemAndWishlist(user);
        } else if (input == 2) {
            // KING ALEX
        } else if (input == 3) {
            //KING TINGYU
        } else if (input == 4) {
            // KING MURRAY
        } else if (input == 5) {
            // KING LOUKOS
        } else if (input == 6) {
            // KING TINGYU
        }


    }

    public void ViewItemAndWishlist(User user){
        boolean flag = true;
        int input = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Your available items:");
        for (Item item : user.getAvailableItems()){
            System.out.println(item);
        }
        System.out.println("\n Your wishlist :");
        for (Item item : user.getWishlistItems()){
            System.out.println(item);
        }

        while (flag){
            System.out.println("(1) Send Item Validation requestion \n (2) Remove an item from available items " +
                    "\n (3) Remove an item from your wishlist");
            input = scan.nextInt();
            if (input < 1 || input > 3){
                System.out.println("Please enter a number from 1 to 3");
            } else flag = false;

         if (input == 1){
             System.out.println("Please the name of your item");
             String name = scan.nextLine();
             System.out.println("Please the item description");
             String description = scan.next();
             System.out.println("Please your username");
             String username = scan.nextLine();
             TradeSystem.adminUser.userManager.sendValidationRequest(name,description,username);
         } else if (input == 2){
             System.out.println("Please the ID of the item you wish to remove from available items");
             int itemID = scan.nextInt();
             for (Item item: user.getAvailableItems()){
                 if (item.getId() == itemID) {
                     user.getAvailableItems().remove(item);
                 }
             }

         } else {
             System.out.println("Please the ID of the item you wish to remove from your wishlist");
             int itemID = scan.nextInt();
             for (Item item: user.getWishlistItems()){
                 if (item.getId() == itemID) {
                     user.getWishlistItems().remove(item);
                 }
             }
         }

        }





    }

}
