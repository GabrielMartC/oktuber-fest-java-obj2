package ar.edu.unahur.obj2.personas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.CervezaNegra;
import ar.edu.unahur.obj2.marcas.CervezaRoja;
import ar.edu.unahur.obj2.marcas.CervezaRubia;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Reglamento;
import ar.edu.unahur.obj2.paises.Pais;

public class PersonaTest {
    Marca hofbrauCervR = new CervezaRoja(50.0, Pais.ALEMANIA);
    Marca corona = new CervezaRubia(50.0, Pais.BELGICA , 0.06);
    Marca GuinessCervN = new CervezaNegra(15.0, Pais.CHECOSLOVAQUIA);

    JarraCerveza jarra1 = new JarraCerveza(0.5, hofbrauCervR);
    JarraCerveza jarra2 = new JarraCerveza(1.0, corona);

    Persona juan = new Persona(95.0, Boolean.FALSE, 20, List.of(corona, GuinessCervN), Pais.BELGICA);
    Persona gabriel = new Persona(96.0, Boolean.FALSE, 30, List.of(corona, GuinessCervN), Pais.CHECOSLOVAQUIA);


    @BeforeEach
    public void init(){
        Reglamento.getInstance().setGraduacionReglamentaria(40.0);
        juan.consumirJarra(jarra1);
        juan.consumirJarra(jarra2);
    }

    @Test
    void juanCantTotalDeAlcoholIngerida0dot31() {
        assertEquals(0.2506, juan.cantTotalDeAlcoholIngerida());
    }

    @Test
    void testEstaEbria() {
        assertTrue(juan.estaEbria()); //26.35
    }

    @Test
    void juanLeGustaMarcaCervezahofbrauCervR() {
        assertTrue(juan.leGusta_MarcaCerveza(hofbrauCervR));
    }

    @Test
    void gabrielNoLeGustaMarcaCorona() {
        assertFalse(gabriel.leGusta_MarcaCerveza(corona));
    }
}
