package ObjetsSimples;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Client {
    
    private String ID;
    private Statut status;
    private String customerlastname;
    private String customerfirstname;
    private String customerbirthdate;
    private String customerregistrationdate;
    private String customermail;
    private String customerphone;
    private String genre = "client";
    private int type = 5;
    
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Client() {
        this.status = new Statut();
    }
    
    public String getId() {
        return ID;
    }
    
    public void setId(String id) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(id);
        if (m.matches()) {
            this.ID = id;
        } else {
            throw new DataExceptions(5001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }
    
    public Statut getStatus() {
        return status;
    }
    
    public void setStatus(Statut status) {
        this.status = status;
    }
    
    public String getCustomerlastname() {
        return customerlastname;
    }
    
    public void setCustomerlastname(String customerlastname) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'éè^éàûôa-zA-Z]{1,100}");
        Matcher m = p.matcher(customerlastname);
        if (customerlastname.isEmpty()) {
            throw new DataExceptions(5002, "Le nom ne peut pas être vide", "Nom");
        } else if (m.matches()) {
            this.customerlastname = customerlastname;
        } else {
            throw new DataExceptions(5003, "Le nom ne peut contenir que des lettres", "Nom");
        }
    }
    
    public String getCustomerfirstname() {
        return customerfirstname;
    }
    
    public void setCustomerfirstname(String customerfirstname) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'éè^éàûôa-zA-Z]{1,100}");
        Matcher m = p.matcher(customerfirstname);
        if (customerfirstname.isEmpty()) {
            throw new DataExceptions(5004, "Le nom ne peut pas être vide", "Nom");
        } else if (m.matches()) {
            this.customerfirstname = customerfirstname;
        } else {
            throw new DataExceptions(5005, "Le nom ne peut contenir que des lettres", "Nom");
        }
    }
    
    public String getCustomerbirthdate() {
        return customerbirthdate;
    }
    
    public void setCustomerbirthdate(String customerbirthdate) throws DataExceptions {
        
        try {
            if (df.parse(customerbirthdate) != null) {
                this.customerbirthdate = customerbirthdate;
            } else {
                throw new DataExceptions(5006, "CECI N'EST PAS UNE DATE", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(5006, "CECI N'EST PAS UNE DATE", "DATE");
        }
        
    }
    
    public String getCustomerregistrationdate() {
        
        return customerregistrationdate;
    }
    
    public void setCustomerregistrationdate(String customerregistrationdate) throws DataExceptions {
        
        try {
            if (df.parse(customerregistrationdate) != null) {
                this.customerregistrationdate = customerregistrationdate;
            } else {
                throw new DataExceptions(5006, "CECI N'EST PAS UNE DATE", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(5006, "CECI N'EST PAS UNE DATE", "DATE");
        }
    }
    
    public String getCustomermail() {
        return customermail;
    }
    
    public void setCustomermail(String customermail) {
        this.customermail = customermail;
    }
    
    public String getCustomerphone() {
        return customerphone;
    }
    
    public void setCustomerphone(String customerphone) throws DataExceptions {
        Pattern p = Pattern.compile("[- .0-9+][0,9 ]{9,14}");
        Matcher m = p.matcher(customerphone);
        if (customerphone.isEmpty()) {
            throw new DataExceptions(5003, "Le tel ne peut pas être vide", "Téléphone");
        } else if (m.matches()) {
            this.customerphone = customerphone;
        } else {
            throw new DataExceptions(5004, "Le nom ne peut contenir que des lettres "
                    + "et/ou des espaces et + pour le format international", "Téléphone");
        }
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public void save(Xchange echange, String value) {
        Statement stat = null;
        
        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == null) {
                query = "INSERT INTO CLIENT "
                        + "(STATUSID, CUSTOMERLASTNAME, CUSTOMERFIRSTNAME, CUSTOMERBIRTHDATE, CUSTOMERREGISTRATIONDATE,"
                        + " CUSTOMERMAIL, CUSTOMERPHONE) VALUES ";
                query += value;
                
            } else {
                query = "UPDATE CLIENT "
                        + value + " WHERE CUSTOMERID = " + this.ID;
            }
            
            System.out.println(query);
            stat.executeUpdate(query);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Client", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Client", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";

            str += status.getId() + ", ";
            str += "'" + apostrophe(customerlastname) + "', ";
            str += "'" + apostrophe(customerfirstname) + "', ";
            if (customerbirthdate == null) {
                str += "" + customerbirthdate + ", ";
            } else {
                str += "'" + customerbirthdate + "', ";
            }
            if (customerregistrationdate == null) {
                str += "" + customerbirthdate + ", ";
            } else {
                str += "'" + customerregistrationdate + "', ";
            }
            str += "'" + customermail + "', ";
            str += "'" + customerphone + "')";
            
        } else {
            str += "SET STATUSID = " + status.getId() + ", ";
            str += " CUSTOMERLASTNAME = '" + apostrophe(customerlastname) + "', ";
            str += " CUSTOMERRFIRSTNAME = '" + apostrophe(customerfirstname) + "', ";
            if (customerbirthdate != null) {
                str += " CUSTOMERBIRTHDATE = '" + customerbirthdate + "', ";
            }
            if (customerregistrationdate != null) {
                str += " CUSTOMERREGISTRATIONDATE = '" + customerregistrationdate + "', ";
            }
            str += " CUSTOMERMAIL = '" + customermail + "'";
            str += " CUSTOMERPHONE = '" + customerphone + "'";
            
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
