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
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author andre
 */
public class RegistroMovimiento extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroMovimiento.class.getName());
    CustomComboBox ui = new CustomComboBox();
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
     * Creates new form RegistroMovimiento
     */
    public RegistroMovimiento() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        
        for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            nombreDependencia.addItem(dep.getNombre());
        }
        nombreDependencia.setSelectedIndex(-1);
        
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
            registro.setFont(openSansBold);
            alertaDni.setFont(openSans);
            alertaDependencia.setFont(openSans);
            alertaObservacion.setFont(openSans);

            // Texto Usuario
            buscar.setFont(openSans);
            nombreBuscar.setFont(openSans);
            nombreBuscar.setBorder(new RoundedBorder(8,Color.BLACK));
            buscar.setOpaque(true);
            buscar.setBackground(Color.WHITE); // Mismo fondo que el JTextField
            // Texto Observación
            observacion.setFont(openSans);
            nombreObservacion.setFont(openSans);
            nombreObservacion.setBorder(new RoundedBorder(8,Color.BLACK));
            observacion.setOpaque(true);
            observacion.setBackground(Color.WHITE); // Mismo fondo que el JTextField
            // Texto Dependencia
            dependencia.setFont(openSans);
            dependencia.setOpaque(true);
            dependencia.setBackground(Color.WHITE);
            nombreDependencia.setUI(ui);
            CustomComboBox.estilizarComboBox(nombreDependencia, openSans);  

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

            mover.setFont(openSansBold2);
            mover.setContentAreaFilled(false);
            mover.setFocusPainted(false);
            mover.setBorderPainted(false);
            mover.setOpaque(false);
                    
            mover.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
        registro = new javax.swing.JLabel();
        buscar = new javax.swing.JLabel();
        filtro = new javax.swing.JLabel();
        observacion = new javax.swing.JLabel();
        dependencia = new javax.swing.JLabel();
        dniIcon = new javax.swing.JLabel();
        alertaDni = new javax.swing.JLabel();
        dependenciaIcon = new javax.swing.JLabel();
        alertaDependencia = new javax.swing.JLabel();
        nombreBuscar = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        mover = new javax.swing.JButton();
        nombreObservacion = new javax.swing.JTextField();
        nombreDependencia = new javax.swing.JComboBox<>();
        rbDecanato = new javax.swing.JRadioButton();
        rbMesaPartes = new javax.swing.JRadioButton();
        rbSecretaria = new javax.swing.JRadioButton();
        rbBiblioteca = new javax.swing.JRadioButton();
        rbTesoreria = new javax.swing.JRadioButton();
        rbBienestar = new javax.swing.JRadioButton();
        cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tramites = new javax.swing.JTable();
        alertaObservacion = new javax.swing.JLabel();
        observacionIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 29));

        registro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        registro.setText("Registro de Movimiento");
        bg.add(registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 280, 30));

        buscar.setText("Buscar por DNI");
        bg.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 162, 74, -1));

        filtro.setText("Filtro por Dependencia");
        bg.add(filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 223, -1, -1));

        observacion.setText("Observación");
        bg.add(observacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 92, 62, -1));

        dependencia.setText("Dependencia de Destino");
        bg.add(dependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 162, 120, -1));

        dniIcon.setBackground(new java.awt.Color(255, 255, 255));
        dniIcon.setForeground(new java.awt.Color(255, 255, 255));
        dniIcon.setText("🛈");
        bg.add(dniIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 201, -1, -1));

        alertaDni.setBackground(new java.awt.Color(255, 255, 255));
        alertaDni.setForeground(new java.awt.Color(255, 255, 255));
        alertaDni.setText("Introduzca un DNI válido.");
        bg.add(alertaDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 201, 266, -1));

        dependenciaIcon.setBackground(new java.awt.Color(255, 255, 255));
        dependenciaIcon.setForeground(new java.awt.Color(255, 255, 255));
        dependenciaIcon.setText("🛈");
        bg.add(dependenciaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 201, -1, -1));

        alertaDependencia.setBackground(new java.awt.Color(255, 255, 255));
        alertaDependencia.setForeground(new java.awt.Color(255, 255, 255));
        alertaDependencia.setText("Seleccione dependencia.");
        bg.add(alertaDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 201, 164, -1));

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

        mover.setBackground(new java.awt.Color(239, 111, 35));
        mover.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        mover.setForeground(new java.awt.Color(255, 255, 255));
        mover.setText("Mover");
        mover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                moverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                moverMouseExited(evt);
            }
        });
        mover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moverActionPerformed(evt);
            }
        });
        bg.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(658, 170, 84, 30));

        nombreObservacion.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreObservacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreObservacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreObservacionFocusLost(evt);
            }
        });
        bg.add(nombreObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 170, 30));

        nombreDependencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombreDependencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreDependenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreDependenciaFocusLost(evt);
            }
        });
        bg.add(nombreDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 170, 30));

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
        rbSecretaria.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 0));
        rbSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSecretariaActionPerformed(evt);
            }
        });
        bg.add(rbSecretaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 246, -1, 25));

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

        alertaObservacion.setBackground(new java.awt.Color(255, 255, 255));
        alertaObservacion.setForeground(new java.awt.Color(255, 255, 255));
        alertaObservacion.setText("Introduzca una observación válida.");
        bg.add(alertaObservacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 131, 254, -1));

        observacionIcon.setBackground(new java.awt.Color(255, 255, 255));
        observacionIcon.setForeground(new java.awt.Color(255, 255, 255));
        observacionIcon.setText("🛈");
        bg.add(observacionIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 131, -1, -1));

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

    private void moverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moverActionPerformed
        // TODO add your handling code here:
        int fila = tramites.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un expediente de la tabla.");
            return;
        }

        String idExpediente = (String) tramites.getValueAt(fila, 0);
        String nombreDestino = (String) nombreDependencia.getSelectedItem();
        String observacion = nombreObservacion.getText().trim();
        if (!observacion.isEmpty() && !observacion.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ,]+$")) {
            alertaObservacion.setForeground(new Color(239,111,35));
            observacionIcon.setForeground(new Color(239,111,35));
            return;
        } else {
            alertaObservacion.setForeground(Color.WHITE);
            observacionIcon.setForeground(Color.WHITE);
        }

        
        Expediente expediente = null;
        Dependencia origen = null;
        Dependencia destino = null;

  
        for (int i = 1; i <= deps.longitud(); i++) {
            Dependencia dep = deps.iesimo(i);
            Cola<Expediente> cola = dep.getColaExpedientes();
            Cola<Expediente> aux = new Cola<>();
            while (!cola.esVacia()) {
                Expediente e = cola.desencolar();
                if (e.getId().equals(idExpediente)) {
                    expediente = e;
                    origen = dep;
                }
                aux.encolar(e);
            }
            while (!aux.esVacia()) cola.encolar(aux.desencolar());
        }

        if (expediente == null) {
            JOptionPane.showMessageDialog(this, "Expediente no encontrado.");
            return;
        }

   
        for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            if (dep.getNombre().equals(nombreDestino)) {
                destino = dep;
                break;
            }
        }

        if (destino == null) {
                alertaDependencia.setForeground(new Color(239,111,35));
                dependenciaIcon.setForeground(new Color(239,111,35));
                return;
            }else{
                alertaDependencia.setForeground(Color.WHITE);
                dependenciaIcon.setForeground(Color.WHITE);
            }
    
        Cola<Expediente> cola = origen.getColaExpedientes();
        Cola<Expediente> nuevaCola = new Cola<>();
        while (!cola.esVacia()) {
            Expediente e = cola.desencolar();
            if (!e.getId().equals(idExpediente)) nuevaCola.encolar(e);
        }
        while (!nuevaCola.esVacia()) cola.encolar(nuevaCola.desencolar());

  
        destino.getColaExpedientes().encolar(expediente);

    
        Seguimiento s = new Seguimiento( origen, destino,new FechaHora(), observacion);
        expediente.getSeguimientos().agregar(s);

        JOptionPane.showMessageDialog(this, "Movimiento registrado.");
        
    }//GEN-LAST:event_moverActionPerformed

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

    private void nombreBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusGained
        nombreBuscar.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        buscar.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreBuscarFocusGained

    private void nombreBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreBuscarFocusLost
        nombreBuscar.setBorder(new RoundedBorder(10,Color.BLACK));
        buscar.setForeground(Color.BLACK);  
    }//GEN-LAST:event_nombreBuscarFocusLost

    private void nombreObservacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreObservacionFocusGained
        nombreObservacion.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        observacion.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreObservacionFocusGained

    private void nombreObservacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreObservacionFocusLost
        nombreObservacion.setBorder(new RoundedBorder(10,Color.BLACK));
        observacion.setForeground(Color.BLACK);  
    }//GEN-LAST:event_nombreObservacionFocusLost

    private void nombreDependenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDependenciaFocusGained
        ui.setBorderColor(new Color(239, 111, 35));
        dependencia.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreDependenciaFocusGained

    private void nombreDependenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDependenciaFocusLost
        ui.setBorderColor(Color.BLACK);
        dependencia.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreDependenciaFocusLost

    private void botonBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseEntered
        Color colorHover = botonBuscar.getBackground().darker();
        botonBuscar.setBackground(colorHover);
    }//GEN-LAST:event_botonBuscarMouseEntered

    private void botonBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBuscarMouseExited
        Color colorNormal = new Color(239, 111, 35);
        botonBuscar.setBackground(colorNormal);
    }//GEN-LAST:event_botonBuscarMouseExited

    private void moverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMouseEntered
        Color colorHover = mover.getBackground().darker();
        mover.setBackground(colorHover);
    }//GEN-LAST:event_moverMouseEntered

    private void moverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMouseExited
        Color colorNormal = new Color(239, 111, 35);
        mover.setBackground(colorNormal);
    }//GEN-LAST:event_moverMouseExited

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
        java.awt.EventQueue.invokeLater(() -> new RegistroMovimiento().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaDependencia;
    private javax.swing.JLabel alertaDni;
    private javax.swing.JLabel alertaObservacion;
    private javax.swing.JPanel bg;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JLabel buscar;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel dependencia;
    private javax.swing.JLabel dependenciaIcon;
    private javax.swing.JLabel dniIcon;
    private javax.swing.JLabel filtro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mover;
    private javax.swing.JTextField nombreBuscar;
    private javax.swing.JComboBox<String> nombreDependencia;
    private javax.swing.JTextField nombreObservacion;
    private javax.swing.JLabel observacion;
    private javax.swing.JLabel observacionIcon;
    private javax.swing.JRadioButton rbBiblioteca;
    private javax.swing.JRadioButton rbBienestar;
    private javax.swing.JRadioButton rbDecanato;
    private javax.swing.JRadioButton rbMesaPartes;
    private javax.swing.JRadioButton rbSecretaria;
    private javax.swing.JRadioButton rbTesoreria;
    private javax.swing.JLabel registro;
    private javax.swing.JTable tramites;
    // End of variables declaration//GEN-END:variables
}
