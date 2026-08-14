package org.example;

import java.util.Map;

enum SplitType{ EQUAL,EXACT,PERCENTAGE

}
public class Expense {
    private int expenseId;
    private double amount;
    private User paidBy;
    private SplitType splitType;
    private Map<Integer,Double> splits;
    public Expense(double amount, int expenseId, User paidBy, Map<Integer, Double> splits, SplitType splitType) {
        this.amount = amount;
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.splits = splits;
        this.splitType = splitType;
        validate();
    }


    public double getAmount() {
        return amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public Map<Integer, Double> getSplits() {
        return splits;
    }

    public SplitType getSplitType() {
        return splitType;
    }
    private void validate(){
        if(amount<0){
            throw new IllegalArgumentException("Invalid amount");

        }
        if(splits==null || splits.isEmpty()){
            throw new IllegalArgumentException("splits can't be empty");
        }
        double total=0;
        for(double i: splits.values()){
            if(i<0){
                throw new IllegalArgumentException("split can't be -ve");
            }
            total+=i;
        }
        if(splitType==SplitType.EXACT &&
            Math.abs(total-amount)>0.0001){
            throw new IllegalArgumentException("Invalid exact spl");
        }
        if (splitType == SplitType.PERCENTAGE &&
                Math.abs(total - 100) > 0.001) {
            throw new IllegalArgumentException("Invalid percentage split");
        }
        if (paidBy == null) {
            throw new IllegalArgumentException("Payer can't be null");
        }

        if (splitType == null) {
            throw new IllegalArgumentException("Split type can't be null");
        }
    }
}
