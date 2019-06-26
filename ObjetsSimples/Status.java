package ObjetsSimples;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;

public class Status {
	
	private String ID;
	private String statusname;
	private String statusdescr;
	private String genre = "Status";
	private String type = "17";
	Scanner sc = new Scanner(System.in);
    
	
	public Status() {
		
	}


	public String getId() {
		return ID;
	}


	public void setId(String id) throws DataExceptions {
		Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(ID);
        if (m.matches()){
		this.ID = id;
        } else {
            throw new DataExceptions(17001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
        }
    }


	public String getStatusname() {
		return statusname;
	}


	public void setStatusname(String statusname) throws DataExceptions {
		Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z]{1,150}");
        Matcher m = p.matcher(statusname);
        if (m.matches()){
		this.statusname = statusname;
        } else {
            throw new DataExceptions(17002, "Le nom ne doit contenir que des lettres et est limité à 150 caractères", "Name");
        }
    }


	public String getStatusdescr() {
		return statusdescr;
	}


	public void setStatusdescr(String statusdescr) throws DataExceptions {
	    Pattern p = Pattern.compile("[- 'À-ȳ-a-zA-Z0-9]{1,150}");
        Matcher m = p.matcher(statusdescr);
        if (m.matches()){
		this.statusdescr = statusdescr;
        } else {
            throw new DataExceptions(17003, "La description est limitée à 150 caractères", "Desc");
        }
    }
	
	public void modifierstatusname() {
		statusname = sc.nextLine();
	}
	
	public void modifierstatusdescr() {
		statusdescr = sc.nextLine();
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
