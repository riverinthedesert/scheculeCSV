package schedule;

import java.io.*;
import java.util.ArrayList;


public class Schedule {
    private Graph graph;
    private String nomCSV;

    /**
     * creer schedule
     * @param nom nom de fichier
     */
    public Schedule(String nom) {
        this.nomCSV=nom;
    }

    /**
     * lecture pour tous les noeud
     */
    public void lectureNoeud(){
        String line = null;
        String csvSplitCol = ";";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(nomCSV));
            while ((line = reader.readLine()) != null) {
                // on ne seulement consider que premier et deuxieme col ce fois
                String[] Cols = line.split(csvSplitCol);
                this.graph.add_node(new Vertex(Cols[0],Integer.parseInt(Cols[1]),0,0,0,0,0,false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * lecture pour tous les arrow
     */
    public void lectureArrow(){
        String line = null;
        String csvSplitCol = ";";
        String csvSplitmot = ",";

        try {
            InputStream f = new FileInputStream (nomCSV);
            BufferedReader reader = new BufferedReader(new InputStreamReader(f));
            while ((line = reader.readLine()) != null) {
                String[] Cols = line.split(csvSplitCol);
                // on seulement consider troisieme col ce fois
                if(Cols.length>2) {
                    String[] tache = Cols[2].split(csvSplitmot);
                    for(String nomPrece:tache)
                    {
                        this.graph.add_arrow(this.graph.getVertex(nomPrece),this.graph.getVertex(Cols[0]));
                    }
                }
                else{
                    // tous les noued qui n'a pas de precedent sont connectes a debut
                    this.graph.add_arrow(this.graph.getDebut(),this.graph.getVertex(Cols[0]));
                }
            }
            // tous les noued qui n'a pas de succeseur sont connectes a fin
            for(Vertex v:this.graph.getLesVertex().values()){
                if(v.getSucceseur().size()==0){
                    this.graph.add_arrow(v,this.graph.getFin());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * boule pour calculuer dateplustot
     * @param v le noeud qui commence
     */
    public void CalculerPlutot(Vertex v){
      //  System.out.println(v.getNom());
        int MaxTot = Integer.MIN_VALUE;
        for (Vertex u : v.getPredecesseurs()) {
            if (u.getDateplutot() + u.getDuree() > MaxTot) {
                MaxTot = u.getDateplutot() + u.getDuree();
            }
        }
        v.setDateplutot(MaxTot);

        //si tous les predecesseur sont visited ce oeud est aussi visited
        boolean TousPrece=true;
        for(Vertex w:v.getPredecesseurs()) {
            if(!w.isVisited()) {
                TousPrece = false;
            }
        }
        v.setVisited(TousPrece);


        for(Vertex s:v.getSucceseur()){
            if(!s.isVisited()) {
                CalculerPlutot(s);
            }
        }
    }

    /**
     * procedure qui s'applique a toute la graphe
     */
    public void Plutot(){
        this.graph.getDebut().setVisited(true);
        for(Vertex v:this.graph.getDebut().getSucceseur()){
            CalculerPlutot(v);
        }

        // remettre
        this.graph.getFin().setVisited(false);
        for(Vertex u:this.graph.getLesVertex().values()){
            u.setVisited(false);
        }
        this.graph.getDebut().setVisited(false);
    }

    /**
     * boule pour calculuer dateplutard
     * @param v le noeud qui commence
     */
    public void CalculerPlutard(Vertex v){
        int MinTard = Integer.MAX_VALUE;
        for (Vertex u : v.getSucceseur()) {
            if (u.getDateplutard() < MinTard) {
                MinTard = u.getDateplutard();
            }
        }
        v.setDateplutard(MinTard - v.getDuree());


        //si tous les sucesseur sont visited ce oeud est aussi visited
        boolean TousSucc=true;
        for(Vertex w:v.getSucceseur()) {
            if(!w.isVisited()){
                TousSucc=false;
            }
        }
        v.setVisited(TousSucc);



        for(Vertex s:v.getPredecesseurs()){
            if(!s.isVisited()) {
                CalculerPlutard(s);
            }
        }
    }

    /**
     * procedure qui s'applique a toute la graphe
     */
    public void Plutard(){
        this.graph.getFin().setVisited(true);
        for(Vertex v:this.graph.getFin().getPredecesseurs()){
            CalculerPlutard(v);
        }

        // remettre
        this.graph.getFin().setVisited(false);
        for(Vertex u:this.graph.getLesVertex().values()){
            u.setVisited(false);
        }
        this.graph.getDebut().setVisited(false);
    }

    /**
     * procedure qui calcule marge totale
     */
    public void CalculerMT(){
        for(Vertex v:this.graph.getLesVertex().values()){
            v.setMargeTotale(v.getDateplutard()-v.getDateplutot());
        }
    }


    /**
     * procedure qui calcule marge libre
     */
    public void CalculerML(){
        for(Vertex v:this.graph.getLesVertex().values()){
            int Minplutot=Integer.MAX_VALUE;
            for(Vertex u:v.getSucceseur()){
                if(u.getDateplutot()<Minplutot){
                    Minplutot=u.getDateplutot();
                }
            }
            v.setMargeLibre(Minplutot-v.getDateplutot()-v.getDuree());
        }

    }

    /**
     * procedure qui calcule marge certain
     */
    public void CalculerMC(){
        for(Vertex v:this.graph.getLesVertex().values()){
            int Minplutot=Integer.MAX_VALUE;
            int Maxplutard=Integer.MIN_VALUE;
            for(Vertex u:v.getSucceseur()){
                if(u.getDateplutot()<Minplutot){
                    Minplutot=u.getDateplutot();
                }
            }
            for(Vertex w:v.getPredecesseurs()){
                if(w.getDateplutard()+w.getDuree()>Maxplutard){
                    Maxplutard=w.getDateplutard()+w.getDuree();
                }
            }
            if(Minplutot-v.getDateplutard()-v.getDuree()<0){
                v.setMargeCertaine(0);
            }
            else {
                v.setMargeCertaine(Minplutot - Maxplutard - v.getDuree());
            }
        }
    }

    /**
     * ecriture pour tous les tache dans graphe
     */
    public void ecriture(){
        // creer le fichier
        char[] nom=nomCSV.toCharArray();
        StringBuffer sb = new StringBuffer();
        int i=0;
        while (nom[i] != '.') {
            sb.append(nom[i]);
            i++;
        }
        sb.append("_solved.csv");
        File writeFile = new File(sb.toString());

        try{
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            writeText.write(this.graph.getDebut().getNom()+";"+this.graph.getDebut().getDuree()+";"+this.graph.getDebut().getDateplutot()+";"+this.graph.getDebut().getDateplutard()+";"+this.graph.getDebut().getMargeTotale()+";"+this.graph.getDebut().getMargeLibre()+";"+this.graph.getDebut().getMargeCertaine());
            writeText.newLine();
            writeText.write(this.graph.getFin().getNom()+";"+this.graph.getFin().getDuree()+";"+this.graph.getFin().getDateplutot()+";"+this.graph.getFin().getDateplutard()+";"+this.graph.getFin().getMargeTotale()+";"+this.graph.getFin().getMargeLibre()+";"+this.graph.getFin().getMargeCertaine());

            for(Vertex u:this.graph.getLesVertex().values()){
                writeText.newLine();
                writeText.write(u.getNom()+";"+u.getDuree()+";"+u.getDateplutot()+";"+u.getDateplutard()+";"+u.getMargeTotale()+";"+u.getMargeLibre()+";"+u.getMargeCertaine());
            }

            writeText.flush();
            writeText.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * observer tous la csv
     */
    public void Observer(){
        //creerGraphe
        this.graph=new Graph();
        lectureNoeud();
        lectureArrow();



        // d'abord on commence par calcluer plutot
        Plutot();
        // plutot egale a plustard en fin
        this.graph.getFin().setDateplutard(this.graph.getFin().getDateplutot());
        // ensuite on commence par calcluer plutard dan l'ordre inverse
        Plutard();



        // calculer tous les marges
        CalculerMT();
        CalculerML();
        CalculerMC();

        //ecriture tous les taches dans fichier csv
        ecriture();

        System.out.println("travail fini");
    }


}
