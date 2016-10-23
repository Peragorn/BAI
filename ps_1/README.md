# BAI
Bezpieczenstwo aplikacji internetowych

1. Pobrać i zainstalować PostgreSQL wersja dowolna (ja mam 9.5) http://www.enterprisedb.com/products-services-training/pgdownload#windows
2. Instalując ustawić usera i password jako postgres (można inne ale wtedy w .cfg bedzie trzeba to uwzględnić)
3. Uruchomić pgAdmin, stworzyć bazę danych na local host (domyślnie localhost:5432 i niech tak zostanie ) o nazwie  BAI_DATABASE
4. Połączyć się ze stworzoną bazą danych i dodać nowy schemat o naziwe bai

-------------------------------------------------------------------------------------------------------------
5. Przy pierwszym uruchamianiu aplikacji w pliku hibernate.cfg.xml odkomentować linijkę
<property name="hibernate.hbm2ddl.auto">create-drop</property>
a zakomentować
<property name="hibernate.hbm2ddl.auto">validate</property> 
6. uruchomienie aplikacji spowoduje stworzenie bazy danych
7. kilka wpisów inicjalizujących jest w klasie InitDb, odpalamy ja Run As > Java Application

8. uruchomić aplikacje na serwerze (ja robię to na apache 7 )
9. http://localhost:8080/ps_1/