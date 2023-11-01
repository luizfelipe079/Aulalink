# DesafioFinal

Este é um repositório no qual me propus a criar um Desafio do zero
Apenas com ideias e pondo-as em prática

Para utilizar de maneira correta é preciso que você tenha o PostgresSQL instalado por este Link: https://www.postgresql.org/download/
ou indo diretamente na fonte no seguinte link: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Ao instalar você pode configurar o seu username e password da maneira que se sentir melhor, lembrando que dependendo de como você configurar o seu Banco de dados você terá que modificar o application.properties da sua aplicação.

Link com imagens de exemplo pra instalação: https://www.postgresqltutorial.com/postgresql-getting-started/install-postgresql/
Link com guia para baixar e instalar postgreSQL no windows 11: https://youtu.be/IYHx0ovvxPs?feature=shared

## Contém o Spring Security e o OpenAPI para testar os endpoints:

<img src=assets/img/swagger.png>

E para testar com acesso total você pode ir no swagger pelo **localhost:(serverPort)/swagger-ui.html**
Após isso você irá utilizar o endpoint **SignIn** com as seguintes informações:

<img src= assets/img/admin.png>


Com isso ele irá retornar um token de acesso autenticado como vemos na imagem seguinte:

<img src= assets/img/token.png>


Então você irá ao authorize ->

<img src= assets/img/authorize.png>


Clique nele e ira abrir uma aba para logar, nesta aba você irá colocar o token que recebeu quando utilizou o endpoint **SigIn***

<img src= assets/img/auth.png>


e com isso você estará autenticado e autorizado para utilizar os demais endpoints.







