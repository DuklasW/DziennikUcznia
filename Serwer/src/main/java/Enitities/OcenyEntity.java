package Enitities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "oceny", schema = "public", catalog = "ikyxpswp")
public class OcenyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ido", nullable = false)
    private long ido;
    @Basic
    @Column(name = "nazwa", nullable = true, length = 20)
    private String nazwa;
    @Basic
    @Column(name = "ocena", nullable = false, precision = 0)
    private float ocena;
    @Basic
    @Column(name = "idu", nullable = false)
    private long idu;
    @Basic
    @Column(name = "idp", nullable = false)
    private long idp;
    @Basic
    @Column(name = "idn", nullable = true)
    private Long idn;
    @Basic
    @Column(name = "data", nullable = true)
    private Date data;
    @ManyToOne
    @JoinColumn(name = "idu", referencedColumnName = "idu", nullable = false, insertable = false, updatable = false)
    private UczniowieEntity uczniowieByIdu;
    @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "idp", nullable = false, insertable = false, updatable = false)
    private PrzedmiotyEntity przedmiotyByIdp;
    @ManyToOne
    @JoinColumn(name = "idn", referencedColumnName = "idn", insertable = false, updatable = false)
    private NauczycieleEntity nauczycieleByIdn;

    public long getIdo() {
        return ido;
    }

    public void setIdo(long ido) {
        this.ido = ido;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public Long getIdn() {
        return idn;
    }

    public void setIdn(Long idn) {
        this.idn = idn;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OcenyEntity that = (OcenyEntity) o;
        return ido == that.ido && Float.compare(that.ocena, ocena) == 0 && idu == that.idu && idp == that.idp && Objects.equals(nazwa, that.nazwa) && Objects.equals(idn, that.idn) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ido, nazwa, ocena, idu, idp, idn, data);
    }

    public UczniowieEntity getUczniowieByIdu() {
        return uczniowieByIdu;
    }

    public void setUczniowieByIdu(UczniowieEntity uczniowieByIdu) {
        this.uczniowieByIdu = uczniowieByIdu;
    }

    public PrzedmiotyEntity getPrzedmiotyByIdp() {
        return przedmiotyByIdp;
    }

    public void setPrzedmiotyByIdp(PrzedmiotyEntity przedmiotyByIdp) {
        this.przedmiotyByIdp = przedmiotyByIdp;
    }

    public NauczycieleEntity getNauczycieleByIdn() {
        return nauczycieleByIdn;
    }

    public void setNauczycieleByIdn(NauczycieleEntity nauczycieleByIdn) {
        this.nauczycieleByIdn = nauczycieleByIdn;
    }
}
