package ar.edu.unahur.obj2.marcas.model.cerveza;

import ar.edu.unahur.obj2.marcas.Marca;
import ar.edu.unahur.obj2.marcas.Reglamento;

public class Negra extends Marca{

    public Negra(Double contenidoLupulo, String pais) {
        super(contenidoLupulo, pais);
    }

    @Override
    public Double graduaciionDeAlcohol() {
        return Math.min(Reglamento.getInstance().getGraduacionReglamentaria(), super.contenidoLupulo);
    }

}
