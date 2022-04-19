package jegyrendszer;
import java.util.ArrayList;
import java.io.Serializable;

public class Wagon implements Serializable{
	/**
	 * Egy vasúti kocsit valósít meg, implementálja a Serializable interfészt is. 
	 * number: integer típusú, a kocsi számát reprezentálja.
	 * reserved: ArrayList típusú, integer-ket tárol amik a foglalt ülések ülészámát reprezentálják.
	 * free: ArrayList típusú, integer-ket tárol amik a szabad ülések ülészámát reprezentálják.
	 */
	private int number;
	private ArrayList<Integer> reserved = new ArrayList<>(30);
	private ArrayList<Integer> free = new ArrayList<>(30);
	
	/**
	 * A Wagon osztály konstruktora, a for ciklus feltölti 30db (1-30-ig) ülésszámmal a free ArrayList-et. 
	 * @param szam
	 */
	public Wagon(int szam) {
		this.number = szam;
		for (int i = 1; i<31; i++) {
			this.free.add(i);
		}
		
	/**
	 *Integer értéket ad vissza, hogy mennyi szabad ülés van még az adott kocsiban. 
	 */
	}
	public int getAmountOfFreeSeats() {
		return this.free.size();
	}
	
	/**
	 * Integer értéket ad vissza, az adott kocsi kocsiszámát.
	 * @return
	 */
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * Lefoglal egy ülést az adott kocsiban, úgy, hogy a free ArrayListben talált ülésszámot kiveszi és átrakja a reserved ArrayList-be, ezzel szimulálva a foglalást.
	 * Integer értékkel tér vissza, ami a lefoglalt ülész ülésszáma. Ha a foglalás nem lehetséges, 0-ával tér vissza, de ez az if block miatt majdhogynem kizárt. ;)
	 * @return
	 */
	public int reserveSeat() {
		if (this.free.size()>0) {
			int akku = this.free.get(0);
			this.free.remove(0);
			this.reserved.add(akku);
			return akku;
		} else {
			return 0;
		}
	}
	/**
	 * Kiírja a kimenetre a foglalt ülések számát egy kocsiban.
	 */
	public void printFreeSeats() {
		for (int seatnumber : this.free) {
			System.out.println(seatnumber);
		}
	}
}
