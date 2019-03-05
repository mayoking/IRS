/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.vo;

/**
 *
 * @author tkola
 */
public class AuthVO {

    /**
     * @return the isAuthform
     */
    public boolean isIsAuthform() {
        return isAuthform;
    }

    /**
     * @return the isPinform
     */
    public boolean isIsPinform() {
        return isPinform;
    }

    /**
     * @param isAuthform the isAuthform to set
     */
    public void setIsAuthform(boolean isAuthform) {
        this.isAuthform = isAuthform;
    }

    /**
     * @param isPinform the isPinform to set
     */
    public void setIsPinform(boolean isPinform) {
        this.isPinform = isPinform;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
    private String password;   
    private String token;
    private boolean isPinform;
    private boolean isAuthform;
}
