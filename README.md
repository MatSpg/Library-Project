# README - Library-Project

Benvenuto in questo repository del progetto di un sito sviluppato per una Libreria! Questo progetto è un'applicazione web che fornisce un sistema di gestione per una libreria, con funzionalità avanzate di gestione degli utenti e sicurezza integrata. Il back-end è sviluppato utilizzando Spring Boot con Spring Data JPA e Spring Security con aggiunta di JWT (Json Web Token), mentre il Front-end è realizzato con Angular.

## Contenuti

- [Requisiti](#requisiti)
- [Setup del Progetto](#setup-del-progetto)
- [Configurazione](#configurazione)
- [Esecuzione del Progetto](#esecuzione-del-progetto)
- [Struttura del Progetto](#struttura-del-progetto)
- [Contributi](#contributi)
- [Licenza](#licenza)

## Requisiti

Prima di iniziare, assicurati di avere installato:

- Java JDK 11 o versione successiva
- Node.js e npm
- Angular CLI
- E il database MySQL

## Setup del Progetto

1. Clona il repository sul tuo sistema locale:

   ```bash
   git clone https://github.com/MatSpg/Library-Project.git
   ```

2. Naviga nella cartella del progetto:

   ```bash
   cd library-project
   ```

3. Backend:
   - Configura il file `application.properties` nel percorso `backend/src/main/resources` con le tue impostazioni del database.

4. Frontend:
   - Installa le dipendenze Angular:

     ```bash
     npm install -g @angular/cli
     ```

## Configurazione

Per configurare il progetto, modifica i file di configurazione appropriati:

- `backend/src/main/resources/application.properties` per le impostazioni del database e altre configurazioni Spring Boot.

## Esecuzione del Progetto

1. Avvia il back-end (Spring Boot):

   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

Il Back-End sarà accessibile all'indirizzo `http://localhost:8080/` di default.

2. Avvia il front-end (Angular):

   ```bash
   cd frontend
   ng serve
   ```

Il Front-End sarà accessibile all'indirizzo `http://localhost:4200/` di default.

## Struttura del Progetto

Il progetto è organizzato nelle seguenti directory principali:

- `backend`: Contiene il codice sorgente del back-end basato su Spring Boot.
- `frontend`: Contiene il codice sorgente del front-end basato su Angular.
- `File Progetto`: Contiene il progetto per lo sviluppo del sito.

## Contributi

Siamo aperti ai contributi! Sentiti libero di aprire issue, proporre miglioramenti o inviare pull request.

## Licenza

Questo progetto è distribuito con licenza [MIT](LICENSE).