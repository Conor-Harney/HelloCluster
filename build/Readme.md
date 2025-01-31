# Table of Contents
<!-- TOC -->
* [Table of Contents](#table-of-contents)
* [Pre-requisites](#pre-requisites)
  * [Windows](#windows)
  * [Docker](#docker)
  * [Kubectl](#kubectl)
  * [Minikube](#minikube)
* [Preparing the Image](#preparing-the-image)
  * [Build The Repo](#build-the-repo)
  * [Create The Image From The Repo](#create-the-image-from-the-repo)
* [Running On Kubernetes](#running-on-kubernetes)
  * [Set up the ingress controller](#set-up-the-ingress-controller)
  * [Deploy The App](#deploy-the-app)
* [Exposing The App](#exposing-the-app)
  * [Expose Through The Service](#expose-through-the-service)
  * [Expose Through an Ingress](#expose-through-an-ingress)
    * [Create a Service to Select the Pods](#create-a-service-to-select-the-pods)
    * [Create The Ingress](#create-the-ingress)
* [Troubleshooting](#troubleshooting)
  * [I need to start again](#i-need-to-start-again)
  * [Image not found](#image-not-found)
<!-- TOC -->

# Pre-requisites
## Windows
The following is one simple Windows centric approach. Steps, particularly in relation to minikube tunnel, will differ on different systems. 

## Docker
For local deployments, ensure docker desktop is installed and started

## Kubectl
Ensure kubectl is installed.

## Minikube
Minikube will need to be installed. After installation, you will need to run minikube and enable the ingress plugin.

```
minikube status
minikube start
kubectl config use-context minikube
```

# Preparing the Image
## Build The Repo

```
mvn clean install
```

## Create The Image From The Repo

From project root directory
```
docker build -t hello-cluster-image:latest -f build/Dockerfile .
minikube image load hello-cluster-image:latest
```

# Running On Kubernetes
## Set up the ingress controller

```
minikube addons enable ingress
kubectl wait --namespace ingress-nginx --for=condition=ready pod --selector=app.kubernetes.io/component=controller --timeout=120s
kubectl -n ingress-nginx get po
```

## Deploy The App

```
kubectl apply -f ./build/namespace.yml
kubectl apply -f ./build/deployment.yml
kubectl get po -n hello-cluster-namespace
```

# Exposing The App

## Expose Through The Service

If you don't wish to add an ingress resource, you can expose the service directly as follows. Otherwise, follow the "Expose through ingress" step

```
kubectl expose deployment app-deployment -n hello-cluster-namespace --type=LoadBalancer --port=41010
kubectl get svc -n hello-cluster-namespace app-deployment
minikube svc -n hello-cluster-namespace app-deployment --url
```

The minikube command will expose the service on a port that can then be used to access the service.

```
PS C:\Workspace\repos\HelloCluster> minikube service -n hello-cluster-namespace app-deployment --url
http://127.0.0.1:51174
! Because you are using a Docker driver on windows, the terminal needs to be open to run it.
```

Then in a different window you can curl the service:

```
C:\Users\Admin>curl 127.0.0.1:51174/api/hello
{"message":"Hello Cluster."}
```

## Expose Through an Ingress
### Create a Service to Select the Pods

```
kubectl apply -f ./build/service.yml
```

You may ensure the pods were correctly picked up by checking the 'Endpoints' while describing the service.   

```
kubectl get svc -n hello-cluster-namespace
kubectl describe svc -n hello-cluster-namespace api-service  | findstr /c:"Endpoints"
```

### Create The Ingress

```
kubectl apply -f ./build/ingress.yml
kubectl get ing -n hello-cluster-namespace
```

For ease of use with windows, you can use minikube to create a tunnel to access the ingress.

```
minikube tunnel
```

This should open a tunnel for your app-ingress ingress

```
C:\Users\Admin>minikube tunnel
* Tunnel successfully started

* NOTE: Please do not close this terminal as this process must stay alive for the tunnel to be accessible ...

! Access to ports below 1024 may fail on Windows with OpenSSH clients older than v8.1. For more information, see: https://minikube.sigs.k8s.io/docs/handbook/accessing/#access-to-ports-1024-on-windows-requires-root-permission
* Starting tunnel for service app-ingress.
```

To avoid messing with the hosts file, you can resolve the host to a loopback in the curl command.

```
curl --resolve "hello-cluster.ie:80:127.0.0.1" -i http://hello-cluster.ie/api/hello
```

# Troubleshooting
## I need to start again

The following steps will clear your environment down to deleting the minikube container. 
This is a useful reference to clean up fresh install environment, but **do not run this on an environment where other artifacts may use minikube or the ingress-nginx namespace**. 

```
kubectl delete namespace hello-cluster-namespace
kubectl delete namespace ingress-nginx
minikube delete
```

## Image not found

If the image was built but is not being picked up, you may need to cache it to the current context.
First check your current context:

```
kubectl config get-contexts
```

If you want to change context you can use the following command.

```
kubectl config use-context <context>
```

If you are using minikube context, and it is not picking up the image, you can cache it from docker local as follows:

```
minikube image load hello-cluster-image:latest
```

