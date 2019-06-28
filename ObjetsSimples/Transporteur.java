package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Transporteur {

    protected String transpID;
    protected Statut transpStatut;
    protected String fraisLivraison;
    protected String transpName;
    protected String transpLogo;
    protected String transpMail;
    protected String transpPhone;
    protected String transpNote;

    protected String genre = "transporteur";
    protected int type = 19;

    public Transporteur() {
        this.transpStatut = new Statut();
    }

    public String getID() {
        return transpID;
    }

    public Statut getStatut() {
        return transpStatut;
    }

    public String getFraisLivraison() {
        return fraisLivraison;
    }

    public String getName() {
        return transpName;
    }

    public String getLogo() {
        return transpLogo;
    }

    public String getTranspMail() {
        return transpMail;
    }

    public String getTranspPhone() {
        return transpPhone;
    }

    public String getNote() {
        return transpNote;
    }

    public String getGenre() {
        return genre;
    }

    public int getType() {
        return type;
    }

    public void setID(String transpID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(transpID);
        if (m.matches()) {
            this.transpID = transpID;
        } else {
            throw new DataExceptions(19001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public void setStatut(Statut transpStatut) {
        this.transpStatut = transpStatut;
    }

    public void setFraisLivraison(String fraisLivraison) throws DataExceptions {
        Pattern p = Pattern.compile("\\d{1,10}[.]?(\\d{1,2})?");
        Matcher m = p.matcher(fraisLivraison);
        if (m.matches()) {
            this.fraisLivraison = fraisLivraison;
        } else {
            throw new DataExceptions(19002, "Le nom de famille ne peut contenir que des lettres", "Nom de famille");
        }
    }

    public void setName(String transpName) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«:-@!-/0-9À-ȳa-zA-Z]{1,150}");
        Matcher m = p.matcher(transpName);
        if (m.matches()) {
            this.transpName = transpName;
        } else {
            throw new DataExceptions(1903, "Le nom n'est pas conforme", "Nom du transporteur");
        }

    }

    public void setLogo(String transpLogo) {
        this.transpLogo = transpLogo;
    }

    public void setTranspMail(String transpMail) throws DataExceptions {
        Pattern p = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$");
        Matcher m = p.matcher(fraisLivraison);
        if (m.matches()) {
            this.transpMail = transpMail;
        } else {
            throw new DataExceptions(19004, "L'adresse mail doit respecter le format aa@bbbb.cc", "Adresse Mail transporteur");
        }
    }
        
    
    public void setTranspPhone(String transpPhone) throws DataExceptions {
        Pattern p = Pattern.compile("[- .0-9+][0,9 ]{9,14}");
        Matcher m = p.matcher(transpPhone);
        if (transpPhone.isEmpty()) {
            throw new DataExceptions(19005, "Le numéro de téléphone ne peut pas être vide", "Téléphone");
        } else if (m.matches()) {
            this.transpPhone = transpPhone;
        } else {
            throw new DataExceptions(19006, "Le telephone ne peut contenir que des numéros "
                    + "et/ou des espaces, points ou tirets  et le + pour le format international", "Téléphone transporteur");
        }

    }

    public void setNote(String transpNote) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«:-@!-/0-9a-zA-ZÀ-ȳ]{0,5000}");
        Matcher m = p.matcher(transpNote);
        if (m.matches()) {
            this.transpNote = transpNote;
        } else {
            throw new DataExceptions(19007, "La note ne doit pas dépasser 5000 caractères", "Note transporteur");
        }
    }
    /*La méthode save permet de sauvegarder l'objet dans SQL 
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
            if (this.transpID == null) {
                query = "INSERT INTO TRANSPORTEUR "
                        + "(STATUSID, SHIPPINGFEES, SHIPPINGCARRIERNAME, SHIPPINGCARRIERLOGO, "
                        + "SHIPPINGCARRIERMAIL, SHIPPINGCARRIERPHONE, SHIPPINGNOTES "
                        + ") VALUES ";
                query += value;
                 

            } else {
                query = "UPDATE TRANSPORTEUR "
                        + value + " WHERE SHIPPINGID = " + this.transpID;
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
        if (this.transpID == null) {
            str += "(";
            
            str += transpStatut.getId() + ", "; // il y a une difference entre int et str (pas de simple cote) 
            str += "" + fraisLivraison + ", ";
            str += "'" + apostrophe(transpName) + "', ";
            str += "" + transpLogo + ", ";
            str += "'" + transpMail + "', ";
            str += "'" + transpPhone + "', ";
            str += "'" + apostrophe(transpNote) + "') ";

        } else {
            

            str += "SET STATUSID = " + transpStatut.getId() + ", ";
            str += "SHIPPINGFEES = " + fraisLivraison + ", ";
            str += "SHIPPINGCARRIERNAME = '" + apostrophe(transpName) + "', ";
            str += "SHIPPINGCARRIERLOGO = " + transpLogo + ", ";
            str += "SHIPPINGCARRIERMAIL = '" + transpMail + "', ";
            str += "SHIPPINGCARRIERPHONE = '" + transpPhone + "', ";
            str += "SHIPPINGNOTES = '" + apostrophe(transpNote) + "'";


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

   
