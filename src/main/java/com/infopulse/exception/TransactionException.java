package com.infopulse.exception;

import com.infopulse.dao.transaction.TransactionManager;

public class TransactionException extends RuntimeException {

    public TransactionException(){
        super();
    }
    public TransactionException(Exception ex){
        super(ex);
    }
}
