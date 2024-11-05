/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Artikal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import kontroler.KlijentskiKontroler;

/**
 *
 * @author Korisnik
 */
public class ModelTabeleArtikal extends AbstractTableModel{

     private ArrayList<Artikal> lista;
    private String[] kolone = {"ID", "Naziv", "Cena", "Boja", "Memorija", "Tip Artikla"};
    private String parametar = "";

    public ModelTabeleArtikal() {
          try {
            lista = KlijentskiKontroler.getInstance().vratiSveArtikle();
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
            Artikal a = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getSifraArtikla();
            case 1:
                return a.getNaziv();
            case 2:
                return a.getCena();
            case 3:
                return a.getBoja();
            case 4:
                return a.getMemorija();
            case 5:
                return a.getTipArtikla().getSifraTipa();

            default:
                return null;
        }
    }
    
    public Artikal getSelectedArtikal(int row) {
        return lista.get(row);
    }
    
    
    public void refreshTable(){
        fireTableDataChanged();
    }
    
}
