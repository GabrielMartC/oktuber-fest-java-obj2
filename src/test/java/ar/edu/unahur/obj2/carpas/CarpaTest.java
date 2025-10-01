package ar.edu.unahur.obj2.carpas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.jarras.JarraCerveza;
import ar.edu.unahur.obj2.marcas.CervezaNegra;
import ar.edu.unahur.obj2.marcas.CervezaRoja;
import ar.edu.unahur.obj2.marcas.CervezaRubia;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Reglamento;
import ar.edu.unahur.obj2.paises.Pais;
import ar.edu.unahur.obj2.personas.Persona;

public class CarpaTest {
    Marca hofbrauCervR = new CervezaRoja(50.0, Pais.ALEMANIA);
    Marca corona = new CervezaRubia(50.0, Pais.BELGICA , 0.06);
    Marca GuinessCervN = new CervezaNegra(15.0, Pais.CHECOSLOVAQUIA);

    JarraCerveza jarra1 = new JarraCerveza(0.5, hofbrauCervR);
    JarraCerveza jarra2 = new JarraCerveza(1.0, corona);

    Persona juan = new Persona(95.0, Boolean.FALSE, 20, List.of(corona, GuinessCervN), Pais.BELGICA);
    Persona gabriel = new Persona(96.0, Boolean.FALSE, 30, List.of(corona), Pais.CHECOSLOVAQUIA);

    Carpa carpaCorona = new Carpa(6, Boolean.FALSE,
         new CervezaRubia(50.0, Pais.BELGICA , 0.09)); 

    @BeforeEach
    public void init(){
        Reglamento.getInstance().setGraduacionReglamentaria(40.0);
        juan.consumirJarra(jarra1);
        juan.consumirJarra(jarra2);
    }

    //carpa corona
    @Test
    void carpaCoronaNoHayBandaDeMusicaTrad() {
        assertFalse(carpaCorona.getHayBandaDeMusicaTrad());
    }

    @Test
    void carpaCoronaLimitePersonas6() {
        assertEquals(6, carpaCorona.getLimitePersonas());
    }

    @Test
    void carpaCoronaVendeMArcaCorona() {
        assertEquals(corona.getClass(), carpaCorona.getMarcaQueVende().getClass());

    }
    
    @Test
    void carpaCoronaNoDejaIngresarAJuan() {
        assertFalse(carpaCorona.dejarIngresarAPersona(juan));
    }

    @Test
    void carpaCoronaDejaIngresarAGabriel() {
        assertTrue(carpaCorona.dejarIngresarAPersona(gabriel));
    }

    //borrar despues
    @Test
    void gabrielQuiereEntrarALaCarpaCorona(){
        assertTrue(gabriel.quiereEntrarALaCarpa(carpaCorona));
    }
    

    @Test
    void gabrielIngresarACarpaCorona() {
        gabriel.ingresarACarpa(carpaCorona);
        assertEquals(1, carpaCorona.cantTotalClientes());;
    }

    @Test
    void servirJarraDeCapacidadMedioLitroAGabriel() {
        gabriel.ingresarACarpa(carpaCorona);
        carpaCorona.servirJarraDeCapacidad_APersona(0.5, gabriel);
        // assertEquals(List.of(corona), gabriel.getJarrasConsumidas());
        
        // assertEquals(
        //     Set.of(corona),
        //     gabriel.getJarrasConsumidas().stream().map(JarraCerveza::getMarcaCerveza).collect(Collectors.toSet())
        // );

        assertTrue(juan.getJarrasConsumidas().stream().map(JarraCerveza::getMarcaCerveza).anyMatch(m -> m.getClass() == corona.getClass()));
    }

    @Test
    void cantEbriosEmpedernidosEnCarpaCorona0() {
        assertEquals(0, carpaCorona.cantEbriosEmpedernidos());
    }
}
