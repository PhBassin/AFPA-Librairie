package ObjetsSimples;

//import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;

public class Editeur {
	
	private String ID;
	private String name;
	private String mail;
	private String tel;
	private String notes;
	private String genre = "Editeur";
	private String type = "1";
	//Scanner sc = new Scanner(System.in);
	

	
	public Editeur() {
		
	}



	public String getName() {
		return name;
	}




	public void setName(String name) throws DataExceptions {
		Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
		Matcher m = p.matcher(name);
		if(name.isEmpty()) {
			throw new DataExceptions(1001,"Le nom ne peut pas être vide", "Nom");
		} else if (m.matches()) {
		this.name = name;
		} else {
			throw new DataExceptions(1002,"Le nom ne peut contenir que des lettres", "Nom");
		}
	}





	public String getMail() {
		return mail;
	}



	public void setMail(String mail) throws DataExceptions {
		this.mail = mail;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) throws DataExceptions {
		Pattern p = Pattern.compile("[0-9+][0,9 ]{9,14}");
		Matcher m = p.matcher(tel);
		if(tel.isEmpty()) {
			throw new DataExceptions(1003,"Le tel ne peut pas être vide", "Téléphone");
		} else if (m.matches()) {
		this.tel = tel;
		} else {
			throw new DataExceptions(1004,"Le nom ne peut contenir que des lettres "
					+ "et/ou des espaces et + pour le format international", "Téléphone");
		}
	}




	public String getNotes() {
		return notes;
	}



	public void setNotes(String notes) throws DataExceptions {
	    Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(notes);
        if (m.matches()){
		this.notes = notes;
        } else {
            throw new DataExceptions(1005, "La description est limitée à 150 caractères", "Desc");
        }
    }



	public String getID() {
		return ID;
	}



	public void setID(String iD) throws DataExceptions {
		 Pattern p = Pattern.compile("[0-9]*");
	        Matcher m = p.matcher(ID);
	        if (m.matches()){
		this.ID = iD;
	        } else {
	            throw new DataExceptions(1006, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
	        }
	    }
	
	
//	public void modifiername() {
//		name = sc.nextLine();
//		
//	}
//	
//	
//
//	public void modifiermail() {
//	     mail = sc.nextLine();
//		
//	}
//	
//	
//	public void modifiertel() {
//		tel = sc.nextLine();
//		
//	}
//	
//	public void modifiernotes() {
//		notes = sc.nextLine();
//		
//	}



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
