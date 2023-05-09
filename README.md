# Backend da rede social Parrot, por Luan Chrystian Pimentel.
---
###### Aplicação apelidada como NetSys. Criada com a versão 20 do Java, Springboot na versão 3.1.0 Snapshot. 
---
##### Pontos iniciais

- Essa API possui uma interface gráfica gerada pelo Swagger, está acessível na URI: */swagger-ui/index.html#/*.
-  O ambiente de desenvolvimento está na porta 8082.
- O projeto em Docker-Compose será disponibilizado em breve.

# Banco de dados e upload de arquivos
O banco de dados utilizado na aplicação é o MongoDB, manipulado pelo framework Spring Data MongoDB.
Para upload de arquivos, é utilizado o AWS-S3, sistema de Buckets da Amazon, conectado através da sua API gratuita.
---
# Serviço Autenticação
A autenticação é realizada através do endpoint '/api/v1/auth', aonde realiza o verbo POST, obtendo o email e a senha, retornando os dados do usuários, além  de gerar um token de autenticação.

Endereço dos arquivos:
- Controller: /Controllers/AuthController
- Serviço: /Services/Authentication/AuthenticationService
---
### Token de autenticação
O token de autenticação é gerado utilizando a biblioteca JSON WEB Token, ele utiliza uma chave BASE64 e o criptografa em HS256.

O token é utilizado para fazer a verificação de que se o usuário estar autenticado ou não, o Front-End se encarregará de permea-lo no ambiente da aplicação, já middleware de autenticação no Back-End fará a validação  dele, cujo também faz o trabalho de inserir o 'user_id' obtido do subject dentro das requisições realizadas pelo cliente.

Endereço dos arquivos:
- Serviço: /Services/Security/JwtService 
- Middleware: /Middlewares/AuthMiddleware
- Config do Middleware: /Middlewares/Configuration/ WebMvcConfig
---
# Entidades
A aplicação possui duas entidades no banco de dados, a de Usuários e a de publicação.

## Entidade de  Usuários
Essa entidade possui 9 campos, sendo eles Id, Email, name, username, password, Biografia, Seguidores, seguindo e Avatar.

Endereço do arquivo: 
- /Models/Entities/Publication

### Usuários: Name, username, email e password.
São dados requeridos na criação do usuário, e enviados pelo Endpoint **/api/v1/user**, através do verbo POST, na rota **/signup**.
A criptografia de senhas é realizada pela biblioteca Bcrypt, dentro do serviço de usuários, no método *createUser*.

O username é baseado no '@usuário' da rede social Twitter, sendo o principal motivo da sua inserção ser utilizado para realizar o verbo GET no endpoint **/api/v1/user/username**.

Name e username compartilham do mesmo endpoint de redefinição, sendo um verbo PUT na rota **/rename**, porém é feito uma regra de negócio permitindo a redefinição de um sem a necessidade de mudar o outro.

Endereço dos arquivos:
- Controller: Controllers/UserController 
- Serviço: Services/User/UserService.

### Usuários: Biografia

A biografia é apenas um único campo, que recebe uma *LinkedList* do tipo *Object_Model*. Esse objeto é composto por dois atributos o Type e o Value. 
Esses atributos serão preenchidos pelo Front-End, através de pares de *inputs*, sendo *Type* vindo de um Input de seleção, aonde haverá opções como 'Birth', 'School', 'University', 'Work', além de outras possibilidades. Já o Input value será preenchido pelo segundo Input do par, que será do tipo texto. 
Então em um cenário que for preenchido a data de aniversário, haverá o Input de seleção preenchido com Birth e o Input de value preenchido com a data.

Optei por esse sistema pois tomei como base o Profile do Facebook, aonde possui vários campos que podem se repetir, por exemplo, locais onde trabalhou ou onde estudou, então resolvi reunir todas essas informações em um único lugar, armazenando tais informações em uma Array.

A biografia possui seu próprio serviço e controller, localizado no endpoint **/api/v1/user/biography**, possuindo três verbos, sendo dois **PATCHS** e um **DELETE**. O primeiro **PATCH** realiza a captura de um objeto contendo uma Array, ele é pensado na situação da criação inicial da biografia, onde são preenchidos vários campos em um único formulário, mas também será utilizado para adicionar novos valores. Já o segundo **PATCH**, na rota */update* recebe apenas um objeto, contendo as propriedades type e value, utilizado na situação de atualização de um campo já existente.

Endereço dos arquivos: 
- Controller: Controllers/BiographyController
- Serviço: Services/Biography/BiographyService)

### Usuários: Avatar
O avatar é um campo String que recebe a URL do endereço do arquivo upado pelo serviço do AWS no Bucket S3. O upload da foto do perfil possui seu próprio controller, no endpoint **/api/v1/user/avatar**. Nele há o verbo **PATCH** no método updateAvatarProfile, e está conectado ao serviço de usuários, utilizando o método *uploadPhotoProfile*, que se relaciona diretamente com o serviço de upload da AWS.

Endereço dos arquivos: 
- Controller: Controllers/UserAvatarController
- Serviços: Services/fileUpload)

### Usuários: Sistema de follow
Ambos campos são uma Array, aonde possui a regra de négocio semelhante. Possuem seu próprio controller, no  endpoint **api/v1/follows** realiza dois verbos, o **POST** com o método follow, nele é recebido pelo Path parameters o ID do usuário a ser seguido, dentro do seu serviço é feito o processo para captar o novo seguidor pro outro usuário e preencher a sua (array de quem realizou a requisição) array de following. 
Já o segundo Verbo é o **DELETE** no método unfollow, aonde possui uma regra de negócio semelhante ao de seguir, obtendo o ID do usuário seguido no Path Parameters, o ID do usuário logado pela requisição. Através do ID do usuário seguido, é feito a verificação na array de 'following' pelo método removeIf e posteriormente removido caso verdadeiro.

Endereço dos arquivos: 
- Controller: Controllers/FollowController
- Serviço: Services/Follows/FollowService)
---
## Entidade de publicação
A entidade de publicação possui 8 campos, sendo eles id, userId, nameAuthor, contentText, contentImage, created_at, comments e likes.

O userId é gerado no momento da criação da publicação pelo método RandomUUID da clase UUID, enquanto o userId é obtido da requisição, cujo é inserido pelo Middleware. O nameAuthor é obtido na regra de negócio, através do getter na entidade. O createad_at também é criado internamente.

O contentText e contentImage são obtidos do corpo da requisição, enviados como Multipartform. O upload de foto utiliza o mesmo serviço de AWS, se relacionando com o serviço de FileUpload. Há liberdade de criar uma publicação somente com foto ou somente com texto.

Seu controller se encontra no endpoint 'api/v1/publications', aonde realiza 4 verbos, dois GET, um na rota "/all/{userId}", que busca os dados de todas as publicações de um usuário, e outro na rota "/{postId}" que busca os dados de apenas uma publicação pelo o seu Id.

Os outros dois é um POST, na rota "/new" e um DELETE na rota "/{postId}"

Endereços: (Controller: Controllers/PublicationController | Serviço: Services/Publications/PublicationService)


### Publicação: Comments

É uma Array *LinkedList* do  tipo *Comment_Model*. possui quatro atributos, id, authorId (autor do comentário), postId (ID da publicação) e content (conteúdo de texto da publicação). 

Possui seu próprio controller, no endpoint **api/v1/publications/comment**, aonde realiza dois verbos, um **POST** no método *createComment*, que recebe o ID da publicação pelo Path Parameters e o *DELETE* no método *deleteComment*, que recebe o ID da publicação e o ID do comentário pelo path parameters. 
*Obs: Esse endpoint será posteriormente refatorado para receber ambos valores pelo Query Params.*

Endereço dos arquivos:
- Controller: Controllers/CommentController
- Serviço: Services/CommentService)

### Publicação: Likes
Também é uma Array *LinkedList*, do tipo *Like_Model*, aonde possui 3 atributos, o name, userId e postId. Seu controller está no endpoint **api/v1/publications/like**, cujo realiza dois verbos, o **POST**, com o método *likePublication*, que possui a rota */{post_id}* e realiza um **DELETE**, com o método *removeLike*, possuindo a rota */delete/{post_id}*.

Endereço dos arquivos:
- Controller: Controllers/LikesController
- Serviço: Services/LikesService)

---
### Arquitetura do projeto: Estrutura de pastas
O projeto utiliza como base o MVC e possui os seguintes pacotes:

##### Pacote Controllers:
Possui todos os controllers da aplicação.

##### Pacote Midlewares: 
Possui o AuthMiddleware, responsável por validar o middleware de autenticação e inserir o userId nas requisições. Dentro desse pacote também tem uma pasta chamada Configuration, aonde possui as configurações do middleware.

##### Pacote Models: 
Dentro dele existe 4 pastas. A primeira é a Entities, que possui as entidades de User e Publication, utilizadas pro banco de dados. A segunda pasta é a Objects, que possui classes utilizadas de modelos para objetos como Biography_Model e Comment_Model. A Terceira e a quarta pasta são Request e Response, com classes utilizadas para modelar as requisições e as respostas. 

##### Pacote Providers: 
Dentro desse pacote, encontra-se duas classes, uma a da configuração do AWS, e outra a FilenameCreator, classe que possuí um método estático utilizado para gerar os nomes dos arquivos upados. Basicamente essa pasta armazena configurações e funções utilitárias, ela será melhor exploarada nas próximas versões do projeto.

##### Pacote Repositories: 
Possui os dois repositórios do banco de dados, um para a entidade de Usuário e outra para de publicação.

##### Pacote Services: 
Possui todos os serviços da aplicação, contendo as principais regras de negócio utilizadas pro funcionamento do projeto.

