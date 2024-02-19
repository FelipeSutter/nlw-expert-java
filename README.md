# NLW Expert da Rocketseat

<p>Neste NLW foram 7 trilhas com 7 linguagens de programação diferentes, e esse repositório é para a trilha de Java.</p>

# Sobre o projeto

<p>O projeto consiste em um sistema de certificações, onde os alunos podem tirar certificações em certas tecnologias, além de ter uma sessão de perguntas e respostas. As top 10 pessoas que mais acertaram perguntas aparecem no ranking.</p>

# Estrutura de Pastas

```
├── modules/ 
│   ├── certifications/
|   |   ├── controllers/
|   |   └── useCases/
│   ├── questions/
|   |   ├── controllers/
|   |   ├── dto/
|   |   ├── entities/
|   |   └── repositories/
│   ├── students/
|   |   ├── controllers/
|   |   ├── dto/
|   |   ├── entities/
|   |   ├── repositories/
|   |   └── useCases/
├── seed/
│   └── CreateSeed.java
```

```modules``` aqui é onde fica tudo o que é necessário para rodar a aplicação, como as classes que criam as tabelas no banco de dados e outras configurações necessárias para rodar a aplicação.

```seed``` configuração para executar um sql de criação do banco e também de inserção de dados.

```certifications``` pasta com as configurações das certificações que existem dentro do sistema.

```questions``` as questões e respostas que o sistema comporta

```students``` os estudantes que tiram suas ```certifications``` e respondem as ```questions```

```controllers``` a camada de controle de cada uma das entidades descritas acima. Ela serve para enviar e receber requisições HTTP, além de outras configurações

```dto``` são objetos, parecidos com as entidades, mas que servem para manipular somente alguns dados da entidade, e normalmente são utilizados no ```body``` da requisição como request/response

# Tecnologias Utilizadas

<ul>
  <li>Java 17</li>
  <li>Spring Boot</li>
  <li>Docker</li>
</ul>
