## Pre-requisites

To build and run this project, install following softwares from EPAM's [Approved Software list](https://kb.epam.com/display/EPMSAM/Approved+Freeware)
1. JDK 11
2. IntelliJ IDEA CE
3. Git
4. Gradle
5. Maven (optional)

### Rule Engine Components
![Rule Engine](https://docs.drools.org/7.64.0.Final/drools-docs/html_single/HybridReasoning/rule-engine-inkscape_enterprise.png)

### Terminology
**Rules**
: It is a set of condition followed by set of actions. 
```
    Rule = Condition + Action
```
Conditions are also referred as Facts and Actions are consequences

**Facts**
: Facts are data on which the rules will act upon. From Java perspective, Facts are data objects.

**Production Memory**
: Location where rules are stored in the Drools engine.

**Working Memory**
: Location where facts are stored in the Drools engine.

**Agenda**
: Location where activated rules are registered and sorted (if applicable) in preparation for execution.

### Important Api Classes
![Class Diagram](https://www.tutorialspoint.com/drools/images/knowledge_builder.jpg)

**KnowledgeBase**
: KnowledgeBase is a collection of Rules. The main purpose of Knowledge Base is to store and reuse them because their creation is expensive. Knowledge Base provides methods for creating knowledge sessions.

**KnowledgeSession**
: KnowledgeSession is generated from KnowledgeBase. The KnowledgeSession can be two types: "Stateless Knowledge Session", "Stateful Knowledge Session"

| Stateless Knowledge Session |Stateful Knowledge Session|
|---|---|
| Validation      | Monitoring |
| Calculation      | Diagnostics |
| Filtering      | Logistics |
