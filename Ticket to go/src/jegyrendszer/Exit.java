package jegyrendszer;


public class Exit extends Menupoint{
	/**
	 * Konstruktor, a Menupoint oszt�lyt�l �r�k�lt konstruktort haszn�lja.
	 * @param menuid
	 * @param menupointname
	 */
	
	public Exit(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	/**
	 * Megh�vja a Serializer oszt�ly saveData met�dus�t, amivel menti (szerializ�lja) a nyilv�ntart�s adatait (a TicketToGo train �s tickets ArrayList-ek tartalm�t).
	 */
	@Override
	void action() {
		Serializer.saveData();
	}

}
