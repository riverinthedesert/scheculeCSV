package schedule;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private Vertex debut;
    private HashMap<String,Vertex> lesVertex;
    private Vertex fin;

    /**
     * la graph
     */
    public Graph(){
        this.debut=new Vertex("debut",0,0,0,0,0,0,false);
        this.fin=new Vertex("fin",0,0,0,0,0,0,false);
        this.lesVertex=new HashMap<>(100000);
    }

    /**
     * ajout le noeud
     * @param u vertex
     */
    public void add_node(Vertex u){
        this.lesVertex.put(u.getNom(),u);
    }

    /**
     * ajouter arrow
     * @param u vertex precedent
     * @param v vertex succeseur
     */
    public void add_arrow(Vertex u,Vertex v){
        u.ajouterSucceseur(v);
        v.ajouterPredecesseur(u);
    }

    /**
     * @param nom identifiant
     * @return vertex
     */
    public Vertex getVertex(String nom){
        return this.lesVertex.get(nom);
    }

    /**
     * @return vertex debut
     */
    public Vertex getDebut() {
        return debut;
    }

    /**
     * @return vertex fin
     */
    public Vertex getFin() {
        return fin;
    }

    /**
     * @return vertex milieu
     */
    public HashMap<String, Vertex> getLesVertex() {
        return lesVertex;
    }
}
