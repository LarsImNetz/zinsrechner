package de.hypoport.einarbeitung;

import java.util.Locale;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import de.hypoport.einarbeitung.lars.ZinsRechnerPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start
 * class.
 * 
 * @see org.homelinux.moonserver.JettyStart#main(String[])
 */
public class WicketApplication extends WebApplication {

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		// return HomePage.class;
		return ZinsRechnerPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		// add your configuration here
	}

	/**
	 * Mal auf Deutsch einstellen
	 */
	@Override
	public Session newSession(Request request, Response response) {
		// TODO Auto-generated method stub
		Session newSession = super.newSession(request, response);
		newSession.setLocale(Locale.GERMANY);
		return newSession;
	}

	@Override
	public RuntimeConfigurationType getConfigurationType() {
		return RuntimeConfigurationType.DEVELOPMENT; //  DEPLOYMENT;
	}

	//	@Override
	//	public String getConfigurationType() {
	//
	//		return Application.DEPLOYMENT;
	//
	//	}
}
