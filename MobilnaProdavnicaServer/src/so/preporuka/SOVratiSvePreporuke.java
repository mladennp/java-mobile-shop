/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.preporuka;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Artikal;
import domen.Preporuka;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSvePreporuke extends AbstractSO{
      private ArrayList<Preporuka> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Preporuka)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Preporuka!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> preporuke = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Preporuka>) (ArrayList<?>) preporuke;
    }

    public ArrayList<Preporuka> getLista() {
        return lista;
    }
}
