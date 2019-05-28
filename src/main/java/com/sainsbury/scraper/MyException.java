package com.sainsbury.scraper;

public class MyException extends Exception {
    private String stringException;
    MyException(String str) {
        this.stringException=str;
    }
    public String toString(){
        return ("Exception occured: " + stringException);
    }
}
