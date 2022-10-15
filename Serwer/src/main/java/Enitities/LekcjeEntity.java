package Enitities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "lekcje", schema = "public", catalog = "ikyxpswp")
public class LekcjeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idl", nullable = false)
    private long idl;
    @Basic
    @Column(name = "godzina", nullable = true)
    private Integer godzina;
    @Basic
    @Column(name = "klasa", nullable = false)
    private long klasa;
    @Basic
    @Column(name = "idnp", nullable = false)
    private long idnp;
    @Basic
    @Column(name = "data", nullable = true)
    private Date data;
    @Basic
    @Column(name = "temat", nullable = true, length = 150)
    private String temat;
    @OneToMany(mappedBy = "lekcjeByIdl")
    private Collection<FrekwencjaEntity> frekwencjasByIdl;
    @ManyToOne
    @JoinColumn(name = "klasa", referencedColumnName = "idk", nullable = false, insertable = false, updatable = false)
    private KlasyEntity klasyByKlasa;
    @ManyToOne
    @JoinColumn(name = "idnp", referencedColumnName = "idnp", nullable = false, insertable = false, updatable = false)
    private NauczycieleprzedmiotowEntity nauczycieleprzedmiotowByIdnp;

    public long getIdl() {
        return idl;
    }

    public void setIdl(long idl) {
        this.idl = idl;
    }

    public Integer getGodzina() {
        return godzina;
    }

    public void setGodzina(Integer godzina) {
        this.godzina = godzina;
    }

    public long getKlasa() {
        return klasa;
    }

    public void setKlasa(long klasa) {
        this.klasa = klasa;
    }

    public long getIdnp() {
        return idnp;
    }

    public void setIdnp(long idnp) {
        this.idnp = idnp;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekcjeEntity that = (LekcjeEntity) o;
        return idl == that.idl && klasa == that.klasa && idnp == that.idnp && Objects.equals(godzina, that.godzina) && Objects.equals(data, that.data) && Objects.equals(temat, that.temat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idl, godzina, klasa, idnp, data, temat);
    }

    public Collection<FrekwencjaEntity> getFrekwencjasByIdl() {
        return frekwencjasByIdl;
    }

    public void setFrekwencjasByIdl(Collection<FrekwencjaEntity> frekwencjasByIdl) {
        this.frekwencjasByIdl = frekwencjasByIdl;
    }

    public KlasyEntity getKlasyByKlasa() {
        return klasyByKlasa;
    }

    public void setKlasyByKlasa(KlasyEntity klasyByKlasa) {
        this.klasyByKlasa = klasyByKlasa;
    }

    public NauczycieleprzedmiotowEntity getNauczycieleprzedmiotowByIdnp() {
        return nauczycieleprzedmiotowByIdnp;
    }

    public void setNauczycieleprzedmiotowByIdnp(NauczycieleprzedmiotowEntity nauczycieleprzedmiotowByIdnp) {
        this.nauczycieleprzedmiotowByIdnp = nauczycieleprzedmiotowByIdnp;
    }
}
