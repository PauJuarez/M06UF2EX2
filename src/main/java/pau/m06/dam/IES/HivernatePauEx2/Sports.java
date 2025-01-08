package pau.m06.dam.IES.HivernatePauEx2;

import jakarta.persistence.*;

@Entity(name = "Sports")  
@Table(name = "deportes")
public class Sports implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cod", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Integer cod;

    @Column(name = "nombre", length = 60)
    private String name;

    public Sports() {}

    public Sports(String name) {
        this.name = name;
    }

    public Sports(Integer cod, String name) {
        this.cod = cod;
        this.name = name;
    }

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
