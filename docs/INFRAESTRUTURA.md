## Kubernetes
Ferramenta para deploy de aplicações em containers, com foco em orquestração de containers.

### Arquiterura do cluster
![Arquitetura do cluster](/images/KUBERNETES_ARQUITETURA.drawio.png)

### Serviço de Cloud
- AWS

#### AWS EKS
- Elastic Kubernetes Service

### Pipelines CI/CD
- Github Actions
  1. Verificar se envs existem no github.secrets
  2. Verificar se o commit é de uma branch de:
    - feature
    - release
    - hotfix
    - fix
    - config
    - tests
    - docs
    - refactor
    - style
    - chore.

  3. Verficiar se a aplicação esta "buildada"
  4. Verificar se a aplicação esta "deployada"
  5. Verificar se os testes estao passando
  6. Verificar se o coverage esta ok