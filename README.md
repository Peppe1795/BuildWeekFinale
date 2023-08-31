# BuildWeekFinale

Progetto build week finale basato su un CRM di una sociteà di fornitura di energia.
Utiliziamo Trello per la suddivisione dei compiti e Draw Io per il diagramma.
Utiliziamo GitHub desktop.
Utiliziamo Postman per simulare le chiamate Http.

Per strutturare il progetto lato back-end sono state utilizzate le seguenti tecnologie:

1. Java 17
2. Spring Boot
3. PostgreSQL

Dependency importate in Java:

1. Lombok
2. Spring Boot Dev Tools
3. PostgreSQL Driver
4. Spring Web
5. Spring Data JPA
6. Spring Security
7. JJWT: API
8. JJWT: IMPL
9. JJWT: EXTENTIONS: JACKSON

Per strutturare il progetto lato front-end sono state utilizzate le seguenti tecnologie:

1. Angular
2. Bootstrap

Dependency importate in Angular:

1. Auth-JWT
2. Concurrently

Nomenclatura:

1. Package per ogni classe/ ambito semantico.
2. nome package lower case.
3. nome classi upper case.
4. nome propietà classi in camel case.

\***\*\*\*\*\*\*\***\*\*\*\*\***\*\*\*\*\*\*\***\*\***\*\*\*\*\*\*\***\*\*\*\*\***\*\*\*\*\*\*\*** DOCUMENTAZIONE API \***\*\*\*\*\*\*\***\*\*\***\*\*\*\*\*\*\***\*\*\*\*\***\*\*\*\*\*\*\***\*\*\***\*\*\*\*\*\*\***

Endpoint per le chiamte http:

Generale: http://localhost:3001;

Per registare un utente:
Endpoint: http://localhost:3001/auth/register;

Esempio di body:

{
"nome": "Giuseppe ",
"cognome" : "Petrucci",
"username":"peppe95",
"email": "depaddd@fyahooh.com",
"password": "dadmddfmi12",
"role": "ADMIN"
}

Per la procedura di login:
Endpoint: http://localhost:3001/auth/login;

Esempio di body:

{
"username":"dedpf95",
"password": "dadmfmi12"
}

{
"email": "depaddd@fyahooh.com",
"password": "dadmddfmi12"
}

{
"username":"dedpf95",
"email": "depaddd@fyahooh.com",
"password": "dadmfmi12"
}

Esempio risposta token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0MDVmODIyMy0xMThlLTQ1NjQtODE3Ni1jNTgyMjc3YTllZDYiLCJpYXQiOjE2OTM0OTI3NTUsImV4cCI6MTY5NDA5NzU1NX0.cyuVarUTvltyvoNDxwnOQEpXIURMbn_GGk-ID1KC2DI;

******\*\*******\*\*\*\*******\*\*******\*\*\*******\*\*******\*\*\*\*******\*\*******Chiamate Clienti******\*\*\*\*******\*\*******\*\*\*\*******\*\*\*******\*\*\*\*******\*\*******\*\*\*\*******
GET:

1. ALL: http://localhost:3001/utenti/cliente;
2. Ordinati per Fattura annuale: http://localhost:3001/utenti/cliente/ordinati?ordinati=fatturato_annuale;
3. Ordinati per Ragione sociale: http://localhost:3001/utenti/cliente/ordinati?ordinati=ragione_sociale;
4. Ordinati per data inserimento: http://localhost:3001/utenti/cliente/ordinati?ordinati=data_inserimento;
5. Ordinati per ultimo contatto: http://localhost:3001/utenti/cliente/ordinati?ordinati=data_ultimoContatto;
6. Ordinati per sede legale: http://localhost:3001/utenti/cliente/ordinati?ordinati=provincia;

7) Filtrare clienti per fatturato annuale: http://localhost:3001/utenti/cliente/fatturato?fatturato=2826359.16;
8) Filtrare clienti per data inserimento: http://localhost:3001/utenti/dataInserimento?dataInserimento=2020-09-15;
9) Filtrare clienti per data ultimo contatto: http://localhost:3001/utenti/dataUltimoContatto?dataUltimoContatto=2020-07-24;
10) Filtrare per ragione sociale: http://localhost:3001/utenti/ragioneSociale?ragioneSociale=Vitale-Serr s.r.l.;
11) Filtrare per parte del nome: http://localhost:3001/utenti/parteDelNome?parteDelNome=E;
12) Filtrare per parte di regione sociale: http://localhost:3001/utenti/parteRagioneSociale?parteRagioneSociale=vil;

POST:
http://localhost:3001/utenti/cliente;

Esempio di body:

{
"ragioneSociale": "Vitale-Serr s.r.l.",
"partitaIva": 70852559110,
"email": "thea.ferri@yahoo.com",
"dataInserimento": "2020-09-15",
"dataUltimoContatto": "2021-03-22",
"fatturatoAnnuale": 2826359.16,
"pec": "egisto.ricci@example.com",
"telefono": "01714177805",
"emailContatto": "erminio.moretti@hotmail.com",
"nomeContatto": "Dindo",
"cognomeContatto": "Investor Accountability Director Riva",
"telefonoContatto": 3368067327,
"tipo_cliente": "PA",
}

PUT:
http://localhost:3001/utenti/cliente/{clienteID};

Esempio Body put:

{
"ragioneSociale": "Vitale-Serr s.r.l.",
"partitaIva": 70852559110,
"email": "thea.ferri@yahoo.com",
"dataInserimento": "2020-09-15",
"dataUltimoContatto": "2021-03-22",
"fatturatoAnnuale": 2826359.16,
"pec": "egisto.ricci@example.com",
"telefono": "01714177805",
"emailContatto": "erminio.moretti@hotmail.com",
"nomeContatto": "Dindo",
"cognomeContatto": "Investor Accountability Director Riva",
"telefonoContatto": 3368067327,
"tipo_cliente": "PA"
}

DELETE:

http://localhost:3001/utenti/cliente/{clienteID};

********\*\*********\*\*********\*\*********\*********\*\*********\*\*********\*\*********Chiamate Fatture******\*\*\*\*******\*\*\*\*******\*\*\*\*******\*\*\*******\*\*\*\*******\*\*\*\*******\*\*\*\*******

GET:

1)ALL: http://localhost:3001/utenti/fattura; 
2) Filtrate per stato fattura: http://localhost:3001/utenti/fattura/statoFattura?statoFattura=EMESSA: 
3) Filtrate per data: http://localhost:3001/utenti/fattura/data?data=2015-03-06; 
4) Filtrate per anno: http://localhost:3001/utenti/fattura/anno?anno=2015; 
5) Filtrate per cliente: http://localhost:3001/utenti/fattura/clienteId?clienteId=eb3fcc89-df65-4de5-b689-e4e705caf79f; 
6) Filtrate per range di importi: http://localhost:3001/utenti/fattura/importo?minImporto=4000&maxImporto=6000;

POST:

http://localhost:3001/utenti/fattura;

Esmpio di body:

{
"anno": 2015,
"data": "2015-03-06",
"importo": 6122.6,
"statoFattura": "ARCHIVIATA"
}

PUT:

http://localhost:3001/utenti/fattura/{numeroFattura};

Esmpio di body:

{
"anno": 2015,
"data": "2015-03-06",
"importo": 6122.6,
"statoFattura": "ARCHIVIATA"
}

DELETE: http://localhost:3001/utenti/fattura/{numeroFattura};

*************************************************************************Chiamate Utenti**********************************************************************
GET:

1) ALL: http://localhost:3001/utenti


PUT:

http://localhost:3001/utenti/{utentiId}

Esempio di bodi 

{ 

"nome": "Giuseppe ", 
"cognome" : "Petrucci", 
"username":"peppe95",
"email": "depaddd@fyahooh.com", 
"password": "dadmddfmi12", 
"role": "ADMIN" 

}

DELETE:

http://localhost:3001/utenti/{utentiId}