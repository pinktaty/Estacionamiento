import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;

public class SimulaciónConFile{

  //Método para generar número random
  public static int generaNumero(int límiteSuperior){
    int numeroRandom = (int) (Math.random()*límiteSuperior);
    return numeroRandom;
  }

  //PosiblesMarcas
  private static String[] posiblesMarcas = new String[]{"BMW", "Suzuki", "Honda", "Peugeot"};
  //Posibles Modelos
  private static String[] posiblesModelosCoches = new String[]{"Fiesta", "Mustang", "X4", "Ibiza", "Génesis", "Swift", "Supra"};
  private static String[] posiblesModelosMotos = new String[]{"Indian Scout", "Varez 125", "Mitt 125 Urban", "Kawazaki Z125", "Macbor Rockester", "Ray 7.7", "Super Soco CPx", "Aprilia RX"};
  //Posibles años
  private static int[] posiblesAños = new int[]{2000, 2001, 2004, 2007, 2011, 2014, 2015, 2018, 2019, 2020, 2023};
  //Posibles colores
  private static String[] posiblesColores = new String[]{"Negro", "Blanco", "Azul", "Rojo", "Morado", "Verde", "Rosa", "Amarillo"};
  //Letras para generar placas
  private static String[] posiblesLetras = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z"};

  //Generador de placas
  public static String generaPlaca(){
    String placaGenerada = posiblesLetras[generaNumero(25)] + posiblesLetras[generaNumero(25)] + posiblesLetras[generaNumero(25)] + String.valueOf(generaNumero(8)) + String.valueOf(generaNumero(8)) + String.valueOf(generaNumero(8));
    return placaGenerada;
  }
  //Generador de número de ticket
  private static int numTicket = 0;
  //Vehiculos que se manejarán
  private static int vehiculosPorDía = generaNumero(30) + 5;
  //Cuántos serán coches
  private static int numCoches = vehiculosPorDía - generaNumero(vehiculosPorDía);
  //Cuántos serán motos
  private static int numMotos = vehiculosPorDía - numCoches;
  //Generador de carros
  private static Carro[] carros = new Carro[numCoches];
  public static void generadorCarros(){
    for(int i = 0; i < numCoches; i++){
      carros[i] = new Carro(posiblesMarcas[generaNumero(3)], posiblesModelosCoches[generaNumero(7)], posiblesAños[generaNumero(11)], posiblesColores[generaNumero(7)], generaPlaca(), generaNumero(24));
    }
  }
  //Generador de motos
  private static Motocicleta[] motocicletas = new Motocicleta[numMotos];
  public static void generadorMotos(){
    for(int i = 0; i < numMotos; i++){
      motocicletas[i] = new Motocicleta(posiblesMarcas[generaNumero(3)], posiblesModelosMotos[generaNumero(7)], posiblesAños[generaNumero(11)], posiblesColores[generaNumero(7)], generaPlaca(), generaNumero(24));
    }
  }

  //¿Será pensionado?
  public static boolean seraPensionado(){
    boolean será = false;
    if(generaNumero(20) == 3 ){
      será = true;
    }
    return será;
  }
  //¿Perdió su boleto?
  public static boolean perdioBoleto(){
    boolean loHizo = false;
    if(generaNumero(20) == 2){
      loHizo = true;
    }
    return loHizo;
  }
  //Variable reloj
  private static int horaActual = 0;
  //Lista que contiene a todos los autos autos estacionados
  static List<Vehiculo> vehiculosEstacionados = new ArrayList<Vehiculo>();
  //Lista que contiene a los autos pensionados con pensión activa
  static List<Vehiculo> vehiculosPensionadosConTicket = new ArrayList<Vehiculo>();

  //Función que genera lista con todos los elementos que entran a una hora especifica
  static List<Vehiculo> vehiculosPorEntrar = new ArrayList<Vehiculo>();
  public static void vehiculosAEntrar(int hora){
    vehiculosPorEntrar.clear();
    for(int i = 0; i < carros.length; i++){
      if(carros[i].getHoraEntrada() == horaActual){
        vehiculosPorEntrar.add(carros[i]);
      }
    }
    for(int i = 0; i < motocicletas.length; i++){
      if(motocicletas[i].getHoraEntrada() == horaActual){
        vehiculosPorEntrar.add(motocicletas[i]);
      }
    }
  }

  public static void main(String[] args){
    generadorCarros();
    generadorMotos();
    //Crear estacinamiento
    Estacionamiento p = new Estacionamiento();
    try{
      //Crear documento
      File f = new File("ResultadosDeUnDíaEstacionamiento.txt");
      FileWriter output = new FileWriter(f);
      output.write("Simulación de un día de las 0 horas a las 23 horas.");
      output.write("\n");
      while(horaActual < 24){
        output.write("\n");
        output.write("Hora: " + horaActual);
        output.write("\n");
        //Generar la lista de esa hora
        vehiculosAEntrar(horaActual);
        //Agregar la posibilidad de que un vehiculo con una oensión activa entre
        if(vehiculosPensionadosConTicket.isEmpty() == false && vehiculosEstacionados.containsAll(vehiculosPensionadosConTicket) == false && generaNumero(10) < 5){
          //Seleccionar un vehiculo pensionado al azar
          vehiculosPorEntrar.add(vehiculosPensionadosConTicket.get(generaNumero(vehiculosPensionadosConTicket.size())));
        }
        //Checar que la lista de esa hora no esté vacía
        if(vehiculosPorEntrar.isEmpty() == false){
          for(int i = 0; i < vehiculosPorEntrar.size(); i++){
            Vehiculo vehiculoQuiereEntrar = vehiculosPorEntrar.get(i);
            if(p.estacionarVehiculo(vehiculoQuiereEntrar)){
              output.write("Un vehículo tipo " + vehiculoQuiereEntrar.getTipoVehiculo().toString() + " ha entrado.");
              output.write("\n");
              output.write("Vehículos estacionados: " + p.autosEstacionados());
              output.write("\n");
              output.write("Lugares disponibles: " + p.lugaresDisponibles());
              output.write("\n");
              //Agregar los vehiculos a la lista general de autos estacionados
              vehiculosEstacionados.add(vehiculoQuiereEntrar);
            } else {
              output.write("Vehículo intentó ingresar pero el estacionamiento se encuentra lleno.");
              output.write("\n");
            }
          }
        }
        if(vehiculosEstacionados.isEmpty() == false && generaNumero(10) < 5){
          Vehiculo porSalir = vehiculosEstacionados.get(generaNumero(vehiculosEstacionados.size()));
          //Checar si es pensionado con pensión activa
          if(vehiculosPensionadosConTicket.contains(porSalir)){
            output.write("Un vehículo con pensión activa se ha retirado.");
          } else {
            Ticket t = new Ticket(porSalir, numTicket, horaActual, seraPensionado(), perdioBoleto());
            numTicket++;
            output.write("Un vehículo se ha retirado. Se ha generado ticket.");
            output.write("\n");
            output.write(t.toString());
            //¿Es pensionado y acaba de renovar o pagar?
            if(t.esPensionado()){
              vehiculosPensionadosConTicket.add(porSalir);
            }
          }
          p.vehiculoSeRetira(porSalir);
          vehiculosEstacionados.remove(porSalir);
          output.write("\n");
          output.write("Vehículos estacionados: " + p.autosEstacionados());
          output.write("\n");
          output.write("Lugares disponibles: " + p.lugaresDisponibles());
          output.write("\n");
        }
        horaActual++;
      }
      output.close();
      System.out.println("El archivo se ha generado correctamente.");
    } catch (Exception e){
      System.out.println(e);
    }
  }
}
