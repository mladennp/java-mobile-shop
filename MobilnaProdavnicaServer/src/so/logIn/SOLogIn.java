/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.logIn;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOLogIn extends AbstractSO{
    
    Korisnik ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Korisnik k = (Korisnik) ado;

        ArrayList<Korisnik> korisnici
                = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().equals(k.getKorisnickoIme())
                    && korisnik.getSifra().equals(k.getSifra())) {
                ulogovani = korisnik;
                return;
            }
        }

        throw new Exception("Ne postoji korisnik sa tim kredencijalima.");
        
    }

    public Korisnik getUlogovani() {
        return ulogovani;
    }
}
