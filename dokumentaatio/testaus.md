# Testausdokumentti

Sovellukselle on luotu automatisoituja JUnit-testejä, joilla ohjelman osien toimintaa on testattu. Lisäksi Viikkokalenterin käyttöönottoa ja toimivuutta järjestelmätasolla on testattu käsin.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Sovelluksen toiminnallisuuden tärkeimmät automatisoidut testit ovat integraatiotestit luokassa [*EventServiceTest*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/test/java/viikkokalenteri/domain/EventServiceTest.java). Niissä testataan *EventService*-luokan metodeja, jotka käsittelevät *Event*-olioita. Tallennukseen käytetään rajapinnan *EventDao* toteuttavan [*FakeEventDao*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/test/java/viikkokalenteri/domain/FakeEventDao.java)-luokan ilmentymää. 

Sovelluslogiikan testaamiseen on luotu myös puhtaita yksikkötestejä, joilla testataan erikseen *Event*-luokkaa sekä itsenäisempää *TimeService*-luokkaa. [*EventTest*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/test/java/viikkokalenteri/domain/EventTest.java) testaa *equals*-metodia, mihin moni *EventService*-luokan toiminto nojaa. [*TimeServiceTest*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/test/java/viikkokalenteri/domain/TimeServiceTest.java) testaa pintapuolisesti aikaikkunan manipulointimetodeja. Testaus oli oleellisempaa sovelluksen aiemmassa versiossa, koska siinä käytettiin Calendar-luokkaa, joka ei aina toiminut odotetusti. Uudemmassa versiossa käytetään LocalDate-luokkaa, johon siirtyminen oli automaattisten testien ansiosta turvallista.

### DAO-luokat

[*FileEventDaoTest*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/test/java/viikkokalenteri/dao/FileEventDaoTest.java) testaa tiedostoon tallentavaa *FileEventDao*-luokkaa hyödyntäen JUnitin [*TemporaryFolder*](https://junit.org/junit4/javadoc/4.12/org/junit/rules/TemporaryFolder.html)-luokkaa, joka otetaan käyttöön [*Rule*](https://junit.org/junit4/javadoc/4.12/org/junit/Rule.html)-annotaation alla. Näin tiedostojen kirjoittaminen ja lukeminen voidaan testata automaattisesti.

### Testauskattavuus

Testauksen rivikattavuus on 87 % ja haaraumakattavuus 83 %. Käyttöliittymäkerrosta ei ole testattu, eikä se ole mukana testauskattavuusraportissa.

![Testauskattavuusraportti](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/t-1.png)

Testit eivät kata tilanteita, missä tapahtumat tallettavaan tiedostoon ei ole pääsyä.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi



### Toiminnallisuudet



## Sovellukseen jääneet laatuongelmat

Jos tapahtumat tallentavaan tiedostoon ei ole kirjoitusoikeuksia, käyttäjä ei välttämättä huomaa asiaa, koska tapahtumat tulevat näkyviin kalenteriin, mutta eivät tallennu pysyvästi, eikä asiasta tule virheilmoitusta. Viikkokalenteri käyttää myös eri tallennuspaikkaa riippuen siitä, käynnistääkö ohjelman komentoriviltä vai klikkaamalla kuvaketta. Kuvakkeesta käynnistetyn ohjelman tallennustiedosto ei tule kansioon näkyviin.
