package controller;

import gui.*;
import gui.messages.*;
import processor.*;
import javax.swing.*;

/**
 *  Klasa odpowiadająca za odbiór komunikatów od widoku w modelu mvc i wykonywanie odpowiedniej akcji.
 * 
 */
public class Controller {
    private Model model;
    private View view;
    
    //Konstruktor przypisuje instancje modelu i widoku celem umożliwiena wykonywania metod na tych obiektach
    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }
    
    //Wykonanie odpowiedniej akcji
    public void doAction(Message click){
        //Sprawdza czy przesłany komunikat jest odpowiednią instancją
        if (click instanceof OpenMessage){  
            try{
                //Uruchamia metodę wyswietlenia wykresu nowym oknie w klasie widok i przekazuje argument z klasy model bedący wygenerowanym wykresem
                view.showChart(model.open(((OpenMessage)click).filePath));
                //PRzechwytuje ewentualne błędy i wyświetla komunikat błędu
            } catch(Exception ex){        
                JOptionPane.showMessageDialog(view.quit, "Blad wczytania danych z pliku.", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        }
    }
}
