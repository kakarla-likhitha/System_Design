package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseManager manager = new ExpenseManager();

        User user1 = new User(1, "Likhi");
        User user2 = new User(2, "Megh");
        User user3 = new User(3, "Rahul");

        manager.createUser(user1);
        manager.createUser(user2);
        manager.createUser(user3);

        Set<Integer> members = new HashSet<>();
        members.add(1);
        members.add(2);
        members.add(3);

        Group group = new Group(101, "Trip", members);

        manager.createGroup(group);

        Map<Integer, Double> splits = new HashMap<>();
        splits.put(1, 300.0);
        splits.put(2, 300.0);
        splits.put(3, 300.0);

        Expense expense = new Expense(
                900.0,
                1,
                user1,
                splits,
                SplitType.EQUAL
        );

        manager.addExpense(101, expense);

        manager.showBalance(101);

        sc.close();
    }
}