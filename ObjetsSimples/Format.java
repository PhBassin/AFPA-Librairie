package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Format {

    private String ID;
    private String formatname;
    private String formatdesc;
    private String genre = "format";
    private int type = 9;


    public Format() {

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
            throw new DataExceptions(9001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public String getFormatname() {
        return formatname;
    }

    public void setFormatname(String formatname) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(formatname);
        if (m.matches()) {
            this.formatname = formatname;
        } else {
            throw new DataExceptions(9002, "Le nom ne doit contenir que des lettres et est limité à 150 caractères", "Name");
        }
    }

    public String getFormatdesc() {
        return formatdesc;
    }

    public void setFormatdesc(String formatdesc) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(formatdesc);
        if (m.matches()) {
            this.formatdesc = formatdesc;
        } else {
            throw new DataExceptions(9003, "La description est limitée à 150 caractères", "Desc");
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
                query = "INSERT INTO FORMAT "
                        + "( FORMATNAME, FORMATDESC) VALUES ";
                query += value;

            } else {
                query = "UPDATE FORMAT "
                        + value + " WHERE FORMATID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Format", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table format", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + formatname + "', ";
            str += "'" + apostrophe(formatdesc) + "')";

        } else {
            str += " FORMATNAME = '" + formatname + "', ";
            str += " FORMATDESC = '" + apostrophe(formatdesc) + "', ";

        }
        return str;
    }
        public String apostrophe(String apostrophe){
        String str ="";
        if(apostrophe != null)
            str = apostrophe.replace("'", "''");
       
        return  str; 
        
    }
}
