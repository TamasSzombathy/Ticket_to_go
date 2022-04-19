package jegyrendszer;

abstract public class Menupoint {
	private int menuid;
	private String menupointname;
	/**
	 * Egy menüpontot megvalósító osztály, az összes menüpont õse.
	 */
	
	/**
	 * Menupoint osztály konstruktora.
	 * menuid: integer típusú, a menüponthoz rendelt menüszámot reprezentálja.
	 * menupointname: string típusú, a menüpont nevét reprezentálja.
	 * @param menuid
	 * @param menupointname
	 */
	public Menupoint(int menuid, String menupointname) {
		this.menuid=menuid;
		this.menupointname = menupointname;
	}
	/**
	 * Az integer típusú menuid értékét adja vissza.
	 * @return
	 */
	public int getMenuid() {return this.menuid;}
	/**
	 * A string típusú menupointname értékét adja vissza.
	 * @return
	 */
	public String getMenuPointName() {return this.menupointname;}
	/**
	 * Absztrakt, nem csinál semmit, ezt a metúdust írja felül mindegyik menüpont a sajátjával.
	 */
	abstract void action();
}
