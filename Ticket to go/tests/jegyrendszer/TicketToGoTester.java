package jegyrendszer;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class TicketToGoTester {
	Train t1;
	Ticket ti2;
	
	boolean trainDataEqualityCheck(Train a, Train b) {
		if (a.getNumber()==b.getNumber() & a.getNumberOfWagons() == b.getNumberOfWagons() & a.getStartPoint().equals(b.getStartPoint()) & a.getStartTime().equals(b.getStartTime()) & a.getEndPoint().equals(b.getEndPoint()) & a.getEndTime().equals(b.getEndTime())) {
			return true;
		} else {
			return false;
		}	
	}
	
	
	@Before
	public void setUp() {
		t1 = new Train(1,"DRPE","2020.04.19-18:45","GRND","2020.11.25-20:25");
		LocalDateTime time1 = LocalDateTime.parse("2020.04.19-18:45", TicketToGo.formatter);
		LocalDateTime time2 = LocalDateTime.parse("2020.11.25-20:25",TicketToGo.formatter);
		ti2 = new Ticket(1,1,1,"Budapest-Keleti",time1,"Bozd",time2);
	}
	
	@Test
	public void addTrainTest() {
		TicketToGo.trains.clear();
		TicketToGo.trains.add(t1);
		
		assertEquals("Üres listához ha hozzáadjuk a t1 vonatot akkor az elsõ eleme meg kell egyezzen a t1 vonattal.", true, trainDataEqualityCheck(t1, TicketToGo.trains.get(0)));
	}
}
