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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import konstante.Operacije;
import sesija.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.StatusOdgovora;

/**
 *
 * @author Korisnik
 */
public class KlijentskiKontroler {
    private static KlijentskiKontroler instance;
    Korisnik trenutniKorisnik;

    private KlijentskiKontroler() {
        
    }

    public static KlijentskiKontroler getInstance() {
        if(instance==null){
            instance= new KlijentskiKontroler();
        }
        return instance;
    }

    public void setTrenutniKorisnik(Korisnik trenutniKorisnik) {
        this.trenutniKorisnik = trenutniKorisnik;
    }

    public Korisnik getTrenutniKorisnik() {
        return trenutniKorisnik;
    }
    
    
    public Korisnik login(Korisnik korisnik) throws Exception {
        return (Korisnik) posaljiZahtev(Operacije.LOGIN, korisnik);
    }
    
    public void registruj(Korisnik korisnik) throws Exception {
        posaljiZahtev(Operacije.REGISTRACIJA, korisnik);
    }
    
    public ArrayList<TipArtikla> vratiTipoveArtikala() throws Exception {
        return (ArrayList<TipArtikla>) posaljiZahtev(Operacije.UCITAJ_TIP_ARTIKALA, null);
    }
    
      public ArrayList<Artikal> vratiSveArtikle() throws Exception {
        return (ArrayList<Artikal>) posaljiZahtev(Operacije.PRETRAZI_ARTIKAL, null);
    }
    
     
     
    
      private Object posaljiZahtev(int operation, Object data) throws Exception {
          KlijentskiZahtev kz = new KlijentskiZahtev(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getS().getOutputStream());
        out.writeObject(kz);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getS().getInputStream());
        ServerskiOdgovor odgovor = (ServerskiOdgovor) in.readObject();
          System.out.println(odgovor);

        if (odgovor.getStatus().equals(StatusOdgovora.NEUSPEH)) {
            throw odgovor.getExc();
        } else {
            return odgovor.getParametar();
        }

    }

    public void sacuvajArtikal(Artikal artikal) throws Exception {
        posaljiZahtev(Operacije.DODAJ_ARTIKAL, artikal);
    }

    public void obrisiArtikal(Artikal artikal) throws Exception {
        posaljiZahtev(Operacije.OBRISI_ARTIKAL, artikal);
    }

    public void izmeniArtikal(Artikal artikal) throws Exception {
        posaljiZahtev(Operacije.IZMENI_ARTIKAL, artikal);
    }

    public void sacuvajRacun(Racun racun) throws Exception {
        posaljiZahtev(Operacije.SACUVAJ_RACUN, racun);
        }

    public void sacuvajStavke(ArrayList<StavkaRacuna> listaStavki) throws Exception {
        posaljiZahtev(Operacije.DODAJ_STAVKU_RACUNA, listaStavki);
    }

    public ArrayList<Racun> vratiRacune() throws Exception {
        return (ArrayList<Racun>) posaljiZahtev(Operacije.VRATI_SVE_RACUNE, null);
    }

    public void obrisiRacun(Racun racun) throws Exception {
        posaljiZahtev(Operacije.OBRISI_RACUN, racun);
        
    }

    public ArrayList<StavkaRacuna> vratiStavkeRacuna(Racun racun) throws Exception {
        return (ArrayList<StavkaRacuna>) posaljiZahtev(Operacije.VRATI_SVE_STAVKE_RACUNA, racun);
    }

    public void izmeniRacun(Racun racun) throws Exception {
        posaljiZahtev(Operacije.IZMENI_RACUN, racun);
    }

    public void obrisiStavke(ArrayList<StavkaRacuna> stavke) throws Exception {
        posaljiZahtev(Operacije.IZBRISI_STAVKU, stavke);
    }

    public void sacuvajPreporuku(Preporuka preporuka) throws Exception {
        posaljiZahtev(Operacije.SACUVAJ_PREPORUKU, preporuka);
    }

    public ArrayList<Preporuka> vratiPreporuke() throws Exception {
        return (ArrayList<Preporuka>) posaljiZahtev(Operacije.VRATI_SVE_PREPORUKE, null);
    }
    
    
}
