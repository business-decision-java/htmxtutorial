# Hands-On Workshop zu HTMX, OBDS

## Teil 3 - Contacts App

### Motivation

In diesem Teil arbeitest Du mit einer einfachen "Contacts App", mit welcher Kontakte verwaltet werden können.
Im HTMX-Buch ["Hypermedia Systms"](https://hypermedia.systems/book/contents/) wird diese Anwenung [im Kapitel 3](https://hypermedia.systems/a-web-1-0-application/) als sogenannte "Web 1.0 Anwendung" realisiert, d.h. rein auf der Basis von Standard-HTML.
Wir (Markus, Thomas) haben diese Anwendung auf der Basis unserer Backend-Technologien Spring Web MVC + Thymeleaf nachimplementiert.
Du kannst unsere Version der "Web 1.0 Anwendung" nun als Ausgangsbasis nehmen, um sie selber mit HTMX-Elementen zu erweitern und insgesamt benutzerfreundlicher zu machen.
Mit unseren Aufgaben hier auf dieser Seite folgen wir exakt den Kapiteln 4 und folgende aus dem Buch ["Hypermedia Systms"](https://hypermedia.systems/book/contents/).
Wir haben den jeweiligen Abschnitt bei den Aufgaben verlinkt, so dass Du Dir bei Bedarf ausführliche Erklärungen holen kannst.

### Vorgehen

1. Starte die Spring Boot Applikation [Teil3ContactsApplication](src/main/java/ch/obds/tutorial/Teil3ContactsApplication.java)

2. Löse die nachfolgenden Aufgaben:
   - Wir haben in den Aufgaben je nur grob beschrieben, was der App hinzugefügt werden soll. Du kannst versuchen, zusammen mit der HTMX-Dokumentation das Ziel selber zu erreichen.
   - Falls Du aber lieber ausführliche Erklärungen und eine Schritt-für-Schritt-Anleitung befolgen möchtest, geben wir immer genau den Abschnitt im HTMX-Buch an, wo die nötigen Arbeitsschritte mit viel Hintergrundinformationen beschrieben sind. 


### Aufgabe 1: Zurechtfinden - schau Dir die Web 1.0 Anwendung erstmal genauer an

Schau Dir die Anwendung an, wie sie aktuell ausschaut.
Versuche, z.B. die einzelnen Bestandteile (HTML-Templates, Controller) zu finden und nachzuvollziehen. 


### Aufgabe 2: Installation von HTMX - Die Anwendung Contact App um HTMX erweitern  

Damit die Erweiterungen von HTMX in den HTML-Seiten überhaupt zur Verfügung stehen, muss die JavaScript Library von HTMX eingebunden werden.
Wie man das machen kann, findest Du u.a. im [Abschnitt "Installing"](https://htmx.org/docs/#installing) der Online-Dokumentation.

**Hinweise:**
- Damit man die Library nicht in jeder einzelnen Seite separat einbinden muss, bietet es sich an, dies im Basis-Template aller Seiten zu tun.
  In unserer Thymeleaf-Anwendung ist diese die Datei [layout.html](/src/main/resources/static/public/layout.html) 
- Für uns als Java-Entwickler interessant: Optional kann HTMX auch über ein sogenanntes *Webjar* als Maven-Dependency automatisch in das Projekt eingebunden werden: `<script type="text/javascript" th:src="@{/webjars/htmx.org/dist/htmx.min.js}"></script>`

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/htmx-in-action/#_installing_htmx


### Aufgabe 3: HTMX Boost der Anwendung

HTMX ist üblicherweise sehr explizit und jedes Attribut sehr einfach in dem was es tut.
Mit einer Ausnahme: Das Attribut `hx-boost` ein ziemlich "automagisches" Feature, welches eine klassische Web 1.0 Anwendung automatisch in eine AJAX-Anwendung verwandelt.
Eine Erklärung findest Du in der Anleitung: [hx-boos](https://htmx.org/docs/#boosting).

**Hinweise:**
- Am einfachsten setzt Du das `hx-boost` einmal im `body`-Element der Datei layout.html.

> Detaillierte Anleitung im Buch:</br>
hhttps://hypermedia.systems/htmx-in-action/#_adding_hx_boost_to_contact_app


### Aufgabe 4: Delete Button auf http-Delete Verb umbauen

Aktuell kann man einen Kontakt nur auf der Seite `edit.html` löschen.
Dazu gibt es im Editier-Formular neben "Save" einen zweiten Button "Delete", der einen `POST`-Request auf eine spezielle URL (/contacts/{id}/delete) auslöst, um den Kontakt zu löschen.
Gemäss http wäre dafür eigentlich das Verb `DELETE` auf der zu löschenden Ressource vorgesehen. Das unterstützt Standard-HTML aber nicht. Mit der Erweiterung HTMX hingegen dann schon.

Stelle den Button der Seite `edit.html` so um, dass er den angezeigten Kontakt mittels eines http `DELETE`-Requests auf die Ressource /contacts/{id} löscht.

**Hinweise:**
- Der Button *kann* nach der Umstellung weiterhin innerhalb des `form`-Elements sein, muss dies aber nicht, ausser das alte Delete soll noch als Fallback funktionieren, wenn z.B. JavaScript ausgeschaltet sein sollte.
- Der normale http Status Code `302 Found` für den Redirect auf die Liste aller Kontakte nach dem Löschen, führt dazu, dass der Browser das HTTP-Verb DELETE auch für das Holen der Kontakt-Auflistung verwenden würde (was unsinnig ist und diese Ressource gar nicht unterstützt). Deshalb muss für den Response nach dem Löschen der http Status Code `303 See other` gesetzt werden, da der Browser hier dann immer ein GET für den Redirect verwendet. [Details](https://hypermedia.systems/htmx-in-action/#_a_response_code_gotcha)
- Sorge dafür, dass HTMX die vom Browser angezeigte URL nach dem Löschen wieder auf die Liste der Kontakte anpasst ([hx-push-url](https://htmx.org/attributes/hx-push-url/)).
- Da Löschen nicht reversibel ist, soll zur Sicherheit ein Bestätigungsdialog vor dem Ausführen angezeigt werden ([hx-confirm](https://htmx.org/attributes/hx-confirm/))

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/htmx-in-action/#_a_second_step_deleting_contacts_with_http_delete


### Aufgabe 5: Serverseitige Validierung einer E-Mail-Adresse

Aktuell wird die E-Mail-Adresse im Formular der Seiten new.html und edit.html nicht gut validiert.
Es wäre wünschenswert, dass eine E-Mail-Adresse schon direkt während der Eingabe auf dem Server validiert wird und allfällige Probleme direkt während der Eingabe beim Eingabefeld angezeigt werden.

Stelle die Validierung des E-Mail Felds auf serverseitige Validierung während der Eingabe um.

**Hinweise:**
- Ist eine E-Mail-Adresse schon in einem anderen Kontakt vorhanden, so soll dies angezeigt werden.
- Die serverseitige Validierung soll ausgelöst werden:
  - Wenn der Wert des Feldes nach Veränderungen feststeht, z.B. beim Wechsel des Cursors in ein anderes Feld (Browser-Event [change](https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/change_event)) 
  - Während des Tippens: nach einem Tastendruck (Browser-Event [keyup](https://developer.mozilla.org/en-US/docs/Web/API/Element/keyup_event))
  - Nutze HTMX [Event-Modifier](https://htmx.org/attributes/hx-trigger/) für weitere Optimierungen: Tastendrücke sollen erst nach 200ms eine Validierung auslösen (`delay`) und auch nur dann, wenn sie den Input verändert (`changed`) haben.

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/htmx-in-action/#_next_steps_validating_contact_emails


### Aufgabe 6: Paging

Die Seite `index.html` listet im Moment *alle* Kontakte auf, was nur so lange gut funktionieren kann, wie die Anzahl der anzuzeigenden Kontakte nicht zu gross wird.
Das UI-Pattern *Paging* kann das Problem lösen, indem immer nur eine bestimmte Anzahl Records (z.B. 10) auf einmal angezeigt wird ("eine Seite"). Man kann dann auf weitere Seiten vor und zurück blättern.

Passe die HTML-Seite `index.html` so an, dass immer nur 10 Kontakte auf einmal angezeigt werden. Unter der Tabelle sollen zwei Links "Previous" und "Next" das Blättern ermöglichen.

**Hinweise:**
- Führe für Deine Lösung einen URL-Parameter `page` ein, welcher die anzuzeigende Seite enthält.
- Kleiner Umbau auf das UI-Pattern "Load more": Optional kannst Du Deine Lösung dann so anpassen, dass anstelle von "Previous"/"Next" unter der Tabelle nur ein Button "Load More" angezeigt wird, um weitere 10 Kontakte unter den bestehenden anzufügen. [Details](https://hypermedia.systems/htmx-in-action/#_click_to_load)
- Kleiner Umbau auf das UI-Pattern "Infinite Scroll": Optional kannst Du nun Deine Lösung einfach so anpassen, dass anstelle des Buttons "Load More" unter der Tabelle ein Nachladen automatisch ausgelöst wird, um weitere 10 Kontakte unter den bestehenden anzufügen, sobald das Ende der Tabelle sichtbar wird. [Details](https://hypermedia.systems/htmx-in-action/#_infinite_scroll)
- Für das "Infinite Scroll" passt vermutlich das Resultat des Controllers nicht mehr: Als Lösung darf man entweder serverseitig nur noch das Fragment mit den neuen Tabellenzeilen (`<tr>`-Elemene) anstelle der ganzen HTML-Seite liefern. Oder man verwendet [hx-select](https://htmx.org/attributes/hx-select/), um spezifisch nur ein Element aus dem Response zu extrahieren.
- Falls man auf `hx-select` verzichten will (z.B. weil so mit einer ganzen Seite eigentlich zu viel in den Browser übertragen wird), kann man die bisherige Controller-Operation intelligent machen, in dem sie rausfindet, ob sie über für Infinite Scroll nur ein Fragment mit genau den neuen Tabellenzeilen liefern soll oder die ganze Seite liefern soll (Initial-Load, Fallback, usw.). Hierzu kann serverseitig geprüft werden, ob im GET-Request der http-Header `HX-Trigger` gesetzt ist. Er zeigt bei einem von HTMX ausgelösten Request an, welches Element der Trigger ist.

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/htmx-in-action/#_another_application_improvement_paging


### Aufgabe 7: Interaktives Suchfeld - "Active Search"

Als etwas komplexere Funktion soll mit HTMX nun das UI-Pattern „Active Search“ realisiert werden.
"Active Search" bedeutet, dass die Ergebnisse einer Suche dynamisch angezeigt werden, während dem ein Benutzer Text in ein Suchfeld eingibt.

**Hinweise:**
- Im HTML wird "Active Search" sehr ähnlich wie die interaktive E-Mail-Validierung realisiert. Die Backend-Funktion ist natürlich eine andere.
- Im `hx-trigger` steht für ein HTML-Inputfeld vom Typ "search" ein Event namens `search` zur Verfügung. Dieses Event wird ausgelöst, wenn jemand die Sucheingabe löscht oder die Eingabetaste im Suchfeld drückt. Es kann anstelle von `change` verwendet werden.
- Sorge wiederum dafür, dass HTMX in der angezeigten Browser-URL den Suchparameter jeweils anpasst ([hx-push-url](https://htmx.org/attributes/hx-push-url/)).
- Optional kannst Du während des Ladens des neuen Suchresultats mit [hx-indicator](https://htmx.org/attributes/hx-indicator/) anzeigen, dass ein Request am Laufen ist. Verwende die Datei [spinning-circles.svg](/src/main/resources/static/public/images/spinning-circles.svg). Zum Ausprobieren kannst Du entweder mit den Dev-Tools des Browsers das Laden verlangsamen oder den Controller künstlich schlafen legen, damit Du den Spinner genug lange siehst.

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/more-htmx-patterns/#_active_search


### Aufgabe 8: Lazy Loading der totalen Anzahl Kontakte 

Es gibt Situationen, wo es sinnvoll sein kann, dass nicht von Anfang an die ganze HTML-Seite angezeigt wird.
Falls es z.B. Inhalte gibt, die erst mit etwas Verzögerung bereitstehen, kann es sinnvoll sein, mit dem UI-Pattern "Lazy Loading" zu arbeiten.
D.h. autonom und asynchron werden diese Inhalte separat geladen und erst angezeigt, sobald sie zur Verfügung stehen.

Zeige oberhalb der Tabelle mit den Kontakten die totale Anzahl Kontakte an und wende das Lazy Loading UI-Pattern an.

**Hinweise:**
- Damit die serverseitige Operation zum Zählen der Records schön lange läuft, kannst Du diese künstlich z.B. für 3 Sekunden schlafen lassen.

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/more-htmx-patterns/#_lazy_loading


### Aufgabe 9: Inline Delete als Action zu jedem Tabelleneintrag

Für unser nächstes Hypermedia-Feature werden wir das UI-Pattern „Inline Delete“ implementieren.
Mit dieser Funktion kann ein Kontakt direkt aus der Tabelle aller Kontakte gelöscht werden, ohne dass der Benutzer zuerst den ganzen Weg zur Bearbeitungsansicht eines bestimmten Kontakts navigieren muss.

In der Tabelle gibt es für jeden Eintrag bereits je einen Link/Button "View" und "Edit" für jeden Kontakt. Nun soll diesen Actions ein weiterer Link/Button "Delete" hinzugefügt werden, der foglendes auslöst:
1. Der Konakt wird aus den Daten gelöscht
2. Die Zeile mit diesem Kontakt "verschwindet" aus der Tabelle aller Kontakte. Und zwar soll wirklich nur genau diese Zeile entfernt werden - und nicht die ganze Tabelle neu geladen.

**Hinweise:**
- In den vorangehenden Aufgaben hast Du alle Elemente bereits kennen gerlent, welche Du für diese Aufgabe benötigst. Z.B. wie Du in der Server-Operation rausfinden kannst, wo das Löschen ausgelöst wurde, um den Response je nach Trigger-Element angepasst zu gestalten.
- Tipp: das Löschen einer Tabellenzeile könnte man auch auffassen als das Ersetzen des `<tr>...</tr>` Elements durch ... "nichts". ;-) 
- Etwas tricky kann es sein, im `hx-target` Attribut die zu löschende Zeile korrekt zu selektieren. Mit Hilfe der Doku und etwas Probieren sollte aber auch das machbar sein: [hx-target](https://htmx.org/attributes/hx-target/) 
- Am Schluss kannst Du die "User Experince (UX)" verbessern, indem die zu löschende Zeile innert einer Sekunde ausgeblendet wird und erst dann ganz gelöscht wird. Wie das geht, findest Du im [Animation Guide von HTMX](https://htmx.org/examples/animations/).

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/more-htmx-patterns/#_inline_delete


### Aufgabe 10 (schwierig und aufwändig): Archiv-Download

Falls Du noch eine weitere Aufgabe angehen möchtest, kannst Du Dich an das Realisieren einer Archiv-Download-Funktion heranwagen.
Diese soll asynchron implementiert werden.


**Hinweise:**
- Diese Aufgabe fehlt unserer Musterlösung noch. Machst Du einen Pull-Request...? ;-)
- Folge für diese Aufgabe den Schritten im Buch und "übersetze" den Backend-Code einfach jeweils nach Java/Spring MVC.

> Detaillierte Anleitung im Buch:</br>
https://hypermedia.systems/a-dynamic-archive-ui/