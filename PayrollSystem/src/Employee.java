import java.time.LocalDate;
import java.util.Scanner;
class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String empID;
    private int age;
    private int salaryGrade;

    private double salary;
    private String designation;
    private String cityAddress;

//    Salary deduction
    private double salaryDeduction = 0;

//    Allowance
    private double allowance = 0;

    private LocalDate empDob;

    public Employee(String fname, String mname, String lname, String id,LocalDate dob, String desig, String address, int salaryGrade, int age, double salary){
        this.firstName = fname;
        this.middleName = mname;
        this.lastName = lname;
        this.age = age;
        this.empID = id;
        this.empDob = dob;
        this.designation = desig;
        this.cityAddress = address;
        this.salaryGrade = salaryGrade;
        this.salary = salary;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setSalaryDeduction(double salaryDeduction) {
        this.salaryDeduction = salaryDeduction;
    }

    public double getSalaryDeduction() {
        return salaryDeduction;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }


    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setDob(LocalDate dob){
        this.empDob = dob;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public LocalDate getEmpDob() {
        return empDob;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String fname){
        this.firstName = fname;
    }

    public String getMiddleName(){
        return middleName;
    }

    public void setMiddleName(String mname){
        this.middleName = mname;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lname){
        this.lastName = lname;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int empAge){

        this.age = empAge;
    }

    public void setEmpID(String id){
        this.empID = id;
    }

    public String getEmpID(){
        return empID;
    }

}
