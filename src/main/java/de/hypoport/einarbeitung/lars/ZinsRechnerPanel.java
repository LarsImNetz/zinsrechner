package de.hypoport.einarbeitung.lars;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class ZinsRechnerPanel extends Panel {

	public ZinsRechnerPanel(String id, final CompoundPropertyModel<ZinsRechnerBean> beanModel) {
		super(id, beanModel);

		Form<Void> form = new Form<Void>("form") {

			@Override
			protected void onSubmit() {
				// super.onSubmit();
				// setResponsePage(ZinsRechnerSubmitPage.class);
				ZinsRechnerBean bean = beanModel.getObject();
				bean.calculate(); // Rechner anwerfen

				double result = Math.floor(bean.getEndkapital());
				bean.setResultat(result);
				info("Berechnung durchgeführt");
			}
		};

		TextField<Double> miete = new TextField<Double>("miete");
		form.add(miete);

		TextField<Double> sparen = new TextField<Double>("sparen");
		form.add(sparen);

		TextField<Double> zins = new TextField<Double>("zins");
		form.add(zins);

		TextField<Double> tilgung = new TextField<Double>("tilgung");
		form.add(tilgung);

		TextField<Integer> laufzeit = new TextField<Integer>("laufzeit") {

			// TODO: hier wäre ein kleiner Interpreter nett, der soetwas wie 12*j + 10 annehmen könnte.
			public String getInput() {
				String input = super.getInput();
				int indexOfYear = input.indexOf('j');
				if (indexOfYear != -1) {
					String yearAsString = input.substring(0, indexOfYear);
					int years = Integer.parseInt(yearAsString) * 12;
					return String.valueOf(years);
				}
				return input;
			};
			//			protected Integer convertValue(String[] value) throws org.apache.wicket.util.convert.ConversionException {
			//				Integer i = super.convertValue(value);
			//				return i;
			//			};
		};
		form.add(laufzeit);

		TextField<Double> result = new TextField<Double>("resultat");
		form.add(result);

		form.add(new FeedbackPanel("feedback"));

		add(form);
	}
}
