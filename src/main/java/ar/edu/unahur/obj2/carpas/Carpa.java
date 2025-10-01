package ar.edu.unahur.obj2.carpas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        return (limitePersonas+1 <= limitePersonas) && !p.estaEbria();
    }

    //req seg P. 8(metodo extra)
    public void ingresarPersona(Persona p){
        clientes.add(p);
    }
    
}
