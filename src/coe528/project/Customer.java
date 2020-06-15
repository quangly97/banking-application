/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.util.*;
import java.io.*;

/**
 *
 * @author Quang
 */
public class Customer extends User {

    private Scanner x;
    private Writer wr;
    private Account bankAcc;
    private Level level;

    public Customer(String user) {
        this.bankAcc = new Account();
        this.level = new Silver();

        try {
            x = new Scanner(new File(user + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        int i =0;
        String a[] = new String[5];

        while (x.hasNext()) {
            a[i] = x.nextLine();
            i++;
        }

        x.close();

        this.userName = a[0];
        this.password = a[1];
        this.role = a[2];
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return this.level;
    }

    @Override
    public boolean login(String user, String pass) {
        try {
            x = new Scanner(new File(user + ".txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        String a[] = new String[5];
        int i = 0;
        while (x.hasNext()) {
            a[i] = x.nextLine();
            i++;
        }

        x.close();
        
        if (user.equals(a[0]) && pass.equals(a[1])) {
            System.out.println("Successful login");
            return true;
        } else {
            System.out.println("Incorrect password");
            return false;
        }
    }

    public void deposit(int amount) {
        this.setBalance(this.getBalance() + amount);
        this.level.changeLevel(this);

        updateFile();
    }

    public void withdraw(int amount) {
        if (amount > this.getBalance()) {
            System.out.println("Not enough money");
        }
        if (amount <= this.getBalance()) {
            this.setBalance(this.getBalance() - amount);
            this.level.changeLevel(this);
        }

        updateFile();
    }

    public void purchase(int amount) {
        if (amount > this.getBalance()) {
            System.out.println("Not enough money");
        }

        if (amount >= 50 && amount < this.getBalance()) {
            this.getLevel().purchase(this, amount);
        } else {
            System.out.println("You need to spend more");
        }

        updateFile();
    }

    public int getBalance() {
        return bankAcc.getAccBalance();
    }

    public void setBalance(int amount) {
        bankAcc.setAccBalance(amount);
    }

    public void updateFile() {
        try {
            wr = new FileWriter(userName + ".txt");
            wr.write(this.userName + System.getProperty("line.separator"));
            wr.write(this.password + System.getProperty("line.separator"));
            wr.write("Customer" + System.getProperty("line.separator"));
            wr.write(Integer.toString(this.getBalance()) + System.getProperty("line.separator"));
            wr.write(this.getLevel().getClass().getSimpleName());
            wr.flush();
            wr.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
