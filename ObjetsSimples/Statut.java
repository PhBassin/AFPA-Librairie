package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Statut {
    
    private String ID;
    private String statusname;
    private String statusdescr;
    private String genre = "Status";
    private int type = 17;
    
    public Statut() {
        
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
            throw new DataExceptions(17001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }
    
    public String getStatusname() {
        return statusname;
    }
    
    public void setStatusname(String statusname) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(statusname);
        if (m.matches()) {
            this.statusname = statusname;
        } else {
            throw new DataExceptions(17002, "Le nom ne doit contenir que des lettres et est limité à 150 caractères", "Name");
        }
    }
    
    public String getStatusdescr() {
        return statusdescr;
    }
    
    public void setStatusdescr(String statusdescr) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(statusdescr);
        if (m.matches()) {
            this.statusdescr = statusdescr;
        } else {
            throw new DataExceptions(17003, "La description est limitée à 150 caractères", "Desc");
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
                query = "INSERT INTO STATUS "
                        + "( STATUSNAME, STATUS_DESCR) VALUES ";
                query += value;
                
            } else {
                query = "UPDATE STATUS "
                        + value + " WHERE FORMATID = " + this.ID;
            }
            
            System.out.println(query);
            stat.executeUpdate(query);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Adresse", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Adresse", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + statusname + "', ";
            str += "'" + apostrophe(statusdescr) + "')";
            
        } else {
            str += " STATUSNAME = '" + statusname + "', ";
            str += " STATUS_DESCR = '" + apostrophe(statusdescr) + "', ";
            
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
