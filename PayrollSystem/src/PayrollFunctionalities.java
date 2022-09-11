import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

abstract class PayrollFunctionalities {
    int numberOfEmployees = 0;
    abstract String generateEmpID();
    abstract void showEmployes();
    abstract LocalDate inputDob();
    abstract int calculateAge(LocalDate dob);

    abstract void registerEmployee();
    abstract void searchEmployee();

    abstract void deductSalary();

    abstract boolean validateEmpID();

    String[] designations = {"Application Developer", "Associate Developer","Software Quality Assurance Analyst","Database Administrator","IT Director"
            ,"IT Analyst","Network Architect","IT Support Specialist","UI/UX Designer"};
    public HashMap<Integer,Double> salaryGrades = new HashMap<Integer,Double>();
    public void setSalaryGrade() {
        double[] salaries = {12517, 13305, 14125, 14993, 15909, 16877, 17899, 18998, 20402, 22190, 25439, 27608, 29798, 32321, 35097
        ,38150, 41508,45203,49835,55799};
        for (int i = 0; i<salaries.length;i++){
            salaryGrades.put(i+1,salaries[i]);
        }

//        this.salaryGrade.put(1,20.0);
    }

    public HashMap getSalaryGrade() {
        return salaryGrades;
    }
}
