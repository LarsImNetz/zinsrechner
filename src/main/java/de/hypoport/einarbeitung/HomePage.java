package de.hypoport.einarbeitung;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.RequestUtils;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.cycle.RequestCycle;
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

		add(new Label("label", Model.of("Label Content")));
		
		// setResponsePage(ZinsRechnerPage.class);

//		RequestUtils.toAbsolutePath(urlFor(ZinsRechnerPage.class, new PageParameters()));
		String renderFullUrl = RequestCycle.get().getUrlRenderer().renderFullUrl(
				   Url.parse(urlFor(ZinsRechnerPage.class,null).toString()));
		System.out.println(renderFullUrl);

		CharSequence urlFor = urlFor(ZinsRechnerPage.class, new PageParameters());
		System.out.println(urlFor);

		String absolutePath = RequestUtils.toAbsolutePath(urlFor.toString(), "");
		System.out.println(absolutePath);
	}
}
