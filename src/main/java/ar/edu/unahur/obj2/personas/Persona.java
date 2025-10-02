package ar.edu.unahur.obj2.personas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Pais getNacionalidad() {
        return nacionalidad;
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
            condicionPrincipal = condicionPrincipal && (unaCarpa.cantTotalAsistentes() % 2 == 0);
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

    //req terc P. 12
    public Boolean esCompatibleConPersona_(Persona p){
        /*ej:
         * gabriel [brahma, corona]
         * juan [brahma, wendeler, heineken]
         * maria [brahma, corona, wendeler]
         * 
         * gabriel & maria:
         *      coinciden 2 cerv / no coinciden  1
         */
        Set<Marca> marcasDeJarrasConsumidasPropias = this.jarrasCompradas.stream().map(JarraCerveza::getMarcaCerveza).collect(Collectors.toSet());
        Set<Marca> marcasDeJarrasConsumidasOtraPersona = p.jarrasCompradas.stream().map(JarraCerveza::getMarcaCerveza).collect(Collectors.toSet());
        Set<Marca> marcasTotales = new HashSet<>();
        marcasTotales.addAll(marcasDeJarrasConsumidasPropias);
        marcasTotales.addAll(marcasDeJarrasConsumidasOtraPersona);
        Integer coincidencias = 0;

        for(Marca marca : marcasDeJarrasConsumidasPropias){
            if(marcasDeJarrasConsumidasOtraPersona.contains(marca)){
                coincidencias += 1;
            }
        }

        return coincidencias > marcasTotales.size() / 2;
    }

    //req terc P. 14
    public List<Carpa> carpasDondeRecibioJarras(){
        return jarrasCompradas.stream().map(JarraCerveza::getCarpaDondeSeSirvio).collect(Collectors.toList());
    }

    //req terc P. 16
    public Boolean entrandoEnElVicio(){ //debe haber consumido 2 jarras o mas
        if(jarrasCompradas.size() < 2){
            throw new IllegalArgumentException("La persona no bebio ninguna jarra o bebio solo 1");
        }

        List<Double> capacidadesJarrasConsumidas = jarrasCompradas.stream().map(JarraCerveza::getCapacidad).collect(Collectors.toList());

        Boolean condicionVaCumpliendose = Boolean.TRUE;

        for(int i = 0; (i < capacidadesJarrasConsumidas.size() - 1) && condicionVaCumpliendose; i++){
            if(capacidadesJarrasConsumidas.get(i) > capacidadesJarrasConsumidas.get(i+1)){
                condicionVaCumpliendose = Boolean.FALSE;
            }
            else if(capacidadesJarrasConsumidas.get(i) <= capacidadesJarrasConsumidas.get(i+1)){
                condicionVaCumpliendose = Boolean.TRUE;
            }
        }
        
        return condicionVaCumpliendose;
    }

}
