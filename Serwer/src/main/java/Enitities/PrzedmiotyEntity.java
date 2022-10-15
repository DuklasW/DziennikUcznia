package Enitities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "przedmioty", schema = "public", catalog = "ikyxpswp")
public class PrzedmiotyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idp", nullable = false)
    private long idp;
    @Basic
    @Column(name = "nazwa", nullable = true, length = 20)
    private String nazwa;
    @OneToMany(mappedBy = "przedmiotyByIdp")
    private Collection<FrekwencjaEntity> frekwencjasByIdp;
    @OneToMany(mappedBy = "przedmiotyByIdp")
    private Collection<NauczycieleprzedmiotowEntity> nauczycieleprzedmiotowsByIdp;
    @OneToMany(mappedBy = "przedmiotyByIdp")
    private Collection<OcenyEntity> oceniesByIdp;
    @OneToMany(mappedBy = "przedmiotyByIdp")
    private Collection<PrzedmiotyklasEntity> przedmiotyklasByIdp;

    public PrzedmiotyEntity(String nazwa) {
        this.nazwa  = nazwa;
    }

    public PrzedmiotyEntity() {

    }

    public long getIdp() {
        return idp;
    }

    public void setIdp(long idp) {
        this.idp = idp;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrzedmiotyEntity that = (PrzedmiotyEntity) o;
        return idp == that.idp && Objects.equals(nazwa, that.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idp, nazwa);
    }

    public Collection<FrekwencjaEntity> getFrekwencjasByIdp() {
        return frekwencjasByIdp;
    }

    public void setFrekwencjasByIdp(Collection<FrekwencjaEntity> frekwencjasByIdp) {
        this.frekwencjasByIdp = frekwencjasByIdp;
    }

    public Collection<NauczycieleprzedmiotowEntity> getNauczycieleprzedmiotowsByIdp() {
        return nauczycieleprzedmiotowsByIdp;
    }

    public void setNauczycieleprzedmiotowsByIdp(Collection<NauczycieleprzedmiotowEntity> nauczycieleprzedmiotowsByIdp) {
        this.nauczycieleprzedmiotowsByIdp = nauczycieleprzedmiotowsByIdp;
    }

    public Collection<OcenyEntity> getOceniesByIdp() {
        return oceniesByIdp;
    }

    public void setOceniesByIdp(Collection<OcenyEntity> oceniesByIdp) {
        this.oceniesByIdp = oceniesByIdp;
    }

    public Collection<PrzedmiotyklasEntity> getPrzedmiotyklasByIdp() {
        return przedmiotyklasByIdp;
    }

    public void setPrzedmiotyklasByIdp(Collection<PrzedmiotyklasEntity> przedmiotyklasByIdp) {
        this.przedmiotyklasByIdp = przedmiotyklasByIdp;
    }
}
