
import exceptions.DataExceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auteur {
    
    private String ID;
    private String lastName;
    private String firstName;
    private String bio;
    private byte[] picture;
    private String notes;
    private int type = 2;
    private String genre = "auteur";

    public Auteur() {
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
            throw new DataExceptions(2001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(lastName);
        if (m.matches()){
            this.lastName = lastName;
        } else {
            throw new DataExceptions(2002, "Le nom ne doit avoir que des lettres", "Last Name");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(firstName);
        if (m.matches()){
            this.firstName = firstName;
        } else {
            throw new DataExceptions(2003, "Le prénom ne doit avoir que des lettres", "First Name");
        }
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) throws DataExceptions {
        Pattern p = Pattern.compile("{0,1024}");
        Matcher m = p.matcher(bio);
        if (m.matches()){
            this.bio = bio;
        } else {
            throw new DataExceptions(2004, "La biographie ne doit pas dépasser 1024 caractères", "Bio");
        }
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws DataExceptions {
        Pattern p = Pattern.compile("{0,500}");
        Matcher m = p.matcher(notes);
        if (m.matches()){
            this.notes = notes;
        } else {
            throw new DataExceptions(2005, "Les notes ne doivent pas dépasser 500 caractères", "Notes");
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
        return ID + " " + lastName + " " + firstName + " " + bio + " " + picture + " " + notes + " " + type + " " + genre;
    }
    
    

    
    
    
    
}
