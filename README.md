## Tradutor de Máquinas de Turing

- Algoritmo que traduz uma máquina de turing com fita duplamente infinita para o modelo de fita semi-infinita.

- __Entrada:__
  - Arquivo texto _.in_ com programa para máquina de turing utilizando __fita duplamente infinita__.
- __Saída:__
  - Arquivo texto _.out_ com programa capaz de ser executado no simulador a partir do modelo de __fita semi-infinita (modelo de Sipser)__. 

### Arquivo

- O arquivo de entrada deve ser constituído no formato:

> < current state > < current symbol > < new symbol > < direction > < new state >