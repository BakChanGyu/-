package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        String comm = "";

        System.out.println("== 명언 앱 ==");
        while (!comm.equals("종료")) {
            System.out.print("명령) ");
            comm = br.readLine();
            if (comm.equals("등록")) {
                System.out.print("명언 : ");
                String quote = br.readLine();
                System.out.print("작가 : ");
                String author = br.readLine();
                System.out.println("1번 명언이 등록되었습니다.");
            }
        }



    }
}