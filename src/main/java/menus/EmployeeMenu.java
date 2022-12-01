package menus;

import controllers.EmployeeController;
import utilities.ListSaver;
import utilities.UserInput;

public class EmployeeMenu {

    private UserInput userInput;
    private EmployeeController employeeController;

    private final String EOL = System.lineSeparator();

    EmployeeMenu(ListSaver listSaver){ //constructor for ItemMenu
        userInput = new UserInput(); //instantiating UserInput
        employeeController = new EmployeeController(listSaver); // Instantiating ItemController
    }

    public void employeeMenu(){
        String input;

        do {

            System.out.print(
                EOL +
                "Employee options menu:" + EOL +
                "0. Return to Main Menu." + EOL +
                "1. Create an employee (Regular Employee)." + EOL +
                "2. Create an employee (Manager)." + EOL +
                "3. Create an employee (Director)." + EOL +
                "4. Create an employee (Intern)." + EOL +
                "5. Remove an employee." + EOL +
                "6. Print specific employee." + EOL +
                "7. Print all registered employees." + EOL

            );

            input = userInput.readString();
            switch ( input ) { // stylistic choice:  we like to have cases and break in 1 line, it is more readable for us
                case "0":                               continue; //skips this loop continues with the next

                case "1": createEmployeeRegular();                  break;

                case "2": createEmployeeManager();                  break;

                case "3": createEmployeeDirector();                 break;

                case "4": createEmployeeIntern();                   break;

                case "5": removeEmployee();                         break;

                case "6": printEmployeeSingle();                    break;

                case "7": printEmployeeAll();                       break;

                default:
                    System.out.println("Please type in another option from 0-7");
                    break;
            }
        } while (!input.equals("0"));
    }

    private void createEmployeeRegular(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        System.out.print("Employee Name: ");
        String employeeName     = userInput.readString();

        System.out.print("Employee Annual Salary: ");
        double employeeSalary   = userInput.readDouble();

        try{
            System.out.print(employeeController.createEmployeeRegular(employeeId, employeeName, employeeSalary));
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    private void createEmployeeManager(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        System.out.print("Employee Name: ");
        String employeeName     = userInput.readString();

        System.out.print("Employee Annual Salary: ");
        double employeeSalary   = userInput.readDouble();

        System.out.print("Employee Degree: ");
        String employeeDegree   = userInput.readString();

        try {
            System.out.print(employeeController.createEmployeeManager(employeeId, employeeName, employeeSalary, employeeDegree));
        }catch(Exception e){
            e.printStackTrace();
        }
    };

    private void createEmployeeDirector(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        System.out.print("Employee Name: ");
        String employeeName     = userInput.readString();

        System.out.print("Employee Annual Salary: ");
        double employeeSalary   = userInput.readDouble();

        System.out.print("Employee Degree: ");
        String employeeDegree   = userInput.readString();

        System.out.print("Department: ");
        String department       = userInput.readString();

        try {
            System.out.print(employeeController.createEmployeeDirector(employeeId, employeeName, employeeSalary, employeeDegree, department));
        }catch(Exception e){
            e.printStackTrace();
        }
    };

    private void createEmployeeIntern(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        System.out.print("Employee Name: ");
        String employeeName     = userInput.readString();

        System.out.print("Employee Annual Salary: ");
        double employeeSalary   = userInput.readDouble();

        System.out.print("Gpa: ");
        int gpa  = userInput.readInt();

        try {
            System.out.print(employeeController.createEmployeeIntern(employeeId, employeeName, employeeSalary, gpa));
        }catch(Exception e){
            e.printStackTrace();
        }
    };

    private void removeEmployee(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        try {
            System.out.print(employeeController.removeEmployee(employeeId));
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    private void printEmployeeSingle(){
        System.out.print("Employee ID: ");
        String employeeId       = userInput.readString();

        try {
            System.out.print(employeeController.printEmployeeSingle(employeeId));
        }catch (Exception e) {
            e.printStackTrace();
        }
    };

    private void printEmployeeAll(){
        try {
            System.out.print(employeeController.printEmployeeAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    };


} // end of EmployeeMenu Class
