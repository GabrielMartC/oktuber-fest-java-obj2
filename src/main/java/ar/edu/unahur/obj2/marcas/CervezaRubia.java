package ar.edu.unahur.obj2.marcas;

import ar.edu.unahur.obj2.paises.Pais;

public class CervezaRubia extends Marca{
    private Double porcentGradAlcoholic;

    public CervezaRubia(Double cantLupulo, Pais paisOrigen, Double porcentGradAlcoholic) {
        super(cantLupulo, paisOrigen);
        this.porcentGradAlcoholic = porcentGradAlcoholic;
    }

    @Override
    public Double graduacionAlcoholica() {
        return this.porcentGradAlcoholic;
    }

}
