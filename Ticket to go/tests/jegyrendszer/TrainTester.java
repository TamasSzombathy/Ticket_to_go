package jegyrendszer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class TrainTester {
	Train t1, t2;
	@Before
	public void setUp() {
		t1 = new Train(1,"DRPE","2020.04.19-18:45","GRND","2020.11.25-20:25");
		LocalDateTime time1 = LocalDateTime.parse("2020.04.19-18:45", TicketToGo.formatter);
		LocalDateTime time2 = LocalDateTime.parse("2020.11.25-20:25",TicketToGo.formatter);
		t2 = new Train(1,"DRPE",time1,"GRND",time2);
	}
	@Test
	public void toStringTest() {
		assertEquals("A toString metódus megfelelõ kimenetet ad-e.", "Vonatszám: 1 Kiinduló állomás: DRPE Indulás dátuma: 2020.04.19-18:45 Célállomás: GRND Érkezési idõ: 2020.11.25-20:25", t1.toString());
	}
	@Test
	public void ReserveSeatOnEmptyTrainTest() {
		t1.connectWagons(3); 	//Ha nem csatolunk a vonathoz kocsikat, nem lesz hol helyet foglaljon a függvény.
		int[] expectedData = new int[] {1,1};
		int[] obtainedData = t1.reserveSeat();
		assertEquals("A reserveSeat egy üres vonatnál valóban az elsõ kocsiban az elsõ ülésszámot foglalja-e le.",expectedData[0]+expectedData[1], obtainedData[0]+obtainedData[1]);
	}
	@Test
	public void ReserveSeatWhenOneIsAlreadyTaken() {
		t1.connectWagons(3); 	//Ha nem csatolunk a vonathoz kocsikat, nem lesz hol helyet foglaljon a függvény.
		t1.reserveSeat();
		int[] expectedData = new int[] {1,2};
		int[] obtainedData = t1.reserveSeat();
		assertEquals("A reserveSeat egy vonatnál ahol az elsõ ülés le van foglalva valóban az elsõ kocsiban a második ülésszámot foglalja-e le.",expectedData[0]+expectedData[1], obtainedData[0]+obtainedData[1]);
	}
	@Test
	public void GetFreeSeatsOnEmptyTrainTest() {
		assertEquals("Üres vonaton(amihez nincsen kocsi csatolva) a helyek száma 0.",0,t1.getFreeSeats());
	}
	@Test
	public void GetFreeSeatsWhenThreeWagonConnectedTest() {
		t1.connectWagons(3);	//Ha nem csatolunk a vonathoz 3 kocsit, nem lesz 3*30, azaz 90 hely a vonaton.
		assertEquals("A vonathoz amihez 3 kocsit hozzákötünk a szabad helyek száma 90 kell legyen.",90,t1.getFreeSeats());
	}
	@Test
	public void ReserveSeatWhenWagonNotConnectedTest() {
		assertEquals("Ha a vonathoz nincs kocsi csatlakoztatva, akkor a reserveSeat null értéket kell visszaadjon.",null,t1.reserveSeat());
	}
	@Test
	public void GetNumberOfWagonsWhenZeroWagonConnectedTest() {
		assertEquals("Üres vonaton(amihez nincsen kocsi csatolva) a getNumberOfWagons 0-át ad.",0,t1.getNumberOfWagons());
	}
	@Test
	public void ConnectOneWagonTest() {
		t1.connectWagons(1);
		assertEquals("1 kocsit csatlakoztattunk, az eredmény tehát 1 kell legyen.",1,t1.getNumberOfWagons());
	}
}
