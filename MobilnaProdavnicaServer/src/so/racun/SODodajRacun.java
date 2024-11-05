/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import com.mysql.cj.xdevapi.Statement;
import db.DBBroker;
import domen.AbstractDomainObject;
import domen.Racun;
import domen.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.spi.StateFactory;
import so.AbstractSO;

/**
 *
 * @author Korisnik
 */
public class SODodajRacun extends AbstractSO{
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Racun racun= (Racun) ado;
        int key=0;
        PreparedStatement ps=DBBroker.getInstance().insert(ado);
        ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                 key = (int) rs.getLong(1);
            }
        
        racun.setSifraRacuna(key);
        
        for (StavkaRacuna stavkaRacuna : racun.getListaStavki()) {
            stavkaRacuna.setRacun(racun);
            AbstractDomainObject ado2=stavkaRacuna;
            DBBroker.getInstance().insert(ado2);
        }
        
            
           
    }
}
