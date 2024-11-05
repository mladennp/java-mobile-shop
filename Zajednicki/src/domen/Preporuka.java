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
public class Preporuka extends AbstractDomainObject {

    private Artikal artikal;
    private Korisnik korisnik;
    private String opis;
    private String razlog;

    public Preporuka() {
    }

    public Preporuka(Artikal artikal, Korisnik korisnik, String opis, String razlog) {
        this.artikal = artikal;
        this.korisnik = korisnik;
        this.opis = opis;
        this.razlog = razlog;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String nazivTabele() {
        return "preporuka";
    }

    @Override
    public String alijas() {
        return "p";
    }

    @Override
    public String join() {
        return " JOIN ARTIKAL A ON (A.SIFRA_ARTIKLA = P.ARTIKAL) "
                + "JOIN KORISNIK K ON (K.KORISNIK_ID= P.KORISNIK) JOIN TIP_ARTIKLA TP ON (A.TIP_ARTIKLA=TP.SIFRA_TIPA) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Korisnik k = new Korisnik(rs.getInt("korisnik_id"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("korisnicko_ime"), rs.getString("sifra"));

            TipArtikla tp = new TipArtikla(rs.getInt("sifra_tipa"),
                    rs.getString("naziv_tipa"));

            Artikal a = new Artikal(rs.getInt("sifra_artikla"),
                    rs.getString("naziv"), rs.getDouble("cena"),
                    rs.getString("boja"), rs.getString("memorija"), tp);

            Preporuka p = new Preporuka(a, k, rs.getString("opis"), rs.getString("razlog"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (artikal, korisnik, opis, razlog) ";

    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " artikal = " + artikal.getSifraArtikla() + "korisnik =" + korisnik.getSifra();
    }

    @Override
    public String vrednostiZaInsert() {
       return "'" + artikal.getSifraArtikla() + "', '" + korisnik.getKorisnikID() + "', "
                + "'" + opis + "', '" + razlog +  "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String toString() {
        return artikal.getNaziv();
    }
    
    

}
