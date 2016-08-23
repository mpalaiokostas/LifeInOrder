package life.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/bank/")
public class BankController {

    @Inject
    private BankInterface bankInterface;

    @RequestMapping("allTransactions")
    //todo the name of this method must change and along the implementation of it, probably a transformer of type should be added
    public List<TableObject> getTableObjects() {
        return bankInterface.getTableObjects();
    }

    @RequestMapping(value = "monthlyExpensesList/{yearNumber}/{monthNumber}")
    public List<TableObject> getMonthlyExpensesList(@PathVariable int monthNumber, @PathVariable int yearNumber){
        return bankInterface.getMonthlyExpensesList(monthNumber, yearNumber);
    }

    @RequestMapping(value = "monthlyIncomeList/{yearNumber}/{monthNumber}")
    public List<TableObject> getMonthlyIncomeList(@PathVariable int monthNumber, @PathVariable int yearNumber){
        return bankInterface.getMonthlyIncomeList(monthNumber, yearNumber);
    }
}