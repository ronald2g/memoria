/**
 * formatear los mensajes para el intercambio
 * 
 * @author Ronald Gutierrez Quispe 
 * @version 0.4
 */

public class Mensaje
{
    Elemento raiz;
    
    public Mensaje(String cadena){capturar(cadena);}
    
    public Mensaje(){raiz=null;}
    
    public void asignar(String nombre,String valor)
    {
        raiz=elemento_insertar(raiz,nombre.split("\\."),0,valor);
    }
    
    public void capturar(String cadena)
    {
        raiz=prefijo_capturar(new Conjunto(cadena));
    }
    
    public String entregar()
    {
        if(raiz==null) return ""; else return prefijo_mostrar(raiz); 
    }
    
    public String toString()
    {
        return infijo_mostrar(raiz);
    }
    
    public String valor(String nombre)
    {
        return ((Lista)elemento_buscar(raiz,nombre.split("\\."),0)).valor;
    }
    
    public static Elemento elemento_buscar(Elemento a,String []b,int lugar)
    {
        while(a != null)
        {
            if(a.nombre.equals(b[lugar]))
            {
                if(++lugar<b.length)
                    return elemento_buscar(((Arbol)a).contenido,b,lugar);
                else
                    return a;
            }
            a = a.siguiente;
        }
        return null;
    }
    
    public static Elemento elemento_insertar(Elemento a,String b[],int lugar,String valor)
    {
        if(a==null)
        {
            if(lugar==b.length-1)
                return new Lista(b[lugar],valor,null);
            else
                return new Arbol(b[lugar],elemento_insertar(null,b,lugar+1,valor),null);
        }
        else
        {
            Elemento primero  = a;
            Elemento anterior = null;
            while(a!=null)
            {    
                if(a.nombre.equals(b[lugar]))
                {
                    if(lugar==b.length-1)
                        ((Lista)a).valor=valor;
                    else
                        elemento_insertar(((Arbol)a).contenido,b,lugar+1,valor);
                    return primero;
                }
                anterior = a;
                a = a.siguiente;
            }
        
            if(lugar==b.length-1)
                anterior.siguiente=new Lista(b[lugar],valor,null);
            else
                anterior.siguiente=new Arbol(b[lugar],elemento_insertar(null,b,lugar+1,valor),null);
            return primero;
        }
    }
    
    
    static String infijo_mostrar(Elemento a)
    {
        if(a==null) return "";
        else
        {
            if(a instanceof Arbol)
            {
                if(a.siguiente == null) return a.nombre+":{"+infijo_mostrar(((Arbol)a).contenido)+"}";
                else return a.nombre+":{"+infijo_mostrar(((Arbol)a).contenido)+"};"+infijo_mostrar(a.siguiente);
            }
            else
            {
                if(a.siguiente == null) return a.nombre+"="+((Lista)a).valor;
                else return a.nombre+"="+((Lista)a).valor+";"+infijo_mostrar(a.siguiente);
            }
        }
    }
    
    public static Elemento prefijo_capturar(Conjunto a)
    {
        
        if(a.lugar<a.trozo.length)
        {
            if(a.trozo[a.lugar].equals(":"))
            {
                a.lugar=a.lugar+2;
                return new Arbol(a.trozo[a.lugar-1],prefijo_capturar(a),null);
            }    
            else
            if(a.trozo[a.lugar].equals(";"))
            {
                a.lugar=a.lugar+1;
                Lista primero = (Lista)prefijo_capturar(a);
                primero.siguiente=prefijo_capturar(a);
                return primero;
            }
            else
            if(a.trozo[a.lugar].equals("="))
            {
                a.lugar=a.lugar+3;
                return new Lista(a.trozo[a.lugar-2],a.trozo[a.lugar-1],null);
            } 
        } 
        return null;
    }
    
    public static String prefijo_mostrar(Elemento a)
    {
        if(a instanceof Arbol)
        {
            if(a.siguiente == null) return ":|"+a.nombre+"|"+prefijo_mostrar(((Arbol)a).contenido);
            else return ";|:|"+a.nombre+"|"+prefijo_mostrar(((Arbol)a).contenido)+"|"+prefijo_mostrar(a.siguiente);
        }
        else
        {
            if(a.siguiente == null) return "=|"+a.nombre+"|"+((Lista)a).valor;
            else return ";|=|"+a.nombre+"|"+((Lista)a).valor+"|"+prefijo_mostrar(a.siguiente);
        }
    }
}

class Elemento
{
    String nombre;
    Elemento siguiente;
}

class Lista extends Elemento
{
    String valor;
    public Lista(String nombre,String valor,Elemento siguiente)
    {
        this.nombre    = nombre;
        this.valor     = valor;
        this.siguiente = siguiente;
    }
}

class Arbol extends Elemento
{
    Elemento contenido;
    public Arbol(String nombre,Elemento contenido,Elemento siguiente)
    {
        this.nombre    = nombre;
        this.contenido = contenido;
        this.siguiente = siguiente;
    }
}

class Conjunto
{
    public int lugar;
    public String [] trozo;
    public Conjunto(String cadena)
    {
        this.lugar=0;
        this.trozo=cadena.split("\\|");
    }
}
