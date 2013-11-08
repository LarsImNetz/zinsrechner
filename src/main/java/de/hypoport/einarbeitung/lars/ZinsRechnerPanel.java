package de.hypoport.einarbeitung.lars;

import java.io.Serializable;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.validation.IErrorMessageSource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidationError;
import org.apache.wicket.validation.IValidator;

public class ZinsRechnerPanel extends Panel {

	public ZinsRechnerPanel(String id, final IModel<ZinsRechnerBean> beanModel) {
		super(id, beanModel);

		Form<ZinsRechnerBean> form = new Form<ZinsRechnerBean>("form") {

			@Override
			protected void onSubmit() {
				// super.onSubmit();
				// setResponsePage(ZinsRechnerSubmitPage.class);
				ZinsRechnerBean bean = beanModel.getObject();
				bean.calculate(); // Rechner anwerfen

				double result = Math.floor(bean.getEndkapital());
				bean.setResultat(result);
				info("Berechnung durchgeführt");

				PageParameters parameter = new PageParameters();
				parameter.add("miete", bean.getMiete());
				parameter.add("extra", bean.getSparen());
				parameter.add("zins", bean.getZins());
				parameter.add("tilgung", bean.getTilgung());
				parameter.add("laufzeit", bean.getLaufzeit());
				parameter.add("result", bean.getResultat());
				setResponsePage(ZinsRechnerPage.class, parameter);
				// super.onSubmit();
				// try {
				// Thread.sleep(5000);
				// } catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			}
		};

		TextField<Double> miete = new TextField<Double>("miete",
				new PropertyModel<Double>(beanModel, "miete"), Double.class);
		miete.setRequired(true);
		miete.add(new IValidator<Double>() {

			@Override
			public void validate(IValidatable<Double> validatable) {
				final Double value = validatable.getValue();
				if (value < 0) {
					validatable.error(new IValidationError() {

						@Override
						public Serializable getErrorMessage(
								IErrorMessageSource messageSource) {
							return "Die Miete sollte ein positiver Wert sein.";
						}
					});
				}
			}

		});
		ValidationIconPanel mieteFeedback = new ValidationIconPanel(
				"mieteFeedback");
		form.add(mieteFeedback);
		miete.add(new MarkIfValidationFailedBehavior(mieteFeedback));
		miete.setOutputMarkupId(true);
		form.add(miete);

		TextField<Double> sparen = new TextField<Double>("sparen",
				new PropertyModel<Double>(beanModel, "sparen"), Double.class);
		form.add(sparen);

		TextField<Double> zins = new TextField<Double>("zins",
				new PropertyModel<Double>(beanModel, "zins"), Double.class);
		zins.add(new IValidator<Double>() {

			@Override
			public void validate(IValidatable<Double> validatable) {
				final Double value = validatable.getValue();
				if (value < 0) {
					validatable.error(new IValidationError() {

						@Override
						public Serializable getErrorMessage(
								IErrorMessageSource messageSource) {
							return "Die Zins sollte z.Z. ein positiver Wert sein.";
						}
					});
				}
			}

		});
		ValidationIconPanel zinsFeedback = new ValidationIconPanel(
				"zinsFeedback");
		form.add(zinsFeedback);
		zins.add(new MarkIfValidationFailedBehavior(zinsFeedback));
		form.add(zins);

		TextField<Double> tilgung = new TextField<Double>("tilgung",
				new PropertyModel<Double>(beanModel, "tilgung"), Double.class);
		tilgung.add(new IValidator<Double>() {

			@Override
			public void validate(IValidatable<Double> validatable) {
				final Double value = validatable.getValue();
				if (value < 0) {
					validatable.error(new IValidationError() {

						@Override
						public Serializable getErrorMessage(
								IErrorMessageSource messageSource) {
							return "Der Tilgungssatz sollte ein positiver Wert sein.";
						}
					});
				}
			}

		});
		ValidationIconPanel tilgungFeedback = new ValidationIconPanel(
				"tilgungFeedback");
		form.add(tilgungFeedback);
		tilgung.add(new MarkIfValidationFailedBehavior(tilgungFeedback));
		tilgung.setOutputMarkupId(true);
		form.add(tilgung);

		TextField<Integer> laufzeit = new TextField<Integer>("laufzeit",
				new PropertyModel<Integer>(beanModel, "laufzeit"),
				Integer.class) {

			// TODO: hier wäre ein kleiner Interpreter nett, der soetwas wie
			// 12*j + 10 annehmen könnte.
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
			// protected Integer convertValue(String[] value) throws
			// org.apache.wicket.util.convert.ConversionException {
			// Integer i = super.convertValue(value);
			// return i;
			// };
		};
		laufzeit.setOutputMarkupId(true);
		form.add(laufzeit);

		TextField<Double> result = new TextField<Double>("resultat",
				new PropertyModel<Double>(beanModel, "resultat"), Double.class);
		result.setOutputMarkupId(true);
		form.add(result);

		form.add(new FeedbackPanel("feedback"));

		add(form);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(CssHeaderItem.forReference(new CssResourceReference(ZinsRechnerPage.class, "ZinsRechnerPage.css")));
		super.renderHead(response);
	}
}
