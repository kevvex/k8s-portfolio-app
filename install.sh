#!/usr/bin/env bash

set -e

echo "==> Building Docker image in Minikube..."
minikube image build -t api:latest -f Dockerfile .

echo "==> Installing Helm chart..."
helm install api ./charts \
    --namespace portfolio \
    --create-namespace

echo "==> Waiting for pods to become ready..."
kubectl wait \
    --for=condition=Ready \
    pod \
    --all \
    --namespace portfolio \
    --timeout=120s

echo
echo "==> Pods:"
kubectl get pods -n portfolio

echo
echo "Installation complete."
echo
echo "To test the API:"
echo "  kubectl port-forward deployment/api 3000:3000 -n portfolio"
echo "  curl http://localhost:3000/api"