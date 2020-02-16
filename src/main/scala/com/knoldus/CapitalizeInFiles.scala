package com.knoldus

import java.io.{File, FileWriter}

import scala.io.Source

class CapitalizeInFiles(directoryPath: String) {

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

  def getFiles: List[File] = {
    val directory = new File(directoryPath)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.toList
    } else {
      List[File]()
    }
  }
}

object A extends App {



}