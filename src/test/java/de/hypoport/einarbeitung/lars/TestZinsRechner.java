package de.hypoport.einarbeitung.lars;



import org.junit.Assert;
import org.junit.Test;

public class TestZinsRechner {

	// Zinsberechnung:

	// =G1*(F$2/100)/12

	// first month:
	// =F1*12*100/(F$2+F$3)

	// nextz month:
	// =G1-(F$1 - H1)-I2

	@Test
	public void testStartkapital() {
		double rate = 550;
		double zins = 3;
		double tilgung = 2;
		int laufzeit = 0;
		double expected = rate * 12 * 100 / (zins + tilgung);

		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, laufzeit);
		double actual = zinsrechner.getStartKapital();

		Assert.assertEquals(expected, actual,0.001d);
	}

	@Test
	public void testZins() {
		double rate = 550;
		double zins = 3;
		double tilgung = 2;
		int laufzeit = 0;
		
		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, laufzeit);
		double startkapital = zinsrechner.getStartKapital();
		double actual = zinsrechner.getZinsPerMonth(startkapital);

		double zins_per_month = startkapital * (zins / 100) / 12;

		Assert.assertEquals(zins_per_month, actual,0.001d);
	}

	@Test
	public void testLaufzeit() {
		double rate = 550;
		double zins = 3;
		double tilgung = 2;
		
		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, 0);
		int laufzeit = zinsrechner.getLaufzeit();
		int expected = 368;
		Assert.assertEquals(expected, laufzeit);
	}

	@Test
	public void testLaufzeit2() {
		double rate = 550;
		double zins = 3;
		double tilgung = 2.5;
		
		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, 0);
		int laufzeit = zinsrechner.getLaufzeit();
		int expected = 317;
		Assert.assertEquals(expected, laufzeit);
	}

	@Test
	public void testLaufzeit3() {
		double rate = 550;
		double zins = 3.5;
		double tilgung = 2.5;
		
		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, 0);
		int laufzeit = zinsrechner.getLaufzeit();
		int expected = 302;
		Assert.assertEquals(expected, laufzeit);
	}

	@Test
	public void testFixeLaufzeit() {
		double rate = 550;
		double zins = 3;
		double tilgung = 0;
		int laufzeit = 23 * 12 + 4;
		
		ZinsRechner zinsrechner = new ZinsRechner(rate, zins, tilgung, laufzeit);
		// double tilgung2 = zinsrechner.calculateTilgung(23 * 12 + 4);
		// Assert.assertEquals(expected, laufzeit);
		double actual = zinsrechner.getTilgung();
		// Assert.assertTrue(actual > 2.988 && actual < 2.989);
		Assert.assertEquals(2.988, actual, 0.001);
		// int breakpoint = 0;
	}
}
