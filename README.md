# K8s Portfolio App

Yes, AI was used for setup, configuration, code, etc.

## Prerequisites
- [minikube](https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download)
- [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/)
- [k9s](https://k9scli.io/) or [k9s - Github](https://github.com/derailed/k9s/releases)
- [helm](https://helm.sh/)

## Informational Resources
- [Docker Local Images Minikube](https://www.baeldung.com/ops/docker-local-images-minikube)

### Docker
Install Docker and then to avoid typing "sudo docker"
add it to the usergroup (See [Docker: Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock](https://stackoverflow.com/questions/47854463/docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socke)).
```bash
sudo usermod -a -G docker $USER
grep docker /etc/group # You should see e.g, docker:x:998:[user]
```

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
