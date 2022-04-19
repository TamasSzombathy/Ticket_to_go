package jegyrendszer;

public class TrainLister extends Menupoint{
	
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public TrainLister(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Megh�vja a TicketToGo listTrains f�ggv�nyt, ami ki�rja a kimenetre a TicketToGo trains ArrayList-ben t�rolt �sszes Train t�pus� vonat objektum adatait. 
	 */
	void action() {
		TicketToGo.listTrains();
		}
	}
	
