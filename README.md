# Viikkokalenteri
Sovellus on tarkoitettu avuksi ja iloksi viikoittaisen agendan suunnittelussa. Seitsemän päivän tärkeiden tapahtumien ja merkintöjen lisäksi kalenterinäkymässä on viikoittain vaihtuva kuva.

Tämä sovellus rakentuu hiljalleen Helsingin yliopiston Ohjelmointitekniikan kurssin harjoitustyönä.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot
### Suorittaminen
Ohjelman voi suorittaa komennolla

> mvn compile exec:java -Dexec.mainClass=viikkokalenteri.Main

### Testaus
Testit suoritetaan komennolla

> mvn test

Testikattavuusraportti luodaan komennolla

> mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html

### Suoritettavan jarin generointi
Komento

>mvn package

generoi hakemistoon target suoritettavan jar-tiedoston Viikkokalenteri-1.0-SNAPSHOT.jar
