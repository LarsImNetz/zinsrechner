package de.hypoport.einarbeitung.lars;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.model.Model;

public class MarkIfValidationFailedBehavior extends AjaxFormComponentUpdatingBehavior {

	/*
	 * mit onblur wird ein Event ausgelöst, wenn eine FormComponente verlassen wird, also z.B. der Cursor zum nächsten
	 * Feld weiter wandert.
	 * 
	 * mit 'onkeyup' wird ein Event ausgelöst, wenn eine Taste gedrückt wurde, leider lassen sich damit bisher nicht die
	 * Testfields vernünftig überwachen, da
	 * immer alles markiert wird, sobald der Event gefeuert wird.
	 */
	private static final String REACT_ON = "onblur";

	private IValueChanger iconChanger;

	public MarkIfValidationFailedBehavior(IValueChanger changer) {
		super(REACT_ON);
		this.iconChanger = changer;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onUpdate(AjaxRequestTarget target) {
		Component c = this.getComponent();
		c.add(new AttributeModifier("style", Model.of("background-color:#fff;")));
		target.add(c);

		iconChanger.change(2);
		target.add(iconChanger.getComponent());
	}

	@Override
	protected void onError(AjaxRequestTarget target, RuntimeException e) {
		Component c = this.getComponent();
		c.add(new AttributeModifier("style", Model.of("background-color:#f00;")));
		target.add(c);

		iconChanger.change(3);
		target.add(iconChanger.getComponent());

		super.onError(target, e);
	}

	// Hint: Wenn wir setRequired erfordern, dann wird beim löschen des letzten Zeichens nicht onUpdate() gefeuert, sondern onError()
	// da wir ja einen Wert erwarten.
	//		textField.setRequired(true);
}
