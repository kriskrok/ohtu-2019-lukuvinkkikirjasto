# ohtu-2019-miniprojekti :cyclone:
## [Lukuvinkkikirjasto](https://github.com/kriskrok/ohtu-2019-lukuvinkkikirjasto)
***

<p align="center">
  <img src="https://circleci.com/gh/kriskrok/ohtu-2019-lukuvinkkikirjasto.svg?style=svg" alt="CircleCI">
  <img src="https://codecov.io/gh/kriskrok/ohtu-2019-lukuvinkkikirjasto/branch/master/graph/badge.svg" alt="codecov">
  <img src="https://api.codeclimate.com/v1/badges/4f3aa250059799c84945/maintainability" alt="codeclimate">
</p>




#### [Product backlog](https://docs.google.com/spreadsheets/d/1LkLCp_9h6MrogtcsBB0s-k0wTKV7P8TSKj_3ccCMi-Y/edit?usp=sharing)

### Laadunhallinta

[Jatkuva integraatio (CircleCI)](https://circleci.com/gh/kriskrok/ohtu-2019-lukuvinkkikirjasto)

[Testikattavuus (codecov)](https://codecov.io/gh/kriskrok/ohtu-2019-lukuvinkkikirjasto)

[Ylläpidettävyys (codeClimate)](https://codeclimate.com/github/kriskrok/ohtu-2019-lukuvinkkikirjasto/maintainability)

### Ohjelman asennus- ja käyttöohje

Mikäli projektin lähdekoodi löytyy omalta koneelta, sovellus vaatii toimiakseen seuraavat loitsut:

- projektin juuressa: ```./gradlew run```
- selaimen osoiteriviin: [localhost:4567](http://localhost:4567)

Vaihtoehtoisesti 1. releasin yhteydessä olevan jar-tiedoston voi ladata ja sen jälkeen ajaa komennolla:

<pre><code> java -jar lukuvinkkikirjasto.jar</code></pre>

### Definition of Done - User Story

#### Määrittely ja suunnittelu:
- User story määritelty INVEST-mallilla
- Hyväksymiskriteerit on määritelty
- Story jaettu toteutettavissa oleviin teknisiin taskeihin
- Taskeille on nimetty tekijät
- Taskeille on arvioitu työaika
- Kaikki yllämainittu on dokumentoitu product backlogiin ja sprint backlogiin

#### Toteutus:
- Uuden toiminnallisuuden hyväksymiskriteerit täyttyvät 
- Sovelluksen ajaminen onnistuu toteuksen jälkeen ilman virheitä
- Tiimin jäsenet näkevät koko ajan koodin ja testien tilanteen CircleCI-palvelusta

#### Testaus:
- User storyssa toteutetulle ominaisuudelle on tehty automatisoidut yksikkötestit jUnitilla
- Storyn hyväksymiskriteerit on määritelty Cucumberin featureiksi
- Testien rivikattavuus tulee ominaisuudelle olla Jacocon mukaan vähintään 75 %
- Ominaisuudelle tehdyt testit menevät läpi
- Myös vanhat (regressio)testit menevät läpi, eli ominaisuus ei riko aiempaa koodia
- Checkstylen määritykset toteutuvat ilman virheitä

#### Vertaisarviointi: 
- Uuden ominaisuuden koodi katselmoitu vähintään yhden muun tiimiläisen toimesta
- Uusi ominaisuus katselmoitu ja testattu käytössä vähintään yhden muun tiimiläisen toimesta



<p align="center">
  <img src="http://www.thechristmasshop.co.uk/WebRoot/BT/Shops/BT4873/5510/1DAA/2F8B/66DF/A3BA/0A0C/05EE/762C/A0512013.jpg">
</p>

