package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class LigneCmd {

    private int type = 11;
    private String genre = "lignecmd";
    private String ID;
    private Commande commande;
    private Livre livre;
    private Client client;
    private String quantite;
    private String prixHT;
    private String tva;

    public LigneCmd() {
        this.commande = new Commande();
        this.livre = new Livre();
        this.client = new Client();
    }

    public String getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(String prixHT) throws DataExceptions {

        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(prixHT);
        if (m.matches()) {
            this.prixHT = prixHT;
        } else {
            throw new DataExceptions(11022, "Le prix HT ne peut contenir que des chiffres", "Prix HT");
        }
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(quantite);
        if (m.matches()) {
            this.quantite = quantite;
        } else {
            throw new DataExceptions(11002, "La quantitée ne peut contenir que des chiffres", "Quantité");
        }

    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(tva);
        if (m.matches()) {
            this.tva = tva;
        } else {
            throw new DataExceptions(11012, "La TVA ne peut contenir que des chiffres", "TVA");
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
            if (this.ID == null) {
                query = "INSERT INTO LIGNECMD "
                        + "(ORDERID, BOOKISBN13, CUSTOMERID, LINEQUANTITY,"
                        + " LINEPRICEHT, LINEVAT) VALUES ";
                query += value;

            } else {
                query = "UPDATE LIGNECMD "
                        + value + " WHERE LINEID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "LigneCMD", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table LIGNECMD", JOptionPane.ERROR_MESSAGE);
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
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";

            str += commande.getID() + ", ";

            str += "'" + livre.getIsbn13() + "', ";

            str += client.getId() + ", ";

            str += "" + quantite + ", ";
            str += "" + prixHT +", ";
            str += "" + tva + ")";
  

        } else {
            str += "SET ORDERID = " + commande.getID() + ", ";
            str += " BOOKISBN13 = '" + livre.getIsbn13() + "', ";
            str += " CUSTOMERID = " + client.getId() + ", ";
            str += " LINEQUANTITY = " + quantite + ", ";
            str += " LINEPRICEHT = " + prixHT + ", ";
            str += " LINEVAT = " + tva ;


        }
        return str;
    }

}
