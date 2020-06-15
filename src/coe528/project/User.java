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
public abstract class User {
    protected String userName;
    protected String password;
    protected String role;

    public abstract boolean login(String user, String pass);
    
    public static void logout(){
        System.out.println("Logged out successful");
    }
    
    public String getUsername(){
        return this.userName;
    }
    
    public String getPassword(){
        return this.password;
    }
}
