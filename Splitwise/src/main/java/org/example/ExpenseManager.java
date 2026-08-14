package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {
    Map<Integer,User> users;
    Map<Integer,Group> groups;
    Map<Integer,Expense> expenses;
    Map<Integer,Map<Integer,Double>> splitBalance;
    public ExpenseManager() {
        users = new HashMap<>();
        groups = new HashMap<>();
        expenses = new HashMap<>();
        splitBalance = new HashMap<>();
    }
    public boolean addMember(int userId,int grpId){
        Group group = groups.get(grpId);
        if(group==null || !users.containsKey(userId)){
            System.out.println("grp doesn't exist");
            return false;
        }
        return group.addMember(userId);

    }
    public boolean createUser(User user){
        if (users.containsKey(user.getUserId())){
            System.out.println("alreday exits");
            return false;
        }
        users.put(user.getUserId(),user);
        return true;
    }
    public boolean createGroup(Group group){
        if (groups.containsKey(group.getGrpId())) {
            return false;
        }
        for (int userId : group.getMembers()) {
            if (!users.containsKey(userId)) {
                return false;
            }
        }
        groups.put(group.getGrpId(), group);
        return true;
    }
    public boolean addExpense(int grpId,Expense expense){
        if (expense.getSplits().size() < 2) {
            return false;
        }

        if(!groups.containsKey(grpId)){
            return false;
        }
        Group grp=groups.get(grpId);
        if(!grp.hasMember(expense.getPaidBy().getUserId())){
            return false;
        }
        for(int i:expense.getSplits().keySet()){
            if(!grp.hasMember(i)){
                return false;
            }
        }
        int payerId=expense.getPaidBy().getUserId();
        if(expense.getSplitType()==SplitType.EQUAL){
            double amount=(expense.getAmount())/(expense.getSplits().size());

            for(int i:expense.getSplits().keySet()){
                if (i==payerId){
                    continue;
                }
                splitBalance.computeIfAbsent(i,k->new HashMap<>()).merge(payerId,amount,Double::sum);
            }
        }
        if(expense.getSplitType()==SplitType.EXACT){

            for(int user:expense.getSplits().keySet()){
                if(user==payerId){
                    continue;
                }
                double amount=expense.getSplits().get(user);
                splitBalance.computeIfAbsent(user,k->new HashMap<>()).merge(payerId,amount,Double::sum);



            }

        }
        if(expense.getSplitType()==SplitType.PERCENTAGE){
            for(int user:expense.getSplits().keySet()){
                if(user==payerId){
                    continue;
                }
                double perc=expense.getSplits().get(user);
                double amount= expense.getAmount()*perc/100;
                splitBalance.computeIfAbsent(user,k->new HashMap<>()).merge(payerId,amount,Double::sum);



            }

        }
        return true;
    }
    public double showBalanceI(int userId1,int userId2){
        if(userId1==userId2){
            return 0;
        }
        double owe1=splitBalance.getOrDefault(userId1,new HashMap<>()).getOrDefault(userId2,0.0);
        double owe2=splitBalance.getOrDefault(userId2,new HashMap<>()).getOrDefault(userId1,0.0);
        return owe1-owe2;
    }
    public void showBalance(int groupId){
        Group grp= groups.get(groupId);
        if(grp==null){
            return;
        }
        List<Integer> members=new ArrayList<>(grp.getMembers());
        for(int i=0;i<members.size();i++){
            for(int j=i+1;j<members.size();j++){
                int user1 = members.get(i);
                int user2 = members.get(j);
                double balance = showBalanceI(user1, user2);
                if (balance > 0) {
                    System.out.println(
                            user1 + " owes " + user2 + " : " + balance
                    );
                }
                else if (balance < 0) {
                    System.out.println(
                            user2 + " owes " + user1 + " : " + (-balance)
                    );
                }
            }
        }

    }
    public boolean settle(int userId1,int userId2,double amount){
        if(userId1==userId2 || amount<0){
            return false;
        }
        double balance=showBalanceI(userId1,userId2);
        if(balance<=0){
            System.out.println("nothing to settle");
            return false;
        }if(balance<amount){
            System.out.println("Amount more than debt");
            return false;
        }
        Map<Integer,Double> debt=splitBalance.get(userId1);
        double remaining=balance-amount;
        if(remaining==0){
            debt.remove(userId2);
        }
        else{
            debt.put(userId2,remaining);
        }
        return true;

    }
}
