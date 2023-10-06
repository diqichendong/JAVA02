/*
 * Clase Empleado
 */
package chendongdiqijava02;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Di Qi
 */
public class Empleado {

    private static int numero_empleado = 1;
    private static Empleado primer_empleado = null;
    private static Empleado ultimo_empleado = null;
    private static Empleado empleado_actual = null;

    private String nombre;
    private int num_empleado;
    private double sueldo;
    private int sueldoMaximo;
    private GregorianCalendar fecha_alta;
    private Empleado siguiente_empleado = null;

    public Empleado(String nombre, int sueldoMaximo) {
        this.nombre = nombre;
        this.sueldoMaximo = sueldoMaximo;
        this.sueldo = 0;
        this.fecha_alta = new GregorianCalendar();
        this.num_empleado = numero_empleado;
        if (numero_empleado == 1) {
            primer_empleado = this;
            empleado_actual = this;
        } else {
            Empleado.ultimo_empleado.siguiente_empleado = this;
        }
        numero_empleado++;
        ultimo_empleado = this;
    }

    public Empleado(String nombre, double sueldo, int sueldoMaximo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.sueldoMaximo = sueldoMaximo;
        this.fecha_alta = new GregorianCalendar();
        this.num_empleado = numero_empleado;
        if (numero_empleado == 1) {
            primer_empleado = this;
            empleado_actual = this;
        } else {
            Empleado.ultimo_empleado.siguiente_empleado = this;
        }
        numero_empleado++;
        ultimo_empleado = this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public int getSueldoMaximo() {
        return sueldoMaximo;
    }

    public void setSueldoMaximo(int sueldoMaximo) {
        this.sueldoMaximo = sueldoMaximo;
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    public GregorianCalendar getFecha_alta() {
        return fecha_alta;
    }

    public String getFecha_alta_str() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateTimeFormat.setCalendar(this.fecha_alta);
        return dateTimeFormat.format(this.fecha_alta.getTime());
    }

    public Empleado getSiguiente_empleado() {
        return this.siguiente_empleado;
    }

    public static Empleado getPrimer_empleado() {
        return primer_empleado;
    }
    
    public static Empleado getEmpleadoActual() {
        return empleado_actual;
    }
    
    public static boolean esPrimero() {
        return empleado_actual.equals(primer_empleado);
    }
    
    public static boolean esUltimo() {
        return empleado_actual.siguiente_empleado == null;
    }
    
    public static void siguiente() {
        if (!esUltimo()) {
            empleado_actual = empleado_actual.siguiente_empleado;
        }
    }
    
    public static void anterior() {
        if (!esPrimero()) {
            Empleado aux = primer_empleado;
            while(!aux.siguiente_empleado.equals(empleado_actual)) {
                aux = aux.siguiente_empleado;
            }
            empleado_actual = aux;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (this.num_empleado != other.num_empleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return num_empleado + "*" + nombre + "*" + sueldo + "*" + sueldoMaximo;
    }

}
