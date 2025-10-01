package ar.edu.unahur.obj2.carpas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.personas.Persona;


public class Carpa {
    private Integer limitePersonas;
    private Boolean hayBandaDeMusicaTrad;
    private Set<Persona> clientes; 
    private Marca marcaQueVende;

    public Carpa(Integer limitePersonas, Boolean hayBandaDeMusicaTrad, Marca marcaQueVende) {
        this.limitePersonas = limitePersonas;
        this.hayBandaDeMusicaTrad = hayBandaDeMusicaTrad;
        this.clientes = new HashSet<Persona>();
        this.marcaQueVende = marcaQueVende;
    }

    public Boolean getHayBandaDeMusicaTrad() {
        return hayBandaDeMusicaTrad;
    }

    public Marca getMarcaQueVende() {
        return marcaQueVende;
    }

    public Integer getLimitePersonas() {
        return limitePersonas;
    }

    //req seg P. 6
    public Boolean dejarIngresarAPersona(Persona p){
        return (clientes.size() + 1 <= limitePersonas) && !p.estaEbria();
    }

    //req seg P. 8(metodo extra)
    public void ingresarPersona(Persona p){
        clientes.add(p);
    }

    //req seg P. 9
    public void servirJarraDeCapacidad_APersona(Double capacidadJarra, Persona p){
        if(!personaEstaEnLaCarpa(p)){
            throw new IllegalArgumentException("La persona no esta en la carpa.");
        }
        p.consumirJarra(new JarraCerveza(capacidadJarra, marcaQueVende));
    }

    //req seg P. 9 func aux
    private Boolean personaEstaEnLaCarpa(Persona p){
        return clientes.contains(p);
    }

    //req seg P. 10
    public Integer cantEbriosEmpedernidos(){
        return (int) clientes.stream().filter(p -> p.soloComproJarrasMasDe1Lts()).count();
    }

    //fun aux test 
    public Integer cantTotalClientes(){
        return clientes.size();
    }

}
