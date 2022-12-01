package employees;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Intern extends Employee{

    //variables
    protected int gpa;

    //Get/Set functions
    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    //Constructor
    public Intern(String ID, String name, double salary, int GPA) throws Exception {
        super(ID, name, salary);
        this.gpa = GPA;
    }


    //implement Abstract methods
    @Override
    // get gross salary based on gpa
    public double getGrossSalary() {
        double tempSalary;
        if(gpa > 5 && gpa < 8){
            tempSalary = salary;
        }else if(gpa >= 8){
            tempSalary = salary + 1000;
        }else{
            tempSalary = 0;
        }

        return tempSalary;
    }

    @Override
    //Net salary = gross salary
    public double calculateSalary() {
        return getGrossSalary();
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        return name + "'s gross salary is " + decimalFormat.format(getGrossSalary()) + " SEK per month. GPA: " + gpa;
    }
}
