package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Quotes> quotesList = new ArrayList<>();
        String comm = "";
        int id = 1;

        System.out.println("== 명언 앱 ==");

        while (!comm.equals("종료")) {
            System.out.print("명령) ");
            comm = br.readLine();
            int index = -1;
            String subComm = "";
            index = comm.indexOf("?");
            if (index != -1) {
                subComm = comm.substring(index + 1);
                comm = comm.substring(0, index);
            }

            if (comm.equals("등록")) {
                System.out.print("명언 : ");
                String quote = br.readLine();
                System.out.print("작가 : ");
                String author = br.readLine();

                quotesList.add(new Quotes(id, quote, author));
                System.out.printf("%d번 명언이 등록되었습니다.\n", id++);
            }

            if (comm.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                try {
                    File file = new File("E:/study/QuotesApp/quote.txt");

                    if (file.exists()) {
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        String line = bufferedReader.readLine();
                        while (line != null) {
                            System.out.println(line);
                            line = bufferedReader.readLine();
                        }
                    }
//                    else {
//                        quotesList.stream()
//                                .sorted((e1, e2) -> e2.getId() - e1.getId())
//                                .forEach(e -> System.out.println(e.toString()));
//                    }
                }
                catch (Exception e) {
                    e.getStackTrace();
                }
            }

            if (comm.equals("삭제")) {
                int rmIndex = subComm.indexOf("=");
                int rmId = Integer.parseInt(subComm.substring(rmIndex + 1));
                boolean isExist = false;

                for (Quotes quote : quotesList) {
                    if (quote.getId() == rmId) {
                        quotesList.remove(quote);
                        System.out.printf("%d번 명언이 삭제되었습니다.\n", rmId);
                        isExist = true;
                        break;
                    }
                }
                if (isExist == false) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", rmId);
                }
            }

            if (comm.equals("수정")) {
                int upIndex = subComm.indexOf("=");
                int upId = Integer.parseInt(subComm.substring(upIndex + 1));
                boolean isExist = false;

                for (Quotes quote : quotesList) {
                    if (quote.getId() == upId) {
                        System.out.println("명언(기존) :" + quote.getQuote());
                        System.out.print("명언 : ");
                        quote.setQuote(br.readLine());

                        System.out.println("작가(기존) :" + quote.getAuthor());
                        System.out.print("작가 : ");
                        quote.setAuthor(br.readLine());

                        isExist = true;
                        break;
                    }
                }
                if (isExist == false) {
                    System.out.printf("%d번 명언은 존재하지 않습니다.\n", upId);
                }
            }
        }

        File file = new File("E:/study/QuotesApp/quote.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        quotesList.stream()
                .forEach(e -> {
                    try {
                        writer.write(e.toString());
                        writer.newLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
        writer.flush();
        writer.close();

        System.out.println("명언 앱을 종료합니다.");
    }
}

class Quotes {
    int id;
    String quote;
    String author;

    public Quotes(int id, String quote, String author) {
        this.id = id;
        this.quote = quote;
        this.author = author;
    }

    @Override
    public String toString() {
        return id + " / " + author + " / " + quote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}