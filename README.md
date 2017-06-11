# LPOO1617_T5G1
Vicente Fernandes Ramada Caldeira Espinha - up201503764 - up201503764@fe.up.pt
Vitor Emanuel Fernandes Magalhães - up201503447 -  up201503447@fe.up.pt

Better Code Hub :
[![BCH compliance](https://bettercodehub.com/edge/badge/BCH-FEUP-OOPLab/LPOO1617_T1G8?branch=master&token=60cfaa03b79c18603b7b8300e60f4c8c967ab639)](https://bettercodehub.com/)

Setup/ Instalation:

Utilizamos Android Studio para a realização deste projeto. Para instalar num dispositivo android através do Android Studio basta clicar em "Build" e depois "Generate Signed APK". Se utilizar um emulador, de preferência o Galaxy Nexus e correr em "Run"-> "Run Android".

Development documentation:

UML (separados por package para se perceber melhor):

![19113349_1540989372600007_577113561_n](https://user-images.githubusercontent.com/22790772/27014096-b3e47634-4ee9-11e7-8fa1-0971c5f13379.png)

![19095789_1540996892599255_2124678298_o](https://user-images.githubusercontent.com/22790772/27014095-b3e36460-4ee9-11e7-921e-2b02a3bad072.png)

![19114343_1540997302599214_1340000154_n](https://user-images.githubusercontent.com/22790772/27014097-b4048320-4ee9-11e7-8491-3542f3c1acb2.png)

![19114819_1541000242598920_1576144548_n](https://user-images.githubusercontent.com/22790772/27014098-b406afd8-4ee9-11e7-8fae-73d1f639496a.png)

![19074958_1541012155931062_439390734_o](https://user-images.githubusercontent.com/22790772/27014099-b40fed8c-4ee9-11e7-8b8f-dbbf862f0fb2.png)

![19096040_1541019872596957_2084270024_o](https://user-images.githubusercontent.com/22790772/27014100-b41f885a-4ee9-11e7-8255-6190918403e4.png)

![19075170_1541028709262740_2092971980_n](https://user-images.githubusercontent.com/22790772/27014101-b4228d34-4ee9-11e7-9790-eab7ea066aa3.png)



State Machine
![statemachine](https://cloud.githubusercontent.com/assets/22790772/25526284/c675aab8-2c0a-11e7-9327-caddb6d1aba2.png)

Design Patters:
Singleton
State
Flyweight
Game Loop
Update Method
Template Method

Design Decisions:
Criamos um botão com imagem, porque apenas queriamos aplicar uma textura simples ao butão.
Todas as texturas baseiam-se nas originais, que nós não utilizamos.
Tivemos problemas com a deteção tátil e as diferentes resoluções.
Apercebemo-nos quanto tempo demora a desenvolver um jogo simples,e, de certeza, que nos vai ajudar no futuro para, se quisermos, criarmos os nossos próprios projetos.

Contribuições:

Começámos a trabalhar antes de 28 de Abril de 2017 e devemos ter trabalhado cerca de 200 horas neste projeto e ditribuimos de igual forma o trabalho deste projeto

Dificuldades:

Da forma como implementamos o código, não foi possivel fazer-mos testes unitários ,pois no nosso código, relacionando com a implementação mvc,temos a view misturada com o controller e o model. Só nos apercebe-mos que, desta forma, não conseguiriamos realizar os testes muito perto da entrega , logo não tivemos tempo para modificar o código para os testes. Também, o facto de a entrega ser no meio da época de exames também não nos ajudou na realização do mesmo.



User Manual:

Main Menu
![screenshot_1](https://user-images.githubusercontent.com/22790772/27014221-61d89c5a-4eec-11e7-9019-716e859752c0.png)

No canto inferior esquerdo, existe um botão para parar ou retomar o som
Outros botões:
-Select Level (2)
-Chicken Almanac (3)
-Food Almanac (4)
-Options (5)
-Exit (terminar o programa)


Select Level (2)
![screenshot_9](https://user-images.githubusercontent.com/22790772/27014383-7a3cff54-4eef-11e7-9fcb-8eb9d2f02db4.png)
Tem três niveis que terminam ao fim de algum tempo e o modo survival que é infinito


Selecionando o primeiro nível, o jogo começa assim:
![screenshot_2](https://user-images.githubusercontent.com/22790772/27002216-d05aa976-4dd3-11e7-890c-473a5ae79305.png)

No jogo, mediante a quantidade de "corn" vamos podendo colocar as "Food" nos "quadrados" de relva , sendo que só uma Food pode estar num quadrado. Para aumentar "corn" coloca-se a personagem que se parece com "corn"(segunda personagem na imagem). Ao longo do tempo, vão aparecendo "Chicken" de forma aleatória numa das lanes e temos que tentar impedir que cheguem a bater na casa. Se colidirem com as Butter, estas destroiem automaticamente a Chicken.

Alguns screenshots tirados durante um jogo:

![screenshot_5](https://user-images.githubusercontent.com/22790772/27002218-d98f3b42-4dd3-11e7-9f92-4a5fc4a5ed86.png)

![screenshot_4](https://user-images.githubusercontent.com/22790772/27002217-d83e2e24-4dd3-11e7-9c5d-739d3723a35d.png)

Perdendo o jogo aperece este menu, onde se pode tentar jogar outra vez ou sair.
![screenshot_10](https://user-images.githubusercontent.com/22790772/27014380-7a217482-4eef-11e7-9ca6-8853c3fb271c.png)

No final dos tres niveis, se ganhar, aparecerá este screen (em android)
![screenshot_12](https://user-images.githubusercontent.com/22790772/27014381-7a3b41a0-4eef-11e7-9398-25b8f3a6db50.png)

Chicken Almanac(3)
![screenshot_6](https://user-images.githubusercontent.com/22790772/27014235-a7fbd328-4eec-11e7-9360-93925504e398.png)

Permite ver as "Chicken" que aparecem ao longo do jogo

Food Almanac(4)
![screenshot_7](https://user-images.githubusercontent.com/22790772/27014236-a81efe70-4eec-11e7-8956-0a2b2d511214.png)
Permite ver as "Food" que aparecem ao longo do jogo


O butão Options(4) serve como "Créditos":

![screenshot_8](https://user-images.githubusercontent.com/22790772/27014382-7a3b8660-4eef-11e7-8534-01a1d7c1f4b6.png)



