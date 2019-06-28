package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Grille {
    
    protected String gridID;
    protected Editeur editeur;
    protected String gridCat;
    protected String gridPrice;
    protected String genre = "grille";
    protected int type = 10;

    public Grille() {
        this.editeur= new Editeur();
    }

    public String getGridID() {
        return gridID;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public String getGridCat() {
        return gridCat;
    }

    public String getGridPrice() {
        return gridPrice;
    }

    public String getGenre() {
        return genre;
    }

    public int getType() {
        return type;
    }

    public void setGridID(String gridID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(gridID);
        if (m.matches()) {
           this.gridID = gridID;
        } else {
            throw new DataExceptions(10001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }
       
   

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public void setGridCat(String gridCat) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z0-9]{0,50}");
        Matcher m = p.matcher(gridCat);
        if (m.matches()) {
            this.gridCat = gridCat;
        } else {
            throw new DataExceptions(10002, "La catégorie ne doit pas dépasser 50 caractères", "Grille catégorie");
        }
    }
        
    

    public void setGridPrice(String gridPrice) throws DataExceptions {
        Pattern p = Pattern.compile("\\d{1,10}[.]?(\\d{1,2})?");
        Matcher m = p.matcher(gridPrice);
        if (m.matches()) {
           this.gridPrice = gridPrice;
        } else {
            throw new DataExceptions(10003, "Le prix doit respecter le format '999.99'", "Grille Prix");
        }
    }
    
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
            if (this.gridID == null) {
                query = "INSERT INTO GRILLE "
                        + "(PUBLISHERID, GRIDCAT, GRIDPRICE) VALUES ";
                query += value;

            } else {
                query = "UPDATE GRILLE "
                        + value + " WHERE GRIDID = " + this.gridID;
            }

            System.out.println(query);
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
        if (this.gridID == null) {
            str = "(";
            //str += ID + ", ";

            str += editeur.getID() + ", "; // il y a une difference entre int et str (pas de simple cote) 
            str += "'" + gridCat + "', ";
            str += "" + gridPrice + ") ";
           

        } else {
           
            str += "SET PUBLISHERID = " + editeur.getID() + ", ";
            str += " GRIDPRICE = " + gridPrice + ", ";             
            str += " GRIDCAT = '" + gridCat + "'";

        }
        return str;
    }
    
}
