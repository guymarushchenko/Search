package sample.Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Indexer {
    private HashMap<String, String[]> dictionary;
    private HashMap<Character, String> filesNames;
    private int currentPostFile;

    public Indexer() {
        currentPostFile = 0;
        dictionary = new HashMap<>();
        filesNames = new HashMap<>();
    }

    public void addAllTerms(HashMap<String, Term> allTerms, String path) {
        path = "/home/guy/Desktop/post/";
        Object[] sortedterms = allTerms.keySet().toArray();
        Arrays.sort(sortedterms);
        StringBuilder line = new StringBuilder();
        List<String> lines = new LinkedList<>();
        for (int i = 0; i < sortedterms.length; i++) {
            line.append("<" + sortedterms[i] + ":" + allTerms.get(sortedterms[i]).getInDocuments().size() + ";");
            Iterator it = allTerms.get(sortedterms[i]).getInDocuments().entrySet().iterator();
            int previous = 0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                line.append((int) pair.getValue() - previous + ",");
                it.remove();
            }
            line.deleteCharAt(line.toString().length() - 1);
            lines.add(line.toString());
            line.setLength(0);
        }
        path += "post" + currentPostFile + ".txt";
        Path file = Paths.get(path);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (Exception e) {
            System.out.println("cannot write");
        }
        currentPostFile++;
    }

    public void mergeAllPostFiles() {
        Scanner[] scanners = new Scanner[currentPostFile];
        String[] currentLine = new String[currentPostFile];
        FileWriter fw = null;
        String toWrite = "";
        int currentIndex = 0;
        try {
            fw = new FileWriter("/home/guy/Desktop/final.txt");
        } catch (Exception e) {
            System.out.println("Failed to create file writer");
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < currentPostFile; i++) {
            try {
                scanners[i] = new Scanner(new File("/home/guy/Desktop/post/post" + i + ".txt"));
            } catch (Exception e) {
                System.out.println("Failed to create a scanner");
            }
            currentLine[i] = scanners[i].nextLine();
        }

        while (true) {
            toWrite = "~";
            for (int i = 0; i < currentPostFile; i++) {
                if(!currentLine[i].equals("~")) {
                    String toCompare = currentLine[i].substring(0, currentLine[i].indexOf(':'));
                    if (toWrite.compareTo(currentLine[i]) > 0) {
                        toWrite = currentLine[i];
                        currentIndex = i;
                    }
                }
            }
            if (scanners[currentIndex].hasNext())
                currentLine[currentIndex] = scanners[currentIndex].nextLine();
            else {
                currentLine[currentIndex] = "~";
            }
            if (!toWrite.equals("~"))
                out.println(toWrite);
            else
                break;
        }
        out.close();
    }
}