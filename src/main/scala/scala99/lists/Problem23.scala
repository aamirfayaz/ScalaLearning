package scala99.lists

import scala.util.Random
import scala99.lists.RemoveKthElementFromList._

object SelectRandom extends App {

  val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

  def randomSelect[A](list: List[A], index: Int,r:Random) = {
    def recursiveSelect(list: List[A],acc: List[A], index: Int): List[A] = {
      if (index <= 0) acc else {
        val (rest, e) = removeAt(r.nextInt(list.length),list)
        recursiveSelect(rest, e :: acc, index - 1)
      }
    }
    recursiveSelect(list, Nil, index)
  }

  println(randomSelect(l,4,new Random()))
}