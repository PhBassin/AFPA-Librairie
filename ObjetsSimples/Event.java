// les exeptions ne sont pas finies (reflechir a la question du taux de reduc ensemble) 
package ObjetsSimples;

import exceptions.DataExceptions;
import java.awt.Image;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Event {

    protected String eventID;
    protected Statut statut;
    protected String name;
    protected String starterDate;
    protected String endingDate;
    protected float discount;
    protected String description;
    protected Image image;
    protected String note;
    protected String genre = "event";
    protected int type = 7;

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Event() {
        this.statut = new Statut();
    }

    public String getEventID() {
        return eventID;
    }

    public Statut getStatus() {
        return statut;
    }

    public String getName() {
        return name;
    }

    public String getStarterDate() {
        return starterDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public float getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public String getGenre() {
        return genre;
    }

    public int getType() {
        return type;
    }

    public void setEventID(String eventID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(eventID);
        if (m.matches()) {
            this.eventID = eventID;
        } else {
            throw new DataExceptions(7001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStarterDate(String starterDate) throws DataExceptions {

        try {
            if (df.parse(starterDate) != null) {
                this.starterDate = starterDate;
            } else {
                throw new DataExceptions(7002, "Cette date n'est pas valide.", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(7002, "Cette date n'est pas valide.", "DATE");
        }
    }

    public void setEndingDate(String endingDate) throws DataExceptions {

        try {
            if (df.parse(endingDate) != null) {
                this.endingDate = endingDate;
            } else {
                throw new DataExceptions(7003, "Cette date n'est pas valide.", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(7003, "Cette date n'est pas valide.", "DATE");
        }
    }

    /*
     // si bas l'ancienne methode de verification de date avec le formt Date et non String 
    

     //    public void setEndingDate(String endingDate) {
     //
     //        do {
     //            try {
     //                if (df.parse((CharSequence) starterDate) != null) {
     //                    this.endingDate = endingDate;
     //                } else {
     //                    System.out.println();
     //                    JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date de fin", JOptionPane.ERROR_MESSAGE);
     //                }
     //            } catch (DateTimeParseException e) {
     //                System.out.println("CECI N'EST PAS UNE DATE");
     //                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date de fin", JOptionPane.ERROR_MESSAGE);
     //            }
     //        } while (true);
     //    } */
    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setDescription(String description) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«:-@!-/0-9a-zA-ZÀ-ȳ]{0,1024}");
        Matcher m = p.matcher(description);
        if (m.matches()) {
            this.description = description;
        } else {
            throw new DataExceptions(7002, "La description ne doit pas dépasser 1024 caractères", "Description");
        }
    }

    public void setImage(Image image) throws DataExceptions {
        Pattern p = Pattern.compile("([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)");
        Matcher m = p.matcher((CharSequence) image);
        if (m.matches()) {
            this.image = image;
        } else {
            throw new DataExceptions(7003, "L'image n'est pas conforme, merci de choisir le format (png, jpg, gif, bmp ou jpeg)", "Image");
        }
    }

    public void setNote(String note) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«:-@!-/0-9a-zA-ZÀ-ȳ]{0,5000}");
        Matcher m = p.matcher(note);
        if (m.matches()) {
            this.note = note;
        } else {
            throw new DataExceptions(7004, "La description ne doit pas dépasser 1024 caractères", "Description");
        }
    }

    /* j'ai supprimer la variable 'statusDate' car elle est apportée par l'objet status

     //   public void setStatusDate(String statusDate) {
     //
     //        do {
     //            try {
     //                if (df.parse((CharSequence) statusDate) != null) {
     //                    this.statusDate = statusDate;
     //                } else {
     //                    System.out.println();
     //                    JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date du statut", JOptionPane.ERROR_MESSAGE);
     //                }
     //            } catch (DateTimeParseException e) {
     //                System.out.println("CECI N'EST PAS UNE DATE");
     //                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date du statut", JOptionPane.ERROR_MESSAGE);
     //            }
     //        } while (true);
     //    } */
    // faire une methode qui gere la question du rabais 
    /*
     La méthode save permet de sauvegarder l'objet dans SQL 
     Elle nécessite un objet echange pour initier la connexion et une string qui correspond à la requête envoyée
     Cette String est générée par la méthode genValue ci-dessous
    
     2 options dans cette requête :
     1- l'objet vient d'être crée et n'existe pas dans SQL , l'addrID est vide donc on crée un nouvel enregistrement
     2- l'objet est déjà dans SQL , le ID n'est pas vide donc on met à jour l'enregistrement
     */
    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.eventID == null) {
                query = "INSERT INTO EVENEMENT "
                        + "(STATUSID, EVENTNAME, EVENTSTARTINGDATE, EVENTENDINGDATE, "
                        + "EVENTDISCOUNT, EVENTDESCR, EVENTPICTURE, EVENTNOTES "
                        + ") VALUES ";
                query += value;

            } else {
                query = "UPDATE EVENEMENT "
                        + value + " WHERE EVENTID= " + this.eventID;
            }

            //System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Grille", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Grille", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    /*
     La méthode genValue sert à générer une partie de la requête necessaire pour la sauvegarde
    
     2 options sont disponibles
     1- l'objet vient d'être crée et n'existe pas dans SQL , l'ID est vide donc on crée un nouvel enregistrement
     2- l'objet est déjà dans SQL , l'ID n'est pas vide donc on met à jour l'enregistrement
     */

    public String genValue() {
        String str = "";
        if (this.eventID == null) {
            str = "(";
            //str += ID + ", ";

            str += statut.getId() + ", "; // il y a une difference entre int et str (pas de simple cote) 
            str += "'" + apostrophe(name) + "', ";
            str += "'" + starterDate + "', ";
            str += "'" + endingDate + "', ";
            str += "" + discount + ", ";
            str += "'" + apostrophe(description) + "', ";
            str += "'" + image + "', ";
            str += "'" + apostrophe(note)+ "') ";

        } else {
            
            str += "SET STATUSID = " + statut.getId() + ", ";
            str += " EVENTNAME = '" + apostrophe(name) + "', ";
            str += " EVENTSTARTINGDATE = '" + starterDate + "', ";
            str += " EVENTENDINGDATE = '" + endingDate + "', ";
            str += " EVENTDISCOUNT = " + discount + ", ";
            str += " EVENTDESCR = '" + apostrophe(description) + "', ";
            str += " EVENTPICTURE = '" + image + "', ";
            str += " EVENTNOTES = '" + apostrophe(note) + "'";
//            str += " STATEVENTDATE" + statut.get + ", "; la variable n'existe pas dans l'objet statut 
//            str += " STATEVENTCOMMENT" + status.getID() + ", ";

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
