package Enitities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "klasy", schema = "public", catalog = "ikyxpswp")
public class KlasyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idk", nullable = false)
    private long idk;
    @Basic
    @Column(name = "nazwa", nullable = false, length = 20)
    private String nazwa;
    @Basic
    @Column(name = "datarozpoczecia", nullable = true)
    private Date datarozpoczecia;
    @Basic
    @Column(name = "wychowawca", nullable = true)
    private Long wychowawca;
    @ManyToOne
    @JoinColumn(name = "wychowawca", referencedColumnName = "idn", insertable = false, updatable = false)
    private NauczycieleEntity nauczycieleByWychowawca;
    @OneToMany(mappedBy = "klasyByKlasa")
    private Collection<LekcjeEntity> lekcjesByIdk;
    @OneToMany(mappedBy = "klasyByIdk")
    private Collection<PrzedmiotyklasEntity> przedmiotyklasByIdk;
    @OneToMany(mappedBy = "klasyByIdk")
    private Collection<UczniowieEntity> uczniowiesByIdk;

    public long getIdk() {
        return idk;
    }

    public void setIdk(long idk) {
        this.idk = idk;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDatarozpoczecia() {
        return datarozpoczecia;
    }

    public void setDatarozpoczecia(Date datarozpoczecia) {
        this.datarozpoczecia = datarozpoczecia;
    }

    public Long getWychowawca() {
        return wychowawca;
    }

    public void setWychowawca(Long wychowawca) {
        this.wychowawca = wychowawca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlasyEntity that = (KlasyEntity) o;
        return idk == that.idk && Objects.equals(nazwa, that.nazwa) && Objects.equals(datarozpoczecia, that.datarozpoczecia) && Objects.equals(wychowawca, that.wychowawca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idk, nazwa, datarozpoczecia, wychowawca);
    }

    public NauczycieleEntity getNauczycieleByWychowawca() {
        return nauczycieleByWychowawca;
    }

    public void setNauczycieleByWychowawca(NauczycieleEntity nauczycieleByWychowawca) {
        this.nauczycieleByWychowawca = nauczycieleByWychowawca;
    }

    public Collection<LekcjeEntity> getLekcjesByIdk() {
        return lekcjesByIdk;
    }

    public void setLekcjesByIdk(Collection<LekcjeEntity> lekcjesByIdk) {
        this.lekcjesByIdk = lekcjesByIdk;
    }

    public Collection<PrzedmiotyklasEntity> getPrzedmiotyklasByIdk() {
        return przedmiotyklasByIdk;
    }

    public void setPrzedmiotyklasByIdk(Collection<PrzedmiotyklasEntity> przedmiotyklasByIdk) {
        this.przedmiotyklasByIdk = przedmiotyklasByIdk;
    }

    public Collection<UczniowieEntity> getUczniowiesByIdk() {
        return uczniowiesByIdk;
    }

    public void setUczniowiesByIdk(Collection<UczniowieEntity> uczniowiesByIdk) {
        this.uczniowiesByIdk = uczniowiesByIdk;
    }
}
