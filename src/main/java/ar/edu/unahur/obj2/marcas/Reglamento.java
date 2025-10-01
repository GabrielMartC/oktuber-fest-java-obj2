package ar.edu.unahur.obj2.marcas;

//singleton
public class Reglamento {
    private static Reglamento instance = new Reglamento();
    private Double graduacionReglamentaria;

    private Reglamento(){}

    public static Reglamento getInstance(){
        return instance;
    }

    public Double getGraduacionReglamentaria() {
        return graduacionReglamentaria;
    }

    public void setGraduacionReglamentaria(Double graduacionReglamentaria) {
        this.graduacionReglamentaria = graduacionReglamentaria;
    }

    

}
