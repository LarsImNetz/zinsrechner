package de.hypoport.einarbeitung.lars;



import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestZinsRechnerPanel {

	protected WicketTester tester;
	private ZinsRechnerBean bean;
	private FormTester formTester;

	@Before
	public void setUp() {

		bean = new ZinsRechnerBean();

		tester = new WicketTester();
		tester.startComponentInPage(new ZinsRechnerPanel("panel", new CompoundPropertyModel<ZinsRechnerBean>(bean)));
		formTester = tester.newFormTester("panel:form");
	}

	@After
	public void after() throws Exception {
		tester.destroy();
	}

	@Test
	public void test() {
		tester.assertEnabled("panel:form:miete");
		tester.assertEnabled("panel:form:sparen");
		tester.assertEnabled("panel:form:tilgung");
		tester.assertEnabled("panel:form:zins");
		tester.assertEnabled("panel:form:laufzeit");

		tester.assertEnabled("panel:form:resultat");
	}

	@Test
	public void testInput() {
		Component component = tester.getComponentFromLastRenderedPage("panel:form:miete");
		TextField<Double> miete = (TextField<Double>) component;
		Assert.assertNotNull(miete);
	}

	@Test
	public void testSetValue() {
		formTester.setValue("miete", "450");
		formTester.submit();
		Assert.assertEquals(Double.valueOf(450.0), bean.getMiete(), 0.001d);
	}

	@Test
	public void testBerechnungUeberTilgung() {
		formTester.setValue("miete", "450"); // startkapital
		formTester.setValue("sparen", "100");
		formTester.setValue("zins", "3");
		formTester.setValue("tilgung", "2");
		// formTester.setValue("laufzeit", String.valueOf(12 * 30));

		formTester.submit();

		Assert.assertEquals(Double.valueOf(132000), bean.getEndkapital(), 0.01d);
	}
	
	@Test
	public void testBerechnungUeberTilgung2() {
		formTester.setValue("miete", "450"); // startkapital
		formTester.setValue("sparen", "100");
		formTester.setValue("zins", "3,5");
		formTester.setValue("tilgung", "2");
		// formTester.setValue("laufzeit", String.valueOf(12 * 30));

		formTester.submit();

		Assert.assertEquals(Double.valueOf(120000), bean.getEndkapital(), 0.01d);
	}

	@Test
	public void testBerechnungUeberTilgung3() {
		formTester.setValue("miete", "450"); // startkapital
		formTester.setValue("sparen", "100");
		formTester.setValue("zins", "3");
		formTester.setValue("tilgung", "2,5");
		// formTester.setValue("laufzeit", String.valueOf(12 * 30));

		formTester.submit();

		Assert.assertEquals(Double.valueOf(120000), bean.getEndkapital(), 0.01d);
	}

	@Test
	public void testBerechnungUeberLaufzeit() {
		formTester.setValue("miete", "450"); // startkapital
		formTester.setValue("sparen", "100");
		formTester.setValue("zins", "3");
		formTester.setValue("laufzeit", "368");
		// formTester.setValue("laufzeit", String.valueOf(12 * 30));

		formTester.submit();

		Assert.assertEquals(Double.valueOf(132000), bean.getEndkapital(), 0.01d);
		Assert.assertEquals(Double.valueOf(2), bean.getTilgung(), 0.01d);
	}
	
	@Test
	public void testLearn() {
		
	}
}
