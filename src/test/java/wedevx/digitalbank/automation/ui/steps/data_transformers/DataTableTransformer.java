package wedevx.digitalbank.automation.ui.steps.data_transformers;

import io.cucumber.java.DataTableType;
import wedevx.digitalbank.automation.ui.models.*;

import java.util.Map;

public class DataTableTransformer {

        @DataTableType
        public AccountCard accountCardEntry(Map<String, String> entry){
            String accountName = entry.get("accountName");
            String accountType = entry.get("accountType");
            String ownership = entry.get("ownership");
            long accountNumber = Long.parseLong(entry.get("accountNumber"));
            String interestRate = entry.get("interestRate");
            double balance = Double.parseDouble(entry.get("balance"));

            return new AccountCard(accountName, accountType, ownership, (long) accountNumber, interestRate, balance);

        }

        @DataTableType
        public BankTransaction transactionEntry(Map<String, String> entry){
            String date = entry.get("date");
            String category = entry.get("category");
            String description = entry.get("description");
            double amount = Double.parseDouble(entry.get("amount"));
            double balance = Double.parseDouble(entry.get("balance"));

            return new BankTransaction(date, category, description, amount, balance);

        }

        @DataTableType
        public NewCheckingAccountInfo newCheckingAccountInfoEntry(Map<String, String> entry){
            String checkingAccountType = entry.get("checkingAccountType");
            String ownership = entry.get("accountOwnership");
            String accountName = entry.get("accountName");
            double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));


            return new NewCheckingAccountInfo(checkingAccountType, ownership,  accountName, initialDepositAmount);

        }
//        @DataTableType
//    public RegistrationPageInfo registrationPageEntry(Map<String, String> entry){
//            String title = entry.get("title");
//            String firstName = entry.get("firstName");
//            String lastName = entry.get("lastName");
//            String gender = entry.get("gender");
//            String dateOfBirth = entry.get("dateOfBirth");
//            String ssn = entry.get("ssn");
//            String emailAddress = entry.get("emailAddress");
//            String password = entry.get("password");
//            String confirmPassword = entry.get("confirmPassword");
//            String address = entry.get("address");
//            String locality = entry.get("locality");
//            String region = entry.get("region");
//            String postalCode= entry.get("postalCode");
//            String country = entry.get("country");
//            String homePhone = entry.get("homePhone");
//            String mobilePhone = entry.get("mobilePhone");
//            String workPhone = entry.get("workPhone");
//
//            return new RegistrationPageInfo(title, firstName, lastName, gender, dateOfBirth,ssn, emailAddress, password, confirmPassword, address, locality, region, postalCode, country, homePhone, mobilePhone, workPhone);
//        }

        @DataTableType
    public NewSavingAccountInfo savingAccountEntry(Map<String, String> entry){
            String savingsAccountType =entry.get("savingsAccountType");
            String accountOwnership =entry.get("accountOwnership");
            String accountName =entry.get("accountName");
            double initialDepositAmount  =Double.parseDouble(entry.get("initialDepositAmount"));

            return new NewSavingAccountInfo(savingsAccountType, accountOwnership, accountName, initialDepositAmount);
        }

        @DataTableType
    public LoginInfo loginInfoEntry(Map<String, String> entry){
            String username = entry.get("username");
            String password  = entry.get("password");

            return new LoginInfo(username, password);
        }

    }

