
# Tools 

## Installation k9s
[k9s - Github](https://github.com/derailed/k9s/releases)

Ubuntu download of k9s:
```bash
# Verifies file integrity (not corrupted) and authenticity (not tampered with)
grep "k9s_linux_amd64.deb" checksums.sha256 | sha256sum --check

# Install k9s using apt
sudo apt install ./k9s_linux_amd64.deb 
```
