# BAZOŠ WEB ANALYZER

**BWA** je webová aplikácia vytvorená za účelom analyzovania cien **Apple** produktov na webstránke [bazos.sk](https://pc.bazos.sk). Aplikácia bola vytvorená za účelom splnenia podmienok predmetu **VPA - vývoj pokročilých aplikácií** a taktiež pre osobné účeli, vzhľadom na to, že sa venujem kúpe,predaju a servisu Apple zariadení.

**BWA** slúži na vytvorenie grafu z dostupných inzerátov. Tento graf sa následne upraví podľa užívateľských preferencií. Užívateľ tak získa trhovú cenu konkrétnej položky. Výsledok, ktorý sa zobrazuje užívateľovi je teda graf obsahujúci ceny a popisy jednotlivých inzerátov, najnižšiu, priemernú a najvyššiu cenu vyhľadávanej položky na trhu.

# VYUŽITIE

**BWA** je konkrétne naprogramované aby napr. filtre, vyhľadávanie + frontend vyhovoval Apple produktom. S miernou úpravou kódu, je však možné analyzovať akúkoľvek kategóriu bazošu. Tejto problematike sa plánujem venovať v budúcej verzii aplikácie.

## Ako to funguje ?

**JEDNODUCHO.** Aplikácia obsahuje metódu **init()** v triede **InitService** na počiatočné naplnenie databázy inzerátmi, táto metóda si ako parameter pýta číslo. Toto číslo je počet stránok, ktoré aplikácia reálne na bazoši prezrie. Odporúčaný počet stránok je **1-15.**

**BWA** sťahuje zdrojový HTML kód stránky bazošu. Tento kód následne rozparsuje pomocou regulárnych výrazov. Z týchto dát vytvorí objekty typu **Item** a uloží ich do MySQL databázy. **BWA** ďalej na základe užívateľskej voľby s týmito dátami ďalej pracuje.

## MySQL Nastavenia
> Nastavenie minimálnej dĺžky slova pre MySQL -> **my.ini (XAMPP)**

    [mysqld]  
    ft_min_word_len = 1

> Konverzia databázy na engine MyISAM pre podporu vyhľadávania **FULLTEXT**

    SET storage_engine=MYISAM;
    ALTER TABLE table_name ENGINE = MyISAM;

## application.properties

**application.properties** je súbor, pomocou ktorého môžete nastavovať vlastnosti vašej aplikácie. Pred spustením **BWA** je nutné vytvoriť si vlastnú MySQL databázu a nakonfigurovať prístupové do danej databázy v konfiguračnom súbore **application.properties.**

    #DATABASE
    bazos.database.jdbc=com.mysql.jdbc.Driver
    bazos.database.url=jdbc:mysql://localhost:3306/java_bazos
    bazos.database.username=root
    bazos.database.password=
    
    #PROPORTIES FOR IPHONES
    items.select.models=3G,3GS,4,4S,5,5C,5S,SE,6,6+,6S,6S+,7,7+,8,8+,X
    items.select.capacity=8,16,32,64,128,256
    items.select.colour=Silver,Space gray,Rose Gold,Jet Black,Gold

    #INIT  
    init.populateDB=false  
    init.pages=15  
      
    #DBHANDLER  
    dbhandler.filter=displej,vymenim,kryt,púzdro,display,puzdro,zakladna,doska,pokazeny,rozbity,nefunkcny,prasknuty,blokovany,zablokovany,sklo,bateru,tvrdene,ochranne,bateria,krabica,krabice,kabel,kable,

 - **#DATABASE** v tejto kategórií nahraďte existujúce údaje vašimi údajmi o databáze
 - **#PROPORTIES FOR IPHONES** obsahuje dostupné modely, kapacity a farby Apple produktov
 - **#DBHANDLER** filter je pole filtrovaných slov, ktoré vyhľadávač využíva na filtrovanie inzerátov

## #INIT

>Prvé spustenie aplikácie:

 

- je nutné spustiť s hodnotou `init.populateDB=true`
- nastavenie počtu strán inzerátov, ktoré sa majú načítať do databázy `init.pages={počet strán}` 

    
 >Opakované spustenie aplikácie:

 

- je nutné spustiť s hodnotou `init.populateDB=false`
- `init.pages={počet strán}` už meniť nemusíme, tento údaj sa už viac nepoužíva
