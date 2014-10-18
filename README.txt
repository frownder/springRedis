
This test project simplifies a COC style multi user RTS Game called starworld.
  (COC:Clash of Clans)

service structre:
    Game Client <--(Http)-->  Memory Game Service <--> redis DB(master) <-->redis slave
                (Player Data)             (Player Data storing/caching for service&failover)

using:
    spring framework for service (simple RESTful style service using just GET method)
    redis (no-sql) for memory caching and recovery.
        - HA using sentinel and DI(Dependency Injection) on application-conifg.xml 
          (MySentinelConfiguration class is made for this purpose)
        - SDR(sping data for redis) and jedis connection pool        
    Jackson and JSON style object serialization for both redis backup and web service
        (JSON is better for web service and Jackson is faster than java object serialization)
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

