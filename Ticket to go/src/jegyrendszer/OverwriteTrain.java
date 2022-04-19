package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class OverwriteTrain extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public OverwriteTrain(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Kiírja a szabványos kimenetre a TicketToGo trains ArrayList-ben található Train típusú vonat objektumok adatait, eközben a vonatok vonatszámát az indexcache ArrayListbe leképzi.
	 * Bekéri, hogy a felsorolt vonatok közül melyik adatait szeretné módosítani a felhasználó, amit a vonat vonatszámának beírásával tud jelezni.
	 * Létrehoz egy új Train objektumot, amihez a megadott számú kocsit hozzácsatolja, az elavult adatokkal rendelkezõ vonatot törli a TicketToGo trains ArrayList-bõl és az új adatokkal létrehozottat hozzáadja ugyanehhez az ArrayList-hez.
	 */
	@Override
	void action() {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> indexcache = new ArrayList<>();
		for (Train vonat : TicketToGo.trains) {
			System.out.println(vonat.toString());
			indexcache.add(vonat.getNumber());
		}
		System.out.print("Írja be annak a vonatnak a számát, amelyik adatait felül szertné írni: ");
		int kertvonatszam = input.nextInt();
		if (indexcache.contains(kertvonatszam) == true) {
			System.out.println("Írja be a vonat új adatait: ");
			String[] adatok = input.next().split(",");
			LocalDateTime startTime = LocalDateTime.parse(adatok[2], TicketToGo.formatter);
			LocalDateTime endTime = LocalDateTime.parse(adatok[4], TicketToGo.formatter);
			Train temptrain = new Train(Integer.parseInt(adatok[0]),adatok[1],startTime,adatok[3],endTime);
			TicketToGo.trains.remove(indexcache.indexOf(kertvonatszam));
			System.out.print("Írja be, hány kocsit fog húzni a vonat: ");
			int kocsiszam = input.nextInt();
			temptrain.connectWagons(kocsiszam);
			TicketToGo.addTrain(temptrain);
		} else {
			System.out.println("A keresett vonat nem található");
		}
	}

}
