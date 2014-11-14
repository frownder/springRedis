
This test project simplifies a COC style multi user RTS Game called starworld.
    and two simple web page for fronend is added.     (COC:Clash of Clans) 

Service structre:
    Game Client <---> Game Service <--> redis DB(master) <-->redis slave
            (Player Data)              (Data caching for failover)

Frontend using:
    spring mobile framework - discrimination mobileWeb from web  
        on RedisController.java 

    files:
        list.jsp(Web) -simple web page using JSTL, jQuery
        listMobile.jsp(mobileWeb)- differentaiate screen size using:
             <meta viewport> & CSS3 @media screen Queury 


Backend using:
    spring framework -  RESTful style service 
    redis (no-sql) for memory caching and recovery.
        - HA using sentinel and DI(Dependency Injection) on conifg xml 
          (MySentinelConfiguration class is made for this purpose)
        - SDR(sping data for redis) and jedis connection pool        
    Jackson and JSON object serialization for both redis cache & web service
        (JSON for WebService & Jackson for faster serialization)

    AspectJ(AOP) for measuring method running time 
    Junit for class unit test
    HtmlUnit for web service test

    Object Oriented Class design for polymorphic BionicUnit
                   BionicUnit
                     /   \
                    /     \
               AttackUnit  Medic
                 / | \
                /  |  \
               /   |   \
           Marine Ghost Firebat

