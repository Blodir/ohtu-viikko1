/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Pyry
 */
public class KauppaTest {
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanKaksiEriTuotetta() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(44);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juustokakku", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("nimi", "12345");

        verify(pankki).tilisiirto(eq("nimi"), eq(44), eq("12345"), eq("33333-44455"), eq(17));
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaTiedoillaKaksiSamaaTuotetta() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(44);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(3);
        when(varasto.saldo(2)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("nimi", "12345");

        verify(pankki).tilisiirto(eq("nimi"), eq(44), eq("12345"), eq("33333-44455"), eq(10));
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanKaksiTuotettaToinenLoppu() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(45);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.saldo(2)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juustokakku", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("nimi", "12345");

        verify(pankki).tilisiirto(eq("nimi"), eq(45), eq("12345"), eq("33333-44455"), eq(12));
    }

    @Test
    public void nollaaEdellisenOstoksenTiedot() {

        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(10);

        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(1);
        when(varasto.saldo(2)).thenReturn(4);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juustokakku", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("nimi", "12345");

        verify(viite, times(1)).uusi();

        verify(pankki).tilisiirto(eq("nimi"), eq(10), eq("12345"), eq("33333-44455"), eq(17));

        when(viite.uusi()).thenReturn(11);
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.saldo(2)).thenReturn(3);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juustokakku", 12));

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("nimi", "12345");

        verify(viite, times(2)).uusi();

        verify(pankki).tilisiirto(eq("nimi"), eq(11), eq("12345"), eq("33333-44455"), eq(12));

    }

    @Test
    public void koristaPoisto() {
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juustokakku", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);

        Tuote jaatelo = varasto.haeTuote(2);
        verify(varasto, times(1)).otaVarastosta(jaatelo);
        k.poistaKorista(2);
        verify(varasto, times(1)).palautaVarastoon(jaatelo);
    }
}
