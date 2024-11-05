/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRacuna;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Artikal;
import domen.StavkaRacuna;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOObrisiStavku extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
                if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Stavka Racunas!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
    
}
