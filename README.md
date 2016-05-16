<p align="center">
	<img src="https://assets-cdn.github.com/images/modules/site/personal-ill-learn.png" alt="ROR Resources Cover" style="max-width:1%;">
</p>

<h1 align="center">Agenda</h1>

<p align="center">Projeto de integração Eng. Software</p>

## :mortar_board: Integrantes

<a id="user-content-Índice" class="anchor" href="#Índice" aria-hidden="true"></a>
<p align="center">
	<a href="https://github.com/Wylianne" target="_blank">Wylianne Costa</a> •
</p>

## :computer: Atividades
Developer | Method | @Test | Descrição
:-- | :-- | :-- | :--
[ Wylianne Costa   ] | Get |  OK       | Exibe "Welcome";
[ Wylianne Costa   ] | Get/todos |  OK       | Exibe todos os cadastros;
[ Wylianne Costa   ] | Post/todos |  OK       | Inclui novo cadastro;


## :beginner: Requisitos 
* Install <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html" target="_blank">JDK 8+</a>
* Install <a href="http://maven.apache.org/" target="_blank">Maven 3+</a>
* Use <a href="https://daringfireball.net/projects/markdown/" target="_blank">Markdown 1.0.1</a>


## :fallen_leaf: Comandos Básicos (Criação do projeto - MVN)
Comando | Funcionalidade
:-- | :-- 
mvn archetype:generate -B -DgroupId=com.mycompany -DartifactId=my-app -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=jooby-archetype -DarchetypeGroupId=org.jooby -DarchetypeVersion=1.0.0.CR3 | Cria um novo projeto a partir do local onde esta sendo executado;
mvn jooby: run | Inicia o servidor local;


## :octocat: Comandos Básicos (Upando projeto - GitHub)
Comando | Funcionalidade
:-- | :-- 
git init | Inicia o Git;
git add . | Adiciona  todos os documentos locais ao GitHub;
git commit -m "mensagem de commit" | Efetua o commit;
git push nomedoSeuProjeto | Confirma o commit, em seguida solicita o usuario e senha;
git pull nomedoSeuProjeto master | Trás todas as modificações do GitHub para o computador local;
git config --global user.email seuEmail | Seta um email global para o computador;
git config --global user.name seuNome | Seta um nome global para o computador;


## A aplicação possui três métodos, são eles:
	* GET: Retorna "Welcome" no index;
	* GET/TODOS: Retorna a lista de contatos;
	* POST/TODOS: Adiciona um novo contato passando os dados(Nome e telefone) através de POST.


Para executá-la é preciso iniciar o servidor com o código que está na tabela "Comandos Básicos (Criação do projeto - MVN)".
Com o servidor iniciado acessamos a aplicação através de url.
## :octocat: Acessando aplicação
Método | URL | Corpo | Resposta
:-- | :-- | :-- | :-- 
GET | http://localhost:8080/ | -- |"Welcome";
GET | http://localhost:8080/todos | -- |[{id: 1, name: "nome1", phone: "6798090"}, {id: 2, name: "nome2", phone: "546789"} .. {id: n, name: "nomen", phone: "456789"}];
GET | http://localhost:8080/todos/:id | {id: 1} |[{id: 1, name: "nome1", phone: "6798090"}];
POST | http://localhost:8080/todos | {name: "nome1", phone: "6798090"} | -- ;

