/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.korisnik;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SODodajKorisnika extends AbstractSO{
    
    
     protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }

        Korisnik k = (Korisnik) ado;

        ArrayList<Korisnik> sviKorisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Korisnik korisnik : sviKorisnici) {
            if (korisnik.getKorisnickoIme().equals(k.getKorisnickoIme())) {
                throw new Exception("Vec postoji korisnik sa tim korisnickim imenom!");
            }
            
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
