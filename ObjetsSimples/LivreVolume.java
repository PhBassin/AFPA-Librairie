package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;

public class LivreVolume {
	
	private String ID;
	private String bookvolname;
	private int bookvolquantity;
	private String genre = "LivreVol";
	private String type = "13";
	Scanner sc = new Scanner(System.in);
	Scanner sc2 = new Scanner(System.in);

	
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
	
	public void modifiervolname() {
		bookvolname = sc.nextLine();
	}
		
	public void modifiervolquantity() {
		bookvolquantity = sc2.nextInt();
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
