package processor;

import java.io.IOException;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;
import processor.input.*;

/*
 *  Klasa model odpowiada za przetwarzanie w modelu mvc i tworzy wykres po wczytaniu danych z pliku.
 *  Błąd ewentualny przy próbie wczytania czy zwalidowania pliku jest przekierowywany do klasy wywołującej daną klasę.
 * 
 */
public class Model {
    public JFreeChart open(String filePath) throws Exception{
        ArrayList<String> rowData;
        XYSeries series; 
        XYSeriesCollection data;
        JFreeChart chart;
  
        //Wczytuje dany z pliku przesłanego z klasy kontrolera parametrem i zzapisuje je w dynamicznej liście
        rowData = FileLoader.load(filePath);                
        series = new XYSeries("Dane z pliku");
                
        //Dzieli pobrane weirsze danych z listy na poszczególne pary wartości x, y i zapisuje je w klasie XYSeries wykresu
        for (String retval: rowData) {
            String[] a = retval.split(" ");
            series.add(Float.parseFloat(a[0]), Float.parseFloat(a[1]));
        }
                
        //Tworzy kolekcję danych i generuje na ich podstawie wykres
        data = new XYSeriesCollection(series);                
        chart = ChartFactory.createXYLineChart("XY Series", "X", "Y", data, PlotOrientation.VERTICAL, true, true, false);
            
        //Zwraca utworzony wykres
        return chart;
    }
}
