package Models;
import javax.persistence.*;

@Entity
@Table(name = "city") 
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "population")
    private Long population;

    @Column(name = "gdp_per_capita")
    private Double gdpPerCapita;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getGdpPerCapita() {
        return gdpPerCapita;
    }

    public void setGdpPerCapita(Double gdpPerCapita) {
        this.gdpPerCapita = gdpPerCapita;
    }
}