package ar.edu.unahur.obj2.carpas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.personas.Persona;
import ar.edu.unahur.obj2.paises.Pais;

public class Carpa {
    private Integer limitePersonas;
    private Boolean hayBandaDeMusicaTrad;
    private List<Persona> asistentes; 
    private Marca marcaQueVende;

    public Carpa(Integer limitePersonas, Boolean hayBandaDeMusicaTrad, Marca marcaQueVende) {
        this.limitePersonas = limitePersonas;
        this.hayBandaDeMusicaTrad = hayBandaDeMusicaTrad;
        this.asistentes = new ArrayList<Persona>();
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
        return (asistentes.size() + 1 <= limitePersonas) && !p.estaEbria();
    }

    //req seg P. 8(metodo extra)
    public void ingresarPersona(Persona p){
        asistentes.add(p);
    }

    //req seg P. 9
    public void servirJarraDeCapacidad_APersona(Double capacidadJarra, Persona p){
        if(!personaEstaEnLaCarpa(p)){
            throw new IllegalArgumentException("La persona no esta en la carpa.");
        }
        p.consumirJarra(new JarraCerveza(capacidadJarra, marcaQueVende, this));
    }

    //req seg P. 9 func aux
    private Boolean personaEstaEnLaCarpa(Persona p){
        return asistentes.contains(p);
    }

    //req seg P. 10
    public Integer cantEbriosEmpedernidos(){
        return (int) asistentes.stream().filter(p -> p.soloComproJarrasMasDe1Lts()).count();
    }

    //fun aux test 
    public Integer cantTotalAsistentes(){
        return asistentes.size();
    }

    //req terc P. 13
    public Boolean esHomogenea(){
        Pais nacionalidadComun = asistentes.getFirst().getNacionalidad();
        return asistentes.stream().allMatch(c -> c.getNacionalidad() == nacionalidadComun);
    }

    //req terc P. 15
    public List<Persona> asistentesQueNoRecibieronJarra(){
        return asistentes.stream().filter(asist -> !asist.carpasDondeRecibioJarras().contains(this)).collect(Collectors.toList());
    }

}
