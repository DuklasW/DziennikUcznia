package Enitities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "frekwencja", schema = "public", catalog = "ikyxpswp")
public class FrekwencjaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idf", nullable = false)
    private long idf;
    @Basic
    @Column(name = "idp", nullable = false)
    private long idp;
    @Basic
    @Column(name = "idu", nullable = false)
    private long idu;
    @Basic
    @Column(name = "rodzaj", nullable = true, length = 20)
    private String rodzaj;
    @Basic
    @Column(name = "idl", nullable = true)
    private Long idl;
    @Basic
    @Column(name = "idn", nullable = true)
    private Long idn;
    @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "idp", nullable = false, insertable = false, updatable = false)
    private PrzedmiotyEntity przedmiotyByIdp;
    @ManyToOne
    @JoinColumn(name = "idu", referencedColumnName = "idu", nullable = false, insertable = false, updatable = false)
    private UczniowieEntity uczniowieByIdu;
    @ManyToOne
    @JoinColumn(name = "idl", referencedColumnName = "idl", insertable = false, updatable = false)
    private LekcjeEntity lekcjeByIdl;
    @ManyToOne
    @JoinColumn(name = "idn", referencedColumnName = "idn", insertable = false, updatable = false)
    private NauczycieleEntity nauczycieleByIdn;

    public long getIdf() {
        return idf;
    }

    public void setIdf(long idf) {
        this.idf = idf;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Long getIdl() {
        return idl;
    }

    public void setIdl(Long idl) {
        this.idl = idl;
    }

    public Long getIdn() {
        return idn;
    }

    public void setIdn(Long idn) {
        this.idn = idn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrekwencjaEntity that = (FrekwencjaEntity) o;
        return idf == that.idf && idp == that.idp && idu == that.idu && Objects.equals(rodzaj, that.rodzaj) && Objects.equals(idl, that.idl) && Objects.equals(idn, that.idn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idf, idp, idu, rodzaj, idl, idn);
    }

    public PrzedmiotyEntity getPrzedmiotyByIdp() {
        return przedmiotyByIdp;
    }

    public void setPrzedmiotyByIdp(PrzedmiotyEntity przedmiotyByIdp) {
        this.przedmiotyByIdp = przedmiotyByIdp;
    }

    public UczniowieEntity getUczniowieByIdu() {
        return uczniowieByIdu;
    }

    public void setUczniowieByIdu(UczniowieEntity uczniowieByIdu) {
        this.uczniowieByIdu = uczniowieByIdu;
    }

    public LekcjeEntity getLekcjeByIdl() {
        return lekcjeByIdl;
    }

    public void setLekcjeByIdl(LekcjeEntity lekcjeByIdl) {
        this.lekcjeByIdl = lekcjeByIdl;
    }

    public NauczycieleEntity getNauczycieleByIdn() {
        return nauczycieleByIdn;
    }

    public void setNauczycieleByIdn(NauczycieleEntity nauczycieleByIdn) {
        this.nauczycieleByIdn = nauczycieleByIdn;
    }
}
