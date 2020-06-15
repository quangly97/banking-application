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
public abstract class Level {
    protected int fee;
    
    public abstract void changeLevel(Customer customer);
    
    public abstract void purchase(Customer customer, int amount);
}
