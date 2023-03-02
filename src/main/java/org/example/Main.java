package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            String rmComm = "";
            index = comm.indexOf("?");
            if (index != -1) {
                rmComm = comm.substring(index + 1);
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
                quotesList.stream()
                        .sorted((e1, e2) -> e2.getId() - e1.getId())
                        .forEach(e -> System.out.println(e.toString()));
            }

            if (comm.equals("삭제")) {
                int rmIndex = rmComm.indexOf("=");
                int rmId = Integer.parseInt(rmComm.substring(rmIndex + 1));

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
        }
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