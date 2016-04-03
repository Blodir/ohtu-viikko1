/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pyry
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
        stats = new Statistics(readerStub);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSearch() {
        assertEquals((String.format("%-20s", "Gretzky") + " " + "EDM" + " " + String.format("%2d",35) + " + " 
                + String.format("%2d",89) + " = " + 124), stats.search("Gretzky").toString());
    }
    
    @Test
    public void testTeamList() {
        assertEquals("[Semenko              EDM  4 + 12 = 16, Kurri                EDM 37 + 53 = 90, Gretzky              EDM 35 + 89 = 124]", stats.team("EDM").toString());
    }
    
    @Test
    public void testTopScorers() {
        assertEquals("[Gretzky              EDM 35 + 89 = 124, Lemieux              PIT 45 + 54 = 99, Yzerman              DET 42 + 56 = 98]", stats.topScorers(2).toString());
    }
    
    @Test
    public void testSearchFail() {
        assertEquals(null, stats.search("pekka"));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
