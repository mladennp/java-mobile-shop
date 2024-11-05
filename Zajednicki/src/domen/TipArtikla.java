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
public class TipArtikla extends AbstractDomainObject {

    private int sifraTipa;
    private String nazivTipa;

    public TipArtikla() {
    }

    public TipArtikla(int sifraTipa, String nazivTipa) {
        this.sifraTipa = sifraTipa;
        this.nazivTipa = nazivTipa;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
    }

    public int getSifraTipa() {
        return sifraTipa;
    }

    public void setSifraTipa(int sifraTipa) {
        this.sifraTipa = sifraTipa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final TipArtikla other = (TipArtikla) obj;
        return this.sifraTipa == other.sifraTipa;
    }

    @Override
    public String nazivTabele() {
        return " tip_artikla ";
    }

    @Override
    public String alijas() {
        return " ta ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            TipArtikla ta = new TipArtikla(rs.getInt("sifra_tipa"),
                    rs.getString("naziv_tipa"));

            lista.add(ta);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return "sifra_tipa = " + sifraTipa;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
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
        return  nazivTipa;
    }

    
    
    
    
}
