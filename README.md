# T23-G24
Requisiti sulla Registrazione ed Autenticazione dei Giocatori

<h2> Requisiti </h2>
<b>T2</b>: L’applicazione deve consentire agli studenti di registrarsi per poter conservare la storia delle attività svolte, oppure per accedere a requisiti di gioco più complessi. All’atto della registrazione, lo studente fornirà nome, cognome, un indirizzo e-mail valido ed una password, il sistema dopo aver controllato la validità dei dati forniti, aggiungerà il giocatore all’elenco dei giocatori registrati e gli assocerà un Id univoco. Sarebbe desiderabile raccogliere anche altre informazioni sugli studenti, come il corso di studi a cui sono iscritti (Bachelor, Master Degree, o altro). <br>
<b>T3</b>: All’atto della autenticazione, lo studente fornirà l’indirizzo e-mail fornito per la registrazione e la relativa password, il sistema dopo aver controllato la validità dei dati forniti, autenticherà il giocatore e gli fornirà una schermata per l’accesso alle funzionalità di gioco o di consultazione delle sessioni di gioco passate.

<h3> Storie utente </h3>
<ol> 
  <li> Come utente non registrato, voglio accedere alla pagina di registrazione dell’applicazione, in modo da potermi registrare.</li>
  <li> Come utente registrato, voglio accedere alla pagina di autenticazione dell’applicazione, in modo da potermi autenticare.</li>
  <li> Come utente registrato, voglio accedere alla pagina di password dimenticata dell’applicazione, in modo da poter recuperare l’account.</li>
  <li> Come admin, voglio accedere alla pagina di gestione utenti, in modo da poter visualizzare l’elenco degli utenti registrati. </li>
</ol>

<h2> Tool e tecnologie utilizzate per lo sviluppo </h2>
<ul>
  <li> <b>IDE e Linguaggio Implementativo</b>: Eclipse IDE, Java 17;</li>
  <li> <b>Database</b>: Database relazionale MySQL 8.0.33;</li>
  <li> <b>Build Automation</b>: Maven;</li>
  <li> <b>Supporto allo sviluppo e Servlet:</b> Spring Tools Suite 4, Spring Boot 3.1.0, Tomcat Apache, Lombok 1.18.26;</li>
  <li> <b>Pattern Architetturali</b>: Spring Web MVC;</li>
  <li> <b>Autenticazione</b>: Spring Security 6.1.0;</li>
  <li> <b>Accesso al Database</b>: Spring Data JPA; </li>
  <li> <b>Template Engine</b>: Thymeleaf; </li>
  <li> <b>Template</b>: HTML, CSS; </li>
  <li> <b>Servizio Email</b>: Spring Java Mail Sender; </li>
  <li> <b>Diagramma UML</b>: Visual Paradigm Community Edition; </li>
  <li> <b>Account Email</b>: Google; </li>
  <li> <b>Container</b>: Docker; </li>
  <li> <b> Strumento di Testing</b>: Postman v10.15 </li>
</ul>


<h3> Installazione su Docker </h3>

<p> Docker Desktop è un’applicazione che permette di istanziare ed eseguire dei container in un ambiente virtuale.
Nel nostro caso abbiamo creato due container: 
<lu>
  <li> Applicazione </li>
  <li> MySQL </li>
</lu>
</p>

