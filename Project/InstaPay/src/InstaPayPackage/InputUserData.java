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
        System.out.println("Enter Your Option (1-5):>");
        input = scanner.nextLine();
        return input;
    }
}
