package jegyrendszer;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SerializerTester {
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
	public void filesystemCheckOnFirstRunTest() {
		File trains = new File(System.getProperty("user.dir")+"/application_data/Trains"); 
		File tickets = new File(System.getProperty("user.dir")+"/application_data/Tickets");
		File appdata = new File(System.getProperty("user.dir")+"/application_data");
		File printed_tickets = new File(System.getProperty("user.dir")+"/printed_tickets");
		trains.delete(); tickets.delete(); appdata.delete(); printed_tickets.delete();
		
		assertEquals("Nincsenek meg a megfelelõ mappák vagy fájlok, ezért a függvény a corruption-re true értékkel tér vissza.", true, Serializer.filesystemCheck());
		assertEquals("Ugyanakkor létrehozza az application_data mappát.",true,appdata.exists());
		assertEquals("Ugyanakkor létrehozza a printed_tickets mappát.",true,printed_tickets.exists());
		assertEquals("Ugyanakkor létrehozza a Trains fájlt az application_data mappában.",true,trains.exists());
		assertEquals("Ugyanakkor létrehozza a Tickets fájlt az application_data mappában.",true,tickets.exists());
	}
	
	@Test
	public void saveDataTester() throws IOException, ClassNotFoundException {
		TicketToGo.trains.clear();
		TicketToGo.addTrain(t1);
		Serializer.saveData();
		ArrayList<Train> testlist = new ArrayList<>();
		
		FileInputStream vonatadatok = new FileInputStream(System.getProperty("user.dir")+"/application_data/"+"Trains");
		ObjectInputStream vonatok = new ObjectInputStream(vonatadatok);
		testlist = (ArrayList) vonatok.readObject();
		vonatadatok.close();
		vonatok.close();
		
		assertEquals("Valóban jó adatokkal mentette a függvény a vonatot?", true , trainDataEqualityCheck(t1, testlist.get(0)));
	}
	
	@Test
	public void loadDataTester() throws IOException {
		TicketToGo.trains.clear();
		TicketToGo.addTrain(t1);
		FileOutputStream vonatadatok = new FileOutputStream(System.getProperty("user.dir")+"/application_data"+"/Trains");
        ObjectOutputStream vonatok = new ObjectOutputStream(vonatadatok);
        vonatok.writeObject(TicketToGo.trains);
        vonatok.close();
        vonatadatok.close();
        TicketToGo.trains.clear();
        Serializer.loadData();
        
        assertEquals("Valóban jó adatokkal hozota be a vonatot függvény?", true , trainDataEqualityCheck(t1, TicketToGo.trains.get(0)));
		
	}

}
