package jegyrendszer;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	/**
	 * A nyilváltartás szerializálását végzi. Menti a TicketToGo trains illetve tickets ArrayList-ek tartalmát, illetve ugyanezen adatokat be is importálja ugyanezekbe a listákba.
	 * Továbbá a program mûködéséhez szükséges mappák létrehozását is elvégzi. 
	 */
	
	/**
	 * A deszerializálást végzi, azaz az application_dat/Trains és az application_data/Tickets tartalmát tölti be a TicketToGo trains és a TicketToGo tickets ArrayList-ekbe.
	 */
	static void loadData() {
		try {
			//
			FileInputStream vonatadatok = new FileInputStream(System.getProperty("user.dir")+"/application_data/"+"Trains");
			ObjectInputStream vonatok = new ObjectInputStream(vonatadatok);
			TicketToGo.trains = (ArrayList) vonatok.readObject();
			vonatadatok.close();
			vonatok.close();
			//
			FileInputStream jegyadatok = new FileInputStream(System.getProperty("user.dir")+"/application_data/"+"Tickets");
			ObjectInputStream jegyek = new ObjectInputStream(jegyadatok);
			TicketToGo.tickets = (ArrayList) jegyek.readObject();
			jegyadatok.close();
			jegyek.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Az osztály nem található,");
			c.printStackTrace();
		}
		
	}
	/**
	 * Ellenõrzi, hogy léteznek-e a program working directory-jában az application_data és a printed tickets mappák. Ha nem létrehozza ezeket és jelzi a corruption_found értékét true-ra állítja.
	 * A corruption found azt jelzi, hogy a fent említett mappák nem léteztek, létre kellett hozni õket, ergo az application_data mappában nem lehet deszerializálandó adat.
	 * @return
	 */
	static boolean filesystemCheck() {
		boolean corruption_found = false;
		File appdata = new File(System.getProperty("user.dir")+"/application_data");
		if ( ! appdata.exists() ) {
			appdata.mkdir();
			corruption_found = true;
		}
		File trains = new File(System.getProperty("user.dir")+"/application_data/Trains"); 
		File tickets = new File(System.getProperty("user.dir")+"/application_data/Tickets");
		if ( (! trains.exists()) | (! tickets.exists())) {
			saveData();
			corruption_found = true;
		}
		File printed_tickets = new File(System.getProperty("user.dir")+"/printed_tickets");
		if ( ! printed_tickets.exists() ) {
			printed_tickets.mkdir();
			corruption_found = true;
		}
		return corruption_found;
	}
	
	/**
	 * A TicketToGo trains és tickets ArrayList-ek tartalmát szerializálja a program working directory-jában található (elsõ indítás elõtt még nem lesz ott) application_data mappába a Trains és a Tickets fájlokba.
	 * Ha a szerializálás nem sikerül arról értesítést küld a szabványos kimeneten.
	 */
	static void saveData() {
		try {
            FileOutputStream vonatadatok = new FileOutputStream(System.getProperty("user.dir")+"/application_data"+"/Trains");
            ObjectOutputStream vonatok = new ObjectOutputStream(vonatadatok);
            vonatok.writeObject(TicketToGo.trains);
            vonatok.close();
            vonatadatok.close();
            FileOutputStream jegyadatok = new FileOutputStream(System.getProperty("user.dir")+"/application_data"+"/Tickets");
            ObjectOutputStream jegyek = new ObjectOutputStream(jegyadatok);
            jegyek.writeObject(TicketToGo.tickets);
            jegyek.close();
            jegyadatok.close();
            System.out.println("Adatok elmentve");
            
        } catch (IOException a) {
            a.printStackTrace();
            System.out.println("Adatok mentése sikertelen.");
        }
	}
}