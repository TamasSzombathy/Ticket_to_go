package jegyrendszer;
import java.util.ArrayList;
import java.io.Serializable;

public class Wagon implements Serializable{
	/**
	 * Egy vas�ti kocsit val�s�t meg, implement�lja a Serializable interf�szt is. 
	 * number: integer t�pus�, a kocsi sz�m�t reprezent�lja.
	 * reserved: ArrayList t�pus�, integer-ket t�rol amik a foglalt �l�sek �l�sz�m�t reprezent�lj�k.
	 * free: ArrayList t�pus�, integer-ket t�rol amik a szabad �l�sek �l�sz�m�t reprezent�lj�k.
	 */
	private int number;
	private ArrayList<Integer> reserved = new ArrayList<>(30);
	private ArrayList<Integer> free = new ArrayList<>(30);
	
	/**
	 * A Wagon oszt�ly konstruktora, a for ciklus felt�lti 30db (1-30-ig) �l�ssz�mmal a free ArrayList-et. 
	 * @param szam
	 */
	public Wagon(int szam) {
		this.number = szam;
		for (int i = 1; i<31; i++) {
			this.free.add(i);
		}
		
	/**
	 *Integer �rt�ket ad vissza, hogy mennyi szabad �l�s van m�g az adott kocsiban. 
	 */
	}
	public int getAmountOfFreeSeats() {
		return this.free.size();
	}
	
	/**
	 * Integer �rt�ket ad vissza, az adott kocsi kocsisz�m�t.
	 * @return
	 */
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * Lefoglal egy �l�st az adott kocsiban, �gy, hogy a free ArrayListben tal�lt �l�ssz�mot kiveszi �s �trakja a reserved ArrayList-be, ezzel szimul�lva a foglal�st.
	 * Integer �rt�kkel t�r vissza, ami a lefoglalt �l�sz �l�ssz�ma. Ha a foglal�s nem lehets�ges, 0-�val t�r vissza, de ez az if block miatt majdhogynem kiz�rt. ;)
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
	 * Ki�rja a kimenetre a foglalt �l�sek sz�m�t egy kocsiban.
	 */
	public void printFreeSeats() {
		for (int seatnumber : this.free) {
			System.out.println(seatnumber);
		}
	}
}
