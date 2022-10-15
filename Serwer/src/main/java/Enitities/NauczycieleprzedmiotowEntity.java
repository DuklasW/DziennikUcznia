package Enitities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "nauczycieleprzedmiotow", schema = "public", catalog = "ikyxpswp")
public class NauczycieleprzedmiotowEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idnp", nullable = false)
    private long idnp;
    @Basic
    @Column(name = "idn", nullable = false)
    private long idn;
    @Basic
    @Column(name = "idp", nullable = false)
    private long idp;
    @OneToMany(mappedBy = "nauczycieleprzedmiotowByIdnp")
    private Collection<LekcjeEntity> lekcjesByIdnp;
    @ManyToOne
    @JoinColumn(name = "idn", referencedColumnName = "idn", nullable = false, insertable = false, updatable = false)
    private NauczycieleEntity nauczycieleByIdn;
    @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "idp", nullable = false, insertable = false, updatable = false)
    private PrzedmiotyEntity przedmiotyByIdp;

    public long getIdnp() {
        return idnp;
    }

    public void setIdnp(long idnp) {
        this.idnp = idnp;
    }

    public long getIdn() {
        return idn;
    }

    public void setIdn(long idn) {
        this.idn = idn;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NauczycieleprzedmiotowEntity that = (NauczycieleprzedmiotowEntity) o;
        return idnp == that.idnp && idn == that.idn && idp == that.idp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idnp, idn, idp);
    }

    public Collection<LekcjeEntity> getLekcjesByIdnp() {
        return lekcjesByIdnp;
    }

    public void setLekcjesByIdnp(Collection<LekcjeEntity> lekcjesByIdnp) {
        this.lekcjesByIdnp = lekcjesByIdnp;
    }

    public NauczycieleEntity getNauczycieleByIdn() {
        return nauczycieleByIdn;
    }

    public void setNauczycieleByIdn(NauczycieleEntity nauczycieleByIdn) {
        this.nauczycieleByIdn = nauczycieleByIdn;
    }

    public PrzedmiotyEntity getPrzedmiotyByIdp() {
        return przedmiotyByIdp;
    }

    public void setPrzedmiotyByIdp(PrzedmiotyEntity przedmiotyByIdp) {
        this.przedmiotyByIdp = przedmiotyByIdp;
    }
}
