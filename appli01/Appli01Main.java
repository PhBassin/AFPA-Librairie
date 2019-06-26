/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appli01;

import ObjetsSimples.Adresse;
import exceptions.DataExceptions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cda201
 */
public class Appli01Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Adresse addr = new Adresse();

        try {
            addr.setPostalCode("aze");
        } catch (DataExceptions ex) {
            ex.msgPrint();
        }

    }
    
}
