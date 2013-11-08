package de.hypoport.einarbeitung.lars;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

@SuppressWarnings("serial")
public class ZinsRechnerPage extends WebPage {

	private IModel<ZinsRechnerBean> beanModel;

	public ZinsRechnerPage(PageParameters parameter) {
		ZinsRechnerBean bean = new ZinsRechnerBean();
		if (parameter != null) {
			StringValue value = parameter.get("result");
			if (value != null && !value.isEmpty()) {
				bean.setResultat(Double.parseDouble(value.toString()));
			}
			StringValue miete = parameter.get("miete");
			if (miete != null && !miete.isEmpty()) {
				bean.setMiete(Double.parseDouble(miete.toString()));
			}
			StringValue extra = parameter.get("extra");
			if (extra != null && !extra.isEmpty()) {
				bean.setResultat(Double.parseDouble(extra.toString()));
			}
			StringValue zins = parameter.get("zins");
			if (zins != null && !zins.isEmpty()) {
				bean.setResultat(Double.parseDouble(zins.toString()));
			}
			StringValue tilgung = parameter.get("tilgung");
			if (tilgung != null && !tilgung.isEmpty()) {
				bean.setResultat(Double.parseDouble(tilgung.toString()));
			}
			StringValue laufzeit = parameter.get("laufzeit");
			if (laufzeit != null && !laufzeit.isEmpty()) {
				bean.setResultat(Double.parseDouble(laufzeit.toString()));
			}
		}
		beanModel = new Model<ZinsRechnerBean>();
		add(new ZinsRechnerPanel("rechner", beanModel));
	}
}
