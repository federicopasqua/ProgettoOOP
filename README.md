# ProgettoOOP
Questa applicazione permette di collegarsi al proprio dropbox e scaricare a scelta delle cartelle in locale. Inoltre è possibile calcolare delle statistiche sui file contenuti nelle cartelle scaricate. In particolare, si può calcolare la dimensione minima, media e massima dei file divisi per tipo.

## Configurazione
Dopo aver clonato la repository, bisogna copiare il proprio token di dropbox nel file .env situato nel root del progetto nel formato
```
TOKEN=<token>
```

## Endpoints
* **POST /getdirectories  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce la lista dei file e delle cartelle contenuti nella cartella DropBox passata come parametro.
<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/diagramma%20interazioni-getdirectories.jpg" width="600"/>

* **POST /downloaddirectory  - Required Parameter: {"path": "/path/to/folder"}**
Scarica in locale la cartella passata come parametro.
<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/diagramma%20interazioni-downloaddirectory.jpg" width="600"/>

* **POST /getlocaldirectories  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce la lista dei file e delle cartelle contenuti nella cartella locale passata come parametro.
<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/diagramma%20interazioni-getlocaldirectories.jpg" width="600"/>

* **POST /delete_folder  - Required Parameter: {"path": "/path/to/folder"}**
Elimina la cartella in locale passata come parametro.
<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/diagramma%20interazioni-delete_folder.jpg" width="600"/>

* **POST /getstatistics  - Required Parameter: {"path": "/path/to/folder"}**
Restituisce le statistiche sulle dimensioni (minore, medie e massime) dei file contenuti nella cartella locale passata come parametro.
<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/diagramma%20interazioni-getstatistics.jpg" width="600"/>

* **GET /get_metadata**
Restituisce i metadata.

## Diagrammi UML
### Diagramma dei casi d'uso

<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/Casi%20d'uso.jpg" width="400"/>

### Diagramma delle classi

<img src="https://raw.githubusercontent.com/federicopasqua/ProgettoOOP/master/UML/Diagrammi%20delle%20classi.gif" width="700"/>

## Autore

* **Federico Pasqualini** - [GitHub](https://github.com/federicopasqua)

