[![Build Status](https://travis-ci.com/mediathekview/zapp.svg?branch=master)](https://travis-ci.com/mediathekview/zapp)

![ZAPP](app/src/main/play/de-DE/listing/featureGraphic/funktionsgrafik.jpg)

1. [Über](#über)
2. [FAQ](app/src/main/res/raw/faq.md)
3. [Changelog](app/src/main/res/raw/changelog.md)
4. [Bibliotheken](#bibliotheken)

---------------------

## Über

Zapp gibt dir schnellen Zugang zu vielen deutschen öffentlich-rechtlichen Fernsehsendern. Schaue ARD, ZDF und andere Sender live oder aus der Mediathek. Zapp macht es einfach, schnell zwischen den Sendern zu wechseln.

**Achtung:** Wenn du Zapp außerhalb Deutschlands benutzt, können manche Sender – wie zum Beispiel ZDF – geblockt sein.

<a href="https://f-droid.org/repository/browse/?fdid=de.christinecoenen.code.zapp" target="_blank">
<img src="https://f-droid.org/badge/get-it-on.png" alt="Get it on F-Droid" height="80"/></a>

<img src="app/src/main/play/de-DE/listing/phoneScreenshots/02_live_video.png" width="49%" alt="video screen" /><img src="app/src/main/play/de-DE/listing/phoneScreenshots/06_mediathek_player.png" width="49%" alt="video screen" /><br>
<img src="app/src/main/play/de-DE/listing/phoneScreenshots/01_live.png" width="33%" alt="main screen" /><img src="app/src/main/play/de-DE/listing/phoneScreenshots/04_mediathek_search.png" width="33%" alt="arrange screen" /><img src="app/src/main/play/de-DE/listing/phoneScreenshots/05_mediathek_details.png" width="33%" alt="settings screen" />

---------------------

## Übersetzungen

Zapp wird in Deutsch entwickelt, kann aber über [Transifex](https://www.transifex.com/none-581/zapp-android-app/) in andere Sprachen übersetzt werden. Vorschläge für weitere Sprachen und Helfer beim Übersetzen sind herzlich willkommen!

## Bibliotheken

Zapp uses a lot of awesome open source libraries:
- [JUnit](http://junit.org/junit4/) to test things out
- [Gson](https://github.com/google/gson) to parse the list of channels
- [Commons IO](https://commons.apache.org/proper/commons-io/) for a sane way to handle files on Android
- [DragListView](https://github.com/woxblom/DragListView) to let you reorder the channel list intuitively
- [exo player](https://google.github.io/ExoPlayer/) the better video player
- [Retrofit](https://square.github.io/retrofit/) for easy server api communication
- [Joda time](http://www.joda.org/joda-time/) for sane date and time handling in Java
