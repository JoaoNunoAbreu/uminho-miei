import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.awt.Point;
public class main{
    public static void main (String args[]){
        UMCarroJa ucj = new UMCarroJa(); int escolha;
        while(true){
            escolha = IO.menu();
            if(escolha == 0) break;
            switch(escolha){
                case 1: // Carregar dados iniciais
                    ucj.carregamentoInicial("logsPOO_carregamentoInicial.bak");
                    break;
                case 2: // Registar um proprietário 
                    Proprietario p = IO.novoProp();
                    ucj.addProp(p);
                    break;
                case 3: // Registar um cliente
                    Cliente c = IO.novoCli();
                    ucj.addCli(c);
                    break;
                case 4: // Login proprietário
                    Proprietario prop = null;
                    try{
                        prop = IO.logInProprietarios(ucj);
                    }
                    catch(WrongPasswordException e){System.out.println(e);}
                    if(prop != null){
                        int escolha_prop = IO.menuProp();
                        Carro car = null;
                        switch(escolha_prop){
                            case 1: // Registar viatura
                                try{
                                    car = IO.novoCarro(prop);
                                }   
                                catch(NullPointerException e){System.out.println("Tipo de carro inserido inválido");}
                                if(car != null){
                                    ucj.addCar(car);
                                    ucj.getProprietarios().get(prop.getNif()).addCarro(car);
                                }
                                break;
                            case 2: // Classificar cliente após aluguer
                                double classificacao = IO.novaClassificacao("do cliente:");
                                Cliente client = IO.cliente(ucj);
                                try{
                                    prop.classificar(classificacao,ucj.getClientes().get(client.getNif()));
                                }
                                catch(ActorNotFoundException e){System.out.println(e);}
                                try{
                                    IO.write("logsPOO_resultados.bak","Classificar:" + client.getNif() + classificacao);
                                }
                                catch(IOException e){System.out.println("Erro a escrever no ficheiro");}
                                break;
                            case 3: // Histórico de alugueres entre data
                                LocalDate data1 = IO.data();
                                LocalDate data2 = IO.data();
                                List<Aluguer> res = prop.getHist_entreDatas(data1,data2);
                                System.out.println(res.toString());
                                break;
                            case 4: // Sinalizar que um carro está disponível/indisponível
                                try{
                                    car = IO.carro(ucj);
                                }
                                catch(CarroNotFoundException e){System.out.println(e);}
                                if(car!=null) prop.sinaliza(ucj,car,IO.disponibilidade());
                                break;
                            case 5: // Abastecer um carro
                                try{
                                    car = IO.carro(ucj);   
                                }
                                catch(CarroNotFoundException e){System.out.println(e);}
                                if(car!=null)prop.abastecer(ucj,car,IO.autonomia());
                                break;
                            case 6: // Alterar o preço por km
                                try{
                                    car = IO.carro(ucj);
                                }
                                catch(CarroNotFoundException e){System.out.println(e);}
                                if(car!=null)prop.alterarPrecoKm(ucj,car,IO.precoPorKm());
                                break;
                            case 7: // Ver quanto custaram as viagens
                                for(Aluguer a: prop.getHist())
                                    System.out.println(a.getPreco());
                                break;
                        }
                    }
                    break;
                case 5: // Login cliente
                    Cliente client = null;
                    try{
                        client = IO.logInClientes(ucj);
                    }
                    catch(WrongPasswordException e){System.out.println(e);}
                    if(client != null){
                        int escolha_client = IO.menuClient();
                        switch(escolha_client){
                            case 1: // Solicitar um aluguer. Se este for aceite, classificar carro e proprietário
                                int escolha_aluguer = IO.menuTipoAlugueres();
                                Carro escolhido = null; String tipo = null; Carro especific = null;
                                String preferencia = null;
                                Point.Double dest = IO.ponto();
                                try{
                                    switch(escolha_aluguer){
                                        case 1:
                                            escolhido = client.carroMaisPerto(ucj,dest);
                                            preferencia = "MaisPerto";
                                            break;
                                        case 2:
                                            tipo = IO.tipo();
                                            escolhido = client.carroMaisPerto(ucj,tipo,dest);
                                            preferencia = "MaisPerto";
                                            break;
                                        case 3:
                                            escolhido = client.carroMaisBarato(ucj,dest);
                                            preferencia = "MaisBarato";
                                            break;
                                        case 4:
                                            tipo = IO.tipo();
                                            escolhido = client.carroMaisBarato(ucj,tipo,dest);
                                            preferencia = "MaisBarato";
                                            break;
                                        case 5:
                                            double dist = IO.distanciaPercorrivel();
                                            escolhido = client.carroMaisBaratoDistancia(ucj,dest,dist);
                                            preferencia = "PercorrivelAPé";
                                            break;
                                        case 6:
                                            try{
                                                especific = IO.carro(ucj);
                                            }
                                            catch(CarroNotFoundException e){System.out.println(e);}
                                            if(especific!=null){
                                                escolhido = client.carroEspecifico(ucj,dest,especific);
                                                preferencia = "CarroEspecífico";
                                            }
                                            break;
                                        case 7:
                                            try{
                                                especific = IO.carro(ucj);
                                            }
                                            catch(CarroNotFoundException e){System.out.println(e);}
                                            double autonomia = IO.autonomia();
                                            if(especific!=null){
                                                escolhido = client.carroEspecificoAutonomia(ucj,dest,especific,autonomia);
                                                preferencia = "CarroEspecífico";
                                            }
                                            break;
                                        }
                                        Aluguer a = new Aluguer(client.getPosicao(),dest,escolhido.preco(dest),escolhido.tempo(dest),client.getNif(),LocalDate.now(),preferencia);
                                        if(escolhido.getProprietario(ucj).decideAluguer(a,escolhido)){
                                            // NOTA: É preciso ir buscar o próprio objecto e não uma cópia sua
                                            
                                            // Parte de classificar o carro 
                                            double classificacao1 = IO.novaClassificacao("do carro:");
                                            client.classificar(classificacao1,ucj.getCarros().get(escolhido.getMatricula()));
                                        
                                            // Parte de classificar o proprietário
                                            double classificacao2 = IO.novaClassificacao("do proprietário:");
                                            client.classificar(classificacao2,ucj.getCarros().get(escolhido.getMatricula()).getProprietario(ucj));

                                            // Altera posições
                                            ucj.getClientes().get(client.getNif()).setPosicao(dest);
                                            ucj.getCarros().get(escolhido.getMatricula()).setPosicao(dest);
                                        
                                            // Altera autonomia
                                            ucj.getCarros().get(escolhido.getMatricula()).setAutonomia(escolhido.novaAutonomia(dest));
                                        
                                            // Adiciona aos históricos de alugueres
                                            ucj.getClientes().get(client.getNif()).addAluguer(a);
                                            ucj.getCarros().get(escolhido.getMatricula()).addAluguer(a);
                                            escolhido.getProprietario(ucj).addAluguer(a);
                                            
                                            // Adiciona as operações ao ficheiro de logs
                                            try{
                                                IO.write("logsPOO_resultados.bak","Aluguer:" + a.toCSV());
                                                IO.write("logsPOO_resultados.bak","Classificar:" + escolhido.getMatricula() + classificacao1);
                                                IO.write("logsPOO_resultados.bak","Classificar:" + escolhido.getProprietario(ucj).getNif() + classificacao2);
                                            }
                                            catch(IOException e){System.out.println("Erro ao escrever no ficheiro");}
                                        }
                                    }
                                catch(NullPointerException e){System.out.println("Segundo os critérios inseridos, nenhum carro consegue fazer a viagem");}
                                break;
                            case 2:
                                LocalDate data1 = IO.data();
                                LocalDate data2 = IO.data();
                                List<Aluguer> res = client.getHist_entreDatas(data1,data2);
                                System.out.println(res.toString());
                                break;
                        }
                    }
                    break;
                case 6: // Total facturado por uma viatura num determinado período
                    Carro car2 = null;
                    try{
                        car2 = IO.carro(ucj);
                    }
                    catch(CarroNotFoundException e){System.out.println(e);}
                    if(car2 != null){
                        LocalDate data1 = IO.data();
                        LocalDate data2 = IO.data();
                        double res = car2.facturadoEntreDatas(data1,data2);
                        System.out.println(res);
                    }
                    break;
                case 7: // Listagem dos 10 clientes que mais utilizam o sistema (em número de vezes e em kms percorridos)
                    int escolha_top10 = IO.menuTopClient();
                    switch(escolha_top10){
                        case 1:
                            List<Cliente> l1 = ucj.bestClients(new ComparatorKms());
                            for(Cliente x : l1){
                                System.out.println(x.getNome());
                                System.out.println(x.kmsPercorridos());
                            }   
                            break;
                        case 2: 
                            List<Cliente> l2 = ucj.bestClients(new ComparatorNumVezes());
                            for(Cliente x : l2){
                                System.out.println(x.getNome());
                            }
                            break;
                    }
                    break;
                case 8: // Gravar o estado da aplicação em ficheiro
                    try{
                        ucj.guardaEstadoClientes();
                        ucj.guardaEstadoProprietarios();
                        ucj.guardaEstadoCarros();
                    }
                    catch(IOException e){System.out.println("Erro na escrita do ficheiro");}
                    break;
                case 9: // Carregar o estado da aplicação a partir de ficheiro
                    try{
                        ucj.setClientes(UMCarroJa.carregaEstadoClientes());
                        ucj.setProprietarios(UMCarroJa.carregaEstadoProprietarios());
                        ucj.setCarros(UMCarroJa.carregaEstadoCarros());
                    }
                    catch(IOException e){System.out.println("Erro na escrita do ficheiro");}
                    catch(ClassNotFoundException e){System.out.println("Classe incorrecta");}
                    break;
                case 10: // Mostrar estado da aplicação
                    System.out.println(ucj.toString());
                    break;
            }
        }
    }
}