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
public class Artikal extends AbstractDomainObject{
    
    private int sifraArtikla;
    private String naziv;
    private double cena;
    private String boja;
    private String memorija;
    private TipArtikla tipArtikla;
    

    public Artikal() {
    }

    public Artikal(int sifraArtikla, String naziv, double cena, String boja, String memorija, TipArtikla tipArtikla) {
        this.sifraArtikla = sifraArtikla;
        this.naziv = naziv;
        this.cena = cena;
        this.boja = boja;
        this.memorija = memorija;
        this.tipArtikla = tipArtikla;
    }

    

    public String getMemorija() {
        return memorija;
    }

    public void setMemorija(String memorija) {
        this.memorija = memorija;
    }

    public int getSifraArtikla() {
        return sifraArtikla;
    }

    public void setSifraArtikla(int sifraArtikla) {
        this.sifraArtikla = sifraArtikla;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artikal other = (Artikal) obj;
        return this.sifraArtikla == other.sifraArtikla;
    }

    public TipArtikla getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(TipArtikla tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    @Override
    public String nazivTabele() {
        return " artikal ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return "JOIN TIP_ARTIKLA TP ON (TP.SIFRA_TIPA=A.TIP_ARTIKLA) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
             ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
                     TipArtikla tp = new TipArtikla(rs.getInt("sifra_tipa"),
                    rs.getString("naziv_tipa"));
            
                    Artikal a = new Artikal(rs.getInt("sifra_artikla"),
                    rs.getString("naziv"),rs.getDouble("cena"), 
                    rs.getString("boja"), rs.getString("memorija"), tp);
            
                    

            lista.add(a);
        }

        rs.close();
        return lista;    }

    @Override
    public String koloneZaInsert() {
        return " (naziv,cena,boja,memorija,tip_artikla) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
          return "sifra_artikla = " + sifraArtikla;
        }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + cena + "', "
                + "'" + boja + "', '" + memorija +  "', '" + tipArtikla.getSifraTipa() + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "naziv= '" + naziv + "',cena= '" + cena + "',boja= "
                + "'" + boja + "',memorija= '" + memorija +  "',tip_artikla='" + tipArtikla.getSifraTipa() + "'";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public String toString() {
        return naziv;
    }

    
    
    
    
    
    
    
    
}
