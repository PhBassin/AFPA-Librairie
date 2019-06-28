package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Theme {

    private String ID;
    private String name;
    private String desc;
    private int type = 18;
    private String genre = "theme";

    public Theme() {
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
            throw new DataExceptions(3001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(name);
        if (m.matches()) {
            this.name = name;
        } else {
            throw new DataExceptions(3002, "Le nom ne doit contenir que des lettres et est limité à 150 caractères", "Name");
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{0,150}");
        Matcher m = p.matcher(desc);
        if (m.matches()) {
            this.desc = desc;
        } else {
            throw new DataExceptions(3003, "La description est limitée à 150 caractères", "Desc");
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
        return ID + " " + name + " " + desc + " " + type + " " + genre;
    }
    
    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == null) {
                query = "INSERT INTO THEME "
                        + "(THEMENAME, THEMEDESC) VALUES ";
                query += value;

            } else {
                query = "UPDATE THEME "
                        + value + " WHERE THEMEID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Theme", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Theme", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + apostrophe(name) + "', ";
            str += "'" + apostrophe(desc) + "')";

        } else {
            str += "SET THEMENAME = '" + apostrophe(name) + "', ";
            str += " THEMEDESC = '" + apostrophe(desc) + "'";

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
