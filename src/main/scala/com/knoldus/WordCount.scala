package com.knoldus

import scala.io.Source

/**
 * In this class we count how many times a word occured
 */
class WordCount {
  /**
   * It takes a file as input and count the words
   *
   * @param fileName file name of which you want to count the words
   * @return
   */
  def countWords(fileName: String): Map[String, Int] = {
    val source = Source.fromFile(fileName)
    val words = source.getLines.flatMap(_.split("\\s+"))
      .foldLeft(Map.empty[String, Int]) { (count, word) => count + (word.toLowerCase -> (count.getOrElse(word.toLowerCase, 0) + 1)) }
    source.close()
    words
  }
}




