# Viikkokalenteri
Sovellus on tarkoitettu avuksi ja iloksi viikoittaisen agendan suunnittelussa. Seitsemän päivän tärkeiden tapahtumien ja merkintöjen lisäksi kalenterinäkymässä on viikoittain vaihtuva kuva.

Tämä sovellus rakentui syksyn 2020 toisen periodin aikana Helsingin yliopiston Ohjelmistotekniikan kurssin harjoitustyönä.

## Dokumentaatio

[Käyttöohje](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 5](https://github.com/maariaw/ot-harjoitustyo/releases/tag/v1.0-viikko5)

[Viikko 6](https://github.com/maariaw/ot-harjoitustyo/releases/tag/v1.1-viikko6)

[Viikko 7 / Loppupalautus](https://github.com/maariaw/ot-harjoitustyo/releases/tag/v2.0-viikko7)

## Komentorivitoiminnot
### Suorittaminen
Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=viikkokalenteri.Main
```

### Testaus
Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston *target/site/jacoco/index.html*

### Suoritettavan jarin generointi
Komento

```
mvn package
```

generoi hakemistoon target suoritettavan jar-tiedoston Viikkokalenteri-1.0-SNAPSHOT.jar

### JavaDoc
JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto *target/site/apidocs/index.html*

### Checkstyle
Tiedoston [checkstyle](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*
