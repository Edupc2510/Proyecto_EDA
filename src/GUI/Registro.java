/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import TDA.Cola;
import TDA.Lista;
import TDANoLineal.ArbolBB;
import controlador.Administrador;
import controlador.Dependencia;
import controlador.Expediente;
import controlador.FechaHora;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
/**
 *
 * @author andre
 */
public class Registro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Registro.class.getName());
    Administrador admin = Administrador.getInstancia();
    Lista<Dependencia> deps = Dependencia.getInstancias();
    
    private static String obtenerFechaUltimoMovimiento(Expediente e) {
    if (e.getSeguimientos().esVacia()) return "Sin fecha";
    FechaHora fh = e.getSeguimientos().iesimo(e.getSeguimientos().longitud()).getFh();
    return fh.toString(); // Suponiendo que tu clase FechaHora tiene un toString() adecuado
    }
    
    
    private static String obtenerDependenciaActual(Expediente e) {
    if (e.getSeguimientos().esVacia()) return "Sin dependencia";
    String dependencia = e.getSeguimientos().iesimo(e.getSeguimientos().longitud()).getDestino().getNombre();
    return dependencia; 
    }   
    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        this.setLocationRelativeTo(null);
        DefaultTableModel modelo = (DefaultTableModel) alertas.getModel();
        modelo.setRowCount(0);
        Lista<Expediente> ordenados = admin.obtenerExpedientesOrdenados(deps);
        for (int i = 1; i <= ordenados.longitud(); i++) {
            Expediente e = ordenados.iesimo(i);
            
            String fecha = obtenerFechaUltimoMovimiento(e);
            String dependencia = obtenerDependenciaActual(e);
            
            modelo.addRow(new Object[]{
            e.getId(), e.getAsunto(), e.getPrioridad(), fecha, dependencia
            });
        }
        
        
        
        
        
        // Diseño
        
        
        
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 18f);
            InputStream is2 = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold2 = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 13f);
            InputStream is3 = getClass().getResourceAsStream("/fonts/OpenSans-VariableFont_wdth,wght.ttf");
            Font openSans = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 13f);
            setLayout(null);
            bg.setLayout(null);
            bienvenido.setFont(openSansBold);
            pregunta.setFont(openSans);
            tramites.setFont(openSans);
            op1.setFont(openSansBold2);
            op1.setContentAreaFilled(false);
            op1.setFocusPainted(false);
            op1.setBorderPainted(false);
            op1.setOpaque(false);
                    
            op1.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });
            
            
            
            op2.setContentAreaFilled(false);
            op2.setFont(openSansBold2);
            op2.setFocusPainted(false);
            op2.setBorderPainted(false);
            op2.setOpaque(false);
                    
            op2.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });
            
            
            
            op3.setFont(openSansBold2);
            op3.setContentAreaFilled(false);
            op3.setFocusPainted(false);
            op3.setBorderPainted(false);
            op3.setOpaque(false);
                    
            op3.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });
            
            op4.setFont(openSansBold2);
            op4.setContentAreaFilled(false);
            op4.setFocusPainted(false);
            op4.setBorderPainted(false);
            op4.setOpaque(false);
                    
            op4.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });

            op5.setFont(openSansBold2);
            op5.setContentAreaFilled(false);
            op5.setFocusPainted(false);
            op5.setBorderPainted(false);
            op5.setOpaque(false);
                    
            op5.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
            
            salir.setFont(openSansBold2);
            salir.setContentAreaFilled(false);
            salir.setFocusPainted(false);
            salir.setBorderPainted(false);
            salir.setOpaque(false);
                    
            salir.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                @Override
                public void paint(Graphics g, JComponent c) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(c.getBackground());
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 40, 40);
                    g2.dispose();
                    super.paint(g, c);
                } 
            });   

            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(239, 111, 35));
                label.setForeground(Color.WHITE);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(openSansBold2); // AQUÍ sí se usa tu fuente
                return label;
            }
        };

        // Aplicar el renderer a cada columna
        for (int i = 0; i < alertas.getColumnCount(); i++) {
            alertas.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);        
        alertas.setBackground(Color.WHITE);
        alertas.setForeground(Color.BLACK);
        alertas.setShowVerticalLines(false);
        alertas.setShowHorizontalLines(true);
        
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(openSans); // ← Aquí asegúrate que `openSans` ya está bien cargado
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);

            return this;
        }
        };
        
        for (int i = 0; i < alertas.getColumnModel().getColumnCount(); i++) {
        alertas.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }



        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
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
        bienvenido = new javax.swing.JLabel();
        salir = new javax.swing.JButton();
        op5 = new javax.swing.JButton();
        op2 = new javax.swing.JButton();
        op3 = new javax.swing.JButton();
        op4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        alertas = new javax.swing.JTable();
        tramites = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        op1 = new javax.swing.JButton();
        pregunta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 29));

        bienvenido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bienvenido.setText("Bienvenido 20232157,");
        bg.add(bienvenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 90, 245, 30));

        salir.setBackground(new java.awt.Color(0, 0, 0));
        salir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Salir");
        salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                salirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salirMouseExited(evt);
            }
        });
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        bg.add(salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 220, 40));

        op5.setBackground(new java.awt.Color(239, 111, 35));
        op5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        op5.setForeground(new java.awt.Color(255, 255, 255));
        op5.setText("Historial");
        op5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op5ActionPerformed(evt);
            }
        });
        bg.add(op5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 90, 30));

        op2.setBackground(new java.awt.Color(239, 111, 35));
        op2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        op2.setForeground(new java.awt.Color(255, 255, 255));
        op2.setText("Registrar Movimiento");
        op2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                op2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                op2MouseExited(evt);
            }
        });
        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op2ActionPerformed(evt);
            }
        });
        bg.add(op2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 220, 40));

        op3.setBackground(new java.awt.Color(239, 111, 35));
        op3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        op3.setForeground(new java.awt.Color(255, 255, 255));
        op3.setText("Finalizar Trámite");
        op3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                op3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                op3MouseExited(evt);
            }
        });
        op3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op3ActionPerformed(evt);
            }
        });
        bg.add(op3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 220, 40));

        op4.setBackground(new java.awt.Color(239, 111, 35));
        op4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        op4.setForeground(new java.awt.Color(255, 255, 255));
        op4.setText("Consultar Seguimiento");
        op4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                op4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                op4MouseExited(evt);
            }
        });
        op4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op4ActionPerformed(evt);
            }
        });
        bg.add(op4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 220, 40));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        alertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Asunto", "Prioridad", "Fecha", "Dependencia actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        alertas.setGridColor(new java.awt.Color(220, 220, 220));
        alertas.setRowHeight(40);
        alertas.setSelectionBackground(new java.awt.Color(0, 0, 0));
        alertas.setSurrendersFocusOnKeystroke(true);
        alertas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(alertas);
        if (alertas.getColumnModel().getColumnCount() > 0) {
            alertas.getColumnModel().getColumn(0).setPreferredWidth(60);
            alertas.getColumnModel().getColumn(1).setPreferredWidth(80);
            alertas.getColumnModel().getColumn(2).setPreferredWidth(80);
            alertas.getColumnModel().getColumn(3).setPreferredWidth(100);
            alertas.getColumnModel().getColumn(4).setPreferredWidth(148);
        }

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 470, 260));

        tramites.setText("Trámites pendientes de revisión");
        bg.add(tramites, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 218, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo1.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 490, 170));

        op1.setBackground(new java.awt.Color(239, 111, 35));
        op1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        op1.setForeground(new java.awt.Color(255, 255, 255));
        op1.setText("Registrar Expediente");
        op1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                op1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                op1MouseExited(evt);
            }
        });
        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op1ActionPerformed(evt);
            }
        });
        bg.add(op1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 220, 40));

        pregunta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pregunta.setText("¿Qué desea hacer?");
        bg.add(pregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 115, 245, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void op5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op5ActionPerformed
        // TODO add your handling code here:
        TramitesFinalizados Tra = new TramitesFinalizados();
        Tra.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_op5ActionPerformed

    private void op2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op2ActionPerformed
        // TODO add your handling code here:
        RegistroMovimiento mov = new RegistroMovimiento();
        mov.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_op2ActionPerformed

    private void op3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op3ActionPerformed
        // TODO add your handling code here:
        FinalizacionDeTramite fin = new FinalizacionDeTramite();
        fin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_op3ActionPerformed

    private void op1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op1ActionPerformed
        // TODO add your handling code here:
        RegistroExpediente registrar = new RegistroExpediente();
        registrar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_op1ActionPerformed

    private void op4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op4ActionPerformed
        // TODO add your handling code here:
        ConsultaDeSeguimiento seg = new ConsultaDeSeguimiento();
        seg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_op4ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void op1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op1MouseEntered
        Color colorHover = op1.getBackground().darker();
        op1.setBackground(colorHover);
    }//GEN-LAST:event_op1MouseEntered

    private void op1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op1MouseExited
        Color colorNormal = new Color(239, 111, 35);
        op1.setBackground(colorNormal);
    }//GEN-LAST:event_op1MouseExited

    private void op2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op2MouseEntered
        Color colorHover = op2.getBackground().darker();
        op2.setBackground(colorHover);
    }//GEN-LAST:event_op2MouseEntered

    private void op2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op2MouseExited
        Color colorNormal = new Color(239, 111, 35);
        op2.setBackground(colorNormal);
    }//GEN-LAST:event_op2MouseExited

    private void op3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op3MouseEntered
        Color colorHover = op3.getBackground().darker();
        op3.setBackground(colorHover);
    }//GEN-LAST:event_op3MouseEntered

    private void op3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op3MouseExited
        Color colorNormal = new Color(239, 111, 35);
        op3.setBackground(colorNormal);
    }//GEN-LAST:event_op3MouseExited

    private void op4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op4MouseEntered
        Color colorHover = op4.getBackground().darker();
        op4.setBackground(colorHover);
    }//GEN-LAST:event_op4MouseEntered

    private void op4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op4MouseExited
        Color colorNormal = new Color(239, 111, 35);
        op4.setBackground(colorNormal);
    }//GEN-LAST:event_op4MouseExited

    private void salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseEntered
        Color colorHover = new Color(50, 50, 50);
        salir.setBackground(colorHover);
    }//GEN-LAST:event_salirMouseEntered

    private void salirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirMouseExited
        Color colorNormal = new Color(0, 0, 0);
        salir.setBackground(colorNormal);
    }//GEN-LAST:event_salirMouseExited

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
        java.awt.EventQueue.invokeLater(() -> new Registro().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable alertas;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel bienvenido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton op1;
    private javax.swing.JButton op2;
    private javax.swing.JButton op3;
    private javax.swing.JButton op4;
    private javax.swing.JButton op5;
    private javax.swing.JLabel pregunta;
    private javax.swing.JButton salir;
    private javax.swing.JLabel tramites;
    // End of variables declaration//GEN-END:variables
}
