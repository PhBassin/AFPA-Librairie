
import exceptions.DataExceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TVA {
    
    private String ID;
    private String vatType;
    private float rate;
    private int type = 20;
    private String genre = "TVA";

    public TVA() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(ID);
        if (m.matches()){
            this.ID = ID;
        } else {
            throw new DataExceptions(14001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public String getVatType() {
        return vatType;
    }

    public void setVatType(String vatType) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]");
        Matcher m = p.matcher(ID);
        if (m.matches()){
            this.vatType = vatType;
        } else {
            throw new DataExceptions(14002, "Le type de TVA ne doit contenir que des lettres et est limité à 150 caractères", "VAT Type");
        }
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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
        return ID + " " + vatType + " " + rate + " " + type + " " + genre;
    }
    
    
    
    
    
}
