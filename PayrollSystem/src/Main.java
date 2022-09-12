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

Salary Deduction
- Search for the employees id then enter amount to deduct✔DONE
- Reflect on show employees and payslip

Payslip Display
- Check if dates are valid (dates must not be ahead on the current date, starting date must not be ahead on the ending date)✔DONE
- Count only working days (weekdays)✔DONE
- Issue a payslip depending on the date range provided.
- Provide a substantial data containing common fields in a payslip like tax, night diff,
    ot, allowances, deduction, etc. formula is up to you.

Allowance
- search for the employee's id then ask for the amount we want to add as compensation✔DONE
- must reflect to payslip
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
//        Convert this to Switch
        if (choice == 1)
            p.registerEmployee();
        if (choice == 2)
            p.searchEmployee();
        if (choice == 3)
            p.deductSalary();
        if (choice == 4)
            p.addAllowance();
        if (choice == 5)
            p.printPayslip();
        if (choice == 6){
            System.out.println("_*_*_*_Payroll System_*_*_*_ | Registered Employees: "+p.numberOfEmployees+"\n----------------------------\n\t[1] Register \n\t[2] Search " +
                    "\n\t[3] Salary Deduction  \n\t[4] Allowance \n\t[5] Payslip***  \n\t[7] Exit \n\t[8] Show Employees ");
        }
        if (choice == 8)
            p.showEmployes();

        if (choice != 6)
            System.out.println("Proceed to Main Menu[6] / Exit[7] ");
//			Asks user if user wants to continue
        System.out.println("________________________________________");
        return AskUser();
    }

    public static void main(String[] args){

        PayrollSystem payrollSystem = new PayrollSystem();
        payrollSystem.setSalaryGrade();
        int userInput = 6;
        while(userInput != 7) {
            System.out.println("----------------------------");
            userInput = payrollFunction(payrollSystem,userInput);
        }
        payrollSystem.sayThankYou();
    }
}
