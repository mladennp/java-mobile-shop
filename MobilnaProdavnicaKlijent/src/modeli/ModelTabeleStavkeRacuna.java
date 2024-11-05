/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Artikal;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.KlijentskiKontroler;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel{

   private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Redni broj", "Naziv artikla", "Kolicina", "Cena"};
    private String parametar = "";

    public ModelTabeleStavkeRacuna() {
          try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ModelTabeleArtikal.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
         return kolone[column];
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         StavkaRacuna stavkaRacuna=lista.get(rowIndex);
            
        
        switch (columnIndex) {
            case 0:
                return stavkaRacuna.getRedniBroj();
            case 1:
                return stavkaRacuna.getArtikal().getNaziv();
            case 2:
                return stavkaRacuna.getKolicina();
            case 3:
                return stavkaRacuna.getArtikal().getCena();
            

            default:
                return null;
        }
    }
    
    public StavkaRacuna getSelectedArtikal(int row) {
        return lista.get(row);
    }
    
    
    public void refreshTable(){
        fireTableDataChanged();
    }
    
    public ArrayList<StavkaRacuna> vratiListu(){
        return lista;
    }
    
    public void dodaj(StavkaRacuna stavka){
        lista.add(stavka);
        fireTableDataChanged();
    }

    public void ukloni(int selectedRow) {
        lista.remove(selectedRow);
        fireTableDataChanged();
        
    }

    public StavkaRacuna stavka(int selectedRow) {
        return lista.get(selectedRow);
    }
}
