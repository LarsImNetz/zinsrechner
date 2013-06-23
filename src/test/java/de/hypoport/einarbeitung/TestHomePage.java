package de.hypoport.einarbeitung;

import org.apache.wicket.Page;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import de.hypoport.einarbeitung.lars.ZinsRechnerPage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {

	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully() {
		//start and render the test page
		// tester.startPage(HomePage.class);
		tester.startPage(ZinsRechnerPage.class);

		//assert rendered page class
		tester.assertRenderedPage(ZinsRechnerPage.class);
		
		Page lastRenderedPage = tester.getLastRenderedPage();
		// tester.urlFor(lastRenderedPage);
	}
}
