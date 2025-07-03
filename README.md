# Trabalho de Padrões de Projeto em Java

Este repositório contém exercícios práticos sobre padrões de projeto (Design Patterns) implementados em Java, demonstrando conceitos fundamentais de programação orientada a objetos e arquitetura de software.

## Execução Online (Alternativa)

**Não tem Java instalado?** Você pode copiar o código de qualquer exercício e executá-lo diretamente no navegador usando o OneCompiler:

🔗 **[https://onecompiler.com/java](https://onecompiler.com/java)**

Basta copiar o conteúdo do arquivo `Main.java` de qualquer exercício e colar no editor online. O código funcionará perfeitamente sem necessidade de instalação local do Java.

## Pré-requisitos

- **Java Development Kit (JDK)** versão 8 ou superior instalado no sistema
- Terminal ou prompt de comando funcional
- Permissões para executar scripts shell (em sistemas Unix/Linux/macOS)

Para verificar se o Java está instalado corretamente, execute:
```bash
java -version
javac -version
```

## Como executar os programas

Um script shell (`executarPrograma.sh`) foi criado para facilitar a execução dos programas. O script detecta automaticamente todas as pastas que contêm arquivos `Main.java` e apresenta um menu interativo organizado.

### Instruções de uso:

1. Abra um terminal na raiz do repositório
2. Torne o script executável (primeira vez apenas):
   ```bash
   chmod +x executarPrograma.sh
   ```
3. Execute o comando:
   ```bash
   ./executarPrograma.sh
   ```
4. Selecione o número do programa que deseja executar no menu
5. O script irá automaticamente:
   - Navegar para a pasta do exercício selecionado
   - Compilar o código Java (`javac Main.java`)
   - Executar o programa (`java Main`)
   - Limpar os arquivos `.class` gerados após a execução
   - Retornar à pasta raiz

### Funcionalidades do script:
- **Ordenação inteligente**: Exercícios numéricos aparecem primeiro (1, 2, 3...), seguidos pelos nomeados em ordem alfabética
- **Tratamento de erros**: Exibe mensagens claras em caso de falha na compilação ou execução
- **Limpeza automática**: Remove arquivos `.class` para manter o repositório limpo
- **Interface amigável**: Menu numerado com opção de saída

## Estrutura do Projeto

```
eng-de-soft-opt/
├── executarPrograma.sh          # Script de execução automática
├── README.md                    # Documentação (este arquivo)
├── 1/                          # Exercício 1: Logger Singleton
│   ├── Main.java
│   └── log.txt                 # Arquivo de log gerado
├── 2/                          # Exercício 2: Config Singleton
├── 3/                          # Exercício 3: Contador Singleton
├── 4/                          # Exercício 4: Formas Prototype
├── 5/                          # Exercício 5: Registry Prototype
├── 6/                          # Exercício 6: Deep Copy
├── command - controle/         # Sistema de Controle Remoto
├── observer - chat/            # Sistema de Chat
├── oberserver - noticias/      # Sistema de Notícias  
├── strategy - exemplo/         # Cálculo de Frete
└── iterator - lista/           # Lista com Iteradores
```

## Objetivos de Aprendizado (Lero-Lero)

Este projeto foi desenvolvido para demonstrar na prática:

### Conceitos Fundamentais
- **Padrões de Projeto (Design Patterns)**: Soluções reutilizáveis para problemas comuns
- **Princípios SOLID**: Especialmente Single Responsibility e Open/Closed
- **Programação Orientada a Objetos**: Encapsulamento, herança, polimorfismo
- **Gerenciamento de Estado**: Diferentes abordagens para controlar dados da aplicação

### Habilidades Técnicas
- **Implementação de padrões sem dependências externas**: Usando apenas Java puro
- **Thread Safety**: Sincronização em ambientes multi-thread
- **Gerenciamento de Memória**: Clonagem e referências de objetos
- **Arquitetura de Software**: Organização e estruturação de código

### Boas Práticas
- **Código Limpo**: Nomenclatura clara e estrutura organizada
- **Documentação**: Comentários e documentação adequada
- **Testabilidade**: Código estruturado para facilitar testes
- **Manutenibilidade**: Facilidade de modificação e extensão

## Tecnologias Utilizadas

- **Java 8+**: Linguagem principal
- **Shell Script**: Automação de execução
- **Padrões GoF**: Gang of Four Design Patterns
- **Terminal/Console**: Interface de usuário

## Contribuição e Extensão

Este projeto serve como base para:
- Estudos de padrões de projeto
- Implementação de novos padrões
- Comparação entre diferentes abordagens
- Exercícios práticos de programação orientada a objetos

Cada exercício pode ser executado independentemente e modificado para experimentação adicional.

## Exercícios implementados

Os exercícios estão organizados em duas categorias: exercícios numerados (1-6) que abordam padrões fundamentais, e exercícios nomeados que demonstram padrões comportamentais mais avançados.

### Padrões Criacionais

#### Exercício 1: Logger Singleton
- **Padrão**: Singleton
- **Descrição**: Sistema de logging que garante uma única instância para gravação em arquivo
- **Funcionalidades**:
  - Criação automática do arquivo `log.txt`
  - Timestamps automáticos em todas as mensagens
  - Sincronização thread-safe para múltiplas chamadas
  - Demonstração de que múltiplas referências apontam para a mesma instância
- **Conceitos demonstrados**: Controle de instância única, inicialização lazy, thread safety

#### Exercício 2: Gerenciador de Configuração Singleton
- **Padrão**: Singleton
- **Descrição**: Sistema de configurações globais compartilhadas entre diferentes módulos
- **Funcionalidades**:
  - Armazenamento de configurações chave-valor
  - Persistência de dados entre diferentes partes do programa
  - Simulação de módulos (login, configurações, reprodutor de mídia)
  - Demonstração de estado compartilhado
- **Conceitos demonstrados**: Estado global, persistência de dados, acesso unificado

#### Exercício 3: Contador Global de Instâncias
- **Padrão**: Singleton
- **Descrição**: Contador que monitora tentativas de criação de instâncias
- **Funcionalidades**:
  - Contagem de chamadas ao método getInstance()
  - Demonstração de estado global mantido
  - Verificação de unicidade da instância
- **Conceitos demonstrados**: Monitoramento de instâncias, estado interno preservado

#### Exercício 4: Hierarquia de Formas Geométricas
- **Padrão**: Prototype
- **Descrição**: Sistema de clonagem de formas geométricas sem usar interface Cloneable
- **Funcionalidades**:
  - Clonagem de objetos Triângulo, Elipse e Círculo
  - Hierarquia de classes com classe abstrata Figura
  - Clonagem independente (alterações no clone não afetam o original)
  - Demonstração de polimorfismo
- **Conceitos demonstrados**: Clonagem manual, herança, polimorfismo

#### Exercício 5: Registro de Protótipos
- **Padrão**: Prototype + Registry
- **Descrição**: Sistema de gerenciamento e registro de protótipos nomeados
- **Funcionalidades**:
  - Registro de protótipos com identificadores únicos
  - Clonagem sob demanda de protótipos registrados
  - Gerenciamento centralizado de objetos modelo
- **Conceitos demonstrados**: Registro de protótipos, factory method, gerenciamento de instâncias

#### Exercício 6: Clonagem Profunda (Deep Copy)
- **Padrão**: Prototype com Deep Copy
- **Descrição**: Demonstração de clonagem profunda de objetos complexos
- **Funcionalidades**:
  - Clonagem de documentos com objetos aninhados (Autor)
  - Cópia independente de objetos internos
  - Verificação de independência entre original e clone
- **Conceitos demonstrados**: Deep copy vs shallow copy, objetos compostos

### Padrões Comportamentais

#### Command - Sistema de Controle Remoto
- **Padrão**: Command
- **Descrição**: Sistema de automação residencial com controle remoto universal
- **Funcionalidades**:
  - Controle de diferentes dispositivos (luzes, TV, som)
  - Comandos com funcionalidade undo/redo
  - Macros (sequência de comandos)
  - Histórico de comandos executados
  - Interface unificada para diferentes tipos de dispositivos
- **Conceitos demonstrados**: Encapsulamento de comandos, desfazer operações, macro commands

#### Observer - Sistema de Chat
- **Padrão**: Observer
- **Descrição**: Sala de chat com notificações em tempo real
- **Funcionalidades**:
  - Múltiplos usuários conectados simultaneamente
  - Notificações automáticas de mensagens para todos os participantes
  - Histórico de mensagens com timestamps
  - Entrada e saída de usuários da sala
  - Diferentes tipos de usuários (normal, moderador, bot)
- **Conceitos demonstrados**: Notificação automática, loose coupling, eventos

#### Observer - Sistema de Notícias
- **Padrão**: Observer
- **Descrição**: Central de notícias com diferentes tipos de assinantes
- **Funcionalidades**:
  - Publicação de notícias por categoria
  - Assinantes com preferências específicas
  - Notificação automática baseada em interesse
  - Diferentes canais de comunicação
- **Conceitos demonstrados**: Publish-subscribe, filtros de interesse

#### Strategy - Cálculo de Frete
- **Padrão**: Strategy
- **Descrição**: Sistema de e-commerce com diferentes estratégias de cálculo de frete
- **Funcionalidades**:
  - Múltiplas estratégias de frete (normal, expresso, econômico)
  - Cálculo baseado em distância, peso e urgência
  - Descontos para clientes VIP
  - Taxas especiais para horários específicos
  - Comparação entre diferentes estratégias
- **Conceitos demonstrados**: Algoritmos intercambiáveis, polimorfismo, encapsulamento de estratégias

#### Iterator - Lista Personalizada
- **Padrão**: Iterator
- **Descrição**: Implementação de lista com múltiplos tipos de iteração
- **Funcionalidades**:
  - Iteração normal (sequencial)
  - Iteração reversa
  - Interface padrão do Java (Iterable)
  - Controle de acesso sequencial
  - Tratamento de exceções para acesso inválido
- **Conceitos demonstrados**: Iteração segura, múltiplos padrões de acesso, encapsulamento de estrutura
