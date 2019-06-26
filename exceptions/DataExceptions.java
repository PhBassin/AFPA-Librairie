package exceptions;

import javax.swing.JOptionPane;

public class DataExceptions extends Exception{

    private int errorCode;
    private String message;
    private String origin;

    public DataExceptions() {
    }

    public DataExceptions(String message) {
        super(message);
    }

    public DataExceptions(int CodeErreur, String message, String origin) {
        super(message);
        this.errorCode = CodeErreur;
        this.message = message;
        this.origin = origin;
    }

    public int getErrorCode() {
        return errorCode;
    }
    
    public void msgPrint(){
        JOptionPane.showMessageDialog(null, message, origin, JOptionPane.WARNING_MESSAGE);
    }

}
