# api-chamados

## Funcionamento do Spring Boot e da API REST

### Como o Spring Boot recebe e processa requisições HTTP

O Spring Boot utiliza um servidor web embarcado, normalmente o Apache Tomcat, para receber as requisições HTTP enviadas pelos clientes. Quando uma requisição é realizada, o Spring identifica o endereço acessado e o método HTTP utilizado, como GET ou POST, encaminhando a solicitação para o método correspondente na aplicação.

No projeto, as requisições são direcionadas aos endpoints definidos no Controller. Após o processamento, a aplicação retorna uma resposta HTTP contendo os dados solicitados ou uma indicação do resultado da operação, acompanhada do código de status apropriado.

### Qual é a função do Controller na aplicação

O Controller é responsável por receber e controlar as requisições HTTP realizadas pelos clientes, direcionando cada solicitação para a funcionalidade correspondente.

Neste projeto, a classe `TicketsController` concentra os endpoints responsáveis pelo gerenciamento dos chamados, permitindo listar, consultar, filtrar, pesquisar e cadastrar registros.

Por meio das anotações do Spring, como `@RestController`, `@GetMapping` e `@PostMapping`, são definidos os caminhos e os métodos HTTP que a API disponibiliza. O Controller também recebe parâmetros enviados pela URL e dados encaminhados no corpo das requisições.

### Como ocorre a conversão entre objetos Java e JSON

A comunicação entre a API e os clientes utiliza o formato JSON para enviar e receber informações.

O Spring Boot utiliza a biblioteca Jackson, integrada ao Spring Web, para realizar automaticamente a conversão entre objetos Java e JSON.

Quando a API retorna um objeto ou uma lista de objetos Java, o Jackson realiza a serialização, transformando essas estruturas em JSON. Já quando um cliente envia um JSON em uma requisição POST, o Jackson realiza a desserialização, convertendo os dados recebidos em um objeto Java, por meio da anotação `@RequestBody`.

Dessa forma, a aplicação consegue trabalhar com objetos Java internamente, enquanto utiliza JSON como formato de comunicação com os clientes.

### Por que os chamados cadastrados não permanecem armazenados após reiniciar a aplicação

Neste projeto, os chamados são armazenados em memória utilizando coleções Java, especificamente uma lista baseada em `ArrayList`.

Esse tipo de armazenamento mantém os registros disponíveis apenas enquanto a aplicação está em execução. Quando o Spring Boot é encerrado ou reiniciado, os dados mantidos na memória são perdidos, pois não existe um banco de dados ou outro mecanismo de persistência responsável por armazená-los permanentemente.

Além disso, os chamados fictícios definidos inicialmente são carregados novamente quando a aplicação é iniciada. Por esse motivo, os registros cadastrados durante a execução anterior não permanecem disponíveis após uma reinicialização.


### Testes e evidências

As evidências dos testes se encontra no PDF evidencias_dos_testes.pdf ![Evidencias](evidencias_dos_testes.pdf)
