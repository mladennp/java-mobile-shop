/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Artikal;
import domen.Korisnik;
import domen.Preporuka;
import domen.Racun;
import domen.StavkaRacuna;
import domen.TipArtikla;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import konstante.Operacije;
import kontroler.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import transfer.StatusOdgovora;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread{
    
    private Socket socket;
    public boolean kraj=false;
    
    
    public ObradaKlijentskihZahteva(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
         try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev klijentskiZahtev = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor serverskiOdgovor = handleRequest(klijentskiZahtev);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(serverskiOdgovor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            

    }

    private ServerskiOdgovor handleRequest(KlijentskiZahtev klijentskiZahtev) {
            ServerskiOdgovor odgovor = new ServerskiOdgovor(null, null, StatusOdgovora.USPEH);
        try {
            switch (klijentskiZahtev.getOperacija()) {
                case Operacije.REGISTRACIJA:
                    Kontroler.getInstance().registruj((Korisnik) klijentskiZahtev.getParametar());
                    break;
                case Operacije.LOGIN:
                    odgovor.setParametar(Kontroler.getInstance().uloguj((Korisnik) klijentskiZahtev.getParametar()));
                    break;
                case Operacije.DODAJ_ARTIKAL:
                    Kontroler.getInstance().dodajArtikal((Artikal)klijentskiZahtev.getParametar());
                    break;
                case Operacije.PRETRAZI_ARTIKAL:
                    odgovor.setParametar(Kontroler.getInstance().vratiArtikle());
                    break;
                case Operacije.UCITAJ_TIP_ARTIKALA:
                    odgovor.setParametar(Kontroler.getInstance().vratiTipArtikala());
                    break;
                case Operacije.OBRISI_ARTIKAL:
                    Kontroler.getInstance().obrisiArtikal((Artikal)klijentskiZahtev.getParametar());
                    break;
                case Operacije.IZMENI_ARTIKAL:
                    Kontroler.getInstance().izmeniArtikal((Artikal) klijentskiZahtev.getParametar());
                    break;
                case Operacije.SACUVAJ_RACUN:
                    Kontroler.getInstance().sacuvajRacun((Racun) klijentskiZahtev.getParametar());
                    break;
                case Operacije.VRATI_SVE_RACUNE:
                    odgovor.setParametar(Kontroler.getInstance().vratiRacune());
                    break;
                case Operacije.OBRISI_RACUN:
                    Kontroler.getInstance().obrisiRacun((Racun)klijentskiZahtev.getParametar());
                    break;
                case Operacije.VRATI_SVE_STAVKE_RACUNA:
                    odgovor.setParametar(Kontroler.getInstance().vratiStavkeRacuna((Racun) klijentskiZahtev.getParametar()));
                    break;
                case Operacije.IZMENI_RACUN:
                    Kontroler.getInstance().izmeniRacun((Racun)klijentskiZahtev.getParametar());
                    break;
                case Operacije.IZBRISI_STAVKU:
                    Kontroler.getInstance().obrisiStavku((ArrayList<StavkaRacuna>) klijentskiZahtev.getParametar());
                    break;
                case Operacije.SACUVAJ_PREPORUKU:
                    Kontroler.getInstance().sacuvajPreporuku((Preporuka)klijentskiZahtev.getParametar());
                    break;
                case Operacije.VRATI_SVE_PREPORUKE:
                    odgovor.setParametar(Kontroler.getInstance().vratiPreporuke());
                    break; 
                default:
                    return null;
            }
        } catch (Exception e) {
            odgovor.setStatus(StatusOdgovora.NEUSPEH);
            odgovor.setExc(e);
        }
        return odgovor;
    }
 
    }
    
    
    
    
    
    
    

