/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Resources;

/**
 *
 * @author Usuario
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class CustomRadioButton{
    public static void estilizarRadioButton(JRadioButton radioButton, Font fuente) {
        radioButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                boolean seleccionado = ((JRadioButton) c).isSelected();

                Color fondo = seleccionado ? new Color(239, 111, 35) : Color.WHITE;
                Color texto = seleccionado ? Color.WHITE : new Color(239, 111, 35);
                Color borde = new Color(239, 111, 35);

                g2.setColor(fondo);
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 25, 25);

                g2.setColor(borde);
                g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 25, 25);

                g2.dispose();

                c.setForeground(texto);

                // Trasladamos el texto hacia la derecha y hacia abajo
                Graphics2D gText = (Graphics2D) g.create();
                gText.translate(13, 0); // Mueve el texto 10px a la derecha y 4px hacia abajo
                super.paint(gText, c);
                gText.dispose();

            }
        });

        radioButton.setContentAreaFilled(false);
        radioButton.setFocusPainted(false);
        radioButton.setBorderPainted(false);
        radioButton.setOpaque(false);
        radioButton.setFont(fuente);
        radioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        radioButton.setMargin(new Insets(8, 20, 8, 20));


        // Asegurarse de que repinte cuando cambia el estado
        radioButton.addChangeListener(e -> radioButton.repaint());
    }
}
