package ar.edu.unahur.obj2.marcas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.paises.Pais;

public class MarcaTest {
    /*
     * Existen varias marcas de cerveza. Están las marcas de cerveza rubia (como la Corona), 
     * las marcas de cerveza negra (como la Guiness), y las marcas de cerveza roja (como la Hofbräu). 
     * De cada marca se sabe su contenido de lúpulo, o sea, cuántos gramos de lúpulo por litro llevan.
     *  También se conoce el país donde se fabrica.
     * 
    */
    Marca hofbrauCervR = new CervezaRoja(50.0, Pais.ALEMANIA);
    Marca corona = new CervezaRubia(50.0, Pais.BELGICA , 0.06);
    Marca GuinessCervN = new CervezaNegra(15.0, Pais.CHECOSLOVAQUIA);

    @BeforeEach
    public void init(){
        Reglamento.getInstance().setGraduacionReglamentaria(40.0);
    }

    //CERV RUBIA
    @Test
    void coronaTieneCantLupulo50() {
        assertEquals(50.0, corona.getCantLupulo());
    }

    @Test
    void coronaTieneGraduacionAlcoholica0Dot06() {
        assertEquals(0.06, corona.graduacionAlcoholica());
    }

    //CERV ROJA
    @Test
    void hofbrauCervRTieneCantLupulo50() {
        assertEquals(50.0, hofbrauCervR.getCantLupulo());
    }

    @Test
    void hofbrauCervRTieneGraduacionAlcoholica50() {
        assertEquals(50.0, hofbrauCervR.graduacionAlcoholica());
    }

    //CERV NEGRA
    @Test
    void GuinessCervNTieneCantLupulo15() {
        assertEquals(15.0, GuinessCervN.getCantLupulo());

    }

    @Test
    void GuinessCervNTieneGraduacionAlcoholica30() {
        assertEquals(30.0, GuinessCervN.graduacionAlcoholica());

    }
}
