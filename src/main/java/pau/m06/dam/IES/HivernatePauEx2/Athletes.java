package pau.m06.dam.IES.HivernatePauEx2;

import jakarta.persistence.*;

@Entity(name = "Athletes")
@Table(name = "atletas")
public class Athletes implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private Integer cod;

    @Column(name = "nombre", length = 60)
    private String name;

    @ManyToOne
    @JoinColumn(name = "cod_deporte", nullable = false)
    private Sports sport;

    public Athletes() {}

    public Athletes(Integer cod, String name, Sports sport) {
        this.cod = cod;
        this.name = name;
        this.sport = sport;
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

    public Sports getSport() {
        return sport;
    }

    public void setSport(Sports sport) {
        this.sport = sport;
    }
}
