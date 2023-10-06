/*
 * Aplicación Swing
 */
package chendongdiqijava02;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Di Qi
 */
public class ChenDongDiQiJAVA02 extends JFrame {

    // Etiquetas
    private JLabel numero_label;
    private JLabel nombre_label;
    private JLabel fecha_alta_label;
    private JLabel sueldo_label;
    private JLabel sueldo_maximo_label;

    // Campos
    private TextField numero_textfield;
    private TextField nombre_textfield;
    private TextField fecha_alta_textfield;
    private TextField sueldo_textfield;
    private TextField sueldo_maximo_textfield;

    // Botones
    private JButton anterior_button;
    private JButton siguiente_button;
    private JButton aceptar_button;
    private JButton cancelar_button;
    private JButton crear_button;

    // Paneles
    private JPanel contenedor;
    private JPanel contenedor_info;
    private JPanel contenedor_botones;
    private JPanel contenedor_nav;
    private JPanel contenedor_crear;
    private JPanel contenedor_opciones;

    public ChenDongDiQiJAVA02() {
        super("Práctica 2");

        // Crear empleados
        new Empleado("Di Qi", 1000, 10000);
        new Empleado("Andrés", 2000, 20000);
        new Empleado("Beatriz", 3000, 30000);
        new Empleado("Carlos", 4000, 40000);
        new Empleado("Diego", 5000, 50000);

        // Crear etiquetas
        numero_label = new JLabel("Número: ");
        nombre_label = new JLabel("Nombre: ");
        fecha_alta_label = new JLabel("Fecha de alta: ");
        sueldo_label = new JLabel("Sueldo: ");
        sueldo_maximo_label = new JLabel("Sueldo máximo: ");

        // Crear campos
        numero_textfield = new TextField(20);
        nombre_textfield = new TextField(20);
        fecha_alta_textfield = new TextField(20);
        sueldo_textfield = new TextField(20);
        sueldo_maximo_textfield = new TextField(20);
        camposEditables(false);

        // Crear botones
        anterior_button = new JButton("Anterior");
        siguiente_button = new JButton("Siguiente");
        aceptar_button = new JButton("Aceptar");
        cancelar_button = new JButton("Cancelar");
        crear_button = new JButton("Crear");
        actualizarBotonesNav();

        // Eventos del botón siguiente
        siguiente_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado.siguiente();
                actualizarBotonesNav();
                mostrarEmpleado();
            }
        });

        // Evento del botón anterior
        anterior_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado.anterior();
                actualizarBotonesNav();
                mostrarEmpleado();
            }
        });

        // Evento boton crear
        crear_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoCrear(true);
            }
        });

        // Evento boton cancelar
        cancelar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modoCrear(false);
                mostrarEmpleado();
            }
        });

        // Evento boton aceptar
        aceptar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombre_textfield.getText();
                String sueldo = sueldo_textfield.getText();
                String sueldo_maximo = sueldo_maximo_textfield.getText();
                boolean datos_correctos = true;

                if (nombre.isEmpty() || sueldo.isEmpty() || sueldo_maximo.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Todos los campos deben rellenarse");
                    datos_correctos = false;
                } else {
                    try {
                        if (Double.parseDouble(sueldo) > Double.parseDouble(sueldo_maximo)) {
                            JOptionPane.showMessageDialog(rootPane, "El sueldo no puede ser mayor que el sueldo máximo");
                            datos_correctos = false;
                        }
                        if (Double.parseDouble(sueldo) < 0 || Double.parseDouble(sueldo_maximo) < 0) {
                            JOptionPane.showMessageDialog(rootPane, "Ni el sueldo ni el sueldo máximo pueden ser negativos");
                            datos_correctos = false;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(rootPane, "El sueldo y el sueldo máximo tienen que ser números");
                        datos_correctos = false;
                    }
                }

                if (datos_correctos) {
                    new Empleado(nombre, Double.parseDouble(sueldo), Integer.parseInt(sueldo_maximo));
                    modoCrear(false);
                    mostrarEmpleado();
                    actualizarBotonesNav();
                }
            }
        });

        // Crear el layout
        establecerLayout();

        // Mostrar primer empleado
        mostrarEmpleado();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final ChenDongDiQiJAVA02 app = new ChenDongDiQiJAVA02();

        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowActivated(WindowEvent e) {
                app.requestFocus();
            }
        });
        app.pack();
        app.setVisible(true);
    }

    /**
     * Muestra el empleado en la ventana
     */
    private void mostrarEmpleado() {
        Empleado e = Empleado.getEmpleadoActual();
        numero_textfield.setText(e.getNum_empleado() + "");
        nombre_textfield.setText(e.getNombre());
        fecha_alta_textfield.setText(e.getFecha_alta_str());
        sueldo_textfield.setText(e.getSueldo() + "");
        sueldo_maximo_textfield.setText(e.getSueldoMaximo() + "");
    }
    
    /**
     * Actualiza los botones de siguiente y anterior
     */
    private void actualizarBotonesNav() {
        Empleado e = Empleado.getEmpleadoActual();
        
        anterior_button.setEnabled(true);
        siguiente_button.setEnabled(true);
        
        if (Empleado.esPrimero()) {
            anterior_button.setEnabled(false);
        }
        if (Empleado.esUltimo()) {
            siguiente_button.setEnabled(false);
        }
    }

    /**
     * Establecer si son editables los campos de texto
     *
     * @param editable true si es editable, false si no
     */
    private void camposEditables(boolean editable) {
        numero_textfield.setEditable(editable);
        nombre_textfield.setEditable(editable);
        sueldo_textfield.setEditable(editable);
        sueldo_maximo_textfield.setEditable(editable);
        fecha_alta_textfield.setEditable(editable);

        if (editable) {
            numero_textfield.setText("");
            nombre_textfield.setText("");
            sueldo_textfield.setText("");
            sueldo_maximo_textfield.setText("");
            fecha_alta_textfield.setText("");
        }
    }

    /**
     * Establece o quita la vista del modo crear
     *
     * @param x true para modo crear, false para modo información
     */
    private void modoCrear(boolean x) {
        camposEditables(x);
        contenedor_opciones.setVisible(x);

        numero_label.setVisible(!x);
        numero_textfield.setVisible(!x);
        fecha_alta_label.setVisible(!x);
        fecha_alta_textfield.setVisible(!x);
        contenedor_nav.setVisible(!x);
        contenedor_crear.setVisible(!x);
    }
    
    /**
     * Establece el layout
     */
    private void establecerLayout() {
        contenedor = new JPanel();
        contenedor.setLayout(new GridLayout(0, 1));
        contenedor_info = new JPanel();
        contenedor_info.setLayout(new GridLayout(0, 2));
        contenedor_botones = new JPanel();
        contenedor_botones.setLayout(new GridLayout(0, 1));
        contenedor_nav = new JPanel();
        contenedor_nav.setLayout(new FlowLayout());
        contenedor_crear = new JPanel();
        contenedor_crear.setLayout(new FlowLayout());
        contenedor_opciones = new JPanel();
        contenedor_opciones.setLayout(new FlowLayout());
        modoCrear(false);

        contenedor_info.add(numero_label);
        contenedor_info.add(numero_textfield);
        contenedor_info.add(fecha_alta_label);
        contenedor_info.add(fecha_alta_textfield);
        contenedor_info.add(nombre_label);
        contenedor_info.add(nombre_textfield);
        contenedor_info.add(sueldo_label);
        contenedor_info.add(sueldo_textfield);
        contenedor_info.add(sueldo_maximo_label);
        contenedor_info.add(sueldo_maximo_textfield);
        contenedor_nav.add(anterior_button);
        contenedor_nav.add(siguiente_button);
        contenedor_crear.add(crear_button);
        contenedor_opciones.add(aceptar_button);
        contenedor_opciones.add(cancelar_button);
        contenedor_botones.add(contenedor_nav);
        contenedor_botones.add(contenedor_crear);
        contenedor_botones.add(contenedor_opciones);
        contenedor.add(contenedor_info);
        contenedor.add(contenedor_botones);

        setContentPane(contenedor);
    }

}
