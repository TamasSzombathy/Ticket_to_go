package jegyrendszer;


public class Exit extends Menupoint{
	/**
	 * Konstruktor, a Menupoint osztálytól örökölt konstruktort használja.
	 * @param menuid
	 * @param menupointname
	 */
	
	public Exit(int menuid, String menupointname) {
		super(menuid, menupointname);
	}
	/**
	 * Meghívja a Serializer osztály saveData metódusát, amivel menti (szerializálja) a nyilvántartás adatait (a TicketToGo train és tickets ArrayList-ek tartalmát).
	 */
	@Override
	void action() {
		Serializer.saveData();
	}

}
