/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class StavkaRacuna extends AbstractDomainObject{
    
    private Racun racun;
    private int redniBroj;
    private int kolicina;
    private Artikal artikal;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int redniBroj, int kolicina, Artikal artikal) {
        this.racun = racun;
        this.redniBroj = redniBroj;
        this.kolicina = kolicina;
        this.artikal = artikal;
    }

    


    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

   

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Artikal getArtikal() {
        return artikal;
    }
    
    

    @Override
    public String nazivTabele() {
        return " stavka_racuna ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
             return " JOIN RACUN R ON (R.SIFRA_RACUNA = SR.RACUN) JOIN ARTIKAL A ON (SR.SIFRA_ARTIKLA=A.SIFRA_ARTIKLA)"
                     + " JOIN KORISNIK K ON (R.KORISNIK=K.KORISNIK_ID) JOIN TIP_ARTIKLA TP ON (TP.SIFRA_TIPA=A.TIP_ARTIKLA)";
    }
    

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
            ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Korisnik k = new Korisnik(rs.getInt("korisnik_id"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("korisnicko_ime"), rs.getString("sifra"));


            Racun racun = new Racun(rs.getInt("sifra_racuna"), 
                    rs.getDouble("ukupan_iznos"), rs.getDouble("uplaceno")
                    , rs.getDouble("povracaj"), rs.getDouble("iznosPDV"), k);
            
            TipArtikla tp = new TipArtikla(rs.getInt("sifra_tipa"),
                    rs.getString("naziv_tipa"));
            
            Artikal a = new Artikal(rs.getInt("sifra_artikla"),
                    rs.getString("naziv"),rs.getDouble("cena"), 
                    rs.getString("boja"), rs.getString("memorija"), tp);
            

            StavkaRacuna sp = new StavkaRacuna(racun, rs.getInt("redni_broj"),
                    rs.getInt("kolicina"), a);

            lista.add(sp);
        }

        rs.close();
        return lista;    }

    @Override
    public String koloneZaInsert() {
          return " (racun,redni_broj,kolicina,sifra_artikla) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        
        return " racun = " + racun.getSifraRacuna()+ 
                " AND redni_broj =" + redniBroj;
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getSifraRacuna() + ", " + redniBroj + ", "
                + " " + kolicina + ", " + artikal.getSifraArtikla() ;
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
         return " WHERE R.SIFRA_RACUNA = " + racun.getSifraRacuna();
        }
    
    
    
    
    
    
    
}
