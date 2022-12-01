package employees;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Director extends Manager{
    //Variables
    String department;

    //Constructor
    public Director(String ID, String name, double salary, String degree, String department) throws Exception {
        super(ID, name, salary, degree);
        this.department = department;
    }

    //setter and getter functions
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }


    // Abstract method implementation
    @Override
    //gets the gross salary of the employee adds 5000Sek and adds bonus based on degree
    public double getGrossSalary() {
        double tempSalary  = salary;
        switch (degree){
            case BSc:
                tempSalary *= 110;
                break;
            case MSc:
                tempSalary *= 120;
                break;
            case PhD:
                tempSalary *= 135;
                break;
        }
        tempSalary = (int)tempSalary;
        tempSalary /= 100;
        tempSalary += 5000.00;
        return tempSalary;
    }

    @Override
    //gets the Net salary of the employee by subtracting taxes from gross salary
    public double calculateSalary() {
        double tempSalary = getGrossSalary();
        if(tempSalary < 30_000){
            tempSalary -= tempSalary * 0.1;
        }else if( tempSalary >= 30_000 && tempSalary <= 50_000){
            tempSalary -= tempSalary * 0.2;
        }else{
            tempSalary -= (30_000 * 0.2) + ((tempSalary - 30_000) * 0.4);
        }

        return tempSalary;
    }

    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return  degree + " " + name + "'s gross salary is " + decimalFormat.format(getGrossSalary()) + " SEK per month. Dept: " + department;
    }

}
