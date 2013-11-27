package de.hypoport.einarbeitung.lars;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

public class ZinsRechnerAdapter implements ZinsRechnerAdoptee {

	@Override
	public ZinsRechnerBean adapt(PageParameters parameter) {
		ZinsRechnerBean bean = new ZinsRechnerBean();
		if (parameter != null) {
			StringValue miete = parameter.get("miete");
			if (miete != null && !miete.isEmpty()) {
				bean.setMiete(Double.parseDouble(miete.toString()));
			}
			StringValue extra = parameter.get("extra");
			if (extra != null && !extra.isEmpty()) {
				bean.setSparen(Double.parseDouble(extra.toString()));
			}
			StringValue zins = parameter.get("zins");
			if (zins != null && !zins.isEmpty()) {
				bean.setZins(Double.parseDouble(zins.toString()));
			}
			StringValue tilgung = parameter.get("tilgung");
			if (tilgung != null && !tilgung.isEmpty()) {
				bean.setTilgung(Double.parseDouble(tilgung.toString()));
			}
			StringValue laufzeit = parameter.get("laufzeit");
			if (laufzeit != null && !laufzeit.isEmpty()) {
				bean.setLaufzeit(Integer.parseInt(laufzeit.toString()));
			}
			StringValue value = parameter.get("result");
			if (value != null && !value.isEmpty()) {
				bean.setResultat(Double.parseDouble(value.toString()));
			}
		}
		return bean;
	}

}
