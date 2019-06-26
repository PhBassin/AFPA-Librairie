// les exeptions ne sont pas finies (reflechir a la question du taux de reduc ensemble) 
package ObjetsSimples;

import exceptions.DataExceptions;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Event {

    protected String eventID;
    protected String status;
    protected String name;
    protected Date starterDate;
    protected Date endingDate;
    protected float discount;
    protected String description;
    protected Image image;
    protected String note;
    protected String statusDate;
    protected String genre = "event";
    protected int type = 7;

    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Event() {
    }

    public String getEventID() {
        return eventID;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Date getStarterDate() {
        return starterDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public float getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    public Image getImage() {
        return image;
    }

    public String getNote() {
        return note;
    }

    public String getStatusDate() {
        return statusDate;
    }

    public String getGenre() {
        return genre;
    }

    public int getType() {
        return type;
    }

    public void setEventID(String eventID) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(eventID);
        if (m.matches()) {
            this.eventID = eventID;
        } else {
            throw new DataExceptions(7001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStarterDate(Date starterDate) {

        do {
            try {
                if (df.parse((CharSequence) starterDate) != null) {
                    this.starterDate = starterDate;
                } else {
                    System.out.println("CECI N'EST PAS UNE DATE");
                    JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date de début", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("CECI N'EST PAS UNE DATE");
                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date de début", JOptionPane.ERROR_MESSAGE); // le message qui sera afficher popup
            }
        } while (true);
    }

    public void setEndingDate(Date endingDate) {

        do {
            try {
                if (df.parse((CharSequence) starterDate) != null) {
                    this.endingDate = endingDate;
                } else {
                    System.out.println();
                    JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date de fin", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("CECI N'EST PAS UNE DATE");
                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date de fin", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setDescription(String description) throws DataExceptions {
        Pattern p = Pattern.compile("{0,1024}");
        Matcher m = p.matcher(description);
        if (m.matches()) {
            this.description = description;
        } else {
            throw new DataExceptions(7002, "La description ne doit pas dépasser 1024 caractères", "Description");
        }
    }

    public void setImage(Image image) throws DataExceptions {
        Pattern p = Pattern.compile("([^\\s]+(\\.(?i)(/bmp|jpg|gif|png))$)");
        Matcher m = p.matcher((CharSequence) image);
        if (m.matches()) {
            this.image = image;
        } else {
            throw new DataExceptions(7003, "L'image n'est pas conforme, merci de choisir le format (png, jpg, gif, bmp ou jpeg)", "Image");
        }
    }

    public void setNote(String note) throws DataExceptions {
        Pattern p = Pattern.compile("{0,5000}");
        Matcher m = p.matcher(note);
        if (m.matches()) {
            this.note = note;
        } else {
            throw new DataExceptions(7004, "La description ne doit pas dépasser 1024 caractères", "Description");
        }
    }

    public void setStatusDate(String statusDate) {

        do {
            try {
                if (df.parse((CharSequence) statusDate) != null) {
                    this.statusDate = statusDate;
                } else {
                    System.out.println();
                    JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date du statut", JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("CECI N'EST PAS UNE DATE");
                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date du statut", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
    }
    
    // faire une methode qui gere la question du rabais 

}
