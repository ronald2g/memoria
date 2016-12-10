
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
        
    public void llenar()
    {
        int largo=n*m;
        int [] linea = new int [100];
        for(int i=0;i<largo;i++)
        {
            linea[i]=i;
        }
        
        Random aleatorio = new Random();
        int lugar,valor;
        System.out.println("************");
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

