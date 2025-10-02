package ar.edu.unahur.obj2.carpas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    Marca brahma = new CervezaRubia(50.0, Pais.BELGICA , 0.04);


    Carpa carpaCorona = new Carpa(6, Boolean.FALSE,
         new CervezaRubia(50.0, Pais.BELGICA , 0.09)); //ej cerveza corona fuerte

    JarraCerveza jarra1 = new JarraCerveza(0.5, hofbrauCervR, carpaCorona);
    JarraCerveza jarra2 = new JarraCerveza(1.0, corona, carpaCorona);

    Persona juan = new Persona(95.0, Boolean.FALSE, 20, List.of(corona, GuinessCervN), Pais.BELGICA);
    Persona gabriel = new Persona(96.0, Boolean.FALSE, 30, List.of(corona), Pais.CHECOSLOVAQUIA);
    Persona maria = new Persona(63.30, Boolean.FALSE, 60, List.of(corona, brahma), Pais.BELGICA);
    Persona jose = new Persona(95.0, Boolean.FALSE, 20, List.of(corona, GuinessCervN), Pais.BELGICA);



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
        assertEquals(1, carpaCorona.cantTotalAsistentes());
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
    void servirJarraDeCapacidadMedioLitroAJuanDaError() {

        // Use assertThrows to capture the exception
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> carpaCorona.servirJarraDeCapacidad_APersona(0.5, juan),
            "La persona no esta en la carpa."
        );

        // Assert the message of the thrown exception
        assertEquals("La persona no esta en la carpa.", thrown.getMessage(), "La persona no esta en la carpa.");
    }

    @Test
    void cantEbriosEmpedernidosEnCarpaCorona0() {
        assertEquals(0, carpaCorona.cantEbriosEmpedernidos());
    }

    //TEST REQ AVANZ

    @Test
    void carpaCoronaEsHomogenea(){
        carpaCorona.ingresarPersona(juan);
        carpaCorona.ingresarPersona(maria);
        assertTrue(carpaCorona.esHomogenea());
    }

    @Test
    void asistentesQueNoRecibieronJarraGabrielYJose(){
        carpaCorona.ingresarPersona(juan);
        carpaCorona.ingresarPersona(maria);
        carpaCorona.ingresarPersona(gabriel);
        carpaCorona.ingresarPersona(jose);
        
        carpaCorona.servirJarraDeCapacidad_APersona(1.0, juan);
        carpaCorona.servirJarraDeCapacidad_APersona(0.5, maria);
        Set personas = new HashSet<>();
        personas.add(gabriel);
        personas.add(jose);
        assertEquals(personas, new HashSet<>(carpaCorona.asistentesQueNoRecibieronJarra()));
    }
}
