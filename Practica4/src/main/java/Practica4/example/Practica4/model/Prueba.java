package Practica4.example.Practica4.model;

public class Prueba {
    private String codigo;
    private String nombre;
    private String descripcion;
    private boolean esRiesgoAgudo;

    public Prueba() {
    }

    public Prueba(String codigo, String nombre, String descripcion, boolean esRiesgoAgudo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esRiesgoAgudo = esRiesgoAgudo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEsRiesgoAgudo() {
        return esRiesgoAgudo;
    }

    public void setEsRiesgoAgudo(boolean esRiesgoAgudo) {
        this.esRiesgoAgudo = esRiesgoAgudo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prueba otra = (Prueba) obj;
        return codigo != null && codigo.equals(otra.codigo);
    }
}
