import java.util.Arrays;
import java.util.Scanner;

public class BankingApplication{
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "💰Welcome to Smart Banking App";
        final String OPEN_ACCOUNT = "Open New Account";
        final String DEPOSIT_MONEY = "Deposit Money";
        final String WITHDRAW_MONEY = "Withdraw Money";
        final String TRANSFER_MONEY = "Transfer Money";
        final String CHECK_BALANCE = "Check Account Balance";
        final String DROP_ACCOUNT = "Drop Existing Account";

        String[][] bankAccount = new String[0][];

        String screen = DASHBOARD;

        do{
            final String APP_TITLE = String.format("%s%s%s",COLOR_BLUE_BOLD,screen,RESET);

            System.out.println(CLEAR);
            System.out.println("\t"+ APP_TITLE + "\n");

            switch(screen){
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit");
                    System.out.println("\tEnter an option to continue");
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch(option){
                        case 1: screen = OPEN_ACCOUNT; break;
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANSFER_MONEY; break;
                        case 5: screen = CHECK_BALANCE; break;
                        case 6: screen = DROP_ACCOUNT; break;
                        case 7: System.out.println(CLEAR); System.exit(0);
                        default: continue;
                    }
                    break;

                case OPEN_ACCOUNT:
                    String accountNumber;
                    String name;
                    double diposit;
                    boolean valid;

                    accountNumber = String.format("SDB-%05d",(bankAccount.length + 1));

                    System.out.printf("Account ID: %s \n", (accountNumber));

                    do{
                        valid = true;
                        System.out.println("\tEnter Account Holder's name :");
                        name = scanner.nextLine().strip();
                    
                    
                        if(name.isBlank()){
                            System.out.printf("\t%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            valid = false;
                            continue;
                        }
                            for (int i = 0; i < name.length(); i++) {
                                if (!(Character.isLetter(name.charAt(i)) || Character.isSpaceChar(name.charAt(i))) ) {
                                    System.out.printf("%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                    valid = false;
                                    break;
                                }
                            }   


                    }while(!valid);

                    do{
                        valid = true;
                        System.out.print("Enter the initial diposit amount (Rs.) :");
                        diposit = scanner.nextDouble();
                        scanner.nextLine();

                        if( diposit < 5000.00){
                            System.out.printf("%sInsufficiant Amount%s\n", COLOR_RED_BOLD, RESET);
                            valid=false;
                        
                        }
                        

                    }while(!valid);
                    

                    String[][] newBankAccount= new String[bankAccount.length+1][3];
                    

                    for(int i=0; i<bankAccount.length;i++){
                        newBankAccount[i] = bankAccount[i];

                    }

                    newBankAccount[newBankAccount.length-1][0] = accountNumber;
                    newBankAccount[newBankAccount.length-1][1] = name;
                    newBankAccount[newBankAccount.length-1][2] = diposit +"";

                    bankAccount = newBankAccount;
                
                    System.out.println();

                    //System.out.println(Arrays.toString(accounts));
                    System.out.println("Account has been created successfully.");
                    System.out.println("Do you want to continue adding (Y/n)? ");
                    if(scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;


                case DEPOSIT_MONEY:
                    
                    int index = 0;
                    
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number :");
                        accountNumber = scanner.nextLine().toUpperCase().strip();

                        if(accountNumber.isBlank()){
                            valid = false; 
                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break;
                            

                        }else if(!(accountNumber.startsWith("SDB-") || accountNumber.length()!=9)){
                            valid = false;
                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);   
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break;  
                        }else{
                            // String number = accountNumber.substring(5);
                            // for(int i=0;i < number.length();i++){
                            //     if(!Character.isDigit(number.charAt(i))){
                            //         valid = false;
                            //         System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            //         if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            //         screen = DASHBOARD;
                            //         break;  
                            //     }
                            // }
                        }

                            boolean exist = false;
                            double currentBalance = 0;
                            for(int i=0;i<bankAccount.length;i++){
                            
                                if(bankAccount[i][0].equals(accountNumber)){
                                    index = i;
                                    exist = true;
                                    System.out.println("founf");

                                    System.out.println("Account Holder Name :"+bankAccount[i][1]);
                                    System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(bankAccount[i][2]));

                            Deposit:
                            do{
                                    valid=true;
                                    System.out.println("Deposit Amount : Rs. ");
                                    double amount = scanner.nextDouble();
                                    scanner.nextLine();

                                    

                                    if(amount<500){
                                        System.out.println("Insufficient Amount. Minimum amount is Rs.500. Do you want to try again (y/n)");
                                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue Deposit;
                                        valid=false;
                                        screen = DASHBOARD;
                                        break; 
                                    

                                    }else{
                                        for(int j=0;j<bankAccount.length;j++){
                                            if(bankAccount[i]==bankAccount[j]){

                                                currentBalance = Double.valueOf(bankAccount[j][2])+ amount;
                                                bankAccount[i][2] = currentBalance +"";

                                            }

                                        }
                                    }
                                         System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(bankAccount[i][2]));
                                         System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);   
                                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                        screen = DASHBOARD;
                                        break;  
                                    

                            }while(!valid);
                                
                                }
                                
                                        if(!exist){
                                            valid = false;
                                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                                if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                                screen = DASHBOARD;
                                                break; 
                                        }                   
                                }

                    }while(!valid);



                    case WITHDRAW_MONEY:
                    
                        index = 0;
                    
                    
                    do{
                        valid = true;
                        System.out.print("\tEnter Account Number :");
                        accountNumber = scanner.nextLine().toUpperCase().strip();

                        if(accountNumber.isBlank()){
                            valid = false; 
                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break;
                            

                        }else if(!(accountNumber.startsWith("SDB-") || accountNumber.length()!=9)){
                            valid = false;
                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);   
                            if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            screen = DASHBOARD;
                            break;  
                        }else{
                            // String number = accountNumber.substring(5);
                            // for(int i=0;i < number.length();i++){
                            //     if(!Character.isDigit(number.charAt(i))){
                            //         valid = false;
                            //         System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                            //         if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                            //         screen = DASHBOARD;
                            //         break;  
                            //     }
                            // }
                        }

                            boolean exist = false;
                            double currentBalance = 0;
                            for(int i=0;i<bankAccount.length;i++){
                            
                                if(bankAccount[i][0].equals(accountNumber)){
                                    index = i;
                                    exist = true;
                                    System.out.println("founf");

                                    System.out.println("Account Holder Name :"+bankAccount[i][1]);
                                    System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(bankAccount[i][2]));

                            Withdraw:
                            do{
                                    valid=true;
                                    System.out.println("Withdrawal Amount : Rs. ");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();

                                    

                                    if(withdrawAmount<100){
                                        System.out.println("Insufficient Amount. Minimum amount is Rs.100. Do you want to try again (y/n)");
                                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue Withdraw;
                                        valid=false;
                                        screen = DASHBOARD;
                                        break; 
                                    

                                    }else{
                                        for(int j=0;j<bankAccount.length;j++){
                                            if(bankAccount[i]==bankAccount[j]){

                                                if(withdrawAmount < Double.valueOf(bankAccount[i][2]))

                                                currentBalance = Double.valueOf(bankAccount[j][2])-withdrawAmount;
                                                bankAccount[i][2] = currentBalance +"";

                                            }

                                        }
                                    }
                                         System.out.printf("Current Balance is : Rs%.2f\n",Float.valueOf(bankAccount[i][2]));
                                         System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);   
                                        if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                        screen = DASHBOARD;
                                        break;  
                                    

                            }while(!valid);
                                
                                }
                                
                                        if(!exist){
                                            valid = false;
                                            System.out.printf("\t%sDo you want to try again ? (Y/N)%s\n", COLOR_RED_BOLD, RESET);
                                                if (scanner.nextLine().strip().toUpperCase().equals("Y")) continue;
                                                screen = DASHBOARD;
                                                break; 
                                        }                   
                                }

                    }while(!valid);








                    


            
                }

                

        }while(true);



        
    }
}