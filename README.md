# LPOO1617_T5G1
Vicente Fernandes Ramada Caldeira Espinha - up201503764 - up201503764@fe.up.pt
Vitor Emanuel Fernandes Magalhães - up201503447 -  up201503447@fe.up.pt

.Architecture Design

StateMachine:
![statemachine](https://cloud.githubusercontent.com/assets/22790772/25526284/c675aab8-2c0a-11e7-9327-caddb6d1aba2.png)

Project Logic:
![project logic](https://cloud.githubusercontent.com/assets/22790772/25526285/c7d7e2b8-2c0a-11e7-95e1-122fa244d1f5.png)

Classes Responsabilities:

ChickenVsFood: começa o jogo, carregando o menu inicial(Main Menu) e declarando o tamanho do ecrã virtual;

ButtonImg: classe que extende ImageButton, sendo possível criar um botão com texturas;

MainMenuScreen: ecrã que contém as opções de jogar, definições e sair do jogo;

LevelScreen: ecrã que mostra os níveis disponíveis para jogar, prontos para serem selecionados;

PlayScreen: ecrã onde se joga, com as personagens e um HUD. Contém botão para sair para o Menu;

Hud: classe que implementa o Head-Up Display, mostrando a quantia de milho que o jogador tem e as comidas que pode utilizar, 
juntamente com o nível atual e a barra de progresso;

Chicken: interface que será implementada por cada tipo de galinha;(imagem abaixo)

Food: interface que será implementada por cada tipo de comida;(imagem abaixo)

SettingScreen: ecrã que mostra opções de jogo, incluindo volume da música e dos sons, além dos créditos;

![functions](https://cloud.githubusercontent.com/assets/22790772/25526287/c937b606-2c0a-11e7-901e-e68dd54bfb8f.png)


-->Design Patterns

Singleton: 
-> Para objetos usados globalmente, como a variável ChickenVsFood 
FlyWeight:
-> Guardar a mesma textura de personagens que são iguais, como o mesmo tipo de galinhas/comida
Object Pool: 
-> Cada comida utilizada pelo user será reutilizada, tal como as "armas" utilizadas por esta.
Cada galinha será também reutilizada, dependendo do nível
Prototype: 
-> A interface Chicken será um protótipo, permitindo que cada galinha faça "spawn".
State:
-> Utilizado para registar os vários estados de cada inimigo e o estado de jogo

.GUI Design

Main Menu:
'New Game' vai para 'Level Menu'
'Settings' vai para 'Settings'
'Quit' exits the game

![new mockup 1](https://cloud.githubusercontent.com/assets/22790772/25526246/a493b3ea-2c0a-11e7-888a-44668fe03a1d.png)

Level Menu
No inicio,o primeiro nível será o único disponivel e se conseguir passar o nível 2 fica disponível ,e assim, sucessivamente.

![new mockup 2](https://cloud.githubusercontent.com/assets/22790772/25526272/bb02ee20-2c0a-11e7-939a-5be503c15752.png)

Game:
O Hud terá as as Food's que poderão ser selecionadas

![new mockup 3](https://cloud.githubusercontent.com/assets/22790772/25526275/c12f95f0-2c0a-11e7-8f1f-3f08ed051977.png)

Settings:

![new mockup 4](https://cloud.githubusercontent.com/assets/22790772/25526281/c47f09ca-2c0a-11e7-95d3-1c517a4d2b71.png)

.Test Design

->Jogo começa sem personagens no mapa

->escolha aleatória de "lanes" das chickens

->selecionar uma Food e posicionar num espaço livre

->verificar colisões
