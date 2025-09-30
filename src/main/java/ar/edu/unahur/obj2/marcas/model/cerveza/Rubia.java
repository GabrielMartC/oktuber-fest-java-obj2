package ar.edu.unahur.obj2.marcas.model.cerveza;

import ar.edu.unahur.obj2.marcas.Marca;

public class Rubia extends Marca{
    private Double graduacionDeAlcohol;

    public Rubia(Double contenidoLupulo, String pais) {
        super(contenidoLupulo, pais);
    }

    @Override
    public Double graduaciionDeAlcohol() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'graduaciionDeAlcohol'");
    }

}
