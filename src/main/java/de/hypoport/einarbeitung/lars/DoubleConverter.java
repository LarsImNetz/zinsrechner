package de.hypoport.einarbeitung.lars;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.wicket.util.convert.converter.AbstractConverter;

public class DoubleConverter extends AbstractConverter<Double> {

	@Override
	public Double convertToObject(String value, Locale locale) {
		NumberFormat nf = NumberFormat.getInstance(locale);
		try {
			Number myNumber = nf.parse(value);
			nf.setMaximumFractionDigits(2);
			// nf.setGroupingUsed(true);
			return myNumber.doubleValue();
		} catch (ParseException ex) {
			this.newConversionException(ex.getMessage(), value, locale);
		}
		return null;
	}

	@Override
	public String convertToString(Double value, Locale locale) {
		NumberFormat nf = NumberFormat.getInstance(locale);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(true);
		final String s = nf.format(value);
		return s;
	}

	@Override
	protected Class<Double> getTargetType() {
		return Double.class;
	}

}
