package ar.edu.unahur.obj2.personas;

import java.util.ArrayList;
import java.util.List;
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
        // if(leGusta_MarcaCerveza(jarraCerv.getMarca()))
        jarrasCompradas.add(jarraCerv);
    }
    
}
