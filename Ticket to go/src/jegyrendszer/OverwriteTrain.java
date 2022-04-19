package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OverwriteTrain extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public OverwriteTrain(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Ki�rja a szabv�nyos kimenetre a TicketToGo trains ArrayList-ben tal�lhat� Train t�pus� vonat objektumok adatait, ek�zben a vonatok vonatsz�m�t az indexcache ArrayListbe lek�pzi.
	 * Bek�ri, hogy a felsorolt vonatok k�z�l melyik adatait szeretn� m�dos�tani a felhaszn�l�, amit a vonat vonatsz�m�nak be�r�s�val tud jelezni.
	 * L�trehoz egy �j Train objektumot, amihez a megadott sz�m� kocsit hozz�csatolja, az elavult adatokkal rendelkez� vonatot t�rli a TicketToGo trains ArrayList-b�l �s az �j adatokkal l�trehozottat hozz�adja ugyanehhez az ArrayList-hez.
	 */
	@Override
	void action() {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> indexcache = new ArrayList<>();
		for (Train vonat : TicketToGo.trains) {
			System.out.println(vonat.toString());
			indexcache.add(vonat.getNumber());
		}
		System.out.print("�rja be annak a vonatnak a sz�m�t, amelyik adatait fel�l szertn� �rni: ");
		int kertvonatszam = input.nextInt();
		if (indexcache.contains(kertvonatszam) == true) {
			System.out.println("�rja be a vonat �j adatait: ");
			String[] adatok = input.next().split(",");
			LocalDateTime startTime = LocalDateTime.parse(adatok[2], TicketToGo.formatter);
			LocalDateTime endTime = LocalDateTime.parse(adatok[4], TicketToGo.formatter);
			Train temptrain = new Train(Integer.parseInt(adatok[0]),adatok[1],startTime,adatok[3],endTime);
			TicketToGo.trains.remove(indexcache.indexOf(kertvonatszam));
			System.out.print("�rja be, h�ny kocsit fog h�zni a vonat: ");
			int kocsiszam = input.nextInt();
			temptrain.connectWagons(kocsiszam);
			TicketToGo.addTrain(temptrain);
		} else {
			System.out.println("A keresett vonat nem tal�lhat�");
		}
	}

}
