# HelloCluster
## Overview
A demonstration of scaling an api through a Kubernetes cluster. 

This is not currently designed to also scale the database, as the replication settings have not been enabled. Database replication will come in future updates. 

## Build

### API Only
You can build the application and run it locally with the following.

```
mvn clean install spring-boot:run
```

### Kubernetes
Refer to the readme in the build directory to build the project and deploy it in Kubernetes.   