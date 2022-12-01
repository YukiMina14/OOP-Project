package employees;

import java.text.DecimalFormat;

public abstract class Employee {
    protected final String employeeID; //every employee should have an ID called employeeID to prevent confusion with item ID's
    protected String name; //every employee should have a name
    protected double salary; //protected so everyone in the same package can get accesses but not other packages.

    //constructor for the abstract class
    public Employee (String ID ,String name, double salary) throws Exception {
        if(ID.isBlank())    throw new Exception("ID cannot be blank.");
        if(name.isBlank())  throw new Exception("Name cannot be blank.");
        if(salary <= 0.00)  throw new Exception("Salary must be greater than zero.");

        this.employeeID = ID;
        this.name       = name;
        this.salary     = salary;
    }

    //getter and setter methods
    public String getEmployeeID(){
        return this.employeeID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public double getSalary(){
        return this.salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }


    @Override //overrides the default toString method
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return this.name + "'s gross salary is " + decimalFormat.format(this.salary) + " SEK per month.";
    }

    public abstract double calculateSalary();
    public abstract double getGrossSalary();

}
