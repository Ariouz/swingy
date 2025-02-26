package fr.vicalvez.swingy.view.gui.style;

import java.awt.*;

public enum ButtonColor {

	BLUE(new Color(59, 89, 182), new Color(79, 109, 202)),
	GREEN(new Color(59, 182, 84), new Color(79, 202, 118)),
	GRAY(new Color(145, 154, 145), new Color(174, 182, 176)),
	RED	(new Color(177, 7, 7), new Color(209, 9, 9)),

	;

	private final Color background;
	private final Color hover;

	ButtonColor(Color background, Color hover)
	{
		this.background = background;
		this.hover = hover;
	}

	public Color getBackground() {
		return background;
	}

	public Color getHover() {
		return hover;
	}
}
