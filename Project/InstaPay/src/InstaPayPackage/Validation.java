package InstaPayPackage;

import java.util.regex.Pattern;

public class Validation {
  public static boolean mobilePhoneIsCorrect(String mobileP) {
    Pattern filter = Pattern.compile("(01)+(0|1|2|5){1}[0-9]{8}$");
    return filter.matcher(mobileP).matches();
  }

  public static boolean passwordIsStrong(String password) {
    Pattern filter = Pattern.compile(
            "^(?:[#-&@*$]{2,15}[a-zA-Z0-9]{5,20}|[a-zA-Z0-9]{5,20}[#-&@$]{2,15}|[#-&@*$]{2,15}[a-zA-Z0-9]{5,20}[#-&@*$]{2,15}|[a-zA-Z0-9]{5,20}[#-&@*$]{2,15}[a-zA-Z0-9]{5,20})$");
    return filter.matcher(password).matches();
  }
}
