/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;


/**
 *
 * @author gutierre
 */
public class Pds {
    DecimalFormat deci = new DecimalFormat("0.0000");
    private float vmax, vmin, dq, xn, xt, c, max, s, niv[], erro, xq;
    private int n[], i, cont, j, l, dec;
    int nt;
    int m[];
       
        public int numeroA(){
           Scanner entrada = new Scanner(System.in);
           System.out.println("repita o numero de pocições: ");
           nt = entrada.nextInt();
        return nt;
        }
       public int coletarDados(){
           Scanner entrada = new Scanner(System.in);
           System.out.println("Digite o valor de vmax: ");
           vmax = entrada.nextFloat();
           System.out.println("Digite o valor de vmin: ");
           vmin = entrada.nextFloat();
           return nt;
       }
       public float intervalo(){ 
           s = vmin;
           c= vmax;
        dq = (vmax - vmin)/nt;
        System.out.println("o intervalo é " + dq);
            return 0;
       }
        public float amostragem(float[] niv, float [] m) throws IOException{
            FileWriter arq3 = new FileWriter("C:\\programapds\\pds.txt");
            PrintWriter gravarArq3 = new PrintWriter(arq3);
            
            FileWriter arq = new FileWriter("C:\\programapds\\amostragem.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            
            FileWriter arq1 = new FileWriter("C:\\programapds\\quantizacao.txt");
            PrintWriter gravarArq1 = new PrintWriter(arq1);
            
            FileWriter arq2 = new FileWriter("C:\\programapds\\erro.txt");
            PrintWriter gravarArq2 = new PrintWriter(arq2);
            
            l = nt-1;
            for(i=0; i<nt; i++){
                niv[i] = (vmax + (vmax-dq))/2;
                m[i]=l;
                l--;
                vmax= vmax-dq;
            }
            l=0;
            gravarArq3.printf("|  n |   x[n]   |   xq[n] |  xe[n]  |  dec |%n");
            for(i=0; i<nt; i++){
                vmax=c;
                 xt = (float) Math.sin(2*Math.PI*i/3);
                 if(xt>-0.00000001 && xt<0){
                     xt=0;
                 }
               cont=0;
               for(j=0; j<nt; j++){
                    if(xt<vmax && xt>=(vmax-dq)){
                        xq = niv[cont];
                        dec = (int) m[cont];
                        erro= xt- niv[cont];
                        gravarArq3.printf("| %d  |  %.4f  | %.4f  | %.4f  | %d  |%n" ,l,  xt, xq, erro, dec);
                        
                        gravarArq.printf(" %.4f  %n",xt);
               
                        
                        gravarArq1.printf("  %.4f  %n", xq);
                        
         
                        gravarArq2.printf("  %.4f  %n", erro);
                        
                        l++;
                    }
                    vmax= vmax-dq;
                    cont++; 
                 }
            }
            arq2.close();
            arq1.close();
            arq.close();
            arq3.close();
           return 0;
       }
    public static void main(String[] args) throws IOException {
       Pds x = new Pds();
       int n = 0;
       n= x.numeroA();
       float v[] = new float[n];
       float h[] = new float[n];
        x.coletarDados();
        x.intervalo();
        x.amostragem(v, h);   
    }
}
