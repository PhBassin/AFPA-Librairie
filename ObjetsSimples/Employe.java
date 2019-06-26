package ObjetsSimples;

import exceptions.DataExceptions;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Employe {
    
   protected String loginID;
   protected String status; 
   protected String lastName; 
   protected String firstName;
   protected String passWord;
   protected String note; 
   protected String statusDate; 
   protected String genre = "employe"; 
   protected int type = 4; 

    public Employe() {
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws DataExceptions{
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,100}");
        Matcher m = p.matcher(lastName);
        if (m.matches()) {
        this.lastName = lastName;
        } else {
            throw new DataExceptions(4001, "Le nom de famille ne peut contenir que des lettres", "Nom de famille");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws DataExceptions{
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,100}");
        Matcher m = p.matcher(lastName);
        if (m.matches()) {
        this.firstName = firstName;
        } else {
            throw new DataExceptions(4002, "Le prénom ne peut contenir que des lettres", "Prénom");
        }
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatusDate() {
        
        return statusDate;
    }

    public void setStatusDate(String statusDate) throws DataExceptions{
        Pattern p = Pattern.compile
        ("(0[1-9]|[1-2][0-9]|3[0-1])(-|\\/)(0[1-9]|1[0-2])(-|\\/)[0-9]{4} (2[0-3]|[01][0-9]):[0-5][0-9]");
        Matcher m = p.matcher(statusDate);
        if (m.matches()) {
        this.statusDate = statusDate;
        } else {
            throw new DataExceptions(4003, "La date doit respecter le format 'jj-mm-aaaa hh:mm'", "Date");
        }
    }

    public String getGenre() {
        return genre;
    }

    public int getType() {
        return type;
    }

      
}
