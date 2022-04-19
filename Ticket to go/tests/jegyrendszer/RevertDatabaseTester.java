package jegyrendszer;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

public class RevertDatabaseTester {
	Train t1, t2, t3;
	
	boolean trainDataEqualityCheck(Train a, Train b) {
		if (a.getNumber()==b.getNumber() & a.getNumberOfWagons() == b.getNumberOfWagons() & a.getStartPoint().equals(b.getStartPoint()) & a.getStartTime().equals(b.getStartTime()) & a.getEndPoint().equals(b.getEndPoint()) & a.getEndTime().equals(b.getEndTime())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	@Before
	public void setUp() {
		t1 = new Train(1,"Belgr�d","2020.04.19-18:45","T�t�","2020.11.25-20:25");
		t2 = new Train(2,"Kukucs","2021.08.01-03:15","Eszes","2021.08.01-05:15");
		t3 = new Train(3,"Bekecs","2021.05.03-16:31","Eger","2021.05.03-17:31");
	}

	@Test
	public void RevertDatabaseTest() throws IOException {
		TicketToGo.menupoints.add(new TrainMaker(1,"Vonat l�trehoz�sa"));
		TicketToGo.menupoints.add(new TicketMaker(2,"Jegy kiad�sa"));
		TicketToGo.menupoints.add(new TrainLister(3,"Vonatok kilist�z�sa"));
		TicketToGo.menupoints.add(new OverwriteTrain(4,"Vonat adatainak fel�l�r�sa"));
		TicketToGo.menupoints.add(new DeleteTrain(5,"Vonat t�rl�se"));
		TicketToGo.menupoints.add(new RevertDatabase(6,"V�ltoztat�sok visszavon�sa"));
		TicketToGo.menupoints.add(new Exit(7,"Ment�s �s kil�p�s"));
		TicketToGo.trains.clear();
		TicketToGo.trains.add(t1);
		FileOutputStream vonatadatok = new FileOutputStream(System.getProperty("user.dir")+"/application_data"+"/Trains");
        ObjectOutputStream vonatok = new ObjectOutputStream(vonatadatok);
        vonatok.writeObject(TicketToGo.trains);
        vonatok.close();
        vonatadatok.close();
        TicketToGo.trains.add(t2);
        TicketToGo.trains.add(t3);
        TicketToGo.menupoints.get(5).action();
        
        assertEquals("1 vonatot adtunk hozz� miel�tt mentett�nk volna, 2-�t k�zben hozz�adtunk, de nem mentett�k, teh�t a vissza�ll�t�sna�l 1 vonat kell legyen a list�ban.",1,TicketToGo.trains.size());
        assertEquals("�s az a vonat t�k�letesen meg kell egyezzen t1-el.",true,trainDataEqualityCheck(t1,TicketToGo.trains.get(0)));
	}

}
