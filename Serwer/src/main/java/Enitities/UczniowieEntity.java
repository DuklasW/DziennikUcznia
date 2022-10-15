package Enitities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "uczniowie", schema = "public", catalog = "ikyxpswp")
public class UczniowieEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idu", nullable = false)
    private long idu;
    @Basic
    @Column(name = "nrwdzienniku", nullable = true)
    private Integer nrwdzienniku;
    @Basic
    @Column(name = "idk", nullable = false)
    private long idk;
    @Basic
    @Column(name = "idus", nullable = false)
    private long idus;
    @Basic
    @Column(name = "idurodzica", nullable = false)
    private long idurodzica;
    @OneToMany(mappedBy = "uczniowieByIdu")
    private Collection<FrekwencjaEntity> frekwencjasByIdu;
    @OneToMany(mappedBy = "uczniowieByIdu")
    private Collection<OcenyEntity> oceniesByIdu;
    @ManyToOne
    @JoinColumn(name = "idk", referencedColumnName = "idk", nullable = false, insertable = false, updatable = false)
    private KlasyEntity klasyByIdk;
    @ManyToOne
    @JoinColumn(name = "idus", referencedColumnName = "idus", nullable = false, insertable = false, updatable = false)
    private UzytkownicyEntity uzytkownicyByIdus;
    @ManyToOne
    @JoinColumn(name = "idurodzica", referencedColumnName = "idus", nullable = false, insertable = false, updatable = false)
    private UzytkownicyEntity uzytkownicyByIdurodzica;

    public long getIdu() {
        return idu;
    }

    public void setIdu(long idu) {
        this.idu = idu;
    }

    public Integer getNrwdzienniku() {
        return nrwdzienniku;
    }

    public void setNrwdzienniku(Integer nrwdzienniku) {
        this.nrwdzienniku = nrwdzienniku;
    }

    public long getIdk() {
        return idk;
    }

    public void setIdk(long idk) {
        this.idk = idk;
    }

    public long getIdus() {
        return idus;
    }

    public void setIdus(long idus) {
        this.idus = idus;
    }

    public long getIdurodzica() {
        return idurodzica;
    }

    public void setIdurodzica(long idurodzica) {
        this.idurodzica = idurodzica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UczniowieEntity that = (UczniowieEntity) o;
        return idu == that.idu && idk == that.idk && idus == that.idus && idurodzica == that.idurodzica && Objects.equals(nrwdzienniku, that.nrwdzienniku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idu, nrwdzienniku, idk, idus, idurodzica);
    }

    public Collection<FrekwencjaEntity> getFrekwencjasByIdu() {
        return frekwencjasByIdu;
    }

    public void setFrekwencjasByIdu(Collection<FrekwencjaEntity> frekwencjasByIdu) {
        this.frekwencjasByIdu = frekwencjasByIdu;
    }

    public Collection<OcenyEntity> getOceniesByIdu() {
        return oceniesByIdu;
    }

    public void setOceniesByIdu(Collection<OcenyEntity> oceniesByIdu) {
        this.oceniesByIdu = oceniesByIdu;
    }

    public KlasyEntity getKlasyByIdk() {
        return klasyByIdk;
    }

    public void setKlasyByIdk(KlasyEntity klasyByIdk) {
        this.klasyByIdk = klasyByIdk;
    }

    public UzytkownicyEntity getUzytkownicyByIdus() {
        return uzytkownicyByIdus;
    }

    public void setUzytkownicyByIdus(UzytkownicyEntity uzytkownicyByIdus) {
        this.uzytkownicyByIdus = uzytkownicyByIdus;
    }

    public UzytkownicyEntity getUzytkownicyByIdurodzica() {
        return uzytkownicyByIdurodzica;
    }

    public void setUzytkownicyByIdurodzica(UzytkownicyEntity uzytkownicyByIdurodzica) {
        this.uzytkownicyByIdurodzica = uzytkownicyByIdurodzica;
    }
}
