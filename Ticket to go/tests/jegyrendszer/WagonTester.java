package jegyrendszer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class WagonTester {
	Wagon w1, w2;
	@Before
	public void setUp() {
		w1 = new Wagon(1);
		w2 = new Wagon(2);
	}
	@Test
	public void NumberOfFreeSeatsOnEmptyWagonTest() {
		assertEquals("Egy, m�g �rintetlen kocsiban a szabad �l�sek sz�ma 30 kell legyen.",30, w1.getAmountOfFreeSeats() );
	}
	@Test
	public void NumberOfFreeSeatsWhenOneSeatReservedTest() {
		w1.reserveSeat();
		assertEquals("Ha egy sz�k m�r foglalt, a kocsiban a szabad �l�sek sz�ma 29 kell legyen.",29, w1.getAmountOfFreeSeats() );
	}

}
