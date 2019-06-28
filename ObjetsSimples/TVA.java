package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class TVA {

    private String ID;
    private String vatType;
    private String rate;
    private int type = 20;
    private String genre = "TVA";

    public TVA() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(ID);
        if (m.matches()) {
            this.ID = ID;
        } else {
            throw new DataExceptions(14001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(vatType);
        if (m.matches()) {
            this.vatType = vatType;
        } else {
            throw new DataExceptions(14002, "Le type de TVA ne doit contenir que des lettres et est limité à 150 caractères", "VAT Type");
        }
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) throws DataExceptions {
        Pattern p = Pattern.compile("\\d{1,3}[.]?(\\d{1,2})?");
        Matcher m = p.matcher(rate);
        if (m.matches()) {
            this.rate = rate;
        } else {
            throw new DataExceptions(14003, "Le taux doit respecter le format '999.99'", "Rate");
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return ID + " " + vatType + " " + rate + " " + type + " " + genre;
    }
    
    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == null) {
                query = "INSERT INTO TVA "
                        + "(VATTYPE, VATTAUX) VALUES ";
                query += value;

            } else {
                query = "UPDATE TVA "
                        + value + " WHERE VATID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "TVA", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table TVA", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + vatType + "', ";
            str += "" + rate + ")";

        } else {
            str += "SET VATTYPE = '" + vatType + "', ";
            str += " VATTAUX = " + rate + "";

        }
        return str;
    }

}
