package recursion

import scala.annotation.tailrec

object Factorial extends App {

  def factorial(n: Int): Long = {
    @tailrec
    def loop(x: Int = n, acc: Long = 1): Long = {
      if (x <= 1) acc
      else loop(x - 1, x * acc)
    }

    loop()
  }

  def factorialRec(n: Int): Long = {
    if (n <= 1) 1
    else n * factorialRec(n - 1)

  }

  println(factorial(15))
  println(factorial(15))

}
