#!/usr/bin/env bash

set -e

echo "==> Uninstalling Helm release..."

helm uninstall api -n portfolio

echo "==> Deleting namespace..."

kubectl delete namespace portfolio --wait=true

echo
echo "Uninstallation complete."