package de.hypoport.einarbeitung.lars;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

@SuppressWarnings("serial")
public class ZinsRechnerPage extends WebPage {

	private IModel<ZinsRechnerBean> bean;

	public ZinsRechnerPage(PageParameters parameter) {
		if (parameter != null) {
			StringValue value = parameter.get("result");
			int breakpoint = 1;
		}
		bean = new Model<ZinsRechnerBean>(new ZinsRechnerBean());
		add(new ZinsRechnerPanel("rechner", bean));
	}
}
