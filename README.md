# SRDVBP
Sistem de Recomandare a Destinațiilor de Vacanță pe Baza Preferințelor

Descriere Generală
Proiectul reprezintă un sistem de recomandare a destinațiilor de vacanță bazat pe preferințele utilizatorilor. Este o aplicație web care oferă utilizatorilor posibilitatea de a naviga prin destinații, de a oferi rating-uri, de a scrie comentarii și de a primi notificări în timp real despre activitatea din platformă.

Functionalități Implementate

1. Managementul Destinațiilor
   Afișarea a patru tipuri de vacanțe:
   Culturală: Londra
   În natură: Amazon
   Montană: Munții Bucegi
   La mare: Grecia
   Fiecare destinație dispune de:
   Carousel cu imagini și videoclipuri.
   Formular pentru comentarii și rating-uri.
   Rating mediu afișat cu două zecimale.

2. Sistem de Comentarii și Rating-uri
   Adăugare comentarii: Utilizatorii pot lăsa un comentariu cu numele lor și data completată automat.
   Rating: Utilizatorii pot acorda un rating între 1 și 5 stele.
   Vizualizare comentarii: Toate comentariile sunt afișate în ordine cronologică.
   Notificări: Administratorii primesc notificări în timp real atunci când este adăugat un comentariu.

3. Sistem de Administrare
   Panoul de administrare:
   Gestionarea destinațiilor (adăugare, ștergere).
   Gestionarea utilizatorilor (adăugare, ștergere).
   Gestionarea recenziilor (vizualizare și ștergere comentarii).
   Notificări WebSocket: Comentariile noi generează notificări ce pot fi vizualizate din panoul de administrare.

4. Notificări în Timp Real
   Implementare prin WebSockets:
   Atunci când este adăugat un comentariu, un mesaj de notificare în timp real apare pe ecranul administratorului.
   Mesajele pot fi accesate pentru informații suplimentare.
   Notificările sunt persistente până la acțiunea de închidere manuală de către administrator.

5. Securitate
   Acces restricționat:
   Panoul de administrare este accesibil doar utilizatorilor autentificați cu rol de administrator.
   Funcționalitățile normale sunt accesibile tuturor utilizatorilor.


   Tehnologii Utilizate
   Backend: Spring Boot, Spring Data JPA, Spring Security, WebSockets
   Frontend: Thymeleaf, HTML5, CSS3, JavaScript

   Baza de Date: MySQL
   Conexiuni în Timp Real: SockJS și STOMP

   Managementul Proiectului: Git
gi
   Arhitectura Aplicației
   MVC (Model-View-Controller): Aplicația este structurată pe baza arhitecturii MVC.
   Model: Clasele Destination, Comment, Rating, User.
   View: Pagini HTML create cu Thymeleaf.
   Controller: Administrează funcționalitatea aplicației prin clase precum HomeController, AdminController, CommentController, RatingController.
   Instrucțiuni de Utilizare
   Lansare Aplicație:
   Configurați baza de date în fișierul application.properties.
   Rulați aplicația folosind IntelliJ sau un terminal cu mvn spring-boot:run.
   Navigare:
   Utilizatorii pot accesa diferitele secțiuni ale aplicației pentru a vizualiza și interacționa cu destinațiile.
   Acces Administrativ:
   Conectați-vă cu un cont de administrator pentru a accesa funcțiile administrative.
   Posibile Dezvoltări Viitoare
   Filtrare avansată a destinațiilor:
   După buget, climă, activități preferate.
   Recomandări personalizate:
   Bazate pe activitatea utilizatorului.
   Persistența notificărilor:
   Stocarea notificărilor în baza de date.
   Galerie Multimedia:
   Încărcare dinamică de imagini și videoclipuri.
   Funcționalități sociale:
   Răspunsuri la comentarii, urmărirea altor utilizatori.
