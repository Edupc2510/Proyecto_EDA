/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.Resources.RoundedBorder;
import TDA.Lista;
import TDA.Pila;
import controlador.Administrador;
import controlador.Expediente;
import controlador.Interesado;
import controlador.Seguimiento;
import controlador.Tramite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class ConsultaDeSeguimiento extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ConsultaDeSeguimiento.class.getName());

    /**
     * Creates new form ConsultaDeSeguimiento
     */
    public ConsultaDeSeguimiento() {
        initComponents();
        this.setLocationRelativeTo(null);
        
       //Diseño
       try {
            InputStream is = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 18f);
            InputStream is2 = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold2 = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 10f);
            InputStream is3 = getClass().getResourceAsStream("/fonts/OpenSans-VariableFont_wdth,wght.ttf");
            Font openSans = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 10f);
            setLayout(null);
            bg.setLayout(null);
            tramites.setFont(openSans);
            seguimiento.setFont(openSans);
            consulta.setFont(openSansBold);
            alertaDni.setFont(openSans);

            // Texto Usuario
            buscar.setFont(openSans);
            nombreBuscar.setFont(openSans);
            nombreBuscar.setBorder(new RoundedBorder(8,Color.BLACK));
            buscar.setOpaque(true);
            buscar.setBackground(Color.WHITE);

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(239, 111, 35));
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(openSansBold2);
                return label;
            }
        };

        for (int i = 0; i < Ttramites.getColumnCount(); i++) {
            Ttramites.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        for (int i = 0; i < Tseguimiento.getColumnCount(); i++) {
            Tseguimiento.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        jScrollPane2.getViewport().setBackground(Color.WHITE);        
        Ttramites.setBackground(Color.WHITE);
        Ttramites.setForeground(Color.BLACK);
        Ttramites.setShowVerticalLines(false);
        Ttramites.setShowHorizontalLines(true);
        jScrollPane1.getViewport().setBackground(Color.WHITE);        
        Tseguimiento.setBackground(Color.WHITE);
        Tseguimiento.setForeground(Color.BLACK);
        Tseguimiento.setShowVerticalLines(false);
        Tseguimiento.setShowHorizontalLines(true);
        
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(openSans);

            if (isSelected) {
                setBackground(new Color(239, 111, 35)); // naranja
                setForeground(Color.WHITE);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }

            return this;
        }
    };

        
        for (int i = 0; i < Ttramites.getColumnModel().getColumnCount(); i++) {
        Ttramites.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
        for (int i = 0; i < Tseguimiento.getColumnModel().getColumnCount(); i++) {
        Tseguimiento.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }
            
            botonBuscar.setFont(openSansBold2);
            botonBuscar.setContentAreaFilled(false);
            botonBuscar.setFocusPainted(false);
            botonBuscar.setBorderPainted(false);
            botonBuscar.setOpaque(false);
                    
            botonBuscar.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 30, 30);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });

            // Boton Cancelar
            cancelar.setContentAreaFilled(false); 
            cancelar.setBorderPainted(false);    
            cancelar.setFocusPainted(false);      
            cancelar.setOpaque(false);            
            cancelar.setForeground(Color.BLACK);  
            cancelar.setFont(openSansBold2);           
            cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));

            
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        Ttramites.addMouseListener(new java.awt.event.MouseAdapter()
        
        {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        int filaSeleccionada = Ttramites.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String idExpediente = (String) Ttramites.getValueAt(filaSeleccionada, 0); 
            mostrarSeguimiento(idExpediente);
            }
        }
        });
    }
    private void mostrarSeguimiento(String idExpediente) {
    Administrador admin = Administrador.getInstancia();
    Tramite tramite = admin.buscarTramitePorID(idExpediente); 
    if (tramite != null) {
        Expediente exp = tramite.getExpediente();
        Lista<Seguimiento> seguimientos = exp.getSeguimientos(); 

        DefaultTableModel modelo = (DefaultTableModel) Tseguimiento.getModel();
        modelo.setRowCount(0); 

        for (int i = 1; i <= seguimientos.longitud(); i++) {
            Seguimiento s = seguimientos.iesimo(i);
            String origen = "N/A";
            
            if (s.getOrigen() != null) {
            origen = s.getOrigen().getNombre();
            }

            String destino = "N/A";
            if (s.getDestino() != null) {
            destino = s.getDestino().getNombre();
            }
            modelo.addRow(new Object[]{
                origen,
                destino,
                s.getFh().toString(),
                s.getObservacion()
            });
        }
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        consulta = new javax.swing.JLabel();
        tramites = new javax.swing.JLabel();
        buscar = new javax.swing.JLabel();
        seguimiento = new javax.swing.JLabel();
        nombreBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tseguimiento = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Ttramites = new javax.swing.JTable();
        botonBuscar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        alertaDni = new javax.swing.JLabel();
        dniIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 29));

        consulta.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        consulta.setText("Consulta de Seguimiento");
        bg.add(consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 300, 30));

        tramites.setText("Trámites del Interesado");
        bg.add(tramites, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 225, -1, -1));

        buscar.setText("Buscar por DNI");
        bg.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 162, 74, -1));

        seguimiento.setText("Seguimiento");
        bg.add(seguimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(414, 35, -1, -1));

        nombreBuscar.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreBuscarFocusLost(evt);
            }
        });
        bg.add(nombreBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 170, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Tseguimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origen", "Destino", "Fecha y Hora", "Información"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tseguimiento.setGridColor(new java.awt.Color(220, 220, 220));
        Tseguimiento.setRowHeight(40);
        Tseguimiento.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Tseguimiento);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 360, 412));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Ttramites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Asunto", "Prioridad", "Fecha y Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Ttramites.setGridColor(new java.awt.Color(220, 220, 220));
        Ttramites.setRowHeight(40);
        Ttramites.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(Ttramites);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 340, 220));

        botonBuscar.setBackground(new java.awt.Color(239, 111, 35));
        botonBuscar.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        botonBuscar.setForeground(new java.awt.Color(255, 255, 255));
        botonBuscar.setText("Buscar");
        botonBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonBuscarMouseExited(evt);
            }
        });
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        bg.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 170, 84, 30));

        cancelar.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        cancelar.setText("Cancelar");
        cancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        bg.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(728, 465, 46, 20));

        alertaDni.setBackground(new java.awt.Color(255, 255, 255));
        alertaDni.setForeground(new java.awt.Color(255, 255, 255));
        alertaDni.setText("Introduzca un DNI válido.");
        bg.add(alertaDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 201, 266, -1));

        dniIcon.setBackground(new java.awt.Color(255, 255, 255));
        dniIcon.setForeground(new java.awt.Color(255, 255, 255));
        dniIcon.setText("🛈");
        bg.add(dniIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 201, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:
        String dni = nombreBuscar.getText().trim();
        if (!dni.matches("\\d{8}")|| dni.isEmpty()) {
            alertaDni.setText("Introduzca un DNI válido.");
            alertaDni.setForeground(new Color(239,111,35));
            dniIcon.setForeground(new Color(239,111,35));
            return;
        }else{
            alertaDni.setForeground(Color.WHITE);
            dniIcon.setForeground(Color.WHITE);
        }
        
        mostrarTramitesPorDNI(dni);
       
    }//GEN-LAST:event_botonBuscarActionPerformed
    
    
    public void mostrarTramitesPorDNI(String dni) {
    DefaultTableModel modelo = (DefaultTableModel) Ttramites.getModel();
    modelo.setRowCount(0);
    Administrador admin = Administrador.getInstancia();
        Interesado in = admin.buscarInteresado(dni);

        if (in == null) {
            alertaDni.setText("No se encontraron trámites con este DNI.");
            alertaDni.setForeground(new Color(239,111,35));
            dniIcon.setForeground(new Color(239,111,35));
            return;
        }else{
            alertaDni.setForeground(Color.WHITE);
            dniIcon.setForeground(Color.WHITE);
        }

        
        Pila<Tramite> pila = in.getTramites();
        Pila<Tramite> aux = new Pila<>();

        while (!pila.esVacia()) {
            Tramite t = pila.desapilar();
            aux.apilar(t);
            modelo.addRow(new Object[]{
                t.getExpediente().getId(),
                t.getExpediente().getAsunto(),
                t.getExpediente().getPrioridad(),
                t.getInicio().toString()
            });
            
        }


        while (!aux.esVacia()) {
            pila.apilar(aux.desapilar());
        }

    }
    
            
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Registro menu = new Registro();
        menu.setVisible(true); 
        
        this.dispose(); 
    }//GEN-LAST:event_cancelarActionPerformed

    private void botonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseEntered
        Color colorHover = botonBuscar.getBackground().darker();
        botonBuscar.setBackground(colorHover);
    }//GEN-LAST:event_botonBuscarMouseEntered

    private void botonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseExited
        Color colorNormal = new Color(239, 111, 35);
        botonBuscar.setBackground(colorNormal);
    }//GEN-LAST:event_botonBuscarMouseExited

    private void nombreBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusGained
        nombreBuscar.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        buscar.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreBuscarFocusGained

    private void nombreBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusLost
        nombreBuscar.setBorder(new RoundedBorder(10,Color.BLACK));
        buscar.setForeground(Color.BLACK); 
    }//GEN-LAST:event_nombreBuscarFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ConsultaDeSeguimiento().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tseguimiento;
    private javax.swing.JTable Ttramites;
    private javax.swing.JLabel alertaDni;
    private javax.swing.JPanel bg;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JLabel buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel consulta;
    private javax.swing.JLabel dniIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nombreBuscar;
    private javax.swing.JLabel seguimiento;
    private javax.swing.JLabel tramites;
    // End of variables declaration//GEN-END:variables
}
