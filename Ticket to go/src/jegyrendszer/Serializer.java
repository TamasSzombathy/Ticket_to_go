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
	 * A nyilv�ltart�s szerializ�l�s�t v�gzi. Menti a TicketToGo trains illetve tickets ArrayList-ek tartalm�t, illetve ugyanezen adatokat be is import�lja ugyanezekbe a list�kba.
	 * Tov�bb� a program m�k�d�s�hez sz�ks�ges mapp�k l�trehoz�s�t is elv�gzi. 
	 */
	
	/**
	 * A deszerializ�l�st v�gzi, azaz az application_dat/Trains �s az application_data/Tickets tartalm�t t�lti be a TicketToGo trains �s a TicketToGo tickets ArrayList-ekbe.
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
			System.out.println("Az oszt�ly nem tal�lhat�,");
			c.printStackTrace();
		}
		
	}
	/**
	 * Ellen�rzi, hogy l�teznek-e a program working directory-j�ban az application_data �s a printed tickets mapp�k. Ha nem l�trehozza ezeket �s jelzi a corruption_found �rt�k�t true-ra �ll�tja.
	 * A corruption found azt jelzi, hogy a fent eml�tett mapp�k nem l�teztek, l�tre kellett hozni �ket, ergo az application_data mapp�ban nem lehet deszerializ�land� adat.
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
	 * A TicketToGo trains �s tickets ArrayList-ek tartalm�t szerializ�lja a program working directory-j�ban tal�lhat� (els� ind�t�s el�tt m�g nem lesz ott) application_data mapp�ba a Trains �s a Tickets f�jlokba.
	 * Ha a szerializ�l�s nem siker�l arr�l �rtes�t�st k�ld a szabv�nyos kimeneten.
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
            System.out.println("Adatok ment�se sikertelen.");
        }
	}
}