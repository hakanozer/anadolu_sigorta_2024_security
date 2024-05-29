package com.works.fileSecurity;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainFileSecur {
    public static void main(String[] args) throws Exception {

        String path = "src/main/resources/resim.jpg";
        FileInputStream fileInputStream = new FileInputStream(path);

        FileChannel fileChannel = fileInputStream.getChannel();
        long size = fileChannel.size();

        ByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        CharBuffer charBuffer = StandardCharsets.ISO_8859_1.decode(byteBuffer);

        Scanner scanner = new Scanner(charBuffer.toString());
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.contains("<?php")) {
                System.err.println("Php Error Line");
            }
            System.out.println(line);
        }



    }
}
