package gui;

import controller.Controller;
import gui.messages.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;

/**
 *  Klasa odpowiada za wyśletlanie elementów graficznych w modelu mvc.
 * 
 */
public class View extends JFrame{
    public Controller controller;
    public JMenuBar mb;
    public JMenu file;
    public JMenuItem open;
    public JMenuItem quit;
    public JMenu help;
    public JMenuItem about;
    
    //Konstruktor klasy inicjalizuje obiekty na pierwszym okienku.
    public View(){
        //Utworzenie ramki i nadanie waściwości
        super("Generator Wykresów");
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              
               
        //Utworzenie menu i podmenu
        mb = new JMenuBar();
        setJMenuBar(mb);
        
        file = new JMenu("Plik");
        mb.add(file);
        open = new JMenuItem("Otw\u00f3rz");
        file.add(open);
        quit = new JMenuItem("Zamknij");
        file.add(quit);
        
        help = new JMenu("Pomoc");
        mb.add(help);
        about = new JMenuItem("O programie...");
        help.add(about);
        
        //Przypisanie akcji do elementów menu wywołanych wybraniem elementu
        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(open);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    controller.doAction(new OpenMessage(fc.getSelectedFile().toString()));
                }
            }
        });
        
        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0); 
            }
        });
        
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(about, 
                        "Generator Wykresów v.1.0\nAutor:\nProjekt na zaliczenie",
                        "O programie\u2026",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        setSize(400,200);
        setVisible(true);
    }
    
    //Przypisanie referencji do obiektu kontrolera w celu wywoływania jego metod
    public void setReference(Controller controller){
        this.controller = controller;
    }

    //Metoda umożliwia wyświetlenie osobnego okna zawierającego wykres przesłany poprzez argument oraz przycisków dialogowych
    public void showChart(JFreeChart chart) {
        //Generuje wygląd okienka
        JFrame frame = new JFrame("Wykres liniowy");
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  

        ChartPanel chartPanel = new ChartPanel(chart);
        
        JPanel panel = new JPanel();
        JLabel labelXmin = new JLabel("Xmin");
        panel.add(labelXmin);
        JTextField tekstXmin = new JTextField("", 4);
        panel.add(tekstXmin);
        JLabel labelXmax = new JLabel("Xmax");
        panel.add(labelXmax);
        JTextField tekstXmax = new JTextField("", 4);
        panel.add(tekstXmax);
        JLabel labelYmin = new JLabel("Ymin");
        panel.add(labelYmin);
        JTextField tekstYmin = new JTextField("", 4);
        panel.add(tekstYmin);
        JLabel labelYmax = new JLabel("Ymax");
        panel.add(labelYmax);
        JTextField tekstYmax = new JTextField("", 4);
        panel.add(tekstYmax);
        JButton przyciskSkaluj = new JButton("Skaluj");
        panel.add(przyciskSkaluj);
        JButton przyciskZapisz = new JButton("Zapisz PNG");
        panel.add(przyciskZapisz);
        JButton przyciskKolor = new JButton("Zmień kolor");
        panel.add(przyciskKolor);
        
        //Przypisuje akcję do przycisku Skaluj umożliwiającą zmianę wartości skali
        przyciskSkaluj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    //Pobiera wartość z istniejącego wykresu
                    XYPlot xyPlot = (XYPlot) chart.getPlot();
                    
                    ValueAxis domainAxis = xyPlot.getDomainAxis();
                    ValueAxis rangeAxis = xyPlot.getRangeAxis();

                    //Ustala wartości minimalne i maksymalne wykresu pobierając wartości z pól tekstowych i wstawiając do metod przekonwertowane do typów Double. 
                    domainAxis.setLowerBound(Double.parseDouble(tekstXmin.getText()));
                    domainAxis.setUpperBound(Double.parseDouble(tekstXmax.getText()));
                    rangeAxis.setLowerBound(Double.parseDouble(tekstYmin.getText()));
                    rangeAxis.setUpperBound(Double.parseDouble(tekstYmax.getText()));
                } catch(Exception e) {
                }
            }
        });

        //Przypisuje akcję zapisania do pliku do przycisku "Zapisz PNG"
        przyciskZapisz.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try {
                    ChartUtilities.saveChartAsPNG(new File("chart.png"), chart, 400, 300);
                } catch(IOException ioe) {
                }
            }
        });
        
        //Przypisuje akcję zmiany koloru do przyciku ZmienKolor
        przyciskKolor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                XYPlot plot = chart.getXYPlot();
                Color lineColor = JColorChooser.showDialog(frame, "Wybierz kolor linii wykresu", Color.white);
                if(lineColor != null){
                    plot.getRenderer().setSeriesPaint(0, lineColor);
                }   
            }
        });        
 
        //Fragment odpowiadający za rozmieszczenie elementów grafiznych według menedzera rozkładu BorderLayout
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(chartPanel, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);        
        
        frame.setSize(1000,600);
        frame.setVisible(true);
    }
}