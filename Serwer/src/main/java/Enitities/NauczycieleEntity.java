package Enitities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "nauczyciele", schema = "public", catalog = "ikyxpswp")
public class NauczycieleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idn", nullable = false)
    private long idn;
    @Basic
    @Column(name = "idus", nullable = false)
    private long idus;
    @OneToMany(mappedBy = "nauczycieleByIdn")
    private Collection<FrekwencjaEntity> frekwencjasByIdn;
    @OneToMany(mappedBy = "nauczycieleByWychowawca")
    private Collection<KlasyEntity> klasiesByIdn;
    @ManyToOne
    @JoinColumn(name = "idus", referencedColumnName = "idus", nullable = false, insertable = false, updatable = false)
    private UzytkownicyEntity uzytkownicyByIdus;
    @OneToMany(mappedBy = "nauczycieleByIdn")
    private Collection<NauczycieleprzedmiotowEntity> nauczycieleprzedmiotowsByIdn;
    @OneToMany(mappedBy = "nauczycieleByIdn")
    private Collection<OcenyEntity> oceniesByIdn;

    public long getIdn() {
        return idn;
    }

    public void setIdn(long idn) {
        this.idn = idn;
    }

    public long getIdus() {
        return idus;
    }

    public void setIdus(long idus) {
        this.idus = idus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NauczycieleEntity that = (NauczycieleEntity) o;
        return idn == that.idn && idus == that.idus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idn, idus);
    }

    public Collection<FrekwencjaEntity> getFrekwencjasByIdn() {
        return frekwencjasByIdn;
    }

    public void setFrekwencjasByIdn(Collection<FrekwencjaEntity> frekwencjasByIdn) {
        this.frekwencjasByIdn = frekwencjasByIdn;
    }

    public Collection<KlasyEntity> getKlasiesByIdn() {
        return klasiesByIdn;
    }

    public void setKlasiesByIdn(Collection<KlasyEntity> klasiesByIdn) {
        this.klasiesByIdn = klasiesByIdn;
    }

    public UzytkownicyEntity getUzytkownicyByIdus() {
        return uzytkownicyByIdus;
    }

    public void setUzytkownicyByIdus(UzytkownicyEntity uzytkownicyByIdus) {
        this.uzytkownicyByIdus = uzytkownicyByIdus;
    }

    public Collection<NauczycieleprzedmiotowEntity> getNauczycieleprzedmiotowsByIdn() {
        return nauczycieleprzedmiotowsByIdn;
    }

    public void setNauczycieleprzedmiotowsByIdn(Collection<NauczycieleprzedmiotowEntity> nauczycieleprzedmiotowsByIdn) {
        this.nauczycieleprzedmiotowsByIdn = nauczycieleprzedmiotowsByIdn;
    }

    public Collection<OcenyEntity> getOceniesByIdn() {
        return oceniesByIdn;
    }

    public void setOceniesByIdn(Collection<OcenyEntity> oceniesByIdn) {
        this.oceniesByIdn = oceniesByIdn;
    }
}
