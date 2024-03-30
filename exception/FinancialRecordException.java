package exception;

import java.io.Serializable;

public class FinancialRecordException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public FinancialRecordException(String message) {
        super(message);
    }
}
