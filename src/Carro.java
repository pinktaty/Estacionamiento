public class Carro implements Vehiculo{
  //Atributos
  private TipoVehiculo tipo;
  private String marca;
  private String modelo;
  private int año;
  private String color;
  private String placa;
  private int horaEntrada;

  //Constructor
  public Carro(String marca, String modelo, int año, String color, String placa, int horaEntrada){
    this.tipo = TipoVehiculo.Coche;
    this.marca = marca;
    this.modelo = modelo;
    this.año = año;
    this.color = color;
    this.placa = placa;
    this.horaEntrada = horaEntrada;
  }

  //Getters
  @Override
  public TipoVehiculo getTipoVehiculo(){
    return this.tipo;
  }
  @Override
  public String getMarca(){
    return this.marca;
  }
  @Override
  public String getModelo(){
    return this.modelo;
  }
  @Override
  public int getAño(){
    return this.año;
  }
  @Override
  public String getColor(){
    return this.color;
  }
  @Override
  public String getPlaca(){
    return this.placa;
  }
  @Override
  public int getHoraEntrada(){
    return this.horaEntrada;
  }
}
