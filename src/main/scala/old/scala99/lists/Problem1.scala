package scala99.lists

//Find last element of a list
object LastElement extends App {

  val l = List(1, 2, 3, 4, 5, 6)
  println("last element of list is = " + l.last)

  def recursiveLast[T](l: List[T]): T = l match {
    case h :: Nil  => h
    case _ :: tail => recursiveLast(tail)
    case _         => throw new NoSuchElementException("List is empty")
  }

  println("last element of list recursive is = " + recursiveLast[Int](l))

  def reduceMethod[T](list: List[T]): T = if (list.nonEmpty) list.reduce((_, b) => b) else throw new NoSuchElementException("List is empty")

  println("last element of list folding is = " + reduceMethod[Int](l))

}
