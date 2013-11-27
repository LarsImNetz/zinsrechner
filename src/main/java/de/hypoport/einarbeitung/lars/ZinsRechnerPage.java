package de.hypoport.einarbeitung.lars;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

@SuppressWarnings("serial")
public class ZinsRechnerPage extends WebPage {

	private IModel<ZinsRechnerBean> beanModel;

	public ZinsRechnerPage(PageParameters parameter) {
		ZinsRechnerBean bean = new ZinsRechnerAdapter().adapt(parameter);
		
		beanModel = new Model<ZinsRechnerBean>(bean);
		add(new ZinsRechnerPanel("rechner", beanModel));
	}

	@Override
	protected void setHeaders(WebResponse response) {
		super.setHeaders(response);
	}
}
