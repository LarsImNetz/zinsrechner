package de.hypoport.einarbeitung;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hypoport.einarbeitung.lars.ZinsRechnerPage;

public class HomePage extends WebPage {

	private final static Logger logger = LoggerFactory.getLogger(HomePage.class);

	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		logger.debug("HomePage of DarlehensRechner");

		setResponsePage(ZinsRechnerPage.class);
	}
}
