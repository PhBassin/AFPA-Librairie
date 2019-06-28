package objets;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class MotClef {

    private String ID;
    private String name;
    private String comment;
    private int type = 14;
    private String genre = "motclef";

    public MotClef() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(name);
        if (m.matches()) {
            this.name = name;
        } else {
            throw new DataExceptions(14002, "Le nom est limité à 100 caractères", "Name");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{0,150}");
        Matcher m = p.matcher(comment);
        if (m.matches()) {
            this.comment = comment;
        } else {
            throw new DataExceptions(14003, "Les commentaires sont limités à 150 caractères", "Comment");
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
        return ID + " " + name + " " + comment + " " + type + " " + genre;
    }

    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == null) {
                query = "INSERT INTO MOTCLEF "
                        + "(KEYWORDNAME, KEYWORDCOMMENT) VALUES ";
                query += value;

            } else {
                query = "UPDATE MOTCLEF "
                        + value + " WHERE KEYWORDID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Mot Clef", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Mot Clef", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + name + "', ";
            str += "'" + apostrophe(comment) + "')";

        } else {
            str += "SET KEYWORDNAME = '" + name + "', ";
            str += " KEYWORDCOMMENT = '" + apostrophe(comment) + "'";

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
