/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Quang
 */
public class Platinum extends Level {

    public Platinum() {
        fee = 0;
    }

    @Override
    public void changeLevel(Customer customer) {
        if (customer.getBalance() >= 10000 && customer.getBalance() < 20000) {
            customer.setLevel(new Gold());
        } else if (customer.getBalance() < 10000) {
            customer.setLevel(new Silver());
        }
    }

    @Override
    public void purchase(Customer customer, int amount) {
        customer.setBalance(customer.getBalance() - amount - fee);
        customer.getLevel().changeLevel(customer);

    }
}
