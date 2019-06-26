package ObjetsSimples;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DataExceptions;
import javax.swing.JOptionPane;

public class Client {
	
	private String ID;
	private String customerlastname;
	private String customerfirstname;
	private String customerbirthdate;
	private String customerregistrationdate;
	private String customermail;
	private String customerphone;
	private String genre = "Client";
	private int type = 5; 
	
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public Client() {

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
	            throw new DataExceptions(5001, "L'ID ne doit contenir que des chiffres et sans espace", "ID");
	        }
	    }




	public String getCustomerlastname() {
		return customerlastname;
	}




	public void setCustomerlastname(String customerlastname)throws DataExceptions {
		Pattern p = Pattern.compile("[- 'éè^éàûôa-zA-Z]{1,100}");
		Matcher m = p.matcher(customerlastname);
		if(customerlastname.isEmpty()) {
			throw new DataExceptions(5002,"Le nom ne peut pas être vide", "Nom");
		} else if (m.matches()) {
		this.customerlastname = customerlastname;
		} else {
			throw new DataExceptions(5003,"Le nom ne peut contenir que des lettres", "Nom");
		}
	}





	public String getCustomerfirstname() {
		return customerfirstname;
	}




	public void setCustomerfirstname(String customerfirstname)throws DataExceptions {
		Pattern p = Pattern.compile("[- 'éè^éàûôa-zA-Z]{1,100}");
		Matcher m = p.matcher(customerlastname);
		if(customerlastname.isEmpty()) {
			throw new DataExceptions(5004,"Le nom ne peut pas être vide", "Nom");
		} else if (m.matches()) {
		this.customerfirstname = customerfirstname;
		} else {
			throw new DataExceptions(5005,"Le nom ne peut contenir que des lettres", "Nom");
		}
	}




	public String getCustomerbirthdate() {
		return customerbirthdate;
	}




	public void setCustomerbirthdate(String customerbirthdate) {
		do {
			try {
		if(df.parse(customerbirthdate) != null)
		{this.customerbirthdate = customerbirthdate;}
		else 
			System.out.println("CECI N'EST PAS UNE DATE");
                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date d'anniverssaire", JOptionPane.ERROR_MESSAGE);
		}catch (DateTimeParseException e) {
			System.out.println("CECI N'EST PAS UNE DATE");
                        JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date d'anniverssaire", JOptionPane.ERROR_MESSAGE);
                }
	}while(true);	
		
	}




	public String getCustomerregistrationdate() {
		
		return customerregistrationdate;
	}




	public void setCustomerregistrationdate(String customerregistrationdate) {
		do {
			try {
		if(df.parse(customerregistrationdate) != null)
		{this.customerregistrationdate = customerregistrationdate;}
		else 
			System.out.println("CECI N'EST PAS UNE DATE");
                JOptionPane.showMessageDialog(null, "Cette date n'est pas valide.", "Date d'anniverssaire", JOptionPane.ERROR_MESSAGE);
		}catch (DateTimeParseException e) {
			System.out.println("CECI N'EST PAS UNE DATE");	
                        JOptionPane.showMessageDialog(null, "Cette date n'est pas valide." + e.getMessage(), "Date d'inscription", JOptionPane.ERROR_MESSAGE);
                }
	}while(true);	
		
	}




	public String getCustomermail() {
		return customermail;
	}




	public void setCustomermail(String customermail) {
		this.customermail = customermail;
	}




	public String getCustomerphone() {
		return customerphone;
	}




	public void setCustomerphone(String customerphone) throws DataExceptions {
		Pattern p = Pattern.compile("[0-9+][0,9 ]{9,14}");
		Matcher m = p.matcher(customerphone);
		if(customerphone.isEmpty()) {
			throw new DataExceptions(5003,"Le tel ne peut pas être vide", "Téléphone");
		} else if (m.matches()) {
	this.customerphone = customerphone;
	} else {
			throw new DataExceptions(5004,"Le nom ne peut contenir que des lettres "
					+ "et/ou des espaces et + pour le format international", "Téléphone");
		}
	}

	
//	public void modifierlastname() {
//		customerlastname = sc.nextLine();
//	}
//	
//	
//	public void modifierfirstname() {
//		customerfirstname = sc.nextLine();
//	}
//	
//	public void modifierbirthdate() {
//		customerbirthdate = sc.nextLine();
//	}
//	
//	public void modifierregistrationdate() {
//		customerregistrationdate = sc.nextLine();
//		
//	}
//	
//	public void modifiermail() {
//		customermail = sc.nextLine();
//	}
//	
//	public void modifierphone() {
//		customerphone = sc.nextLine();
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
	
	
	
	


