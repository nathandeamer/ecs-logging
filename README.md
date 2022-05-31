# ECS Logging

See [build.gradle](build.gradle)  
`implementation 'co.elastic.logging:logback-ecs-encoder:1.4.0'`

See [logback-spring.xml](src/main/resources/logback-spring.xml)

### Prerequisite:
- Rancher-Desktop with Kubernetes (k3s) enabled and working
 
### Build sample project
1. `./gradlew clean build`
2. `docker build --file=Dockerfile  --tag=logging:latest --rm=true .`

### Deploy sample project
1. `kubectl apply -f kube/deployment.yaml`
2. `kubectl apply -f kube/service.yaml`

### Test running:
The service.yaml will expose the service on port 8085
1. `curl localhost:8085/logging`

## Deploy elastic search / kibana / filebeat
1. Setup local-path-provisioner  
`kubectl apply -f https://raw.githubusercontent.com/rancher/local-path-provisioner/v0.0.22/deploy/local-path-storage.yaml`
2. Add elastic helm repo  
`helm repo add elastic https://Helm.elastic.co`
3. Install elasticsearch:
`helm install elasticsearch elastic/elasticsearch -f kube/elasticsearch/values.yaml`
4. Verify elasticsearch in browser: http://localhost:9200  
`kubectl port-forward svc/elasticsearch-master 9200` 
5. Install kibana:  
`helm install kibana elastic/kibana`
6. Verify kibana in browser: http://localhost:5601
`kubectl port-forward deployment/kibana-kibana 5601`
7. Install filebeat:  
`helm install filebeat elastic/filebeat -f kube/filebeat/values.yaml`
8. Setup filebeat as an index in kibana: http://localhost:5601/app/management/kibana/indexPatterns
9. Look at your shiny new logs in Kibana