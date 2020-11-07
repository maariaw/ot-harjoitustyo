# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on tarkoitettu avuksi ja iloksi viikoittaisen agendan suunnittelussa. Seitsemän päivän tärkeiden tapahtumien ja merkintöjen lisäksi kalenterinäkymässä on viikoittain vaihtuva kuva.

## Käyttäjät

Sovelluksen kehitys aloitetaan yhdellä käyttäjäroolilla. Normaali *käyttäjä* pystyy lisäämään tapahtumia sekä muokkaamaan ja poistamaan lisäämiään tapahtumia. Myöhemmin on tarkoitus lisätä *ylläpitäjän* rooli, joka pystyy muokkaamaan sellaisia sovelluksen ominaisuuksia, jotka aluksi on suoraan koodattuna. Ylläpitäjä pystyy esimerkiksi muokkaamaan myös kalenterissa valmiiksi olevia merkintöjä, kuten kansallisia juhlapäiviä. Ylläpitäjänä pääsisi lisäksi muokkaamaan kuvagalleriaa. Sovellukselta haluttu ominaisuus on, että toinen henkilö voi valita kalenterikuvat yllätykseksi käyttäjälle. Näin kuvavalikoiman voi myös vaihtaa joka vuosi.

## Perusversion toiminnallisuus

* Sovelluksessa näkyy kuva
  * Kuva vaihtuu joka viikko
  * Kuva liittyy vuodenaikaan
  * Kuvissa on söpöjä eläimiä
* Käyttäjä näkee viikon numeron, viikonpäivät maanantaista sunnuntaihin ja päivämäärät
* Käyttäjä voi selata kalenteria eteen- ja taaksepäin
* Käyttäjä voi lisätä kalenteriin merkintöjä
  * Merkinnällä on päivä, teksti ja mahdollisesti ajankohta
* Käyttäjä näkee jokaisen päivän kohdalla sille päivälle kuuluvat merkinnät
  * Kalenterissa on valmiina tärkeimmät kansalliset ja kansainväliset juhlapäivät
  * Koko päivää koskevat merkinnät ovat ylimpänä
  * Ajastetut merkinnät ovat kronologisessa järjestyksessä alkuajan mukaan

## Jatkokehitysideoita

* Hyppääminen valikon kautta suoraan tiettyyn viikkoon
* Roolin vaihtaminen käyttäjän ja ylläpitäjän välillä
* Ylläpitäjä voi muokata kuvavalikoimaa valitsemalla omista tiedostoistaan näytettäviä kuvia
* Ylläpitäjä voi muokata kaikkia kalenterimerkintöjä
* Kuvia on joka viikolle 2-3, joista käyttäjä voi valita mieluisimman
* Kuvien selaaminen taaksepäin, mutta ei eteenpäin (mahdollisesti voi kiinnittää vanhemman kuvan näkymään)
* Muistutukset tulevan viikon tapahtumista
  * Käyttäjä voi valita, mistä tapahtumista haluaa muistutuksen edellisellä viikolla
* Koko viikkoa koskevat merkinnät
* Graafisempi päivänäkymä
  * Lukujärjestysmäinen, ajastetut tapahtumat palkkeina tunneittain ym.
* Sisällytetty todo-toiminnallisuus
  * Viikkokohtaiset ja päiväkohtaiset to-dot
  * Mahdollisuus siirtää tekemättömät todot seuraavalle viikolle
  * Toistuvat todot, ym.
