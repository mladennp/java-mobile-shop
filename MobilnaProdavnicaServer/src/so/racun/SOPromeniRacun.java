/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Racun;
import domen.StavkaRacuna;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOPromeniRacun extends AbstractSO{
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
         Racun racun=(Racun) ado;
        
        DBBroker.getInstance().update(ado);
        
         for (StavkaRacuna stavkaRacuna : racun.getListaStavki()) {
            stavkaRacuna.setRacun(racun);
            AbstractDomainObject ado2=stavkaRacuna;
            DBBroker.getInstance().insert(ado2);
        }
        
    }

}
