/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.artikal;

import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Artikal;
import java.util.ArrayList;
import javax.swing.SpringLayout;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SoPromeniArtikal extends AbstractSO{
    
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Artikal)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Artikal!");
        }

        Artikal a = (Artikal) ado;

//        ArrayList<Artikal> sviArtikli = (ArrayList<Artikal>) (ArrayList<?>) DBBroker.getInstance().select(ado);
//
//        for (Artikal artikal : sviArtikli) {
//            if (artikal.getSifraArtikla()==(a.getSifraArtikla())) {
//                System.out.println("Naso je");
//                
//            }
//        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}
