package ObjetsSimples;

import exceptions.DataExceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grille {
    
    protected String gridID;
    protected Editeur editeur;
    protected String gridCat;
    protected String gridPrice;
    protected String genre = "grille";
    protected int type = 10;

    public Grille() {
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
        Pattern p = Pattern.compile("{0,50}");
        Matcher m = p.matcher(gridCat);
        if (m.matches()) {
            this.gridCat = gridCat;
        } else {
            throw new DataExceptions(10002, "La cathégorie ne doit pas dépasser 50 caractères", "Grille cathégorie");
        }
    }
        
    

    public void setGridPrice(String gridPrice) {
        this.gridPrice = gridPrice;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
