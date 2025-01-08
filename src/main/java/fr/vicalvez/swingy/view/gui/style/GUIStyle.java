package fr.vicalvez.swingy.view.gui.style;

import javax.swing.*;
import java.awt.*;

public class GUIStyle {

	public static void styleButton(JButton button, ButtonColor color, int fontSize) {
		button.setFocusPainted(false);
		button.setBackground(color.getBackground());
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, fontSize));
		button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(color.getHover());
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(color.getBackground());
			}
		});

	}

}
