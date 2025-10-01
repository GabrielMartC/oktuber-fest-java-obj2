package ar.edu.unahur.obj2.personas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.paises.Pais;

public class Persona {
    private Double peso;
    private List<JarraCerveza> jarrasCompradas = new ArrayList<JarraCerveza>();
    private Boolean leGustaMusicTrad;
    private Integer nivelAguante;
    private List<Marca> marcasFavoritas;
    private Pais nacionalidad;

    public Persona(Double peso, Boolean leGustaMusicTrad, Integer nivelAguante, List<Marca>marcasFavoritas, Pais nacionalidad) {
        this.peso = peso;
        // this.jarrasCompradas = jarrasCompradas;
        this.leGustaMusicTrad = leGustaMusicTrad;
        this.nivelAguante = nivelAguante;
        this.marcasFavoritas = marcasFavoritas;
        this.nacionalidad = nacionalidad;
    }


    public Boolean estaEbria(){
        return cantTotalDeAlcoholIngerida() * this.peso > this.nivelAguante;
    }

    public Double cantTotalDeAlcoholIngerida(){
        return this.jarrasCompradas.stream().mapToDouble(JarraCerveza::contenidoAlcoholico).sum();
    }

    public Boolean leGusta_MarcaCerveza(Marca marcaCerveza){
        Boolean resultado = switch (this.nacionalidad) {
            case Pais.BELGICA -> marcaCerveza.getCantLupulo() > 4.0;
            case Pais.CHECOSLOVAQUIA -> marcaCerveza.graduacionAlcoholica() > 0.08;
            case Pais.ALEMANIA -> Boolean.TRUE;
        };

        return resultado;
    }


    public void consumirJarra(JarraCerveza jarraCerv) {
        jarrasCompradas.add(jarraCerv);
    }

    public List<JarraCerveza> getJarrasConsumidas(){
        List<JarraCerveza> jarrasCompradasCopia = new ArrayList<JarraCerveza>(jarrasCompradas);
        return jarrasCompradasCopia;
    }




    //req seg P. 5
    public Boolean quiereEntrarALaCarpa(Carpa unaCarpa){

        Boolean condicionPrincipal = this.leGusta_MarcaCerveza(unaCarpa.getMarcaQueVende()) && 
            ((leGustaMusicTrad && unaCarpa.getHayBandaDeMusicaTrad()) || 
            (!leGustaMusicTrad && !unaCarpa.getHayBandaDeMusicaTrad()));

        if(nacionalidad.equals(Pais.ALEMANIA)){
            condicionPrincipal = condicionPrincipal && (unaCarpa.getLimitePersonas() % 2 == 0);
        }
        return condicionPrincipal;
    }

    //req seg P. 7
    public Boolean puedeEntrarACarpa(Carpa c){
        return quiereEntrarALaCarpa(c) && c.dejarIngresarAPersona(this);
    }

    //req seg P. 8
    public void ingresarACarpa(Carpa unaCarpa){
        if(!puedeEntrarACarpa(unaCarpa)){
            throw new IllegalArgumentException("La persona no pudo ingresar a la carpa.");
        }
        unaCarpa.ingresarPersona(this);
    }

    //req seg P. 10 func aux
    public Boolean soloComproJarrasMasDe1Lts(){
        return jarrasCompradas.stream().allMatch(j -> j.getCapacidad() > 1.0);
    }

    //req seg P. 11
    public Boolean esPatriota(){
        return jarrasCompradas.stream().allMatch(jarra -> jarra.getMarcaCerveza().getPaisOrigen().equals(this.nacionalidad));
    }
}
