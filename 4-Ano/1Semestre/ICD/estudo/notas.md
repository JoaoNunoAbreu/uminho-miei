# Infraestrutura de Centro de Dados

## Número de páginas dos slides

- infraestrutura - 9
- data storage - 44
- filesystems - 20
- dns - 14
- ntp - 7
- dhcp - 12
- reliability - 64
- data centers - 50

## Aulas teóricas 📕

### Data storage

- https://www.youtube.com/watch?v=YqsVxCEpA0w - DAS vs NAS vs SAN
- https://www.youtube.com/watch?v=3yZDDr0JKVc - NAS vs SAN
- https://www.youtube.com/watch?v=U-OCdTeZLac - RAID 0, 1, 5 e 10

### DNS

- https://www.youtube.com/watch?v=mpQZVYPuDGU

### NTP

- https://www.youtube.com/watch?v=1GtySPUW-XA

### DHCP

- https://www.youtube.com/watch?v=e6-TaH5bkjo

### Data center basics

- **Resiliência** - permite que a aplicação continue com funcionalidade total ou parcial depois de uma ou mais falhas.
- **Reabilidade** - representa a probabilidade dum sistema não encontrar nenhuma falha ao longo de um tempo. 
- **Disponibilidade** - habilidade dum sistema manter uma aplicação a correr.
- **Facilidade de serviço** - probabilidade dum serviço ser completado com tempo limite.
- **Tolerância a faltas** - sistemas que conseguem correr com faltas de componentes.

---

## Aulas práticas 🖥

<details>
<summary>LVM</summary>

### Logic Volume Manager

O ***LVM*** é uma ferramenta disponível no kernel do Linux para gestão de volumes lógicos. Ao originar uma camada de abstracção sobre o armazenamento físico, faz com que a criação e gestão de volumes lógicos seja mais flexível e dinâmica.

### Algumas vantagens
  
- Redimensionamento sem necessidade de refazer as partições.
- Nomes dos dispositivos personalizados pelo utilizador.
- Disk Striping, para aumento das velocidades de leitura e escrita (maior performance I/O).
- Mirroring volumes, para redundância dos dados.
- Volume Snapshots, para backups consistentes ou para testar alterações sem afectar os dados reais.

</details>

<details>
<summary>DRBD</summary>

### Distributed Replicated Block Device

O ***DRBD*** é utilizado para construção de clusters de alta disponibilidade, onde os dados devem ser replicados em mais de um local.

Os dados são gravados em dispositivos físicos comuns como HDs IDE e SATA, mas através do módulo do DRBD é criado um dispositivo de bloco, que gravará os dados via rede, num ou mais dispositivos físicos reais.

O DRBD trabalha como se fosse um RAID 1, mas em rede, ou seja, terás o teu dispositivo espelhado de uma máquina para outra via rede.

### Vantagens

- Fornece alta disponibilidade e integridade de dados nos 2 servidores em caso de falha de hardware ou sistema.
- Garante a integridade dos dados reforçando *write consistency* no nó primário e secundário.

### Desvantagens

- Fornece apenas um método para duplicar dados entre os nós. Os nós secundários não podem usar o dispositivo DRBD enquanto os dados estão a ser replicados.
- Não pode fornecer escalabilidade, pois os nós secundários não têm acesso aos dados secundários.

### Uso recomendado

- Situações de alta disponibilidade em que o acesso simultâneo aos dados não é necessário, mas o acesso instantâneo aos dados ativos no caso de falha do sistema ou hardware é necessário.

### Arquitetura

<img src="https://i.imgur.com/Bpv61hI.png" alt="drawing" width="500"/>

</details>

<details>
<summary>iSCSI Multipath</summary>

### Internet Small Computer System Interface

O ***iSCSI multipathing*** configura várias rotas entre um servidor e seus dispositivos de armazenamento para manter uma conexão constante e **equilibrar a carga de tráfego**. O software *multipathing* lida com todas as solicitações de entrada e saída e transmite-as no melhor caminho possível. O tráfego dos servidores host é transportado para o armazenamento compartilhado usando o protocolo *iSCSI* que empacota comandos *SCSI* em pacotes *iSCSI* e os transmite na rede *Ethernet*.

O *multipath iSCSI* fornece *failover*. No caso de falha de um caminho ou de qualquer um de seus componentes, o servidor seleciona outro caminho disponível. Além do *failover* de caminho, o balanceamento de carga de vários caminhos distribui as cargas de armazenamento em vários caminhos físicos para reduzir ou remover possíveis *bottlenecks* (uma aplicação possui um *bottleneck* quando está limitada por um único componente).

### Arquitetura

<img src="https://i.imgur.com/8dFEgyx.png" alt="drawing" width="500"/>

</details>

<details>
<summary>IPVS</summary>

### IP Virtual Server

O ***IPVS*** é incorporado no *LVS* (Linux Virtual Server), onde é executado num host e atua como um **balanceador de carga** na frente dum *cluster* de servidores reais. O *IPVS* pode direcionar solicitações de serviços baseados em *TCP* e *UDP* para os servidores reais e fazer com que os serviços dos servidores reais apareçam como serviços virtuais num único endereço IP.

### Vantagens

- Facilidades a serem simplificadas, economia de espaço, de tempo e custos.
- Gestão centralizada e compatibilidade total com aplicativos.
- Confiabilidade e disponibilidade - a falha do software não afeta os outros serviços.
- Balanceamento de carga: toda a máquina virtual é encapsulada. Assim, torna-se fácil mudar a plataforma da máquina virtual e aumentar seu desempenho.
- Redução de custos com pessoal, energia e refrigeração, uma vez que temos menos equipamento físico.

### Desvantagens

- Quando o servidor ficar offline, todos os sites hospedados por ele também ficarão inativos.
- Gestão - ambientes virtuais precisam ser instanciados (criar instâncias em máquinas virtuais), monitorizados, configurados e guardados.
- Desempenho - não existem métodos consolidados para medir o desempenho de ambientes virtualizados.
- Grande consumo de RAM já que cada máquina virtual ocupará uma área separada da mesma, e grande consumo de disco uma vez que guarda todos os arquivos de cada sistema operativo instalado em cada uma das máquinas virtuais.

### Arquitetura

<img src="https://i.imgur.com/P711jay.png" alt="drawing" width="500"/>

</details>

<details>
<summary>Keepalived</summary>

### Keepalived

O ***Keepalived*** fornece estruturas para **balanceamento de carga** e **alta disponibilidade**. A estrutura de balanceamento de carga depende do conhecido e amplamente usado módulo de kernel **Linux Virtual Server (IPVS)**. *Keepalived* implementa um conjunto de *health checkers* para manter e gerir de forma dinâmica e adaptativa *pools* de servidores com carga balanceada de acordo com sua "saúde". A alta disponibilidade é alcançada pelo *Virtual Redundancy Routing Protocol* (VRRP). *VRRP* é uma peça fundamental para *failover* do *router*.

### Arquitetura

<img src="https://i.imgur.com/oIUJcxp.png" alt="drawing" width="500"/>
</details>

<details>
<summary>High-Availability Cluster</summary>

### High-Availability Cluster

Um ***High-Availability Cluster*** é um grupo de hosts que age como um único sistema e fornece tempo de atividade contínuo.

Os *High-Availability Clusters* costumam ser usados para fins de **balanceamento de carga**, **backup** e ***failover***. Para configurar adequadamente um HA, todos os hosts do cluster devem ter acesso ao mesmo armazenamento partilhado. Isso permite que as VMs num determinado host façam *failover* para outro host sem qualquer tempo de inatividade em caso de falha.

Os clusters de HA podem variar de dois a dezenas de nós, mas os administradores de armazenamento devem ser cautelosos com o número de VMs e hosts que adicionam a um cluster de HA porque muitos podem complicar o balanceamento de carga.

### Arquitetura

<img src="https://i.imgur.com/wr8kcD3.png" alt="drawing" width="500"/>
<img src="https://i.imgur.com/rIICFmb.png" alt="drawing" width="500"/>

</details>