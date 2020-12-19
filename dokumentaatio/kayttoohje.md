# Käyttöohje

## Lataaminen

Lataa tiedosto [Viikkokalenteri.jar](https://github.com/maariaw/ot-harjoitustyo/releases/latest).

## Käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar Viikkokalenteri.jar
```

## Navigointi

Kalenteri avautuu kuluvan viikon kohdalta. Kuvan alla näkyy viikon numero. Sen molemmin puolin olevilla painikkeilla voi selata kalenteria eteen- ja taaksepäin. Tulevia viikkoja katsellessa kuva pysyy tämänviikkoisessa kuvassa, uusi kuva tulee näkyviin vasta sille kuuluvalla viikolla.

## Uuden tapahtuman luominen

Kuvan alla vasemmalla on nappi "Uusi tapahtuma". Kun sitä painaa, avautuu uusi ikkuna uuden tapahtumamerkinnän kirjaamista varten.

![Uusi tapahtuma -ikkuna](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-1.png)

Valitse tapahtuman päivämäärä klikkaamalla päivämääräkentän oikeassa laidassa olevaa kalenteri-ikonia ja valitsemalla avautuvassa kalenterivalikosta klikkaamalla haluttu päivä. Kalenterivalikossa voi navigoida kuukausien ja vuosien välillä.

![Kalenterivalikko](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-2.png)

Jos haluat asettaa tapahtumalle kellonajan, klikkaa valinta ruutuun "Aseta aika", jolloin avautuu valikko ajan asettamista varten.

![Aikavalikko](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-3.png)

Valikossa on aikavalinnat 15 minuutin välein, mutta haluamansa ajan voi myös kirjoittaa ruutuun. Ruudussa on jonkin verran rajoitus- ja korjaustoimintoja, jotta valittu aika on oikean muotoinen. Tapahtumaa ei voi lisätä, jos kellonaika on virheellinen. Tapahtumalle täytyy myös antaa kuvaus, ennen kuin sen voi tallentaa painamalla "Vie kalenteriin"-nappia. Ajastetut tapahtumat näkyvät kalenterissa ajastamattomien jälkeen aikajärjestyksessä.

![Tapahtumalista](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-4.png)

## Tapahtuman poistaminen

Kalenterissa olevan tapahtuman kuvaustekstiä voi klikata, jolloin avautuu pieni valikko, jossa voi poistaa tapahtuman tai muokata sitä.

![Tapahtumamenu](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-5.png)

Klikkaamalla "Poista" avautuu varmistusikkuna, jossa voi vielä perua poiston painamalla "Cancel" tai vahvistaa poiston painamalla "OK".

![Poistonvahvistusikkuna](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-6.png)

## Tapahtuman muokkaaminen

Klikkaamalla tapahtumaa ja avautuvasta valikosta "Muokkaa", avautuu ikkuna tapahtuman muokkaamista varten. Tapahtuman nykyiset tiedot ovat valmiiksi valittuina, ja niitä voi muokata samoin kuin uutta tapahtumaa luodessa.

![Muokkausikkuna](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/k-7.png)

## Tapahtumien säilyminen

Ohjelma luo .jar-tiedoston kanssa samaan kansioon tiedoston events.txt, johon tapahtumat tallennetaan. Älä siis hävitä tätä tiedostoa!
