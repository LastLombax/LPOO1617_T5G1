# LPOO1617_T5G1
Vicente Fernandes Ramada Caldeira Espinha - up201503764 - up201503764@fe.up.pt
Vitor Emanuel Fernandes Magalhães - up201503447 -  up201503447@fe.up.pt

Setup/ Instalation:

Utilizamos Android Studio para a realização deste projeto. Para instalar num dispositivo android através do Android Studio basta clicar em "Build" e depois "Generate Signed APK". Se utilizar um emulador, de preferência o Galaxy Nexus e correr em "Run"-> "Run Android".

Development documentation:

UML:
State Machine
![statemachine](https://cloud.githubusercontent.com/assets/22790772/25526284/c675aab8-2c0a-11e7-9327-caddb6d1aba2.png)

Design Patterns:
Strategy para as personagens Food e Chicken

Dificuldades:
Da forma como implementamos o código, não foi possivel fazer-mos testes unitários ,pois no nosso código, relacionando com a implementação mvc,temos a view misturada com o controller e o model. Só nos apercebe-mos que, desta forma, não conseguiriamos realizar os testes muito perto da entrega , logo não tivemos tempo para modificar o código para os testes. Também, o facto de a entrega ser no meio da época de exames também não nos ajudou na realização do mesmo.

Contributions:



User Manual:

Main Menu
![screenshot_1](https://user-images.githubusercontent.com/22790772/27009747-ceef9d20-4e8d-11e7-8e47-7195ef7b35fa.png)

No canto inferior esquerdo, existe um botão para parar ou retomar o som
Outros botões:
-Select Level (2)
-Chicken Almanac (3)
-Food Almanac (4)
-Options (5)
-Exit (terminar o programa)


Select Level (2)
![screenshot_9](https://user-images.githubusercontent.com/22790772/27009742-ced842ba-4e8d-11e7-8a65-20c7aee0c3db.png)
Tem três niveis que terminam ao fim de algum tempo e o modo survival que é infinito


Selecionando o primeiro nível, o jogo começa assim:
![screenshot_2](https://user-images.githubusercontent.com/22790772/27002216-d05aa976-4dd3-11e7-890c-473a5ae79305.png)

No jogo, mediante a quantidade de "corn" vamos podendo colocar as "Food" nos "quadrados" de relva , sendo que só uma Food pode estar num quadrado. Para aumentar "corn" coloca-se a personagem que se parece com "corn"(segunda personagem na imagem). Ao longo do tempo, vão aparecendo "Chicken" de forma aleatória numa das lanes e temos que tentar impedir que cheguem a bater na casa. Se colidirem com as Butter, estas destroiem automaticamente a Chicken.

Alguns screenshots tirados durante um jogo:

![screenshot_5](https://user-images.githubusercontent.com/22790772/27002218-d98f3b42-4dd3-11e7-9f92-4a5fc4a5ed86.png)

![screenshot_4](https://user-images.githubusercontent.com/22790772/27002217-d83e2e24-4dd3-11e7-9c5d-739d3723a35d.png)

Perdendo o jogo aperece este menu, onde se pode tentar jogar outra vez ou sair.
![screenshot_10](https://user-images.githubusercontent.com/22790772/27009744-ceda52e4-4e8d-11e7-89e7-9a28ada9f32e.png)

No final dos tres niveis, se ganhar, aparecerá este screen (em android)
![screenshot_12](https://user-images.githubusercontent.com/22790772/27009746-cedbc156-4e8d-11e7-9fad-fa10c3386074.png)

Chicken Almanac(3)
![screenshot_8](https://user-images.githubusercontent.com/22790772/27009743-ced97b62-4e8d-11e7-8f7d-1c6b8d761b24.png)

Permite ver as "Chicken" que aparecem ao longo do jogo

Food Almanac(4)
![screenshot_7](https://user-images.githubusercontent.com/22790772/27009741-ced48b7a-4e8d-11e7-9f14-9c5ea0d63017.png)
Permite ver as "Food" que aparecem ao longo do jogo


O butão Options(4) serve como "Créditos":

![screenshot_6](https://user-images.githubusercontent.com/22790772/27009745-cedbb7c4-4e8d-11e7-8a82-9bf0e6d2e3ad.png)



