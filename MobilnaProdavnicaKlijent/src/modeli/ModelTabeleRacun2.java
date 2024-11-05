/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleRacun2 extends AbstractTableModel{ 
    
    public ArrayList<Racun> listaRacuna;
    private String[] kolone = {"Sifra", "ukupan_iznos", "povracaj", "IznosPDV", "Korisnik", "Uplaceno"};
    private String parametar = "";
    
    public ModelTabeleRacun2() {
          try {
            listaRacuna = new ArrayList<>();
        } catch (Exception ex) {
        }
    
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Sifra Racuna";
            case 1:
                return "Ukupan Iznos";
            case 2:
                return "Povracaj";
            case 3:
                return "Iznos pdv";
            case 4:
                return "Zaposleni";
            case 5:
                return "Uplaceno";
             default:
                return null;

        }    
       
                
                
    }
    
   
    
    
    @Override
    public int getRowCount() {
        return listaRacuna.size();
        }

    @Override
    public int getColumnCount() {
        return kolone.length;
        }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            Racun racun=listaRacuna.get(rowIndex);
            
        
        switch (columnIndex) {
            case 0:
                return racun.getSifraRacuna();
            case 1:
                return racun.getUkupanIznos();
            case 2:
                return racun.getPovracaj();
            case 3:
                return racun.getIznosPDV();
            case 4:
                return racun.getKorisnik().getIme();
            case 5:
                return racun.getUplaceno();
            

            default:
                return null;
        }    }
    
    
    public Racun getSelectedArtikal(int row) {
        return listaRacuna.get(row);
    }
    
     public void refreshTable(){
        fireTableDataChanged();
    }
     
     
     public ArrayList<Racun> vratiListu(){
        return listaRacuna;
    }
     
     
     public void dodaj(Racun racun){
        listaRacuna.add(racun);
        fireTableDataChanged();
    }

    public void obrisi(int selectedRow) {
        listaRacuna.remove(selectedRow);
        fireTableDataChanged();   
    }
    
    public Racun vratiRacun(int selectedRow){
        return listaRacuna.get(selectedRow);
    }
     
     
     
}
