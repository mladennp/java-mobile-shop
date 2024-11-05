/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Racun extends AbstractDomainObject {

    private int sifraRacuna;
    private double ukupanIznos;
    private double uplaceno;
    private double povracaj;
    private double iznosPDV;
    private Korisnik korisnik;
    private ArrayList<StavkaRacuna> listaStavki;
    

    public Racun() {
    }

    public Racun(int sifraRacuna, double ukupanIznos, double uplaceno, double povracaj, double iznosPDV, Korisnik korisnik) {
        this.sifraRacuna = sifraRacuna;
        this.ukupanIznos = ukupanIznos;
        this.uplaceno = uplaceno;
        this.povracaj = povracaj;
        this.iznosPDV = iznosPDV;
        this.korisnik = korisnik;
       
    }

    
  
    
    
    

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public int getSifraRacuna() {
        return sifraRacuna;
    }

    public void setSifraRacuna(int sifraRacuna) {
        this.sifraRacuna = sifraRacuna;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public double getPovracaj() {
        return povracaj;
    }

    public void setPovracaj(double povracaj) {
        this.povracaj = povracaj;
    }

    public double getIznosPDV() {
        return iznosPDV;
    }

    public void setIznosPDV(double iznosPDV) {
        this.iznosPDV = iznosPDV;
    }

   

    public void setUplaceno(double uplaceno) {
        this.uplaceno = uplaceno;
    }

    public double getUplaceno() {
        return uplaceno;
    }

    public void setListaStavki(ArrayList<StavkaRacuna> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public ArrayList<StavkaRacuna> getListaStavki() {
        return listaStavki;
    }
    
    
    
    

    @Override
    public String nazivTabele() {
        return " racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN KORISNIK K ON (K.KORISNIK_ID = R.KORISNIK) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Korisnik k = new Korisnik(rs.getInt("korisnik_id"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("korisnicko_ime"), rs.getString("sifra"));
             
           
            Racun racun = new Racun(rs.getInt("sifra_racuna"), rs.getDouble("ukupan_iznos"),
                    rs.getDouble("uplaceno"), rs.getDouble("povracaj"),
                    rs.getDouble("iznosPDV"),k);
            lista.add(racun);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ukupan_iznos, povracaj, iznosPDV, korisnik, uplaceno) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " sifra_racuna = " + sifraRacuna;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" +  ukupanIznos + "' , '" + povracaj + " ', '" + iznosPDV + "', '" + korisnik.getKorisnikID() + "', '" +uplaceno + " '";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " povracaj = " + povracaj + ", iznosPDV =" + iznosPDV+
                ", korisnik =" + korisnik.getKorisnikID() +
                ", uplaceno =" + uplaceno;
    }

    @Override
    public String uslov() {
        return "";
    }

}
