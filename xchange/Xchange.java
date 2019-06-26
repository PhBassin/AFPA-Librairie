
package xchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Xchange {
    
    /*
    Class de gestion de la connexion à la base SQL
    Les propriétés peuvent est modifiées pour correspondre aux paramètres de connexion de chacun
    
    /!\ NE PAS OUBLIER D'IMPORTER LA LIBRAIRIE SQL
    */
    
    private Connection connexion;
    private String server = "localhost:1433";
    private String databaseName = "LIBRAIRIE";
    private String connectionUrl = "jdbc:sqlserver://" + server
            + ";databaseName=" + databaseName
            + ";user=sa;password=sa";

    public Xchange() {
    }

    public Xchange(Connection connexion) {
        this.connexion = connexion;
    }

    public Connection getConnexion() {
        return connexion;
    }

    /*
    Fonction Connect() :
    Permet d'ouvrir la connexion au serveur SQL pour envoyer une requête
    */
        public void connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            //System.err.println("Oups : ClassNotFoundException : " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Oups : ClassNotFoundException : " + ex.getMessage(), server, JOptionPane.ERROR_MESSAGE);
        }

        try {
            connexion = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            System.err.println("Oups : connexion : " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Oups : connexion : " + ex.getMessage(), databaseName, JOptionPane.ERROR_MESSAGE);
        }
    }
    /*
    Fonction Close() :
    Permet de fermer  la connexion au serveur SQL
    */
    public void close() {

        try {
            connexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Close : " + ex.getMessage(), databaseName, JOptionPane.ERROR_MESSAGE); ;
        }

    }
}
