# Arkkitehtuurikuvaus

# Rakenne

Sovellus on rakennettu kerrosarkkitehtuurityylillä. Koodin kolmitasoista rakennetta kuvaa alla oleva pakkauskaavio.

![Sovelluksen pakkausrakenne](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-1.png)

Sovelluksen JavaFX:llä toteutetun graafisen käyttöliittymän koodi on pakkauksessa `viikkokalenteri.ui`. Sovelluslogiikan koodi taas sisältyy pakkaukseen `viikkokalenteri.domain` ja kalenterin tapahtumien pysyväistallennuksesta vastaava koodi pakkaukseen`viikkokalenteri.dao`.

## Käyttöliittymä

Viikkokalenterin käyttöliittymässä on yksi päänäkymä, josta voi avata pienen ikkunan uuden tapahuman kirjaamista varten. Molempia vastaa oma [Stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)-olionsa, joissa on aina sama [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olio sijoitettuna. Kalenterin viikkoja selatessa tai tapahtumaa lisätessä päänäkymä päivittyy [VBox](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html)-oliomuuttujan sisältöä muokkaamalla. Käyttöliittymän toiminnasta vastaa luokka [viikkokalenteri.ui.ViikkokalenteriUI](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/ui/ViikkokalenteriUi.java).

Käyttöliittymä ei toteuta sovelluslogiikkaan liittyviä toimintoja suoraan, vaan kutsuu oliomuuttujiensa eventService ja timeService metodeja, kun kalenteria selataan tai muokataan. Näin käyttöliittymä ja sovelluslogiikka on eriytetty toisistaan.

## Sovelluslogiikka

Sovelluksessa on kaksi toiminnasta vastaavaa luokkaa [TimeService](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/TimeService.java) ja [EventService](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/EventService.java), sekä tapahtumaa mallintava luokka [Event](https://github.com/maariaw/ot-harjoitustyo/blob/main/Viikkokalenteri/src/main/java/viikkokalenteri/domain/Event.java).

TimeService pitää kirjaa kalenterin kulloisestakin tilasta aikajanalla ja tarjoaa UI:lle metodeja tilan muuttamiseen, jotta kalenteria voi selata, sekä tilaan liittyvien tietojen tiedusteluun näkymän muodostamiseksi. Metodeja ovat muun muassa:

* int getWeek()
* void nextWeek()
* void lastWeek()
* boolean isFuture()

EventService toimii välittäjänä käyttöjärjestelmän ja pysyväistallennuksen välissä. Se tarjoaa metodit käyttöliittymän tapahtumiin liittyville toiminnoille, eli uuden tapahtuman luomiselle käyttäjän pyynnöstä ja tietyn päivän tapahtumien listaamiselle kalenterinäkymää varten:

* boolean createEvent(LocalDate date, String description)
* List<Event> getEventsForDay(LocalDate date)
  
Tapahtumat luodaan ja välitetään olioina luokasta Event.

Sovelluksen luokkien ja pakkausten suhteita kuvaa seuraava luokka/pakkauskaavio:

![Sovelluksen luokka/pakkauskaavio](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-2.png)

# Toiminta

### Päätoiminnallisuudet

#### Uuden tapahtuman lisääminen

Kun käyttäjä on Uusi tapahtuma -ikkunassa, hän voi valita kalenterivalitsijalla päivämäärän ja kirjoittaa tekstikenttään tapahtuman nimen tai kuvauksen. Kun käyttäjä sitten painaa createButton-nappia ("Vie kalenteriin"), tapahtuman käsittely etenee seuraavan kaavion mukaisesti:

![Sekvenssikaavio tapahtuman lisäämisestä](https://github.com/maariaw/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/a-3.png)
