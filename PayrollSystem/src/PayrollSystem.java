import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

import java.text.SimpleDateFormat;
class PayrollSystem extends PayrollFunctionalities{
    public ArrayList< Employee> employees = new ArrayList<>();


    Scanner sc = new Scanner(System.in);

    //  Payroll Functionalities Class
    @Override
    void registerEmployee(){
        boolean duplicate = false;
        boolean isLegalAge = true;
        int counter = 1;
        String errMsg = "Error: ";
        System.out.println("Enter First Name:");
        String fname = sc.nextLine();
        System.out.println("Enter Middle Name:");
        String mname = sc.nextLine();
        System.out.println("Enter Last Name:");
        String lname = sc.nextLine();
        System.out.println("Enter Designation:");
        for (String a : designations) {
            counter++;
            System.out.print(String.format("[%s] ",a ));
            if (counter == 5)
                System.out.println();
        }
        System.out.println(":");
        String designation = sc.nextLine();
        counter = 1;
        System.out.println("Enter Address:");
        String address = sc.nextLine();
        System.out.println("Salary Grade[1-20]:");
        for (Map.Entry me : salaryGrades.entrySet()) {
            counter++;
            System.out.print(String.format("[%d - %f] ",me.getKey(),me.getValue()) );
            if (counter == 10)
                System.out.println();
        }
        System.out.println(":");
        int salaryGrade = sc.nextInt();
        sc.nextLine();
        LocalDate dob = inputDob();
        int age = calculateAge(dob);
        if (age < 18){
            errMsg+="\nAge below 18.";
            isLegalAge = false;
        }


        String empID;
        if (employees.size() == 0){ //if employees size is zero
            employees.add(new Employee(fname,mname,lname, generateEmpID(),dob,designation,address,salaryGrade, age));

            System.out.println("Added Succesfully!");
            return;
        }
        Employee emp;
        for (Employee e: // if employees have elements
                employees) {

            if (e.getFirstName().equals(fname) && e.getMiddleName().equals(mname) && e.getLastName().equals(lname)){
                errMsg+="\nDuplicate Records.";
                duplicate = true;

            }

        }

        if (!duplicate && isLegalAge){
            employees.add(new Employee(fname,mname,lname, generateEmpID(),dob,designation,address,salaryGrade, age));
            System.out.println("Added Succesfully!");

        }else{
            System.out.println(errMsg);
        }

        System.out.println("______________________________________________");
    }

    @Override
    void searchEmployee() {
//        FIX THIS!! Searching empcode is fine, Searching Lastname is shit!

        System.out.println("Search for Last name or ID:  ");
        String input = sc.nextLine().toLowerCase();
        String result= "";
        String empid = "";
        String lname = "";
        boolean found = false;
        for (Employee e: // if employees have elements
                employees) {
//            e.getLastName().toLowerCase();
//            e.getEmpID().toLowerCase();
//            input.toLowerCase();
            empid = e.getEmpID().toLowerCase();
            lname = e.getLastName().toLowerCase();

            if (lname.equals(input) || empid.equals(input)){
                result = String.format("\t\t**RESULT**\nID:\t\t\t\t %s \nFull name:\t\t %s %s %s \nAge:\t\t\t %s \nDesignation:\t %s " +
                        "\nAddress:\t\t %s \nSalary Grade:\t %d- $%f ",e.getEmpID(),e.getFirstName(),e.getMiddleName(),e.getLastName(),e.getAge()
                        ,e.getDesignation(), e.getCityAddress(),e.getSalaryGrade(), getSalaryGrade().get(e.getSalaryGrade()));
                found = true;
                break;
            }



        }
        if (!found)
            result = "No Matches!";
//        if (result.equals(""))
//            result = "No Matches";
        System.out.println(result);
    }



    @Override
    String generateEmpID() {
        numberOfEmployees++;
        if (numberOfEmployees < 10)
            return String.format("emp%03d",numberOfEmployees);
        else if (numberOfEmployees >= 10 && numberOfEmployees < 100)
            return String.format("emp%02d",numberOfEmployees);
        else
            return String.format("emp%d",numberOfEmployees);
    }

    @Override
    LocalDate inputDob() {
        Scanner scanner = new Scanner(System.in);

//reads the date of birth from the user
        System.out.println("Enter birth month [MM]: ");
        String month = scanner.nextLine();
        System.out.println("Enter birth day [DD]: ");
        String day = scanner.nextLine();
        System.out.println("Enter birth year [YYYY]: ");
        String year = scanner.nextLine();
//the parse() method obtains an instance of LocalDate from a text string such as 1992-08-11
        LocalDate dob = LocalDate.parse(String.format("%s-%s-%s",year,month,day));
        return dob;
    }

    @Override
    int calculateAge(LocalDate dob) {
        //creating an instance of the LocalDate class and invoking the now() method
//now() method obtains the current date from the system clock in the default time zone
        LocalDate curDate = LocalDate.now();
//calculates the amount of time between two dates and returns the years
        if ((dob != null) && (curDate != null)) {
            return Period.between(dob, curDate).getYears();
        } else {
            return 0;
        }
    }

    @Override
    void showEmployes() {
        if (employees.size() == 0){
            System.out.println("There are no registered employee!");
            return;
        }
        for (Employee e: // if employees have elements
                employees) {

            System.out.print(e.getEmpID()+" |");
            System.out.print(e.getFirstName()+" ");
            System.out.print(e.getMiddleName()+" |");
            System.out.print(e.getLastName()+" |");
            System.out.print(e.getAge()+" |");
            System.out.print(String.format("%d: $%f |",e.getSalaryGrade(), getSalaryGrade().get(e.getSalaryGrade())));
            System.out.println(e.getDesignation()+" |");
            System.out.println("_______________");
        }
    }
}
