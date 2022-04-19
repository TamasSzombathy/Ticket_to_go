package jegyrendszer;

public class TrainLister extends Menupoint{
	
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	public TrainLister(int menuid, String menupointname) {
		super(menuid, menupointname);
	}

	/**
	 * Meghívja a TicketToGo listTrains függvényt, ami kiírja a kimenetre a TicketToGo trains ArrayList-ben tárolt összes Train típusú vonat objektum adatait. 
	 */
	void action() {
		TicketToGo.listTrains();
		}
	}
	
