
package ObjetsSimples;

public class Societe {
    
    private String nom;
    private Adresse adresse;
    private String siret;
    private String siren;
    private String phone;

    public Societe() {
    }

    public String getNom() {
        return nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getSiret() {
        return siret;
    }

    public String getSiren() {
        return siren;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return nom + " " + adresse + " " + siret + " " + siren + " " + phone;
    }
    
    
    
}
