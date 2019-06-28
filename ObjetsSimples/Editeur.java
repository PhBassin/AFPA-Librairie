package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Editeur {

    private String ID;
    private Statut status;
    private String name;
    private String mail;
    private String tel;
    private String notes;
    private String genre = "editeur";
    private int type = 1;


    public Editeur() {
        this.status = new Statut();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'éè^éàûôa-zA-Z]{1,100}");
        Matcher m = p.matcher(name);
        if (name.isEmpty()) {
            throw new DataExceptions(1001, "Le nom ne peut pas être vide", "Nom");
        } else if (m.matches()) {
            this.name = name;
        } else {
            throw new DataExceptions(1002, "Le nom ne peut contenir que des lettres", "Nom");
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) throws DataExceptions {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9+][0,9 ]{9,14}");
        Matcher m = p.matcher(tel);
        if (tel.isEmpty()) {
            throw new DataExceptions(1003, "Le tel ne peut pas être vide", "Téléphone");
        } else if (m.matches()) {
            this.tel = tel;
        } else {
            throw new DataExceptions(1004, "Le nom ne peut contenir que des lettres "
                    + "et/ou des espaces et + pour le format international", "Téléphone");
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{0,150}");
        Matcher m = p.matcher(notes);
        if (m.matches()) {
            this.notes = notes;
        } else {
            throw new DataExceptions(1005, "La description est limitée à 150 caractères", "Desc");
        }
    }

    public String getID() {
//        if(ID == null){
//            //System.out.println(ID);
//            return "";
//        }
//        else
            return ID;
    }

    public void setID(String ID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(ID);
        if (m.matches()) {
            this.ID = ID;
        } else {
            throw new DataExceptions(1006, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public Statut getStatus() {
        return status;
    }

    public void setStatus(Statut status) {
        this.status = status;
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
                query = "INSERT INTO EDITEUR "
                        + "( STATUSID, EDITEUR_NOM, PUBLISHERMAIL, PUBLISHERPHONE, PUBLISHERNOTES) VALUES ";
                query += value;

            } else {
                query = "UPDATE CLIENT "
                        + value + " WHERE CUSTOMERID = " + this.ID;
            }

            //System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Editeur", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Editeur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + status.getId() + "', ";
            str += "'" + apostrophe(name) + "', ";
            str += "'" + mail + "', ";
            str += "'" + tel + "', ";
            str += "'" + apostrophe(notes) + "') ";

        } else {
            str += "SET STATUSID = '" + status.getId() + "', ";
            str += " EDITEUR_NOM = '" + apostrophe(name) + "', ";
            str += " PUBLISHERMAIL = '" + mail + "', ";
            str += " PULISHERPHONE = '" + tel + "', ";
            str += " PUBLISHERNOTES = '" + apostrophe(notes) + "'";

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
