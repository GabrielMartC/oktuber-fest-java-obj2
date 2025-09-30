package ar.edu.unahur.obj2.marcas;

public abstract class Marca {
    private Double contenidoLupulo;
    private String pais;

    public Marca(Double contenidoLupulo, String pais) {
        this.contenidoLupulo = contenidoLupulo;
        this.pais = pais;
    }

    public abstract Double graduaciionDeAlcohol();

    public Double getContenidoLupulo() {
        return contenidoLupulo;
    }

    public String getPais() {
        return pais;
    }

    
}
