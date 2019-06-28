package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import xchange.Xchange;

public class LivreVolume {
	
	private String ID;
	private String bookvolname;
	private int bookvolquantity;
	private String genre = "livreVol";
	private int type = 13;


	
	public LivreVolume() {
		
	}

	public String getId() {
		return ID;
	}

	public void setId(String ID) throws DataExceptions {
	    Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(ID);
        if (m.matches()){
		this.ID = ID;
        } else {
            throw new DataExceptions(13001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }

	public String getBookvolname() {
		return bookvolname;
	}

	public void setBookvolname(String bookvolname) throws DataExceptions {
	      Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
	        Matcher m = p.matcher(bookvolname);
	        if (m.matches()){
		this.bookvolname = bookvolname;
	        } else {
	            throw new DataExceptions(13002, "Le nom ne doit contenir que des lettres et est limité à 150 caractères", "Name");
	        }
	    }

	public int getBookvolquantity() {
		return bookvolquantity;
	}

	public void setBookvolquantity(int bookvolquantity) {
		this.bookvolquantity = bookvolquantity;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
  public void save(Xchange echange, String value) {
        Statement stat = null;

        try {
            stat = echange.getConnexion().createStatement();
            String query = "";
            if (this.ID == null) {
                query = "INSERT INTO LIVREVOLUME "
                        + "( BOOKVOLNAME, BOOKVOLQUANTITY) VALUES ";
                query += value;

            } else {
                query = "UPDATE LIVREVOLUME "
                        + value + " WHERE BOOKVOLID = " + this.ID;
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
    public String genValue() {
        String str = "";
        if (this.ID == null) {
            str = "(";
            //str += ID + ", ";
            str += "'" + apostrophe(bookvolname) + "', ";
            str += "'" + bookvolquantity + "')";
    

        } else {
            str += " BOOKVOLNAME = '" + apostrophe(bookvolname) + "', ";
            str += " BOOKVOLQUANTITY = '" + bookvolquantity + "', ";
       
     

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
