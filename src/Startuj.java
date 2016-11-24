/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import controller.*;
import gui.*;
import javax.swing.JFrame;
import processor.*;
/**
 *  Klasa uruchomieniowa. Łączy ze sobą poszcególne klasy modelu mvc.
 * 
 */
public class Startuj {
    public static void main (String[] args){
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        //Zwraca referencję zwrotną do kontrolera
        view.setReference(controller);
    }
}
