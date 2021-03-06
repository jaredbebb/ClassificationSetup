package file;

import lin.StringFormat;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Reading {
    String headers;
    private BufferedReader in;
    private String file;
    private Dictionary dict;

    public Reading(String file) {
        this.file = file;
        dict = new Dictionary();
    }

    private void open(String file) {
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildDictionary() {
        open(file);
        String line = "";
        try {
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count > 0) {
                    String text = getText(line, 1);
                    if (!text.equals("")) {
                        String[] words = text.split(" ");
                        for (String word : words) {
                            dict.put(format(word));
                        }
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
        dict.setList();
    }

    public void buildDictionary(boolean format) {
        if (!format) {
        }
        StringFormat f;
        open(file);
        String line = "";
        try {
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count > 0) {
                    f = new StringFormat(line);
                    line = f.Get();
                    String text = getText(line, 1 ,true);
                    if (!text.equals("")) {
                        String[] words = text.split(" ");
                        for (String word : words) {
                            word = f.stem(word);
                            dict.put(format(word));
                        }
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
        dict.setList();
    }

    public Dictionary getDict() {
        return dict;
    }

    /**
     * @param in
     * @param position
     * @return
     */
    public String getText(String in, int position) {
        String[] chunks = in.split(",");
        return chunks[position];
    }

    public String getText(String in, int position, boolean format) {
        StringFormat f;
        String[] chunks = in.split(",");


        f = new StringFormat(chunks[position]);
        f.RemoveStopWords();
        chunks[position] = f.Get();
        String[] text = chunks[position].split(" ");
        for (int word = 0; word < text.length; word++) {
            String curr = text[word];
            text[word] = f.stem(text[word]);
        }
        String texts ="";
        for(String word: text){
            texts = texts+" "+word;
        }
        chunks[position] = texts.trim();
        return chunks[position];
    }

    public String readLine(String line) {
        Set<String> set = new HashSet<String>();
        String csv = getText(line, 0);
        String text = getText(line, 1);
        String decision = getText(line, 2);
        if (csv.equals("") || text.equals("")) {
            return null;
        }
        String[] words = text.split(" ");
        for (String word : words) {
            set.add(word);
        }
        for (String word : dict.getList()) {
            if (set.contains(word)) {
                csv = csv + ",TRUE";
            } else {
                csv = csv + ",FALSE";
            }
        }
        csv = csv + "," + decision;
        return csv;
    }

    public String readLine(String line, boolean format) {
        Set<String> set = new HashSet<String>();
        String csv = getText(line, 0);
        String text = getText(line, 1,format);
        String decision = getText(line, 2);
        if (csv.equals("") || text.equals("")) {
            return null;
        }
        String[] words = text.split(" ");
        for (String word : words) {
            set.add(word);
        }
        for (String word : dict.getList()) {
            if (set.contains(word)) {
                csv = csv + ",TRUE";
            } else {
                csv = csv + ",FALSE";
            }
        }
        csv = csv + "," + decision;
        return csv;
    }

    public void printLines() {
        open(file);
        String line = "";
        try {
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (count > 0) {
                    System.out.println(readLine(line));
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    /**
     * @param out the file output
     */
    public void writeLines(String out) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(out, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        open(file);
        String line = "";
        try {
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (!getText(line, 1).equals("")) {
                    if (count > 0) {
                        pw.println(readLine(line));
                    } else {
                        System.out.println("<" + getText(line, 0) + ">" + "<" + getDict().getHeaders() + ">" + "," + "<" + getText(line, 2) + ">");
                        pw.println(getText(line, 0,true) + "," + getDict().getHeaders() + "," + getText(line, 2));
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();
        close();
    }
    public void writeLines(String out, boolean format) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(out, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        open(file);
        String line = "";
        try {
            int count = 0;
            while ((line = in.readLine()) != null) {
                if (!getText(line, 1).equals("")) {
                    if (count > 0) {
                        pw.println(readLine(line,true));
                    } else {
                        System.out.println("<" + getText(line, 0) + ">" + "<" + getDict().getHeaders() + ">" + "," + "<" + getText(line, 2) + ">");
                        pw.println(getText(line, 0) + "," + getDict().getHeaders() + "," + getText(line, 2));
                    }
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.close();
        close();
    }


    /**
     * Uses various string regex
     * String is returned in uppercase, no non-letter characters,
     * no consecutive whitespace, or leading and trailing whitespaces
     *
     * @param in String
     * @return String
     */
    public String format(String in) {
        String out = in.toUpperCase();
        out = out.replaceAll("[^A-Z ]", "");
        out = out.replaceAll(" +", " ");
        out = out.trim();
        return out;
    }

}
