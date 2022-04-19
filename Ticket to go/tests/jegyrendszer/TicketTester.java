package jegyrendszer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class TicketTester {
	Ticket ti1,ti2;
	
	@Before
	public void setUp() {
		LocalDateTime time1 = LocalDateTime.parse("2020.04.19-18:45", TicketToGo.formatter);
		LocalDateTime time2 = LocalDateTime.parse("2020.11.25-20:25",TicketToGo.formatter);
		ti1 = new Ticket(1,1,1,"Budapest-Keleti",time1,"Bozd",time2);
	}

	@Test
	public void toStringTester() {
		assertEquals("Ticket osztály toString metódusának tesztje.","Vonatszám: 1 Kocsiszám: 1 Ülésszám: 1 Kiinduló állomás: Budapest-Keleti Indulás dátuma: 2020.04.19-18:45 Célállomás: Bozd Érkezési idõ: 2020.11.25-20:25",ti1.toString());
	}

}
