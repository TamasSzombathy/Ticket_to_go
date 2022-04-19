package jegyrendszer;
import java.io.FileWriter;  
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Ticket extends Train{
	/**
	 * A Train oszt�ly gyereke, egy jegyet val�s�t meg.
	 */
	private int seatnumber;
	private int wagonnumber;
	
	/**
	 * Alapb�l a Train oszt�ly konstruktor�t haszn�lja, n�h�ny extra attrib�tummal:
	 * seatnumber: integer t�pus�, a jegyen szerepl� �l�ssz�mot reprezent�lja
	 * wagonnumber: integer t�pus�, a jegyen szerepl� kocsisz�mot reprezent�lja 
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
	 * A Ticket oszt�ly toString met�dusa, a jegy adatait �rja ki a szabv�nyos kimenetre.
	 */
	public String toString() {
		return ("Vonatsz�m: "+super.getNumber()+" Kocsisz�m: "+this.wagonnumber+" �l�ssz�m: "+this.seatnumber+" Kiindul� �llom�s: "+super.getStartPoint()+" Indul�s d�tuma: "+super.getStartTime().format(TicketToGo.formatter)+" C�l�llom�s: "+super.getEndPoint()+" �rkez�si id�: "+super.getEndTime().format(TicketToGo.formatter));
	}
	
	/**
	 * Egy jegy .txt f�jlba val� ki�r�s�t v�gzi, a f�jln�v a rendszer aktu�lis idej�nek a nanoszekundumos �rt�k�b�l, a vonatsz�mb�l, a kocsisz�mb�l �s az �l�ssz�mb�l tev�dik ki, k�t�jellel elv�lasztva.
	 *  A jegyeket a program working directory-j�n bel�l, a printed_tickets mapp�ba �rja. (Olyan mint ha kinyomtatn� az ember :) )
	 */
	public void printTicket() {
		try {
			int nanoid = LocalDateTime.now().getNano(); 
			String filename = (System.getProperty("user.dir")+"/printed_tickets/"+nanoid+"-"+this.getNumber()+"-"+this.wagonnumber+"-"+this.seatnumber+".txt");
			FileWriter ticketWriter =new FileWriter(filename);
			PrintWriter ticket = new PrintWriter(ticketWriter);
			String utasadat = ("Helyadatok:\nVonatsz�m: "+this.getNumber()+", Kocsisz�m: "+this.wagonnumber+", �l�ssz�m: "+this.seatnumber);
			String helyadat = ("Indul�si �s c�l �llom�s:\n"+this.getStartPoint()+" -> "+this.getEndPoint());
			String idopont = ("Indul�s �s �rkez�s d�tuma:\n"+this.getStartTime().format(TicketToGo.formatter)+" - "+this.getEndTime().format(TicketToGo.formatter));
			ticket.println(utasadat);
			ticket.println(helyadat);
			ticket.println(idopont);
			System.out.println("Jegy kiexport�lva a "+System.getProperty("user.dir")+" printed_tickets mapp�ba, a"+nanoid+".txt f�jlba.");
			ticket.close();
			} catch (IOException e) {
				System.out.println("Hiba t�rt�nt a f�jl �r�sakor.");
				e.printStackTrace();
			}
	}
	
}
