package de.hypoport.einarbeitung.lars;

import java.util.ArrayList;

public class ZinsRechner {

	final double rate_im_monat;
	final double zins;

	double tilgung = 0;
	int laufzeit = 0;

	public ZinsRechner(double rate_im_monat, double zins, double tilgung,
			int laufzeit) {
		this.rate_im_monat = rate_im_monat;
		this.zins = zins;
		
		if (tilgung == 0 && laufzeit == 0) {
			this.laufzeit = 0;
			this.tilgung = 0;		
		}
		if (tilgung != 0) {
			this.laufzeit = calculateLaufzeit(tilgung);
			this.tilgung = tilgung;
		} 
		else if (laufzeit != 0) {
			this.laufzeit = laufzeit;
			this.tilgung = calculateTilgung(laufzeit);
		}
		else {
			this.tilgung = 2;
			this.laufzeit = calculateLaufzeit(tilgung);
			// unbehandelter Fall, tilgung == 0 und laufzeit == null
		}
	}

	public ZinsRechner(double rate_im_monat, double zins) {
		this.rate_im_monat = rate_im_monat;
		this.zins = zins;
	}

	public int calculateLaufzeit(double tilgung) {
		tp = new TilgungsPlan(rate_im_monat, zins, tilgung);
		int laufzeit = tp.getLaufzeit();
		return laufzeit;
	}

	public double calculateTilgung(int laufzeit) {
		double tilgung = 20;
		double halftilgung = tilgung / 2;
		int newlaufzeit = 0;

		while (newlaufzeit != laufzeit) {
			tp = new TilgungsPlan(rate_im_monat, zins, tilgung);
			newlaufzeit = tp.getLaufzeit();
			if (newlaufzeit == laufzeit) {
				return tilgung;
			}
			if (newlaufzeit < laufzeit) {
				// neue Laufzeit ist zu klein, wir verringern die Tilgung und
				// die Hälfte
				tilgung = tilgung - halftilgung;
			} else {
				// neue Laufzeit ist zu lang, wir vergrößern die Tilgung um die
				// Hälfte
				tilgung = tilgung + halftilgung;
			}
			halftilgung = halftilgung / 2;
		}
		return 0;
	}

	private TilgungsPlan tp;

	private class TilgungsPlan {

		// Zinsberechnung:

		// =G1*(F$2/100)/12

		// first month:
		// =F1*12*100/(F$2+F$3)

		// next month:
		// =G1-(F$1 - H1)-I2

		private ArrayList<Double> zinslist;
		private ArrayList<Double> kapitallist;

		public TilgungsPlan(double rate_im_monat, double zins, double tilgung) {
			zinslist = new ArrayList<Double>();
			kapitallist = new ArrayList<Double>();

			double startkapital = getStartKapital(rate_im_monat, zins, tilgung);
			kapitallist.add(startkapital);
			double current_zins_im_monat = getZinsPerMonth(startkapital, zins);
			zinslist.add(current_zins_im_monat);
			double current_kapital = startkapital;

			int month = 1;
			while (current_kapital > 0 || month > (60 * 12)) {
				current_kapital = current_kapital
						- (rate_im_monat - current_zins_im_monat);
				kapitallist.add(current_kapital);
				current_zins_im_monat = getZinsPerMonth(current_kapital, zins);
				zinslist.add(current_zins_im_monat);
				month++;
			}
		}

		public int getLaufzeit() {
			return zinslist.size();
		}
	}

	public int getLaufzeit() {
		if (tp == null) {
			calculateLaufzeit(tilgung);
		}
		return tp.getLaufzeit();
	}

	public int getLaufzeit(double tilgung) {
		TilgungsPlan tp2 = new TilgungsPlan(rate_im_monat, zins, tilgung);
		return tp2.getLaufzeit();
	}

	public double getStartKapital() {
		return getStartKapital(rate_im_monat, zins, tilgung);
	}

	public double getZinsPerMonth(double startkapital) {
		return getZinsPerMonth(startkapital, zins);
	}

	public static double getStartKapital(double rate_im_monat, double zins,
			double tilgung) {
		final double startkapital = rate_im_monat * 12 * 100 / (zins + tilgung);
		return startkapital;
	}

	public static double getZinsPerMonth(double kapital, double zins) {
		final double zins_per_month = kapital * (zins / 100) / 12;
		return zins_per_month;
	}

	public double getTilgung() {
		if (laufzeit != 0 && tilgung == 0) {
			tilgung = calculateTilgung(laufzeit);
		}
		return tilgung;
	}
}
