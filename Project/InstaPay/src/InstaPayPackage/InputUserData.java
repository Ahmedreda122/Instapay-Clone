package InstaPayPackage;
import java.util.Scanner;
public class InputUserData {
    Scanner scanner = new Scanner(System.in);
    String input;
    public String enterUserName(){
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
        System.out.println("Enter your username : ");
        input = scanner.nextLine();
        return input;
    }
}
