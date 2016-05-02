/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author Pyry
 */
public class Summa implements Komento {
 
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private int edellinenSyote;

    public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.edellinenSyote = 0;
    }
    
    @Override
    public void suorita() {
        int syote = Integer.parseInt(syotekentta.getText());
        edellinenSyote = syote;
        sovellus.plus(syote);
        tuloskentta.setText("" + sovellus.tulos());
    }

    @Override
    public void peru() {
        sovellus.miinus(edellinenSyote);
        tuloskentta.setText("" + sovellus.tulos());
    }
    
}
