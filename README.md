# Trabalho de Padrões de Projeto em Java

Este repositório contém exercícios práticos sobre padrões de projeto implementados em Java.

## Como executar os programas

Um script shell (`executarPrograma.sh`) foi criado para facilitar a execução dos programas. O script permite selecionar e executar qualquer um dos exercícios através de um menu interativo.

### Instruções de uso:

1. Abra um terminal na raiz do repositório
2. Execute o comando:
   ```bash
   ./executarPrograma.sh
   ```
3. Selecione o número do programa que deseja executar no menu
4. O script irá:
   - Compilar o código Java (`javac Main.java`)
   - Executar o programa (`java Main`)
   - Limpar automaticamente os arquivos `.class` gerados após a execução

## Exercícios implementados

### Padrão Singleton

#### Exercício 1: Logger Singleton
- **Descrição**: Implementação de uma classe Logger que escreve mensagens em um arquivo de log.
- **Objetivo**: Garantir que apenas uma instância dessa classe exista durante toda a execução.
- **Funcionalidade**: Várias chamadas do Logger usam a mesma instância, garantindo operações de log consistentes.

#### Exercício 2: Gerenciador de Configuração
- **Descrição**: Implementação de uma classe Config Singleton que armazena configurações globais.
- **Objetivo**: Demonstrar persistência de dados em uma única instância.
- **Funcionalidade**: Modificações nas configurações em uma parte do programa se refletem em todo o sistema.

#### Exercício 3: Contador Global de Instâncias
- **Descrição**: Classe InstanciaUnica que conta quantas vezes diferentes objetos tentaram ser criados.
- **Objetivo**: Reforçar o conceito de manter estado global mesmo com múltiplas chamadas ao construtor.
- **Funcionalidade**: Sempre retorna a mesma instância e conta o número total de requisições.

### Padrão Prototype

#### Exercício 4: Hierarquia de Formas com Prototype
- **Descrição**: Implementação de uma hierarquia de formas geométricas com método de clonagem.
- **Objetivo**: Demonstrar o padrão Prototype sem usar a interface Cloneable.
- **Funcionalidade**: Criar cópias independentes de objetos Triangulo e Elipse.

#### Exercício 5: Registro de Protótipos
- **Descrição**: Classe RegistroDeProtótipos que armazena e gerencia formas protótipos.
- **Objetivo**: Implementar um sistema de registro e recuperação de protótipos.
- **Funcionalidade**: Permitir registrar protótipos com nome e cloná-los conforme necessário.

#### Exercício 6: Prototype com Deep Copy
- **Descrição**: Implementação de classe Documento com clonagem profunda.
- **Objetivo**: Demonstrar o conceito de deep copy no padrão Prototype.
- **Funcionalidade**: Garantir que objetos internos (como a classe Autor) sejam clonados corretamente.
