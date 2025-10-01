package ar.edu.unahur.obj2.personas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.CervezaNegra;
import ar.edu.unahur.obj2.marcas.CervezaRoja;
import ar.edu.unahur.obj2.marcas.CervezaRubia;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Reglamento;
import ar.edu.unahur.obj2.paises.Pais;

public class PersonaTest {
    Marca hofbrauCervR = new CervezaRoja(50.0, Pais.ALEMANIA);
    Marca corona = new CervezaRubia(50.0, Pais.BELGICA , 0.09);
    Marca brahma = new CervezaRubia(50.0, Pais.BELGICA , 0.04);

    Marca GuinessCervN = new CervezaNegra(15.0, Pais.CHECOSLOVAQUIA);

    JarraCerveza jarra1 = new JarraCerveza(0.5, hofbrauCervR);
    JarraCerveza jarra2 = new JarraCerveza(1.0, corona);

    Persona juan = new Persona(95.0, Boolean.FALSE, 20, List.of(corona, GuinessCervN), Pais.BELGICA);
    Persona gabriel = new Persona(96.0, Boolean.FALSE, 30, List.of(corona), Pais.CHECOSLOVAQUIA);

    Carpa carpaCorona = new Carpa(6, Boolean.FALSE, corona);

    @BeforeEach
    public void init(){
        Reglamento.getInstance().setGraduacionReglamentaria(40.0);
        juan.consumirJarra(jarra1);
        juan.consumirJarra(jarra2);
    }

    @Test
    void juanCantTotalDeAlcoholIngerida_() {
        //(((40.0 * 1.25) * 0.5 / 100) + (0.09 * 1.0 / 100)))
        //0.2509
        assertEquals(0.2509, juan.cantTotalDeAlcoholIngerida());
    }

    @Test
    void juanEstaEbrio() {
        assertTrue(juan.estaEbria()); //26.35
    }

    @Test
    void juanLeGustaMarcaCervezahofbrauCervR() {
        assertTrue(juan.leGusta_MarcaCerveza(hofbrauCervR));
    }

    @Test
    void juanQuiereEntrarACarpaCorona(){
        assertTrue(juan.quiereEntrarALaCarpa(carpaCorona));
    }

    @Test
    void juanIntentaIngresarACarpaCoronaPeroSeGeneraError(){
        assertThrows(IllegalArgumentException.class, () -> {
            juan.ingresarACarpa(carpaCorona);
        });
    }

    @Test
    void juanNoConsumioTodasJarrasDeMasDe1Lts(){
        assertFalse(juan.soloComproJarrasMasDe1Lts());
    }

    @Test
    void juanNoEsPatriota(){
        assertFalse(juan.esPatriota());
    }


    //gabriel

    @Test
    void gabrielNoLeGustaMarcaBrahma() {
        assertFalse(gabriel.leGusta_MarcaCerveza(brahma));
    }

    @Test
    void gabrielCantTotalDeAlcoholIngerida0() {
        assertEquals(0, gabriel.cantTotalDeAlcoholIngerida());
    }

    @Test
    void gabrielNoEstaEbrio() {
        assertFalse(gabriel.estaEbria()); 
    }

    @Test
    void gabrielQuiereEntrarALaCarpaCorona(){
        assertTrue(gabriel.quiereEntrarALaCarpa(carpaCorona));
    }

    @Test
    void gabrielPuedeEntrarALaCarpaCorona(){
        assertTrue(gabriel.puedeEntrarACarpa(carpaCorona));
    }


}
