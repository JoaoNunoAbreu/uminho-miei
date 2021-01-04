# Notas

### Suport Vector Machines

* As distâncias entre observações e o *threshold* são as mesmas e ambas refletem a **margem**.
![](https://i.imgur.com/PZMijLw.png)

* **Maximal Margin Classifiers** são super sensíveis aos *outliers* na *training data*, por isso não é um bom método.
* Para fazer uma *threshold* que não é sensível aos *outliers* temos de **permitir misclassifications**.
* Escolher uma *threshold* que permite misclassifications é um exemplo de **Bias/Variance Tradeoff**.
* Quando permitimos misclassifications, a distância entre as observações e a *threshold* é chamada **Soft Margin**.
* Para descobrir qual é a melhor Soft Margin usamos **Cross Validation** para determinar quantas misclassifications permitiremos dentro da Soft Margin para ter melhor classificação.
* Quando usamos uma Soft Margin para determinar o local da *threshold* estaremos a usar **Soft Margin Classifier aka Support Vector Classifier** para classificar as observações.
* Quando os dados têm 4 ou mais dimensões, o Support Vector Classifier é uma **hyperplane**, no entanto, não estaria incorrecto usar esta terminologia também em 3 dimensões ou menos, apesar de ser mais comum a sua utilização quando não as conseguimos representar em papel.
* Quando a informação se encontra como a imagem abaixo, independentemente onde coloquemos o classifier, estaremos a fazer muitas misclassifications. Sendo assim, os Support Vector Classifiers não conseguem lidar bem com este tipo de dados...

![](https://i.imgur.com/OPL5U2o.png)

* Haverá solução para este problema? **Sim!** - **Support Vector Machines**
* Ao criarmos um novo eixo-y representado respetivamente cada valor do eixo-x elevado a 2, estaremos a transformar os dados para 2 dimensões.
![](https://i.imgur.com/J6jFw4p.png)
* Ou seja, a solução será:
    * Começar com dados numa dimensão relativamente pequena. (o exemplo foi numa dimensão)
    * Mover os dados para uma nova dimensão. (no exemplo foi usado a função quadrática)
    * Encontrar o Support Vector Classifier que separa os dados em 2 grupos.

* No entanto, a função transformadora de dados para a dimensão superior não precisa necessariamente de ser a função quadrática, até porque certos exemplos poderão não ser resolvidos com ela.
* Por isso, as Support Vector Machines usam algo chamado **Kernel Functions** para sistematicamente encontrar os **Support Vector Classifiers** em maiores dimensões.


### Source

* [Support Vector Machines, Clearly Explained!!!](https://www.youtube.com/watch?v=efR1C6CvhmE&t)