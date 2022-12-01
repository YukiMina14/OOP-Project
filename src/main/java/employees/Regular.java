package employees;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Regular extends Employee{

    //Attributes of regular employee

    //constructor
    public Regular(String ID, String name, double salary) throws Exception {
        super(ID, name, salary);
    }

    //implement Abstract methods
    @Override
    //get net salary by subtracting taxes
    public double calculateSalary() {
        double netSalary = super.salary - (super.salary * 0.1); //salary - 10% tax
        return netSalary;
    }

    //get gross salary
    public double getGrossSalary(){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);

        return Double.parseDouble( decimalFormat.format(salary) ) ;
    }

}//End of regular

//employee has 10% income tax
