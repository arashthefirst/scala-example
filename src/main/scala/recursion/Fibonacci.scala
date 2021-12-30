package recursion

import scala.annotation.tailrec

//tail recursion vs conventional recursion
object Fibonacci extends App{


  def fibonacci(n: Int): Long = {
    @tailrec
    def fibo(counter: Int = 0, prev1: Long = 0, prev2: Long = 1): Long = {
      if (n <= 1) n
      else if (counter + 2 <= n) fibo(counter + 1, prev2, prev2 + prev1)
      else prev2
    }

    fibo()
  }

  def fibonacciRec(n: Int): Long = {
    if (n <= 1) n
    else fibonacciRec(n - 1) + fibonacciRec(n - 2)
  }

  println(fibonacci(15))
  println(fibonacciRec(15))

  // with the second version you will get a stackoverflow error if you try bigger numbers


}
