# Viikkokalenteri
Sovellus on tarkoitettu avuksi ja iloksi viikoittaisen agendan suunnittelussa. Seitsemän päivän tärkeiden tapahtumien ja merkintöjen lisäksi kalenterinäkymässä on viikoittain vaihtuva kuva.

Tämä sovellus rakentuu hiljalleen Helsingin yliopiston Ohjelmistotekniikan kurssin harjoitustyönä.

## Dokumentaatio

[Käyttöohje](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)

[Release](https://github.com/maariaw/ot-harjoitustyo/releases/tag/v1.0-viikko5)

## Komentorivitoiminnot
### Suorittaminen
Ohjelman voi suorittaa komennolla

> mvn compile exec:java -Dexec.mainClass=viikkokalenteri.Main

### Testaus
Testit suoritetaan komennolla

> mvn test

Testikattavuusraportti luodaan komennolla

> mvn jacoco:report

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedoston target/site/jacoco/index.html

### Suoritettavan jarin generointi
Komento

>mvn package

generoi hakemistoon target suoritettavan jar-tiedoston Viikkokalenteri-1.0-SNAPSHOT.jar

Tiedoston suorittamiseksi kansiossa täytyy olla myös tiedosto config.properties, joka sisältää vain tämän rivin:

> eventFile=events.txt

### Checkstyle
Tiedoston [checkstyle](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

> mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html
