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
public class Account {
    private int balance = 0;
    
    public int getAccBalance(){
        return this.balance;
    }
    
    public void setAccBalance(int amount){
        this.balance = amount;
    }
}
