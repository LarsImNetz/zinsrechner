package de.hypoport.einarbeitung.lars;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZinsRechnerBean implements Serializable {

	private double miete;
	private double sparen;

	private double zins;

	private double tilgung;
	private int laufzeit; // in monaten

	private double endkapital = 0;

	private double resultat;

	public ZinsRechnerBean() {
		zins = 3;
		tilgung = 2;
		laufzeit = 0;
	}

	public double getMiete() {
		return miete;
	}

	public void setMiete(double miete) {
		this.miete = miete;
	}

	public double getSparen() {
		return sparen;
	}

	public void setSparen(double sparen) {
		this.sparen = sparen;
	}

	public double getTilgung() {
		return tilgung;
	}

	public void setTilgung(double tilgung) {
		this.tilgung = tilgung;
	}

	public double getZins() {
		return zins;
	}

	public void setZins(double zins) {
		this.zins = zins;
	}

	public int getLaufzeit() {
		return laufzeit;
	}

	public void setLaufzeit(int laufzeit) {
		this.laufzeit = laufzeit;
	}

	public double getResultat() {
		return resultat;
	}

	public void setResultat(double resultat) {
		this.resultat = resultat;
	}

	public void calculate() {
		double rate_im_monat = miete + sparen;
//		double kapital_per_anno = kapital * 12;
//		double zins_per_anno = kapital_per_anno * (1 + getZins() / 100) - kapital_per_anno;
//		double zins_for_all = kapital_per_anno * Math.pow(1 + getZins() / 100, laufzeit / 12);
//		endkapital = kapital * laufzeit - zins_for_all;
//
//		int breakpoint = 0;
		
		// TODO: Das gefällt mir so noch nicht wirklich
		ZinsRechner rechner = new ZinsRechner(rate_im_monat,zins, tilgung, laufzeit);
		setTilgung(rechner.getTilgung());
		setLaufzeit(rechner.getLaufzeit());
		setEndkapital(rechner.getStartKapital());
	}

	public double getEndkapital() {
		return endkapital;
	}

	private void setEndkapital(double kapital) {
		this.endkapital = kapital;
	}

}
