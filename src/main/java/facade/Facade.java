package facade;

import controllers.*;
import employees.Employee;
import employees.Manager;
import utilities.ListSaver;

import java.util.List;
import java.util.Map;

public class Facade {

    private final ListSaver listSaver;
    private final ItemController itemController;
    private final ReviewController reviewController;
    private final TransactionController transactionController;
    private final EmployeeController employeeController;

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.

    public Facade(){
        listSaver = new ListSaver();
        itemController = new ItemController(listSaver);
        reviewController = new ReviewController(listSaver);
        transactionController = new TransactionController(listSaver);
        employeeController = new EmployeeController(listSaver);

    }

    public String createItem(String itemID, String itemName, double unitPrice){return itemController.createItem(itemID, itemName, unitPrice);}

    public String printItem(String itemID) { return itemController.printAnSpecificItem(itemID);}

    public String removeItem(String itemID) {
        return itemController.RemoveItem(itemID);
    }

    public boolean containsItem(String itemID) {
        return itemController.IdExists(itemID);
    }

    public double buyItem(String itemID, int quantity) {
        return itemController.buyItem(itemID, quantity);//transactionController.buyItem( itemID, quantity);
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        return reviewController.createReview(itemID, reviewGrade, reviewComment);
    }

    public String reviewItem(String itemID, int reviewGrade) {
        return reviewController.createReview(itemID, reviewGrade, "");
    }

    public String getItemCommentsPrinted(String itemID) {
        return reviewController.printAllReviewsForItem(itemID);
    }

    public List<String> getItemComments(String itemID) {
        return reviewController.getAllComments(itemID);
    }

    public double getItemMeanGrade(String itemID) {
        return reviewController.getMeanGrade(itemID);
    }

    public int getNumberOfReviews(String itemID) {
        return reviewController.getReviewAmount(itemID);
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        return reviewController.printSpecificReview(itemID, reviewNumber);
    }

    public String getPrintedReviews(String itemID) {
        return reviewController.printAllReviewsForItem(itemID);
    }

    public String printMostReviewedItems() {
        return reviewController.printMostReviewedItem();
    }

    public List<String> getMostReviewedItems() {
        return reviewController.getMostReviewedItem();
    }

    public List<String> getLeastReviewedItems() {
        return reviewController.getLeastReviewedItem();
    }

    public String printLeastReviewedItems() {
        return reviewController.printLeastReviewedItem();
    }

    public double getTotalProfit() {
        return transactionController.getTotalProfit();
    }




    public String printItemTransactions(String itemID) {
        return transactionController.printItemTransactions(itemID);
    }

    public int getTotalUnitsSold() {
        return transactionController.getTotalSales();
    }

    public int getTotalTransactions() {
        return transactionController.getTotalTransactions();
    }

    public double getProfit(String itemID) {
        return transactionController.getProfitSum(itemID);
    }//trans.itemHistoryProfit(itemID)


    public int getUnitsSolds(String itemID) {
        return transactionController.getSaleVolume(itemID);
    }

    public String printAllTransactions() {
        return transactionController.printAllTransactions();
    }

    public String printWorseReviewedItems() {
        return reviewController.printWorstMeanGrade();
    }

    public String printBestReviewedItems() {
        return reviewController.printBestMeanGrade();
    }

    public List<String> getWorseReviewedItems() {
        return reviewController.getWorstMeanGrade();
    }

    public List<String> getBestReviewedItems() {
        return reviewController.getBestMeanGrade();
    }

    public String printAllReviews() {
        return reviewController.printAllReviews();
    }

    public String updateItemName(String itemID, String newName) {
        return itemController.updateItemName(itemID, newName);
    }

    public String updateItemPrice(String itemID, double newPrice) {
        return itemController.updateItemPrice(itemID, newPrice);
    }

    public String printAllItems() {
        return itemController.printAllItem();
    }

    public String printMostProfitableItems() {
        return transactionController.printMostProfitableItem();
    }



    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        return employeeController.createEmployeeRegular(employeeID, employeeName, grossSalary);
    }

    public String printEmployee(String employeeID) throws Exception {
        return employeeController.printEmployeeSingle(employeeID);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        return employeeController.createEmployeeManager(employeeID, employeeName, grossSalary, degree );
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return employeeController.createEmployeeIntern(employeeID, employeeName, grossSalary, gpa);
    }

    public double getNetSalary(String employeeID) throws Exception {
        return employeeController.getEmployee(employeeID).calculateSalary();
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        return employeeController.createEmployeeDirector(employeeID, employeeName, grossSalary, degree, dept);
    }

    public String removeEmployee(String empID) throws Exception {
        return employeeController.removeEmployee(empID);
    }

    public String printAllEmployees() throws Exception {
        return employeeController.printEmployeeAll();
    }

    public double getTotalNetSalary() throws Exception {
        return employeeController.getTotalNetSalary();
    }

    public String printSortedEmployees() throws Exception {
        return employeeController.printEmployeeSortGrossAsc();
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        return employeeController.changeName(empID, newName);
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        return employeeController.changeGpa(empID, newGPA);
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        return employeeController.changeDegree(empID, newDegree);
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        return employeeController.changeDepartment(empID, newDepartment);
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        return employeeController.changeGrossSalary(empID, newSalary);
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        return employeeController.printEmployeePerDegree();
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        return employeeController.promoteToManager(empID, degree );

    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        return employeeController.promoteToDirector(empID, degree, department);
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        return employeeController.promoteToIntern(empID, gpa);
    }
}