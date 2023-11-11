import java.util.LinkedList;
import java.util.List;

public class Estacionamiento{
  private int lugaresDisponibles = 20;
  private int filas = 4;
  private int espaciosPorFila = 5;
  private int autosEstacionados;
  private List<Lugar> lugaresOcupados;

  public Estacionamiento(){
    lugaresOcupados = new LinkedList<>();
  }
  public int lugaresDisponibles(){
    return lugaresDisponibles;
  }
  public Lugar encontrarLugar(){
    if(lugaresOcupados.isEmpty()){
      return new Lugar(0,0);
    } else {
      Lugar últimoLugarOcupado = lugaresOcupados.get(lugaresOcupados.size()-1);
      if(últimoLugarOcupado.getEspacio() < espaciosPorFila){
          return new Lugar(últimoLugarOcupado.getFila(), últimoLugarOcupado.getEspacio()+1);
      } else {
        return new Lugar(últimoLugarOcupado.getFila()+1, 0);
      }
    }
  }
  public boolean estacionarVehiculo(Vehiculo vh){
    boolean sePudo;
    if(lugaresDisponibles < 1){
      sePudo = false;
    } else {
      Lugar lugarLibre = encontrarLugar();
      lugarLibre.estacionar(vh);
      lugaresOcupados.add(lugarLibre);
      sePudo = true;
      lugaresDisponibles--;
      autosEstacionados++;
    }
    return sePudo;
  }
  public void vehiculoSeRetira(Vehiculo vh){
    for(int i = 0; i < lugaresOcupados.size(); i++){
      Lugar lugarVehicular = lugaresOcupados.get(i);
      Vehiculo vehiculoEnLugar = lugarVehicular.getVehiculoEstacionado();
      if(vehiculoEnLugar == vh){
        lugarVehicular.eliminarAuto();
        lugaresOcupados.remove(lugarVehicular);
        lugaresDisponibles++;
        autosEstacionados--;
      }
    }
  }
  public int autosEstacionados(){
    return autosEstacionados;
  }
}
