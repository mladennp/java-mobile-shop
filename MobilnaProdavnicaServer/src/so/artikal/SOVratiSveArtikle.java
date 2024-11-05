/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.artikal;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Artikal;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSveArtikle extends AbstractSO {
     private ArrayList<Artikal> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Artikal)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Artikal!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> proizvodi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Artikal>) (ArrayList<?>) proizvodi;
    }

    public ArrayList<Artikal> getLista() {
        return lista;
    }
}
