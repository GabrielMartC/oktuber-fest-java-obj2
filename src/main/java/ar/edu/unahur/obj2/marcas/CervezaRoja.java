package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.paises.Pais;

public class CervezaRoja extends CervezaNegra{

    public CervezaRoja(Double cantLupulo, Pais paisOrigen) {
        super(cantLupulo, paisOrigen);
    }

    @Override
    public Double graduacionAlcoholica() {
        return super.graduacionAlcoholica() * 1.25;
    }

}
