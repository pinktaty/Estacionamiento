public class Lugar{
  private Vehiculo vehiculoEstacionado;
  private int fila;
  private int espacio;

  public Lugar(int fila, int espacio){
    fila = fila;
    espacio = espacio;
  }

  //Getters
  public Vehiculo getVehiculoEstacionado(){
    return vehiculoEstacionado;
  }
  public int getFila(){
    return fila;
  }
  public int getEspacio(){
    return espacio;
  }

  //¿Está disponible el lugar?
  public boolean estáDisponible(){
    return vehiculoEstacionado == null;
  }
  //Método para estacionar un auto
  public void estacionar(Vehiculo vh){
    vehiculoEstacionado = vh;
  }
  //Método para eliminar auto
  public void eliminarAuto(){
    vehiculoEstacionado = null;
  }
}
