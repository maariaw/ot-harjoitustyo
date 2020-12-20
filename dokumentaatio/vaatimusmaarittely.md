# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on tarkoitettu avuksi ja iloksi viikoittaisen agendan suunnittelussa. Viikon päivänäkymien ja niihin kuuluvien tapahtumien lisäksi kalenterinäkymässä on viikoittain vaihtuva kuva.

## Käyttäjät

Sovelluksessa on yksi käyttäjärooli. Tämä normaali *käyttäjä* pystyy lisäämään tapahtumia sekä muokkaamaan ja poistamaan lisäämiään tapahtumia. Jatkokehityksessä voisi lisätä suuremmilla oikeuksilla toimivan *pääkäyttäjän*, joka voisi esimerkiksi käsitellä kalenterikuvia.

## Käyttöliittymäluonnos

Sovelluksessa on yksi näkymä, joka aukeaa sovelluksen käynnistyessä. Sovelluksessa on nappi uuden kalenterimerkinnän lisäämiseen. Klikkaamalla aiemmin luotua merkintää voi valita, haluaako muokata merkintää tai poistaa sen. Poisto tapahtuu varmistusikkunan kautta. Alla olevassa luonnoksessa on myös jatkokehityksessä lisättävä ominaisuus, eli sovelluksessa valmiina olevat juhlapäivät ja pyhät.

![Luonnoskuva sovelluksesta](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/vm-1.png)

## Perusversion toiminnallisuus

* Käyttäjä voi selata kalenteria eteen- ja taaksepäin
* Käyttäjä näkee viikon numeron, viikonpäivät maanantaista sunnuntaihin ja päivämäärät
* Käyttäjä voi lisätä kalenteriin merkintöjä
  * Merkinnällä on päivä, teksti ja valinnaisesti ajankohta
* Käyttäjä voi poistaa tekemiään merkintöjä
* Käyttäjä näkee jokaisen päivän kohdalla sille päivälle kuuluvat merkinnät
  * Koko päivää koskevat merkinnät ovat ylimpänä
  * Ajastetut merkinnät ovat kronologisessa järjestyksessä alkuajan mukaan
* Sovelluksessa näkyy kuva
  * Kuva vaihtuu joka viikko
  * Menneet kuvat näkyvät, kun selaa taaksepäin, mutta tulevat kuvat eivät

## Jatkokehitysideoita

* Kalenterissa on valmiina tärkeimmät kansalliset ja kansainväliset juhlapäivät
* Hyppääminen valikon kautta suoraan tiettyyn viikkoon
* Roolin vaihtaminen käyttäjän ja ylläpitäjän välillä
  * Ylläpitäjä voi muokata kuvavalikoimaa valitsemalla omista tiedostoistaan näytettäviä kuvia
  * Ylläpitäjä voi muokata kaikkia kalenterimerkintöjä
* Kuvia on joka viikolle 2-3, joista käyttäjä voi valita mieluisimman
* Vanhemman kuvan kiinnittäminen näkymään
* Muistutukset tulevan viikon tapahtumista
  * Käyttäjä voi valita, mistä tapahtumista haluaa muistutuksen edellisellä viikolla
* Tapahtumien poistaminen bulkissa (esim. kaikki menneet)
* Toistuvat tapahtumat
* Kalenterinäkymän muokkaaminen (viikon alkupäivä, päivämäärän merkintätapa, kieli ym.)
* Koko viikkoa koskevat merkinnät
* Graafisempi päivänäkymä
  * Lukujärjestysmäinen, ajastetut tapahtumat palkkeina tunneittain ym.
* Sisällytetty todo-toiminnallisuus
  * Viikkokohtaiset ja päiväkohtaiset to-dot
  * Mahdollisuus siirtää tekemättömät todot seuraavalle viikolle
  * Toistuvat todot, ym.
