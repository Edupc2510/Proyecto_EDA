
package controlador;

public class FechaHora {
    private int año;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;
    private int segundo;

    public FechaHora(int año, int mes, int dia, int hora, int minuto, int segundo) {
        this.año = año;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }
    
    public boolean esAnteriorA(FechaHora otra) {
    if (this.año != otra.año) return this.año < otra.año;
    if (this.mes != otra.mes) return this.mes < otra.mes;
    if (this.dia != otra.dia) return this.dia < otra.dia;
    if (this.hora != otra.hora) return this.hora < otra.hora;
    if (this.minuto != otra.minuto) return this.minuto < otra.minuto;
    return this.segundo < otra.segundo;
    }

    // metodo para mostrar la fecha y hora 
    @Override
    public String toString() {
      return String.format("%04d-%02d-%02d %02d:%02d:%02d", año, mes, dia, hora, minuto, segundo);
    }
}
