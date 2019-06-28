package ObjetsSimples;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Adresse {

    private int type = 3;
    private String genre = "adresse";
    private String addrID;
    private Pays country;
    private Editeur publisher;
    private Client customer;
    private Client cli_Customer;
    private String name;
    private String number;
    private String streetType;
    private String streetName;
    private String postalCode;
    private String city;
    private String more;

    public Adresse() {
        this.country = new Pays();
        this.publisher = new Editeur();
        this.customer = new Client();
        this.cli_Customer = new Client();
        
    }

    public void setAddrID(String addrID) {
        this.addrID = addrID;
    }
    
    public void setPublisher(Editeur publisher) {

        this.publisher = publisher;
    }

    public void setCustomer(Client customer) {
        this.customer = customer;
    }

    public void setCli_Customer(Client cli_Customer) {
        this.cli_Customer = cli_Customer;
    }

    public void setCountry(Pays country) {
        this.country = country;
    }

    public Editeur getPublisher() {
        return publisher;
    }

    public Client getCustomer() {
        return customer;
    }

    public Client getCli_Customer() {
        return cli_Customer;
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getAddrID() {
        return addrID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getMore() {
        return more;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{0,100}");
        Matcher m = p.matcher(name);
        if (m.matches()) {
            this.name = name;
        } else {
            throw new DataExceptions(3012, "Le nom ne peut contenir que des lettres (max 100)", "Nom");
        }
    }

    public void setNumber(String number) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{0,10}");
        Matcher m = p.matcher(number);
        if (number.isEmpty()) {
            this.number = "";
        } else if (m.matches()) {
            this.number = number;
        } else {
            throw new DataExceptions(3022, "Le numéro ne peut contenir que des chiffres (max 10)", "Numéro");
        }
    }

    public void setStreetType(String streetType) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{1,20}");
        Matcher m = p.matcher(streetType);
        if (m.matches()) {
            this.streetType = streetType;
        } else {
            throw new DataExceptions(3032, "Le type de rue ne peut contenir que des lettres (max 20)", "Type de Rue");
        }

    }

    public void setStreetName(String streetName) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{1,100}");
        Matcher m = p.matcher(streetName);
        if (m.matches()) {
            this.streetName = streetName;
        } else {
            throw new DataExceptions(3042, "Le nom de rue ne peut contenir que des lettres (max 100)", "Nom de Rue");
        }
        this.streetName = streetName;
    }

    public void setPostalCode(String postalCode) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{5}");
        Matcher m = p.matcher(postalCode);
        if (m.matches()) {
            this.postalCode = postalCode;
        } else {
            throw new DataExceptions(3052, "Le code postal ne peut contenir que 5 chiffres", "Code Postal");
        }

    }

    public void setCity(String city) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{1,100}");
        Matcher m = p.matcher(city);
        if (m.matches()) {
            this.city = city;
        } else {
            throw new DataExceptions(3062, "Le nom de la ville ne peut contenir que des lettres (max 100)", "ville");
        }

    }

    public void setMore(String more) throws DataExceptions {
        Pattern p = Pattern.compile("{0,30}");
        Matcher m = p.matcher(more);
        if (m.matches()) {
            this.more = more;
        } else {
            throw new DataExceptions(3072, "Le complement d'adresse doit contenir moins de 30 caractères", "Complément");
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
            if (this.addrID == null) {
                query = "INSERT INTO ADRESSE "
                        + "(COUNTRYID, PUBLISHERID, CUSTOMERID, CLI_CUSTOMERID,"
                        + " ADDRESSNAME, ADDRESSNUMBER, ADDRESSSTREETTYPE,"
                        + " ADDRESSSTREETNAME, ADDRESSPOSTALCODE, ADDRESSCITY,"
                        + " ADDRESSMORE) VALUES ";
                query += value;

            } else {
                query = "UPDATE ADRESSE "
                        + value + " WHERE ADDRESSID = " + this.addrID;
            }

            System.out.println(query);
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
    /*
     La méthode genValue sert à générer une partie de la requête necessaire pour la sauvegarde
    
     2 options sont disponibles
     1- l'objet vient d'être crée et n'existe pas dans SQL , l'addrID est vide donc on crée un nouvel enregistrement
     2- l'objet est déjà dans SQL , l'addrID n'est pas vide donc on met à jour l'enregistrement
     */

    public String genValue() {
        String str = "";
        if (this.addrID == null) {
            str = "(";
            //str += ID + ", ";

            str += country.getCountryID() + ", ";

            str += publisher.getID() + ", ";

            str += customer.getId() + ", ";
            str += cli_Customer.getId() + ", ";
            str += "'" + apostrophe(name) + "', ";
            str += "'" + number + "', ";
            str += "'" + streetType + "', ";
            str += "'" + apostrophe(streetName) + "', ";
            str += "'" + postalCode + "', ";
            str += "'" + city + "', ";
            str += "'" + apostrophe(more) + "')";

        } else {
            str += "SET COUNTRYID = " + country.getCountryID() + ", ";
            str += " PUBLISHERID = " + publisher.getID() + ", ";
            str += " CUSTOMERID = " + customer.getId() + ", ";
            str += " CLI_CUSTOMERID = " + customer.getId() + ", ";
            str += " ADDRESSNAME = '" + apostrophe(name) + "', ";
            str += " ADDRESSNUMBER = '" + number + "', ";
            str += " ADDRESSSTREETTYPE = '" + streetType + "', ";
            str += " ADDRESSSTREETNAME = '" + apostrophe(streetName) + "', ";
            str += " ADDRESSPOSTALCODE = '" + postalCode + "', ";
            str += " ADDRESSCITY = '" + city + "', ";
            str += " ADDRESSMORE = '" + apostrophe(more) + "'";

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
