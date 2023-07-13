# T23-G24
Requisiti sulla Registrazione ed Autenticazione dei Giocatori

<h2> Requisiti </h2>
<b>T2</b>: L’applicazione deve consentire agli studenti di registrarsi per poter conservare la storia delle attività svolte, oppure per accedere a requisiti di gioco più complessi. All’atto della registrazione, lo studente fornirà nome, cognome, un indirizzo e-mail valido ed una password, il sistema dopo aver controllato la validità dei dati forniti, aggiungerà il giocatore all’elenco dei giocatori registrati e gli assocerà un Id univoco. Sarebbe desiderabile raccogliere anche altre informazioni sugli studenti, come il corso di studi a cui sono iscritti (Bachelor, Master Degree, o altro). <br>
<b>T3</b>: All’atto della autenticazione, lo studente fornirà l’indirizzo e-mail fornito per la registrazione e la relativa password, il sistema dopo aver controllato la validità dei dati forniti, autenticherà il giocatore e gli fornirà una schermata per l’accesso alle funzionalità di gioco o di consultazione delle sessioni di gioco passate.

<h3>Requisiti funzionali</h3>
<lu>
  <li> Il sistema deve consentire agli utenti di potersi registrare inserendo i propri dati personali e le credenziali di accesso. Un utente si registrerà al sistema inserendo nome, cognome, percorso di studi, università, email e password. All’atto della registrazione (temporanea), il sistema invierà all’utente un’email per consentirgli di confermare la registrazione. In seguito alla conferma, l’utente risulterà registrato ed eventualmente potrà autenticarsi. </li>
  <li> L’utente inserisce la propria e-mail e password per autenticarsi. Il sistema controlla la validità dei dati: se corretti, l’utente viene autenticato. </li>
    <li> Il sistema deve consentire all’utente autenticato di accedere alle funzionalità di gioco o alla pagina di consultazione delle sessioni passate. </li>
  <li> Il sistema deve consentire all’utente autenticato di effettuare il logout. </li>
<li> Il sistema deve consentire all’utente registrato di recuperare la password. </li>
<li> Il sistema deve consentire all’Admin di visualizzare un riepilogo di tutti gli utenti registrati al sistema. </li>	
</lu>

<h3> Funzionalità implementate: </h3>
<lu>
  <li> Login </li>
  <li> Registrazione </li>
  <li> Recupera Password </li>
</lu>

<h3> Tool e tecnologie impiegate: </h3>
<lu>
  <li> Spring Boot </li>
  <li> Spring MVC </li>
  <li> Spring Security </li>
  <li> Spring Data JPA </li>
  <li> Spring Mail </li>
  <li> MySQL DB </li>
  <li> Maven </li>
  <li> Lombok </li>
  <li> Tymeleaf </li>
  <li> Xampp </li>
  <li> Docker </li>
</lu>

<h3> Installazione su Docker </h3>

<p> Docker Desktop è un’applicazione che permette di istanziare ed eseguire dei container in un ambiente virtuale.
Nel nostro caso abbiamo creato due container: 
<lu>
  <li> Applicazione </li>
  <li> MySQL </li>
</lu>
</p>

