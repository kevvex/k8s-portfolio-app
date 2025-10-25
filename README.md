# K8s Portfolio App

Yes, AI was used for setup, configuration, code, etc.

## Informational Resources
- [Docker Local Images Minikube](https://www.baeldung.com/ops/docker-local-images-minikube)
- [Install kubectl linux](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)
- [k9s](https://k9scli.io/)

## Installation via Helm/Docker/K8s
Build image and deploy
```bash
minikube image build -t api:latest -f Dockerfile . && \
helm install api ./charts -n portfolio
```

Do a port-forward to test it:
```bash
kubectl port-forward deployment/api 3000:3000 -n portfolio
curl http://localhost:3000/api
```

## Uninstallation via Helm/Docker/K8s
```bash
helm uninstall api -n portfolio
```

## Minikube commands
```bash
eval $(minikube -p minikube docker-env)
minikube status
minikube start
minikube image build -t api:latest -f Dockerfile.api .
minikube image ls --format table
minikube image rm docker.io/library/api:latest
```
