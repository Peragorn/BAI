# BAI
Bezpieczenstwo aplikacji internetowych

0. Aby działały Gettery i Settery niezbędne jest zainstalowanie lombok.jar (znajdziecie to w katalogu Other), po zainstalowaniu należy zrestartować IDE

-------------------------------------------------------------------------------------------------------------
1. Pobrać i zainstalować PostgreSQL wersja dowolna (ja mam 9.5) http://www.enterprisedb.com/products-services-training/pgdownload#windows
2. Instalując ustawić usera i password jako postgres (można inne ale wtedy w .cfg bedzie trzeba to uwzględnić)
3. Uruchomić pgAdmin, stworzyć bazę danych na localhost (domyślnie localhost:5432 i niech tak zostanie ) o nazwie  BAI_DATABASE
4. Połączyć się ze stworzoną bazą danych i dodać nowy schemat o nazwie bai

-------------------------------------------------------------------------------------------------------------
5. Przy pierwszym uruchamianiu aplikacji w pliku hibernate.cfg.xml odkomentować linijkę
<property name="hibernate.hbm2ddl.auto">create-drop</property>
a zakomentować
<property name="hibernate.hbm2ddl.auto">validate</property> 
6. kilka wpisów inicjalizujących jest w klasie InitDb, odpalamy ja Run As > Java Application

7. uruchomić aplikacje na serwerze (ja robię to na apache 7 )
8. http://localhost:8080/BAI/


Sprawozdanie
https://docs.google.com/document/d/1YbaOANb4KcjW3e4dAC_VcgPWrJzHxDSgW7H_oVGCbWw/edit?usp=sharing