package ObjetsSimples;

import exceptions.DataExceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditeurCollection {

    private int type = 8;
    private String genre = "editeurcollection";
    private String publisherCollID;
    private Editeur publisher;
    private String publisherCollName;

    public EditeurCollection() {
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisherCollID() {
        return publisherCollID;
    }

    public void setPublisherCollID(String publisherCollID) {
        this.publisherCollID = publisherCollID;
    }

    public Editeur getPublisher() {
        return publisher;
    }

    public void setPublisherID(Editeur publisher) {
        this.publisher = publisher;
    }

    public String getPublisherCollName() {
        return publisherCollName;
    }

    public void setPublisherCollName(String publisherCollName) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳa-zA-Z]{1,150}");
        Matcher m = p.matcher(publisherCollName);
        if (m.matches()) {
            this.publisherCollName = publisherCollName;
        } else {
            throw new DataExceptions(8002, "Le nom de la collection editeur ne peut contenir que des lettres (max 150)", "nom de collection");
        }

    }

}
