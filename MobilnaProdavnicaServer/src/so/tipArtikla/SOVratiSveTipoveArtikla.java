/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.tipArtikla;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.TipArtikla;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SOVratiSveTipoveArtikla extends AbstractSO{
     private ArrayList<TipArtikla> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof TipArtikla)) {
            throw new Exception("Prosledjeni objekat nije instanca klase TipArtikla!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tipoviArtikala = DBBroker.getInstance().select(ado);
        lista = (ArrayList<TipArtikla>) (ArrayList<?>) tipoviArtikala;
    }

    public ArrayList<TipArtikla> getLista() {
        return lista;
    }

}
