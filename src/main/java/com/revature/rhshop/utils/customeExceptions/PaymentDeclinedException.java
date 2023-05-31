package com.revature.rhshop.utils.customeExceptions;

public class PaymentDeclinedException extends Exception {

    public PaymentDeclinedException(){
        super("Payment Declined! Please Retry Again ...");
    }
    
}
