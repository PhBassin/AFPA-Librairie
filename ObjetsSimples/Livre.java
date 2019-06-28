package ObjetsSimples;

import exceptions.DataExceptions;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class Livre {

    private int type = 12;
    private String genre = "livre";
    private String isbn13;
    private Format format;
    private LivreVolume bookVol;
    private Auteur author;
    private EditeurCollection publisherColl;
    private Grille grid;
    private Statut statut;

    private String title;
    private String subTitle;
    private String isbn10;
    private byte[] picture;
    private String paging;
    private String releaseDate;
    private String synopsis;
    private String availableStock;
    private String serie;
    private String price;
    private String notes;

    public Livre() {
        this.format = new Format();
        this.bookVol = new LivreVolume();
        this.author = new Auteur();
        this.publisherColl = new EditeurCollection();
        this.grid = new Grille();
        this.statut = new Statut();
    }

    public int getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public Auteur getAuthor() {
        return author;
    }

    public void setAuthor(Auteur author) {
        this.author = author;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public void setIsbn13(String isbn13) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{3}[-][0-9]{10}");
        Matcher m = p.matcher(isbn13);
        if (m.matches()) {
            this.isbn13 = isbn13;
        } else {
            throw new DataExceptions(12002, "L'ISBN13 doit contenir 13 chiffres", "ISBN13");
        }
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public LivreVolume getBookVol() {
        return bookVol;
    }

    public void setBookVol(LivreVolume bookVol) {
        this.bookVol = bookVol;
    }

    public EditeurCollection getPublisherColl() {
        return publisherColl;
    }

    public void setPublisherColl(EditeurCollection publisherColl) {
        this.publisherColl = publisherColl;
    }

    public Grille getGrid() {
        return grid;
    }

    public void setGrid(Grille grid) {
        this.grid = grid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(title);
        if (m.matches()) {
            this.title = title;
        } else {
            throw new DataExceptions(12012, "Le titre est limité à 150 caractères", "Titre");
        }

    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,300}");
        Matcher m = p.matcher(subTitle);
        if (m.matches()) {
            this.subTitle = subTitle;
        } else {
            throw new DataExceptions(12022, "Le sous titre est limité à 300 caractères", "Sous Titre");
        }

    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(isbn10);
        if (m.matches()) {
            this.isbn10 = isbn10;
        } else {
            throw new DataExceptions(12032, "L'ISBN10 doit contenir 10 chiffres", "ISBN10");
        }

    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getPaging() {
        return paging;
    }

    public void setPaging(String paging) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{1,5}");
        Matcher m = p.matcher(paging);
        if (m.matches()) {
            this.paging = paging;
        } else {
            throw new DataExceptions(12042, "Le nombre de page est limité à 5 chiffres", "Nombre de page");
        }

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            if (df.parse((CharSequence) releaseDate) != null) {
                this.releaseDate = releaseDate;
            }

        } catch (DateTimeParseException e) {
//            System.out.println("CECI N'EST PAS UNE DATE");
            JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date de sortie", JOptionPane.ERROR_MESSAGE); // le message qui sera afficher popup
        }

    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,5000}");
        Matcher m = p.matcher(synopsis);
        if (m.matches()) {
            this.synopsis = synopsis;
        } else {
            throw new DataExceptions(12052, "Le synopsis est limité à 5000 caractères", "Synopsis");
        }

    }

    public String getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(String availableStock) throws DataExceptions {
        Pattern p = Pattern.compile("[0-9]{1,}");
        Matcher m = p.matcher(availableStock);
        if (m.matches()) {
            this.availableStock = availableStock;
        } else {
            throw new DataExceptions(12062, "Le stock disponible ne contient que des chiffres", "Nombre de page");
        }

    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(serie);
        if (m.matches()) {
            this.serie = serie;
        } else {
            throw new DataExceptions(12072, "La serie est limité à 150 caractères", "Serie");
        }

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) throws DataExceptions {
        Pattern p = Pattern.compile("[.0-9]{1,}");
        Matcher m = p.matcher(price);
        if (m.matches()) {
            this.price = price;
        } else {
            throw new DataExceptions(12082, "Le prix ne contient que des chiffres", "Prix");
        }

    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) throws DataExceptions {
        Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{0,5000}");
        Matcher m = p.matcher(notes);
        if (m.matches()) {
            this.notes = notes;
        } else {
            throw new DataExceptions(12092, "Les notes sont limitées à 5000 caractères", "Notes");
        }

    }

    public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (!testBase(echange)) {
                query = "INSERT INTO LIVRE "
                        + "(BOOKISBN13, FORMATID, BOOKVOLID, AUTHORID, "
                        + "PUBLISHERCOLLID, GRIDID, STATUSID, BOOKTITLE, "
                        + "BOOKSUBTITLE, BOOKISBN10, BOOKPICTURE, BOOKPAGING, "
                        + "BOOKRELEASEDATE, BOOKSYNOPSIS, BOOKAVAILABLESTOCK, "
                        + "BOOKSERIES, BOOKPRICE, BOOKNOTES) VALUES ";

                query += value;

            } else {
                query = "UPDATE LIVRE "
                        + value + " WHERE BOOKISBN13 = '" + this.isbn13 + "'";
            }

            System.out.println(query);
            stat.executeUpdate(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ecriture Table : " + ex.getErrorCode() + " / " + ex.getMessage(), "Livre", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fermeture statement : " + ex.getErrorCode() + " / " + ex.getMessage(), "Table Livre", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public String genValue(Xchange echange) {
        String str = "";

        if (!testBase(echange)) {
            str = "(";
            str += "'" + isbn13 + "', ";
            str += "" + format.getId() + ", ";
            str += "" + bookVol.getId() + ", ";
            str += "" + author.getID() + ", ";
            str += "" + publisherColl.getPublisherCollID() + ", ";
            str += "" + grid.getGridID() + ", ";
            str += "" + statut.getId() + ", ";
            str += "'" + apostrophe(title) + "', ";
            str += "'" + apostrophe(subTitle) + "', ";
            str += "" + isbn10 + ", ";
            str += "" + picture + ", ";
            str += "'" + paging + "', ";
            str += "'" + releaseDate + "', ";
            str += "'" + apostrophe(synopsis) + "', ";
            str += "" + availableStock + ", ";
            str += "'" + apostrophe(serie) + "', ";
            str += "" + price + ", ";
            str += "'" + apostrophe(notes) + "') ";

        } else {
            str += "SET BOOKISBN13 = '" + isbn13 + "', ";
            str += "FORMATID = " + format.getId() + ", ";
            str += "BOOKVOLID = " + bookVol.getId() + ", ";
            str += "AUTHORID = " + author.getID() + ", ";
            str += "PUBLISHERCOLLID = " + publisherColl.getPublisherCollID() + ", ";
            str += "GRIDID = " + grid.getGridID() + ", ";
            str += "STATUSID = " + statut.getId() + ", ";
            str += "BOOKTITLE = '" + apostrophe(str) + "', ";
            str += "BOOKSUBTITLE = '" + apostrophe(subTitle) + "', ";
            str += "BOOKISBN10 = " + isbn10 + ", ";
            str += "BOOKPICTURE = " + picture + ", ";
            str += "BOOKPAGING = '" + paging + "', ";
            str += "BOOKRELEASEDATE = '" + releaseDate + "', ";
            str += "BOOKSYNOPSIS = '" + apostrophe(synopsis) + "', ";
            str += "BOOKAVAILABLESTOCK = " + availableStock + ", ";
            str += "BOOKSERIES = '" + apostrophe(serie) + "', ";
            str += "BOOKPRICE = " + price + ", ";
            str += "BOOKNOTES = '" + apostrophe(notes) + "'";

        }
        return str;
    }

    public boolean testBase(Xchange ech) {
        //Xchange ech = new Xchange();
        Statement stat = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            stat = ech.getConnexion().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Livre.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            String query = "SELECT * FROM LIVRE WHERE BOOKISBN13 = '" + this.isbn13 + "'";
            System.out.println(query);
            rs = stat.executeQuery(query);

//            do {
//                System.out.println("--");
//                System.out.println(rs.getString("BOOKISBN13"));
//            } while (rs.next());
//            while( rs.next()) {
//                System.out.println( ">>>>>>"+rs.getString("BOOKISBN13"));
//            }
            result = rs.next();

        } catch (SQLException ex) {
            new DataExceptions(12100, "test présence en base : " + ex.getMessage(), "TESTBASE").msgPrint();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Oups : SQL : " + ex.getErrorCode() + " / " + ex.getMessage(), "TestClose", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        return result;
    }

    public String apostrophe(String apostrophe) {
        String str = "";
        if (apostrophe != null) {
            str = apostrophe.replace("'", "''");
        }

        return str;

    }
}
