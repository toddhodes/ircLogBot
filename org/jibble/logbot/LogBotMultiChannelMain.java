package org.jibble.logbot;

import java.io.*;
import java.util.*;

public class LogBotMultiChannelMain {
    
    public static void main(String[] args) throws Exception {
        
        Properties p = new Properties();
        if (args.length > 0)
          p.load(new FileInputStream(new File(args[0])));
        else 
          p.load(new FileInputStream(new File("./config.ini")));
        
        String server = p.getProperty("Server", "localhost");
        String channelLine = p.getProperty("Channels", "#test");
        String nick = p.getProperty("Nick", "LogBot");
        String joinMessage = p.getProperty("JoinMessage", "This channel is logged.");

        String[] channels = channelLine.split(" ");
        for (String channel : channels) {
          String cname = channel.replace("#", "");
          File outDir = new File(p.getProperty("OutputDir", "./output/") + cname + "/");
          outDir.mkdirs();
          if (!outDir.isDirectory()) {
            System.out.println("Cannot make output directory (" + outDir + ")");
            System.exit(1);
          }
          LogBot.copy(new File("html/header.inc.php"), new File(outDir, "header.inc.php"));
          LogBot.copy(new File("html/footer.inc.php"), new File(outDir, "footer.inc.php"));
          LogBot.copy(new File("html/index.php"), new File(outDir, "index.php"));

          BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outDir, "config.inc.php")));
          writer.write("<?php");
          writer.newLine();
          writer.write("    $server = \"" + server + "\";");
          writer.newLine();
          writer.write("    $channel = \"" + channel + "\";");
          writer.newLine();
          writer.write("    $nick = \"" + nick + "_" +cname + "\";");
          writer.newLine();
          writer.write("?>");
          writer.flush();
          writer.close();
        
          LogBot bot = new LogBot(nick + "_" +cname, outDir, joinMessage);
          bot.connect(server);
          bot.joinChannel(channel);
        }
    }
    
}
