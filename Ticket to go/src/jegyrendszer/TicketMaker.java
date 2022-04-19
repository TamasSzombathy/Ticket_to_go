package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class TicketMaker extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public TicketMaker(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	
	
	/**
	 * Az utazási adatok alapján megkeresi a feltételeknek megfelelõ vonatokat, ezeket kilistázza, hogy a felhasználó a számára legmegfelelõbb vonatra válthasson jegyet.
	 * A keresési feltételek, hogy a vonat késõbb induljon, mint a beírt idõpont, a megadott állomásról a megadott állomásra tartson és legyen rajta még hely.
	 * Készít egy Ticket típusú objektumot amit a nyilvátartásba, a TicketToGo tickets ArrayList-hez hozzáad.
	 * Az elõbb létrehozott jegyet kiexportálja egy .txt fájlba a Ticket osztály printTicket metódusával. Mindezt a program working directory-jában található printed_ticket mappába.  
	 */
	void action() {
		System.out.println("Írja be az utazás adatait vesszõvel elválasztva. Kiinduló állomás,indulás ideje,célállomás");
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
			System.out.print("A megadott adatoknak megfelelõ vonat nem létezik.");
		} else {
			System.out.print("A fentiek közül melyik vonatot választja? Választani a vonatokhoz rendelt szám beírásával lehet: ");
			int chosedtrainindex = jovonatindexek.get(input.nextInt()-1);
			int[] helyadat = TicketToGo.trains.get(chosedtrainindex).reserveSeat();
			Ticket tempticket = new Ticket(helyadat[1],helyadat[0],TicketToGo.trains.get(chosedtrainindex).getNumber(),TicketToGo.trains.get(chosedtrainindex).getStartPoint(),TicketToGo.trains.get(chosedtrainindex).getStartTime(),TicketToGo.trains.get(chosedtrainindex).getEndPoint(),TicketToGo.trains.get(chosedtrainindex).getEndTime());
			TicketToGo.tickets.add(tempticket);
			System.out.println("Jegy nyomtatása...");
			tempticket.printTicket();
		}
	}
}
