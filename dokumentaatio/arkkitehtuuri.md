# Arkkitehtuurikuvaus

## Rakenne

Sovellus on rakennettu kerrosarkkitehtuurityylillä. Koodin kolmitasoista rakennetta kuvaa alla oleva pakkauskaavio.

![Sovelluksen pakkausrakenne](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-1.png)

Sovelluksen JavaFX:llä toteutetun graafisen käyttöliittymän koodi on pakkauksessa `viikkokalenteri.ui`. Sovelluslogiikan koodi taas sisältyy pakkaukseen `viikkokalenteri.domain` ja kalenterin tapahtumien pysyväistallennuksesta vastaava koodi pakkaukseen`viikkokalenteri.dao`.

### Käyttöliittymä

Viikkokalenterin käyttöliittymässä on yksi päänäkymä, josta voi avata pienen ikkunan uuden tapahtuman kirjaamista tai olemassaolevan tapahtuman muokkaamista varten. Molempia näkymiä vastaa oma [*Stage*](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)-olionsa, joissa on aina sama [*Scene*](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olio sijoitettuna. Päänäkymä päivittyy kutsumalla metodia [*setWeekScene*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java#L111), joka muokkaa [*VBox*](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html)-oliomuuttujan sisältöä, kun kalenteria selataan tai tapahtumissa tehdään muutoksia. Lisäksi käyttöliittymässä avautuu vahvistusikkuna, kun käyttäjä on poistamassa tapahtumaa. Käyttöliittymän toiminnasta vastaa luokka [*viikkokalenteri.ui.ViikkokalenteriUI*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java). Sen tukena on luokka [*viikkokalenteri.ui.Localization*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/ui/Localization.java), jota hyödynnetään ajan esitystapojen muotoilussa.

Käyttöliittymä ei toteuta sovelluslogiikkaan liittyviä toimintoja suoraan, vaan kutsuu oliomuuttujiensa *eventService* ja *timeService* metodeja, kun kalenteria selataan tai muokataan. Näin käyttöliittymä ja sovelluslogiikka on eriytetty toisistaan.

### Sovelluslogiikka

Sovelluksessa on kaksi toiminnasta vastaavaa luokkaa [*TimeService*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/TimeService.java) ja [*EventService*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/EventService.java), sekä tapahtumaa mallintava luokka [*Event*](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/Event.java).

*TimeService* pitää kirjaa kalenterin kulloisestakin tilasta aikajanalla ja tarjoaa UI:lle metodeja tilan muuttamiseen, jotta kalenteria voi selata, sekä tilaan liittyvien tietojen tiedusteluun näkymän muodostamiseksi. Metodeja ovat muun muassa:

* int getWeek()
* void nextWeek()
* void lastWeek()
* boolean isFuture()

*EventService* toimii välittäjänä käyttöjärjestelmän ja pysyväistallennuksen välissä. Se tarjoaa metodeja käyttöliittymän tapahtumiin liittyville toiminnoille, kuten tapahtumien luomiselle ja muokkaamiselle sekä tietyn päivän tapahtumien listaamiselle kalenterinäkymää varten, esimerkiksi:

* boolean createEvent(LocalDate date, String time, String description, boolean timed)
* void editEvent(Event event, LocalDate date, String time, String description, boolean timed)
* boolean removeEvent(Event event)
* List<Event> getEventsForDay(LocalDate date)
  
Tapahtumat luodaan ja välitetään olioina luokasta *Event*.

Sovelluksen luokkien ja pakkausten suhteita kuvaa seuraava luokka/pakkauskaavio:

![Sovelluksen luokka/pakkauskaavio](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-2.png)

### Tietojen pysyväistallennus

Pakkauksessa *viikkokalenteri.dao* on rajapinta *EventDao*, jota toteuttaa luokka *FileEventDao*, joka käytännössä vastaa tapahtumien tallentamisesta tiedostoon. Sovelluslogiikka käyttää luokkaa rajapinnan kautta, joten luokka on mahdollista vaihtaa toisenlaiseen toteutukseen, esimerkiksi tietokantaan. Testauksessa käytetäänkin rajapinnan toteuttavaa *FakeEventDao*-luokkaa, joka ei tallenna tietoa pysyvästi.

Viikkokalenteri tallettaa tapahtumat tiedostoon, jonka nimen määrittelee konfiguraatiotiedosto *config.properties*, jonka se tarvittaessa luo. Sovellus tallettaa tapahtumat seuraavassa formaatissa:

```
2020-12-19;00:00;Äidin synttärit;false
2020-12-19;14:30;Juhlat;true
```

Ensimmäisenä on päiväys muodossa vvvv-kk-pp, sitten aika muodossa tt:mm, sitten tapahtuman kuvaus/nimi ja lopuksi tieto siitä, onko käyttäjä asettanut tapahtumalle ajan vai ei, eli näytetäänkö tapahtuma ajastettuna vai ajastamattomana. Kentät on erotettu puolipisteillä.

#### Kuvat

Viikkokalenterissa näytettävät kuvat on tallennettu */src/main/resources*-kansioon. Käyttäjä ei toistaiseksi pääse muokkaamaan kuvia. Sovellus löytää viikolle oikean kuvatiedoston sen nimen perusteella. Esimerkiksi vuoden 2020 viikon 49 kuva on nimeltään *202049.jpg*.

## Toiminta

### Päätoiminnallisuudet

#### Uuden tapahtuman lisääminen

Kun käyttäjä on Uusi tapahtuma -ikkunassa, hän valitsee päivämäärän ja halutessaan kellonajan, sekä kirjoittaa tekstikenttään tapahtuman nimen tai kuvauksen. Kun käyttäjä sitten painaa *okButton*-nappia ("Vie kalenteriin"), tapahtuman käsittely etenee seuraavan kaavion mukaisesti:

![Sekvenssikaavio tapahtuman lisäämisestä](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-3.png)

[Tapahtumakäsittelijä](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java#L317) kutsuu *EventService*-luokan metodia [*createEvent*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/domain/EventService.java#L51), joka luo annetuilla parametreillä *Event*-olion. *EventService* antaa tapahtuman sitten tallennettavaksi kutsumalla *EventDaon* metodia [*create*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/dao/EventDao.java#L20). Onnistuneen tallennuksen seurauksena kontrolli palaa käyttöliittymälle, joka päivittää näkymän.

#### Tapahtuman muokkaaminen

Jos taas käyttäjä on muokkaamassa olemassaolevaa tapahtumaa, "Tallenna muutokset" -napin painamista seuraa tämän kaavion mukainen käsittelyketju:

![Sekvenssikaavio tapahtuman muokkaamisesta](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-4.png)

Kyseessä on sama tapahtumakäsittelijä kuin edellä, mutta se käyttäytyy eri tavoin riippuen siitä, onko käyttäjä päätynyt ikkunaan uuden tapahtuman luomisnapista vai tapahtuman muokkausnapista. Tällä kertaa kutsutaan *EventService*-luokan metodia [*editEvent*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/domain/EventService.java#L95), jolle annetaan parametreiksi vanha tapahtuma *Event*-oliona, sekä halutut uudet aika- ja kuvaustiedot. Tämä metodi puolestaan kutsuu saman luokan *createEvent*-metodia, joka toimii kuten ensimmäisessä sekvenssikaaviossa. Jos annetut uudet parametrit vastaisivat täysin vanhoja, uutta tapahtumaa ei luotaisi, jolloin *EventDaon* *create-metodi* palauttaisi `false`. Tällöin vanhaa tapahtumaa ei myöskään poistettaisi, ja kontrolli palaisi käyttöliittymälle. Jos taas muutoksia on tehty, kuten esimerkissä, uusi tapahtuma on nyt luotu ja sekä *EventDaon* *create-metodi* että *EventServicen* *createEvent* palauttavat `true`. Tällöin *EventServicen* metodi *editEvent* kutsuu seuraavaksi saman luokan metodia [*removeEvent*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/domain/EventService.java#L119), joka puolestaan kutsuu *EventDaon* metodia [*remove*](https://github.com/maariaw/ot-harjoitustyo/blob/c40fc66d0ddbeb5a96da5fbfdaa1294bc1c38de1/Viikkokalenteri/src/main/java/viikkokalenteri/dao/EventDao.java#L43). Parametrina molemmissa on vanha tapahtumaolio, joka poistuu sitten pysyväistallennuksesta. Lopuksi palataan käyttöliittymään, joka päivittää näkymän.

#### Tapahtuman poistaminen

Tapahtuman poistamisessa käyttöliittymän [tapahtumakäsittelijä](https://github.com/maariaw/ot-harjoitustyo/blob/f1602772d6cd172d2e074df6740aefd281fb4107/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java#L240) kutsuu *EventServicen* metodia *removeEvent*, ja kontrolli etenee kuten edellisen kaavion jälkipuoliskolla.

### Aikatoiminnallisuudet

Kalenterin aikanäkymän muuttamiseksi käyttöliittymä kutsuu *TimeService*-luokan metodeja. *TimeService* ylläpitää aikaikkunaa [*LocalDate*](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html) luokan ilmentymän avulla. Aikaikkunaa vaihdetaan vaihtamalla ilmentymä vastaamaan haluttua aikaa. Jos esimerkiksi [tapahtumakäsittelijä](https://github.com/maariaw/ot-harjoitustyo/blob/f1602772d6cd172d2e074df6740aefd281fb4107/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java#L139) kutsuu *TimeServicen* metodia [*lastWeek*](https://github.com/maariaw/ot-harjoitustyo/blob/f1602772d6cd172d2e074df6740aefd281fb4107/Viikkokalenteri/src/main/java/viikkokalenteri/domain/TimeService.java#L55), se sijoittaa *date*-olioon uuden luokan *LocalDate* ilmentymän kutsumalla sillä metodia [*minusWeeks*](https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#minusWeeks-long-).

## Ohjelman rakenteeseen jääneet heikkoudet

### Lokalisaatio

Ohjelmassa on nyt useampi luokka, joka käyttää luokkaa *Localization*. Sen sijainti ui-pakkauksessa kuvaa sen käyttötarkoitusta, mutta on ongelmallinen riippuvuuksien hallinnan kannalta. Lokalisaation vaihdettavuuden kannalta paras olisi jonkinlainen rajapinta, jota toteuttamaan voisi tehdä erilaisia luokkia. Siten lokalisaation toteutuksen voisi injektoida riippuvuutena. Luokka ei ole todennäköisesti sisällöltäänkään vielä täydellinen siten, että sen kenttiä muuttamalla voisi täydellisesti muuttaa ohjelman käyttämät standardit. Lisäksi tarvittaisiin ehkä myös eriytetty kielipaketti, jotta ohjelman voisi helposti kääntää toisessa maassa käytettäväksi. Tällä hetkellä luokan *Localization* pääasiallinen hyöty on koodin selkiyttäminen eristämällä mm. ikävän näköiset säännölliset lausekkeet.

### Käyttöliittymä

Käyttöliittymää voisi siistiä huomattavasti esimerkiksi käyttämällä FXML-määrittelyä.

### Tiedon tallennus

Tiedon pysyväistallennukseen voisi olla edistyneempää käyttää tietokantaa. Se mahdollistaisi monipuolisemman tapahtumien käsittelyn. Esimerkiksi toistuvien tapahtumien kollektiivista muokkaamista lienisi tällä menetelmällä hankala toteuttaa.
