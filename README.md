# SafetyNetAlerts

Path Json Datafile in the file application.properties to SafetyNetAlerts\src\main\resources

Endpoints :

http://localhost:8080/person Create person (POST)

http://localhost:8080/person Read all person (GET)

http://localhost:8080/person/1 Read person id 1 (GET)

http://localhost:8080/person/1 Update person id 1 (PUT)


http://localhost:8080/person/firstName/LastName Delete person(DELETE)

http://localhost:8080/medicalRecord Create medicalRecord (POST)

http://localhost:8080/medicalRecord Read all medicalRecord (GET)

http://localhost:8080/medicalRecord/1 Read medicalRecord id 1 (GET)

http://localhost:8080/medicalRecord/1 Update medicalRecord id 1 (PUT)

http://localhost:8080/medicalRecord/firstName/LastName Delete medicalRecord(DELETE)


http://localhost:8080/firestation Create firestation (POST)

http://localhost:8080/firestations Read all firestation (GET)

http://localhost:8080/firestation/1 Read firestation id 1 (GET)

http://localhost:8080/firestation/1 Update firestation id 1 (PUT)

http://localhost:8080/firestation/station/address Delete firestation(DELETE)


Alerts :

/childAlert?address=address

/communityEmail?city=city

/fire?address=address

/firestation?stationNumber=number

/flood/stations?stations=stations

/personInfo?firstName=firstName&lastName=lastName

/phoneAlert?firestation=numberFirestation