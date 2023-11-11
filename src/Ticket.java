import java.io.*;

public class Ticket{
  //Atributos de vehiculo que irán igualmente registrados en el ticket
  private TipoVehiculo tipo;
  private String marca;
  private String modelo;
  private int año;
  private String color;
  private String placa;
  private int horaEntrada;
  //Atributos
  private int numeroTicket;
  private int horaSalida;
  private int cobro;
  private int cambio;
  private boolean pensionado;
  private boolean boletoPerdido;
  private int pago;

  //Constructor
  public Ticket(Vehiculo vh, int numeroTicket, int horaSalida, boolean pensionado, boolean boletoPerdido){
      numeroTicket = numeroTicket;
      horaEntrada = horaEntrada;
      horaSalida = horaSalida;
      pensionado = pensionado;
      boletoPerdido = boletoPerdido;
      cobro = cobrar(this.horaEntrada, horaSalida);
      pago = pago(cobro);
      if(cobro == pago){
        this.cambio = 0;
      } else {
        this.cambio = pago - cobro;
      }
      tipo = vh.getTipoVehiculo();
      marca = vh.getMarca();
      modelo = vh.getModelo();
      año = vh.getAño();
      color = vh.getColor();
      placa = vh.getPlaca();
  }

  public boolean esPensionado(){
    return pensionado;
  }

  //Método para calcular el cobro
  public int cobrar(int horaEntrada, int horaSalida){
    int monto = 0;
    if(boletoPerdido == true){
      monto = 350;
    } else if (pensionado == true){
      monto = 750;
    } else {
      int horas = horaSalida - horaEntrada;
      if(horas < 2){
        monto = 10;
      } else if(horas >= 2){
        int cuartetos = (int) (horas-2)*4;
        monto = 10 + (cuartetos*15);
      }
    }
    return monto;
  }
  //Generar un pago
  public int pago(int cobro){
    int num = (int) (Math.random()*10);
    int pago = num*cobro;
    if(pago < cobro){
      pago = cobro*2;
    }
    return pago;
  }
  //toString();
  public String toString(){
    String ticketGenerado = "Número de ticket {" + String.valueOf(this.numeroTicket) + "}, Tipo de Vehículo {" + this.tipo + "} Marca {" + this.marca + "} Modelo {" + this.modelo + "} Año {" + this.año + "} Color {" + this.color + "} Placas {" + this.placa + "} Hora de entrada {" + String.valueOf(this.horaEntrada) + "} Hora de salida {" + String.valueOf(this.horaSalida) + "} Monto total cobrado {$" + String.valueOf(this.cobro) + "} Monto con el cuál se pagó el servicio {$" + String.valueOf(this.pago) + "} Cambio devuelto {$" + String.valueOf(this.cambio) + "}.";
    return ticketGenerado;
  }
}
