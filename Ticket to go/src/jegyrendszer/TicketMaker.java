package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMaker extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public TicketMaker(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	
	
	/**
	 * Az utaz�si adatok alapj�n megkeresi a felt�teleknek megfelel� vonatokat, ezeket kilist�zza, hogy a felhaszn�l� a sz�m�ra legmegfelel�bb vonatra v�lthasson jegyet.
	 * A keres�si felt�telek, hogy a vonat k�s�bb induljon, mint a be�rt id�pont, a megadott �llom�sr�l a megadott �llom�sra tartson �s legyen rajta m�g hely.
	 * K�sz�t egy Ticket t�pus� objektumot amit a nyilv�tart�sba, a TicketToGo tickets ArrayList-hez hozz�ad.
	 * Az el�bb l�trehozott jegyet kiexport�lja egy .txt f�jlba a Ticket oszt�ly printTicket met�dus�val. Mindezt a program working directory-j�ban tal�lhat� printed_ticket mapp�ba.  
	 */
	void action() {
		System.out.println("�rja be az utaz�s adatait vessz�vel elv�lasztva. Kiindul� �llom�s,indul�s ideje,c�l�llom�s");
		Scanner input = new Scanner(System.in);
		String[] jegyadatok = input.nextLine().split(",");
		LocalDateTime starttime = LocalDateTime.parse(jegyadatok[1], TicketToGo.formatter);
		ArrayList<Integer> jovonatindexek = new ArrayList<>();
		int index = 0;
		for (Train vonat : TicketToGo.trains) {
			if (vonat.getStartPoint().equals(jegyadatok[0]) & starttime.isBefore(vonat.getStartTime()) & vonat.getEndPoint().equals(jegyadatok[2]) & vonat.getFreeSeats()>0) {
				jovonatindexek.add(TicketToGo.trains.indexOf(vonat));
				index++;
				System.out.println("("+index+") "+vonat.toString());

			}
		}
		if (jovonatindexek.size()==0) {
			System.out.print("A megadott adatoknak megfelel� vonat nem l�tezik.");
		} else {
			System.out.print("A fentiek k�z�l melyik vonatot v�lasztja? V�lasztani a vonatokhoz rendelt sz�m be�r�s�val lehet: ");
			int chosedtrainindex = jovonatindexek.get(input.nextInt()-1);
			int[] helyadat = TicketToGo.trains.get(chosedtrainindex).reserveSeat();
			Ticket tempticket = new Ticket(helyadat[1],helyadat[0],TicketToGo.trains.get(chosedtrainindex).getNumber(),TicketToGo.trains.get(chosedtrainindex).getStartPoint(),TicketToGo.trains.get(chosedtrainindex).getStartTime(),TicketToGo.trains.get(chosedtrainindex).getEndPoint(),TicketToGo.trains.get(chosedtrainindex).getEndTime());
			TicketToGo.tickets.add(tempticket);
			System.out.println("Jegy nyomtat�sa...");
			tempticket.printTicket();
		}
	}
}
