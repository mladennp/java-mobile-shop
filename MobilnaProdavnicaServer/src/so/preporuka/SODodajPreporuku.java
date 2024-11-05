/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.preporuka;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Artikal;
import domen.Preporuka;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SODodajPreporuku extends AbstractSO{
      protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Preporuka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Preporuka!");
        }

        

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}
