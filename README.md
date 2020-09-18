# ProgettoOOP
Questa applicazione permette di collegarsi al proprio dropbox e scaricare a scelta delle cartelle in locale. Inoltre è possibile calcolare delle statistiche sui file contenuti nelle cartelle scaricate. In particolare, si può calcolare la dimensione minima, media e massima dei file divisi per tipo.

## Configurazione
Dopo aver clonato la repository, bisogna copiare il proprio token di dropbox ne file .env situato nel root del progetto nel formato
```
TOKEN=<token>
```

## Endpoints
* **POST /getdirectories  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce la lista dei file e delle cartelle contenuti nella cartella DropBox passata come parametro.
* **POST /downloaddirectory  - Required Parameter: {"path": "/path/to/folder"}**
Scarica in locale la cartella passata come parametro.
* **POST /getlocaldirectories  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce la lista dei file e delle cartelle contenuti nella cartella locale passata come parametro.
* **POST /delete_folder  - Required Parameter: {"path": "/path/to/folder"}**
Elimina la cartella in locale passata come parametro.
* **POST /getstatistics  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce le statistiche sulle dimensioni (minore, medie e massime) dei file contenuti nella cartella locale passata come parametro.
* **GET /get_metadata**
Restituisce i metadata.

## Diagrammi UML
