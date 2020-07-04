import java.util.Scanner;

public class AdminActions {

    public void run(AdminUser admin) {
        mainMenu(admin);
    }

    public void mainMenu(AdminUser admin) {

        boolean running = true;
        while (running) {
            int input = -1;
            Scanner scan = new Scanner(System.in);
            System.out.println("--- Admin Menu --- \n");
            System.out.println("(1) Set frozen threshold \n (0) Quit");
            boolean valid_input = false;
            while (!valid_input) {
                input = scan.nextInt();
                if (input > 1 || input < 0) {
                    System.out.println("Please enter a number from 0 to 1");
                } else if (input == 1) {
                    setThreshold(admin);
                    valid_input = true;
                } else if (input == 0) {
                    valid_input = true;
                    running = false;
                }
            }
        }
    }

    public void setThreshold(AdminUser admin) {
        boolean flag = true;
        int input = 0;
        while (flag) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter the new threshold value for lent vs borrowed: ");
            input = scan.nextInt();
            if (input > 50 || input < 0) {
                System.out.println("Please enter a valid threshold number");
            }
            else {
                UserManager.setBorrowLendThreshold(input);
                flag = false;
            }
        }
    }
}
