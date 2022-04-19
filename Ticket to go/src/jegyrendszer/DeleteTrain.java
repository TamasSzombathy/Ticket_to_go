package jegyrendszer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTrain extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public DeleteTrain(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Kiírja a nyilvántartásban lévõ vonatok adatait a szabványos kimenetre, ezután a felhasználó a törölni kívánt vonat vonatszámát beírja.
	 * Ha alétezik a megadott vonatszámmal vonat a nyilvántartásba (ami a TicketToGo trains ArrayList), akkor azt onnan törli. 
	 */
	@Override
	void action() {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> indexcache = new ArrayList<>();
		for (Train vonat : TicketToGo.trains) {
			System.out.println(vonat.toString());
			indexcache.add(vonat.getNumber());
		}
		System.out.print("Írja be annak a vonatnak a számát, amelyiket törölni szeretné: ");
		int kertvonatszam = input.nextInt();
		if (indexcache.contains(kertvonatszam) == true) {
			TicketToGo.trains.remove(indexcache.indexOf(kertvonatszam));
			System.out.println("Kért vonat sikeresen eltávolítva.");
		} else {
			System.out.println("A keresett vonat nem található");
		}
		
	}
	

}
