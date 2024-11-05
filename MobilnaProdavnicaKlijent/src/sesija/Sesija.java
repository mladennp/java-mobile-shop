/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domen.Korisnik;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Korisnik
 */
public class Sesija {
    
    private static Sesija instance;
    Socket s;
    private Korisnik korisnik;
    
    
    public Sesija() {
        try {
            s= new Socket("localhost", 9000);
            System.out.println("Klijent se povezao!");
        } catch (IOException ex) {
            System.out.println("Greska u povezivanju soketa!");
        }
    }

    public static Sesija getInstance() {
        if(instance==null){
            instance= new Sesija();
        }
        return instance;
    }

    public Socket getS() {
        return s;
    }

   
    
    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
    
    
    public void posaljiZahtev(KlijentskiZahtev kz){
        try {
            ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
        } catch (IOException ex) {
            Logger.getLogger(Sesija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ServerskiOdgovor primiOdgovor(){
        ServerskiOdgovor so= new ServerskiOdgovor();
        try {
            ObjectInputStream ois= new ObjectInputStream(s.getInputStream());
            so= (ServerskiOdgovor) ois.readObject();
            
        } catch (IOException ex) {
            Logger.getLogger(Sesija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sesija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return so;
    }
    
    
    
    
    
}