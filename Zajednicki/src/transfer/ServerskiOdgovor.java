/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Korisnik
 */
public class ServerskiOdgovor implements Serializable{
    
    private Exception exc;
    private Object parametar;
    private StatusOdgovora status;
    
    

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Exception exc, Object parametar, StatusOdgovora status) {
        this.exc = exc;
        this.parametar = parametar;
        this.status = status;
    }

    

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public Exception getExc() {
        return exc;
    }

    public void setExc(Exception exc) {
        this.exc = exc;
    }

    

    public StatusOdgovora getStatus() {
        return status;
    }

    public void setStatus(StatusOdgovora status) {
        this.status = status;
    }
    
    
    
            
    
    
    
}
