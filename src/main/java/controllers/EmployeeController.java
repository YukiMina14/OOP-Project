package controllers;

import employees.*; //import the whole employee package
import utilities.ListSaver;


import java.util.*;

public class EmployeeController {
    private final String EOL = System.lineSeparator();
    private final List<Employee> employeeList;


    //Constructor
    public EmployeeController(ListSaver listSaver){
        this.employeeList = listSaver.getEmployeeList();
    }


    //getter
    //returns Employee list
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    //returns a specific employee in object form
    public Employee getEmployee(String employeeId) throws Exception{
        for(Employee employee : employeeList){
            if( employee.getEmployeeID().equals(employeeId) ){
                return employee;
            }
        }
        throw new Exception("Employee "+employeeId+" was not registered yet.");
    }


    //Employee creation
    //create a Regular employee and adds it to employeelist
    public String createEmployeeRegular(String employeeID, String employeeName, double salary)                                              throws Exception {
        // Will check to see if the employee ID is empty or the employee name is empty or the price is <= 0

        for (Employee employee : employeeList) {
            if (employee.getEmployeeID().equals(employeeID)) {
                throw new Exception("Two employees cannot have the same ID!");
            }
        }

        // Otherwise, create a new employee and add to the employeeController arraylist.
        Employee employee = new Regular(employeeID, employeeName, salary);
        employeeList.add(employee);
        return "Employee " + employeeID + " was registered successfully.";
    }

    //create a Manager employee and adds it to employeelist
    public String createEmployeeManager(String employeeId, String employeeName, double salary, String degree)                       throws Exception{

        for (Employee employee : employeeList) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return "Two employees cannot have the same ID!";
            }
        }

        // Otherwise, create a new employee and add to the employeeController arraylist.
        Employee employee = new Manager(employeeId, employeeName, salary, degree);
        employeeList.add(employee);
        return "Employee " + employeeId + " was registered successfully.";

    }

    //create a Director employee and adds it to employeelist
    public String createEmployeeDirector(String employeeId, String employeeName, double salary, String degree, String department)   throws Exception{
        if(checkNotDepartment(department)) throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        for (Employee employee : employeeList) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return "Two employees cannot have the same ID!";
            }
        }

        // Otherwise, create a new employee and add to the employeeController arraylist.
        Employee employee = new Director(employeeId, employeeName, salary, degree, department);
        employeeList.add(employee);
        return "Employee " + employeeId + " was registered successfully.";
    }

    //create an Intern employee and adds it to employeelist
    public String createEmployeeIntern(String employeeId, String employeeName, double salary, int gpa)                                      throws Exception{
        if (gpa < 0 || gpa > 10) throw new Exception( gpa + " outside range. Must be between 0-10.");
        for (Employee employee : employeeList) {
            if (employee.getEmployeeID().equals(employeeId)) {
                return "Two employees cannot have the same ID!";
            }
        }

        // Otherwise, create a new employee and add to the employeeController arraylist.

        Employee employee = new Intern(employeeId, employeeName, salary, gpa);

        employeeList.add(employee);
        return "Employee " + employeeId + " was registered successfully.";
    }

    public String removeEmployee(String employeeId) throws Exception{
        for(Employee employee : employeeList){
            if( employee.getEmployeeID().equals(employeeId) ){
                employeeList.remove( employee );
                return "Employee " + employee.getEmployeeID() + " was successfully removed.";
            }
        }
        throw new Exception("Employee " + employeeId + " was not registered yet.");
    }



    //Returns a specific employee in string form
    public String printEmployeeSingle(String employeeId) throws Exception{

        for(Employee employee : employeeList) {
            if( employee.getEmployeeID().equals(employeeId) ){
                return employee.toString();
            }
        }
        throw new Exception("Employee " +employeeId+ " was not registered yet.");

    }

    //returns all employees in a list
    public String printEmployeeAll() throws Exception{
        if (employeeList.isEmpty()) throw new Exception("No employees registered yet.");
        StringBuilder result = new StringBuilder();
        result.append("All registered employees:" + EOL);

        for (Employee employee : employeeList){
            result.append(employee.toString() + EOL);
        }

        return result.toString();
    }

    //returns net salary of all employees combined
    public double getTotalNetSalary() throws  Exception {
        if (employeeList.isEmpty()) throw new Exception("No employees registered yet.");
        double temp = 0;

        for (Employee employee : employeeList){
            temp += employee.calculateSalary();
        }

        temp = Math.floor(temp * 100) / 100;
        return temp;
    }



    //sorted Prints
    //returns Print employee sorted by gross salary ascending
    public String printEmployeeSortGrossAsc()throws Exception{
        if (employeeList.isEmpty()) throw new Exception("No employees registered yet.");
        StringBuilder result = new StringBuilder();
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return Double.compare(  employee1.getGrossSalary(),  employee2.getGrossSalary()  );
            }
        });
        result.append("Employees sorted by gross salary (ascending order):"+EOL);
        for (Employee employee : employeeList){
            result.append(employee + EOL);
        }

        return result.toString();
    }

    //return each Degree and how many employees have that degree
    public Map<String, Integer> printEmployeePerDegree() throws Exception{
        if (employeeList.isEmpty()) throw new Exception("No employees registered yet.");
        int bsc = 0, msc = 0, phd = 0;

        for (Employee employee : employeeList){
            if(employee instanceof Manager){
                Manager.Degree degree = ((Manager) employee).getDegree();
                switch (degree){
                    case BSc:   bsc++;      break;
                    case MSc:   msc++;      break;
                    case PhD:   phd++;      break;
                }
            }
        }
        HashMap<String, Integer> degreeMap = new HashMap<>();

        if(bsc != 0) degreeMap.put("BSc", bsc);
        if(msc != 0) degreeMap.put("MSc", msc);
        if(phd != 0) degreeMap.put("PhD", phd);

        return degreeMap;
    }



    //Change Functions
    //change name of an employee
    public String changeName        (String employeeId, String name) throws Exception{
        if(name.isBlank()) throw new Exception("Name cannot be blank.");
        try {
            getEmployee(employeeId).setName(name);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        return "Employee " + employeeId + " was updated successfully";
    }

    //change Gross sallary of an employee
    public String changeGrossSalary (String employeeId, double salary) throws Exception{
        if(salary <= 0.00) throw new Exception("Salary must be greater than zero.");
        try {
            getEmployee(employeeId).setSalary(salary);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        return "Employee " + employeeId + " was updated successfully";
    }

    //change degree of an employee
    public String changeDegree      (String employeeId, String degree) throws Exception {
        try {
            Manager.Degree.valueOf(degree);
        }catch (Exception e){
            throw  new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }

        Employee temp;
        try {
            temp = getEmployee(employeeId);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        if(! (temp instanceof Manager) )    throw new Exception("Employee is not a manager.");

        ((Manager) temp).setDegree(Manager.Degree.valueOf(degree));

        return "Employee " + employeeId + " was updated successfully";
    }

    //change department of an employee
    public String changeDepartment  (String employeeId, String department) throws Exception{
        if (checkNotDepartment(department)) throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        Employee temp;
        try {
            temp = getEmployee(employeeId);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        if(! (temp instanceof Director) )    throw new Exception("Employee is not a manager.");

        ((Director) temp).setDepartment(department);

        return "Employee " + employeeId + " was updated successfully";
    }

    //change gpa an employee
    public String changeGpa         (String employeeId, int gpa) throws Exception{
        if (gpa < 0 || gpa > 10) throw new Exception( gpa + " outside range. Must be between 0-10.");
        Employee temp;
        try {
            temp = getEmployee(employeeId);
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        if(! (temp instanceof Intern) )    throw new Exception("Employee is not a manager.");

        ((Intern) temp).setGpa(gpa);

        return "Employee " + employeeId + " was updated successfully";
    }

    //check if the departments given to it exists
    private Boolean checkNotDepartment(String department) throws Exception{
        switch (department){
            case "Human Resources":
            case "Technical":
            case "Business":
                return false;
            default:
                return true;
        }
    }



    //promotions
    //promotes employee to manager
    public String promoteToManager(String employeeId, String degree) throws Exception {
        Employee temp;
        String name;
        double salary;

        try {
            temp = getEmployee(employeeId);
            name = temp.getName();
            salary = temp.getSalary();

            employeeList.remove(temp);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        Employee employee = new Manager(employeeId, name, salary, degree);
        employeeList.add(employee);

        return employeeId + " promoted successfully to Manager.";
    }

    //promotes employee to Director
    public String promoteToDirector(String employeeId, String degree, String department) throws Exception {
        Employee temp;
        String name;
        double salary;

        try {
            temp = getEmployee(employeeId);
            name = temp.getName();
            salary = temp.getSalary();

            employeeList.remove(temp);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        Employee employee = new Director(employeeId, name, salary, degree, department);
        employeeList.add(employee);

        return employeeId + " promoted successfully to Director.";
    }

    //promotes employee to Intern
    public String promoteToIntern(String employeeId, int gpa) throws Exception {
        Employee temp;
        String name;
        double salary;

        try {
            temp = getEmployee(employeeId);
            name = temp.getName();
            salary = temp.getSalary();

            employeeList.remove(temp);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Employee " + employeeId + " was not registered yet.");
        }

        Employee employee = new Intern(employeeId, name, salary, gpa);
        employeeList.add(employee);

        return employeeId + " promoted successfully to Intern.";
    }




}


