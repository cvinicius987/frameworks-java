# vehicle-delivery-routing

## Technologias:

 - Java 8;
 - Spring Boot;
 - Spring Data;
 - Maven 3;
 - H2 Database (Pode ser alterado para qualquer outro Banco de dados relacional /ou outra fonte de persistência)

## Endpoints

 - /clients 									- <b>POST</b>: Cadastro de novos Clientes;
 - /clients/{id} 								- <b>PUT</b> : Alteração de Clientes;
 - /clients/{id} 								- <b>PUT</b> : Visualização de Clientes; 
 - /restaurants 								- <b>POST</b>: Cadastro de novos Restaurantes;
 - /restaurants/{id} 							- <b>PUT</b> : Alteração de Restaurantes;
 - /restaurants/{id} 							- <b>PUT</b> : Visualização de Restaurantes; 
 - /orders										- <b>POST</b>: Geração de Pedidos.
 - /orders/{id}									- <b>GET</b>: Visualização de Pedidos;
 - /orders/restaurant/{id}  					- <b>GET</b>: Visualização dos Pedidos de um Restaurante;
 - /orders/restaurant/{id}/delivery/{delivery}  - <b>GET</b>: Visualização dos Pedidos de um Restaurante limitando pela Data de Entrega;

Para detalhes consulte:
	
  - /swagger-resources ou /swagger-ui.html
  
## Como usar:

### Maven com IDE
	
 - O Projeto pode ser configurado em uma IDE de preferência;
 - Após a configuração, executar a classe: <<>> 
 
 Obs: A ao sistema irá alocar a porta :8080, caso deseje alterar, basta modificar o arquivo src/main/resources/application.yml
 
### Maven

 - Executar o comando <b>mvn package</b>
 - O jar gerado deve ficar no mesmo diretório do arquivo application.yml;
 - Executar o comando java -jar <<jar_file>>
	