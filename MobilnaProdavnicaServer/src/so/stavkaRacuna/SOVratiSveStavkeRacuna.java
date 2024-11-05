/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavkaRacuna;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.StavkaRacuna;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSveStavkeRacuna extends AbstractSO{
     private ArrayList<StavkaRacuna> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaRacuna!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> stavkeRacuna = DBBroker.getInstance().select(ado);
        lista = (ArrayList<StavkaRacuna>) (ArrayList<?>) stavkeRacuna;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }
}
