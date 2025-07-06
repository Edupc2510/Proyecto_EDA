/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.Resources.CustomComboBox;
import GUI.Resources.CustomRadioButton;
import GUI.Resources.RoundedBorder;
import TDA.Cola;
import TDA.Lista;
import controlador.Administrador;
import controlador.Dependencia;
import controlador.Expediente;
import controlador.FechaHora;
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
import javax.swing.ButtonGroup;
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
public class FinalizacionDeTramite extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FinalizacionDeTramite.class.getName());
    private ButtonGroup grupoFiltro;
    private boolean mpseleccionado = false;
    private boolean sseleccionado = false;
    private boolean dseleccionado = false;
    private boolean tseleccionado = false;
    private boolean bseleccionado = false;
    private boolean buseleccionado = false;
    Administrador admin = Administrador.getInstancia();
    Lista<Dependencia> deps = Dependencia.getInstancias();
    /**
     * Creates new form FinalizacionDeTramite
     */
    public FinalizacionDeTramite() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        grupoFiltro = new ButtonGroup();
        grupoFiltro.add(rbMesaPartes);
        grupoFiltro.add(rbSecretaria);
        grupoFiltro.add(rbDecanato);
        grupoFiltro.add(rbTesoreria);
        grupoFiltro.add(rbBiblioteca);
        grupoFiltro.add(rbBienestar);
        tablaCompleta();

        // Diseño
            try {
            InputStream is = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 18f);
            InputStream is2 = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold2 = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 10f);
            InputStream is3 = getClass().getResourceAsStream("/fonts/OpenSans-VariableFont_wdth,wght.ttf");
            Font openSans = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 10f);
            setLayout(null);
            bg.setLayout(null);
            filtro.setFont(openSans);
            finalizacion.setFont(openSansBold);
            alertaDoc.setFont(openSans);
            alertaDni.setFont(openSans);

            // Texto Usuario
            buscar.setFont(openSans);
            nombreBuscar.setFont(openSans);
            nombreBuscar.setBorder(new RoundedBorder(8,Color.BLACK));
            buscar.setOpaque(true);
            buscar.setBackground(Color.WHITE); // Mismo fondo que el JTextField
            // Texto Documentos
            doc.setFont(openSans);
            nombreDoc.setFont(openSans);
            nombreDoc.setBorder(new RoundedBorder(8,Color.BLACK));
            doc.setOpaque(true);
            doc.setBackground(Color.WHITE); // Mismo fondo que el JTextField


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
        for (int i = 0; i < tramites.getColumnCount(); i++) {
            tramites.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);        
        tramites.setBackground(Color.WHITE);
        tramites.setForeground(Color.BLACK);
        tramites.setShowVerticalLines(false);
        tramites.setShowHorizontalLines(true);
        
        DefaultTableCellRenderer customRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setHorizontalAlignment(SwingConstants.CENTER);
            setFont(openSans);
            if (isSelected) {
                setBackground(new Color(239, 111, 35));
                setForeground(Color.WHITE);             
            } else {
                setBackground(Color.WHITE);            
                setForeground(Color.BLACK);             
            }


            return this;
        }
        };
        
        for (int i = 0; i < tramites.getColumnModel().getColumnCount(); i++) {
        tramites.getColumnModel().getColumn(i).setCellRenderer(customRenderer);
        }

            finalizar.setFont(openSansBold2);
            finalizar.setContentAreaFilled(false);
            finalizar.setFocusPainted(false);
            finalizar.setBorderPainted(false);
            finalizar.setOpaque(false);
                    
            finalizar.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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

            CustomRadioButton.estilizarRadioButton(rbMesaPartes, openSansBold2);
            CustomRadioButton.estilizarRadioButton(rbTesoreria, openSansBold2);
            CustomRadioButton.estilizarRadioButton(rbBiblioteca, openSansBold2);
            CustomRadioButton.estilizarRadioButton(rbBienestar, openSansBold2);
            CustomRadioButton.estilizarRadioButton(rbDecanato, openSansBold2);
            CustomRadioButton.estilizarRadioButton(rbSecretaria, openSansBold2);

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
        finalizacion = new javax.swing.JLabel();
        buscar = new javax.swing.JLabel();
        nombreBuscar = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        doc = new javax.swing.JLabel();
        nombreDoc = new javax.swing.JTextField();
        finalizar = new javax.swing.JButton();
        filtro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tramites = new javax.swing.JTable();
        rbMesaPartes = new javax.swing.JRadioButton();
        rbSecretaria = new javax.swing.JRadioButton();
        rbDecanato = new javax.swing.JRadioButton();
        rbTesoreria = new javax.swing.JRadioButton();
        rbBiblioteca = new javax.swing.JRadioButton();
        rbBienestar = new javax.swing.JRadioButton();
        cancelar = new javax.swing.JButton();
        alertaDni = new javax.swing.JLabel();
        dniIcon = new javax.swing.JLabel();
        docIcon = new javax.swing.JLabel();
        alertaDoc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 29));

        finalizacion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        finalizacion.setText("Finalización de Trámite");
        bg.add(finalizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 280, 30));

        buscar.setText("Buscar por DNI");
        bg.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 162, 74, -1));

        nombreBuscar.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreBuscarFocusLost(evt);
            }
        });
        nombreBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreBuscarActionPerformed(evt);
            }
        });
        bg.add(nombreBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 170, 30));

        botonBuscar.setBackground(new java.awt.Color(239, 111, 35));
        botonBuscar.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        botonBuscar.setForeground(new java.awt.Color(255, 255, 255));
        botonBuscar.setText("Buscar");
        botonBuscar.setActionCommand("botonBuscar");
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

        doc.setText("Documentos generados ");
        bg.add(doc, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 162, 118, -1));

        nombreDoc.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreDoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreDocFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreDocFocusLost(evt);
            }
        });
        bg.add(nombreDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 170, 30));

        finalizar.setBackground(new java.awt.Color(239, 111, 35));
        finalizar.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        finalizar.setForeground(new java.awt.Color(255, 255, 255));
        finalizar.setText("Finalizar");
        finalizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        finalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                finalizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                finalizarMouseExited(evt);
            }
        });
        finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarActionPerformed(evt);
            }
        });
        bg.add(finalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 170, 84, 30));

        filtro.setText("Filtro por Dependencia");
        bg.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 223, -1, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tramites.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Asunto", "Dependencia", "Interesado", "Prioridad", "Fecha y Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tramites.setGridColor(new java.awt.Color(220, 220, 220));
        tramites.setRowHeight(40);
        tramites.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tramites);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 700, 180));

        rbMesaPartes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbMesaPartes.setForeground(new java.awt.Color(239, 111, 35));
        rbMesaPartes.setText("Mesa de Partes");
        rbMesaPartes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        rbMesaPartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMesaPartesActionPerformed(evt);
            }
        });
        bg.add(rbMesaPartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 246, -1, 25));

        rbSecretaria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbSecretaria.setForeground(new java.awt.Color(239, 111, 35));
        rbSecretaria.setText("Secretaría Académica");
        rbSecretaria.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        rbSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSecretariaActionPerformed(evt);
            }
        });
        bg.add(rbSecretaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 246, -1, 25));

        rbDecanato.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbDecanato.setForeground(new java.awt.Color(239, 111, 35));
        rbDecanato.setText("Decanato");
        rbDecanato.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        rbDecanato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDecanatoActionPerformed(evt);
            }
        });
        bg.add(rbDecanato, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 246, -1, 25));

        rbTesoreria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbTesoreria.setForeground(new java.awt.Color(239, 111, 35));
        rbTesoreria.setText("Tesorería");
        rbTesoreria.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 3, 1, 1));
        rbTesoreria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTesoreriaActionPerformed(evt);
            }
        });
        bg.add(rbTesoreria, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 246, -1, 25));

        rbBiblioteca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbBiblioteca.setForeground(new java.awt.Color(239, 111, 35));
        rbBiblioteca.setText("Biblioteca");
        rbBiblioteca.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        rbBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBibliotecaActionPerformed(evt);
            }
        });
        bg.add(rbBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 246, -1, 25));

        rbBienestar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbBienestar.setForeground(new java.awt.Color(239, 111, 35));
        rbBienestar.setText("Bienestar Universitario");
        rbBienestar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        rbBienestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbBienestarActionPerformed(evt);
            }
        });
        bg.add(rbBienestar, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 246, -1, 25));

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

        docIcon.setBackground(new java.awt.Color(255, 255, 255));
        docIcon.setForeground(new java.awt.Color(255, 255, 255));
        docIcon.setText("🛈");
        bg.add(docIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 201, -1, -1));

        alertaDoc.setBackground(new java.awt.Color(255, 255, 255));
        alertaDoc.setForeground(new java.awt.Color(255, 255, 255));
        alertaDoc.setText("Introduzca documentos válidos.");
        bg.add(alertaDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 201, 238, -1));

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
    
    private void filtrarPorDependencia(String nombreDep) {
        DefaultTableModel modelo = (DefaultTableModel) tramites.getModel();
        modelo.setRowCount(0);
        
        for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            if (!dep.getNombre().equals(nombreDep)) continue;

            Cola<Expediente> cola = dep.getColaExpedientes();
            Cola<Expediente> aux = new Cola<>();

            while (!cola.esVacia()) {
                Expediente exp = cola.desencolar();
                aux.encolar(exp);

                String fecha = obtenerFechaUltimoMovimiento(exp);
                String dependencia = obtenerDependenciaActual(exp);
                modelo.addRow(new Object[]{
                    exp.getId(),
                    exp.getAsunto(),
                    dependencia,
                    exp.getInteresado().getNombre(),
                    exp.getPrioridad(),
                    fecha
                });
            }

            while (!aux.esVacia()) cola.encolar(aux.desencolar());
            break;
        }
    }
    
    
    private static String obtenerFechaUltimoMovimiento(Expediente e) {
    if (e.getSeguimientos().esVacia()) return "Sin fecha";
    FechaHora fh = e.getSeguimientos().iesimo(e.getSeguimientos().longitud()).getFh();
    return fh.toString(); 
    }
    
    private static String obtenerDependenciaActual(Expediente e) {
    if (e.getSeguimientos().esVacia()) return "Sin dependencia";
    String dependencia = e.getSeguimientos().iesimo(e.getSeguimientos().longitud()).getDestino().getNombre();
    return dependencia; 
    }
    
    private void tablaCompleta(){
        DefaultTableModel modelo = (DefaultTableModel) tramites.getModel();
        modelo.setRowCount(0);
        
        Lista<Expediente> ordenados = admin.obtenerExpedientesOrdenados(deps);
        for (int i = 1; i <= ordenados.longitud(); i++) {
            Expediente e = ordenados.iesimo(i);
            
            String fecha = obtenerFechaUltimoMovimiento(e);
            String dependencia = obtenerDependenciaActual(e);
            modelo.addRow(new Object[]{
            e.getId(), e.getAsunto(), dependencia, e.getInteresado().getNombre(), e.getPrioridad(), fecha
            });
        }
    }
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

        DefaultTableModel modelo = (DefaultTableModel) tramites.getModel();
        modelo.setRowCount(0);
        int cantidadResultados = 0;
        
        
        for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            Cola<Expediente> cola = dep.getColaExpedientes();
            Cola<Expediente> aux = new Cola<>();

            while (!cola.esVacia()) {
                Expediente exp = cola.desencolar();
                aux.encolar(exp);

                if (exp.getInteresado().getDni().equals(dni)) {
                    String fecha = obtenerFechaUltimoMovimiento(exp);
                    String dependencia = obtenerDependenciaActual(exp);
                    modelo.addRow(new Object[]{
                        exp.getId(),
                        exp.getAsunto(),
                        dependencia,
                        exp.getInteresado().getNombre(),
                        exp.getPrioridad(),
                        fecha
                    });
                    cantidadResultados++;
                }
            }

            while (!aux.esVacia()) cola.encolar(aux.desencolar());
        }
        grupoFiltro.clearSelection();
        if (cantidadResultados == 0){
            alertaDni.setText("No se encontraron trámites con este DNI.");
            alertaDni.setForeground(new Color(239,111,35));
            dniIcon.setForeground(new Color(239,111,35));
        }else{
            alertaDni.setForeground(Color.WHITE);
            dniIcon.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tramites.getSelectedRow();
        if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un expediente para finalizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
        }
        
        String idExpediente = tramites.getValueAt(filaSeleccionada, 0).toString();
       

       for (int i = 1; i <= deps.longitud(); i++) {
        Dependencia dep = deps.iesimo(i);
        Cola<Expediente> cola = dep.getColaExpedientes();
        Cola<Expediente> aux = new Cola<>();
        boolean encontrado = false;

        while (!cola.esVacia()) {
            Expediente exp = cola.desencolar();
            if (exp.getId().equals(idExpediente)) {
                encontrado = true;

               
                

                
                FechaHora fechaFinal = new FechaHora();
                Seguimiento seguimiento = new Seguimiento(dep, null, fechaFinal, "Finalización de Trámite");
                exp.getSeguimientos().agregar(seguimiento);

                
                Tramite tramite = admin.buscarTramitePorID(idExpediente);
                if (tramite == null) {
                    tramite = new Tramite(exp, exp.getSeguimientos().iesimo(1).getFh()); 
                    admin.AgregarTramite(tramite);
                }

                Lista<String> documentosFinales = new Lista<>();
                String doc = nombreDoc.getText().trim();
                if (!doc.isEmpty() && !doc.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ,]+$")) {
                    alertaDoc.setForeground(new Color(239,111,35));
                    docIcon.setForeground(new Color(239,111,35));
                    while (!aux.esVacia()) cola.encolar(aux.desencolar());
                    return;
                } else {
                    alertaDoc.setForeground(Color.WHITE);
                    docIcon.setForeground(Color.WHITE);
                }
                String[] docs = doc.split(",");
                for (String d : docs) {
                    String limpio = d.trim();
                    if (!limpio.isEmpty()) {
                        documentosFinales.agregar(limpio);
                    }
                }
                documentosFinales.agregar("Resolución Final");
                // Finalizar el trámite
                tramite.finalizarTramite(fechaFinal, documentosFinales);
                System.out.println("Documentos generados: ");
                documentosFinales.mostrar();
               
                break; 

            } else {
                aux.encolar(exp);
            }
        }

        
        while (!aux.esVacia()) cola.encolar(aux.desencolar());

        if (encontrado) {
            JOptionPane.showMessageDialog(this, "Trámite finalizado correctamente.");
           
            return;
        }
    }

    JOptionPane.showMessageDialog(this, "No se encontró el expediente.", "Error", JOptionPane.ERROR_MESSAGE);


    }//GEN-LAST:event_finalizarActionPerformed

    private void rbMesaPartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMesaPartesActionPerformed
        if(mpseleccionado){
            grupoFiltro.clearSelection();
            mpseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Mesa de Partes");
        mpseleccionado = true;
        sseleccionado = false;
        tseleccionado = false;
        dseleccionado = false;
        bseleccionado = false;
        buseleccionado = false;
        }
    }//GEN-LAST:event_rbMesaPartesActionPerformed

    private void rbSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSecretariaActionPerformed
        if(sseleccionado){
            grupoFiltro.clearSelection();
            sseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Secretaría Académica");
        mpseleccionado = false;
        sseleccionado = true;
        tseleccionado = false;
        dseleccionado = false;
        bseleccionado = false;
        buseleccionado = false;
        }
    }//GEN-LAST:event_rbSecretariaActionPerformed

    private void rbDecanatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDecanatoActionPerformed
        if(dseleccionado){
            grupoFiltro.clearSelection();
            dseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Decanato");
        mpseleccionado = false;
        sseleccionado = false;
        tseleccionado = false;
        dseleccionado = true;
        bseleccionado = false;
        buseleccionado = false;
        }
    }//GEN-LAST:event_rbDecanatoActionPerformed

    private void rbTesoreriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTesoreriaActionPerformed
        if(tseleccionado){
            grupoFiltro.clearSelection();
            tseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Tesorería");
        mpseleccionado = false;
        sseleccionado = false;
        tseleccionado = true;
        dseleccionado = false;
        bseleccionado = false;
        buseleccionado = false;
        }
    }//GEN-LAST:event_rbTesoreriaActionPerformed

    private void rbBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBibliotecaActionPerformed
        if(bseleccionado){
            grupoFiltro.clearSelection();
            bseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Biblioteca");
        mpseleccionado = false;
        sseleccionado = false;
        tseleccionado = false;
        dseleccionado = false;
        bseleccionado = true;
        buseleccionado = false;
        }
    }//GEN-LAST:event_rbBibliotecaActionPerformed

    private void rbBienestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbBienestarActionPerformed
        if(buseleccionado){
            grupoFiltro.clearSelection();
            buseleccionado = false;
            tablaCompleta();
        }else{
        filtrarPorDependencia("Bienestar Universitario");
        mpseleccionado = false;
        sseleccionado = false;
        tseleccionado = false;
        dseleccionado = false;
        bseleccionado = false;
        buseleccionado = true;
        }
    }//GEN-LAST:event_rbBienestarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Registro menu = new Registro();
        menu.setVisible(true); 
        
        this.dispose(); 
    }//GEN-LAST:event_cancelarActionPerformed

    private void nombreBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreBuscarActionPerformed

    private void nombreDocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDocFocusGained
        nombreDoc.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        doc.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreDocFocusGained

    private void nombreDocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDocFocusLost
        nombreDoc.setBorder(new RoundedBorder(10,Color.BLACK));
        doc.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreDocFocusLost

    private void nombreBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusGained
        nombreBuscar.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        buscar.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreBuscarFocusGained

    private void nombreBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusLost
        nombreBuscar.setBorder(new RoundedBorder(10,Color.BLACK));
        buscar.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreBuscarFocusLost

    private void botonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseEntered
        Color colorHover = botonBuscar.getBackground().darker();
        botonBuscar.setBackground(colorHover);    
    }//GEN-LAST:event_botonBuscarMouseEntered

    private void botonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseExited
        Color colorNormal = new Color(239, 111, 35);
        botonBuscar.setBackground(colorNormal);  
    }//GEN-LAST:event_botonBuscarMouseExited

    private void finalizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalizarMouseEntered
        Color colorHover = finalizar.getBackground().darker();
        finalizar.setBackground(colorHover);    
    }//GEN-LAST:event_finalizarMouseEntered

    private void finalizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalizarMouseExited
        Color colorNormal = new Color(239, 111, 35);
        finalizar.setBackground(colorNormal);  
    }//GEN-LAST:event_finalizarMouseExited

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
        java.awt.EventQueue.invokeLater(() -> new FinalizacionDeTramite().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaDni;
    private javax.swing.JLabel alertaDoc;
    private javax.swing.JPanel bg;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JLabel buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel dniIcon;
    private javax.swing.JLabel doc;
    private javax.swing.JLabel docIcon;
    private javax.swing.JLabel filtro;
    private javax.swing.JLabel finalizacion;
    private javax.swing.JButton finalizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreBuscar;
    private javax.swing.JTextField nombreDoc;
    private javax.swing.JRadioButton rbBiblioteca;
    private javax.swing.JRadioButton rbBienestar;
    private javax.swing.JRadioButton rbDecanato;
    private javax.swing.JRadioButton rbMesaPartes;
    private javax.swing.JRadioButton rbSecretaria;
    private javax.swing.JRadioButton rbTesoreria;
    private javax.swing.JTable tramites;
    // End of variables declaration//GEN-END:variables
}
