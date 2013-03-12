package de.hypoport.einarbeitung.lars;

import org.apache.wicket.Component;

public interface IValueChanger {

	public void change(Integer newValue);

	public Component getComponent();
}
