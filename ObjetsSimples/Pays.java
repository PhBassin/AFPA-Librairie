package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Pays {

    private String countryID;
    private String name;
    private String A2;
    private String A3;
    private String number;
    private int type = 22;
    private String genre = "pays";

    public Pays() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[-a-zA-Z]{1,50}");
        Matcher m = p.matcher(name);
        if (m.matches()) {
            this.name = name;
        } else {
            throw new DataExceptions(22002, "Le nom de pays ne peut contenir que des lettres", "Nom de Pays");
        }

    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryID() {
        return countryID;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String A2) throws DataExceptions {
        Pattern p = Pattern.compile("[A-Z]{2}");
        Matcher m = p.matcher(A2);
        if (m.matches()) {
            this.A2 = A2;
        } else {
            throw new DataExceptions(22012, "Le A2 de pays ne peut contenir que 2 lettres majuscule", "A2 de Pays");
        }

    }

    public String getA3() {
        return A3;
    }

    public void setA3(String A3) throws DataExceptions {
        Pattern p = Pattern.compile("[A-Z]{3}");
        Matcher m = p.matcher(A3);
        if (m.matches()) {
            this.A3 = A3;
        } else {
            throw new DataExceptions(22022, "Le A3 de pays ne peut contenir que 3 lettres majuscules", "A3 de Pays");
        }

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{3}");
        Matcher m = p.matcher(number);
        if (m.matches()) {
            this.number = number;
        } else {
            throw new DataExceptions(22032, "Le Number de pays ne peut contenir que 3 chiffres", "Number de Pays");
        }

    }

    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.countryID == null) {
                query = "INSERT INTO PAYS "
                        + "(Pays, A2, A3, Number) VALUES ";

                query += value;

            } else {
                query = "UPDATE PAYS "
                        + value + " WHERE COUNTRYID = " + this.countryID;
            }

            //System.out.println(query);
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
        if (this.countryID == null) {
            str = "(";
            str += "'" + name + "', ";
            str += "'" + A2 + "', ";
            str += "'" + A3 + "', ";
            str += "" + number + ")";

        } else {

            str += "SET Pays = '" + name + "', ";
            str += " A2 = '" + A2 + "', ";
            str += " A3 = '" + A3 + "', ";
            str += " Number = " + number ;

        }
        return str;
    }

}
