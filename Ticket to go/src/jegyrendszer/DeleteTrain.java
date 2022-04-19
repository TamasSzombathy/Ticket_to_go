package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTrain extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public DeleteTrain(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Ki�rja a nyilv�ntart�sban l�v� vonatok adatait a szabv�nyos kimenetre, ezut�n a felhaszn�l� a t�r�lni k�v�nt vonat vonatsz�m�t be�rja.
	 * Ha al�tezik a megadott vonatsz�mmal vonat a nyilv�ntart�sba (ami a TicketToGo trains ArrayList), akkor azt onnan t�rli. 
	 */
	@Override
	void action() {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> indexcache = new ArrayList<>();
		for (Train vonat : TicketToGo.trains) {
			System.out.println(vonat.toString());
			indexcache.add(vonat.getNumber());
		}
		System.out.print("�rja be annak a vonatnak a sz�m�t, amelyiket t�r�lni szeretn�: ");
		int kertvonatszam = input.nextInt();
		if (indexcache.contains(kertvonatszam) == true) {
			TicketToGo.trains.remove(indexcache.indexOf(kertvonatszam));
			System.out.println("K�rt vonat sikeresen elt�vol�tva.");
		} else {
			System.out.println("A keresett vonat nem tal�lhat�");
		}
		
	}
	

}
