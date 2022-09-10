import java.time.LocalDate;
import java.util.Scanner;
class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String empID;
    private int age;
    private int salaryGrade;
    private String designation;
    private String cityAddress;

    private LocalDate empDob;

    public Employee(String fname, String mname, String lname, String id,LocalDate dob, String desig, String address, int salaryGrade, int age){
        this.firstName = fname;
        this.middleName = mname;
        this.lastName = lname;
        this.age = age;
        this.empID = id;
        this.empDob = dob;
        this.designation = desig;
        this.cityAddress = address;
        this.salaryGrade = salaryGrade;
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
