# Hands-On Workshop zu HTMX, OBDS

## Motivation

Nachdem Oliver Nautsch uns an die Thematik "Server Side Rendering (SSR)" und dessen Verbesserung mit HTMX herangeführt hat, soll es nun darum gehen, das Besprochene selber praktisch ausprobieren zu können.
Hierzu haben wir einen einfachen Workshop entworfen, der Euch Spring Web MVC, Thymeleaf, HTMX und deren Kombination ausprobieren lässt.

## Struktur

Der Workshop ist als eine Art *"Leitfaden"* zu verstehen und soll Dir die Möglichkeit geben, weitgehend selbständig auszuprobieren.
Der Workshop folgt weitgehend dem Ansatz der "Baby Steps", damit die Komplexität tief gehalten werden kann und einzelnen Technologien verständlich bleiben.

Aufgeteilt ist der Workshop in **drei Teile**, die am sinnvollsten in dieser Reihenfolge angegangen werden sollten:


### Teil 1 - HTMX pur

Mit einfachen Sandbox-Beispielen kannst Du in diesem Teil die einzelnen Elemente von HTMX selber ein erstes Mal isoliert ausprobieren.

Starte diesen Teil im [Readme zum Teil 1](teil1htmx/readme.md)


### Teil 2 - Thymeleaf pur

Mit einfachen Sandbox-Beispielen kannst Du in diesem Teil die grundlegendsten Aspekte von Thymeleaf isoliert ausprobieren.

Starte diesen Teil im [Readme zum Teil 2](teil2thymeleaf/readme.md)


### Teil 3 - Contacts App als integriertes Beispiel

Hier liegt eine funktionierende einfache **"Web 1.0" Anwendung als Ausgangspunkt** vor.
Auf der Basis von Spring Web MVC und Thymeleaf werden in dieser Anwendung die Webseiten auf dem Server gerendert (Paradigma: "Server Side Rendering, SSR").
Die Anwendung entspricht der im Buch ["Hypermedia Systems"](https://hypermedia.systems/book/contents/) in Kapitel 3 beschriebenen "Web 1.0 Anwendung" - einfach mit anderen Backend-Technologien.

Du kannst diese einfache Anwendung nun selber schrittweise zu einer interaktiveren/verbesserten HTMX-Anwendung ausbauen.
Wir beschreiben in unserem Leitfaden eher zusammenfassend und grob was jeweils zu tun ist.
Falls Dir das nicht reicht, findest Du die jeweils erforderlichen Schritte zur Anpassung sehr detailliert beschrieben im Buch ab Kapitel 4.
In unserem Leitfaden verlinken wir immer den jeweils relevanten Abschnitt im Buch.    

*Anmerkung zum Buch:* Da im Buch serverseitig die Technologie "Flask" verwendet wird, müssen diese Teile auf unsere Technologien Spring Web MVC Controller und Thymeleaf "übersetzt" werden.

Starte diesen Teil im [Readme zum Teil 3](teil3contactsApp/readme.md)


> **Hinweis:**</br> Du findest für jeden der Teile 1 bis 3 je ein eigenständiges Maven-Modul mit je einer eigenen Spring Boot Anwendung.


