
import java.util.LinkedList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author junio
 */
public class Main {
    public static void Learning(List<Dados> dados, double weight[]){ 
           int isVerificar = 2;
           int i = 0, y = 0;
           while(isVerificar != 0){ 
               i = 0;
               isVerificar = 2;
               while(dados.size() > i){
                    y = CalculateY(weight, dados.get(i).getDados());
                    if(y == dados.get(i).getResultadoEsperado()){
                       isVerificar -=1;
                    }else{
                        BalanceWeights(weight, dados.get(i).getDados(), dados.get(i).getResultadoEsperado(),y);
                        isVerificar +=1;
                    }
                    i++;
               }
           }
        
    }
    public static void BalanceWeights(double weight[], int dados[], int resultadoEsperado, int resultadoDaConta){
           double neta = 0.4;
          
            for(int i = 0; i < 9; i++){
                if(i==0){
                   weight[0] = weight[0] + (neta*(1)*(resultadoEsperado - resultadoDaConta));
                }
                weight[i] = weight[i] + (neta*(dados[i])*(resultadoEsperado - resultadoDaConta));
            }
    }
    public static int CalculateY(double weight[],int dados[]){
          double valor = 0.0;
          int y;
          valor = 1 * weight[0];
          for(int i = 1; i < 9; i++){
              valor += weight[i] * dados[i];
          }
          
          if(valor > 0){
              y = 1;
          }else{
              y = -1;
          }
          return y;
    }
    public static void Teste(int dados[], double weight[]){
        int y = CalculateY(weight, dados);
        //T, O resultado esperado é 1
        if(y == 1){
            System.out.println("O T foi reconhecido");
        }else{
        //H, O resultado esperado é -1
            System.out.println("O H foi reconhecido");
        }
    }
    public static void main(String[] args) {
        int teste1[] = { 1,1,1,
                         0,1,0,
                         0,1,0
                              };
        int teste2[] = {
                        1,0,1,
                        1,1,1,
                        1,0,1
                       
                        };
        //T
        Dados d = new Dados();
        d.setDados(teste1);
        d.setResultadoEsperado(1);
        //H
        Dados d2 = new Dados();
        d2.setDados(teste2);
        d2.setResultadoEsperado(-1);
       
        
        List<Dados> dados = new LinkedList<Dados>();
        dados.add(d);
        dados.add(d2);
      
        //o primeiro valor aleatorio é o limiar, valores aleatório
        double weight[]= {-0.5,0.8,0.1,0.4,-0.2,0.9,-0.7,0.6,0.3,-0.8};
        System.out.println("Pesos antes do treinamento:");
        for(int i = 0; i < 10; i++){
            System.out.print(weight[i] + " ");
        }
        
        //Treinando a rede
        Learning(dados, weight);
        System.out.println(" ");
        System.out.println("Pesos depois do treinamento:");
        for(int i = 0; i < 10; i++){
            System.out.print(weight[i] + " ");
        }
        //1-Teste
        System.out.println(" ");
        System.out.println("-Passando um T como entrada. Se ele conheceR ele responde com a letra que foi passada:");
        Teste(teste1,weight);
        //2-Teste
        System.out.println("-Passando uma H como entrada:");
        Teste(teste2,weight);
        
     
    }
}
