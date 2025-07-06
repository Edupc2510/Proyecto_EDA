/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import controlador.Administrador;
import controlador.Dependencia;
import controlador.Expediente;
import controlador.FechaHora;
import controlador.Interesado;
import controlador.Seguimiento;
import javax.swing.JOptionPane;

import GUI.Resources.*;
import static GUI.Resources.CustomComboBox.estilizarComboBox;
import TDA.Lista;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
/**
 *
 * @author andre
 */
public class RegistroExpediente extends javax.swing.JFrame {
    private static int id = 0;
    private boolean camposAutocompletados = false;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroExpediente.class.getName());
    CustomComboBox ui1 = new CustomComboBox();
    CustomComboBox ui2 = new CustomComboBox();
    CustomComboBox ui3 = new CustomComboBox();
    /**
     * Creates new form RegistroExpediente
     */
    public RegistroExpediente() {
        initComponents();
        this.setLocationRelativeTo(null);
        Lista<Dependencia> deps = Dependencia.getInstancias();
        
        for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            nombreDependencia.addItem(dep.getNombre());
        }
        nombreDependencia.setSelectedIndex(-1);
        
    // Diseño
    
    try{
            InputStream is = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 24f);
            InputStream is2 = getClass().getResourceAsStream("/fonts/OpenSans-Bold.ttf");
            Font openSansBold2 = Font.createFont(Font.TRUETYPE_FONT, is2).deriveFont(Font.PLAIN, 10f);
            InputStream is3 = getClass().getResourceAsStream("/fonts/OpenSans-VariableFont_wdth,wght.ttf");
            Font openSans = Font.createFont(Font.TRUETYPE_FONT, is3).deriveFont(Font.PLAIN, 10f);
            setLayout(null);
            bg.setLayout(null);
            registro.setFont(openSansBold);
            
            
            // Texto Dni
            dni.setFont(openSans);
            nombreDni.setFont(openSans);
            nombreDni.setBorder(new RoundedBorder(8,Color.BLACK));
            dni.setOpaque(true);
            dni.setBackground(Color.WHITE);
            alertaDni.setFont(openSans);
            // Texto Asunto
            asunto.setFont(openSans);
            nombreAsunto.setFont(openSans);
            nombreAsunto.setBorder(new RoundedBorder(8,Color.BLACK));
            asunto.setOpaque(true);
            asunto.setBackground(Color.WHITE);
            alertaAsunto.setFont(openSans);
            // Texto Nombre
            nombre.setFont(openSans);
            nombreNombre.setFont(openSans);
            nombreNombre.setBorder(new RoundedBorder(8,Color.BLACK));
            nombre.setOpaque(true);
            nombre.setBackground(Color.WHITE);
            alertaNombre.setFont(openSans);
            // Texto Telefono
            telefono.setFont(openSans);
            nombreTelefono.setFont(openSans);
            nombreTelefono.setBorder(new RoundedBorder(8,Color.BLACK));
            telefono.setOpaque(true);
            telefono.setBackground(Color.WHITE);
            alertaTelefono.setFont(openSans);
            // Texto Email
            email.setFont(openSans);
            nombreEmail.setFont(openSans);
            nombreEmail.setBorder(new RoundedBorder(8,Color.BLACK));
            email.setOpaque(true);
            email.setBackground(Color.WHITE);
            alertaEmail.setFont(openSans);
            // Texto Docref
            docref.setFont(openSans);
            nombreDocref.setFont(openSans);
            nombreDocref.setBorder(new RoundedBorder(8,Color.BLACK));
            docref.setOpaque(true);
            docref.setBackground(Color.WHITE);
            alertaDocref.setFont(openSans);
            // Texto Prioridad
            prioridad.setFont(openSans); // Usa tu fuente cargada
            prioridad.setOpaque(true);
            prioridad.setBackground(Color.WHITE);
            nombrePrioridad.setUI(ui3);
            CustomComboBox.estilizarComboBox(nombrePrioridad, openSans);  
            alertaPrioridad.setFont(openSans);
            // Texto Tipo
            tipo.setFont(openSans);
            tipo.setOpaque(true);
            tipo.setBackground(Color.WHITE);
            nombreTipo.setUI(ui1);
            CustomComboBox.estilizarComboBox(nombreTipo, openSans);
            alertaTipo.setFont(openSans);

            // Texto Dependencia
            dependencia.setFont(openSans);
            dependencia.setOpaque(true);
            dependencia.setBackground(Color.WHITE);
            nombreDependencia.setUI(ui2);
            CustomComboBox.estilizarComboBox(nombreDependencia, openSans); 
            alertaDependencia.setFont(openSans);
            // Boton Ingresar
            crear.setFont(openSansBold2);
            crear.setContentAreaFilled(false);
            crear.setFocusPainted(false);
            crear.setBorderPainted(false);
            crear.setOpaque(false);
                    
            crear.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
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
            cancelar.setContentAreaFilled(false); // Sin fondo
            cancelar.setBorderPainted(false);     // Sin borde
            cancelar.setFocusPainted(false);      // Sin borde al enfocar
            cancelar.setOpaque(false);            // No opaco
            cancelar.setForeground(Color.BLACK);  // Texto negro
            cancelar.setFont(openSansBold2);           // Usa tu fuente si quieres mantener coherencia
            cancelar.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Para que se vea como link al pasar

            

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
        dni = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        registro = new javax.swing.JLabel();
        asunto = new javax.swing.JLabel();
        docref = new javax.swing.JLabel();
        dependencia = new javax.swing.JLabel();
        prioridad = new javax.swing.JLabel();
        alertaDni = new javax.swing.JLabel();
        dniIcon = new javax.swing.JLabel();
        nombreIcon = new javax.swing.JLabel();
        alertaNombre = new javax.swing.JLabel();
        telefonoIcon = new javax.swing.JLabel();
        alertaTelefono = new javax.swing.JLabel();
        emailIcon = new javax.swing.JLabel();
        alertaEmail = new javax.swing.JLabel();
        alertaAsunto = new javax.swing.JLabel();
        asuntoIcon = new javax.swing.JLabel();
        alertaDocref = new javax.swing.JLabel();
        docrefIcon = new javax.swing.JLabel();
        tipoIcon = new javax.swing.JLabel();
        alertaTipo = new javax.swing.JLabel();
        dependenciaIcon = new javax.swing.JLabel();
        alertaDependencia = new javax.swing.JLabel();
        prioridadIcon = new javax.swing.JLabel();
        alertaPrioridad = new javax.swing.JLabel();
        nombreDni = new javax.swing.JTextField();
        nombreNombre = new javax.swing.JTextField();
        nombreTelefono = new javax.swing.JTextField();
        nombreEmail = new javax.swing.JTextField();
        nombreAsunto = new javax.swing.JTextField();
        nombreDocref = new javax.swing.JTextField();
        cancelar = new javax.swing.JButton();
        crear = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        nombreTipo = new javax.swing.JComboBox<>();
        nombrePrioridad = new javax.swing.JComboBox<>();
        nombreDependencia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, 29));

        dni.setText("DNI");
        bg.add(dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 172, 20, -1));

        nombre.setText("Nombre");
        bg.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 232, 42, -1));

        telefono.setText("Teléfono");
        bg.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 292, 44, -1));

        email.setText("Email");
        bg.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 352, 28, -1));

        tipo.setText("Tipo de Interesado");
        bg.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 412, 92, -1));

        registro.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        registro.setText("Registro de Expediente");
        bg.add(registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 280, 30));

        asunto.setText("Asunto");
        bg.add(asunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 172, 36, -1));

        docref.setText("Documento de Referencia");
        bg.add(docref, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 232, 125, -1));

        dependencia.setText("Dependencia Inicial");
        bg.add(dependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 352, 98, -1));

        prioridad.setText("Prioridad");
        bg.add(prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 292, 46, -1));

        alertaDni.setBackground(new java.awt.Color(255, 255, 255));
        alertaDni.setForeground(new java.awt.Color(255, 255, 255));
        alertaDni.setText("Introduzca un DNI válido.");
        bg.add(alertaDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 211, 164, -1));

        dniIcon.setBackground(new java.awt.Color(255, 255, 255));
        dniIcon.setForeground(new java.awt.Color(255, 255, 255));
        dniIcon.setText("🛈");
        bg.add(dniIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 211, -1, -1));

        nombreIcon.setBackground(new java.awt.Color(255, 255, 255));
        nombreIcon.setForeground(new java.awt.Color(255, 255, 255));
        nombreIcon.setText("🛈");
        bg.add(nombreIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 271, -1, -1));

        alertaNombre.setBackground(new java.awt.Color(255, 255, 255));
        alertaNombre.setForeground(new java.awt.Color(255, 255, 255));
        alertaNombre.setText("Introduzca un nombre válido.");
        bg.add(alertaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 271, 164, -1));

        telefonoIcon.setBackground(new java.awt.Color(255, 255, 255));
        telefonoIcon.setForeground(new java.awt.Color(255, 255, 255));
        telefonoIcon.setText("🛈");
        bg.add(telefonoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 331, -1, -1));

        alertaTelefono.setBackground(new java.awt.Color(255, 255, 255));
        alertaTelefono.setForeground(new java.awt.Color(255, 255, 255));
        alertaTelefono.setText("Introduzca un teléfono válido.");
        bg.add(alertaTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 331, 178, -1));

        emailIcon.setBackground(new java.awt.Color(255, 255, 255));
        emailIcon.setForeground(new java.awt.Color(255, 255, 255));
        emailIcon.setText("🛈");
        bg.add(emailIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 391, -1, -1));

        alertaEmail.setBackground(new java.awt.Color(255, 255, 255));
        alertaEmail.setForeground(new java.awt.Color(255, 255, 255));
        alertaEmail.setText("Introduzca un email válido.");
        bg.add(alertaEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 391, 174, -1));

        alertaAsunto.setBackground(new java.awt.Color(255, 255, 255));
        alertaAsunto.setForeground(new java.awt.Color(255, 255, 255));
        alertaAsunto.setText("Introduza un asunto válido.");
        bg.add(alertaAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 211, 174, -1));

        asuntoIcon.setBackground(new java.awt.Color(255, 255, 255));
        asuntoIcon.setForeground(new java.awt.Color(255, 255, 255));
        asuntoIcon.setText("🛈");
        bg.add(asuntoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 211, -1, -1));

        alertaDocref.setBackground(new java.awt.Color(255, 255, 255));
        alertaDocref.setForeground(new java.awt.Color(255, 255, 255));
        alertaDocref.setText("Introduzca un documento válido.");
        bg.add(alertaDocref, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 271, 184, -1));

        docrefIcon.setBackground(new java.awt.Color(255, 255, 255));
        docrefIcon.setForeground(new java.awt.Color(255, 255, 255));
        docrefIcon.setText("🛈");
        bg.add(docrefIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 271, -1, -1));

        tipoIcon.setBackground(new java.awt.Color(255, 255, 255));
        tipoIcon.setForeground(new java.awt.Color(255, 255, 255));
        tipoIcon.setText("🛈");
        bg.add(tipoIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 451, -1, -1));

        alertaTipo.setBackground(new java.awt.Color(255, 255, 255));
        alertaTipo.setForeground(new java.awt.Color(255, 255, 255));
        alertaTipo.setText("Seleccione tipo.");
        bg.add(alertaTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 451, 184, -1));

        dependenciaIcon.setBackground(new java.awt.Color(255, 255, 255));
        dependenciaIcon.setForeground(new java.awt.Color(255, 255, 255));
        dependenciaIcon.setText("🛈");
        bg.add(dependenciaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 391, -1, -1));

        alertaDependencia.setBackground(new java.awt.Color(255, 255, 255));
        alertaDependencia.setForeground(new java.awt.Color(255, 255, 255));
        alertaDependencia.setText("Seleccione dependencia inicial.");
        bg.add(alertaDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 391, 190, -1));

        prioridadIcon.setBackground(new java.awt.Color(255, 255, 255));
        prioridadIcon.setForeground(new java.awt.Color(255, 255, 255));
        prioridadIcon.setText("🛈");
        bg.add(prioridadIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 331, -1, -1));

        alertaPrioridad.setBackground(new java.awt.Color(255, 255, 255));
        alertaPrioridad.setForeground(new java.awt.Color(255, 255, 255));
        alertaPrioridad.setText("Seleccione prioridad.");
        bg.add(alertaPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 331, 180, -1));

        nombreDni.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreDniFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreDniFocusLost(evt);
            }
        });
        nombreDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreDniActionPerformed(evt);
            }
        });
        nombreDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreDniKeyReleased(evt);
            }
        });
        bg.add(nombreDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 170, 30));

        nombreNombre.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreNombreFocusLost(evt);
            }
        });
        bg.add(nombreNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 170, 30));

        nombreTelefono.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreTelefonoFocusLost(evt);
            }
        });
        bg.add(nombreTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 170, 30));

        nombreEmail.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreEmailFocusLost(evt);
            }
        });
        bg.add(nombreEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 170, 30));

        nombreAsunto.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreAsunto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreAsuntoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreAsuntoFocusLost(evt);
            }
        });
        bg.add(nombreAsunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 170, 30));

        nombreDocref.setSelectionColor(new java.awt.Color(239, 123, 54));
        nombreDocref.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreDocrefFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreDocrefFocusLost(evt);
            }
        });
        bg.add(nombreDocref, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 170, 30));

        cancelar.setBackground(new java.awt.Color(239, 111, 35));
        cancelar.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 255, 255));
        cancelar.setText("Cancelar");
        cancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        bg.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 428, 52, -1));

        crear.setBackground(new java.awt.Color(239, 111, 35));
        crear.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        crear.setForeground(new java.awt.Color(255, 255, 255));
        crear.setText("Crear Expediente");
        crear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                crearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                crearMouseExited(evt);
            }
        });
        crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearActionPerformed(evt);
            }
        });
        bg.add(crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 420, 96, 30));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo.png"))); // NOI18N
        fondo.setText("jLabel2");
        bg.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 310, 500));

        nombreTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ulima", "Externo" }));
        nombreTipo.setSelectedIndex(-1);
        nombreTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombreTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreTipoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreTipoFocusLost(evt);
            }
        });
        bg.add(nombreTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 170, 30));

        nombrePrioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        nombrePrioridad.setSelectedIndex(-1);
        nombrePrioridad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombrePrioridad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombrePrioridadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombrePrioridadFocusLost(evt);
            }
        });
        bg.add(nombrePrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 170, 30));

        nombreDependencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nombreDependencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nombreDependenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreDependenciaFocusLost(evt);
            }
        });
        nombreDependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreDependenciaActionPerformed(evt);
            }
        });
        bg.add(nombreDependencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 360, 170, 30));

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

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        Registro menu = new Registro();
        menu.setVisible(true); 
        
        this.dispose(); 
    }//GEN-LAST:event_cancelarActionPerformed

    private void crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearActionPerformed
        // TODO add your handling code here:
        
        String asunto = nombreAsunto.getText().trim();
        String documento = nombreDocref.getText().trim();
        String email = nombreEmail.getText().trim();
        String dni = nombreDni.getText().trim();
        String telefono = nombreTelefono.getText().trim();
        String nombre = nombreNombre.getText().trim();
        
        if (asunto.isEmpty() || documento.isEmpty() || email.isEmpty() || dni.isEmpty() || telefono.isEmpty() || nombre.isEmpty() || nombreDependencia.getSelectedIndex() == -1 || nombreTipo.getSelectedIndex() == -1 || nombrePrioridad.getSelectedIndex() == -1) {
            if (!dni.matches("\\d{8}")|| dni.isEmpty()) {
            alertaDni.setForeground(new Color(239,111,35));
            dniIcon.setForeground(new Color(239,111,35));
            }else{
                alertaDni.setForeground(Color.WHITE);
                dniIcon.setForeground(Color.WHITE);
            }

            if (!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$") || nombre.isEmpty()) {
                alertaNombre.setForeground(new Color(239,111,35));
                nombreIcon.setForeground(new Color(239,111,35));
            }else{
                alertaNombre.setForeground(Color.WHITE);
                nombreIcon.setForeground(Color.WHITE);
            }

            if (!telefono.matches("\\d{7,9}")|| telefono.isEmpty()) {
                alertaTelefono.setForeground(new Color(239,111,35));
                telefonoIcon.setForeground(new Color(239,111,35));
            }else{
                alertaTelefono.setForeground(Color.WHITE);
                telefonoIcon.setForeground(Color.WHITE);
            }

            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || email.isEmpty()) {
                alertaEmail.setForeground(new Color(239,111,35));
                emailIcon.setForeground(new Color(239,111,35));
            }else{
                alertaEmail.setForeground(Color.WHITE);
                emailIcon.setForeground(Color.WHITE);
            }

            if (nombreTipo.getSelectedIndex() == -1){
                alertaTipo.setForeground(new Color(239,111,35));
                tipoIcon.setForeground(new Color(239,111,35));
            }else{
                alertaTipo.setForeground(Color.WHITE);
                tipoIcon.setForeground(Color.WHITE);

            }

            if (!asunto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$") || asunto.isEmpty()) {
                alertaAsunto.setForeground(new Color(239,111,35));
                asuntoIcon.setForeground(new Color(239,111,35));
            }else{
                alertaAsunto.setForeground(Color.WHITE);
                asuntoIcon.setForeground(Color.WHITE);
            }

            if (!documento.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$") || documento.isEmpty()) {
                alertaDocref.setForeground(new Color(239,111,35));
                docrefIcon.setForeground(new Color(239,111,35));
            }else{
                alertaDocref.setForeground(Color.WHITE);
                docrefIcon.setForeground(Color.WHITE);
            }

            if (nombrePrioridad.getSelectedIndex() == -1){
                alertaPrioridad.setForeground(new Color(239,111,35));
                prioridadIcon.setForeground(new Color(239,111,35));
            }else{
                alertaPrioridad.setForeground(Color.WHITE);
                prioridadIcon.setForeground(Color.WHITE);

            }

            if (nombreDependencia.getSelectedIndex() == -1){
                alertaDependencia.setForeground(new Color(239,111,35));
                dependenciaIcon.setForeground(new Color(239,111,35));
            }else{
                alertaDependencia.setForeground(Color.WHITE);
                dependenciaIcon.setForeground(Color.WHITE);

            }
            return;
        }
         
        String dependenciaNombre = (String) nombreDependencia.getSelectedItem();
        String prioridadTexto = (String) nombrePrioridad.getSelectedItem();
        int prioridad = Integer.parseInt(prioridadTexto);
        String Tipo = (String) nombreTipo.getSelectedItem();

       
        Lista<Dependencia> deps = Dependencia.getInstancias();
        
        
         for (int i = 1; i <= deps.longitud(); i++)  {
            Dependencia dep = deps.iesimo(i);
            if (dep.getNombre().equals(dependenciaNombre)) 
            {
                
            String prefijo = dependenciaNombre.substring(0, 3).toUpperCase();
            String ID = prefijo + String.format("%03d", id++);
            Administrador admin = Administrador.getInstancia();
            

            FechaHora ahora = new FechaHora();
            Interesado interesado = admin.buscarInteresado(dni);
            if (interesado == null) {
                interesado = new Interesado(dni, nombre, telefono, email, Tipo);
            }

            admin.registrarTramite(ID, prioridad, interesado, asunto, documento, ahora, dep);

            System.out.println("admin: " + admin);
            System.out.println("interesado: " + interesado.getDni());

           
            JOptionPane.showMessageDialog(this, "Expediente registrado correctamente.","Éxito",JOptionPane.INFORMATION_MESSAGE);
            Registro menu = new Registro();
            menu.setVisible(true); 

            this.dispose(); 
            return;
            
            }
        
         }    
        
    }//GEN-LAST:event_crearActionPerformed

    private void nombreDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreDniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreDniActionPerformed

    private void nombreDependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreDependenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreDependenciaActionPerformed

    private void nombreDniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDniFocusGained
        nombreDni.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        dni.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreDniFocusGained

    private void nombreDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDniFocusLost
        nombreDni.setBorder(new RoundedBorder(10,Color.BLACK));
        dni.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_nombreDniFocusLost

    private void nombreNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreNombreFocusGained
        nombreNombre.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        nombre.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreNombreFocusGained

    private void nombreNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreNombreFocusLost
        nombreNombre.setBorder(new RoundedBorder(10,Color.BLACK));
        nombre.setForeground(Color.BLACK); 
    }//GEN-LAST:event_nombreNombreFocusLost

    private void nombreTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTelefonoFocusGained
        nombreTelefono.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        telefono.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreTelefonoFocusGained

    private void nombreTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTelefonoFocusLost
        nombreTelefono.setBorder(new RoundedBorder(10,Color.BLACK));
        telefono.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreTelefonoFocusLost

    private void nombreEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreEmailFocusGained
        nombreEmail.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        email.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreEmailFocusGained

    private void nombreEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreEmailFocusLost
        nombreEmail.setBorder(new RoundedBorder(10,Color.BLACK));
        email.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreEmailFocusLost

    private void nombreTipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTipoFocusGained
        ui1.setBorderColor(new Color(239, 111, 35));
        tipo.setForeground(new Color(239, 111, 35)); 
    }//GEN-LAST:event_nombreTipoFocusGained

    private void nombreTipoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreTipoFocusLost
        ui1.setBorderColor(Color.BLACK);
        tipo.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreTipoFocusLost

    private void nombreAsuntoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreAsuntoFocusGained
        nombreAsunto.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        asunto.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreAsuntoFocusGained

    private void nombreAsuntoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreAsuntoFocusLost
        nombreAsunto.setBorder(new RoundedBorder(10,Color.BLACK));
        asunto.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombreAsuntoFocusLost

    private void nombreDocrefFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDocrefFocusGained
        nombreDocref.setBorder(new RoundedBorder(10,new Color(239,111,35)));
        docref.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreDocrefFocusGained

    private void nombreDocrefFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDocrefFocusLost
        nombreDocref.setBorder(new RoundedBorder(10,Color.BLACK));
        docref.setForeground(Color.BLACK);  
    }//GEN-LAST:event_nombreDocrefFocusLost

    private void nombrePrioridadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombrePrioridadFocusGained
        ui3.setBorderColor(new Color(239, 111, 35));
        prioridad.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombrePrioridadFocusGained

    private void nombrePrioridadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombrePrioridadFocusLost
        ui3.setBorderColor(Color.BLACK);
        prioridad.setForeground(Color.BLACK);
    }//GEN-LAST:event_nombrePrioridadFocusLost

    private void nombreDependenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDependenciaFocusGained
        ui2.setBorderColor(new Color(239, 111, 35));
        dependencia.setForeground(new Color(239,111,35));
    }//GEN-LAST:event_nombreDependenciaFocusGained

    private void nombreDependenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreDependenciaFocusLost
        ui2.setBorderColor(Color.BLACK);
        dependencia.setForeground(Color.BLACK); 
    }//GEN-LAST:event_nombreDependenciaFocusLost

    private void crearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearMouseEntered
        Color colorHover = crear.getBackground().darker();
        crear.setBackground(colorHover);     
    }//GEN-LAST:event_crearMouseEntered

    private void crearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearMouseExited
        Color colorNormal = new Color(239, 111, 35);
        crear.setBackground(colorNormal);  
    }//GEN-LAST:event_crearMouseExited

    private void nombreDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreDniKeyReleased
        Administrador admin = Administrador.getInstancia();
        String dniTexto = nombreDni.getText().trim();
        if (!dniTexto.isEmpty()) {
        Interesado interesado = admin.buscarInteresado(dniTexto);
        if (interesado != null) {
            nombreNombre.setText(interesado.getNombre());
            nombreTelefono.setText(interesado.getTelefono());
            nombreEmail.setText(interesado.getEmail());
            nombreTipo.setSelectedItem(interesado.getTipo());
            nombreNombre.setEditable(false);
            nombreTipo.setEnabled(false);
            nombreNombre.setForeground(new Color(239,111,35));
            ui1.setFontColor(new Color(239,111,35));
            nombreTipo.repaint();
            camposAutocompletados = true;
        }else if (camposAutocompletados){
            nombreNombre.setEditable(true);
            nombreTipo.setEnabled(true);
            nombreNombre.setText("");
            nombreTelefono.setText("");
            nombreEmail.setText("");
            nombreTipo.setSelectedIndex(-1);
            nombreNombre.setForeground(Color.BLACK);
            ui1.setFontColor(Color.BLACK);
            nombreTipo.repaint();
            camposAutocompletados = false;
        }
        }
        
    }//GEN-LAST:event_nombreDniKeyReleased

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
        java.awt.EventQueue.invokeLater(() -> new RegistroExpediente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alertaAsunto;
    private javax.swing.JLabel alertaDependencia;
    private javax.swing.JLabel alertaDni;
    private javax.swing.JLabel alertaDocref;
    private javax.swing.JLabel alertaEmail;
    private javax.swing.JLabel alertaNombre;
    private javax.swing.JLabel alertaPrioridad;
    private javax.swing.JLabel alertaTelefono;
    private javax.swing.JLabel alertaTipo;
    private javax.swing.JLabel asunto;
    private javax.swing.JLabel asuntoIcon;
    private javax.swing.JPanel bg;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton crear;
    private javax.swing.JLabel dependencia;
    private javax.swing.JLabel dependenciaIcon;
    private javax.swing.JLabel dni;
    private javax.swing.JLabel dniIcon;
    private javax.swing.JLabel docref;
    private javax.swing.JLabel docrefIcon;
    private javax.swing.JLabel email;
    private javax.swing.JLabel emailIcon;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nombreAsunto;
    private javax.swing.JComboBox<String> nombreDependencia;
    private javax.swing.JTextField nombreDni;
    private javax.swing.JTextField nombreDocref;
    private javax.swing.JTextField nombreEmail;
    private javax.swing.JLabel nombreIcon;
    private javax.swing.JTextField nombreNombre;
    private javax.swing.JComboBox<String> nombrePrioridad;
    private javax.swing.JTextField nombreTelefono;
    private javax.swing.JComboBox<String> nombreTipo;
    private javax.swing.JLabel prioridad;
    private javax.swing.JLabel prioridadIcon;
    private javax.swing.JLabel registro;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel telefonoIcon;
    private javax.swing.JLabel tipo;
    private javax.swing.JLabel tipoIcon;
    // End of variables declaration//GEN-END:variables
}
