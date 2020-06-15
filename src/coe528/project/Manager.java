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
public class Manager extends User {
    // OVERVIEW
    // Manager is a mutable class
    // A manager class creates and deletes Customers and keeps a record of the customers

    private ArrayList<Customer> customer = new ArrayList<>();   // the representation
    private static Manager instance = null;
    private Writer wr;
    private Scanner x;

    //Abstraction Function
    //A set of unique Customer usernames required to log in i.e no identical usernames
    //AF(c) = {ci = c.customer[i].getUsername() | 0 <= i < c.customer.size()} 
    //Rep Invariant
    //True if all elements of the list of customers must have a unique username
    //RI(c) = c.customer != null &&
    //c.customer[i].getUsername() ! = c.customer[j].getUsername() | 0 <= i < j < c.customers.size()
    private Manager() {
        //EFFECTS: Initializes a Manager object
        userName = "admin";
        password = "admin";
        role = "Manager";
    }

    public static Manager getInstance() {
        //REQUIRES: instance == null
        //EFFECTS: Creates a Singleton Manager object. i.e Only one Manager object can be instantiated
        if (instance == null) {
            instance = new Manager();
        } else {
            System.out.println("Only 1 manager allowed");
        }
        return instance;
    }

    @Override
    public boolean login(String user, String pass) {
        //REQUIRES: The Customer's username exists
        //EFFECTS: A Manager's username, password and role are authenticated and returns true if the input matches
        if (user.equals(this.userName) && pass.equals(this.password) && role.equals("Manager")) {
            System.out.println("Login successful");
            return true;
        } else {
            System.out.println("Incorrect login");
            return false;
        }
    }

    public void add(String user, String pass) {
        //EFFECTS: A Customer's profile is created and stored into a file and the list. The Customer is deposited $100.
        if (customer.contains(getCustomer(user)) || user.equalsIgnoreCase("admin")) {
            System.out.println("The username is already taken");
            return;
        } else {
            try {
                wr = new FileWriter(user + ".txt");
                wr.write(user + System.getProperty("line.separator"));
                wr.write(pass + System.getProperty("line.separator"));
                wr.write("Customer");
                wr.flush();
                wr.close();
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }

            Customer x = new Customer(user);
            x.deposit(100);
            customer.add(x);
            x.updateFile();
            System.out.println("Therefore " + user + " has successfully been added to the Customer list");
            System.out.println(toString());

        }
    }

    public void delete(String user) throws NullPointerException {
        //REQUIRES: customer != null
        //EFFECTS: If customer = null throws NullPointerException else a Customer with the specified username is deleted from the list.
        File file = new File(user + ".txt");
        boolean check = false;
        if (file.delete()) {
            System.out.println(user + " successfully deleted");
        }

        if (customer != null) {
            for (int i = 0; i < customer.size(); i++) {
                if (customer.get(i).getUsername().equals(user)) {
                    check = true;
                    customer.remove(i);
                    break;
                }
            }
            System.out.println(toString());
        } else {
            throw new NullPointerException("No customers");
        }

        if (check == false) {
            System.out.println("Username does not exist");
        }
    }

    public Customer getCustomer(String user) throws NullPointerException {
        //REQUIRES: customer != null
        //EFFECTS: If customer = null throw NullPointerException else returns the specified Customer from the list
        Customer a = null;
        try {
            for (int i = 0; i < customer.size(); i++) {
                if (customer.get(i).getUsername().equals(user)) {
                    a = customer.get(i);
                    return a;
                }
            }
            if (a == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("The user is not in the Customer list");
        }
        return a;
    }

    public void inputCustomers(final File folder) {
        //EFFECTS: Manager will get all registered customers, instantiate them and put it into the Customer list
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().endsWith(".txt") == true && !(fileEntry.getName().equalsIgnoreCase("admin.txt"))){
                try {
                    x = new Scanner(new File(fileEntry.getName()));
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }

                int i = 0;
                String a[] = new String[5];

                while (x.hasNext()) {
                    a[i] = x.nextLine();
                    i++;
                }

                x.close();
                
                customer.add(new Customer(a[0]));
                Customer temp = getCustomer(a[0]);
                temp.setBalance(Integer.parseInt(a[3]));
                temp.getLevel().changeLevel(temp);
            }
        }
    }

    public String toString() {
        //EFFECTS: Prints out the customer list usernames
        String a = "Customer list: ";
        for (int i = 0; i < customer.size(); i++) {
            a += customer.get(i).getUsername() + " , ";
        }
        return a;
    }

    public boolean repOK() {
        //EFFECTS: Returns true if customer list is not null and all usernames are unique
        if (customer == null) {
            return false;
        }

        for (int j = 1; j < customer.size(); j++) {
            for (int i = 0; i < j; i++) {
                if (customer.get(i).getUsername().equals(customer.get(j).getUsername())) {
                    return false;
                }
            }
        }
        return true;
    }
}
