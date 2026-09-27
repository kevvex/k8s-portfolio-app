#!/usr/bin/env bash

set -euo pipefail

NAMESPACE=portfolio
VALUES_FILE="${VALUES_FILE:-charts/values.yaml}"
: "${MONGO_USERNAME:?Set MONGO_USERNAME before running install.sh}"
: "${MONGO_PASSWORD:?Set MONGO_PASSWORD before running install.sh}"

echo "==> Building Docker image in Minikube..."
minikube image build -t api:latest -f Dockerfile .

echo "==> Installing Helm chart..."
helm install api ./charts \
    --namespace "$NAMESPACE" \
    --create-namespace \
    --values "$VALUES_FILE" \
    --set-string "mongodb.auth.username=$MONGO_USERNAME" \
    --set-string "mongodb.auth.password=$MONGO_PASSWORD"

echo "==> Waiting for pods to become ready..."
kubectl wait \
    --for=condition=Ready \
    pod \
    --all \
    --namespace "$NAMESPACE" \
    --timeout=120s

echo
echo "==> Pods:"
kubectl get pods -n "$NAMESPACE"

echo
echo "Installation complete."
echo
echo "To test the API:"
echo "  kubectl port-forward deployment/api 3000:3000 -n $NAMESPACE"
echo "  curl http://localhost:3000/api"