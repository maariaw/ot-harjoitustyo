# Käyttöohje

## Lataaminen

## Käynnistäminen

Alustavasti oletetaan että ohjelma suoritetaan repositoriosta käsin komennolla

> mvn compile exec:java -Dexec.mainClass=viikkokalenteri.Main

## Navigointi

Kalenteri avautuu kuluvan viikon kohdalta. Kuvan alla näkyy viikon numero. Sen molemmin puolin olevilla painikkeilla voi selata kalenteria eteen- ja taaksepäin. Tulevia viikkoja katsellessa kuva pysyy tämänviikkoisessa kuvassa, uusi kuva tulee näkyviin vasta sille kuuluvalla viikolla.

## Uuden tapahtuman luominen

Kuvan alla vasemmalla on nappi "Uusi tapahtuma". Kun sitä painaa, avautuu uusi ikkuna uuden tapahtumamerkinnän kirjaamista varten. Valitse tapahtuman päivämäärä klikkaamalla päivämääräkentän oikeassa laidassa olevaa kalenteri-ikonia ja valitsemalla avautuvassa kalenterivalikosta klikkaamalla haluttu päivä. Valikon yläreunassa on vasemmalla nuolinapit kuukausien välillä navigointiin ja oikeassa laidassa vuosien välillä navigointiin. Tapahtuman kuvaus kirjoitetaan "Uusi tapahtuma"-ikkunan alempaan kenttään. Painamalla "Vie kalenteriin"-nappia tapahtuma tallentuu kalenteriin. Voit lisätä sitten seuraavan tapahtuman, tai sulkea ikkunan.
