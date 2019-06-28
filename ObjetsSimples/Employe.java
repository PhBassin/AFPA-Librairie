package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Employe {
    
    protected String loginID;
    private Statut status;
    protected String lastName;
    protected String firstName;
    protected String passWord;
    protected String statusDate;
    protected String note;
    protected String genre = "employe";
    protected int type = 4;
    
    public Employe() {
        this.status = new Statut();
    }
    
    public String getLoginID() {
        return loginID;
    }
    
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    
    public Statut getStatus() {
        return status;
    }
    
    public void setStatus(Statut status) {
        this.status = status;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) throws DataExceptions {
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
    
    public void setFirstName(String firstName) throws DataExceptions {
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
    
    public void setStatusDate(String statusDate) throws DataExceptions {
        Pattern p = Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1])(-|\\/)(0[1-9]|1[0-2])(-|\\/)[0-9]{4} (2[0-3]|[01][0-9]):[0-5][0-9]");
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
    
    public void save(Xchange echange, String value) {
        Statement stat = null;
        
        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.loginID == null) {
                query = "INSERT INTO EDITEUR "
                        + "( STATUSID, EMPLASTNAME, EMPFIRSTNAME, EMPPASSWORD, COMMENTSTATUSDATE, AUTHORNOTES ) VALUES ";
                query += value;
                
            } else {
                query = "UPDATE CLIENT "
                        + value + " WHERE CUSTOMERID = " + this.loginID;
            }

            //System.out.println(query);
            stat.executeUpdate(query);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Employe", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Employe", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public String genValue() {
        String str = "";
        if (this.loginID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + status.getId() + "', ";
            str += "'" + apostrophe(lastName) + "', ";
            str += "'" + apostrophe(firstName) + "', ";
            str += "'" + passWord + "', ";
            if (statusDate == null) {
                str += "" + statusDate + ", ";
            } else {
                str += "'" + statusDate + "', ";
            }
            str += "'" + apostrophe(note) + "') ";
            
        } else {
            str += "SET STATUSID = '" + status.getId() + "', ";
            str += " EMPLASTNAME = '" + apostrophe(lastName) + "', ";
            str += " EMPFIRSTNAME = '" + apostrophe(firstName) + "', ";
            str += " EMPPASSWORD = '" + passWord + "', ";
            if (statusDate != null) {
                str += " COMMENTSTATUSDATE = '" + statusDate + "'";
            }
            str += " AUTHORNOTES = '" + apostrophe(note) + "'";
            
        }
        return str;
    }
    
    public String apostrophe(String apostrophe) {
        String str = "";
        if (apostrophe != null) {
            str = apostrophe.replace("'", "''");
        }
        
        return str;
        
    }
}
