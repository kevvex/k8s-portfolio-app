# Portfolio Kubernetes Project

Yes, AI was used for setup, configuration, code, etc.

## Installation
- [Docker Local Images Minikube](https://www.baeldung.com/ops/docker-local-images-minikube)
- [Install kubectl linux](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

## Installation via Helm/Docker/K8s
Build and create the image in minikube
```bash
eval $(minikube -p minikube docker-env)
minikube status
minikube start
minikube image build -t api:latest -f Dockerfile.api .
minikube image ls --format table
minikube image rm docker.io/library/api:latest
```

Prerequisites deployment.yaml (Otherwise ImagePullBackOff)
```bash
imagePullPolicy: Never
```

## Deployment via Helm
Deploy with Helm
```bash
# Deploy installation
helm install api ./charts/api -n portfolio
kubectl get all -n portfolio
```

## Uninstallation via Helm
```bash
helm uninstall api -n portfolio
helm install api ./charts/api -n portfolio
```
