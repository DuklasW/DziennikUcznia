package Enitities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "uzytkownicy", schema = "public", catalog = "ikyxpswp")
public class UzytkownicyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idus", nullable = false)
    private long idus;
    @Basic
    @Column(name = "login", nullable = true, length = 20)
    private String login;
    @Basic
    @Column(name = "haslo", nullable = true, length = 20)
    private String haslo;
    @Basic
    @Column(name = "imie", nullable = true, length = 20)
    private String imie;
    @Basic
    @Column(name = "nazwisko", nullable = true, length = 20)
    private String nazwisko;
    @Basic
    @Column(name = "rola", nullable = false, length = 20)
    private String rola;
    @OneToMany(mappedBy = "uzytkownicyByIdus")
    private Collection<NauczycieleEntity> nauczycielesByIdus;
    @OneToMany(mappedBy = "uzytkownicyByIdus")
    private Collection<UczniowieEntity> uczniowiesByIdus;
    @OneToMany(mappedBy = "uzytkownicyByIdurodzica")
    private Collection<UczniowieEntity> uczniowiesByIdus_0;

    public long getIdus() {
        return idus;
    }

    public void setIdus(long idus) {
        this.idus = idus;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UzytkownicyEntity that = (UzytkownicyEntity) o;
        return idus == that.idus && Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo) && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(rola, that.rola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idus, login, haslo, imie, nazwisko, rola);
    }

    public Collection<NauczycieleEntity> getNauczycielesByIdus() {
        return nauczycielesByIdus;
    }

    public void setNauczycielesByIdus(Collection<NauczycieleEntity> nauczycielesByIdus) {
        this.nauczycielesByIdus = nauczycielesByIdus;
    }

    public Collection<UczniowieEntity> getUczniowiesByIdus() {
        return uczniowiesByIdus;
    }

    public void setUczniowiesByIdus(Collection<UczniowieEntity> uczniowiesByIdus) {
        this.uczniowiesByIdus = uczniowiesByIdus;
    }

    public Collection<UczniowieEntity> getUczniowiesByIdus_0() {
        return uczniowiesByIdus_0;
    }

    public void setUczniowiesByIdus_0(Collection<UczniowieEntity> uczniowiesByIdus_0) {
        this.uczniowiesByIdus_0 = uczniowiesByIdus_0;
    }
}
