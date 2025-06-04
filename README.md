# smart-city-platform
Microservizio per la simulazione e raccolta dati sensori

# Ispezionare il DB
Entrare interattivamente nella Mongo Shell
Se il tuo container si chiama mongodb, da terminale esegui:

docker exec -it mongodb mongosh
A questo punto sei dentro la shell di MongoDB e puoi usare questi comandi:

Mostrare tutti i database disponibili

show dbs
Ti restituirà l’elenco dei database e la loro dimensione su disco.

Selezionare il database
use smartcity

Mostrare tutte le collection nel database corrente
show collections

Controllare i documenti di una collection
db.sensor_data.find().pretty()

Se vuoi limitarti a pochi record:
db.sensor_data.find().limit(5).pretty()
Verificare lo schema di una collection

Se vuoi avere un’idea di quali campi ci siano, puoi eseguire:
db.sensor_data.findOne()

Uscire dalla shell
exit
