<h1 align="center">
   <a href="https://ianmateuses.github.io/bodybuilder/"><img alt="Logo" src="./.github/assets/logo-p.png"></a>
</h1>
<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/ianmateusES/bodybuilder.svg">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/ianmateusES/bodybuilder.svg">
  
  <a href="https://github.com/ianmateusES/bodybuilder/commits/master">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/ianmateusES/bodybuilder.svg">
  </a>

  <a href="https://github.com/ianmateusES/bodybuilder/issues">
    <img alt="Repository issues" src="https://img.shields.io/github/issues/ianmateusES/bodybuilder.svg">
  </a>

  <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
</p>

<p align="center">
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-documentação-do-projeto">Documentação</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
   <a href="#-site">Site do sistema</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-como-contribuir">Como contribuir</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-licença">Licença</a>
</p>

<br>

<p align="center">
  <img alt="Frontend" src="./docs/.github/bodybuilder.png" width="100%">
</p>

## 🚀 Tecnologias

Esse projeto foi desenvolvido com as seguintes tecnologias:

<!-- [Node.js](https://nodejs.org/en/) -->
- [Java](https://www.oracle.com/br/java/technologies/)
<!-- - [Electron](https://www.electronjs.org) -->

## 💻 Projeto

O Bodybuilder é um software de auxílio a personais e nutricionistas no acompanhamento detalhado da evolução de seus alunos, com elaboração de treinos e dietas personalizados baseados em suas estatísticas. O aluno acessa sua série e acompanha todas as orientações dadas pelo personal, assim como ele pode acessar sua dieta orientada pelo nutricionista. O aplicativo é prático, que foi criado para descomplicar a gestão de alunos em qualquer local que esteja.

<a href="https://www.youtube.com/watch?v=8Fmom2yW-2o" target="_blank" align="center">
  <img src="./docs/.github/youtube.jpeg">
</a>

## 🤖 Execução
Para execução do projeto, primeiro é preciso executar o banco de dados para que o aplicação possa se comunicar. Uma opção para o banco de dados, é rodar uma imagem docker do postgres. 
```
   docker run --name bodybuilder -e POSTGRES_USER=body -e POSTGRES_PASSWORD=123456 -p 5432:5432 -d postgres:13-alpine
```
Depois, criar as tabelas dentro da container gerado usando o sql que se encontra em `./frontend/bd/table_bd.sql`. E executar o executavel da aplicação que se encontra em `./executable/body.jar` .

## 📁 Documentação do projeto
  
  <a href="./.github/documents/">Súmario de documentação do projeto</a>

## 🔗 Site
  
  <a href="https://ianmateuses.github.io/bodybuilder/" target="_blank" >Clique aqui</a>

## 👩‍💻 Time dev

- Adan Bueno
- Ian Mateus
- José Ribamar
- Vitória Moreira

## 🤔 Como contribuir

- Faça um fork desse repositório;
- Cria uma branch com a sua feature: `git checkout -b minha-feature`;
- Faça commit das suas alterações: `git commit -m 'feat: Minha nova feature'`;
- Faça push para a sua branch: `git push origin minha-feature`.

Depois que o merge da sua pull request for feito, você pode deletar a sua branch.

## 📝 Licença

Esse projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.

---

Feito com ♥ by team Bodybuilder 👋
