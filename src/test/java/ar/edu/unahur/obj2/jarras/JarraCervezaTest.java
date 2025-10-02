package ar.edu.unahur.obj2.jarras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.carpas.Carpa;
import ar.edu.unahur.obj2.marcas.CervezaRoja;
import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Reglamento;
import ar.edu.unahur.obj2.paises.Pais;
import ar.edu.unahur.obj2.personas.Persona;

public class JarraCervezaTest {
    Marca hofbrauCervRoj = new CervezaRoja(50.0, Pais.ALEMANIA);
    Carpa carpaHof = new Carpa(6, Boolean.FALSE, hofbrauCervRoj);

    JarraCerveza jarra1 = new JarraCerveza(0.5, hofbrauCervRoj, carpaHof);


    @BeforeEach
    public void init(){
        Reglamento.getInstance().setGraduacionReglamentaria(40.0);
    }

    @Test
    void contenidoAlcoholicoDeJarra1Es0dot25(){
        assertEquals(0.25, jarra1.contenidoAlcoholico());
    }
}
