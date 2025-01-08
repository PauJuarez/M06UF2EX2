package pau.m06.dam.IES.HivernatePauEx2;

import jakarta.persistence.*;

@Entity(name = "Sports")  // Cambié el nombre de la entidad a 'Sports'
@Table(name = "deportes")
public class Sports implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asegura que el ID se genere automáticamente
    private Integer cod;

    @Column(name = "nombre", length = 60)
    private String name;

    // Constructor por defecto
    public Sports() {}

    // Constructor con solo nombre
    public Sports(String name) {
        this.name = name;
    }

    // Constructor con cod y nombre
    public Sports(Integer cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    // Getters y Setters
    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
