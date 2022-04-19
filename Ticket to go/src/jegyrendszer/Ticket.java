package jegyrendszer;
import java.io.FileWriter;  
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Ticket extends Train{
	/**
	 * A Train osztály gyereke, egy jegyet valósít meg.
	 */
	private int seatnumber;
	private int wagonnumber;
	
	/**
	 * Alapból a Train osztály konstruktorát használja, néhány extra attribútummal:
	 * seatnumber: integer típusú, a jegyen szereplõ ülésszámot reprezentálja
	 * wagonnumber: integer típusú, a jegyen szereplõ kocsiszámot reprezentálja 
	 * @param ulesszam
	 * @param kocsiszam
	 * @param szam
	 * @param indulopont
	 * @param induloido
	 * @param vegpont
	 * @param vegido
	 */
	public Ticket(int ulesszam, int kocsiszam, int szam, String indulopont, LocalDateTime induloido, String vegpont, LocalDateTime vegido) {
		super(szam, indulopont, induloido, vegpont, vegido);
		this.seatnumber = ulesszam;
		this.wagonnumber = kocsiszam;
	}
	
	/**
	 * A Ticket osztály toString metódusa, a jegy adatait írja ki a szabványos kimenetre.
	 */
	public String toString() {
		return ("Vonatszám: "+super.getNumber()+" Kocsiszám: "+this.wagonnumber+" Ülésszám: "+this.seatnumber+" Kiinduló állomás: "+super.getStartPoint()+" Indulás dátuma: "+super.getStartTime().format(TicketToGo.formatter)+" Célállomás: "+super.getEndPoint()+" Érkezési idõ: "+super.getEndTime().format(TicketToGo.formatter));
	}
	
	/**
	 * Egy jegy .txt fájlba való kiírását végzi, a fájlnév a rendszer aktuális idejének a nanoszekundumos értékébõl, a vonatszámból, a kocsiszámból és az ülésszámból tevõdik ki, kötõjellel elválasztva.
	 *  A jegyeket a program working directory-ján belül, a printed_tickets mappába írja. (Olyan mint ha kinyomtatná az ember :) )
	 */
	public void printTicket() {
		try {
			int nanoid = LocalDateTime.now().getNano(); 
			String filename = (System.getProperty("user.dir")+"/printed_tickets/"+nanoid+"-"+this.getNumber()+"-"+this.wagonnumber+"-"+this.seatnumber+".txt");
			FileWriter ticketWriter =new FileWriter(filename);
			PrintWriter ticket = new PrintWriter(ticketWriter);
			String utasadat = ("Helyadatok:\nVonatszám: "+this.getNumber()+", Kocsiszám: "+this.wagonnumber+", Ülésszám: "+this.seatnumber);
			String helyadat = ("Indulási és cél állomás:\n"+this.getStartPoint()+" -> "+this.getEndPoint());
			String idopont = ("Indulás és érkezés dátuma:\n"+this.getStartTime().format(TicketToGo.formatter)+" - "+this.getEndTime().format(TicketToGo.formatter));
			ticket.println(utasadat);
			ticket.println(helyadat);
			ticket.println(idopont);
			System.out.println("Jegy kiexportálva a "+System.getProperty("user.dir")+" printed_tickets mappába, a"+nanoid+".txt fájlba.");
			ticket.close();
			} catch (IOException e) {
				System.out.println("Hiba történt a fájl írásakor.");
				e.printStackTrace();
			}
	}
	
}
