# CRUD Spring Boot

## Descrição
Este projeto é um exemplo simples de um aplicativo CRUD (Create, Read, Update, Delete) utilizando Spring Boot, focado na entidade `Aluno`. Ele serve como um ponto de partida para aqueles que estão aprendendo a construir aplicativos usando Spring Boot e tecnologias relacionadas.

## Tecnologias Utilizadas
- Spring Boot
- Maven
- Java
- Thymeleaf (para templates HTML)

## Estrutura do Projeto
- `src/main/java/net/aarruda/crud/controller/`: Contém os controladores do projeto, como [AlunoController.java](https://github.com/Verotic/crud-spring/blob/main/Projeto%20CRUD%20(Spring)/src/main/java/net/aarruda/crud/controller/AlunoController.java).
- `src/main/java/net/aarruda/crud/entity/`: Contém as entidades do projeto, como [Aluno.java](https://github.com/Verotic/crud-spring/blob/main/Projeto%20CRUD%20(Spring)/src/main/java/net/aarruda/crud/entity/Aluno.java).
- `src/main/java/net/aarruda/crud/service/`: Contém os serviços do projeto, como [AlunoService.java](https://github.com/Verotic/crud-spring/blob/main/Projeto%20CRUD%20(Spring)/src/main/java/net/aarruda/crud/service/AlunoService.java).
- `src/main/java/net/aarruda/crud/repository/`: Contém os repositórios do projeto, como [AlunoRepository.java](https://github.com/Verotic/crud-spring/blob/main/Projeto%20CRUD%20(Spring)/src/main/java/net/aarruda/crud/repository/AlunoRepository.java).
- `src/main/resources/`: Contém recursos como templates e arquivos de configuração, como [application.properties](https://github.com/Verotic/crud-spring/blob/main/Projeto%20CRUD%20(Spring)/src/main/resources/application.properties).

## Funcionalidades
- Create: Adiciona um novo registro de aluno.
- Read: Lê e lista registros de alunos existentes.
- Update: Atualiza registros de alunos existentes.
- Delete: Remove registros de alunos existentes.
