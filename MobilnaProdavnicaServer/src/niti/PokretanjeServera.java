/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import kontroler.Kontroler;

/**
 *
 * @author Korisnik
 */
public class PokretanjeServera extends Thread{
    
    private ServerSocket ss;
    public boolean kraj=false;

    @Override
    public void run() {
        try {
            ss= new ServerSocket(9000);
            System.out.println("Server je pokrenut!");
         
            
            while(!kraj){
                
                Socket s= ss.accept();
                System.out.println("Povezan je klijent");
                
                
                ObradaKlijentskihZahteva okz= new ObradaKlijentskihZahteva(s);
                okz.start();
                Kontroler.getInstance().dodajOkz(okz);
                
                
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("Server je ugasen");
        }
    }

    public void setSs(ServerSocket ss) {
        this.ss = ss;
    }

    public ServerSocket getSs() {
        return ss;
    }
    
    
            
            
    
    
    
}
