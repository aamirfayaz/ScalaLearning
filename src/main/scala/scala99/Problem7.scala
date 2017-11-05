package scala99

object FlattenList extends App {

  def flatten(x: List[Any]): List[Any] = {
    x flatMap {
      case n: List[_] => flatten(n)
      case e => List(e)
    }
  }

  println("flatten result = " + flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
  println(List(List(1, 1), List(2), List(List(3), List(5, 8)).flatten))

  abstract sealed class NestedList[T]

  case class Value[T](v: T) extends NestedList[T]

  case class Sequence[T](list: List[NestedList[T]]) extends NestedList[T]

  def f2[T]: NestedList[T] => List[T] = {
    case Value(v) => List(v)
    case Sequence(l) => l.flatMap(f2)
  }

  val list: List[NestedList[Int]] = List[Value[Int]](Value(1), Value(2), Value(3))

  val l = Sequence(list)
  val res = f2(l)
  println(s"functional ==> $res")

}
