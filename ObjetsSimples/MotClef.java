
import exceptions.DataExceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MotClef {
    
    private String ID;
    private String name;
    private String comment;
    private int type = 14;
    private String genre = "motclef";

    public MotClef() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(name);
        if (m.matches()){
            this.name = name;
        } else {
            throw new DataExceptions(14002, "Le nom est limité à 100 caractères", "Name");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{0,150}");
        Matcher m = p.matcher(comment);
        if (m.matches()){
            this.comment = comment;
        } else {
            throw new DataExceptions(14003, "Les commentaires sont limités à 150 caractères", "Comment");
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
        return ID + " " + name + " " + comment + " " + type + " " + genre;
    }
    
    
    
}
