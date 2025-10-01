package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.paises.Pais;

public class CervezaNegra extends Marca{

    public CervezaNegra(Double cantLupulo, Pais paisOrigen) {
        super(cantLupulo, paisOrigen);
    }

    @Override
    public Double graduacionAlcoholica() {
        return Double.min(Reglamento.getInstance().getGraduacionReglamentaria(), this.cantLupulo * 2);
    }

}
