# MatCarv IT Services ®

A MatCarv IT Services ® é uma empresa de consultoria em tecnologia da informação e desenvolvimento de software, com foco em oferecer para a sua empresa soluções sob medida.

Com experiência em desenvolvimento de aplicações web e móvel, a MatCarv IT Services ® está sempre criando soluções inovadoras em diversos segmentos, conforme a sua necessidade.

A aplicação desenvolvida trata-se de um portfólio backend de cadastro de produtos e pedidos, baseado em microserviços, onde ao realizar um pedido, o produto deve ter sua quantidade reduzida conforme a quantidade pedida.

Para isso, foi utilizado o Redis como solução de cache para compartilhamento dessas informações entre os modulos de pedido e produto.

- [www.matcarv.com](http://www.matcarv.com)
- [contato@matcarv.com](mailto:contato@matcarv.com)

## Recursos e Soluções

- HSQLDB Database
- Redis as a Cache
- Spring Boot
- Spring Data / JPA
- Spring Security / JWT
- Swagger UI
- Netflix Zuul and Eureka

## Instalação

Primeiramente é necessário que faça o download do [Redis](https://redis.io/download). Siga as instruções do próprio site para instalação e execução da solução.

Faça o clone do [código fonte](https://github.com/matcarvit/matcarv.git) do projeto.

## MatCarv Commons

```bash
cd matcarv/matcarv-commons
mvn clean install
cd ..
```

## MatCarv Gateway ( Netflix Eureka )

```bash
cd matcarv-gateway
mvn clean package
nohup java -jar target/matcarv-gateway.jar & tail -f nohup.out
```

## MatCarv Auth

```bash
cd matcarv-auth
mvn clean package
nohup java -jar target/matcarv-auth.jar & tail -f nohup.out
```

## MatCarv Products

```bash
cd matcarv-products
mvn clean package
nohup java -jar target/matcarv-products.jar & tail -f nohup.out
```

## MatCarv Orders

```bash
cd matcarv-order
mvn clean package
nohup java -jar target/matcarv-order.jar & tail -f nohup.out

```

###MatCarv Proxy ( Netflix Zuul )

```bash
cd matcarv-proxy
mvn clean package
nohup java -jar target/matcarv-proxy.jar & tail -f nohup.out

```
Após a build e execução dos módulos, acesse as URLs:

```bash
módulo auth  http://{yourhost}:9998/api/auth/info
módulo products > http://{yourhost}:9998/api/products/info
módulo orders > http://{yourhost}:9998/api/order/info

```
OBS: Os Bancos de Dados serão gerados em memória, sendo a estrutura criada em tempo de deploy. Caso deseja que seja de forma física, vá em application.yml de cada módulo e modifique os dados referente à conexão de Banco de Dados.

## Login e Senha para autenticação no MatCarv Auth

- Usuário: admin
- Senha: 123456

É necessário copiar o JWT gerado para a reutilização nos outros módulos.




