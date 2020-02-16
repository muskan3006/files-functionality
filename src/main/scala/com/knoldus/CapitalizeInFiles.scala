package com.knoldus

import java.io.{File, FileWriter}

import scala.io.Source

/**
 * In this class we take a directory as input, search files in it and then capitalize the words in the files
 * Also the modified content is saved to another file with same name but different directory
 **/
class CapitalizeInFiles(directoryPath: String) {
  /**
   * It capitalizes the content of file
   * @param filename name of the file
   * @param outputDirectory output directory
   * @return
   */
  def capitalContent(filename: File, outputDirectory: String): String = {
    val copyFileName = filename.getName
    val fw = new FileWriter(s"$outputDirectory/$copyFileName")
    val source = Source.fromFile(filename)
    val content = source.mkString
    fw.write(content.toUpperCase() + "\n")
    fw.close()
    source.close()
    fw.toString
  }

  /**
   * This function basically search that if we have a file then what to do
   * @param listOfFile a list of files in the directory
   * @param outputDirectory output directory
   * @param result an empty list that stores the altered file
   * @return
   */
  def capitalizingContent(listOfFile: List[File], outputDirectory: String, result: List[String]): List[String] = {
    listOfFile match {
      case head :: tail if head.isFile =>
        val transformedContent = capitalContent(head, outputDirectory)
        capitalizingContent(tail, outputDirectory, transformedContent :: result)
      case head :: Nil if head.isFile =>
        val transformedContent: String = capitalContent(head, outputDirectory)
        transformedContent :: result
      case Nil => result

    }
  }

  /**
   * this method returs a list of files in the directory
   * @return
   */
  def getFiles: List[File] = {
    val directory = new File(directoryPath)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.toList
    } else {
      List[File]()
    }
  }
}
