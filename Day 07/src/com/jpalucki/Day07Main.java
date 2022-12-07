package com.jpalucki;

import java.util.*;

public class Day07Main {

  private static final String CMD_MARKER = "$";
  private static final String CD_CMD = "$ cd";
  private static final String LS_CMD = "$ ls";
  private static final String PARENT_DIR = "..";
  private static final String DIR_OUTPUT = "dir";

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    Map<String, Set<String>> directoriesContents = new HashMap<String, Set<String>>();
    String currentDir = "";
    int currentDirTotalFileSize = 0;

    for (String line : INPUT.split("\n")) {
      if (line.startsWith(CMD_MARKER)) {
        if (currentDirTotalFileSize > 0) {
          addContent(directoriesContents, currentDir, String.valueOf(currentDirTotalFileSize));
          currentDirTotalFileSize = 0;
        }
      }

      if (line.startsWith(CD_CMD)) {
        String newDir = line.split(" ")[2];
        if (newDir.equals(PARENT_DIR)) {
          currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
        } else if (newDir.equals("/")) {
          currentDir = "/";
        } else {
          if (!currentDir.equals("/")) {
            currentDir = currentDir + "/";
          }
          currentDir = currentDir + newDir;
        }
        continue;
      }

      if (line.startsWith(LS_CMD)) {
        continue;
      }

      if (line.startsWith(DIR_OUTPUT)) {
        String newDir = line.split(" ")[1];
        addContent(directoriesContents, currentDir, newDir);
        continue;
      }

      int fileSize = Integer.parseInt(line.split(" ")[0]);
      currentDirTotalFileSize += fileSize;
    }

    if (currentDirTotalFileSize > 0) {
      addContent(directoriesContents, currentDir, String.valueOf(currentDirTotalFileSize));
    }

    System.out.println(directoriesContents);

    int desiredDirsTotalSize = 0;
    for (Map.Entry<String, Set<String>> entry: directoriesContents.entrySet()) {
      int dirSize = getDirSize(directoriesContents, entry.getKey());
      if (dirSize <= 100000) {
        System.out.println(entry.getKey() + ": " + dirSize);
        desiredDirsTotalSize += dirSize;
      }
    }

    System.out.println("Total size of dirs below 100K is: " + desiredDirsTotalSize);
  }

  private static void addContent(Map<String, Set<String>> directoriesContents, String dir, String content) {
    if (directoriesContents.containsKey(dir)) {
      directoriesContents.get(dir).add(content);
    } else {
      directoriesContents.put(dir, new HashSet<String>(Collections.singletonList(content)));
    }
  }

  private static int getDirSize(Map<String, Set<String>> directoriesContents, String dir) {
    int directoryTotalSize = 0;
    dir = dir.replace("//", "/");
    if (!directoriesContents.containsKey(dir)) {
      System.out.println("Directory: " + dir + " is not contained in map!");
      return directoryTotalSize;
    }

    for (String item : directoriesContents.get(dir)) {
      try {
        directoryTotalSize += Integer.parseInt(item);
      } catch (NumberFormatException e) {
        directoryTotalSize += getDirSize(directoriesContents, dir + "/" + item);
      }
    }

    return directoryTotalSize;
  }
}
