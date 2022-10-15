package Enitities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "przedmiotyklas", schema = "public", catalog = "ikyxpswp")
public class PrzedmiotyklasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idpk", nullable = false)
    private long idpk;
    @Basic
    @Column(name = "idp", nullable = false)
    private long idp;
    @Basic
    @Column(name = "idk", nullable = false)
    private long idk;
    @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "idp", nullable = false, insertable = false, updatable = false)
    private PrzedmiotyEntity przedmiotyByIdp;
    @ManyToOne
    @JoinColumn(name = "idk", referencedColumnName = "idk", nullable = false, insertable = false, updatable = false)
    private KlasyEntity klasyByIdk;

    public long getIdpk() {
        return idpk;
    }

    public void setIdpk(long idpk) {
        this.idpk = idpk;
    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public long getIdk() {
        return idk;
    }

    public void setIdk(long idk) {
        this.idk = idk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrzedmiotyklasEntity that = (PrzedmiotyklasEntity) o;
        return idpk == that.idpk && idp == that.idp && idk == that.idk;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpk, idp, idk);
    }

    public PrzedmiotyEntity getPrzedmiotyByIdp() {
        return przedmiotyByIdp;
    }

    public void setPrzedmiotyByIdp(PrzedmiotyEntity przedmiotyByIdp) {
        this.przedmiotyByIdp = przedmiotyByIdp;
    }

    public KlasyEntity getKlasyByIdk() {
        return klasyByIdk;
    }

    public void setKlasyByIdk(KlasyEntity klasyByIdk) {
        this.klasyByIdk = klasyByIdk;
    }
}
