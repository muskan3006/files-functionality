package com.knoldus

import java.io.FileWriter

import scala.io.Source

class WordCount {

  def countWords(fileName: String, outputDir: String): Map[String, Int] = {
    val file = outputDir + " " + fileName.split("/").last
    val fw = new FileWriter(file)
    val source = Source.fromFile(fileName)
    val words = source.getLines.flatMap(_.split("\\s+"))
      .foldLeft(Map.empty[String, Int]) { (count, word) => count + (word.toLowerCase -> (count.getOrElse(word.toLowerCase, 0) + 1)) }
    source.close()
    fw.close()
    words
  }


}

