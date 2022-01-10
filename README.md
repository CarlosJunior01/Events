## Events
Aplicativo de listagem e check-in de eventos.

![GitHub top language](https://img.shields.io/github/languages/top/Carlosjr01/Filmes-App) 
[![MVVM](https://img.shields.io/badge/Architecture-MVVM_+_CLEAN_ARCHITECTURE-black)](https://www.youtube.com/watch?v=tIPxSWx5qpk) 
[![coroutines](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 
[![Koin](https://img.shields.io/badge/koin-3.0.2-black.svg)]()
[![Navigation](https://img.shields.io/badge/navigation-2.4.0-black.svg)]()
[![Lottie](https://img.shields.io/badge/lottie-3.6.1-black.svg)]()
[![Retrofit](https://img.shields.io/badge/retrofit-2.9.0-black.svg)]()
[![OkHttp](https://img.shields.io/badge/okhttp-4.9.1-black.svg)]()
[![Glide](https://img.shields.io/badge/glide-4.11.0-black.svg)]()
[![Mockk](https://img.shields.io/badge/mockk-1.11.0-black.svg)]()
[![Robolectric](https://img.shields.io/badge/robolectric-4.7.3-black.svg)]()
[![ViewBinding](https://img.shields.io/badge/viewbinding-4.3.0-black.svg)]()
![API](https://img.shields.io/badge/API-Eventos-lightgrey)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)]()

*******

Aplicativo Android Nativo escrito em Kotlin, consumindo Api de Eventos e processando resposta com retrofit e tratamento de concorrência com Kotlin Flow, utilizando padrão de arquitetura MVVM + Clean Architecture, com divisão de responsabilidades e separação de conceitos e desacoplamento de camadas. Injeção de dependência com Koin. Navegação e controle de fluxo de telas com Navigation e animações com Lottie. Testes unitários e Testes de interface utilizando Robolectric e Espresso.

![image](https://user-images.githubusercontent.com/9430430/148788876-a2cb5b49-63e4-4e1e-9be6-1e858f672987.png)
*******
![image](https://user-images.githubusercontent.com/9430430/148788991-61fde9d1-fe88-4e70-802c-4dc20dcff65f.png)

*******
* **Arquitetura MVVM + CLEAN ARCHITECTURE**
*******
![image](https://user-images.githubusercontent.com/9430430/148726004-f2bf587d-ef1b-4b53-8a1e-b3fb22515c5e.png)

![image](https://user-images.githubusercontent.com/9430430/148726286-ed2c0e92-897c-4e98-8cac-71ef7430f614.png)


**MVVM:** Tem como principal objetivo separar responsabilidades entre Views e Modelos
Aqui temos a View que responde somente para a ViewModel, e a ViewModel não comunica diretamente com a View. A ViewModel é então uma classe intermediaria entre a View e a Model que conecta uma com a outra fazendo assim intermediação entre elas através do mecanismo de conexão Data Binding.

**Modelo (Model):**
A Model caracteriza um conjunto de classes para descrever a lógica de negócios. Ela também descreve as regras de negócios para dados sobre como os dados podem ser manipulados ou alterados.

**Visão (View):**
A View representa componentes da interface do usuário. Ela também exibe os modelos na interface do usuário a partir do retorno da Presenter e da ViewModel. Assim como no MVP, a View aqui também tende a possuir o mínimo de regra de negócio possível, ela também é "burra", quem vai definir o que ela vai exibir é a ViewModel.

**ViewModel:**
A ViewModel é responsável por apresentar funções, métodos e comandos para manter o estado da View, operar a Model e ativar os eventos na View.
O ViewModel expõe fluxos de dados relevantes para o View. Mesmo neste caso, é a ponte entre o repositório e a View e contém toda a lógica de negócios.

### Principais benefícios da arquitetura Android
**Manutenção**
É possível entrar em partes menores e focadas do código e fazer alterações facilmente por causa da separação de diferentes tipos de códigos de maneira mais limpa. Isso ajudará a lançar novas versões rapidamente e a manter a agilidade.

**Testabilidade:**
No caso da arquitetura MVVM, cada parte do código permanece granular. Caso seja implementada da maneira adequada, todas as dependências internas e externas permanecerão do código que contém a lógica principal do aplicativo que você estava planejando testar.
Portanto, se você planeja escrever testes unitários com a lógica principal, fica muito mais fácil. Lembre-se de verificar se funciona bem quando escrito e continue executando, principalmente quando houver qualquer tipo de alteração no código, por menor que seja.

**Extensibilidade:**
Devido ao aumento de partes granulares de código e limites de separação, às vezes ele se mistura com a capacidade de manutenção. Se você planeja reutilizar qualquer uma dessas partes terá mais chances de fazê-lo.
Ele também vem com o recurso no qual você pode adicionar novos trechos de código ou substituir os existentes que executam trabalhos semelhantes em alguns locais da estrutura da arquitetura.
Sem dúvida, o principal objetivo de escolher a arquitetura MVVM é abstrair as Views para que o código por trás da lógica de negócios possa ser reduzido a uma extensão.

*******
* **Explicação de frameworks utilizados no projeto:**
*******

[![Retrofit](https://img.shields.io/badge/retrofit-2.9.0-black.svg)]()

Retrofit: É uma biblioteca desenvolvida pela Square que é utilizada como um REST Client no Android.
Utiliza a biblioteca OkHttp para fazer os Http Requests.
O Retrofit torna mais fácil recuperar e fazer upload de JSON através de uma Web service REST.
Com o Retrofit podemos escolher que conversor usar para a serialização de dados, como por exemplo o Moshi e GSON.

[![Glide](https://img.shields.io/badge/glide-4.11.0-black.svg)]()

Glide: É um gerenciador de mídia de código aberto rápido e eficiente e estrutura de carregamento de imagem para Android que envolve decodificação de mídia, armazenamento em cache de memória e disco e pool de recursos em uma interface simples e fácil de usar.

[![Navigation](https://img.shields.io/badge/navigation-2.4.0-black.svg)]()

Navigation: É um componente do Jetpack que permite que os usuários naveguem pelo aplicativo.
Está dividido em três partes principais:
        - Grafo de navegação: é um recurso XML que reúne todos os destinos e ções(cmnhos) que podem ser percorridos pelo usuário, em um local centralizado.
        - NavHost: contêiner vazio que mostra destinos do grafo de navegação.
        -NavController: objeto que gerencia a navegação do aplicativo em um NavHost. É responsável por organizar a troca do conteúdo de destino (tela) no
         NavHost conforme os usuários naveguem pelo aplicativo.

Benefícios do Componente de Navegação do Jetpack:
    - Automaticamente processa as transações do fragmento.
    - Comportamento adequado das ações de voltar (Up e vack buttons).
    - Recursos padronizados para animações e transições.
    - Implementa e processa Deeplinks.
    - Esforço mínimo para implementar a barra de navegação (Bottom navigation view) e a gaveta de navegação (Navigation Drawer)
   
[![coroutines](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 

Kotlin Flow: Utilizada para fluxos assíncronos, com isso podemos retornar vários valores calculados de forma assíncrona ao contrário de ma função de suspensão que retorna de forma assíncrona um único valor.

[![Lottie](https://img.shields.io/badge/lottie-3.6.1-black.svg)]()

Lottie: Framework para adicionar, renderizar e controlar Animações dentro do app. Lottie é um formato de arquivo de animação de código aberto que é pequeno, de alta qualidade, interativo e pode ser manipulado em tempo de execução.

[![Koin](https://img.shields.io/badge/koin-3.0.2-black.svg)]()

Koin: É uma biblioteca de injeção de dependência que reduz a injeção manual de código no projeto, criando e gerenciando as instâncias provendo os módulos dentro da aplicação.

[![Mockk](https://img.shields.io/badge/mockk-1.11.0-black.svg)]()

Mockk: É uma ferramenta para mocking quando escrevemos testes para aplicações Kotlin. Possúi um bom suporte para recursos de linguagem Kotlin, como corrotinas ou blocos lambda. Como uma biblioteca nativa, ela ajuda a escrever código limpo e conciso ao testar aplicações Kotlin, em vez de usar empacotadores incômodos do Mockito ou do PowerMock.

[![Robolectric](https://img.shields.io/badge/robolectric-4.7.3-black.svg)]()
[![Build Status](https://github.com/robolectric/robolectric/actions/workflows/tests.yml/badge.svg)](https://github.com/robolectric/robolectric/actions?query=workflow%3Atests)

Robolectric: É uma estrutura que traz testes de unidade rápidos e confiáveis para o Android. Os testes são executados dentro da JVM em sua estação de trabalho com mais velocidade. O Robolectic suporta a execução de testes de unidade para 15 versões diferentes do Android, variando de Jelly Bean (API de nível 16) a S (API de nível 31).

*******
* **Conclusão:** 
*******
Para este projeto foi escolhido o padrão de Arquitetura MVVM com Clean Architecture justamente por fazer uso um padrão de divisão de responsabilidades, com separação de conceitos, e camadas diferentes, nele temos o desacoplamento da camada de "Network" da camada de "Apresentação", Repository: Utilizado para centralizar funções e não repetir códigos centraliza o acesso aos dados. UseCases com responsabilidade única, possuindo as regras de negócio da aplicação e fazendo o meio de campo entre as duas camadas "ViewModel" e "Repository" separando ainda mais as responsabilidades da aplicação, separando a camada de apresentação da camada de dados.
Com essas duas arquiteturas implementadas melhoramos a clareza e entendimento de cada parte do projeto, facilitando e possibilitando o trabalho em diferentes frentes de camadas desacopladas em um projeto mais organizado, expandível, testável e flexível de forma padronizada de desenvolvimento para que a aplicação venha a ser escalável e testável com maior separação de conceitos e responsabilidades. 
