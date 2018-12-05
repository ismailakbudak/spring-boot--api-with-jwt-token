package report.service.v3.exception;

import java.rmi.NotBoundException;

public class TransactionNotFoundException extends NotBoundException {
    public TransactionNotFoundException() {
        super("transaction not found");
    }
}
