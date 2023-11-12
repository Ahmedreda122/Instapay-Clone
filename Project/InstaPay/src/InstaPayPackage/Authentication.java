package InstaPayPackage;

public class Authentication {
        public  Authentication(){}
//    create obj from class model --> will be private object

        private Boolean mobilePhoneIsExist(String mobileP){

            return true;
        }
        private Boolean usernameIsExist(String username){
            //        call method in class model
            return true;
        }

        //    parameter ---> InstapayAccount Account in register ==> (username + password +
//    Account(wallet | BankAccount))
        public Boolean login(Object Account){
//       call method in class model
            return true;
        }
        //    parameter ---> InstapayAccount Account in register ==> (username + password +
//    Account(wallet | BankAccount))
        public Boolean register(Object Account){
//       call method in class model ---> insert Instapay Account
            return true;
        }
}


