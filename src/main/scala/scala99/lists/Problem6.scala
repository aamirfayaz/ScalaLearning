package scala99.lists

//check if list is palindrome
object PalindromeCheck extends App {

  val l = List(1, 2, 2, 1)
  println("is list palindrome == " + (l == l.reverse)) // == compares values
  println("is list palindrome eq = " + (l eq l.reverse)) //eq compares reference

  val isPal = l.zip(l.reverse)
                .forall { case (a,b) => a == b }
  println("is list palindrome = " + isPal)

}