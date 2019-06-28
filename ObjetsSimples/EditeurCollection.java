package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class EditeurCollection {

    private int type = 8;
    private String genre = "editeurcollection";
    private String publisherCollID;
    private Editeur publisher;
    private Statut status;
    private String publisherCollName;

    public Statut getStatus() {
        return status;
    }

    public void setStatus(Statut status) {
        this.status = status;
    }

    public EditeurCollection() {
        this.publisher = new Editeur();
        this.status = new Statut();
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisherCollID() {
        return publisherCollID;
    }

    public void setPublisherCollID(String publisherCollID) {
        this.publisherCollID = publisherCollID;
    }

    public Editeur getPublisher() {
        return publisher;
    }

    public void setPublisher(Editeur publisher) {
        this.publisher = publisher;
    }

    public String getPublisherCollName() {
        return publisherCollName;
    }

    public void setPublisherCollName(String publisherCollName) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{1,150}");
        Matcher m = p.matcher(publisherCollName);
        if (m.matches()) {
            this.publisherCollName = publisherCollName;
        } else {
            throw new DataExceptions(8002, "Le nom de la collection editeur ne peut contenir que des lettres (max 150)", "nom de collection");
        }

    }
    
     /*
     La méthode save permet de sauvegarder l'objet dans SQL
     Elle nécessite un objet echange pour initier la connexion et une string qui correspond à la requête envoyée
     Cette String est générée par la méthode genValue ci-dessous
    
     2 options dans cette requête :
     1- l'objet vient d'être crée et n'existe pas dans SQL , l'addrID est vide donc on crée un nouvel enregistrement
     2- l'objet est déjà dans SQL , l'addrID n'est pas vide donc on met à jour l'enregistrement
     */

    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.publisherCollID == null) {
                query = "INSERT INTO EDITEURCOLLECTION "
                        + "(PUBLISHERID, PUBLISHERCOLLNAME) VALUES ";
                query += value;

            } else {
                query = "UPDATE EDITEURCOLLECTION "
                        + value + " WHERE PUBLISHERCOLLID = " + this.publisherCollID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Editeur Collection", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Editeur Collection", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    /*
     La méthode genValue sert à générer une partie de la requête necessaire pour la sauvegarde
    
     2 options sont disponibles
     1- l'objet vient d'être crée et n'existe pas dans SQL , l'addrID est vide donc on crée un nouvel enregistrement
     2- l'objet est déjà dans SQL , l'addrID n'est pas vide donc on met à jour l'enregistrement
     */

    public String genValue() {
        String str = "";
        if (this.publisherCollID == null) {
            str = "(";

            str += publisher.getID() + ", ";
            str += "'" + apostrophe(publisherCollName) + "')";


        } else {
            
            str += "SET PUBLISHERID = " + publisher.getID() + ", ";
            str += " PUBLISHERCOLLNAME = '" + apostrophe(publisherCollName) + "'";


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
