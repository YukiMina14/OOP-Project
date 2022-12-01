package employees;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Manager extends Employee {
    //Enums
    public enum Degree{
        BSc,        //+10%
        MSc,        //+20%
        PhD,        //+35%
    }

    //variables
    protected Degree degree;

    //Constructor
    public Manager(String ID, String name, double salary, String degree) throws Exception {

        super(ID, name, salary);
        try{
            this.degree = Degree.valueOf(degree);
        } catch (Exception e){
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }

    }

    //Get/Set functions
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    // Abstract method implementation
    @Override
    // get Gross salary applies degree bonus
    public double getGrossSalary() {
        double tempSalary = salary;
        tempSalary = Double.parseDouble(  String.valueOf((int) (tempSalary * 100) ) );
        tempSalary /= 100;
        switch (degree){
            case BSc:
                tempSalary *= 1.10;
                break;
            case MSc:
                tempSalary *= 1.20;
                break;
            case PhD:
                tempSalary *= 1.35;
                break;
        }

        return tempSalary;
    }

    @Override
    // get Net salary by subtracting taxes
    public double calculateSalary() {
        double tempSalary = getGrossSalary();
        return tempSalary - tempSalary * 0.1;
    }

    @Override
    public String toString(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return degree + " " + name + "'s gross salary is " + decimalFormat.format(getGrossSalary()) + " SEK per month.";
    }
}
