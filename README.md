# ww_simulation
This is a simple simulation game which takes input as text file and simulate the written scenario step by step.

How to run:
+ Run the main class and give full path of input and output file

My Design and assumptions:
+ Needed info is taken from the input file:
  - Resource info
  - Hp and attack info of the hero
  - Enemy info such as name, hp, attack info is stored in enemies hash map
  - By using position and name info of enemies, enemy is created for every position and stored in road hash map
+ Simulation:
  - Output is written to file step by step
  - Hero moves 1 meter in every step
  - Checking at every move if there is enemy or not
  - If there is an enemy, the attack will start and continue until hero or enemy dies.

Sample input:
Resources are {integer} meters away
Hero has {integer} hp
Hero attack is {integer}
{string-enemy name} is Enemy
{string-enemy name} has 50 hp
{string-enemy name} attack is 2
There is a {string-enemy name} at position {integer}

+ You can add as many as enemies with needed info (hp, attack, position)
+ There must be only one hero
