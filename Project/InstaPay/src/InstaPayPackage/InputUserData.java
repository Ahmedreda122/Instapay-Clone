package InstaPayPackage;
import java.util.Scanner;
public class InputUserData {
    public  Scanner scanner = new Scanner(System.in);
    String input;
    public  String enterUserName(){
        System.out.println("Enter your username : ");
        input = scanner.nextLine();
        return input;
    }
    public String enterPassword(){
        System.out.println("Enter your password : ");
        input = scanner.nextLine();
        return input;
    }
    public String enterMobilePhone(){
        System.out.println("Enter your Mobile Phone : ");
        input = scanner.nextLine();
        return input;
    }

    public String enterYourOption(){
        System.out.println("Enter Your Option (1-6):>");
        input = scanner.nextLine();
        return input;
    }
    public String enterYourOptionInMain(){
        System.out.println("Enter Your Option (1-2):>");
        input = scanner.nextLine();
        return input;
    }
    public String enterMoney(){
        System.out.println("Enter The Amount you want to Transfer: ");
        input = scanner.nextLine();
        return input;
    }

    public String completeBill(String billType, double cost) {
        System.out.println("This the "+ billType + "Cost: "+cost);
        System.out.println("If you want to complete Paying "+billType+ ", Enter 1");
        input = scanner.nextLine();
        return input;
    }
}
