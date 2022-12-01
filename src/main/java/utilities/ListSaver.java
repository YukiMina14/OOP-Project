package utilities;

import employees.Employee;
import modules.*;
import java.util.ArrayList;
import java.util.List;

public class ListSaver {


    private final List<Item>          itemList;
    private final List<Review>        reviewList;
    private final List<Transaction>   transactionList;
    private final List<Employee>      employeeList;

    public ListSaver(){
        itemList        = new ArrayList<>();
        reviewList      = new ArrayList<>();
        transactionList = new ArrayList<>();
        employeeList    = new ArrayList<>();
    }
    
    public List<Item>           getItemList(){
        return itemList;
    }
    
    public List<Review>         getReviewList(){
        return reviewList;
    }
    
    public List<Transaction>    getTransactionList(){
        return transactionList;
    }

    public List<Employee>       getEmployeeList() {
        return employeeList;
    }

}
