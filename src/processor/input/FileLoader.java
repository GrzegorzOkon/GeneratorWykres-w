package processor.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Klasa wczytuje plik.
 * 
 */
public class FileLoader {
    //Metoda wczytuje plik, którego ścieżka jest przekazana parametrem.
    public static ArrayList load(String nazwa) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(nazwa));
        ArrayList<String> al = new ArrayList<>();
        String linia = null;
        
        //Przypisuje kolejne wiersze do dynamicznej listy
        while((linia = br.readLine()) != null)
        {
            al.add(linia);
        }
       //Zamyka strumieńdo pliku
        br.close();
        
        return al;
    }
}
