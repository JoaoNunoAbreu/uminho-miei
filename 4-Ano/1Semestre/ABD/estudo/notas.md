# ABD - Notas

## Transações

Uma **transação** é um conjunto de operações individuais, sejam de leitura ou escrita, e distintas mas que queremos tratar como uma unidade de trabalho indivisível. Devem ter propriedades **ACID**.

- **Atomicidade** - conjunto indivisível (ou se faz todas as operações ou nenhuma).
- **Consistência** - se apagarmos uma linha com uma chave estrangeira, ou apagámos também a linha para onde aponta a chave estrangeira ou impedimos toda a operação. Existe consistência se a BD for consistente no início e fim da transação.
- **Isolamento** - esteja uma transação a executar sozinha ou ao mesmo tempo com outras, o resultado é indistinguível. 
- **Durabilidade** - a partir do momento em que uma transação é dada como confirmada, os seus efeitos persistem independentemente das eventualidades.

### Controlo de concorrência (Isolamento)

#### Anomalias - nível de itens de tabelas (updates)

- **Lost update (RWW)** - Operação de update duma transação vai ser sobreposta com outra transação.

<div style="text-align:center">
    <img src="https://i.imgur.com/sZCo8tP.png" alt="drawing" width="400"/>
</div>

- **Dirty read/Uncommitted Dependecy (WRW)** - quando uma transação usa um resultado doutra transação que ainda não foi terminada. 

<div style="text-align:center">
    <img src="https://i.imgur.com/vP2ryIh.png" alt="drawing" width="400"/>
</div>

- **Non-repeatable read (RWR)** - quando uma transação lê vários valores da BD mas outra transação faz update de alguns desses valores durante a execução da primeira, lendo a primeira mais tarde valores errados. 

<div style="text-align:center">
    <img src="https://i.imgur.com/cRZ7QcN.png" alt="drawing" width="400"/>
</div>

#### Anomalias - nível de tabelas (inserts/deletes)

- **Phantoms (RWR)** - mesmo que non-repeatable read mas a nível da coleção.

<div style="text-align:center">
    <img src="https://i.imgur.com/4q1Ugqf.png" alt="drawing" width="400"/>
</div>

- **Write skew (RRWW)** - quando duas transações leem uma variável diferente mas que são usadas para cálculos os mesmos cálculos que vão ser usados para atualizar tais variáveis.

<div style="text-align:center">
    <img src="https://i.imgur.com/4EhoeQn.png" alt="drawing" width="400"/>
</div>

#### Soluções

- 2-phase locking: manter todos os locks adquiridos até ao fim da transação.
- Shared vs Exclusive: 
	- várias operações a ler ao mesmo tempo o item de dados.
	- apenas uma operação está a escrever no item de dados, excluindo todas outras escritas e leituras.
- Multi-level locking (granularidade):
	- Row locks
	- Table locks

Como escolher a melhor combinação? Através dos níveis de isolamento... 

#### Níveis de isolamento

- **Read uncommitted** - locks exclusivos em writes até a transação estar completa. **Não há shared locks.** Permite: Dirty read, Non-repeatable read, Phantoms e Write skew.

- **Read committed** - locks exclusivos em writes até a transação estar completa. **Há shared locks em reads.** Permite: Non-repeatable read, Phantoms e Write skew.

- **Repeatable read** - locks exclusivos em writes até a transação estar completa. **Há shared locks em reads até a transação estar completa.** Permite: Phantoms e Write skew.

- **Serializable** - locks exclusivos em writes até a transação estar completa. **Há range/table locks em reads até a transação estar completa.** Permite: nada. No entanto: 
    - table locking tem um grande impacto na concorrência.
    - range locking usa índices que adicionam complexidade/overhead.

- **Snapshot isolation** - Multi-version: nunca se dá overwrite no dados, mas sim cria-se uma nova versão. Lê da versão existente quando a transação começa sem precisar de locks. Nos writes, faz-se lock exclusivo e o primeiro committer ganha, os outros fazem rollback. Permite: Write skew

- **Serializable snapshot isolation** - igual ao SI, sendo ainda capaz de detectar dependências read-write e fazer rollback de transações onde ocorram write skews. Permite: nada. 


### Atomicidade

Requer:
- Operações individuais atómicas
- Saber-se quando operações individuais acabam

#### Redo log aka WAL

- Refazer escritas que tenham sido perdidas, ou seja, em caso de recuperação, vamos refazer aquilo que ficou a meio, para repor a atomicidade.
- Formatos:
    - **Físico** - **escrita idempotente** (se recuperação 2 vezes não tem problema porque o bloco é o mesmo que já está escrito) mas **ineficiente** (basta alterar uma linha num bloco que todo o bloco vai ser copiado para os logs, ou seja, log cresce indefinidamente)
    - **Lógico** - é escrito no log a indicação da modificação lógica que foi feita num bloco. **Eficiente** mas recuperação **menos idempotente**.
    - **Physiological** - combinação das duas.

Como remover prefixos do redo log?  
Escrevendo todas as mudanças de committed transactions para o disco.  
Mas como saber quais transações modificaram cada bloco?  
Regra **no-steal** - não podemos escreverem um bloco de memória para o disco se este foi alterado por outra transação a correr.   
**Vantagem**: transações poderem dar commit sem modificar o disco (**no-force**).  
**Desvantagem**: as modificações só podem ser escritas no disco depois da transação dar commit (**no-steal**), assumindo que a memória é grande suficiente para aguentar com todas as modificações.

#### Undo log

- Guarda-se no log as versões passadas e não as futuras atualizadas, modificando blocos diretamente da memória cache para o disco sem marcadores de commit. O marcador do commit só aparece após todos os blocos terem sido copiados para disco.

Como remover prefixos do undo log?  
Basta termos um bit por bloco a dizer se ele está modificado ou não, ou seja, podemos remover o bloco se a sua respetiva transação já tiver terminado. Se tivermos uma transação em curso ela ainda pode desfazer um bloco e necessitar de ir ao ficheiro log buscar o valor antigo.  

#### Redo/Undo log

Ficamos com as vantagens dos dois e nenhuma das desvantagens.
- Primeiro, os blocos só podem ser modificados no disco se primeiro for escrito no log o seu valor antigo (disco -> log). Mas também é preciso cumprir a regra do re-do, ou seja, antes de fazer commit, temos de escrever para o log a versão futura (cache -> log) e só depois teremos a marca do commit no ficheiro log. 

Para a recuperação, faz-se os dois procedimentos ao mesmo tempo. Redo, tail to head e Undo head to tail.  
O único caso em que teríamos problemas na ordem do redo/undo seria se não estivéssemos a fazer locks exclusivos até ao fim da transação, ou seja, não estaríamos a cumprir o mecanismo de isolamento.

Se os logs tiverem informação redo/undo:
- um bloco pode ser sempre copiado para disco (steal)
- uma transação pode dar commit imediatamente (force)

