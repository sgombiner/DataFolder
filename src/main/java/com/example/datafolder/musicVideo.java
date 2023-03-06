package com.example.datafolder;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class musicVideo extends music implements Serializable {
    final private String youtubeUploader;
    private int ranking;


    public musicVideo(String name, float interactions, String released, int ranking, String youtubeUploader) {
        super(name, interactions, released);
        this.setRanking(ranking);
        this.youtubeUploader = youtubeUploader;
    }

    public String getYoutubeUploader() {
        return youtubeUploader;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

  public String toString() {
       return"The song " + getName() + " was ranked number " + ranking + " out off all music videos on youtube with " + getInteractions() + " billion views. It was uploaded on " + youtubeUploader + "‘s account on " + getReleased() + ".";
    }

    static void readAllData() throws Exception {
        Scanner sc = new Scanner(new File("src/main/java/com/example/datafolder/musicVideoData"));
        while (sc.hasNext()) {
            String str = sc.nextLine();
            Scanner lineScanner = new Scanner(str);
            lineScanner.useDelimiter("\t");

            String ranking = lineScanner.next();
            String rankingClean = ranking.replace(".","");
            int rankingNumber = Integer.parseInt(rankingClean);

            String name = lineScanner.next();
            //name not being used but ranking
            String nameClean = name.replace("\"","");
            int firstBracketLocation = nameClean.indexOf("[");
            if (firstBracketLocation >= 0) {
                nameClean = nameClean.substring(0, firstBracketLocation);
            }

            String youtubeUploader = lineScanner.next();

            float interactions = lineScanner.nextFloat();

            String released = lineScanner.next();

            new musicVideo(nameClean,interactions,released,rankingNumber,youtubeUploader);
        }
        sc.close();
    }

}