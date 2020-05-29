package schedule;

import java.util.ArrayList;

public class Vertex {
    private String nom;
    private int duree;
    private int dateplutot;
    private int dateplutard;
    private int margeTotale;
    private int margeLibre;
    private int margeCertaine;
    private boolean visited;
    private ArrayList<Vertex> succeseur;
    private ArrayList<Vertex> predecesseur;

    /**
     * contruction vertex
     * @param nom identifiant
     * @param duree duree
     * @param dateplutot dateplustot
     * @param dateplutard dateplustard
     * @param margeTotale marge totale
     * @param margeLibre marge libre
     * @param margeCertaine marge certaine
     * @param visited boolean parcours
     */
    public Vertex(String nom,int duree,int dateplutot,int dateplutard,int margeTotale,int margeLibre,int margeCertaine,boolean visited){
        this.nom=nom;
        this.duree=duree;
        this.dateplutot=dateplutot;
        this.dateplutard=dateplutard;
        this.margeTotale=margeTotale;
        this.margeLibre=margeLibre;
        this.margeCertaine=margeCertaine;
        this.visited=visited;
        this.succeseur=new ArrayList<>(100);
        this.predecesseur=new ArrayList<>(100);
    }

    /**
     * ajouter succeseur
     * @param v succeseur
     */
    public void ajouterSucceseur(Vertex v){
        this.succeseur.add(v);
    }

    /**
     * ajouter predesesseur
     * @param v predecesseur
     */
    public void ajouterPredecesseur(Vertex v){
        this.predecesseur.add(v);
    }

    /**
     * obtenir nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * obtenir dateplutard
     * @return dateplutard
     */
    public int getDateplutard() {
        return dateplutard;
    }

    /**
     * obtenir duree
     * @return duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * obtenir dateplutot
     * @return dateplutot
     */
    public int getDateplutot() {
        return dateplutot;
    }

    /**
     * obtenir margetotale
     * @return margetotale
     */
    public int getMargeTotale() {
        return margeTotale;
    }

    /**
     * obtenir margelibre
     * @return margelibre
     */
    public int getMargeLibre() {
        return margeLibre;
    }

    /**
     * obtenir margecertaine
     * @return margecertaine
     */
    public int getMargeCertaine() {
        return margeCertaine;
    }

    /**
     * si il est parcours
     * @return boolean parcours
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * obtenir succeseur
     * @return succeseur
     */
    public ArrayList<Vertex> getSucceseur() {
        return succeseur;
    }

    /**
     * obtenir predecesseur
     * @return predecesseur
     */
    public ArrayList<Vertex> getPredecesseurs() {
        return predecesseur;
    }

    /**
     * changer dateplutard
     * @param dateplutard dateplutard
     */
    public void setDateplutard(int dateplutard) {
        this.dateplutard = dateplutard;
    }

    /**
     * changer dateplutot
     * @param dateplutot dateplutot
     */
    public void setDateplutot(int dateplutot) {
        this.dateplutot = dateplutot;
    }

    /**
     * changer margetotale
     * @param margeTotale margetotale
     */
    public void setMargeTotale(int margeTotale) {
        this.margeTotale = margeTotale;
    }

    /**
     * changer margelibre
     * @param margeLibre margelibre
     */
    public void setMargeLibre(int margeLibre) {
        this.margeLibre = margeLibre;
    }

    /**
     * changer margecertaine
     * @param margeCertaine margecertaine
     */
    public void setMargeCertaine(int margeCertaine) {
        this.margeCertaine = margeCertaine;
    }

    /**
     * changer etate parcours
     * @param visited etat parcours
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
