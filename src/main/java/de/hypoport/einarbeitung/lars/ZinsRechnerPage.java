package de.hypoport.einarbeitung.lars;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class ZinsRechnerPage extends WebPage {

	private IModel<ZinsRechnerBean> bean;

	public ZinsRechnerPage() {

		bean = new Model<ZinsRechnerBean>(new ZinsRechnerBean());
		add(new ZinsRechnerPanel("rechner", bean));
	}
}
