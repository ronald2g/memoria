
/**
 * Write a description of class Base here.
 * 
 * @author Ronald Gutierrez Quispe
 * @version 0.1
 */

import java.util.Random;

public class Base
{
    public void llenar()
    {
        Malla a=new Malla(3,3);
        a.llenar();
        a.mostrar();
    }
}

class Malla
{
    int n,m;
    int [][] malla;
    public Malla(int n,int m)
    {
        this.n=n;
        this.m=m;
        malla=new int[n][m];
    }   
        
    // llena la matriz con elementos pares
    public void llenar()
    {
        // llenar el vector 'linea' con la serie 0,1,2,3,4,5...
        int largo=n*m;
        int [] linea = new int [largo];
        for(int i=0;i<largo;i++)
        {
            linea[i]=i;
        }
        
        // seleccionar un elemento del vector aleatoriamente
        // reeplazarlo con el ultimo elemento del vector
        // y reducir el tamaño del vector en 1
        Random aleatorio = new Random();
        int lugar,valor;
        float contador=1.0f;
        for(int i=0;i<largo;i++)
        {
             lugar = aleatorio.nextInt(largo-i);
             valor = linea[lugar];
             linea[lugar] = linea[largo-i-1];
             malla[valor/m][valor%m] = (int)contador;
             contador+=0.5;
        }
    }
    
    public void mostrar()
    {
        for(int j=0;j<n;j++)
        {
            for(int i=0;i<m;i++)
                System.out.print(malla[j][i]+" ");
            System.out.println();
        }
    }
}

