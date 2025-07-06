/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Resources;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;

public class CustomComboBox extends BasicComboBoxUI {

    private Color borderColor = Color.BLACK;
    public final int radius = 10;
    private final Color arrowColor = new Color(239, 111, 35);
    private Color fontColor = Color.BLACK;
    
    public void setBorderColor(Color color) {
        this.borderColor = color;
        if (comboBox != null) {
            comboBox.repaint();
        }
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    
    @Override
    public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
    ListCellRenderer renderer = comboBox.getRenderer();
    Object value = comboBox.getSelectedItem();

    // Usamos un JList falso solo para renderizar correctamente
    JList<Object> dummyList = new JList<>();

    Component comp = renderer.getListCellRendererComponent(
        dummyList,
        value,
        -1,
        false,
        false
    );

    // Asegurar fondo blanco y texto negro
    if (comp instanceof JLabel label) {
    label.setBackground(Color.WHITE);
    label.setForeground(fontColor);
    label.setOpaque(false);
    label.setBorder(new EmptyBorder(15, 10, 4, 10)); // ← Centrado también aquí
}   


    comp.setBounds(bounds);
    Graphics2D g2 = (Graphics2D) g.create();
    comp.paint(g2);
    g2.dispose();
}



    public static void estilizarComboBox(JComboBox comboBox, Font fuente) {
    comboBox.setRenderer(new DefaultListCellRenderer() {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setOpaque(true);
        label.setFont(fuente);



        if (index >= 0) {
            label.setBackground(isSelected ? new Color(239, 111, 35) : Color.WHITE);
            label.setForeground(isSelected ? Color.WHITE : Color.BLACK);
        } else {
            label.setBackground(Color.WHITE);
            label.setForeground(Color.BLACK);
        }

        return label;
    }
});

    comboBox.setOpaque(false); // Muy importante
    comboBox.setBackground(Color.WHITE);
    comboBox.setForeground(Color.BLACK);

    }
    
    @Override
    protected ComboPopup createPopup() {
        BasicComboPopup popup = new BasicComboPopup(comboBox) {
            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroller = super.createScroller();
                scroller.setBackground(Color.WHITE);
                scroller.getViewport().setBackground(Color.WHITE);
                return scroller;
            }

            @Override
            protected JList<Object> createList() {
                JList<Object> list = super.createList();
                list.setBackground(Color.WHITE);   // Fondo blanco de la lista
                list.setForeground(Color.BLACK);   // Texto negro
                list.setSelectionBackground(new Color(239, 111, 35)); // Fondo selección
                list.setSelectionForeground(Color.WHITE); // Texto al seleccionar
                return list;
            }
        };

        // 🔥 Aseguramos el fondo del popup entero
        popup.setOpaque(true);
        popup.setBackground(Color.WHITE); // Fondo del popup
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); // Borde suave opcional

        return popup;
    }

    
    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujamos la flechita naranja
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                int size = 8;
                int x = (w - size) / 2;
                int y = (h - size) / 2;
                Polygon arrow = new Polygon();
                arrow.addPoint(x, y);
                arrow.addPoint(x + size, y);
                arrow.addPoint(x + size / 2, y + size);
                g2.setColor(arrowColor);
                g2.fill(arrow);
                g2.dispose();
            }
        };
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        return button;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(borderColor);
        comboBox.setBorder(new EmptyBorder(5, 10, 5, -15));
        listBox.setSelectionBackground(new Color(239, 111, 35));
        listBox.setSelectionForeground(Color.WHITE);
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        // No pintamos fondo para evitar el marco gris
    }

@Override
public void paint(Graphics g, JComponent c) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // 1. Pintamos fondo blanco redondeado ANTES de todo
    g2.setColor(Color.WHITE);
    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), radius, radius);

    // 2. Luego dejamos que el combo pinte su contenido (texto, flecha)
    super.paint(g, c);

    // 3. Pintamos el borde por encima de todo
    g2.setColor(borderColor);
    g2.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, radius, radius);

    g2.dispose(); // Limpieza
}
    
}

