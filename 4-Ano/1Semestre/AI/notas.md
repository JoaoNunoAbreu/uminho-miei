# Notas

## Yellow Pages

### Register agent represented by the pingaponga agent in directory facilitator (DF)

```java
    // Faz-se no setup()

    super.setup()
    DFAgentDescription dfd = new DFAgentDescription();
    dfd.setName(getAID());
    ServiceDescription sd = new ServiceDescription();
    sd.setType("ping pong");
    sd.setName("JADE-replies-pong");
    dfd.addServices(sd);
    
    try{
        DFService.register(this,dfd);
    }
    catch(FIPAException e){
        e.printStackTrace();
    }   
```

### Get agent from yellow pages

```java 
    // Faz-se num behaviour

    DFAgentDescription template = new DFAgentDescription();
    ServiceDescription sd = new ServiceDescription();
    sd.setType("ping pong");
    template.addServices(sd);

    try{
        DFAgentDescription[] result = DFService.search(myAgent,template);
        pingpongAgents = new AID[result.length];
        for(int i = 0; i < result.length; i++){
            pingpongAgents[i] = result[i].getName();
        }
    }
    catch(FIPAException e){
        e.printStackTrace();
    }
```

---