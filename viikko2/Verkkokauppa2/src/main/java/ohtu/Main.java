package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.iKauppa;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        
        Kauppa kauppa = (Kauppa) ctx.getBean("kauppa");

        asioi(kauppa);
        asioi_osa_2_kaupan_vastaisku(kauppa);
        
        // kirjanpito
        for (String tapahtuma : ctx.getBean(Kirjanpito.class).getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
    
    public static void asioi(Kauppa kauppa) {
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");
    }
    
    public static void asioi_osa_2_kaupan_vastaisku(Kauppa kauppa) {
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }
        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");
    }
}
