package de.hypoport.einarbeitung.lars;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@SuppressWarnings("serial")
public class ValidationIconPanel extends Panel implements IValueChanger {

	final ResourceReference emptyImage;
	final ResourceReference nopImage;
	final ResourceReference okImage;

	Image nothingimage;
	Image okimage;
	Image notokimage;

	public ValidationIconPanel(String id) {
		this(id, Model.of(Integer.valueOf(1)));
	}

	public ValidationIconPanel(String id, IModel<Integer> iconVisible) {
		super(id, iconVisible);

		emptyImage = new PackageResourceReference(ValidationIconPanel.class, "empty.png");
		nopImage = new PackageResourceReference(ValidationIconPanel.class, "nop.png");
		okImage = new PackageResourceReference(ValidationIconPanel.class, "ok.png");

		nothingimage = new Image("nothing", emptyImage);
		// image.setOutputMarkupId(true);
		// image.setEnabled(true);
		add(nothingimage);

		okimage = new Image("ok", okImage);
		// okimage.setVisible(false);
		add(okimage);

		notokimage = new Image("notok", nopImage);
		// notokimage.setVisible(false);
		add(notokimage);

		setOutputMarkupId(true);
	}

	@Override
	protected void onConfigure() {
		Integer iconVisible = (Integer) getDefaultModelObject();
		if (iconVisible != null) {
			nothingimage.setVisible(false);
			okimage.setVisible(false);
			notokimage.setVisible(false);

			if (iconVisible.equals(Integer.valueOf(1))) {
				nothingimage.setVisible(true);
			}
			if (iconVisible.equals(Integer.valueOf(2))) {
				okimage.setVisible(true);
			}
			if (iconVisible.equals(Integer.valueOf(3))) {
				notokimage.setVisible(true);
			}
		}
		super.onConfigure();
	}

	@Override
	public void change(Integer newValue) {
		setDefaultModel(Model.of(newValue));
	}

	@Override
	public Component getComponent() {
		return this;
	}
}
