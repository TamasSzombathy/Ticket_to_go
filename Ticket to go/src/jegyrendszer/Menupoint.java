package jegyrendszer;

abstract public class Menupoint {
	private int menuid;
	private String menupointname;
	/**
	 * Egy men�pontot megval�s�t� oszt�ly, az �sszes men�pont �se.
	 */
	
	/**
	 * Menupoint oszt�ly konstruktora.
	 * menuid: integer t�pus�, a men�ponthoz rendelt men�sz�mot reprezent�lja.
	 * menupointname: string t�pus�, a men�pont nev�t reprezent�lja.
	 * @param menuid
	 * @param menupointname
	 */
	public Menupoint(int menuid, String menupointname) {
		this.menuid=menuid;
		this.menupointname = menupointname;
	}
	/**
	 * Az integer t�pus� menuid �rt�k�t adja vissza.
	 * @return
	 */
	public int getMenuid() {return this.menuid;}
	/**
	 * A string t�pus� menupointname �rt�k�t adja vissza.
	 * @return
	 */
	public String getMenuPointName() {return this.menupointname;}
	/**
	 * Absztrakt, nem csin�l semmit, ezt a met�dust �rja fel�l mindegyik men�pont a saj�tj�val.
	 */
	abstract void action();
}
