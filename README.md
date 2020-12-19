# simple-gradle-springboot-service

##
Tutorial
http://www.neilghosh.com/2019/02/quick-microservice-with-gradle-spring.html

## Containerize 

[Install Docker](https://store.docker.com/search?type=edition&offering=community)

[Install the pack tool (a CLI for running Buildpacks)](https://buildpacks.io/docs/install-pack/)

Buildpack

```
pack build --builder gcr.io/buildpacks/builder:v1 simple-gradle-springboot-service
```

Docker

```
docker build . -t neilghosh/gradle-spring-boot-app:latest
docker push neilghosh/gradle-spring-boot-app
```

RUN 
```
##Local
./gradlew run 
## Using docker
docker run --rm -p 8080:8080 simple-gradle-springboot-service 
```


## Deploy

Create an nginx controller which would be used as load balancer by the ingress 

```
helm install --name ingestion-ingress stable/nginx-ingress --set rbac.create=true
kubectl apply -f app-httpsdeploy.yaml
```
Now we need to access the app using the same domain name for which we have installed the certificate

Note: The IP it needs to resolve is the IP of the nginx controller which we can found by doing ```kubectl get services``` or one can use /etc/hosts to locally resolve the IP 
```
curl -v -k --resolve app.centralus.cloudapp.azure.com:443:<IP_OF_NGINX_CONTROLLER> https://appcentralus.cloudapp.azure.com
```
Create .pfx file 
```
openssl pkcs12 -export -out app-tls.pfx -inkey app-tls.key -in app-tls.crt
```
