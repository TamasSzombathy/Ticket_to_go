package jegyrendszer;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Train implements Serializable{
	
	/**
	 * Vonatot reprezent�l� oszt�ly, implement�lja a Serializable interf�szt. Attrib�tumai:
	 * number: integer t�pus�, a vonat sz�ma.
	 * wagons: ArrayList t�pus�, Wagon objektumokat (kocsikat) t�rol ezzel szimul�lva a vonatra csatolt kocsikat.
	 * startPoint: string t�pus�, az indul�si �llom�s neve.
	 * startTime: LocalDateTime t�pus�, az indul�s d�tuma.
	 * endPoint: string t�pus�, a c�l�llom�s neve.
	 * endTime: LocalDateTime t�pus�, a c�lba�r�s d�tuma. 
	 */
	private int number;
	private ArrayList<Wagon> wagons = new ArrayList<>();
	private String startPoint;
	private LocalDateTime startTime;
	private String endPoint;
	private LocalDateTime endTime;
	
	/**
	 * Az oszt�ly konstruktora.
	 * @param szam
	 * @param indulopont
	 * @param induloido
	 * @param vegpont
	 * @param vegido
	 */
	public Train(int szam,String indulopont, LocalDateTime induloido, String vegpont, LocalDateTime vegido) {
		this.number = szam;
		this.startPoint = indulopont;
		this.startTime = induloido;
		this.endPoint = vegpont;
		this.endTime = vegido;
	}
	
	/**
	 * Az oszt�ly konstruktora, az id�pontokat string t�pusban veszi be �s alak�tja �t LocalDateTime t�pus�v�.
	 * @param szam
	 * @param indulopont
	 * @param induloido
	 * @param vegpont
	 * @param vegido
	 */
	public Train(int szam, String indulopont, String induloido, String vegpont, String vegido) {
		this.number = szam;
		this.startPoint = indulopont;
		this.startTime = LocalDateTime.parse(induloido, TicketToGo.formatter);
		this.endPoint = vegpont;
		this.endTime = LocalDateTime.parse(vegido, TicketToGo.formatter);
	}
	
	/**
	 * A vonatsz�mot adja vissza, ami integer t�pus�.
	 * @return
	 */
	public int getNumber() {return this.number;}
	
	/**
	 * A kiindul� �llom�st nev�t adja vissza, ami string t�pus�.
	 * @return
	 */
	public String getStartPoint() {return this.startPoint;}
	
	/**
	 * Az indul�s id�pontj�t adja vissza, ami egy LocalTimDate objektum.
	 * @return
	 */
	public LocalDateTime getStartTime() {return this.startTime;}
	
	/**
	 * A c�l�llom�s nev�t adja vissza, ami string t�pus�.
	 * @return
	 */
	public String getEndPoint() {return this.endPoint;}
	
	/**
	 * Az �rkez�s id�pontj�t adja vissza, ami egy LocalTimDate objektum.
	 * @return
	 */
	public LocalDateTime getEndTime() {return this.endTime;}

	
	public int getNumberOfWagons() {return this.wagons.size();}
	
	/**
	 * Az oszt�ly toString met�dusa, ki�rja a vonat attrib�tumait.
	 */
	public String toString() {
		return ("Vonatsz�m: "+this.number+" Kiindul� �llom�s: "+this.startPoint+" Indul�s d�tuma: "+this.startTime.format(TicketToGo.formatter)+" C�l�llom�s: "+this.endPoint+" �rkez�si id�: "+this.endTime.format(TicketToGo.formatter));
	}
	
	/**
	 * Integer t�pus� �rt�ket ad vissza, hogy a vonaton mennyi szabad hely van. 
	 * @return
	 */
	public int getFreeSeats() {
		int freeSeats = 0;
		for (Wagon kocsi : this.wagons) {
			freeSeats += kocsi.getAmountOfFreeSeats();
		}
		return freeSeats;
	}
	
	/**
	 * Egy integer t�pusban megadott sz�m� Wagon objektumot hoz l�tre �s csatol hozz� a vonathoz.
	 * @param amount
	 */
	public void connectWagons(int amount) {
		for (int i = 0; i<amount; i++) {
			this.wagons.add(new Wagon(i+1));
		}
	}
	
	/**
	 * V�gigmegy a vonaton �s a legels� szabad helyet lefoglalja a vonaton a kocsiban �s visszaadja a kocsi sz�m�t �s a hely sz�m�t amit lefoglalt egy integereket tartalmaz� t�mbben, ami k�t elem�.
	 * Ha nem tal�lt szabad helyet, null �rt�kkel t�r vissza.
	 * @return
	 */
	public int[] reserveSeat() {
		for (Wagon kocsi : this.wagons) {
			if (kocsi.getAmountOfFreeSeats()>0) {
				int[] helyadat = new int[] {kocsi.getNumber(), kocsi.reserveSeat()};
				return helyadat;
			}
		}
		return null;
	}
}
