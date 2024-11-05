/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;


import domen.Artikal;
import domen.Korisnik;
import domen.Preporuka;
import domen.Racun;
import domen.StavkaRacuna;
import domen.TipArtikla;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;
import niti.PokretanjeServera;
import so.artikal.SODodajArtikal;
import so.artikal.SOObrisiArtikal;
import so.artikal.SOVratiSveArtikle;
import so.artikal.SoPromeniArtikal;
import so.korisnik.SODodajKorisnika;
import so.logIn.SOLogIn;
import so.preporuka.SODodajPreporuku;
import so.preporuka.SOVratiSvePreporuke;
import so.racun.SODodajRacun;
import so.racun.SOObrisiRacun;
import so.racun.SOPromeniRacun;
import so.racun.SOVratiSveRacune;
import so.stavkaRacuna.SOObrisiStavku;
import so.stavkaRacuna.SOVratiSveStavkeRacuna;
import so.tipArtikla.SOVratiSveTipoveArtikla;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Korisnik
 */
public class Kontroler {
    private static Kontroler instance;
    private ArrayList<ObradaKlijentskihZahteva> listaZahteva;
    PokretanjeServera ps;
    ArrayList<Korisnik> listaKorisnika;
    
    
    
    
    
    private Kontroler() {
        listaZahteva= new ArrayList<>();
        listaKorisnika= new ArrayList<>();
       
    }

    public static Kontroler getInstance() {
        if(instance==null){
            instance= new Kontroler();
        }
        return instance;
    }

    public void setListaZahteva(ArrayList<ObradaKlijentskihZahteva> listaZahteva) {
        this.listaZahteva = listaZahteva;
    }

    public ArrayList<ObradaKlijentskihZahteva> getListaZahteva() {
        return listaZahteva;
    }

    public void dodajOkz(ObradaKlijentskihZahteva okz) {
        listaZahteva.add(okz);
    }
    
    public KlijentskiZahtev primiZahtev(Socket s){
        
        KlijentskiZahtev kz= new KlijentskiZahtev();
        try {
            ObjectInputStream ois= new ObjectInputStream(s.getInputStream());
            kz=(KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kz;
        
        
        
    }

    public void setListaKorisnika(ArrayList<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public ArrayList<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }
    

    
    public void posaljiOdgovor(ServerskiOdgovor so, Socket s){
        try {
            ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void pokreniServer() {
        ps= new PokretanjeServera();
        ps.start();
    }

    


    public ArrayList<Artikal> vratiArtikle() throws Exception {
        SOVratiSveArtikle so= new SOVratiSveArtikle();
        so.templateExecute(new Artikal());
        return so.getLista();
        
    }

    public void registruj(Korisnik korisnik) throws Exception {
            SODodajKorisnika so= new SODodajKorisnika();
            so.templateExecute(korisnik);
            
    }

    public Korisnik uloguj(Korisnik korisnik) throws Exception{
            for (Korisnik korisnik1 : listaKorisnika) {
                if(korisnik1.equals(korisnik)){
                    return null;
            }
        }          
                listaKorisnika.add(korisnik);
                SOLogIn logIN= new SOLogIn();
                logIN.templateExecute(korisnik);
                return logIN.getUlogovani();
    }  
    

    public void dodajArtikal(Artikal artikal) throws Exception {
            SODodajArtikal so= new SODodajArtikal();
            so.templateExecute(artikal);
           
    }

    public ArrayList<TipArtikla> vratiTipArtikala() throws Exception {
            SOVratiSveTipoveArtikla so = new SOVratiSveTipoveArtikla();
            so.templateExecute(new TipArtikla());
            return so.getLista();
    }

    public void obrisiArtikal(Artikal artikal) throws Exception {
            SOObrisiArtikal so= new SOObrisiArtikal();
            so.templateExecute(artikal);
            
        }

    public void izmeniArtikal(Artikal artikal) throws Exception {
            SoPromeniArtikal so= new SoPromeniArtikal();
            so.templateExecute(artikal);
        
    }

    public void sacuvajRacun(Racun racun) throws Exception {
            SODodajRacun so= new SODodajRacun();
            so.templateExecute(racun);
    }


    public Object vratiRacune() throws Exception {
        SOVratiSveRacune so= new SOVratiSveRacune();
        so.templateExecute(new Racun());
        return so.getLista();   
    }

    public void obrisiRacun(Racun racun) throws Exception {
        SOObrisiRacun so= new SOObrisiRacun();
        so.templateExecute(racun);
    }

    public Object vratiStavkeRacuna(Racun racun) throws Exception {
        SOVratiSveStavkeRacuna so= new SOVratiSveStavkeRacuna();
        StavkaRacuna stavka= new StavkaRacuna();
        stavka.setRacun(racun);
        so.templateExecute(stavka);
        return so.getLista();
    }

    public void izmeniRacun(Racun racun) throws Exception {
        SOPromeniRacun so= new SOPromeniRacun();
        so.templateExecute(racun);
    }

    public void obrisiStavku(ArrayList<StavkaRacuna> stavke) throws Exception {
        SOObrisiStavku so = new SOObrisiStavku();
        for (StavkaRacuna stavkaRacuna : stavke) {
            so.templateExecute(stavkaRacuna);
        }
    }

    public void sacuvajPreporuku(Preporuka preporuka) throws Exception {
        SODodajPreporuku so= new SODodajPreporuku();
        so.templateExecute(preporuka);
    }

    public ArrayList<Preporuka> vratiPreporuke() throws Exception {
        SOVratiSvePreporuke so= new SOVratiSvePreporuke();
        so.templateExecute(new Preporuka());
        return so.getLista();
    }

    public void odjaviKorisnike() {
        for (ObradaKlijentskihZahteva obradaKlijentskihZahteva : listaZahteva) {
            
            
        }
    }
    

    
    
            
}
