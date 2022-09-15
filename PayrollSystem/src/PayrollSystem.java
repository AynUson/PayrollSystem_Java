
import java.io.FileWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.time.*;
import java.time.temporal.*;
import java.io.FileOutputStream;
import java.io.IOException;
class PayrollSystem extends PayrollFunctionalities{
    public ArrayList< Employee> employees = new ArrayList<>();
//    ArrayList that will store employee object


    Scanner sc = new Scanner(System.in);

//    Method that validates if the input is numeric
//    Used on setting salary, allowance, deductions
    double validateDouble(){
        double d;
        while (true) {
//            System.out.println("Type a double-type number:");
            try {
                d = Double.parseDouble(sc.nextLine());
                return d;
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input. Enter valid number!");
            }
        }
    }

    //  Payroll Functionalities Class

//   method for registering employee
    @Override
    void registerEmployee(){
//        booleans for validations
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
        System.out.println("Enter Address:");
        String address = sc.nextLine();

        System.out.println("Monthly Salary: ");

        double salary = validateDouble();
//
//        while (true) {
//            System.out.println("Type a double-type number:");
//            try {
//                salary = Double.parseDouble(sc.nextLine());
//                break; // will only get to here if input was a double
//            } catch (NumberFormatException ignore) {
//                System.out.println("Invalid input. Enter your salary!!");
//            }
//        }


        double minimumSalary = 0;
        int grade = 0;
        for (Map.Entry me : salaryGrades.entrySet()) {
            double val = (double) me.getValue();
            int g = (int) me.getKey();
            if (salary < val)
                break;
            grade = g;
            minimumSalary = val;
        }
        System.out.println("Your salary: "+ salary);

        System.out.println(String.format("Grade: %d - %f",grade,minimumSalary));


//
        LocalDate dob = inputDob();
        int age = calculateAge(dob);
        if (age < 18){
            errMsg+="\nAge below 18.";
            isLegalAge = false;
        }


        if (employees.size() == 0){ //if employees size is zero
            employees.add(new Employee(fname,mname,lname, generateEmpID(),dob,designation,address,grade, age,salary));

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
            employees.add(new Employee(fname,mname,lname, generateEmpID(),dob,designation,address,grade, age,salary));
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
                        "\nAddress:\t\t %s \nSalary Grade:\t %d- $%.2f \nAllowance:\t\t $%.2f\nSalary Deduction: $%.2f ",e.getEmpID(),e.getFirstName(),e.getMiddleName(),e.getLastName(),e.getAge()
                        ,e.getDesignation(), e.getCityAddress(),e.getSalaryGrade(), getSalaryGrade().get(e.getSalaryGrade()),e.getAllowance(), e.getSalaryDeduction());
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

    String validateDate(String msg){
        String date;
        while (true) {
            System.out.println(msg);
            try {
                date = Integer.toString(Integer.parseInt(sc.nextLine()));
                if (date.length() == 1)
                    date = "0"+date;
                return date;
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input. Follow the instruction. example January = [01]!");
            }
        }
    }
    @Override
    LocalDate inputDob() {
        Scanner scanner = new Scanner(System.in);

//reads the date of birth from the user
        String month = validateDate("Enter birth month [MM]: ");
        String day = validateDate("Enter birth day [DD]:  ");
        String year =validateDate("Enter birth year [YYYY]: ");
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
//        Extra method to show the registered employees
        if (employees.size() == 0){
            System.out.println("There are no registered employee!");
            return;
        }

        System.out.println("\t\t**RESULT**");
        for (Employee e: // if employees have elements
                employees) {

            System.out.println(String.format("--------------------------------------\nID:\t\t\t\t %s \nFull name:\t\t %s %s %s \nAge:\t\t\t %s \nDesignation:\t %s " +
                            "\nAddress:\t\t %s \nSalary Grade:\t %d- $%f \nSalary Deduction:\t $%f\nAllowance:\t $%f \n--------------------------------------",e.getEmpID(),e.getFirstName(),e.getMiddleName(),e.getLastName(),e.getAge()
                    ,e.getDesignation(), e.getCityAddress(),e.getSalaryGrade(), getSalaryGrade().get(e.getSalaryGrade()),e.getSalaryDeduction(),e.getAllowance()));
//
        }
    }

    @Override
    boolean validateEmpID() {
        boolean valid = false;
        System.out.println("Enter Employee ID: ");
        String empID = sc.nextLine();
        for (Employee user:
                employees) {
            if (empID.equals(user.getEmpID())){
                this.loggedIn = user;
                valid = true;
                break;
            }
        }
        if (!valid)
            System.out.println("Did not match any record.");
        return valid;

    }

    Employee loggedIn;
    @Override
    void deductSalary() {
        if (employees.size() == 0){
            System.out.println("There are no registered employee!");
            return;
        }

        System.out.println("*_*_* Salary Deduction *_*_*");
        if (!validateEmpID())
            return;

        System.out.println(loggedIn.getFirstName());
        System.out.println(loggedIn.getDesignation());
        System.out.println(loggedIn.getSalary());

        System.out.println("Enter amount to deduct: ");
        double deduction = validateDouble();
        loggedIn.setSalaryDeduction(deduction);
        System.out.println(String.format("Current Salary: %f \nSalary Deduction: %f\n Salary Deduction Recorded.",loggedIn.getSalary(),loggedIn.getSalaryDeduction()));
        this.loggedIn = null;

    }

    @Override
    void addAllowance() {
        if (employees.size() == 0){
            System.out.println("There are no registered employee!");
            return;
        }

        System.out.println("*_*_* Allowance *_*_*");
        if (!validateEmpID())
            return;

        System.out.println(String.format("%s %s %s",loggedIn.getFirstName(), loggedIn.getMiddleName(), loggedIn.getLastName()));
        System.out.println(loggedIn.getDesignation());
        System.out.println(loggedIn.getSalary());

        System.out.println("Enter amount to add as compensation: ");
        double allowance = validateDouble();
        loggedIn.setAllowance(allowance);
        System.out.println(String.format("Current Salary: %f \nAllowance: %f\n Added compensation Recorded.",loggedIn.getSalary(),loggedIn.getAllowance()));
        this.loggedIn = null;
    }

    @Override
    void printPayslip() {
        if (employees.size() == 0){
            System.out.println("There are no registered employee!");
            return;
        }

        System.out.println("*_*_* Payslip *_*_*");
        validateEmpID();

        System.out.println(loggedIn.getFirstName());
        System.out.println(loggedIn.getDesignation());
        System.out.println(loggedIn.getSalary());

        System.out.println("Date range(from)");
        String monthFrom = validateDate("Month [mm]");
        String dayFrom = validateDate("Day [dd]");
        String yearFrom = validateDate("Year [yyyy]");

        System.out.println("Date range(to)");
        String monthTo =validateDate("Month [mm]");
        String dayTo = validateDate("Day [dd]");
        String yearTo = validateDate("Year [yyyy]");

        DateTimeFormatter dtfWords = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        String fromDate = String.format("%s %s %s",dayFrom, monthFrom, yearFrom);
        String toDate = String.format("%s %s %s",dayTo, monthTo, yearTo);


        LocalDateTime date1 = LocalDate.parse(fromDate, dtf).atStartOfDay();
        LocalDateTime date2 = LocalDate.parse(toDate, dtf).atStartOfDay();


        String fromDateWords = dtfWords.format(date1);
        String toDateWords = dtfWords.format(date2);

        int dateValidity =  date1.compareTo(now);
        // 0: if both dates are equal.
//        value less than 0: if the date is before the argument date.
//        value greater than 0: if the date is after the argument date.

        if (dateValidity > 0){
            System.out.println("Starting date can not be ahead the current date!");
            return;
        }

        if (date1.compareTo(date2 ) > 0){
            System.out.println("Starting date can not be ahead the ending date!");
            return;
        }
        double salary = 0;
        double totalDeduction = 0;
        double incomeTax = this.loggedIn.getSalary() * incomeTaxTax;
        double sss = this.loggedIn.getSalary() * sssTax;
        double philhealth = this.loggedIn.getSalary() * philHealthTax;
        double pagibig = this.loggedIn.getSalary() * pagIbigTax;
        double totalCont = sss + philhealth + pagibig;
        int workingDaysInAMonth = 20;
        long daysBetween = calcWeekDays1(date1,date2);
//        long daysBetween = Duration.between(date1, date2).toDays();

        System.out.println(String.format("Inclusive Dates: %s - %s", fromDateWords, toDateWords));
        System.out.println ("Working Days: " + daysBetween);
        System.out.print(loggedIn.getFirstName()+" | ");
        System.out.println(loggedIn.getDesignation());
        System.out.println("Monthly Salary"+loggedIn.getSalary());
        System.out.println("Gross Salary: "+ (this.loggedIn.getSalary()/workingDaysInAMonth) * daysBetween);
        System.out.println("--------------------------------------");
        System.out.println("Allowance: "+ this.loggedIn.getAllowance());
        salary = ((this.loggedIn.getSalary()/workingDaysInAMonth) * daysBetween) + this.loggedIn.getAllowance();
        System.out.println("Salary Deduction: "+ this.loggedIn.getSalaryDeduction());
        System.out.println("--------------Income Tax--------------");
        System.out.println(String.format("Tax: %.2f", incomeTax));

        System.out.println("--------------Contributions--------------");
        System.out.println(String.format("SSS: %.2f",sss));
        System.out.println(String.format("PhilHealth: %.2f",philhealth));
        System.out.println(String.format("PAG-IBIG: %.2f", pagibig));
        System.out.println(String.format("Total Contributions: %.2f", totalCont));
        System.out.println("------------------------------------------");
        totalDeduction = totalCont + incomeTax + this.loggedIn.getSalaryDeduction();
        System.out.println(String.format("Total Deductions: %.2f", totalDeduction));
        salary -= totalDeduction;
        System.out.println(String.format("Net Pay after Deductions and additional compensation: %.2f",salary));
        String toBePrinted = String.format("Inclusive Dates: %s - %s\nWorking Days: %d\nEmployee Name: %s %s %s\nDesignation: %s \nMonthly Salary: %.2f\nGross Salary: %.2f\n" +
                        "--------------------------------------\nAllowance: %.2f\nSalary Deduction: %.2f\n--------------Income Tax--------------\nTax: %.2f\n" +
                        "--------------Contributions--------------\nSSS: %.2f\nPhilHealth: %.2f\nPAG-IBIG: %.2f\nTotal Contributions: %.2f\n" +
                        "------------------------------------------\nTotal Deduction: %.2f\nNet Pay after Deductions and additional compensation: %.2f", fromDateWords, toDateWords,
                daysBetween,loggedIn.getFirstName(),loggedIn.getMiddleName(),loggedIn.getLastName(),loggedIn.getDesignation(), loggedIn.getSalary(),((this.loggedIn.getSalary()/workingDaysInAMonth) * daysBetween),
                this.loggedIn.getAllowance(),this.loggedIn.getSalaryDeduction(),incomeTax,sss,philhealth,pagibig,totalCont,totalDeduction
                ,salary);
        printTextFilePayslip(toBePrinted);
        System.out.println("Text File to be printed!");
    }


    public static long calcWeekDays1(final LocalDateTime start, final LocalDateTime end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }

    void printTextFilePayslip(String toBePrinted){
        // Assign the file content

        String fileContent = "███ ███ █╬█ ██ █╬ █ ███\n" +
                "█▄█ █▄█ █▄█ █▄ █╬ █ █▄█\n" +
                "█╬╬ █╬█ ╬█╬ ▄█ ██ █ █╬╬\n";
        fileContent +=toBePrinted;


        try {
            FileWriter myWriter = new FileWriter(String.format("Payslip%s.txt",this.loggedIn.getLastName()));
            myWriter.write(fileContent);
            System.out.println("File Created");
            myWriter.close();
        }catch (IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
        this.loggedIn = null;
    }

}
