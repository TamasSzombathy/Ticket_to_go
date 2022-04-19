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
		assertEquals("Ticket oszt�ly toString met�dus�nak tesztje.","Vonatsz�m: 1 Kocsisz�m: 1 �l�ssz�m: 1 Kiindul� �llom�s: Budapest-Keleti Indul�s d�tuma: 2020.04.19-18:45 C�l�llom�s: Bozd �rkez�si id�: 2020.11.25-20:25",ti1.toString());
	}

}
