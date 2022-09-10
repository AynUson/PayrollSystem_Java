import java.util.Scanner;
/*
Register Employee (Fields are up to you)
- system must be able to check if there are duplicates ✔DONE
- check if the applicant is 18 above ✔DONE
- must be assigned with unique ID with the following convention: emp001 ✔DONE

Search Employee
- search by lastname ✔DONE
- search by id ✔DONE
- Display Name✔DONE, Designation✔DONE, City Address✔DONE, Salary Grade✔DONE (search for some common salary grades in PH.)
                       ~Show Designation Choices also





 */



public class Main {
    static int AskUser(){
        Scanner sc = new Scanner(System.in);
        String userChoiceString = sc.nextLine();

        int userInput = 0;
        boolean singleChar = true;
        boolean numeric = true;
        boolean validNumber = false;
        if(userChoiceString.length() > 1){
            singleChar = false;
        }
        try {
            userInput = Integer.parseInt(userChoiceString);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        if(userInput > 0 && userInput <= 8)
            validNumber = true;
        if (numeric && singleChar && validNumber){
            return userInput;
        }else {
            System.out.println("Invalid input. Ending Transaction.");
            return 0;
        }
    }

    static int payrollFunction(PayrollSystem p, int choice){
        if (choice == 1)
            p.registerEmployee();
        if (choice == 2)
            p.searchEmployee();
        if (choice == 8)
            p.showEmployes();

        System.out.println("Proceed to Main Menu[6] / Exit[7] ");
//			Asks user if user wants to continue
        System.out.println("________________________________________");
        return AskUser();
    }

    public static void main(String[] args){

        PayrollSystem payrollSystem = new PayrollSystem();
        payrollSystem.setSalaryGrade();
//        System.out.println(payrollSystem.getSalaryGrade());
        System.out.println("_*_*_*_Payroll System_*_*_*_ | Registered Employees: "+payrollSystem.numberOfEmployees+"\n----------------------------\n\t[1] Register \n\t[2] Search " +
                "\n\t[3]  \n\t[4] \n\t[5]  \n\t[7] \n\t[8] Show Employees ");
        int userInput = AskUser();
        while(userInput != 7) {
            System.out.println("----------------------------");
            switch (userInput){
                case 1:
                    userInput = payrollFunction(payrollSystem,userInput);
                    break;
                case 2:
                    userInput = payrollFunction(payrollSystem,userInput);
                    break;
                case 6:

                    System.out.println("_*_*_*_Payroll System_*_*_*_ | Registered Employees: "+payrollSystem.numberOfEmployees+"\n----------------------------\n\t[1] Register \n\t[2] Search " +
                            "\n\t[3]  \n\t[4] \n\t[5]  \n\t[7] \n\t[8] Show Employees ");
                    userInput = AskUser();
                    break;
                case 7:
                    System.out.println("Exit");
                    userInput = 7;
                    break;
                case 8:
                    userInput = payrollFunction(payrollSystem,userInput);
                    break;
                default:
                    userInput = 7;
                    break;

            }
        }


    }
}
