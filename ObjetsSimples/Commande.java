/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

/**
 *
 * @author cda201
 */
public class Commande {

    private int type = 6;
    private String genre = "commande";
    private String ID;
    private Client client;
    private Adresse addrInv;
    private Adresse addrExp;
    private Statut status;
    private Transporteur transp;
    private String cmdDate;
    private String montantGlobal;
    private String coutTransp;
    private String transpDate;
    private String nbPaiement;
    private String montantPaiement;
    private String paiementDate;
    private String clientIP;
    private String notes;
    private String numEnvoi;
    private String etatEnvoi;

    public Commande() {
        this.client = new Client();
        this.addrExp = new Adresse();
        this.addrInv = new Adresse();
        this.status = new Statut();
        this.transp = new Transporteur();

    }

    public String getEtatEnvoi() {
        return etatEnvoi;
    }

    public void setEtatEnvoi(String etatEnvoi) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«!-/0-9a-zA-ZÀ-ȳ:-@]{0,5000}");
        Matcher m = p.matcher(etatEnvoi);
        if (m.matches()) {
            this.etatEnvoi = etatEnvoi;
        } else {
            throw new DataExceptions(6102, "L'etat de l'envoi ne doit pas dépasser 100 caractères", "Etat envoi");
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Adresse getAddrInv() {
        return addrInv;
    }

    public void setAddrInv(Adresse addrInv) {
        this.addrInv = addrInv;
    }

    public Adresse getAddrExp() {
        return addrExp;
    }

    public void setAddrExp(Adresse addrExp) {
        this.addrExp = addrExp;
    }

    public Statut getStatus() {
        return status;
    }

    public void setStatus(Statut status) {
        this.status = status;
    }

    public Transporteur getTransp() {
        return transp;
    }

    public void setTransp(Transporteur transp) {
        this.transp = transp;
    }

    public String getCmdDate() {
        return cmdDate;
    }

    public void setCmdDate(String cmdDate) throws DataExceptions {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            if (df.parse(cmdDate) != null) {
                this.cmdDate = cmdDate;
            } else {
                throw new DataExceptions(6002, "Cette date n'est pas valide.", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(6003, "Cette date n'est pas valide.", "DATE");
        }
    }

    public String getMontantGlobal() {
        return montantGlobal;
    }

    public void setMontantGlobal(String montantGlobal) throws DataExceptions {

        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(montantGlobal);
        if (m.matches()) {
            this.montantGlobal = montantGlobal;
        } else {
            throw new DataExceptions(6012, "Le montant Global ne peut contenir que des chiffres", "Montant Global");
        }
    }

    public String getCoutTransp() {
        return coutTransp;
    }

    public void setCoutTransp(String coutTransp) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(coutTransp);
        if (m.matches()) {
            this.coutTransp = coutTransp;
        } else {
            throw new DataExceptions(6022, "Le coût transporteur ne peut contenir que des chiffres", "Coût Transporteur");
        }
    }

    public String getTranspDate() {
        return transpDate;
    }

    public void setTranspDate(String transpDate) throws DataExceptions {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            if (df.parse(transpDate) != null) {
                this.transpDate = transpDate;
            } else {
                throw new DataExceptions(6032, "Cette date n'est pas valide.", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(6042, "Cette date n'est pas valide.", "DATE");
        }
    }

    public String getNbPaiement() {
        return nbPaiement;
    }

    public void setNbPaiement(String nbPaiement) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{0,}");
        Matcher m = p.matcher(nbPaiement);
        if (m.matches()) {
            this.nbPaiement = nbPaiement;
        } else {
            throw new DataExceptions(6052, "Le nombre de paiement ne peut contenir que des chiffres", "Nombre paiement");
        }
    }

    public String getMontantPaiement() {
        return montantPaiement;
    }

    public void setMontantPaiement(String montantPaiement) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9.]{0,}");
        Matcher m = p.matcher(montantPaiement);
        if (m.matches()) {
            this.montantPaiement = montantPaiement;
        } else {
            throw new DataExceptions(6062, "Le montant de paiement ne peut contenir que des chiffres", "Montant paiement");
        }
    }

    public String getPaiementDate() {
        return paiementDate;
    }

    public void setPaiementDate(String paiementDate) throws DataExceptions {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            if (df.parse(paiementDate) != null) {
                this.paiementDate = paiementDate;
            } else {
                throw new DataExceptions(7071, "Cette date n'est pas valide.", "DATE");
            }
        } catch (DateTimeParseException e) {
            throw new DataExceptions(6072, "Cette date n'est pas valide.", "DATE");
        }
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«!-/0-9a-zA-ZÀ-ȳ:-@]{0,5000}");
        Matcher m = p.matcher(notes);
        if (m.matches()) {
            this.notes = notes;
        } else {
            throw new DataExceptions(6082, "Les notes ne doivent pas dépasser 5000 caractères", "Notes");
        }
    }

    public String getNumEnvoi() {
        return numEnvoi;
    }

    public void setNumEnvoi(String numEnvoi) throws DataExceptions {
        Pattern p = Pattern.compile("[- '^~¨°²³±µ»«!-/0-9a-zA-ZÀ-ȳ:-@]{0,20}");
        Matcher m = p.matcher(numEnvoi);
        if (m.matches()) {
            this.numEnvoi = numEnvoi;
        } else {
            throw new DataExceptions(6092, "Le numéro d'envoi ne doit pas dépasser 20 caractères", "Numero d'envoi");
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
                query = "INSERT INTO COMMANDE "
                        + "(CUSTOMERID, ADDRESSINVOICEID, ADDRESSSHIPPID, STATUSID,"
                        + " SHIPPINGID, ORDERDATE, ORDERGLOBALAMOUNT,"
                        + " ORDERSHIPPINGFEES, ORDERSHIPPINGDATE, ORDERPAYMENTCOUNT,"
                        + " ORDERPAYMENTAMOUNT, ORDERPAYMENTDATE, ORDERCUSTOMERIPADDRESS,"
                        + " ORDERNOTES, ORDERSHIPPNUMBER, ORDERSHIPPSTAT) VALUES ";
                query += value;

            } else {
                query = "UPDATE COMMANDE "
                        + value + " WHERE ORDERID = " + this.ID;
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Commande", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Commande", JOptionPane.ERROR_MESSAGE);
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

            str += client.getId() + ", ";

            str += addrInv.getAddrID() + ", ";
            str += addrExp.getAddrID() + ", ";
            str += status.getId() + ", ";
            str += transp.getID() + ", ";
            str += "'" + cmdDate + "', ";
            str += "" + montantGlobal + ", ";
            str += "" + coutTransp + ", ";
            if (transpDate == null) {
                str += "" + transpDate + ", ";
            } else {
                str += "'" + transpDate + "', ";
            }
            str += "" + nbPaiement + ", ";
            str += "" + montantPaiement + ", ";
            if (paiementDate == null) {
                str += "" + paiementDate + ", ";
            } else {
                str += "'" + paiementDate + "', ";
            }

            str += "'" + clientIP + "', ";
            str += "'" + notes + "', ";
            str += "'" + numEnvoi + "', ";
            str += "'" + apostrophe(etatEnvoi) + "')";

        } else {
            str += "SET CUSTOMERID = " + client.getId() + ", ";
            str += " ADDRESSINVOICEID = " + addrInv.getAddrID() + ", ";
            str += " ADDRESSSHIPPID = " + addrExp.getAddrID() + ", ";
            str += " STATUSID = " + status.getId() + ", ";
            str += " SHIPPINGID = " + transp.getID() + ", ";
            str += " ORDERDATE = '" + cmdDate + "', ";
            str += " ORDERGLOBALAMOUNT = " + montantGlobal + ", ";
            str += " ORDERSHIPPINGFEES = " + coutTransp + ", ";
            if (transpDate != null) {
                str += " ORDERSHIPPINGDATE = '" + transpDate + "', ";
            }
            str += " ORDERPAYMENTCOUNT = " + nbPaiement + ", ";
            str += " ORDERPAYMENTAMOUNT = " + montantPaiement + ", ";
            if (paiementDate != null) {
                str += " ORDERPAYMENTDATE = '" + paiementDate + "', ";
            }
            str += " ORDERCUSTOMERIPADDRESS = '" + clientIP + "', ";
            str += " ORDERNOTES = '" + apostrophe(notes) + "', ";
            str += " ORDERSHIPPNUMBER = '" + numEnvoi + "', ";

            str += " ORDERSHIPPSTAT = '" + apostrophe(etatEnvoi) + "'";

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
